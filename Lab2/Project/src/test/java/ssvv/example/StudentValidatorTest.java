package ssvv.example;

import domain.Student;
import org.junit.Test;
import validation.StudentValidator;
import validation.ValidationException;


public class StudentValidatorTest {

    @Test
    public void testValidStudent(){
        Student validStud = new Student("1", "John Doe", 123, "john.doe@example.com");
        // Act & Assert
        try {
            new StudentValidator().validate(validStud);
        } catch (ValidationException e) {
            // Fail the test if an exception is thrown
            assert false : "Unexpected ValidationException for a valid student";
        }
    }


    @Test
    public void testInvalidGroup() {
        // Arrange
        Student invalidGroupStudent = new Student("1", "Jj", -10, "john.doe@example.com");

        // Act & Assert
        try {
            new StudentValidator().validate(invalidGroupStudent);
            // Fail the test if no exception is thrown for an invalid group name
            assert false : "Expected ValidationException for invalid group name";
        } catch (ValidationException e) {
            // Pass the test if a ValidationException is thrown
            assert true;
        }
    }

    @Test
    public void testInvalidEmail() {
        // Arrange
        Student invalidEmailStudent = new Student("1", "John Doe", 123, "");

        // Act & Assert
        try {
            new StudentValidator().validate(invalidEmailStudent);
            // Fail the test if no exception is thrown for an invalid email
            assert false : "Expected ValidationException for invalid email";
        } catch (ValidationException e) {
            // Pass the test if a ValidationException is thrown
            assert true;
        }
    }


}
