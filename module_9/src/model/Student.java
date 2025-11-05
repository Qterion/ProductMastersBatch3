package model;

public class Student {
    private int id;
    private String name;
    private String groupName;
    private boolean isAttended;
    
    public Student(int id, String name, String groupName, boolean isAttended) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
        this.isAttended = isAttended;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public boolean isAttended() {
        return isAttended;
    }
    
    public boolean getAttended() {
        return isAttended;
    }
    
    public void setAttended(boolean attended) {
        isAttended = attended;
    }
}

