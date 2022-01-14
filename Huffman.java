import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Hashtable; // import the HashMap class
import java.util.PriorityQueue;

public class Huffman {

	public static void main(String[] args) throws IOException{

		Scanner input = new Scanner(System.in);
		System.out.print("Enter message/characters/words: ");
		String strmess = input.nextLine();
		PriorityQueue<Huffnode> pq = Stringtoheap(strmess);

		Dictionary<String,String> dictcode = new Hashtable<String,String>() ;
		Huffnode bigTree = treeCombiner(pq);
		traversal(bigTree,"",dictcode);
		
		String newmsg = "";
		for(int i=0;i<strmess.length();i++) {
			newmsg = newmsg + dictcode.get(strmess.substring(i,i+1));
		}
		System.out.println(dictcode);
		System.out.println("Encoded message:" + newmsg);
		input.close();
	}

	
	public static void traversal (Huffnode n, String s, Dictionary<String, String> d) { 
	//recursively traverses through the final tree, and adding 1's and 0's to the empty string in the dictionary until it reaches the bottom. 

    	if(n.character != null) {	
    		d.put(n.character, s);
    		return;
    	}

		traversal(n.left, s+"0", d);
		traversal(n.right, s+"1", d);
	}
	
	
	public static PriorityQueue<Huffnode> Stringtoheap (String s){
	//Takes in string and puts into a dictionary the key(character) and the frequency(elements). Then using the enumeration class, 
	//iterate through the dictionary and puts each key and element in a Huffnode, before putting the huffnodes into a priority queue.

		Hashtable<Character,Integer> dict = new Hashtable<Character,Integer>();
		for(int i=0;i<s.length();i++) {
			char k = s.charAt(i);
			if (dict.containsKey(k)){
				dict.put(k, dict.get(k)+ 1);
			}
			else {
				dict.put(k,1);
			}
		}
		PriorityQueue<Huffnode> queue = new PriorityQueue<Huffnode>();
		Enumeration<Character> numkeys = dict.keys();
		Enumeration<Integer> numfreq = dict.elements();

		while(numkeys.hasMoreElements()) {
			Huffnode q = new Huffnode(numfreq.nextElement(),numkeys.nextElement().toString());
			queue.add(q);
		}
		return queue;

	}

	public static Huffnode treeCombiner(PriorityQueue<Huffnode> q) {
	//takes the first two Huffnodes in the priorityQueue which is sorted by frequency, and combines them into a new Huffnode, with a root of null 
	//and the two original nodes as children, and then putting the new Huffnode back in, and repeating this process until there is only one Huffnode
	//left in the priority queue and returns that Huffnode.
		while(q.size()>1) {
			Huffnode n1 = q.poll();
			Huffnode n2 = q.poll();
			Huffnode nsum = new Huffnode(n1.frequency+n2.frequency,null);
			nsum.left=n1;
			nsum.right=n2;
			q.add(nsum);

		}
		return q.poll();
	}
	
}
	

	
	
	
