/**
 * It is okay to use ArrayList class but you are not allowed to use any other
 * predefined class supplied by Java.
 */
import java.util.ArrayList;

public class CompressDecompress
{
    /**
     * Get a string representing a Huffman tree where its root node is root
     * @param root the root node of a Huffman tree
     * @return a string representing a Huffman tree
     */
    public static String getTreeString(final BinaryNodeInterface<Character> root, String str)
    {
        if(root==null)
        return "";
        if(root.isLeaf())
        str= "L" + root.getData();
        else
        str = "I";
        str = str + getTreeString(root.getLeftChild(),str);
        str = str + getTreeString(root.getRightChild(),str);

        return str;  // Do not forget to change this line!!!
    }

    /**
     * Compress the message using Huffman tree represented by treeString
     * @param root the root node of a Huffman tree
     * @param message the message to be compressed
     * @return a string representing compressed message.
     */
    public static String compress(final BinaryNodeInterface<Character> root, final String message)
    {
        char[] charArray = new char[message.length()];
        charArray = message.toCharArray();
        String str = "";
        for(int i = 0;i<message.length();i++)
        {
            str = str + findPathTo(root,charArray[i]);
        }
        return str;
    }
    public static String findPathTo(final BinaryNodeInterface<Character> root, final char c)
    {
        if(root==null)
        return null;
        String str = "";
        if(root.isLeaf())
        {
            if( root.getData() == c )
            return str;
        }

        if( root.hasLeftChild() )
        {
            String s = findPathTo(root.getLeftChild(), c);

            if( s != null )
            {
                str = str + "0";
                str = str + s;
                return str;
            }
        }

        if( root.hasRightChild() )
        {
            String s = findPathTo(root.getRightChild(), c);

            if( s != null )
            {
                str = str + "1";
                str = str + s;
                return str;
            }
        }
        return null;
    }
    
    /**
     * Decompress the message using Huffman tree represented by treeString
     * @param treeString the string represents the Huffman tree of the
     * compressed message
     * @param message the compressed message to be decompressed
     * @return a string representing decompressed message
     */
    public static String decompress(final String treeString, final String message)
    {
            BinaryNodeInterface<Character> tree = new BinaryNode();
             int count = 0;
             if(message.length()==1)
             {
                 String str = "";
                 str = str + treeString.charAt(2);
                 return str;
             }
            count=1;
            while(treeString.length()>count)
            {
              if(treeString.charAt(count) =='I')
              {
                  if(!tree.hasLeftChild())
                  {
                      tree.setLeftChild(new BinaryNode());
                      tree = tree.getLeftChild();
                      count++;
                    }
                  else if(!tree.hasRightChild())
                  {
                      tree.setRightChild(new BinaryNode());
                      tree = tree.getRightChild();
                      count++;
                  }
                  else
                  {
                      tree = tree.getParent();
                  }
              }
              if(treeString.charAt(count)=='L')
              {
                  
                if(!tree.hasLeftChild())
                  {
                      tree.setLeftChild(new BinaryNode(treeString.charAt(++count)));
                      count++;
                    }
                  else if(!tree.hasRightChild())
                  {
                      tree.setRightChild(new BinaryNode(treeString.charAt(++count)));
                      count++;
                  }
                  else
                  {
                      tree= tree.getParent();
                  }
              }
            }
            while(tree.hasParent())
            {
                tree = tree.getParent();
            }
            String decode = "";
            count = 0;
            while(message.length()>count)
            {
                if(message.charAt(count)=='0')
                {
                    tree = tree.getLeftChild();
                    count++;
                }
                else if(message.charAt(count)=='1')
                {
                    tree = tree.getRightChild();
                    count++;
                }
                if(tree.isLeaf())
                {
                    decode = decode+tree.getData();
                    while(tree.hasParent())
                    {
                        tree = tree.getParent();
                    }
                }
                
            }
            
            return decode;
    }
    
}
