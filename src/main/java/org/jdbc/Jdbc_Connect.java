package org.jdbc;


import java.sql.*;

public class Jdbc_Connect {
    public static void main(String[] args) throws SQLException {

        try (final Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/databaze", "root", "er@nio2000")) {
            dropping(connection);
            insert(connection);
            update(connection,"red",2);
            display(connection);

        } catch (SQLException exp) {
            System.out.println(exp);
        }

    }
    /* dropping */
   private static void  dropping (Connection connection) {
        try (Statement dropStatement = connection.createStatement()) {
            dropStatement.execute("drop table car");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*add*/
    private  static void insert(Connection connection){
        try (Statement createtable = connection.createStatement()){
            String query = "insert into  car (type , color , brand , year ) value ( 'makina', 'blu' , 'bmv' , 2010)" ;
            String query1 = "insert into   car (type , color , brand , year )  value ( 'makina', 'blu' , 'bmv' , 2010)" ;
            String query2 = "insert into    car (type , color , brand , year ) value ('makina', 'blu' , 'bmv' , 2010 )" ;

            createtable.execute(query);
            createtable.execute(query1);
            createtable.execute(query2);

        } catch (SQLException throwables) {
           throwables.printStackTrace();
        }

    }
    /*update*/
    private static void update(Connection connection, String color, int id) {
        String query = "UPDATE car SET color = " + color + " where  id = " +id;
        try (PreparedStatement update = connection.prepareStatement(query)){
       update.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void display(Connection connection) {
        try (Statement displayy = connection.createStatement()) {
            String con = "select * from car";
            ResultSet display = displayy.executeQuery(con);
            while (display.next()) {
                String type = display.getString("type");
                String color = display.getString("color");
                String brand = display.getString("brand");
                int year = display.getInt("year");

                System.out.println("____________________");
                System.out.println("type: " + type);
                System.out.println("color: " + color);
                System.out.println("brand: " + brand);
                System.out.println("Year: " + year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
