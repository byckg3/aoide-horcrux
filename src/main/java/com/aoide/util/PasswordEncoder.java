package com.aoide.util;

import java.util.Base64;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;

import javax.crypto.SecretKeyFactory;

public class PasswordEncoder
{
    public static String encode( String password, String salt )
    {
        try
        {
            Base64.Decoder decoder = Base64.getDecoder();
            Base64.Encoder encoder = Base64.getEncoder();
            SecretKeyFactory factory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );

            KeySpec spec = new PBEKeySpec( password.toCharArray(), decoder.decode( salt ), 1000, 128 );
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return encoder.encodeToString( hash );
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }
}
