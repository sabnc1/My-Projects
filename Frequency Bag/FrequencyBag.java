
public class FrequencyBag<T>
{
    private Node firstNode;
    private int numOfEntries;
    private int numOfNodes;
    private boolean firstData;
    /**
     * Constructor
     * Constructs an empty frequency bag.
     */
    public FrequencyBag()
    {
        firstNode = new Node(null, null, 0);;
        numOfEntries = 0;
        numOfNodes = 0;
        firstData = true;
        
    }
    
    /**
     * Adds new entry into this frequency bag.
     * @param aData the data to be added into this frequency bag.
     */
    public void add(T aData)
    {   
        if(firstData)
        {
            firstNode = new Node(aData, null, 1);
            firstData=false;
            numOfNodes = 1;
        }
        else
        {
            checkData(aData).frequency++;
            
        }
        numOfEntries++;
    }
    public Node checkData(T aData)
    {
        Node currentNode = firstNode;
        boolean matchFound = false;
        int count = 0;
        while(!matchFound)
        {
            if(count<numOfNodes)
            {
                if (currentNode == null)
                {
                    count++;
                    currentNode = checkData(aData);
                }
                if((currentNode.data).equals(aData))
                {
                    matchFound = true;
                    break;
                }
                else
                {
                    currentNode = currentNode.next;
                    count++;
                }   
            }
            else
                {
                    Node newNode = new Node(aData, firstNode, 0);
                    firstNode = newNode;
                    currentNode = firstNode;
                    numOfNodes++;
                    matchFound = true;
                    break;
                }
            }
        return currentNode;
    }
    
    /**
     * Gets the number of occurrences of aData in this frequency bag.
     * @param aData the data to be checked for its number of occurrences.
     * @return the number of occurrences of aData in this frequency bag.
     */
    public int getFrequencyOf(T aData)
    {
        int frequency = checkData(aData).frequency;
        return frequency;
    }

    /**
     * Gets the maximum number of occurrences in this frequency bag.
     * @return the maximum number of occurrences of an entry in this
     * frequency bag.
     */
    public int getMaxFrequency()
    {
        Node currentNode = firstNode;
        int maxFrequency = 0;
        for(int i = 0; i<numOfNodes;i++)
        {
            if(currentNode.frequency > maxFrequency)
            {
                maxFrequency = currentNode.frequency;
            }
            currentNode = currentNode.next;
        }
        return maxFrequency;
    }
    
    /**
     * Gets the probability of aData
     * @param aData the specific data to get its probability.
     * @return the probability of aData
     */
    public double getProbabilityOf(T aData)
    {
        double occurrences = (double) checkData(aData).frequency;
        return (double) (occurrences/numOfEntries);
    }

    /**
     * Empty this bag.
     */
    public void clear()
    {
        firstNode.next = null;
        firstNode.data = null;
        firstNode.frequency= 0;
        numOfNodes = 0;
        numOfEntries = 0;
        firstData = true;
    }
    
    /**
     * Gets the number of entries in this bag.
     * @return the number of entries in this bag.
     */
    public int size()
    {
        return numOfEntries;
    }
    public class Node
    {
        private T data;
        private int frequency;
        private Node next;
        
        private Node(T aData, Node nextNode, int aFrequency)
        {
            data = aData;
            next = nextNode;
            frequency = aFrequency;
        }
    }

}