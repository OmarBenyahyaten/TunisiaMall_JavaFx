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
public class UsernameValidator{
	
	  private Pattern pattern;
	  private Matcher matcher;
 
	  private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	  
	  public UsernameValidator(){
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
