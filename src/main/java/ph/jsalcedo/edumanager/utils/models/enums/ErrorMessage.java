package ph.jsalcedo.edumanager.utils.models.enums;

import org.aspectj.asm.IProgramElement;

public enum ErrorMessage {
    ADDING_SCHOOL_DETAILS_ERROR(Constants.ADDING_SCHOOL_DETAILS_MESSAGE),
    MINIMUM_LENGTH_REQUIRED_ERROR(Constants.MINIMUM_LENGTH_REQUIRED_MESSAGE),
    EMPTY_FIELD_REQUIRED_ERROR(Constants.EMPTY_FIELD_REQUIRED_MESSAGE);



    ErrorMessage(String errorMessage) {
    }

    public static class Constants{
        public static final String MINIMUM_LENGTH_REQUIRED_MESSAGE = "length must have at least 2 characters";
        public static final String ADDING_SCHOOL_DETAILS_MESSAGE = "school name or school domain already exists!";
        public static final String EMPTY_FIELD_REQUIRED_MESSAGE = "field must not be blank!";
        public static final String NOT_FOUND_MESSAGE = "field not found";
    }


}
