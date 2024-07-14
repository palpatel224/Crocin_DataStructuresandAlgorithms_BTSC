import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class SHA256 {
  public static void main(String[] args) {
    try
    {
        System.out.println("HashCode Generated by SHA-256 for:");
        String msg = "Hello Bob,Let's meet at 5 PM";
        System.out.println("\n" + msg + " : " + toHexString(getSHA(msg)));
    }
    // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
        System.out.println("No algorithm found!!! " + e);
    }
  }
  public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return md.digest(input.getBytes(StandardCharsets.UTF_8));
 }
  public static String toHexString(byte[] hash){
    BigInteger number = new BigInteger(1, hash);
    StringBuilder hexString = new StringBuilder(number.toString(16));
    while(hexString.length() < 32){
      hexString.insert(0, '0');
    }
    return hexString.toString();
    }
}