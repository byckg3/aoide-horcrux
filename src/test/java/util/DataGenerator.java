package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataGenerator
{
    public static String generateTimestamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
        return sdf.format( new Date() );
    }
}
