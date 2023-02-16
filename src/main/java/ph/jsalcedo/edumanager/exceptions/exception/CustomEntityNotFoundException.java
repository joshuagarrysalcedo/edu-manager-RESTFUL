package ph.jsalcedo.edumanager.exceptions.exception;

import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;

/**
 * <h1> CustomEntityNotFoundException </h1>
 * <p></p>
 * <b>Note:</b>
 * <h2>@created 16/02/2023 - 9:08 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class CustomEntityNotFoundException extends RuntimeException{
    public CustomEntityNotFoundException(String attribute, Object value) {
        super(String.format(ExceptionMessage.ENTITY_NOT_FOUND_MESSAGE, attribute, value));
    }
}
