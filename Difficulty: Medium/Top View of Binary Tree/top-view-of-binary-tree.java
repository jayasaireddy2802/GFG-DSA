//{ Driver Code Starts
// Initial Template for JAVA

import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;


// } Driver Code Ends

// User function Template for Java

/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class Pair{
    Node node;
    int level;
    
    public Pair(Node node, int level)
    {
        this.node = node;
        this.level = level;
    }
}
class Solution {
    // Function to return a list of nodes visible from the top view
    // from left to right in Binary Tree.
    static int mini;
    static int maxi;
    
    static ArrayList<Integer> topView(Node root) {
        // code here
        maxi = 0;
        mini = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        
        if(root == null)
            return ans;
        
        queue.add(new Pair(root, 0));
        // map.put(0, root.val);
        
        while(!queue.isEmpty())
        {
            Pair pair = queue.poll();
            Node node = pair.node;
            int level = pair.level;
            
            if(!map.containsKey(level))
                map.put(level, node.data);
            
            if(node.left != null){
                queue.add(new Pair(node.left, level - 1));
                mini = Math.min(mini, level - 1);
            }
            if(node.right != null){
                queue.add(new Pair(node.right, level + 1));
                maxi = Math.max(maxi, level + 1);
            }
        }
        
       
            
        for(int key = mini; key <= maxi; key++)
        {
            ans.add(map.get(key));
        }
        
        return ans; 
    }
}


//{ Driver Code Starts.

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class Tree {

    static Node buildTree(String str) {

        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while (queue.size() > 0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= ip.length) break;

            currVal = ip[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    static void printInorder(Node root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.data + " ");

        printInorder(root.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            String s = br.readLine();
            Node root = buildTree(s);

            Solution ob = new Solution();

            ArrayList<Integer> vec = ob.topView(root);
            for (int x : vec) System.out.print(x + " ");
            System.out.println();

            t--;

            System.out.println("~");
        }
    }
}
// } Driver Code Ends