package com.aoide.member.controller;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

public class LoginForm
{
    @NotBlank( message = "account cannot be null or empty string" ) 		// validates that the property is not null or whitespace
    @Size( min = 5, max = 14, message = "account size must be between 5 and 14" )	// validates that the annotated property value has a size between the attributes min and max
	private String account;

    @Size(min = 8, max = 16, message = "password size must be between 8 and 16" )
	private String password;

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
}
