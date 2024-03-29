package tacos;

import lombok.Data;

@Data
public class UserVO {
	private Long id;

	private String username;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
}
