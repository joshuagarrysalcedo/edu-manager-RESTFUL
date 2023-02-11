package ph.jsalcedo.edumanager.data.models.enums;

public enum Semester {
    FIRST_SEMESTER("FIRST SEMESTER"),
    SECOND_SEMESTER("SECOND SEMESTER"),
    THIRD_SEMESTER("THIRD SEMESTER");

    private String name;

    Semester(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
