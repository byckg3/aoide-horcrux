package com.aoide.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginFormTests
{
    private static Validator validator;
    private Set< ConstraintViolation< LoginForm > > constraintViolations;
    private LoginForm form;

    @BeforeAll
    public static void suiteSetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        form = new LoginForm();
        form.setAccount( "test0001" );
        form.setPassword( "password" );
    }

    @Test
    public void test_validFormContent()
    {
        constraintViolations = validator.validate( form );
        assertTrue( constraintViolations.isEmpty() );
    }

    @Test
    public void test_accountFieldCannotBeNull()
    {
        form.setAccount( null );
        constraintViolations = validator.validate( form );
        assertEquals( 1, constraintViolations.size() );
        
        form.setAccount( "" );
        constraintViolations = validator.validate( form );
        assertEquals( 2, constraintViolations.size() );

        form.setAccount( "1234" );
        constraintViolations = validator.validate( form );
        assertEquals( 1, constraintViolations.size() );
        
   }

   @Test
    public void test_passwordSize()
    {
        form.setPassword( "abcde" );
        constraintViolations = validator.validate( form );
        printError();
        assertEquals( 1, constraintViolations.size() );
        
    }

    private void printError()
    {
        for ( ConstraintViolation< LoginForm > violation : constraintViolations ) {
            System.out.println( violation.getMessage() ); 
        }
    }
}
