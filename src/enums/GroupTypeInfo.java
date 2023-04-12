package enums;
public enum GroupTypeInfo {
    IKT581("ИКТ_581"),
    IKT582("ИКТ_582"),

    IKT583("ИКТ_583");

    private final String groupName;

    GroupTypeInfo(String groupName){
        this.groupName = groupName;
    }


    public String getGroupName() {
         return groupName;
    }

}
