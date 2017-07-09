package ru.kovladimir.parser.report;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import ru.kovladimir.parser.Vacancy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HtmlFileCreatorTest {

    @Test
    public void whenTwoVacanciesThenReturnTableWithTwoRows() throws IOException {
        String separator = File.separator;
        String pathToReportDirectory = "src" + separator + "test" + separator + "resources";
        ReportCreator creator = new HtmlFileCreator(pathToReportDirectory, "report.html");
        Vacancy vacancy1 = getJavaVacancyVersionOne();
        Vacancy vacancy2 = getJavaVacancyVersionTwo();
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy1);
        vacancies.add(vacancy2);

        creator.createReport(vacancies);
        File report = new File(pathToReportDirectory, "report.html");
        File example = new File(pathToReportDirectory, "example_report_2java.html");
        boolean isValid = FileUtils.contentEquals(report, example);
        Files.delete(Paths.get(new File(pathToReportDirectory, "report.html").getAbsolutePath()));

        assertThat(isValid, is(true));
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

}