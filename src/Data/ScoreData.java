/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import ConnectService.sqlConnect;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 84969
 */
public class ScoreData {

    private ArrayList<Student> listStudents;
    private Connection connection;

    public ScoreData() {
        try {
            this.connection = sqlConnect.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentData.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listStudents = new ArrayList<>();
    }

    public ArrayList<Student> getListStudents() {
        return this.listStudents;
    }

    public void getData() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dbo.Students");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String mssv = resultSet.getString("mssv");
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                Student student = new Student(id,name,age,email,phone,mssv,sex,address);
                listStudents.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getListStudentByQuery(String nameQuery,String emailQuery, String mssvQuery,String sexQuery){
                this.listStudents.clear();
                try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dbo.Students WHERE name LIKE N'%"+nameQuery+"%' AND email LIKE N'%"+emailQuery+"%' AND mssv LIKE '%"+mssvQuery+"%' AND sex LIKE N'%"+sexQuery+"%'");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String mssv = resultSet.getString("mssv");
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                Student student = new Student(id,name,age,email,phone,mssv,sex,address);
                listStudents.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
