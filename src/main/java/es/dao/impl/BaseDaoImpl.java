package es.dao.impl;



import es.dao.BaseDao;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl implements BaseDao {
    private final static String user = "root";
    private final static  String password = "mysql@2692277504";
    private final static   String UTR = "jdbc:mysql://localhost:3306/selectcourse?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static Connection getConnection(){
        Connection conn=null;
        try{

             Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(UTR,user,password);
            System.out.println("连接成功 。。。。");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  conn;
    }


    public  List<Map<String,Object>> search(String  sql){
        List<Map<String,Object>> list =new LinkedList<>();
        try{
            Connection connection= getConnection();
            Statement statement= connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData= resultSet.getMetaData();

            int count= metaData.getColumnCount();
            while (resultSet.next()){

                Map<String ,Object> map= new HashMap<String,Object>();
                for(int i=1;i<=count;i++){
                    map.put(metaData.getColumnName(i),resultSet.getObject(i));
                }
                list.add(map);
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
       return list;
    }


//add ,delete and update
    public  boolean execute(String sql){
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
