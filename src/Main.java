import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/productdb";
        String user = "postgres";
        String password = "Ch11072003$";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection(url, user, password);


            statement = connection.createStatement();



            String insertDataSQL = "INSERT INTO Product (name, price_per_unit, active_for_sell) VALUES " +
                    "('Product1', 10.5, TRUE), " +
                    "('Product2', 20.0, FALSE), " +
                    "('Product3', 15.75, TRUE) " +
                    "ON CONFLICT DO NOTHING;";
            statement.executeUpdate(insertDataSQL);


            resultSet = statement.executeQuery("SELECT * FROM Product");


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double pricePerUnit = resultSet.getDouble("price_per_unit");
                boolean activeForSell = resultSet.getBoolean("active_for_sell");

                System.out.println("ID: " + id + ", Name: " + name + ", Price per Unit: " + pricePerUnit + ", Active for Sell: " + activeForSell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
