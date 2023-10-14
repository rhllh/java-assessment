package com.dxc.assessment;

/**
 * 
 * mvn compile exec:java -Dexec.mainClass="com.dxc.assessment.App" -Dexec.args="e b 'I PLAY BY THE RULES'"
 * output -> BH OK/X AX SGD QTKDR
 * 
 * mvn compile exec:java -Dexec.mainClass="com.dxc.assessment.App" -Dexec.args="d b 'H OK/X AX SGD QTKDR'"
 * output -> I PLAY BY THE RULES
 */
public class App {

    static String origTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    static String encode(String offsetChar, String plainText) {
        int offset = origTable.indexOf(offsetChar);
        String newTable = origTable.substring(origTable.length()-offset) 
                            + origTable.substring(0, origTable.length()-offset);

        StringBuilder sb = new StringBuilder();
        sb.append(offsetChar);

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            
            if (origTable.indexOf(c) >= 0) {
                int index = origTable.indexOf(c);
                sb.append(newTable.charAt(index));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    static String decode(String offsetChar, String encodedText) {
        int offset = origTable.indexOf(offsetChar);
        String newTable = origTable.substring(offset, origTable.length()) 
                            + origTable.substring(0, offset);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            
            if (origTable.indexOf(c) >= 0) {
                int index = origTable.indexOf(c);
                sb.append(newTable.charAt(index));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main( String[] args ) {

        if ((args.length != 3) || 
            (!((args[0].toLowerCase().equals("e")) || 
                (args[0].toLowerCase().equals("d")))) ||
            (!origTable.contains(args[1].toUpperCase()))) {
            return;
        }

        if (args[0].toLowerCase().equals("e")) { 
            System.out.println(encode(args[1].toUpperCase(), args[2].toUpperCase())); 
        } else { 
            System.out.println(decode(args[1].toUpperCase(), args[2].toUpperCase())); 
        }
    }
}
