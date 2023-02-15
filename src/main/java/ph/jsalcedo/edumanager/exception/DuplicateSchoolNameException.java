package ph.jsalcedo.edumanager.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateSchoolNameException extends RuntimeException{

    private final String description;
    private final String resolution;
    public DuplicateSchoolNameException(String schoolName) {
        super(String.format("Error: School [%s] name already exists", schoolName));
        this.description = "Description: " +
                "The school name you are attempting to add or update already exists in the database. " +
                "Duplicate school names are not allowed.";
        this.resolution = "Resolution: Please choose a different name for the school or update the existing record instead.";
    }
}
