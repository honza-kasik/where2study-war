package cz.honzakasik.upol.where2study.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Util class handling user related tasks
 */
public class UserUtils {
	
	/**
	 * Get standartized password hash for given password
	 * @param passwd password for  which the hash will be generated
	 * @return password hash
	 */
    public static String getPasswdHash(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passwd.getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
