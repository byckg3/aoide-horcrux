package com.aoide.model.member;

import javax.persistence.*;

@Entity
@Table( name = "members" )
public class Member implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;

	@Column( name = "account", unique = true )
	private String account;

	@Column( name = "password" )
	private String password;

	@Column( name = "name" )
	private String name;

	@Column( name = "email" )
	private String email;
	
	@Column( name = "salt", nullable = true )
	private String salt;

	public Long getId() {
		return id;
	}
	public void setId( Long memberId) {
		this.id = memberId;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail( String email ) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}
	public void setSalt( String salt ) {
		this.salt = salt;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( id + "\t" ).append( account + "\t" ).append( password + "\t" ).append( name + "\t" ).append( email );
		return sb.toString();
	}
}
