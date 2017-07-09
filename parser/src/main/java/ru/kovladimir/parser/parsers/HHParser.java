package ru.kovladimir.parser.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.kovladimir.parser.Vacancy;
import ru.kovladimir.parser.date.HHDateConverter;

import java.io.IOException;
import java.util.*;

/**
 * Vacancies parser from site hh.ru.
 */
public class HHParser implements Parser {

    /**
     * Value to search.
     */
    private final String vacancyName;

    /**
     * Start url.
     */
    protected final String siteUrl = "https://hh.ru/search/vacancy?area=1261&text=%s&page=%d&nocookies";

    /**
     * Unique parser's name.
     */
    private final String parserName;

    /**
     * Consctructor with value to search.
     * @param vacancyName String.
     */
    public HHParser(String vacancyName) {
        this.vacancyName = vacancyName;
        parserName = "HH: " + vacancyName;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    public String getParserName() {
        return parserName;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Set<Vacancy> getAllVacancies() {
        Set<Vacancy> vacancies = new HashSet<>();
        int pageNumber = 0;
        while (true) {
            Document document = getDocumentFromURL(vacancyName, pageNumber);
            Set<Vacancy> vacanciesFromOnePage = getVacanciesFromDocument(document);
            if (vacanciesFromOnePage.size() == 0) {
                break;
            } else {
                vacancies.addAll(vacanciesFromOnePage);
                pageNumber++;
            }
        }
        return vacancies;
    }

    /**
     * Get document from one page.
     * @param vacancyName Name to search.
     * @param page Page number.
     * @return Document with vacancies.
     */
    protected Document getDocumentFromURL(String vacancyName, int page) {
        String request = String.format(siteUrl, vacancyName, page);
        Document document = null;
        try {
            document = Jsoup.connect(request)
                    .userAgent("Mozilla")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * Get all vacancies from document.
     * @param document Document.
     * @return Set.
     */
    protected Set<Vacancy> getVacanciesFromDocument(Document document) {
        Set<Vacancy> vacancies = new HashSet<>();
        Elements elements = document.select("div[data-qa=\"vacancy-serp__vacancy\"]");

        for (Element element : elements) {
            Vacancy vacancy = getVacancy(element);
            if (vacancy != null) {
                vacancies.add(vacancy);
            }
        }

        return vacancies;
    }

    /**
     * Create Vacancy Object from Element.
     * @param element Element.
     * @return Vacancy.
     */
    protected Vacancy getVacancy(Element element) {
        Vacancy vacancy = new Vacancy(
                getVacancyName(element),
                getDescription(element),
                getURL(element),
                getCompanyName(element),
                getCreatedDate(element),
                getCityName(element)
        );
        vacancy.setSalary(getSalary(element));
        return vacancy;
    }

    /**
     * Get vacancy name.
     * @param element Element.
     * @return String.
     */
    protected String getVacancyName(Element element) {
        return element.select("a[data-qa=\"vacancy-serp__vacancy-title\"]").text();
    }

    /**
     * Get vacancy salary.
     * @param element Element.
     * @return String.
     */
    protected String getSalary(Element element) {
        String salary = element.select("div[data-qa=\"vacancy-serp__vacancy-compensation\"]").text().replaceAll("\\u00A0+", " ");
        String result = null;
        if (!"".equals(salary)) {
            result = salary;
        }
        return result;
    }

    /**
     * Get vacancy description.
     * @param element Element.
     * @return String.
     */
    protected String getDescription(Element element) {
        return element.select("div[class=\"search-result-item__snippet\"]").text();
    }

    /**
     * Get vacancy url.
     * @param element Element.
     * @return String.
     */
    protected String getURL(Element element) {
        return element.select("div[class=\"search-result-item__head\"] > a").attr("href");
    }

    /**
     * Get vacancy company name.
     * @param element Element.
     * @return String.
     */
    protected String getCompanyName(Element element) {
        return element.select("div[class=\"search-result-item__company\"] > a").text();
    }

    /**
     * Get vacancy city.
     * @param element Element.
     * @return String.
     */
    protected String getCityName(Element element) {
        return element.select("div[class=\"search-result-item__info\"] > span[data-qa=\"vacancy-serp__vacancy-address\"]").text();
    }

    /**
     * Get vacancy creation date.
     * @param element Element.
     * @return String.
     */
    protected Calendar getCreatedDate(Element element) {
        String dateString = element.select("div[class=\"search-result-item__info\"] > span[data-qa=\"vacancy-serp__vacancy-date\"]").text();
        return new HHDateConverter().getCalendarDate(dateString);
    }
}
