import java.io.*;
import java.util.*;
import java.lang.*;

public class ac_test{
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Node DictionaryRootNode = new Node('\0');
        Node UserHistoryRootNode = new Node('\0');
        Node tempNode = DictionaryRootNode;
       
        
        File Dictionary = new File("dictionary.txt");
        File UserHistory = new File("user_history.txt");
        DLBTrieConstructor(DictionaryRootNode, Dictionary);
        DLBTrieConstructor(UserHistoryRootNode, UserHistory);
        FileWriter writer = new FileWriter(UserHistory, true);
        if(!UserHistory.exists())
            UserHistory.createNewFile();
        
        tempNode = tempNode.child;
        
        
        System.out.println("Autocomplete Program");
        System.out.println("Type a word one character at a time to get a list of predicted words");
        System.out.println("Enter the number of a predicted word to complete it, or if your word is complete type '$' to add it to your history");
        System.out.println("Enter '!' at any time to exit");
        
        char currentChar = '0';
        String input = "";
        String word = "";
        Scanner scan = new Scanner(System.in);
        long runningTotalTime = 0;
        int numOfPredictions = 0;
        
        System.out.println("Please enter a character: ");
        String[] printable = new String[5];
        input = scan.next();
        while(currentChar!='!'){
            if(input.length()==1){
                currentChar = input.charAt(0);
                if(currentChar=='!'){
                    System.out.println("Average Time: " +(runningTotalTime/(numOfPredictions*1000000000.0)) + " seconds");
                    break;
                }
                if(currentChar=='$'){
                    UserHistoryRootNode.addWord(word);
                    writer.append(word + "\n");
                    System.out.println("Word added");
                    word = "";
                    System.out.println("Please enter a character: ");
                    input = scan.next();
                    continue;
                }
                if(currentChar=='1'||currentChar=='2'||currentChar=='3'||currentChar=='4'||currentChar=='5'){
                    word = printable[Character.getNumericValue(currentChar)-1];
                    UserHistoryRootNode.addWord(word);
                    writer.append(word + "\n");
                    System.out.println("Word added");
                    word = "";
                    System.out.println("Please enter a character: ");
                    input = scan.next();
                    continue;
                }
                word +=currentChar;
                printable = getPredictions(word, UserHistoryRootNode, DictionaryRootNode);
                System.out.println(Double.parseDouble(printable[5])/1000000000.0 + " seconds");
                for(int i=0;i<5;i++){
                      if(printable[i]==null)
                      printable[i] = "N/A";
                      System.out.print("(" + (i+1) + ")" + printable[i] + "\t");
                }
                       
                runningTotalTime += Long.parseLong(printable[5]);
                numOfPredictions++;
                System.out.println();
                System.out.println("Please enter a character: ");
                input = scan.next();

            }
            else{
                System.out.println("Please enter only a single character");
                input = scan.next();
                continue;
            }
        }
        writer.close();
    }
    public static String[] getPredictions(String prefix, Node UserHistoryNode, Node DictNode){
        long start = System.nanoTime();
        boolean DictionaryFlag = true;
        boolean UserHistoryFlag = true;
        for(int i = 0;i<prefix.length();i++){
            //System.out.println(prefix.charAt(i));
            if(!UserHistoryFlag&&!DictionaryFlag)
            break;
            if(UserHistoryNode==null||UserHistoryNode.child==null){
                UserHistoryFlag=false;
            }
            if(DictNode==null||DictNode.child==null){
                DictionaryFlag = false;
            }
            if(UserHistoryFlag){
                UserHistoryNode = UserHistoryNode.child.findNode(prefix.charAt(i));
                if(UserHistoryNode==null)
                    UserHistoryFlag=false;
            }
            if(DictionaryFlag){
                DictNode = DictNode.child.findNode(prefix.charAt(i));
                if(DictNode==null)
                    DictionaryFlag=false;
            }
        }
        Node CurrentNode = UserHistoryNode;
        Stack NodeHistory = new Stack();
        String[] predictions = new String[6];
        int count = 0;
        String stringToOutput = prefix.substring(0,prefix.length()-1);
        predictionloop:
        while(count<5){
            if(UserHistoryFlag){
                if(UserHistoryNode.data=='^'){
                    boolean uniqueFlag = true;
                    for(int i = 0;i<count;i++){
                        if(predictions[i].equals(stringToOutput))
                        uniqueFlag=false;
                    }
                    if(uniqueFlag){
                        predictions[count]=stringToOutput;
                        count++;
                    }
                    if(UserHistoryNode.next==null){
                          UserHistoryNode = (Node) NodeHistory.pop();
                          stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                          while(UserHistoryNode.next==null&&!NodeHistory.empty()){
                              UserHistoryNode = (Node) NodeHistory.pop();
                              stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                          }
                          if(NodeHistory.empty())
                          UserHistoryFlag = false;
                          UserHistoryNode = UserHistoryNode.next;
                        }
                    else
                        UserHistoryNode = UserHistoryNode.next;
                }
                else if(UserHistoryNode.child!=null){
                    NodeHistory.push(UserHistoryNode);
                    stringToOutput+=UserHistoryNode.data;
                    UserHistoryNode = UserHistoryNode.child;
                }
                /*else{
                    System.out.println("REACHED");
                    if(NodeHistory.isEmpty())
                        break;
                   
                    UserHistoryNode =(Node) NodeHistory.pop();
                    stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                    while(UserHistoryNode.next==null&&NodeHistory.empty()){
                        UserHistoryNode = (Node) NodeHistory.pop();
                        stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                    }
                    if(NodeHistory.isEmpty())
                        break;
                    UserHistoryNode=UserHistoryNode.next;
                }*/
            }
            else if(DictionaryFlag){
                if(DictNode.data=='^'){
                    boolean uniqueFlag = true;
                    for(int i = 0;i<count;i++){
                        if(predictions[i].equals(stringToOutput))
                        uniqueFlag=false;
                    }
                    if(uniqueFlag){
                        predictions[count]=stringToOutput;
                        count++;
                    }
                    if(DictNode.next==null){
                          DictNode = (Node) NodeHistory.pop();
                          stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                          while(DictNode.next==null&&!NodeHistory.empty()){
                              DictNode = (Node) NodeHistory.pop();
                              stringToOutput = stringToOutput.substring(0,stringToOutput.length()-1);
                          }
                          if(NodeHistory.empty())
                          DictionaryFlag = false;
                          DictNode = DictNode.next;
                        }
                    else
                        DictNode = DictNode.next;
                }
                else if(DictNode.child!=null){
                    NodeHistory.push(DictNode);
                    stringToOutput+=DictNode.data;
                    DictNode = DictNode.child;
                }
            }
            else
            break;
        }
        long end = System.nanoTime();
        predictions[5]=Long.toString((end-start));
        return predictions;
    }
    public static void DLBTrieConstructor(Node RootNode, File file) throws IOException
    {  
        Node currentNode = RootNode;
        String dir  = System.getProperty("user.dir");
       try{ 
           BufferedReader reader = new BufferedReader(new FileReader(file));         //read file
           String line;
           while((line = reader.readLine()) != null){                                                               //read each word and add to tree
               currentNode.addWord(line);
               currentNode = RootNode;
            }
           reader.close();
        }   
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
}
class Node{
    Node next;
    Node child;
    char data;
    boolean endOfWord;
    public Node(Node kid, Node nxt, char dat, boolean y)
    {
        child = kid;
        next = nxt;
        data = dat;
    }
    public Node(char dat){
        next = null;
        child = null;
        data = dat;
    }
    public void addChild(char x)
    {
         if(child!=null)
             child.addNext(x);
         else
             child = new Node(x);
    }
    public Node addNext(char x){
        if(next==null){
            next = new Node(x);
            return next;}
        else
            return next.addNext(x);
    }
    public Node findNode(char x){
        if(data==x)
            return this;
        if(next==null)
        return null;
        return next.findNode(x);
    }
    public boolean containsChar(char x){
        if(data==x)
            return true;
        else if(next==null)
            return false;
        return next.containsChar(x);
    }
    public void addWord(String line){
        Node currentNode=this;
        line+='^';
        for(int i = 0;i<line.length();i++){
            char temp = line.charAt(i);
            if(currentNode.child==null){
                currentNode.addChild(temp);
                currentNode = currentNode.child;
            }
            else if(currentNode.child.containsChar(temp))
                currentNode = currentNode.child.findNode(temp);
            else
                currentNode = currentNode.child.addNext(temp);
        }
    }
}