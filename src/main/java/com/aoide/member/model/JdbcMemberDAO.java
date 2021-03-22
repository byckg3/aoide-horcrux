package com.aoide.member.model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;

import java.sql.DriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberDAO implements AoideDAO< Member >
{  
	private static final String INSERT_STMT = 
		"INSERT INTO members( account, password, name, email ) VALUES ( ?, ?, ?, ? )";
		
	private static final String UPDATE_STMT = 
		"UPDATE members SET password = ?, name = ?, email = ? WHERE account = ?";
		
	private static final String DELETE_STMT = 
		"DELETE FROM members WHERE id = ?";
		
	private static final String GET_ONE_STMT = 
		"SELECT * FROM members WHERE id = ?";
		
	private static final String GET_ALL_STMT = 
		"SELECT * FROM members";
	
	private DataSource dataSource;

	private String url;
	private String user;
	private String password;
	
	public JdbcMemberDAO() {}

	public JdbcMemberDAO( DataSource dataSource )
	{
		this.dataSource = dataSource;
	}

	public JdbcMemberDAO( String jdbcUrl, String userName, String password )
	{
		this.url = jdbcUrl;
		this.user = userName;
		this.password = password;
	}
	
	@Override
	public int create( Member vo ) 
	{
		int insertedRowCount = 0;
		
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( INSERT_STMT, Statement.RETURN_GENERATED_KEYS ) )
		{
			pstmt.setString( 1, vo.getAccount() );
			pstmt.setString( 2, vo.getPassword() );
			pstmt.setString( 3, vo.getName() );
            pstmt.setString( 4, vo.getEmail() );
            
			insertedRowCount = pstmt.executeUpdate();

			ResultSet keys = pstmt.getGeneratedKeys();
			if ( keys.next() ) 
			{
				vo.setId( keys.getLong( 1 ) );
			}
			return insertedRowCount;
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	@Override
	public int update( Member vo )
	{
		int updatedRowCount = 0;
		
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( UPDATE_STMT ) )
		{
			pstmt.setString( 1, vo.getPassword());
			pstmt.setString( 2, vo.getName());
            pstmt.setString( 3, vo.getEmail());
			pstmt.setString( 4, vo.getAccount() );

			updatedRowCount = pstmt.executeUpdate();

			return updatedRowCount;
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	@Override
	public int delete( Object pk )
	{
		int deletedRowCount = 0;
		
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( DELETE_STMT ) )
		{
			pstmt.setObject( 1, pk );
			deletedRowCount = pstmt.executeUpdate();

			return deletedRowCount;
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	@Override
	public Optional< Member > findByPrimaryKey( Object pk )
	{
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( GET_ONE_STMT ) )
		{
			pstmt.setObject( 1, pk );

			ResultSet rs = pstmt.executeQuery();
			if ( rs.next() ) 
			{
				Member vo = new Member();
				vo.setId( rs.getLong( "id" ) );
				vo.setAccount( rs.getString( "account" ) );
				vo.setPassword( rs.getString( "password" ) );
				vo.setName( rs.getString( "name" ) );
				vo.setEmail( rs.getString( "email" ) );

				return Optional.of( vo );
			}
			return Optional.empty();
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	@Override
	public List< Member > getAll()
	{
		List< Member > voList = new ArrayList<>();

		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( GET_ALL_STMT );
			 ResultSet rs = pstmt.executeQuery() )
		{
			while ( rs.next() ) 
			{
				Member vo = new Member();
				vo.setId( rs.getLong( "id" ) );
				vo.setAccount( rs.getString( "account" ) );
				vo.setPassword( rs.getString( "password" ) );
				vo.setName( rs.getString( "name" ) );
				vo.setEmail( rs.getString( "email" ) );

				voList.add( vo );
			}
			return voList;
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	public boolean canConnect()
	{
		try( Connection conn = DriverManager.getConnection( url, user, password ) )
		{
			return true;
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		return false;
	}

	private Connection getConnection() throws SQLException
	{
		try
		{
            if ( dataSource != null )
			{
				return dataSource.getConnection();
			}
			System.out.println( "no data source..." );

			return DriverManager.getConnection( url, user, password );
        }
		catch ( SQLException e ) {
			throw e;
        }
	}
}
