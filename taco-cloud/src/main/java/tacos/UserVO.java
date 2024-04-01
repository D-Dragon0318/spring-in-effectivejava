package tacos;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3449967021050669071L;

	private Long id;

	private String username;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
}
