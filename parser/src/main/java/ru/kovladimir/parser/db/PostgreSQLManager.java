package ru.kovladimir.parser.db;

import ru.kovladimir.parser.Vacancy;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Manager to communicate with PostgreSQL Database.
 */
public class PostgreSQLManager implements DBManager {

    /**
     * Config loader to load DB properties.
     */
    private ConfigLoader loader;

    /**
     * Connection to DB.
     */
    private Connection connection;

    /**
     * Constructor.
     *
     * @param loader ConfigLoader.
     */
    public PostgreSQLManager(ConfigLoader loader) {
        this.loader = loader;
        connection = getConnection();
        createTableStructure();
    }

    /**
     * {@inheritDoc}
     */
    public void openConnection() {
        connection = getConnection();
    }

    /**
     * {@inheritDoc}
     */
    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param vacancy    Vacancy.
     * @param parserName Unique name of parser.
     */
    public void addVacancy(Vacancy vacancy, String parserName) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO vacancies (name, salary, description, url, city, company, published_date, parser, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getSalary());
            statement.setString(3, vacancy.getDescription());
            statement.setString(4, vacancy.getUrl());
            statement.setString(5, vacancy.getCity());
            statement.setInt(6, getCompanyID(vacancy.getCompany()));
            statement.setDate(7, new Date(vacancy.getDate().getTimeInMillis()));
            statement.setInt(8, getParserID(parserName));
            statement.setInt(9, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param parserName String.
     * @return
     */
    public Map<Integer, Vacancy> getVacanciesByParser(String parserName) {
        Map<Integer, Vacancy> vacancies = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT v.id, v.name AS vname, v.salary, v.description," +
                " v.url, v.city, c.name AS cname, v.published_date, s.name AS sname FROM vacancies AS v " +
                "INNER JOIN companies AS c ON v.company = c.id " +
                "INNER JOIN status AS s ON v.status = s.id " +
                "INNER JOIN parsers AS p ON v.parser = p.id " +
                "WHERE p.name = ? AND v.status = 1")) {
            statement.setString(1, parserName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                Vacancy vacancy = getVacancy(resultSet);

                vacancies.put(id, vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    public Map<Integer, Vacancy> getAllActiveVacancies() {
        Map<Integer, Vacancy> vacancies = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT v.id, v.name AS vname, v.salary, v.description," +
                    " v.url, v.city, c.name AS cname, v.published_date, s.name AS sname FROM vacancies AS v " +
                    "INNER JOIN companies AS c ON v.company = c.id " +
                    "INNER JOIN status AS s ON v.status = s.id " +
                    "WHERE v.status = 1");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");

                Vacancy vacancy = getVacancy(resultSet);

                vacancies.put(id, vacancy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    /**
     * {@inheritDoc}
     * @param vacancyID id.
     */
    public void setStatusOld(int vacancyID) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE vacancies SET status = 2 WHERE id = ?")) {
            statement.setInt(1, vacancyID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get parser's ID by its name from table. Add if it's not in the table yet.
     *
     * @param parserName String.
     * @return id.
     */
    private int getParserID(String parserName) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM parsers WHERE name = ?"
        )) {
            statement.setString(1, parserName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            } else {
                id = addParser(parserName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Get company's ID by its name from table. Add if it's not in the table yet.
     *
     * @param company String.
     * @return id.
     */
    private int getCompanyID(String company) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM companies WHERE lower(name) = ?"
        )) {
            statement.setString(1, company.toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            } else {
                id = addCompany(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Add parser's name to database.
     *
     * @param parserName String.
     * @return id.
     */
    private int addParser(String parserName) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO parsers (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
        )) {
            id = insertNewRow(statement, parserName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Add company's name to database.
     *
     * @param company String.
     * @return id.
     */
    private int addCompany(String company) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO companies (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS
        )) {
            id = insertNewRow(statement, company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Insert new row by PreparedStatement.
     *
     * @param statement PreparedStatement with query.
     * @param value     Value to insert.
     * @return id.
     * @throws SQLException
     */
    private int insertNewRow(PreparedStatement statement, String value) throws SQLException {
        statement.setString(1, value);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt(1);
    }

    /**
     * Get next vacancy from ResultSet.
     * @param resultSet ResultSet with rows.
     * @return Vacancy.
     * @throws SQLException
     */
    private Vacancy getVacancy(ResultSet resultSet) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date(resultSet.getDate("published_date").getTime()));

        Vacancy vacancy = new Vacancy(
                resultSet.getString("vname"),
                resultSet.getString("description"),
                resultSet.getString("url"),
                resultSet.getString("cname"),
                calendar,
                resultSet.getString("city")
        );
        vacancy.setSalary(resultSet.getString("salary"));
        return vacancy;
    }

    /**
     * Create connection.
     * @return Connection.
     */
    private Connection getConnection() {
        loader.loadConfig();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(loader.getUrl(), loader.getUserName(), loader.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Create all tables.
     */
    private void createTableStructure() {
        try {
            createStatusTable();
            createParsersTable();
            createCompaniesTable();
            createVacanciesTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create status table.
     * @throws SQLException
     */
    private void createStatusTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS status (id SERIAL PRIMARY KEY, name VARCHAR(20))"
        )) {
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO status (id, name) SELECT 1, 'ACTIVE' WHERE NOT exists(" +
                        "SELECT id FROM status WHERE id = 1 AND name = 'ACTIVE')"
        )) {
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO status (id, name) SELECT 2, 'OLD' WHERE NOT exists(" +
                        "SELECT id FROM status WHERE id = 2 AND name = 'OLD')"
        )) {
            statement.executeUpdate();
        }
    }

    /**
     * Create parsers table.
     * @throws SQLException
     */
    private void createParsersTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS parsers (id SERIAL PRIMARY KEY, name VARCHAR(100))"
        )) {
            statement.executeUpdate();
        }
    }

    /**
     * Create companies table.
     * @throws SQLException
     */
    private void createCompaniesTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS companies (id SERIAL PRIMARY KEY, name VARCHAR(100))"
        )) {
            statement.executeUpdate();
        }
    }

    /**
     * Create main vacancies table.
     * @throws SQLException
     */
    private void createVacanciesTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS vacancies (id SERIAL PRIMARY KEY, name VARCHAR(100), salary VARCHAR(40)," +
                        "description TEXT, url TEXT, city VARCHAR(50), company INT REFERENCES companies(id), published_date DATE," +
                        "parser INT REFERENCES parsers(id), status INT REFERENCES status(id))"
        )) {
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT setval('vacancies_id_seq', COALESCE((SELECT MAX(id)+1 FROM vacancies), 1), false);"
        )) {
            statement.executeQuery();
        }
    }


}
