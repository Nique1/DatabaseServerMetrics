package package1;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String connectionString = "jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        try {
            try (Connection conn = DriverManager.getConnection(connectionString)) {
                System.out.println("Connection established");
                Statement statement = conn.createStatement();
                String sqlQuery = "SELECT TOP 5 * FROM Customers";


                ResultSet resultSet = statement.executeQuery(sqlQuery);


                while (resultSet.next()) {
                    // Retrieve and display data from the result set
                    String customerID = resultSet.getString("CustomerID");
                    String companyName = resultSet.getString("CompanyName");
                    // Add more fields as needed

                    System.out.println("Customer ID: " + customerID);
                    System.out.println("Company Name: " + companyName);
                    // Print other fields as needed


                }

                // Close the resources
                resultSet.close();

                statement.close();
                conn.close();
            }

        } catch (SQLException ex) {
            System.out.println("Error connecting to the database");
            ex.printStackTrace();
        }
    }
}

