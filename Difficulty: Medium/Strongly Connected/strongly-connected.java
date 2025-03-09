//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends

// User function Template for Java

class Solution {
    
    public void dfs(int node, boolean[] visited, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj)
    {
        visited[node] = true;
        
        for(int neighbour : adj.get(node))
        {
            if(!visited[neighbour])
                dfs(neighbour, visited, st, adj);
        }
        
        st.push(node);
    }
    
    public void dfs(int node, boolean[] visited, List<List<Integer>> list)
    {
        visited[node] = true;
        
        for(int neighbour : list.get(node))
        {
            if(!visited[neighbour])
                dfs(neighbour, visited, list);
        }
    }
    
    public void reverse(ArrayList<ArrayList<Integer>> adj, List<List<Integer>> list)
    {
        int V = adj.size();
        for(int i = 0; i < V; i++)
        {
            int u = i;
            for(int v : adj.get(i))
            {
                list.get(v).add(u);
            }
        }
    }
    
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int V = adj.size();
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        
        for(int i = 0; i < V; i++)
        {
            list.add(new ArrayList<>());
        }
        
        // traverse to get all edges as per finishing time
        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
            {
                dfs(i, visited, st, adj);
            }
        }
        
        // reverse edges
        reverse(adj, list);
        
        // dfs
        visited = new boolean[V];
        int ct = 0;
        while(!st.isEmpty())
        {
            int i = st.pop();
            
            if(!visited[i])
            {
                dfs(i, visited, list);
                ct++;
            }
        }
        
        return ct;
    }
}


//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
            }

            Solution obj = new Solution();
            System.out.println(obj.kosaraju(adj));

            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends