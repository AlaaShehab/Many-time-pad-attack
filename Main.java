import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    public static void main (String[] args) throws java.lang.Exception
    {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
        HashSet<Integer> alphabet_set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            alphabet_set.add((int) str.charAt(i));
        }
        String[] msgs = {
                "588B12E8140D66511035513D07656FF4A29135FA7814A41C367BFCBC88",
                "588B16E9190D2A1615265C3C033179B1A88A67F93D10EB172D62E9A69E",
                "5FCE13E40A41345317275068097F6FF4BF9678F93D13E515797EEDAD88",
                "42CE06F40A08324F42354229147464B1B88C35F56E43E7032C76E1B597",
                "44D800A10C1629160435563C09632AB5BE8B7DF97317ED123861E1BB95",
                "45C300A11D0F235B1B745E26096679F4BF9770BC7C0FE31E2B7CFCBC96",
        };
        char[][] messages = new char[6][29];
        for (char[] a : messages) {
            Arrays.fill(a, '?');
        }
        for (int st = 0; st < msgs[0].length(); st += 2) {
            System.out.println("Character: " + (st / 2));
            int answer = 0;
            int validKey = 0;
            for (int key = 0; key < 256; key++) {
                boolean allValid = true;
                List<Character> valid = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    String hex = msgs[i].substring(st, st + 2);
                    int encrypted = Integer.parseInt(hex, 16);
                    int msg = key ^ encrypted;
                    allValid &= alphabet_set.contains(msg);
                    valid.add((char) msg);
                }
                if (allValid) {
                    System.out.println(valid);
                    validKey = key;
                    answer++;
                }
            }
            if (answer == 1) {
                for (int i = 0; i < 6; i++) {
                    String hex = msgs[i].substring(st, st + 2);
                    int encrypted = Integer.parseInt(hex, 16);
                    int msg = validKey ^ encrypted;
                    messages[i][st / 2] = (char) msg;
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(new String(messages[i]));
        }
    }
}