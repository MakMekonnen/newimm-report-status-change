package edu.gmu.cs321;

import static edu.gmu.cs321.Status.valueOf;

import java.sql.*;
import java.util.LinkedList;

import com.cs321.Workflow;

//import com.mysql.cj.protocol.Resultset;

public class Utility {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/cs321";
    private static final String USER = "root";
    private static final String PASSWORD = "SkibidiToilet"; // replace with your MySQL password

    // JDBC variables for opening, closing connection and statement
    private static Connection connection;
    private static Statement statement;

    // Method to establish connection
    public static Connection getConnection() {
        try {
            // Load and register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to create a new user
    public static int createImmigrant(String first, String last, Date dob) {
        // id is autogenerated by the database as I configured the primary key with "AI"
        // in MySQL Workbench UI
        String insertQuery = "insert into immigrant (first, last, dob) VALUES (?, ?, ?)";
        int id = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setDate(3, dob);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Immigrant added successfully.");
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                    id = rs.getInt(1);
            } else {
                System.out.println("Error adding immigrant.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // Method to select all users from the database
    public static void selectImmigrants() {
        String selectQuery = "SELECT * FROM Immigrant";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String dob = rs.getString("dob");
                System.out.println("ID: " + id + ", First: " + first + ", Last: " + last + ", DOB: " + dob);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a user's email based on user ID
    public static void updateImmigrant(int userId, String first, String last) {
        String updateQuery = "UPDATE immigrant SET first = ?, last = ? WHERE id = ?";
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
                
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setInt(3, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static int createForm(int formId, State state, String name, String dob, String aid, Status status){
        String insertQuery = "insert into form (formid, state, name, dob, aid, status) VALUES (?, ?, ?, ?, ?, ?)";
        int id = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, formId);
            stmt.setString(2, state.name());
            stmt.setString(3, name);
            stmt.setString(4, dob);
            stmt.setString(5, aid);
            stmt.setString(6, status.name());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Form added successfully.");
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                    id = rs.getInt(1);
            } else {
                System.out.println("Error adding Form.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return id;
    }
    public static void updateForm(int formId, State state) {
        String updateQuery = "UPDATE form SET state = ? WHERE formId = ?";

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, state.name());
            stmt.setInt(2, formId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Form updated successfully.");
            } else {
                System.out.println("Form not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Form> selectForms(State state){
        String selectQuery = "SELECT * FROM Form WHERE state = ?";
        LinkedList<Form> forms = new LinkedList<Form>();
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(selectQuery);

            stmt.setString(1, state.name());
        
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int formId = rs.getInt("formId");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String aid = rs.getString("aid");
                Status status = valueOf(rs.getString("status"));

                Form current = new Form(aid, name, dob, status);
                current.setFormId(formId);
                current.setState(state);

                forms.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forms;
    }
    public static void updateWorkflow(Workflow workflow, State state){
        return;
    }
    public static void main(String[] args) {
        // Create new users
        // createImmigrant(106, "First John", "First Doe", Date.valueOf("2003-01-01"));
        int id = createImmigrant("Kellie", "Mayham", Date.valueOf("1999-12-01"));
        System.out.println("New Immigrant ID: " + id);

        // Select and display all users
        System.out.println("All users in the database:");
        selectImmigrants();

        // Update user's email
        updateImmigrant(110, "New Guy", "New Who");

        // Select and display all users after update
        // System.out.println("All users after update:");
        selectImmigrants();
    }

}
