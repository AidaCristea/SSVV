package ssvv.example;

import domain.Tema;
import junit.framework.TestCase;
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

public class TemeValidatorTest extends TestCase {
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
    public void testNullId()
    {
        Tema tema = new Tema(null, "dd", 2, 4);
        assertThrows(NullPointerException.class, () -> this.service.addTema(tema));

    }

    @Test
    public void testEmptyId()
    {
        Tema tema = new Tema("", "dd", 2, 4);

        try {
            new TemaValidator().validate(tema);
            assert false : "Expected ValidationException for empty Id";
        } catch (ValidationException e) {
            assert true;
        }
    }



}
