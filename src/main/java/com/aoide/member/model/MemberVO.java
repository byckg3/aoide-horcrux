package com.aoide.member.model;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

public class MemberVO implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank( message = "account cannot be null or empty string" ) 		// validates that the property is not null or whitespace
    @Size( min = 5, max = 14, message = "size must be between 5 and 14" )	// validates that the annotated property value has a size between the attributes min and max
	private String account;

	private String password;
	private String name;

	@Email( message = "未填寫郵件或格式不正確" )
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId( Integer memberId) {
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( id + "\t" ).append( account + "\t" ).append( password + "\t" ).append( name + "\t" ).append( email );
		return sb.toString();
	}
}
