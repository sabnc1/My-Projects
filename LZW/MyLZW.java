/*************************************************************************
 *  Compilation:  javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  Execution:    java LZW + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *
 *  Compress or expand binary input from standard input using LZW.
 *
 *  WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/

public class MyLZW {
    private static final int R = 256;        // number of input chars
    private static int L = 512;       // number of codewords = Math.pow(2,W)
    private static int W = 9;         // codeword width
    private static String mode = "n";   //mode for full dictionary
    private static int originalSize = 0;
    private static int compressedSize = 0;
    private static boolean isMonitoringRatio = false;
    private static double currentRatio = 0;
    private static double savedRatio = 0;
    

    public static void compress() { 
        W = 9;
        L = 512;
        originalSize = 0;
        compressedSize = 0;
        
        if(mode.equals("r")) BinaryStdOut.write('r',8);
        if(mode.equals("n")) BinaryStdOut.write('n',8);
        if(mode.equals("m")) BinaryStdOut.write('n',8);
        
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF

        while (input.length() > 0) {
            L = (int) Math.pow(2,W);
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOut.write(st.get(s), W);  // Print s's encoding.
            compressedSize += W;
            int t = s.length();
            originalSize = t*8;
            currentRatio = originalSize/compressedSize;
            if (t < input.length() && code < L)
            {
                // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            }
            if( (W<16) && (code==L) ) {
                W++;
                L = (int) Math.pow(2,W);
                st.put(input.substring(0,t+1),code++);
            }
            
            //Reset mode
            if(code==65536&&mode.equals("r")){
                st = new TST<Integer>();
                for (int i = 0; i < R; i++)
                st.put("" + (char) i, i);
                code = R+1;  // R is codeword for EOF
                W = 9;
                L = 512;
            }
            
            //Monitor mode
            if(code==65536&&mode.equals("m")){
                if(!isMonitoringRatio){
                    savedRatio = currentRatio;
                    isMonitoringRatio = true;
                }
                if(savedRatio/currentRatio > 1.1){
                    st = new TST<Integer>();
                    for (int i = 0; i < R; i++)
                    st.put("" + (char) i, i);
                    code = R+1;  // R is codeword for EOF
                    W = 9;
                    L = 512;
                    savedRatio = 0;
                    currentRatio = 0;
                    isMonitoringRatio = false;
                }
            }
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    } 


    public static void expand() {
        char modeChar = BinaryStdIn.readChar(8);
        if(modeChar == 'r')
            mode = "r";
        if(modeChar == 'm')
            mode = "m";
        String[] st = new String[65536];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(W);
        if (codeword == R) return;           // expanded message is empty string
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val);
            originalSize+=val.length()*8;
            
            codeword = BinaryStdIn.readInt(W);
            compressedSize += W;
            currentRatio = originalSize/compressedSize;
            String s = st[codeword];

            if (codeword == R) break;
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L-1) st[i++] = val + s.charAt(0);
            
            if(i==L-1&&W<16)
            {
                st[i++] = val+s.charAt(0);
                W++;
                L = (int) Math.pow(2,W);
            }
            val = s;
            if(mode.equals("r") && i==65535)
            {
                W = 9;
                L = 512;
                st = new String[65536];
                for (i = 0; i < R; i++)
                    st[i] = "" + (char) i;
                st[i++] = "";
                codeword = BinaryStdIn.readInt(W);
                if(codeword==R)break;
                val = st[codeword];
            }
            if(mode.equals("m")&&i==65535){
                if(!isMonitoringRatio){
                    savedRatio = currentRatio;
                    isMonitoringRatio = true;
                }
                if(savedRatio/currentRatio>1.1){
                   W = 9;
                   L = 512;
                   st = new String[65536];
                   for (i = 0; i < R; i++)
                        st[i] = "" + (char) i;
                   st[i++] = "";
                   codeword = BinaryStdIn.readInt(W);
                   if(codeword==R)break;
                   val = st[codeword]; 
                   savedRatio = 0;
                   currentRatio = 0;
                   isMonitoringRatio = false;
                }
            }
        }
        BinaryStdOut.close();
    }



    public static void main(String[] args) {
        if      (args[0].equals("-")){
            if(args[1].equals("r"))
            mode = "r";
            if(args[1].equals("n"))
            mode = "n";
            if(args[1].equals("m"))
            mode = "m";
            
            compress();
        }
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

}
