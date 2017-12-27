import java.util.*;
import java.io.*;


public class pw_check
{
    private static Node rootNode;
    private static Node rootNodeallPW;

    public static void find() throws IOException
    {
        DLBtrieconstructor();
        Node currentNode = rootNode;
        BufferedWriter writer  = new BufferedWriter(new FileWriter(new File("all_passwords.txt")));
        char firstchar = 'a';
        char secondchar = 'b';
        char thirdchar = 'c';
        char fourthchar = 'd';
        char fifthchar ='e';
        
        char[] legalChars = {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','2','3','5','6','7','8','9','!','@','$','^','_','*'};//create array of legal chars
        char[] noNumbChars = {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','!','@','$','^','_','*'};
        char[] noSpecialChars = {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','2','3','5','6','7','8','9'};
        char[] noLetterChars = {'0','2','3','5','6','7','8','9','!','@','$','^','_','*'};
        char[] letters = {'b','c','d','e','f','g','h','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int letterCount = 0;
        int numCount = 0;                                                                                                                                                                            //keep track of how many of each char type
        int specialCount = 0;
        String pass;
        long startTime = System.nanoTime();
        pass = ""+ firstchar+ secondchar+ thirdchar+ fourthchar; //letters[char5];
        //if(dictionaryCheck(pass)){
        //    writer.write(pass);
        //    writer.newLine();
        //}
        for(int a = 0;a<38;a++){
            for(int b = 0;b<38;b++){
                for(int c = 0;c<38;c++){
                    for(int d = 0;d<38;d++){
                        for(int e = 0;e<38;e++){
                            pass = "" + legalChars[a] + legalChars[b] + legalChars[c] + legalChars[d] + legalChars[e];
                            if(legalChars[a]>'a'&&legalChars[a]<='z'){
                                letterCount++;
                            }
                            if(legalChars[b]>'a'&&legalChars[b]<='z'){
                                letterCount++;
                            }
                            if(legalChars[c]>'a'&&legalChars[c]<='z'){
                                letterCount++;
                            }
                            if(legalChars[d]>'a'&&legalChars[d]<='z'){
                                letterCount++;
                            }
                            if(legalChars[e]>'a'&&legalChars[e]<='z'){
                                letterCount++;
                            }
                            if(legalChars[a]>='0'&&legalChars[a]<='9'){
                                numCount++;
                            }
                            if(legalChars[b]>='0'&&legalChars[b]<='9'){
                                numCount++;
                            }
                            if(legalChars[c]>='0'&&legalChars[c]<='9'){
                                numCount++;
                            }
                            if(legalChars[d]>='0'&&legalChars[d]<='9'){
                                numCount++;
                            }
                            if(legalChars[e]>='0'&&legalChars[e]<='9'){
                                numCount++;
                            }
                            if(legalChars[a]=='!'||legalChars[a]=='@'||legalChars[a]=='$'||legalChars[a]=='^'||legalChars[a]=='_'||legalChars[a]=='*'){
                                specialCount++;
                            }
                            if(legalChars[b]=='!'||legalChars[b]=='@'||legalChars[b]=='$'||legalChars[b]=='^'||legalChars[b]=='_'||legalChars[b]=='*'){
                                specialCount++;
                            }
                            if(legalChars[c]=='!'||legalChars[c]=='@'||legalChars[c]=='$'||legalChars[c]=='^'||legalChars[c]=='_'||legalChars[c]=='*'){
                                specialCount++;
                            }
                            if(legalChars[d]=='!'||legalChars[d]=='@'||legalChars[d]=='$'||legalChars[d]=='^'||legalChars[d]=='_'||legalChars[d]=='*'){
                                specialCount++;
                            }
                            if(legalChars[e]=='!'||legalChars[e]=='@'||legalChars[e]=='$'||legalChars[e]=='^'||legalChars[e]=='_'||legalChars[e]=='*'){
                                specialCount++;
                            }
                            double time = (double) (System.nanoTime() - startTime)/ 1000000L;
                            if(dictionaryCheck(pass)&&letterCount>0&&letterCount<4&&numCount<3&&numCount>0&&specialCount<3&&specialCount>0){
                                writer.write(pass + "," + time);
                                writer.newLine();
                            }
                            letterCount = 0;
                            numCount = 0;
                            specialCount = 0;
                        }
                    }
                }
            }
        }
        writer.close();
        
    }
    private static boolean dictionaryCheck(String pass) //returns true if password is valid(there is no conflict between dictionary and word)
    {
        char[] characters = pass.toCharArray();
        Node currentNode = rootNode;
        boolean check = true;
        for(int x=0;x<5;x++){
            if(characters[x]=='7'){
                characters[x] = 't';
            }
            else if(characters[x]=='0'){
                characters[x] = 'o';
            }
            else if(characters[x]=='3'){
                characters[x] = 'e';
            }
            else if(characters[x]=='$'){
                characters[x] = 's';
            }

        }
        if(characters[0]>'a'&&characters[0]<='z'){
          for(int a = 0;a < rootNode.children.length;a++){                            //assuming word starts at first char
            if(characters[0]==rootNode.children[a].data){
                currentNode = rootNode.children[a];
                if(!currentNode.endOfWord){
                    for(int b = 0;b < currentNode.children.length;b++){
                        if(characters[1]==currentNode.children[b].data){
                            currentNode = currentNode.children[b];
                            if(!currentNode.endOfWord){
                                for(int c = 0;c<currentNode.children.length;c++){
                                    if(characters[2]==currentNode.children[c].data){
                                        currentNode = currentNode.children[c];
                                        if(!currentNode.endOfWord){
                                            for(int d = 0;d<currentNode.children.length;d++){
                                                if(characters[3]==currentNode.children[d].data){
                                                    currentNode = currentNode.children[d];
                                                    if(!currentNode.endOfWord){
                                                        for(int e = 0;e<currentNode.children.length;e++){
                                                            if(characters[4]==currentNode.children[e].data){
                                                                check = false;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        check = false;
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        else{
                                            check = false;
                                        }
                                        break;
                                    }
                                }
                            }
                            else{
                                check = false;
                            }
                            break;
                        }
                    }
                }
                else{
                    check = false;
                }
                break;
            }
            }
        }
        if (characters[1]>'a'&&characters[1]<='z'){
            currentNode = rootNode;
           for(int b = 0;b < currentNode.children.length;b++){
             if(characters[1]==currentNode.children[b].data){
                currentNode = currentNode.children[b];
                if(!currentNode.endOfWord){
                    for(int c = 0;c<currentNode.children.length;c++){
                        if(characters[2]==currentNode.children[c].data){
                            currentNode = currentNode.children[c];
                            if(!currentNode.endOfWord){
                                for(int d = 0;d<currentNode.children.length;d++){
                                    if(characters[3]==currentNode.children[d].data){
                                        currentNode = currentNode.children[d];
                                        if(!currentNode.endOfWord){
                                            for(int e = 0;e<currentNode.children.length;e++){
                                                if(characters[4]==currentNode.children[e].data){
                                                    check = false;
                                                    break;
                                                }
                                            }
                                        }
                                        else{
                                            check = false;
                                        }
                                        break;
                                    }
                                }
                            }
                            else{
                                check = false;
                            }
                            break;
                        }
                    }
                }
                else{
                    check = false;
                }
                break;
             }
                    } 
        }
        if(characters[2]>'a'&&characters[2]<='z'){
            currentNode = rootNode;
            for(int c = 0;c<currentNode.children.length;c++){
                if(characters[2]==currentNode.children[c].data){
                    currentNode = currentNode.children[c];
                    if(!currentNode.endOfWord){
                        for(int d = 0;d<currentNode.children.length;d++){
                            if(characters[3]==currentNode.children[d].data){
                                currentNode = currentNode.children[d];
                                if(!currentNode.endOfWord){
                                    for(int e = 0;e<currentNode.children.length;e++){
                                        if(characters[4]==currentNode.children[e].data){
                                            check = false;
                                            break;
                                        }
                                    }
                                }
                                else{
                                    check = false;
                                }
                                break;
                            }
                        }
                    }
                    else{
                        check = false;
                    }
                    break;
                }
             }
        }
        if(characters[3]>'a'&&characters[3]<='z'){
          for(int a = 0;a < rootNode.children.length;a++){                            //assuming word starts at fourth char
            if(characters[3]==rootNode.children[a].data){
               currentNode = rootNode.children[a];
               if(!currentNode.endOfWord){
                   for(int b = 0;a < rootNode.children.length;b++){
                       if(characters[4]==rootNode.children[a].children[b].data){
                           check = false;
                        }
                        break;
                    }
                }
                else {
                    check = false;
                }
                break;
            }
          } 
        }
        return check;
    }
    public static void check()throws IOException{
        String dir  = System.getProperty("user.dir");
        BufferedReader reader = new BufferedReader(new FileReader(dir +  "\\all_passwords.txt"));
        String line;
        String timeTaken;
        rootNodeallPW = new Node (new Node[0],null,' ');
        Node currentNode = rootNodeallPW;
        boolean valid = false;
       
        try{
            while((line = reader.readLine())!=null){
                timeTaken = line.substring(6,line.length());
                line = line.substring(0,5);
                currentNode.addWordandTime(line,timeTaken);
                currentNode = rootNodeallPW;
            }
        }
        catch(IOException e){
            e.printStackTrace();}
        
        System.out.println("Enter Passwords.");
        System.out.println("Press q to quit.");
        Scanner scan = new Scanner(System.in);
        
        while(scan.hasNextLine()){
            //if pw is valid
            
            char[] password = scan.nextLine().toCharArray();
            if(password[0]=='q'||password[0]=='Q')
            break;
            
            currentNode = rootNodeallPW;
            for(int x = 0;x<5;x++){
                for(int y = 0;y<currentNode.children.length;y++){
                    if(password[x]==currentNode.children[y].data)
                    {
                        currentNode = currentNode.children[y];
                        if(x==4)
                        valid = true;
                        
                        break;
                    }
                }
            }
            if(!valid){
                System.out.println("Your password is invalid. Here are the passwords with the closes prefixes");
                Node prefixNode = currentNode;
                int count = 0;
                while(!currentNode.endOfWord)
                {
                    currentNode = currentNode.children[0];
                }
                currentNode = currentNode.parent;
                if(currentNode.children.length>=10){
                    for(int i = 0;i<10;i++){
                        System.out.print(currentNode.parent.parent.parent.data + currentNode.parent.parent.data + currentNode.parent.data + currentNode.data + currentNode.children[i].data + " " + currentNode.children[i].time);
                    }
                }
                else{
                    System.out.println("There are only " + currentNode.children.length + " passwords with the same prefix");
                    for(int i = 0;i<currentNode.children.length;i++){
                        System.out.print(currentNode.parent.parent.parent.data + currentNode.parent.parent.data + currentNode.parent.data + currentNode.data + currentNode.children[i].data + " " + currentNode.children[i].time);
                    }
                }
            }
            else{
                System.out.println("Password is valid. It took the cracker " + currentNode.time + "ms to guess it");
            }
            
        }

    }
    public static void DLBtrieconstructor() throws IOException
    {  
        rootNode = new Node (new Node[0],null,'0');
        Node currentNode = rootNode;
        String dir  = System.getProperty("user.dir");
       try{ 
           BufferedReader reader = new BufferedReader(new FileReader(new File(dir +  "\\dictionary.txt")));         //read dictionary.txt
           String line;
           while((line = reader.readLine()) != null){                                                               //read each word and add to tree
               currentNode.addWord(line);
               currentNode = rootNode;
            }      
        }   
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    public static void main(String args[])throws IOException{
         DLBtrieconstructor();
         if(args[0].equals("-find")){
             find();
            }
         if(args[0].equals("-check")){
             check();
         }
    }
}















class Node
{
    Node[] children;
    Node parent;
    char data;
    boolean endOfWord;
    String time;
    public Node(Node[] arr, Node par, char dat)
    {
        children = arr;
        parent = par;
        data = dat;
        endOfWord = false;
    }
    public void addChild(char x)
    {
         if(children.length == 0){
             children = new Node[1];
             children[0] = new Node(new Node[0],this,x);
            }
         else
         {
             Node[] temp = new Node[children.length +1];                    //copy into temp array one bigger to hold extra value
             for(int y = 0;y<children.length;y++){                      
                temp[y] = children[y];
                }
                temp[children.length] = new Node(new Node[0],this,x);
                children = temp;
            }
            
            
    }
    public void addWord(String line){
        Node currentNode = this;
        if(line.length()<=5){                                                                                    // prune words that are greater than 5 chars
            for(int x = 0;x<line.length();x++){                                                                     //check if part of word already exists
                char dat =  line.charAt(x);
                if(currentNode.children.length == 0){
                    currentNode.addChild(dat);;
                    currentNode = currentNode.children[0];
                }
                else {
                    for(int y = 0;y<currentNode.children.length;y++){
                        
                    if(currentNode.children[y].data == dat){                                                    //if match move down tree
                        currentNode = currentNode.children[y];
                        break;
                    }
                    else if(y == currentNode.children.length-1){                                                //if no match found add char to tree
                        currentNode.addChild(dat);
                        currentNode = currentNode.children[currentNode.children.length-1];
                    }
                  }
                }
              }
              currentNode.endOfWord = true;
            }
    }
     public void addWordandTime(String line,String timeTaken){
        Node currentNode = this;
        if(line.length()<=5){                                                                                    // prune words that are greater than 5 chars
            for(int x = 0;x<line.length();x++){                                                                     //check if part of word already exists
                char dat =  line.charAt(x);
                if(currentNode.children.length == 0){
                    currentNode.addChild(dat);;
                    currentNode = currentNode.children[0];
                }
                else {
                    for(int y = 0;y<currentNode.children.length;y++){
                        
                    if(currentNode.children[y].data == dat){                                                    //if match move down tree
                        currentNode = currentNode.children[y];
                        break;
                    }
                    else if(y == currentNode.children.length-1){                                                //if no match found add char to tree
                        currentNode.addChild(dat);
                        currentNode = currentNode.children[currentNode.children.length-1];
                    }
                  }
                }
              }
              currentNode.endOfWord = true;
              currentNode.time = timeTaken;
            }
        }
    public boolean hasChildren(){
        if(children.length!=0)
        return true;
        else
        return false;
    }
}
