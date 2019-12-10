package es.entity;

public class Section {
    private String section_id;
    private int max_students;
    private String course_id;
    private int class_times;
    private String year;
    private String evaluation_mode;
    private String semester;

    public String getSection_id() {
        return section_id;
    }

    public Section(String section_id,int max_students,String evaluation_mode,int class_times,String year,String semester){
        this.section_id =section_id;
        this.max_students = max_students;
        this.evaluation_mode =evaluation_mode;
        this.class_times = class_times;
        this.year = year;
        this.semester = semester;
    }


    public String getCourse_id() {
        return course_id;
    }

    public int getMax_students() {
        return max_students;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    public String getEvaluation_mode() {
        return evaluation_mode;
    }

    public int getClass_times() {
        return class_times;
    }
}
