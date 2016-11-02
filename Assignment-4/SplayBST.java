//-----------------------------------------------------------
//---- Ket-Meng Cheng ~ Assignment #4 ~ 2 November, 2016 ---- 
//---- ================================================= ----
//----    This program generates an arraylist of item    ----
//---- Word, which contains the frequencies and the word ----
//---- itself. From this, the program generates another  ----
//---- array that has the number of words the frequency  ----
//---- refers to. From this, the program enters the      ----
//---- items of the array into the Splay Tree after      ----
//---- shuffling the items of the array. Then it does    ----
//---- the same into a Red-Black BST.                    ----
//----     Afterwards, the program runs get() on a       ----
//---- mass of randomly selected words in respect to     ----
//---- proportionality and displays the number of key    ----
//---- comparisons made.                                 ----
//---- ================================================= ----
//----   Credits: -Sedgewick for Splay and RB-BST        ----
//----            -Dylan Porter for peer-reviewing code  ----
//-----------------------------------------------------------

import java.util.*;
import java.io.*;

public class SplayBST<Key extends Comparable<Key>, Value>  {

    static int comparisons = 0;	

    private Node root;   // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;            // key
        private Value value;        // associated data
        private Node left, right;   // left and right subtrees

        public Node(Key key, Value value) {
            this.key   = key;
            this.value = value;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with the given key
    // if no such value, return null
    public Value get(Key key) {
	comparisons=0;
        root = splay(root, key);
        int cmp = key.compareTo(root.key);
	comparisons++;
        if (cmp == 0) {
	       return root.value;
	}
        else return null;
    }    

   /***************************************************************************
    *  Splay tree insertion.
    ***************************************************************************/
    public void put(Key key, Value value) {
        // splay key to root
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        // Insert new node at root
        if (cmp < 0) {
            Node n = new Node(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        else if (cmp > 0) {
            Node n = new Node(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        // It was a duplicate key. Simply replace the value
        else {
            root.value = value;
        }

    }
    
   /***************************************************************************
    * Splay tree function.
    * **********************************************************************/
    // splay key in the tree rooted at Node h. If a node with that key exists,
    //   it is splayed to the root of the tree. If it does not, the last node
    //   along the search path for the key is splayed to the root.
    private Node splay(Node h, Key key) {
	comparisons++;
        if (h == null) return null;

        int cmp1 = key.compareTo(h.key);
	comparisons++;

        if (cmp1 < 0) {
            // key not in tree, so we're done
            if (h.left == null) {
		comparisons++;
                return h;
            }
            int cmp2 = key.compareTo(h.left.key);
	    comparisons++;
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null) {
		    comparisons++;
                    h.left = rotateLeft(h.left);
		}
            }
            
            if (h.left == null) {
		  comparisons++; 
		  return h;
	    }
            else {
		  return rotateRight(h);
	    }
        }

        else if (cmp1 > 0) { 
            // key not in tree, so we're done
            if (h.right == null) {
		comparisons++;
                return h;
            }

            int cmp2 = key.compareTo(h.right.key);
	    comparisons++;
            if (cmp2 < 0) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null) {
		    comparisons++;
                    h.right = rotateRight(h.right);
		}
            }
            else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            
            if (h.right == null) {
		   comparisons++;
	           return h;
	    }
            else return rotateLeft(h);
        }

        else return h;
    }


   /***************************************************************************
    *  Helper functions.
    ***************************************************************************/

    // height of tree (1-node tree has height 0)
    public int height() { return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    
    public int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }
    
    // right rotate
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    // left rotate
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    //find root
    private Key findroot() {
	//root = splay(root, null);
    	return root.key;
    }

    // test client
    public static void main(String[] args) {
	int count = 0;

	try {
		String[] wordArray = new String[268729919];
        	SplayBST<String, Integer> dictsplay = new SplayBST<>();
		RBST<String, Integer> dictrb = new RBST<>();
		//String[] tempArray = new String[268729919];
		String word;

		ArrayList<Word<String, Integer>> dict = new ArrayList<Word<String, Integer>>();
		Scanner input = new Scanner(new FileReader("freqs.txt"));

		while(input.hasNextLine()) {
			word = input.nextLine();
			String[] spaced = word.split("[ ]+");
			dict.add(new Word<String,Integer>(spaced[0], Integer.parseInt(spaced[1])));
		}

		//----------------------------------------
		//-Populates the frequency array
		//----------------------------------------
		for (int i = 0; i < dict.size(); i++) {
			for(int j = 0; j < dict.get(i).frequency(); j++) {
				wordArray[count] = dict.get(i).word();
				count++;
			}
		}

		System.out.println(count);
		Collections.shuffle(Arrays.asList(wordArray));			//Randomizes the array.
		System.out.println();

	      	//------------------------------------------
             	//-Populates the splay tree
		//------------------------------------------
		for(int i = 0; i < wordArray.length; i++) {
			dictsplay.put(wordArray[i], 1);
		}
		System.out.println(dictsplay.findroot());

		//-------------------------------------------
		//-Populates the frequency
		//-------------------------------------------
		for(int i = 0; i < wordArray.length; i++) {
			dictrb.put(wordArray[i], 1);
		}

		//------------------------------------------
		//-Testing Efficiency
		//------------------------------------------
 		Random rand = new Random();	
  		String[] testArray = new String[10000000];
  		for(int i = 0; i < 100000; i++) {
   			testArray[i] = wordArray[rand.nextInt(268729919)];
			dictsplay.get(testArray[i]);
			dictrb.get(testArray[i]);
			System.out.println("Splay: " + testArray[i] + " " + comparisons);
			System.out.println("RBST: " + testArray[i] + " " + dictrb.comparisons);
			System.out.println();
		}
		
		//-------------------------------------------
		//-Testing EFficiency 2
		//-------------------------------------------
 		


 	} catch (IOException e) {
 		System.out.println("Error Found: " + e);
 		System.exit(0);
	} 
    }
}
