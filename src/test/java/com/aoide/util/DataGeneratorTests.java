package com.aoide.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataGeneratorTests
{
    @BeforeEach
    void SetUp()
    {
        
    }

    @ParameterizedTest( name = "@MethodSource#{index} : test_generateRandomString( {arguments} )" )
    @MethodSource( value = "com.aoide.util.DataGenerator#generateRandomIntStream" )
    void test_generateRandomString( Integer length ) throws Exception
    {
        String randomString = DataGenerator.generateRandomString( length );
        assertEquals( length, randomString.length() );
    }
}
