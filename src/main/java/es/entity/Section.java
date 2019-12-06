package es.entity;

public class Section {
    private String section_id;
    private String course_id;
    private int max_students;
    private int class_times;
    private String year;
    private String evaluation_mode;
    private String semester;

    public String getSection_id() {
        return section_id;
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
