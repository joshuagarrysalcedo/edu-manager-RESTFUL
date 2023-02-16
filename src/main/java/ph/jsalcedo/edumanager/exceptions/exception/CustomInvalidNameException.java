package ph.jsalcedo.edumanager.exceptions.exception;

import lombok.Getter;
import lombok.Setter;
import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;

/**
 * <h1> Custom exception for an invalid name for the whole program! </h1>
 * <p></p>
 * <b>Note:</b>
 * <h2>@created 16/02/2023 - 7:32 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
@Getter
@Setter
public class CustomInvalidNameException extends RuntimeException {

    public CustomInvalidNameException(String name) {
        super(String.format(ExceptionMessage.INVALID_NAME_MESSAGE, name));
    }

}


