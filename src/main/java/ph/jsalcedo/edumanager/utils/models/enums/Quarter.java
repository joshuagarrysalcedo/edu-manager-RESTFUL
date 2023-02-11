package ph.jsalcedo.edumanager.utils.models.enums;

public enum Quarter {
    FIRST_QUARTER("FIRST QUARTER"),
    SECOND_QUARTER("SECOND QUARTER"),
    THIRD_QUARTER("THIRD QUARTER"),
    FOURTH_QUARTER("FOURTH QUARTER");

    private final String name;

    Quarter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
