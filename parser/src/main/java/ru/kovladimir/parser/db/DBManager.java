package ru.kovladimir.parser.db;

import ru.kovladimir.parser.Vacancy;

import java.util.Map;

/**
 * Manager to communicate with database.
 */
public interface DBManager {

    /**
     * Open DB-connection.
     */
    void openConnection();

    /**
     * Close DB-connection.
     */
    void closeConnection();

    /**
     * Add vacancy to database.
     * @param vacancy Vacancy.
     * @param parserName Unique name of parser.
     */
    void addVacancy(Vacancy vacancy, String parserName);

    /**
     * Get all vacancies which were parsed by one parser.
     * @param parserName String.
     * @return Pair ID-Vacancy.
     */
    Map<Integer, Vacancy> getVacanciesByParser(String parserName);

    /**
     * Get all vacancies from DB.
     * @return Pair ID-Vacancy.
     */
    Map<Integer, Vacancy> getAllActiveVacancies();

    /**
     * Mark vacancy as old by its id.
     *
     * @param vacancyID id.
     */
    void setStatusOld(int vacancyID);

}
