package es.uclm.esi.iso2.scholarship;

/**
 * Exception thrown when a student profile contains an invalid value.
 */
public class InvalidStudentProfileException extends IllegalArgumentException {

    /**
     * Creates an exception describing the invalid student profile value.
     *
     * @param message description of the validation failure
     */
    public InvalidStudentProfileException(String message) {
        super(message);
    }
}
