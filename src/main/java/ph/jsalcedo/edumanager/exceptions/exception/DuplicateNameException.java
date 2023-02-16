package ph.jsalcedo.edumanager.exceptions.exception;

import lombok.Getter;
import lombok.Setter;
import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;

@Getter
@Setter
public class DuplicateNameException extends RuntimeException{

    private final String description;
    private final String resolution;
    public DuplicateNameException(String schoolName) {
        super(String.format(ExceptionMessage.DUPLICATE_NAME_MESSAGE, schoolName));
        this.description = "Description: " +
                "The school name you are attempting to add or update already exists in the database. " +
                "Duplicate school names are not allowed.";
        this.resolution = "Resolution: Please choose a different name for the school or update the existing record instead.";
    }
}
