package aoide.model.member;

public class MemberVO implements java.io.Serializable
{
	private Integer id;
	private String account;
	private String password;
	private String name; 
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
