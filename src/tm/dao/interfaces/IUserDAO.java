/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import tm.entities.User;

/**
 *
 * @author omarblythe
 */
public interface IUserDAO {
    public void updateLastLogin(User user);
     void insertUser(User user);
public ObservableList<User> findUserByUsernameObs(String search);
   public void updateUserName(String username,int id);
public void updateEmail(String mail,int id);
    void deleteUser(int id);
public void enableUser(int id);
    List<User> DisplayAllUser();
  
    boolean connect (String username,String password) ;
    
    User findUserByUsername(String username);
   // void changePassword(User user,String pass);
    public void changePassword(int id,String pass);
    public ObservableList<User> DisplayAllUserObs();
    public ObservableList<User> DisplayAllUserObsDisabled();
    public void updateLastLoginSign(int id);
}
