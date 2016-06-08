/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author timoumi med hedi
 */
public class PasswordValidator{
	
	  private Pattern pattern;
	  private Matcher matcher;
 
	  private static final String USERNAME_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	  
	  public PasswordValidator(){
		  pattern = Pattern.compile(USERNAME_PATTERN);
	  }
	  
	  /**
	   * Validate username with regular expression
	   * @param username username for validation
	   * @return true valid username, false invalid username
	   */
	  public boolean validate(final String username){
		  
		  matcher = pattern.matcher(username);
		  return matcher.matches();
	    	    
	  }
}
