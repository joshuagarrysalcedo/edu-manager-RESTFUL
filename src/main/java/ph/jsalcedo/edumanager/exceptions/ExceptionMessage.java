package ph.jsalcedo.edumanager.exceptions;

/**
 * <h1> ExceptionMessage </h1>
 * <p></p>
 * <b>Note:</b>
 * <h2>@created 16/02/2023 - 7:54 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class ExceptionMessage {
    private ExceptionMessage(){}
    public static final String INVALID_NAME_MESSAGE = "Error: Invalid Name Exception for[name : %s]\n" +
            "Description: The name provided is invalid and does not meet the naming conventions required for this field.";

    public static final String DUPLICATE_NAME_MESSAGE = "Error: Name [%s]  already exists\nDescription:" +
            "The Name name you are attempting to add or update already exists in the database. ";

    public static final String ENTITY_NOT_FOUND_MESSAGE = "Error: Entity with attributes of [%s : %s] not found\n" +
            "Description: The entity you are attempting to access could not be found in the database. ";

    public static final String ENTITY_NOT_OWNED_MESSAGE = "Error: Parent entity %s does not own a %s entity " +
            "with attributes of [%s : %s]";
}
