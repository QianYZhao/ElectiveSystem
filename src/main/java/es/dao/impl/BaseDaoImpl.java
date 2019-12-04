package es.dao.impl;

import es.dao.BaseDao;

import java.sql.*;

public class BaseDaoImpl implements BaseDao {
    private final static String user = "root";
    private final static  String password = "mysql@2692277504";
    private final static   String UTR = "jdbc:mysql://localhost:3306/starbaba?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

    private static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(UTR,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  conn;
    }


    public static ResultSet search(String  sql){
        ResultSet resultSet= null;
        try{
            Connection connection= getConnection();
            Statement statement= connection.createStatement();
            resultSet = statement.executeQuery(sql);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }


//add ,delete and update
    public static boolean execute(String sql){
        boolean bool= false;
        try{
            Connection connection = getConnection();
            Statement statement= connection.createStatement();
            if(statement.executeUpdate(sql)>0)
                bool= true;
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return bool;
    }

}
