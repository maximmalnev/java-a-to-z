package ru.kovladimir.parser;

import ru.kovladimir.parser.db.ConfigLoader;
import ru.kovladimir.parser.db.DBManager;
import ru.kovladimir.parser.db.PostgreSQLManager;
import ru.kovladimir.parser.parsers.HHJavaParser;
import ru.kovladimir.parser.parsers.Parser;
import ru.kovladimir.parser.report.HtmlFileCreator;
import ru.kovladimir.parser.report.ReportCreator;

import java.util.*;

/**
 * Main class.
 */
public class Start {

    /**
     * Params from user.
     */
    private Params params;

    /**
     * Database manager to communicate with database.
     */
    private DBManager manager;

    /**
     * Report creator.
     */
    private ReportCreator reportCreator;

    /**
     * Vacancies' updater.
     */
    private VacancySynchronizer synchronizer;

    /**
     * Constructor.
     *
     * @param params String.
     */
    public Start(Params params) {
        this.params = params;
    }

    /**
     * Start program.
     */
    private void start() {
        if (params.isValid()) {
            init();
            startParsers();
            waitForStop();
            createReport();
        } else {
            System.out.println(params.getError());
        }
    }

    /**
     * Initialize all fields.
     */
    private void init() {
        manager = new PostgreSQLManager(new ConfigLoader(params.getPathToConfig()));

        reportCreator = new HtmlFileCreator(params.getDirectoryForReport());

        List<Parser> parsers = new ArrayList<>();
        parsers.add(new HHJavaParser());
        synchronizer = new VacancySynchronizer(manager, parsers);
    }

    /**
     * Start parsers.
     */
    private void startParsers() {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                manager.openConnection();
                synchronizer.synchronizeVacancies();
                manager.closeConnection();
            }
        };
        timer.schedule(task, 1000, params.getPeriod() * 60 * 1000);
    }

    /**
     * Wait until user type "stop".
     */
    private void waitForStop() {
        Scanner scanner = new Scanner(System.in);
        while (!"stop".equals(scanner.nextLine())) {

        }
    }

    /**
     * Create report with all active vacancies from database.
     */
    private void createReport() {
        manager.openConnection();
        reportCreator.createReport(new ArrayList<>(manager.getAllActiveVacancies().values()));
        manager.closeConnection();
    }

    public static void main(String[] args) {
        Params params = new Params(args);
        Start start = new Start(params);
        start.start();
    }
}
