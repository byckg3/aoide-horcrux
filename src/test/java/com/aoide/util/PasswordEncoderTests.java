package com.aoide.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordEncoderTests
{
    private String rawPassword;
    private String salt;

    @BeforeEach
    void setUp() {
        rawPassword = DataGenerator.generateBase64RandomString( 10 );
        salt = DataGenerator.generateBase64RandomString( 20 );
    }

    @Test
    void test_encode()
    {
        String saltedPassword = PasswordEncoder.encode( rawPassword, salt );
        String anotherPassword = DataGenerator.generateBase64RandomString( 10 );

        assertEquals( saltedPassword, PasswordEncoder.encode( rawPassword, salt ) );
        assertNotEquals( saltedPassword, PasswordEncoder.encode( anotherPassword, salt ) );
    }
}
