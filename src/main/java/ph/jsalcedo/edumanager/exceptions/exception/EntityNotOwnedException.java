package ph.jsalcedo.edumanager.exceptions.exception;

import ph.jsalcedo.edumanager.exceptions.ExceptionMessage;

/**
 * <h1> EntityNotOwnedException</h1>
 * <dl>
 * <dt>PURPOSE</dt>
 * <dd><b> purpose here </b></dd>
 * <dt>TYPE</dt>
 * <dd><b> type here </b></dd>
 * </dl>
 * <b>Note:</b> *
 * <h2>@created 17/02/2023</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class EntityNotOwnedException extends RuntimeException{
    public EntityNotOwnedException(String parent, String child, String fieldName, Object value) {
        super(String.format(ExceptionMessage.ENTITY_NOT_OWNED_MESSAGE, parent,child,fieldName,value));
    }
}
