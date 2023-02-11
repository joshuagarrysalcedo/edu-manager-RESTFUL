package ph.jsalcedo.edumanager.utils.models.enums;

public enum EnrollmentStatus {
    ELIGIBLE("Eligible for enrollment"),
    PENDING("Pending review"),
    APPROVED("Enrollment approved"),
    REJECTED("Enrollment rejected"),
    NOT_ELIGIBLE("Not eligible for enrollment");
    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}