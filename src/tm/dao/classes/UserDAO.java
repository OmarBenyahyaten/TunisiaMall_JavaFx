/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.classes;
//import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tm.dao.interfaces.IUserDAO;
import tm.entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import tm.technique.DataSource;
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO implements IUserDAO{
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    public UserDAO() {
       connection =(DataSource.getInstance()).getConnection();
    }
    public static String getEncodedPassword(String key) {
	  byte[] uniqueKey = key.getBytes();
	  byte[] hash = null;
	  try {
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
	  } catch (NoSuchAlgorithmException e) {
		throw new Error("no MD5 support in this VM");
	  }
	  StringBuffer hashString = new StringBuffer();
	  for ( int i = 0; i < hash.length; ++i ) {
		String hex = Integer.toHexString(hash[i]);
		if ( hex.length() == 1 ) {
		  hashString.append('0');
		  hashString.append(hex.charAt(hex.length()-1));
		} else {
		  hashString.append(hex.substring(hex.length()-2));
		}
	  }
	  return hashString.toString();
	}
public static boolean testPassword(String clearTextTestPassword,
				   String encodedActualPassword)
				   throws NoSuchAlgorithmException
	{
	String encodedTestPassword = getEncodedPassword(
					  clearTextTestPassword);

	return (encodedTestPassword.equals(encodedActualPassword));
	}

    @Override
    public void insertUser(User user) {
        try {
            String salt="";
            String req="insert into user (username,username_canonical,email,email_canonical,enabled,salt,password,locked,expired,roles,credentials_expired,path_image) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=connection.prepareStatement(req);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getUsernameCanonical());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getEmailCanonical());
            pst.setBoolean(5, user.isEnabled());
            pst.setString(6, salt);
            pst.setString(7, getEncodedPassword(user.getPassword()));
            pst.setBoolean(8, user.isLocked());
            pst.setBoolean(9, user.isExpired());
            pst.setString(10, user.getRoles());
            pst.setBoolean(11, user.isCredentialsExpired());
            pst.setString(12, user.getPath());
            System.out.println(pst);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUserName(String username,int id) {
       try {
            String req2= "update user set "
            + "username='"+username+"',username_canonical='"+username+"' where id="+id;
            System.out.println(req2);
            pst.executeUpdate(req2);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public void updateEmail(String mail,int id) {
       try {
            String req2= "update user set "
            + "email='"+mail+"',email_canonical='"+mail+"' where id="+id;
            System.out.println(pst);
            pst.executeUpdate(req2);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(int id) {
      
        String requete = "delete from user where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            System.out.println(ps);
            ps.executeUpdate();
            
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    @Override
    public List<User> DisplayAllUser() {
        String req="select * from user";
        List<User> users=new ArrayList<>();
        
        try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            
            while(rs.next()){
                User u=new User(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getBoolean("enabled"));
               users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
 @Override
    public User findUserByUsername(String username) {
       String req="select * from user where username = "+"'"+username+"'";
       
       try {
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            System.out.println(rs);
           if( rs.next())
           {
           User u=new User(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getBoolean("enabled"),rs.getString("salt"),rs.getString("password"),rs.getDate("last_login"),rs.getBoolean("locked"),rs.getBoolean("expired"),rs.getDate("expires_at"),rs.getString("confirmation_token"),rs.getDate("password_requested_at"),rs.getString("roles"),rs.getBoolean("credentials_expired"),rs.getDate("credentials_expire_at"),rs.getString("path_image"));
       return u;
           }
           else
           {
               
               return null;
           }
       } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null; 
    }
    @Override
    public boolean connect(String username,String password) {
        User u=new User();
        u=findUserByUsername(username);
        
        if(u!=null)
        {
            try {
                if(testPassword(password,u.getPassword())&&(u.isEnabled()))
                {
                    return true;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
        }
       
public void updateLastLogin(User user)
{
    
 try {
            String req2= "update user set last_login=sysdate() where id="+user.getId();
            pst.executeUpdate(req2);
            System.out.println(req2);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void enableUser(int id)
{
    String requete = "update user set enabled=1 where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            System.out.println(ps);
            ps.executeUpdate();
            
            System.out.println("enablig effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    
 
    }
   
    @Override
    public void changePassword(int id,String pass)
    {
        try {
            String req2= "update user set password='"+BCrypt.hashpw(pass, BCrypt.gensalt())+"' where id="+id;
            System.out.println(req2);
            pst.executeUpdate(req2);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ObservableList<User> DisplayAllUserObs() {
      ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                User pro = new User();
                pro.setId(resultat.getInt(1));
                pro.setUsername(resultat.getString(2));
                pro.setEmail(resultat.getString(4));
                pro.setLastLogin(resultat.getTimestamp("lastlogin"));
                

                listepromo.add(pro);
                System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }
    @Override
    public ObservableList<User> DisplayAllUserObsDisabled() {
      ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user where enabled="+0;
        System.out.println(requete);
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                User pro = new User();
                pro.setId(resultat.getInt(1));
                pro.setUsername(resultat.getString(2));
                pro.setEmail(resultat.getString(4));
                pro.setPassword((resultat.getString(8)));
                

                listepromo.add(pro);
                System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void updateLastLoginSign(int id)
{
    
 try {
            String req2= "update user set last_login=sysdate() where id="+id;
            pst.executeUpdate(req2);
            System.out.println(req2);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ObservableList<User> findUserByUsernameObs(String search)
    {
        ObservableList<User> listepromo = FXCollections.observableArrayList();
        String requete = "select * from user where username like '"+search+"%' and enabled="+0;
        System.out.println(requete);
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                User pro = new User();
                pro.setId(resultat.getInt(1));
                pro.setUsername(resultat.getString(2));
                pro.setEmail(resultat.getString(4));
               // pro.setLastLogin(resultat.getTimestamp("lastlogin"));
                

                listepromo.add(pro);
                System.out.println(pro.toString());
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }
    
}
