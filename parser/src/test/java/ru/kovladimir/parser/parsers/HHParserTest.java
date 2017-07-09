package ru.kovladimir.parser.parsers;

import org.jsoup.Jsoup;
import org.junit.Test;
import ru.kovladimir.parser.Vacancy;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class HHParserTest {

    @Test
    public void whenParseOneVacancyVersionOneThenReturnListWithOneVacancy() throws IOException {
        HHParser parser = new HHParser("java");
        String separator = File.separator;
        String pathToTestFile = "src" + separator + "test" + separator + "resources" + separator + "1java_v1.html";
        Set<Vacancy> list = new HashSet<>();
        list.add(getJavaVacancyVersionOne());

        Set<Vacancy> result = parser.getVacanciesFromDocument(Jsoup.parse(new File(pathToTestFile), "UTF-8", "https://hh.ru/"));

        assertThat(result, is(list));
    }

    @Test
    public void whenParseOneVacancyVersionTwoThenReturnListWithOneVacancy() throws IOException {
        HHParser parser = new HHParser("java");
        String separator = File.separator;
        String pathToTestFile = "src" + separator + "test" + separator + "resources" + separator + "1java_v2.html";
        Set<Vacancy> list = new HashSet<>();
        list.add(getJavaVacancyVersionTwo());

        Set<Vacancy> result = parser.getVacanciesFromDocument(Jsoup.parse(new File(pathToTestFile), "UTF-8", "https://hh.ru/"));

        assertThat(result, is(list));
    }

    @Test
    public void whenParseTwoJavaVacanciesThenReturnListWithTwoJavaVacancies() throws IOException {
        HHParser parser = new HHParser("java");
        String separator = File.separator;
        String pathToTestFile = "src" + separator + "test" + separator + "resources" + separator + "2java.html";
        Set<Vacancy> list = new HashSet<>();
        list.add(getJavaVacancyVersionOne());
        list.add(getJavaVacancyVersionTwo());

        Set<Vacancy> result = parser.getVacanciesFromDocument(Jsoup.parse(new File(pathToTestFile), "UTF-8", "https://hh.ru/"));

        assertThat(result, is(list));
    }

    @Test
    public void whenParseOneJavaOneJSVacanciesThenReturnListWithOneJavaOneJSVacancies() throws IOException {
        HHParser parser = new HHParser("java");
        String separator = File.separator;
        String pathToTestFile = "src" + separator + "test" + separator + "resources" + separator + "1java_1js.html";
        Set<Vacancy> list = new HashSet<>();
        list.add(getJavaVacancyVersionOne());
        list.add(getJSVacancyVersionOne());

        Set<Vacancy> result = parser.getVacanciesFromDocument(Jsoup.parse(new File(pathToTestFile), "UTF-8", "https://hh.ru/"));

        assertThat(result, is(list));
    }

    private Vacancy getJavaVacancyVersionOne() {
        Calendar date = Calendar.getInstance();
        date.set(2016, Calendar.SEPTEMBER, 5);
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.MILLISECOND, date.getActualMinimum(Calendar.MILLISECOND));
        Vacancy vacancy = new Vacancy(
                "Java специалист на поддержку",
                "Разработка, доработка алгоритмов и модулей систем товародвижения. Оптимизация текущего " +
                        "инструментария. Проектирование баз данных и потоков информации." +
                        " Java7SE. SQL через JDBC. VBA — базовый уровень. JavaScript + HTML - базовый уровень. SVN. " +
                        "TDD при помощи JUnit, Agile.",
                "https://hh.ru/vacancy/17461588?query=java",
                "МАГНИТ, Розничная сеть",
                date,
                "Екатеринбург"
        );
        vacancy.setSalary("35 000-46 000 руб.");
        return vacancy;
    }

    private Vacancy getJavaVacancyVersionTwo() {
        Calendar date = Calendar.getInstance();
        date.set(2016, Calendar.SEPTEMBER, 2);
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.MILLISECOND, date.getActualMinimum(Calendar.MILLISECOND));
        return new Vacancy(
                "Разработчик (.NET / Java)",
                "Ваша задача - реализация многопользовательских и распределенных систем на Java или .NET, в" +
                        " том числе с использованием веб-технологий. Опыт работы с Java (Java SE / EE) или .NET" +
                        " (Framework версии 2.0 и выше, C#, ASP.NET) от 1 года.",
                "https://hh.ru/vacancy/15955253?query=java",
                "Экстрим про",
                date,
                "Екатеринбург"
        );
    }

    private Vacancy getJSVacancyVersionOne() {
        Calendar date = Calendar.getInstance();
        date.set(2016, Calendar.SEPTEMBER, 2);
        date.set(Calendar.HOUR_OF_DAY, date.getActualMinimum(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, date.getActualMinimum(Calendar.MINUTE));
        date.set(Calendar.SECOND, date.getActualMinimum(Calendar.SECOND));
        date.set(Calendar.MILLISECOND, date.getActualMinimum(Calendar.MILLISECOND));
        Vacancy vacancy = new Vacancy(
                "Разработчик JavaScript",
                "Интерес к изучению новых языков программирования, фреймворков, технологий разработки. Хороший " +
                        "технический английский. Будет плюсом: Знание jQuery. Опыт использования Node.js, npm, " +
                        "Bower, Grunt. Опыт использования LESS. Знание Java.",
                "https://hh.ru/vacancy/17243688?query=java",
                "Экстрим про",
                date,
                "Екатеринбург"
        );
        vacancy.setSalary("40 000-70 000 руб.");
        return vacancy;
    }

}
