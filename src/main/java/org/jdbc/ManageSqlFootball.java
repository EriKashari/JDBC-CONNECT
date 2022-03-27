package org.jdbc;

import java.sql.*;

public class ManageSqlFootball {
    public static void main(String[] args) throws SQLException  {
        try (final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soccer", "root", "er@nio2000")) {
            System.out.println("Connected");
            insert(connection);
            dropping(connection);
            display(connection);
            updating(connection);
          //  up(connection);
            updateTeam( 1, "inter", connection);
        } catch (SQLException exp) {

         exp.printStackTrace();
        }

    }


    /** ###############################
         insert table
        ###############################
     */
    private static void insert(Connection connection) {

try(Statement in =  connection.createStatement()){
    String add = "insert into football (id,name_club, color , manager ) value ( 6 ,'Villareal', 'yellow','Emery' )";
    String add1 = "insert into football (id,name_club, color , manager ) value ( 7 ,'Real Sociedad', 'blue-white','De Tomas' )";
    String add2 = "insert into football (id,name_club, color , manager ) value ( 8 ,'Valencia', 'yellow-blue','Marco' )";
     in.execute(add);
    in.execute(add1);
    in.execute(add2);
} catch (SQLException e) {
    e.printStackTrace();
}

    }

    /** ###############################
         dropping table
        ###############################
     */
    private static void dropping(Connection connection) {
        try(Statement drop = connection.createStatement()){
            String dropp = "drop table football";
                    drop.execute(dropp);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /** ###############################
          Display table
        ###############################
     */
    private static void display(Connection connection){

        try (Statement display = connection.createStatement()){
            String show = "select * from football";
            ResultSet resultSet = display.executeQuery(show);
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name_club = resultSet.getString("name_club");
                String color= resultSet.getString("color");
                String manager = resultSet.getString("manager");
                System.out.println("____________________");
                System.out.println("id: " + id);
                System.out.println("name_club: " + name_club);
                System.out.println("color: " + color);
                System.out.println("manager: " + manager);
            }

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /** ###############################
        update table
        ###############################
     */
    private static  void updating(Connection connection){
        try (Statement update = connection.createStatement()){
            String query1 = "update football set color = blue " + "where manager = Anceloti";
            update.executeUpdate(query1);
            System.out.println("Record has been updated in the table successfully..................");
        } catch ( SQLException excep  ) {
            excep.printStackTrace();

    }
}
    /** ###############################
     update table preparedStatement
     ###############################
     */
private  static  void up (Connection connection){
    String sqlStatement ="update football " + "set color = ?, " + "where manager = ? ";
    try( PreparedStatement updateQuery  = connection.prepareStatement(sqlStatement)) {
       updateQuery.setString(1, "red");
        updateQuery.setString(2, "Anceloti");
       updateQuery.executeUpdate(sqlStatement);
        System.out.println("Record has been updated in the table successfully..................");
   }catch (SQLException e){
       e.printStackTrace();
   }
}

    public  static  void updateTeam(int id, String name_club, Connection connection ) {
        String query = "UPDATE football SET name_club = ? WHERE  id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, name_club);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
