import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class MD5Hash {

    public String convertToMD5Hash(String password) throws NoSuchAlgorithmException {
        String original = password;  //Replace "password" with the actual password inputted by the user
        String hashedPass = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return hashedPass = sb.toString();

    }
}
