package com.aoide.util;

import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aoide.util.HibernateUtil;

public class HibernateDAOHelper< T >
{
    private String cfgFile;

    public HibernateDAOHelper( String cfgFilePath )
    {
        this.cfgFile = cfgFilePath;
    }

    public void persist( T vo )
    {
        Transaction transaction = null;
        try( Session session = openHibernateSession() )
        {
            transaction = session.beginTransaction();

            session.persist( vo );

            transaction.commit();
        }
        catch( Exception e )
        {
            if ( transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException( e );
        }
    }

    public int parameterizedQuery( String hql, Object... params )
    {
        int affectedRows = 0;

        Transaction transaction = null;
        try( Session session = openHibernateSession() )
        {
            transaction = session.beginTransaction();

            Query query = session.createQuery( hql );
            for ( int i = 0; i < params.length; i++ )
            {
                query.setParameter( i + 1, params[ i ] );
            }
            affectedRows = query.executeUpdate();
        
            transaction.commit();

            return affectedRows;
        }
        catch( Exception e )
        {
            if ( transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException( e );
        }
    }

    public List< T > list( String hql, Object... params )
    {
        try( Session session = openHibernateSession() )
        {
            session.beginTransaction();

            Query query = session.createQuery( hql );
            for ( int i = 0; i < params.length; i++ )
            {
                query.setParameter( i + 1, params[ i ] );
            }
            List< T > list = query.getResultList();

            session.getTransaction().commit();

            return list;
        }
        catch( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    private Session openHibernateSession()
    {
        return HibernateUtil.getSessionFactory( cfgFile ).openSession();
    }
}
