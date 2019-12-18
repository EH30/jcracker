import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class jforce{

    public static String os = System.getProperty("os.name").toLowerCase();

    public static String crypto(String str, String hashing){
        try {
            MessageDigest md = MessageDigest.getInstance(hashing);
            byte[] b = md.digest(str.getBytes());

            BigInteger bg = new BigInteger(1, b);
            String hashed = bg.toString(16);
            while (hashed.length() < 32){
                hashed = "0" + hashed;
            }

            return hashed.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void read_file(String filename, String hash){
        try {
            String line;
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                System.out.print(String.format("\r \u001B[33m [+]Trying: %s \u001B[0m", line.trim()));
                System.out.flush();
                if(crypto(line, "MD5").equals(hash) | crypto(line.trim(), "MD5").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: MD5 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "MD2").equals(hash) | crypto(line.trim(), "MD2").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: MD2 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "SHA-1").equals(hash) | crypto(line.trim(), "SHA-1").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: SHA-1 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "SHA-224").equals(hash) | crypto(line.trim(), "SHA-224").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: SHA-224 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "SHA-256").equals(hash) | crypto(line.trim(), "SHA-256").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: SHA-256 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "SHA-384").equals(hash) | crypto(line.trim(), "SHA-384").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: SHA-384 Cracked: %s \u001B[0m", line));
                    break;
                }
                else if(crypto(line, "SHA-512").equals(hash) | crypto(line.trim(), "SHA-512").equals(hash)){
                    System.out.println(String.format("\u001B[36m Type: SHA-512 Cracked: %s \u001B[0m", line));
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void console_clear(){
        try {
            if(oswindows()){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else if(oslinux()){
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
  
    }


    public static void main(String[] args) {
        console_clear();

        try {
            read_file(args[0], args[1]);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("Usege: java jforce [wdlist] [hash]");
        }
    }

    public static boolean oswindows(){return os.contains("windows") | os.contains("win");}
    public static boolean oslinux(){return os.contains("linux") | os.contains("unix");}
}