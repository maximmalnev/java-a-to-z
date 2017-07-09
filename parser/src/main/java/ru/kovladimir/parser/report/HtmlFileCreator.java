package ru.kovladimir.parser.report;

import ru.kovladimir.parser.Vacancy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Creator to html-file.
 */
public class HtmlFileCreator implements ReportCreator {

    /**
     * Directory to create html-file in.
     */
    private File directory;

    /**
     * Report's name.
     */
    private String reportName;

    /**
     * Constructor with path to directory
     *
     * @param pathToReportDirectory String.
     */
    public HtmlFileCreator(String pathToReportDirectory, String reportName) {
        this.directory = new File(pathToReportDirectory);
        this.reportName = reportName;
    }

    /**
     *
     */
    public HtmlFileCreator(String pathToReportDirectory) {
        this(pathToReportDirectory, null);
    }

    /**
     * {@inheritDoc}
     *
     * @param vacancies all active vacancies.
     */
    @Override
    public void createReport(List<Vacancy> vacancies) {
        if (directory.isDirectory()) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getReportFile()), "UTF-8"))
            ) {
                printHeader(writer);
                sortVacancies(vacancies);
                printBody(writer, vacancies);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sort vacancies by creation date.
     *
     * @return Sorted List.
     */
    private List<Vacancy> sortVacancies(List<Vacancy> vacancies) {
        Collections.sort(vacancies, new Comparator<Vacancy>() {
            @Override
            public int compare(Vacancy o1, Vacancy o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return vacancies;
    }

    /**
     * Return new file for report.
     *
     * @return File.
     */
    private File getReportFile() {
        File file;
        if (reportName == null) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = String.format("vacancies_%s.html", formatDate.format(new Date()));
            file = new File(directory, fileName);
        } else {
            file = new File(directory, reportName);
        }
        return file;
    }

    /**
     * Print html-header.
     *
     * @param writer Writer.
     * @throws IOException
     */
    private void printHeader(Writer writer) throws IOException {
        writer.write("<!doctype html><html><head><title>Вакансии</title><meta charset=\"utf-8\"><style>table {" +
                "border: 3px solid black;border-collapse: collapse;}" +
                "td {border: 1px solid black;padding-left: 5px;padding-right: 5px;}</style></head>");
    }

    /**
     * Print body with vacancies.
     *
     * @param writer    Writer.
     * @param vacancies Vacancies.
     * @throws IOException
     */
    private void printBody(Writer writer, List<Vacancy> vacancies) throws IOException {
        writer.write("<body><table>");
        int count = 0;
        for (Vacancy vacancy : vacancies) {
            writer.write("<tr>");
            writer.write(String.format("<td>%d</td><td><a href=\"%s\">%s</a></td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
                    ++count,
                    vacancy.getUrl(),
                    vacancy.getName(),
                    vacancy.getSalary() == null ? "" : vacancy.getSalary(),
                    vacancy.getCompany(),
                    vacancy.getCity(),
                    new SimpleDateFormat("dd-MM-yyyy").format(vacancy.getDate().getTime()),
                    vacancy.getDescription()
            ));
            writer.write("</tr>");
        }
        writer.write("</table></body></html>");
    }
}
