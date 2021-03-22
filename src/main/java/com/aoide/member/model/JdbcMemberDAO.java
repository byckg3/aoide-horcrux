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

public class JdbcMemberDAO implements MemberDAO
{  
	private static final String INSERT_STMT = 
		"INSERT INTO members( account, password, name, email ) VALUES ( ?, ?, ?, ? )";
		
	private static final String UPDATE_STMT = 
		"UPDATE members SET password = ?, name = ?, email = ? WHERE account = ?";
		
	private static final String DELETE_STMT = 
		"DELETE FROM members WHERE account = ?";
		
	private static final String GET_ONE_STMT = 
		"SELECT * FROM members WHERE account = ?";
		
	private static final String GET_ALL_STMT = 
		"SELECT * FROM members";
	
	private DataSource dataSource;
	
	public JdbcMemberDAO() {}

	public JdbcMemberDAO( DataSource dataSource ) {
		this.dataSource = dataSource;
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
	public int delete( String account )
	{
		int deletedRowCount = 0;
		
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( DELETE_STMT ) )
		{
			pstmt.setString( 1, account );
			deletedRowCount = pstmt.executeUpdate();

			return deletedRowCount;
		}
		catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
	}

	@Override
	public Optional< Member > findByAccount( String account )
	{
		try( Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement( GET_ONE_STMT ) )
		{
			pstmt.setString( 1, account );

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

	private Connection getConnection() throws SQLException
	{
		try
		{
            if ( dataSource != null )
			{
				return dataSource.getConnection();
			}
			String currentDir = System.getProperty( "user.dir" );
			System.out.println( "no data source object..." );
			return DriverManager.getConnection( "jdbc:h2:tcp://localhost/" + currentDir + "/aoide", "admin", "admin" );
        }
		catch ( SQLException e ) {
			throw e;
        }
	}
	
	public static void main(String[] args) {

		Member newVo = new Member();

		newVo.setAccount( "test012" );
		newVo.setPassword( "abcde" );
		newVo.setName( "Curry" );
		newVo.setEmail( "curry02@email.com" );

		try
		{
			JdbcMemberDAO dao = new JdbcMemberDAO();

			List< Member > list = dao.getAll();
			for ( Member vo : list )
			{
				System.out.println( vo.toString() );
			}

			Optional< Member > optionalMember = dao.findByAccount( "test012" );
			System.out.println( optionalMember.get() );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	
	}
}
