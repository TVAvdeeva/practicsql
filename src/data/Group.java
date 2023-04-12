package data;

public class Group {
    private  int id=-1;
    private  String name="";
    private  int curatorId=-1;

    public Group(int id, String name, int curatorId) {
        this.id = id;
        this.name = name;
        this.curatorId = curatorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCuratorId() {
        return curatorId;
    }
}
