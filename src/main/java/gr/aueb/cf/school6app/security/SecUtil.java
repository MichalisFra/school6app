package gr.aueb.cf.school6app.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecUtil {

    private SecUtil(){}

    public static String hashPassword(String inputPassword) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(inputPassword, salt);
    }

    public static boolean isPasswordValid(String inputPassword, String storedHashedPassword) {
        return BCrypt.checkpw(inputPassword, storedHashedPassword);
    }
}
