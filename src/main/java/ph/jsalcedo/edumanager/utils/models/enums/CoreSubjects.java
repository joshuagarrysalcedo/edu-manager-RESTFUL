package ph.jsalcedo.edumanager.data.models.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;




public enum CoreSubjects {
    LANGUAGE("Language"),
    HUMANITIES("Humanities"),
    COMMUNICATION("Communication"),
    MATHEMATICS("Mathematics"),
    PHILOSOPHY("Philosophy"),
    SCIENCE("Science"),
    SOCIAL_SCIENCE("Social Science"),
    NATURAL_SCIENCE("Natural Science"),
    FORMAL_SCIENCE("Formal Science"),
    AGRICULTURE("Agriculture"),
    ARCHITECTURE_AND_DESIGN("Architecture and Design"),
    BUSINESS("Business"),
    HEALTH_SCIENCE("Health Science"),
    EDUCATION("Education"),
    ENGINEERING("Engineering"),
    MEDIA_AND_COMMUNICATION("Media and Communication"),
    PUBLIC_ADMINISTRATION("Public Administration"),
    TRANSPORTATION("Transportation"),
    FAMILY_AND_CONSUMER_SCIENCE("Family and Consumer Science"),
    CRIMINAL_JUSTICE("Criminal Justice");

    private final String capitalizedName;

    CoreSubjects(String capitalizedName) {
        this.capitalizedName = capitalizedName;
    }


    public String getCapitalizedName() {
        return capitalizedName;
    }


//https://www.courses.com.ph/undergraduate-degree-programs-in-the-philippines/
}
