import YahayaRabiu.FirstProject.Developers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class Developer implements Developers
{
    @Override
    public ResultSet loadDevelopers() throws SQLException {
        //Initialise Connection
        Connection connection=null;
        //Initialise Resultset
        ResultSet resultSet= null;
        try{
            // Database credential
            String url="jdbc:mysql://localhost:3307/developer";
            String userName="root";
            String password="1234567890";
            // make connectiton
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection created");
            // Create statement
            Statement statement = connection.createStatement();
            // Create Table Query
            String createTable = "CREATE TABLE IF NOT EXISTS developer( name Text, age Integer, location Text, skill Text)";
            // Execute the creation of table query
            statement.execute(createTable);
            System.out.println("Table created");
            // FIle Path
            String fileName= "C:\\Users\\HP\\Documents\\project.txt";
            // Get The content of a file
            String data = new String(
                    Files.readAllBytes(Paths.get(fileName)));
            // Split the content of the file with new line
            List<String> statements= List.of(data.split("\n"));
            for (String s : statements) {
                // Split  each line with a comma and space
                List<String> eachLine = List.of(s.split(", "));
                // to remove the last #
                String skill = eachLine.get(3).substring(0, eachLine.get(3).length() - 2);
               // Insert statement for each developer
                String insertUserSQL3 = "INSERT INTO developer(name, age, location, skill) VALUES('" + eachLine.get(0) + "'," + Integer.parseInt(eachLine.get(1)) + "," + "'" + eachLine.get(2) + "'" + "," + "'" + skill + "'" + ")";
                // Execute the insert statement
                statement.execute(insertUserSQL3);
                System.out.println("Inserted successfully");

            }
            // Query statement to get all developers form the table
            String queryStatement = "SELECT * FROM developer";
            // Execute the statement to get resultset
            resultSet = statement.executeQuery(queryStatement);

        }catch(Exception ex){
            System.out.println("An Error occurred "+ ex.getMessage());
        }
        finally {

            if (connection != null) {
                connection.close();
            }


        }
        return resultSet;
    }

    public static void main(String[] args) throws SQLException {
        Developer developer= new Developer();
        developer.loadDevelopers();
    }
}
