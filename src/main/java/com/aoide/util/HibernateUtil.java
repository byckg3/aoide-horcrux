package com.aoide.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory()
    {
        return getSessionFactory( "hibernate.cfg.xml" );
    }

    public static SessionFactory getSessionFactory( String cfgFilePath )
    {
        if ( sessionFactory == null )
        {
            try {
                sessionFactory = new Configuration().configure( cfgFilePath ).buildSessionFactory();
            }
            catch ( Throwable ex ) {
                throw new ExceptionInInitializerError( ex );
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
