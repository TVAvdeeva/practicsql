package db;
import setting.PropertiesReader;


import java.sql.*;
import java.util.*;

public class DBCConnector implements IDBConnector {

    private static Connection connection = null;
    private static Statement statement = null;
    private Map<String,String> settings = new PropertiesReader().read();
    public DBCConnector() {
     connect();
    }
    private void connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        settings.get("url") + "/" + settings.get("db_name"),
                        settings.get("username"),
                        settings.get("password")
                );
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (statement == null) {
            try {
              statement =  connection.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }


    @Override
    public void close(){
      if (statement!=null) {
          try {
              statement.close();
          } catch (SQLException ex) {
              ex.printStackTrace();
          }
      }
      if (connection!=null) {
          try {
               connection.close();
              } catch (SQLException ex) {
              ex.printStackTrace();
          }
      }
    }


    @Override
    public void executeRequest(String sqlRequest) {
      try{
          statement.execute(sqlRequest);
      } catch (SQLException ex){
          ex.printStackTrace();
      }
    }

    @Override
    public ResultSet execute(String sqlRequest) {
      try {
           Statement statement=connection.createStatement();
           return statement.executeQuery(sqlRequest);
      } catch (SQLException ex){
           ex.printStackTrace();
      }
      return null;
    }
}
