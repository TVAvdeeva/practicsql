package tables;

import db.IDBConnector;

import java.util.*;
import java.util.stream.Collectors;

public class CuratorTable extends AbsTable {

    public CuratorTable(IDBConnector idbConnector) {
        super("curators", idbConnector);
    }
      public static List<String> columnsCurator = Arrays.asList(
                "id int auto_increment ",
                "fio varchar(100)",
                "primary key (id)"
        );

    @Override
    public  void update(int groupId, int curatorId) {
        String query = String.format("update `%s` set id_curator = %d where ID = %d",
                tableName,
                groupId,
                curatorId);

        idbConnector.executeRequest(query);
    }

    @Override
    public void insert(List<String> columnValues) {
        String values = columnValues.stream().map(s -> "'" + s + "'").collect(Collectors.joining(", "));
        String query = String.format("insert into `%s` (fio ) values (%s)", tableName, values);
        idbConnector.executeRequest(query);
    }



}
