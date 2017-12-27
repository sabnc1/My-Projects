import java.util.Arrays;
public class InfiniteInteger implements Comparable<InfiniteInteger>
{
    boolean negative;
    int[] integer;
    int size;

    /**
     * Constructor: Constructs this infinite integer from a string
     * representing an integer.
     * @param s  a string represents an integer
     */
    public InfiniteInteger(String s)
    {
       boolean leadingZero = true;
       size = s.length();
       StringBuilder sb = new StringBuilder(s);
       int x =0;
       if(sb.charAt(0) == '-')
       {
           negative = true;
           size--;
           sb.deleteCharAt(0);
        }
        int j = size;
        for(int i =0; i<j; i++)
        {
            if(leadingZero)
            {
                if(sb.charAt(i)== '0')
                {
                    size--;
                    
                    if(size == 0)
                    {
                        integer = new int[1];
                        integer[0] = 0;
                        size=1;
                        negative = false;
                        break;
                    }
                }
               
                else
                    {
                        leadingZero= false;
                        i--;
                        integer = new int[size];
                    }
            }
            else
            {
                integer[x] = sb.charAt(i);
                integer[x] = integer[x] - 48;
                x++;
            }
        }
    }
    private InfiniteInteger(InfiniteInteger anInfiniteInteger)
    {
        integer = anInfiniteInteger.integer;
        size = anInfiniteInteger.size;
        negative = anInfiniteInteger.negative;
    }

    /**
     * Constructor: Constructs this infinite integer from an integer.
     * @param anInteger  an integer
     */
    public InfiniteInteger(int anInteger)
    {
        this(Integer.toString(anInteger));
    }
    
    /**
     * Gets the number of digits of this infinite integer.
     * @return an integer representing the number of digits
     * of this infinite integer.
     */
    public int getNumberOfDigits()
    {
        return size;
    }

    /**
     * Checks whether this infinite integer is a negative number.
     * @return true if this infinite integer is a negative number.
     * Otherwise, return false.
     */
    public boolean isNegative()
    {
        return negative;
    }

