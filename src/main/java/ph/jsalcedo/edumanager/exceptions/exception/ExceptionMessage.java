package ph.jsalcedo.edumanager.exceptions.exception;

/**
 * <h1> ExceptionMessage </h1>
 * <p></p>
 * <b>Note:</b>
 * <h2>@created 16/02/2023 - 7:54 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class ExceptionMessage {
    private ExceptionMessage(){}
    public static final String INVALID_NAME_MESSAGE = "Error: Invalid Name Exception for[name : %s\n" +
            "Description: The name provided is invalid and does not meet the naming conventions required for this field.]";
}
