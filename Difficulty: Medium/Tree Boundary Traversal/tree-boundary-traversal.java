//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

class GFG {
    static Node buildTree(String str) {
        // Corner Case
        if (str.length() == 0 || str.equals('N')) return null;
        String[] s = str.split(" ");

        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        // Starting from the second element
        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            // Get and remove the front of the queue
            Node currNode = q.remove();

            // Get the current node's value from the string
            String currVal = s[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= s.length) break;
            currVal = s[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.right);
            }

            i++;
        }

        return root;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t > 0) {
            String s = br.readLine();
            Node root = buildTree(s);

            Solution T = new Solution();

            ArrayList<Integer> res = T.boundaryTraversal(root);
            for (Integer num : res) System.out.print(num + " ");
            System.out.println();
            t--;

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java
/*
class Node
{
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    ArrayList<Integer> ans;
    
    private boolean isLeaf(Node node)
    {
        return (node.left == null && node.right == null);
    }
    
    public void leftBoundary(Node node)
    {
        Node curr = node.left;
        
        while(curr != null)
        {
            if(!isLeaf(curr))
                ans.add(curr.data);
            if(curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }
    
    public void leafBoundary(Node node)
    {
        if(node == null)
            return ;
        
        if(isLeaf(node))
            ans.add(node.data);
        leafBoundary(node.left);
        leafBoundary(node.right);
    }
    
    public void rightBoundary(Node node)
    {
        Node curr = node.right;
        ArrayList<Integer> list = new ArrayList<>();
        
        while(curr != null)
        {
            if(!isLeaf(curr))
                list.add(curr.data);
            if(curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }
        
        int size = list.size();
        for(int i = size - 1; i >= 0; i--)
        {
            ans.add(list.get(i));
        }
    }
    
    ArrayList<Integer> boundaryTraversal(Node node) {
        // code here
        ans = new ArrayList<>();
        if(node == null)
            return ans;
            
        if(!isLeaf(node))
            ans.add(node.data);
        leftBoundary(node);
        leafBoundary(node);
        rightBoundary(node);
        
        return ans;
    }
}
