package effective.java.item6;

import java.util.regex.Pattern;

public class EmailValidator {
	
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    
    public static void main(String[] args) {
    	System.out.println(isValidEmail("code_read@bilibili.com"));
    	System.out.println(isValidEmail("www.bilibili.com"));
    }
}
