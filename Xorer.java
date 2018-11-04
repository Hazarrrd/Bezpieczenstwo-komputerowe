import java.nio.charset.Charset;

import java.io.IOException;

public class Xorer {


    public String [] encode(String[] s, String[] key) {
        String[] out = new String[s.length];
        for(int i=0;i<s.length;i++){
            out[i]=encode(s[i], key[i%key.length]);
          //  System.out.println(i + " asd " + key.length );
        }
        return out;
    }

    public String encode(String s, String key) {
        try{
            return xorWithKey(s, key);
        }catch(Exception ex){
            System.out.println("Unsupported character set" + ex);
        }
        return " ";
    }

    private String xorWithKey(String s, String key) {
        int a = Integer.parseInt(s, 2);
        int b = Integer.parseInt(key, 2);
        byte aa = (byte)a;
        byte bb = (byte)b;
        int xor = aa ^ bb;
      //  System.out.println(xor);
        return String.format("%8s", Integer.toBinaryString(xor & 0xFF)).replace(' ', '0');
    }

    public String convertbinToString(String[] array){

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<array.length; i++){
            if(array[i]!=null  && !array[i].equals("*")){
                int decimal = Integer.parseInt(array[i], 2);
                sb.append((char)decimal);
            }  
            else
                sb.append("*");
        }
        return sb.toString();
    }

    public String[] convertStringToBin(String text){

        StringBuilder sb = new StringBuilder();
        String[] result = new String[text.length()];
        int i=0;
        for (char c : text.toCharArray()){
           result[i]=Integer.toBinaryString((int)c);
           i++;
        }   
        return result;
    }

}