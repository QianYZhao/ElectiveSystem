package es.entity;

import java.sql.*;


public class Examination {
    private String exam_id;
    private Date date;
    private Time exam_starTime;
    private Time exam_endTime;
    private String  modality;
    private String instructor_id;
    private String classroom_id;

    public Examination(String exam_id, Date date, Time exam_starTime, Time exam_endTime, String modality, String instructor_id, String classroom_id) {
        this.exam_id = exam_id;
        this.date = date;
        this.exam_starTime = exam_starTime;
        this.exam_endTime = exam_endTime;
        this.modality = modality;
        this.instructor_id = instructor_id;
        this.classroom_id = classroom_id;
    }

    public String getExam_id() {
        return exam_id;
    }

    public Date getDate() {
        return date;
    }

    public Time getExam_starTime() {
        return exam_starTime;
    }

    public Time getExam_endTime() {
        return exam_endTime;
    }

    public String getModality() {
        return modality;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public String getClassroom_id() {
        return classroom_id;
    }
}
