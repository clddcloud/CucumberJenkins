package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class dbUtils {
    public static List<Map<String,String>> fetch (String query)  {
        String dbUrl=ConfigReader.read("dbUrl");
        String username=ConfigReader.read("dbUserName");
        String password=ConfigReader.read("dbPassword");
        List<Map<String,String>> tableData=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl,username,password);
             Statement statement = connection.createStatement();
             ) {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsm= resultSet.getMetaData();

            while (resultSet.next()){
                Map<String,String> rowMap=new LinkedHashMap<>();
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    String key=rsm.getColumnLabel(i);
                    String value=resultSet.getString(i);
                    rowMap.put(key,value);
                }
                tableData.add(rowMap);
        }
        } catch (SQLException sqlException){
             sqlException.printStackTrace();
        }
        return tableData;
   }
}
