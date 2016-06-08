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
public class EmailValidator{
	
	  private Pattern pattern;
	  private Matcher matcher;
 
	   private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
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
