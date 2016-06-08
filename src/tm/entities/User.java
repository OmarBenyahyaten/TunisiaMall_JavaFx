/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timoumi med hedi
 */
public class User {
    private String path;
    private int id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private boolean locked;
    private boolean expired;
    private Date expiresAt;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private boolean credentialsExpired;
    private Date credentialsExpiredAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Date getCredentialsExpiredAt() {
        return credentialsExpiredAt;
    }

    public void setCredentialsExpiredAt(Date credentialsExpiredAt) {
        this.credentialsExpiredAt = credentialsExpiredAt;
    }

    public String base_convert(String inputValue, int fromBase, int toBase) {
        return new BigInteger(inputValue, fromBase).toString(toBase);
    }

    public static String uniqid(String prefix, boolean more_entropy) {
        long time = System.currentTimeMillis();
        //String uniqid = String.format("%fd%05f", Math.floor(time),(time-Math.floor(time))*1000000);
        //uniqid = uniqid.substring(0, 13);
        String uniqid = "";
        if (!more_entropy) {
            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
        } else {
            SecureRandom sec = new SecureRandom();
            byte[] sbuf = sec.generateSeed(8);
            ByteBuffer bb = ByteBuffer.wrap(sbuf);

            uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
            uniqid += "." + String.format("%.8s", "" + bb.getLong() * -1);
        }

        return uniqid;
    }

    public String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public User() {
        this.password="just1";
        this.username="just1";
        this.usernameCanonical="just1";
        this.email="just1@live.com";
        this.emailCanonical="just1@live.com";
        this.enabled = true;
        
        Random rn = new Random();

        try {
            this.salt = base_convert(sha1(uniqid(Long.toString(rn.nextInt()), true)), 16, 36);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.locked = false;
        this.expired = false;
        this.roles = "a";
        this.credentialsExpired = false;
        this.roles="sannou3";
        this.credentialsExpired=false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User(String username, String email, String password,String path) {
        this.username = username;
        this.usernameCanonical=username;
        this.email = email;
        this.emailCanonical=email;
        this.password = password;
        this.enabled=false;
        this.path=path;
        Random rn = new Random();

        try {
            this.salt = base_convert(sha1(uniqid(Long.toString(rn.nextInt()), true)), 16, 36);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.locked = false;
        this.expired = false;
        this.roles = "a";
        this.credentialsExpired = false;
        this.roles="a:1:{i:0;s:10:\"ROLE_AGENT\";}";
        this.credentialsExpired=false;
    }
 public User(String username, String email, String password,String path,String roles) {
        this.username = username;
        this.usernameCanonical=username;
        this.email = email;
        this.emailCanonical=email;
        this.password = password;
        this.enabled=false;
        this.path=path;
        Random rn = new Random();

        try {
            this.salt = base_convert(sha1(uniqid(Long.toString(rn.nextInt()), true)), 16, 36);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.locked = false;
        this.expired = false;
        this.roles = "a";
        this.credentialsExpired = false;
        this.roles=roles;
        this.credentialsExpired=false;
    }
    public User(int id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Date lastLogin, boolean locked, boolean expired, Date expiresAt, String confirmationToken, Date passwordRequestedAt, String roles, boolean credentialsExpired, Date credentialsExpiredAt,String path) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.locked = locked;
        this.expired = expired;
        this.expiresAt = expiresAt;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.credentialsExpired = credentialsExpired;
        this.credentialsExpiredAt = credentialsExpiredAt;
        this.path=path;
    }

    
    
    
    
    public User(int id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
    }

    
    @Override
    public String toString() {
        return "user{" + "id=" + id + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password +  ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", locked=" + locked + ", expired=" + expired + ", expiresAt=" + expiresAt + ", roles=" + roles + ", credentialsExpired=" + credentialsExpired + ", credentialsExpiredAt=" + credentialsExpiredAt + '}';
    }


}
