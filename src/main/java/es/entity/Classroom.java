package es.entity;

public class Classroom {
    private String classroom_id;
    private String building;
    private String room_number;
    private int  capacity;

    public Classroom(String classroom_id, String building, String room_number, int capacity) {
        this.classroom_id = classroom_id;
        this.building = building;
        this.room_number = room_number;
        this.capacity = capacity;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom_number() {
        return room_number;
    }

    public int getCapacity() {
        return capacity;
    }
}
