package tables;


import db.IDBConnector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentTable extends AbsTable {

    public StudentTable(IDBConnector idbConnector) {
        super("students", idbConnector);
    }


    public static List<String> columnsStudent = Arrays.asList(
                "id int auto_increment",
                "fio varchar(50)",
                "sex varchar(1)",
                "id_group int",
                "primary key(id)"

    );




    @Override
    public void insert(List<String> columnValues) {
        String values = columnValues.stream().map(s -> "'"+ s + "'").collect(Collectors.joining(", "));
        String query = String.format("insert into `%s` (fio, sex, id_group) values (%s)", tableName, values);
        idbConnector.executeRequest(query);
    }
    @Override
    public  void update(int groupId, int curatorId) {
        String query = String.format("update `%s` set id_curator = %d where ID = %d",
                tableName,
                groupId,
                curatorId);

        idbConnector.executeRequest(query);
    }

}
