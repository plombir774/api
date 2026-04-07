import java.sql.*;

public class DBConnection {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://localhost:5432/qa_test";
        String user = "postgres";
        String password = "1234";

        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM users");

        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }

        connection.close();
    }
}