package db;
import java.sql.ResultSet;
public interface IDBConnector {

    void executeRequest(String sqlrequest);
    ResultSet execute(String sqlRequest);
    void close();

}
