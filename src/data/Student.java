package data;

public class Student {
    private  int id=-1;
    private  String fullName="";
    private  String gender="ж";
    private int groupId=-1;

    public Student(int id, String fullName, String gender, int groupId) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public int getGroupId() {
        return groupId;
    }

}
