package es.entity;

public class Course {
    String course_id;
    String course_name;
    String type;
    int  credit;
    String dept_name;

    public Course(String course_id, String course_name, int credit, String dept_name) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.credit = credit;
        this.dept_name = dept_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getType() {
        return type;
    }

    public int getCredit() {
        return credit;
    }

    public String getDept_name() {
        return dept_name;
    }
}
