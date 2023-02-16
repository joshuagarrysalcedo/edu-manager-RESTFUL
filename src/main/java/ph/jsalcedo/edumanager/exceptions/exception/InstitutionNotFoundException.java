package ph.jsalcedo.edumanager.exceptions.exception;

public class InstitutionNotFoundException extends RuntimeException{
    public InstitutionNotFoundException(Long id) {
        super(String.format("Cannot find Institution with id %d", id));
    }


}
