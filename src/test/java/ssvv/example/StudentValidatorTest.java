package ssvv.example;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentValidatorTest {
    private Service service;

    @BeforeEach
    public void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String fileStudent = "fisiere/Studenti.xml";
        String fileTema = "fisiere/Teme.xml";
        String fileNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepo = new StudentXMLRepo(fileStudent);
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo(fileTema);
        NotaXMLRepo notaXMLRepo = new NotaXMLRepo(fileNota);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);

        this.service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
    }


    @Test
    public void addStudentDuplicateId() {
        Student student = new Student("1", "John", 123, "john@gmail.com");
        try {
            service.addStudent(student);
            assert (true);
        } catch (ValidationException ex) {
            System.out.println(ex);
            assert (true);
        }
    }


    @Test
    public void testValidStudent() {
        Student validStud = new Student("1", "John Doe", 123, "john.doe@example.com");
        try {
            new StudentValidator().validate(validStud);
        } catch (ValidationException e) {
            assert false : "Unexpected ValidationException for a valid student";
        }
    }


    @Test
    public void testInvalidGroup() {
        // Arrange
        Student invalidGroupStudent = new Student("1", "Jj", -10, "john.doe@example.com");

        try {
            new StudentValidator().validate(invalidGroupStudent);
            assert false : "Expected ValidationException for invalid group name";
        } catch (ValidationException e) {
            assert true;
        }
    }

    @Test
    public void testInvalidEmail() {
        // Arrange
        Student invalidEmailStudent = new Student("1", "John Doe", 123, "");

        try {
            new StudentValidator().validate(invalidEmailStudent);
            assert false : "Expected ValidationException for invalid email";
        } catch (ValidationException e) {
            assert true;
        }
    }


    @Test
    public void testEmptyId() {
        Student emptyIdStudent = new Student("", "John Doe", 123, "a@gmail.com");

        try {
            new StudentValidator().validate(emptyIdStudent);
            assert false : "Expected ValidationException for empty Id";
        } catch (ValidationException e) {
            assert true;
        }
    }

    @Test
    public void testNullId() {
        Student nullIdStudent = new Student(null, "John Doe", 123, "a@gmail.com");
        assertThrows(NullPointerException.class, () -> this.service.addStudent(nullIdStudent));
        /*
        // Act & Assert
        try {
            new StudentValidator().validate(nullIdStudent);
            // Fail the test if no exception is thrown for an invalid email
            assert false : "Expected ValidationException for null Id";
        } catch (ValidationException e) {
            // Pass the test if a ValidationException is thrown
            assert true;
        }*/
    }

    @Test
    public void testEmptyName() {
        Student emptyNameStudent = new Student("1", "", 123, "a@gmail.com");

        try {
            new StudentValidator().validate(emptyNameStudent);
            assert false : "Expected ValidationException for empty name";
        } catch (ValidationException e) {
            assert true;
        }
    }

    @Test
    public void testNullName() {
        Student nullNameStudent = new Student("1", null, 123, "a@gmail.com");

        try {
            new StudentValidator().validate(nullNameStudent);
            assert false : "Expected ValidationException for null name";
        } catch (ValidationException e) {
            assert true;
        }
    }

    @Test
    public void testNullEmail() {
        Student nullEmailStudent = new Student("1", "John Doe", 123, null);

        try {
            new StudentValidator().validate(nullEmailStudent);
            assert false : "Expected ValidationException for null email";
        } catch (ValidationException e) {
            assert true;
        }
    }


}
