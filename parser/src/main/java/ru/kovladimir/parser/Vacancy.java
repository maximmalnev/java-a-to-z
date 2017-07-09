package ru.kovladimir.parser;

import java.util.Calendar;
import java.util.Objects;

/**
 * Vacancy.
 */
public class Vacancy {

    /**
     * Name of vacancy.
     */
    private String name;

    /**
     * Salary.
     */
    private String salary;

    /**
     * Vacancy's description.
     */
    private String description;

    /**
     * URL to see full information.
     */
    private String url;

    /**
     * Company's name.
     */
    private String company;

    /**
     * Date of creation.
     */
    private Calendar date;

    /**
     * City.
     */
    private String city;

    /**
     * Constructor.
     * @param name String.
     * @param description String.
     * @param url String.
     * @param company String.
     * @param date Calendar.
     * @param city String.
     */
    public Vacancy(String name, String description, String url, String company, Calendar date, String city) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.company = company;
        this.date = date;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name) &&
                Objects.equals(salary, vacancy.salary) &&
                Objects.equals(description, vacancy.description) &&
                Objects.equals(url, vacancy.url) &&
                Objects.equals(company, vacancy.company) &&
                Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, description, url, company, date);
    }
}
