package ru.kovladimir.parser.parsers;


import ru.kovladimir.parser.Vacancy;

import java.util.Set;

/**
 * Parser that gets vacancies from site.
 */
public interface Parser {

    /**
     * Get all vacancies from site.
     * @return Set<Vacancy>
     */
    Set<Vacancy> getAllVacancies();

    /**
     * Get unique parser's name.
     * @return String.
     */
    String getParserName();

}
