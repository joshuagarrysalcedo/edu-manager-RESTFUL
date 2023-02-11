package ph.jsalcedo.edumanager.utils.models.enums;

public enum StudentLevel {
    GRADE_1("GRADE 1"),
    GRADE_2("GRADE 2"),
    GRADE_3("GRADE 3"),
    GRADE_4("GRADE 4"),
    GRADE_5("GRADE 5"),
    GRADE_6("GRADE 6"),
    GRADE_7("GRADE 7"),
    GRADE_8("GRADE 8"),
    GRADE_9("GRADE 9"),
    GRADE_10("GRADE 10"),
    JUNIOR_HIGH_SCHOOL("JR. HIGH SCHOOL"),
    SENIOR_HIGH_SCHOOL("SR. HIGH SCHOOL"),
    COLLEGE_FRESHMAN,
    COLLEGE_SOPHOMORE,
    COLLEGE_JUNIOR,
    COLLEGE_SENIOR,
    GRADUATE_LEVEL,
    DOCTORAL;

    private final String name;
    StudentLevel() {
        this.name = this.toString();
    }

    StudentLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
