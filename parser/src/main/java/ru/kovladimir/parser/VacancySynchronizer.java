package ru.kovladimir.parser;

import ru.kovladimir.parser.db.DBManager;
import ru.kovladimir.parser.db.PostgreSQLManager;
import ru.kovladimir.parser.parsers.Parser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Vacancies' synchronizer that get vacancies from multiple parser
 * and write them to database through DB-manager.
 */
public class VacancySynchronizer {

    /**
     * DB Manager.
     */
    private final DBManager manager;

    /**
     * All parsers from multiple sites.
     */
    private List<Parser> parsers;

    /**
     * Constructor.
     *
     * @param manager DB manager.
     * @param parsers all parsers.
     */
    public VacancySynchronizer(DBManager manager, List<Parser> parsers) {
        this.manager = manager;
        this.parsers = parsers;
    }

    /**
     * Synchronize database's vacancies.
     */
    public void synchronizeVacancies() {
        for (Parser parser : parsers) {
            synchronizeVacanciesFromParser(getVacanciesFromDB(parser.getParserName()), getVacanciesFromSite(parser), parser.getParserName());
        }
    }

    /**
     * Synchronize vacancies from one site.
     *
     * @param fromDB     vacancies from database.
     * @param fromSite   vacancies from site.
     * @param parserName parser's unique name.
     */
    private void synchronizeVacanciesFromParser(Map<Integer, Vacancy> fromDB, Set<Vacancy> fromSite, String parserName) {
        refreshOldVacancies(fromDB, fromSite);
        addAllNewVacancies(fromSite, parserName);
    }

    /**
     * Refresh oll vacancies in database.
     * If there is no vacancy on the site then mark this as "old".
     *
     * @param fromDB   vacancies from database.
     * @param fromSite vacancies from site.
     */
    private void refreshOldVacancies(Map<Integer, Vacancy> fromDB, Set<Vacancy> fromSite) {
        for (Map.Entry<Integer, Vacancy> entry : fromDB.entrySet()) {
            boolean wasRemoved = fromSite.remove(entry.getValue());
            if (!wasRemoved) { // that means that this vacancy is not on site anymore
                manager.setStatusOld(entry.getKey());
            }
        }
    }

    /**
     * Add all new vacancies that database doesn't contain.
     *
     * @param fromSite   new vacancies.
     * @param parserName parser's name.
     */
    private void addAllNewVacancies(Set<Vacancy> fromSite, String parserName) {
        for (Vacancy vacancy : fromSite) {
            manager.addVacancy(vacancy, parserName);
        }
    }

    /**
     * Get only those vacancies from database date were parsed by
     * exactly one parser that has name from args.
     *
     * @param parserName name of the parser.
     * @return vacancies from database.
     */
    private Map<Integer, Vacancy> getVacanciesFromDB(String parserName) {
        return manager.getVacanciesByParser(parserName);
    }

    /**
     * Get all vacancies from one site by parser.
     *
     * @param parser parser.
     * @return vacancies from site.
     */
    private Set<Vacancy> getVacanciesFromSite(Parser parser) {
        return parser.getAllVacancies();
    }

}
