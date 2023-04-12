package tables;

import db.IDBConnector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;





public abstract class AbsTable {
   public  String tableName;
   private List<String> columns;
   public  IDBConnector idbConnector;

   public String getName() {
        return tableName;
    }
   public AbsTable(String tableName, IDBConnector idbConnector) {
        this.tableName = tableName;
        this.idbConnector = idbConnector;

    }

   public void create(List<String> columns ) throws SQLException{
        idbConnector.executeRequest(String.format("CREATE TABLE IF NOT EXISTS %s (%s);",
                        tableName, String.join(", ", columns))
                );
    }




  public int getCount() {
    try (ResultSet result = idbConnector.execute(String.format("select count(*) from `%s`", tableName))) {
        if (!result.next()){ throw new RuntimeException("Unexpected error");}
        return result.getInt(1);
      } catch (SQLException e) {
         throw new RuntimeException(e);
     }
  }


    public void delete() {
       idbConnector.executeRequest(String.format("drop table if exists `%s`", tableName));
    }


    public abstract void insert(List<String> columnValues);
    public abstract void update(int value1, int value2);

    public  void printResultSet(String strQuery) throws SQLException {

         System.out.println("Следующий пукт...");
            ResultSet result = idbConnector.execute(strQuery);
            ResultSetMetaData resultStrings = result.getMetaData();
            int columnsNumber = resultStrings.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print("  |  ");
                    String columnValue = result.getString(i);
                    System.out.print(String.format("%s: %s", resultStrings.getColumnName(i), columnValue));
                }
                System.out.println("");
            }

    }
  }



