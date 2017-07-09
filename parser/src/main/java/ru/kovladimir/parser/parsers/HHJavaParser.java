package ru.kovladimir.parser.parsers;

import ru.kovladimir.parser.Vacancy;
import ru.kovladimir.parser.parsers.HHParser;
import ru.kovladimir.parser.parsers.Parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Parser that gets only Java-vacancies from site hh.ru.
 */
public class HHJavaParser implements Parser{

    /**
     * Basic parser.
     */
    private Parser parser = new HHParser("java");

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getParserName() {
        return "HHJava";
    }

    /**
     * Filter all vacancies. Include only those who contains word "Java" and
     * doesn't contain words "Java Script" or "JavaScript".
     * @return Set.
     */
    @Override
    public Set<Vacancy> getAllVacancies() {
        Set<Vacancy> validVacancies = new HashSet<>();
        Set<Vacancy> allVacancies = parser.getAllVacancies();
        for (Vacancy vacancy : allVacancies) {
            String name = vacancy.getName().toLowerCase();
            if (!name.contains("javascript") && !name.contains("java script") && name.contains("java")) {
                validVacancies.add(vacancy);
            }
        }
        return validVacancies;
    }


}