    /**
     * Calculates the result of this infinite integer plus anInfiniteInteger
     * @param anInfiniteInteger the infinite integer to be added to this
     * infinite integer.
     * @return a NEW infinite integer representing the result of this
     * infinite integer plus anInfiniteInteger
     */
    public InfiniteInteger plus(final InfiniteInteger anInfiniteInteger)
    {   InfiniteInteger addition = new InfiniteInteger(0);
        String sum1 = "";
        if(anInfiniteInteger.isNegative()&&!negative)
        {
            anInfiniteInteger.negative =false;
            if(this.compareTo(anInfiniteInteger)== -1)
            {
                addition = new InfiniteInteger(anInfiniteInteger.minus(this));
                addition.negative = true;
            }
            else
            {
                addition = new InfiniteInteger(this.minus(anInfiniteInteger));
                addition.negative = false;
            }
            anInfiniteInteger.negative = true;
        }
        else if(negative&&!anInfiniteInteger.isNegative())
        {
            negative = false;
            if(this.compareTo(anInfiniteInteger)== 1)
            {
                addition = new InfiniteInteger(this.minus(anInfiniteInteger));
                addition.negative = true;
            }
            else
            {
                addition = new InfiniteInteger(anInfiniteInteger.minus(this));
                addition.negative = false;
            }
            
            negative = true;
        }
        else
        {
            //makes new arrays for the operation
            int newSize = size;
             if(anInfiniteInteger.size>=size)
            {
                newSize = anInfiniteInteger.size;
            }
            
            int[] sum = new int[newSize+1];
            int[] newInteger = new int[newSize];
            int[] newInteger1 = new int[newSize];
            if(size>anInfiniteInteger.size)
            {
                for(int x=anInfiniteInteger.integer.length-1;x>=0;x--)
                {
                    newInteger1[x+newSize-anInfiniteInteger.integer.length] = anInfiniteInteger.integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                }
            }
            else if(anInfiniteInteger.size>size)
            {
                for(int x=size-1;x>=0;x--)
                {
                    newInteger[x+newSize-size] = integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            else
            {
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            
            
            
            for(int i = newSize-1;i>=0;i--)
            {
                sum[i+1] = sum[i+1]+newInteger1[i] + newInteger[i];
                if(sum[i+1] >=10)
                {
                        sum[i+1] = sum[i+1]-10;
                        sum[i] = sum[i] + 1;
                }
            }
            
            if(negative&&anInfiniteInteger.negative)
            sum1 = "-" + sum1;
            for(int i =0;i<sum.length;i++)
            {
                sum1 = sum1 + sum[i] + "";
            }

            
             addition = new InfiniteInteger(sum1);
             
        }
        return addition;
    }

    /**
     * Calculates the result of this infinite integer subtracted by anInfiniteInteger
     * @param anInfiniteInteger the infinite integer to subtract.
     * @return a NEW infinite integer representing the result of this
     * infinite integer subtracted by anInfiniteInteger
     */
    public InfiniteInteger minus(final InfiniteInteger anInfiniteInteger)
    {
        InfiniteInteger difference = new InfiniteInteger(0);;
        if(anInfiniteInteger.isNegative()&&!negative)
        {
            anInfiniteInteger.negative = false;
            difference = this.plus(anInfiniteInteger);
            anInfiniteInteger.negative = true;
            
        }
        else if(negative&&!anInfiniteInteger.isNegative())
        {
            negative = false;
            difference = anInfiniteInteger.plus(this);
            difference.negative = true;
        }
        else
        {
            //makes new arrays for the operations
            int newSize = size;
             if(anInfiniteInteger.size>=size)
            {
                newSize = anInfiniteInteger.size;
            }
            
            int[] diff = new int[newSize+1];
            int[] newInteger = new int[newSize];
            int[] newInteger1 = new int[newSize];
            if(size>anInfiniteInteger.size)
            {
                for(int x=anInfiniteInteger.integer.length-1;x>=0;x--)
                {
                    newInteger1[x+newSize-anInfiniteInteger.integer.length] = anInfiniteInteger.integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                }
            }
            else if(anInfiniteInteger.size>size)
            {
                for(int x=size-1;x>=0;x--)
                {
                    newInteger[x+newSize-size] = integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            else
            {
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            if(negative&&anInfiniteInteger.negative)
            {
                if(anInfiniteInteger.compareTo(this)==-1)
                {
                    for(int i = newSize-1;i>=0;i--)
                    {
                        diff[i+1] = diff[i+1]+ newInteger1[i] - newInteger[i];
                        if(diff[i+1]<0)
                        {
                            diff[i+1] = diff[i+1]+10;
                            diff[i] = diff[i] - 1;
                        }
                    }
                }
                else
                {
                    for(int i = newSize-1;i>=0;i--)
                    {   
                        diff[i+1] = diff[i+1]+ newInteger[i] - newInteger1[i];
                        if(diff[i+1]<0)
                        {
                            diff[i+1] = diff[i+1]+10;
                            diff[i] = diff[i] - 1;
                        }
                    }
                }
            }
            else
            {
                if(anInfiniteInteger.compareTo(this)==1)
                {
                    for(int i = newSize-1;i>=0;i--)
                    {
                        diff[i+1] = diff[i+1]+ newInteger1[i] - newInteger[i];
                        if(diff[i+1]<0)
                        {
                            diff[i+1] = diff[i+1]+10;
                            diff[i] = diff[i] - 1;
                        }
                    }
                }
                else
                {
                    for(int i = newSize-1;i>=0;i--)
                    {   
                        diff[i+1] = diff[i+1]+ newInteger[i] - newInteger1[i];
                        if(diff[i+1]<0)
                        {
                            diff[i+1] = diff[i+1]+10;
                            diff[i] = diff[i] - 1;
                        }
                    }
                }
            }
            
            
            
            String diff1 = "";
            if(negative&&anInfiniteInteger.negative)
            {
                if(anInfiniteInteger.compareTo(this)==1)
                diff1 = "-" + diff1;
            }
            else
            {
                if(anInfiniteInteger.compareTo(this)==1)
                diff1 = "-" + diff1;
            }
                for(int i =0;i<newSize;i++)
                {
                    diff1 = diff1 + diff[i+1] + "";
                   
                }
            difference = new InfiniteInteger(diff1);
        }
        return difference;
    }

    /**
     * Calculates the result of this infinite integer multiplied by anInfiniteInteger
     * @param anInfiniteInteger the multiplier.
     * @return a NEW infinite integer representing the result of this
     * infinite integer multiplied by anInfiniteInteger.
     */
    public InfiniteInteger multiply(final InfiniteInteger anInfiniteInteger)
    {
            //makes new arrays for the operations
            int newSize = size;
             if(anInfiniteInteger.size>=size)
            {
                newSize = anInfiniteInteger.size;
            }
            int[] product = new int[newSize+1];
            InfiniteInteger[] products = new InfiniteInteger[newSize];
            int[] newInteger = new int[newSize];
            int[] newInteger1 = new int[newSize];
            if(size>anInfiniteInteger.size)
            {
                for(int x=anInfiniteInteger.integer.length-1;x>=0;x--)
                {
                    newInteger1[x+newSize-anInfiniteInteger.integer.length] = anInfiniteInteger.integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                }
            }
            else if(anInfiniteInteger.size>size)
            {
                for(int x=size-1;x>=0;x--)
                {
                    newInteger[x+newSize-size] = integer[x];
                }
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            else
            {
                for(int y = newSize-1;y>=0;y--)
                {
                    newInteger[y] = integer[y];
                    newInteger1[y] = anInfiniteInteger.integer[y];
                }
            }
            
            
            for(int i = newSize-1;i>=0;i--)
            {
                product = new int[newSize+1];
                for(int j = newSize-1;j>=0;j--)
                {
                    product[j+1] = product[j+1] + (newInteger[i] * newInteger1[j]);
                    while(product[j+1] >=10)
                    {
                        product[j+1] = product[j+1]-10;
                        product[j] = product[j]+1;
                    }
                    
                }
                String prod = "";
                for(int k =0;k<=newSize;k++)
                    {
                        
                        prod = prod + product[k] + "";       
                    }
                for(int y = newSize-1-i;y>0;y--)
                    {
                         prod = prod + "0";
                    }     
                    products[i] = new InfiniteInteger(prod);
            }
            InfiniteInteger finalProduct = new InfiniteInteger(0);
            
            for(int x = 0; x<newSize;x++)
            {
                finalProduct = finalProduct.plus(products[x]);
            }
            if((anInfiniteInteger.negative&&!negative)||(negative&&!anInfiniteInteger.negative))
                finalProduct.negative = true;
            if(finalProduct.toString()=="0"||finalProduct.toString()=="-0")
            finalProduct.negative = false;
            
            
            return finalProduct;
    }
    
    /**
     * Generates a string representing this infinite integer. If this infinite integer
     * is a negative number a minus symbol should be in the front of numbers. For example,
     * "-12345678901234567890". But if the infinite integer is a positive number, no symbol
     * should be in the front of the numbers (e.g., "12345678901234567890").
     * @return a string representing this infinite integer number.
     */
    public String toString()
    {
        String result = "";
        if(integer[0] == 0)
        {
            negative = false;
            return "0";
        }
        if(negative)
        {
            result = "-";
        }
        for(int i =0;i<size;i++)
        {
            result = result + integer[i] + "";
        }
        return result;
    }
    
    /**
     * Compares this infinite integer with anInfiniteInteger
     * @return either -1, 0, or 1 as follows:
     * If this infinite integer is less than anInfiniteInteger, return -1.
     * If this infinite integer is equal to anInfiniteInteger, return 0.
     * If this infinite integer is greater than anInfiniteInteger, return 1.
     */
    public int compareTo(final InfiniteInteger anInfiniteInteger)
    {
        if(anInfiniteInteger.negative&&!negative)
        return 1;
        else if (negative&&!anInfiniteInteger.negative)
        return -1;
        else if(negative&&anInfiniteInteger.negative)
        {
            if(anInfiniteInteger.getNumberOfDigits() > size)
            return 1;
            else if(size>anInfiniteInteger.getNumberOfDigits())
            return -1;
            else
            {
                for(int i = 0;i<size;i++)
                {
                    if(anInfiniteInteger.integer[i]>integer[i]) 
                        return 1;
                        else if(integer[i]>anInfiniteInteger.integer[i])
                        return -1;
                
                    }
                    return 0;
                }
        }
        else
        {
            if(anInfiniteInteger.getNumberOfDigits() > size)
            return -1;
            else if(size>anInfiniteInteger.getNumberOfDigits())
            return 1;
            else
            {
                for(int i = 0;i<size;i++)
                {
                    if(anInfiniteInteger.integer[i]>integer[i]) 
                        return -1;
                        else if(integer[i]>anInfiniteInteger.integer[i])
                        return 1;
                
                    }   
                    return 0;
                }
        }
        
    }
}