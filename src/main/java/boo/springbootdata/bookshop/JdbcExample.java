package boo.springbootdata.bookshop;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class JdbcExample {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/bookshop";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String STATEMENT = "SELECT id, title, number_of_pages, price, date_of_publishing FROM books";
    private static final String PREPARED_STATEMENT = "INSERT INTO BOOKS (title, price) VALUES (?, ?)";

    public static void main(String[] arguments) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(STATEMENT)) {
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(", title: \"" + resultSet.getString("title") + "\"");
                System.out.print(", number of pages: " + resultSet.getInt("number_of_pages"));
                System.out.print(", price: " + resultSet.getBigDecimal("price"));
                System.out.println(", date of publishing: " + resultSet.getObject("date_of_publishing",
                        LocalDate.class));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(PREPARED_STATEMENT)) {
            preparedStatement.setString(1, "Brave New World");
            preparedStatement.setBigDecimal(2, BigDecimal.valueOf(55));
            System.out.println(preparedStatement.executeUpdate() + " records inserted");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
