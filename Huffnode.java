
public class Huffnode  implements Comparable<Huffnode>{
	//Create Huffman tree class, stores frequency and character
    Integer frequency;
    String character;
    Huffnode left;
    Huffnode right;
    
    Huffnode(int i,String c){
    	frequency = i;
    	character = c;
    	left = null;
    	right = null;
    }
    
	   
    //Learned how to sort Priorityqueues with the provided link in the assignment: https://howtodoinjava.com/java/collections/java-priorityqueue/
    
    @Override
    public int compareTo(Huffnode emp) {
        return this.frequency.compareTo(emp.frequency);
    }

}
