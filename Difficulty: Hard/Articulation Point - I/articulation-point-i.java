//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer>ans = obj.articulationPoints(V, adj);
            for (int i =0 ;i < ans.size (); i++) 
                System.out.print (ans.get (i) + " ");
            System.out.println();
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


class Solution
{
    
    int[] time;
    int[] lowTime;
    boolean[] visited;
    boolean[] parentChild;
    ArrayList<Integer> ans;
    Set<Integer> set;
    
    public void dfs(int node, int parent, int ct, ArrayList<ArrayList<Integer>> adj)
    {
        visited[node] = true;
        time[node] = ++ct;
        lowTime[node] = ct;
        int child = 0;
        
        for(int neighbour : adj.get(node))
        {
            if(neighbour == parent)
                continue ;
            
            if(!visited[neighbour])
            {
                dfs(neighbour, node, ct, adj);
                lowTime[node] = Math.min(lowTime[node], lowTime[neighbour]);
                
                if(lowTime[neighbour] >= time[node] && parent != -1)
                {
                    set.add(node);
                }
                child++;
            }
            
            else
            {
                lowTime[node] = Math.min(lowTime[node], time[neighbour]);
            }
            
        }
        
        if(parent == -1 && child > 1)
            set.add(node);
        
    }
    
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        time = new int[V];
        lowTime = new int[V];
        visited = new boolean[V];
        ans = new ArrayList<>();
        set = new HashSet<>();
        
        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
                dfs(i, -1, 1, adj);
        }
        
        for(int num : set)
            ans.add(num);
            
        if(ans.isEmpty())
            ans.add(-1);
        Collections.sort(ans);
            
        
        return ans;
    }
}