package ak88.model;

public class Student {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String status;
    private int classId;

    public Student() {
    }

    public Student(int id, String name, String address, String phone, String status, int classId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.classId = classId;
    }

    public Student(String name, String address, String phone, String status, int classId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.classId = classId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
