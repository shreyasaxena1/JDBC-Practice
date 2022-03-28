package com.example.practice;
import java.sql.*;
import java.util.*;

public  class HelloApplication{                  //forname throw checked exception
    public static void main(String[] args) throws Exception{

        Class.forName("com.mysql.jdbc.Driver");

        String url="jdbc:mysql://localhost:3306/practice?autoReconnect=true&useSSL=false";
        String uname = "root";
        String pass="shreya";


        Connection con = DriverManager.getConnection(url,uname,pass);

        Statement st = con.createStatement();

        System.out.println("Select from following options");
        System.out.println("1 - Display Record");
        System.out.println("2 - Update Record");
        System.out.println("3 - Delete Record");
        System.out.println("4 - Insert Record");
        System.out.println("5 - View all Records");

        Scanner s = new Scanner(System.in);

        int option = s.nextInt();

        switch (option){
            case 1:
                display(st);
                break;
            case 2:
                update(con);
                break;
            case 3:
                delete(st);
                break;
            case 4:
                insert(con);
                break;
            case 5:
                all(st);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        st.close();
        con.close();



    }
   //Working Fine
    public static void display(Statement st) throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter name of students to view its record");
        String name = scn.nextLine();
        String query = "SELECT * FROM students where sname ='"+name+"'";

        ResultSet rs = st.executeQuery(query);

        rs.next();
        String output = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3);

        System.out.println(output);

    }

    public static void update(Connection con) throws SQLException{
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Id of student to update record");
        int id = scn.nextInt();
        System.out.println("1 : Update Student name\n" +
                "2: Update Student phone number\n" +
                "3: Update both\n");
        int op = scn.nextInt();
        String query="",n,p;
        Scanner s = new Scanner(System.in);

        switch (op) {
            case 1:

                System.out.println("Enter Updated Name");
                n = s.nextLine();
                query = "Update students SET sname = ? WHERE sid = ?";
                PreparedStatement st=con.prepareStatement(query);
                st.setString(1,n);
                st.setInt(2,id);
                st.executeUpdate();
                break;
            case 2:
                System.out.println("Enter Updated Phone");
                p = s.nextLine();
                query = "Update students SET sphone = ? WHERE sid = ?";
                PreparedStatement sts=con.prepareStatement(query);
                sts.setString(1,p);
                sts.setInt(2,id);
                sts.executeUpdate();
                break;
            case 3:
                System.out.println("Enter Updated Name");
                n = s.nextLine();
                System.out.println("Enter Updated Phone");
                p = s.nextLine();
                query = "Update students SET sphone = ?, sname = ? WHERE sid = ?";
                PreparedStatement sta=con.prepareStatement(query);
                sta.setString(1,p);
                sta.setString(2,n);
                sta.setInt(3,id);
                sta.executeUpdate();
                break;

            default:
                System.out.println("Invalid Choice");
                break;
        }

    }

    //Working Fine
    public static void delete(Statement st) throws SQLException{
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Id of student to delete");
        int id = scn.nextInt();

        String query = "DELETE FROM students WHERE sid = "+id;

        st.executeUpdate(query);

    }
   /*
   DDL, DML -- (ex insert - not fetching data -- exuteUpdate(retrun rows affected)
   DQL -- excuteQuery -- return object of result set

    */
    //Working Fine
    public static void insert(Connection con) throws SQLException{

        Scanner scn = new Scanner(System.in);
        System.out.println("Enter name of student");
        String name = scn.nextLine();
        System.out.println("Enter phone number of student");
        String phone = scn.nextLine();

        String query = "INSERT INTO students (sname,sphone) VALUES (?,?)";

        // use prepared stmt - work with predefined query but value changes
        PreparedStatement st = con.prepareStatement(query);
        //Giving value to ?
        st.setString(1,name);
        st.setString(2,phone);

        st.executeUpdate();

    }
    //Working Fine
    public static void all(Statement st) throws SQLException {

        ResultSet rs = st.executeQuery("SELECT * FROM students");
        while (rs.next()) {

            String output = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3);
            System.out.println(output);

        }
    }
}