package ru.kovladimir.parser.report;

import ru.kovladimir.parser.Vacancy;

import java.util.List;

/**
 * Create report about available vacancies.
 */
public interface ReportCreator {

    /**
     * Create report about vacancies.
     * @param vacancies all active vacancies.
     */
    void createReport(List<Vacancy> vacancies);

}
