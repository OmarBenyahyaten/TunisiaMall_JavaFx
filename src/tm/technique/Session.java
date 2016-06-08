/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.technique;


/**
 *
 * @author timoumi med hedi
 */
public class Session {
   
   //private  Connected connection;
  private String username;
  private int id;
  private int accessToModify;
   private static Session instance;
   private String pass;
   private String email;

  private Session(String username,int id) {
        this.username = username;
        this.id=id;
       this.accessToModify =0;
    }
   
  
   public static Session setInstance(String username,int id) {
        if (instance == null) {
            instance = new Session(username,id);
        }
        return instance;
    }

    public int getAccessToModify() {
        return accessToModify;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAccessToModify(int accessToModify) {
        this.accessToModify = accessToModify;
    }
public static Session getInstance()
{
 return instance;  
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return this.id;
    }
    public  void deleteInstance ()
    {
      instance =null;
    }
   
}