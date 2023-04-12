package tables;

import db.IDBConnector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupTable extends AbsTable{

    public GroupTable(IDBConnector idbConnector) { super("groupst", idbConnector);
    }



    public static List<String> columnsGroup = Arrays.asList(
            "id int auto_increment ",
            "id_curator int",
            "name varchar(50)",
            "primary key(id)"

     );
          //

    @Override
    public void insert(List<String> columnValues) {
        String values = columnValues.stream().map(s -> "'"+ s + "'").collect(Collectors.joining(", "));
        String query = String.format("insert into %s (name, id_curator) values (%s)", tableName, values);
        idbConnector.executeRequest(query);
    }

    @Override
    public void update(int groupId, int curatorId) {
             String query = String.format("update  %s set id_curator = %d where id = %d",
                 tableName,
                 groupId,
                 curatorId);
             idbConnector.executeRequest(query);
    }

}
