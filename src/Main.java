import db.*;

import tables.*;
import enums.*;

import java.sql.SQLException;
import java.util.*;

public class Main {
 // private static AbsTable groupTable=;

    private static IDBConnector idbConnector;
    private static CuratorTable curatorTable;
    private static GroupTable   groupTable;
    private static StudentTable studentTable;




    private static void insertData() {
        //4 куратора
        curatorTable.insert(List.of("Ганицкий Денис Сергеевич"));
        curatorTable.insert(List.of("Шлык Владимир Алексеевич"));
        curatorTable.insert(List.of("Пурыгин Кирилл Евгеньевич"));
        curatorTable.insert(List.of("Сусоева Наталья Владимировна"));
        // 3 группы

        groupTable.insert(Arrays.asList(GroupTypeInfo.IKT581.getGroupName(), Integer.toString(3)));
        groupTable.insert(Arrays.asList(GroupTypeInfo.IKT582.getGroupName(), Integer.toString(2)));
        groupTable.insert(Arrays.asList(GroupTypeInfo.IKT583.getGroupName(), Integer.toString(4)));
        // 15 студентов
        studentTable.insert(Arrays.asList("Кузько Алексей Николаевич",GenderTypeInfo.M.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Гудзь Василий Сидорович", GenderTypeInfo.M.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Никифорова Ольга Олеговна", GenderTypeInfo.F.toString(), Integer.toString(2)));
        studentTable.insert(Arrays.asList("Неговоров Дмитрий Александрович", GenderTypeInfo.M.toString(), Integer.toString(2)));
        studentTable.insert(Arrays.asList("Лесюк Игорь Иванович", GenderTypeInfo.M.toString(), Integer.toString(3)));
        studentTable.insert(Arrays.asList("Ковалева Татьяна Михайловна", GenderTypeInfo.F.toString(), Integer.toString(3)));
        studentTable.insert(Arrays.asList("Коваленко Василий Викторович", GenderTypeInfo.M.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Васильева Ольга  Николаевна", GenderTypeInfo.F.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Новикова Елизавета Игоревна", GenderTypeInfo.F.toString(), Integer.toString(3)));
        studentTable.insert(Arrays.asList("Лапин Михаил Николаевич", GenderTypeInfo.M.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Зверева Анна Степановна", GenderTypeInfo.F.toString(), Integer.toString(2)));
        studentTable.insert(Arrays.asList("Торопов Николай Иванович", GenderTypeInfo.M.toString(), Integer.toString(2)));
        studentTable.insert(Arrays.asList("Бакулин Олег Петрович", GenderTypeInfo.M.toString(), Integer.toString(3)));
        studentTable.insert(Arrays.asList("Муравьев Петр Иванович", GenderTypeInfo.M.toString(), Integer.toString(1)));
        studentTable.insert(Arrays.asList("Смирнова Надежда Федоровна", GenderTypeInfo.F.toString(), Integer.toString(2)));
    }
    private static void showStudentsInfo() throws SQLException {

        String query = String.format(
                "select s.id, s.fio, s.sex, g.name, c.fio " +
                        "from `%s` as s " +
                        "join `%s` as g on g.id = s.id_group " +
                        "join `%s` as c on c.id = g.id_curator",
                studentTable.getName(), groupTable.getName(), curatorTable.getName());
        groupTable.printResultSet(query);


    }

    private static void showStudentsCount() { System.out.println("Количество студентов: "+ studentTable.getCount());
    }

    private static void showGirls() throws SQLException{
       String  query = String.format(
                        "select * from `%s` where `%s` = '%s'",studentTable.getName(),"SEX", GenderTypeInfo.F);
       groupTable.printResultSet(query);
        }

    private static void showGroupsAndCurators() throws SQLException{
         String query = String.format(
                        "select g.id, g.name, c.fio " +
                        "from `%s` as g " +
                        "join `%s` as c on c.id = g.id_curator",
                         groupTable.getName(), curatorTable.getName());

        groupTable.printResultSet(query);

    }


    private static void showGroup() throws SQLException{ //10

          String query = String.format("select * from `%s` where id_group = (select id from `%s` where `name` = '%s')",
                                      studentTable.getName(), groupTable.getName(), GroupTypeInfo.IKT581.getGroupName());
           studentTable.printResultSet(query);
    }

    private  static void changeGroupCurator() {
        groupTable.update(1,1);
        System.out.println("Куратор группы изменен.");
    }

    public static void close() {
        System.out.println("Следующий пукт....");
        studentTable.delete();
        System.out.printf("Таблица '%s' удалена.\n", studentTable.getName());
        groupTable.delete();
        System.out.printf("Таблица '%s' удалена.\n", groupTable.getName());
        curatorTable.delete();
        System.out.printf("Таблица '%s' удалена.\n", curatorTable.getName());

    }

    private  static void Run() throws SQLException {

        groupTable.create(GroupTable.columnsGroup); //2
        curatorTable.create(CuratorTable.columnsCurator);//3
        studentTable.create(StudentTable.columnsStudent);//1

        insertData();// 4
        showStudentsInfo();//5
        showStudentsCount();//6
         changeGroupCurator();//8
        showGirls();//7
        showGroupsAndCurators();//9
        showGroup();
        close();
   }

     public static  void main(String... arg){

      try{
            idbConnector=new DBCConnector();
            curatorTable = new CuratorTable(idbConnector);
            groupTable = new GroupTable(idbConnector);
            studentTable = new StudentTable(idbConnector);
            Run();
         } catch (SQLException e) {
             throw new RuntimeException(e);
        } finally {
          idbConnector.close();
        }
    }
}
