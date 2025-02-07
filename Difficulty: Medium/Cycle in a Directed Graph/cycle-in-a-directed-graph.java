//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++) list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new Solution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

// class Solution {
//     // Function to detect cycle in a directed graph.
    
//     public boolean dfs(int i, boolean[] visited, boolean[] pathVisited, ArrayList<ArrayList<Integer>> adj)
//     {
//         visited[i] = true;
//         pathVisited[i] = true;
        
//         for(int neighbour : adj.get(i))
//         {
//             // self cycle
//             if(neighbour == i)
//                 return true; 
                
//             if(!visited[neighbour]){
//                 if(dfs(neighbour, visited, pathVisited, adj))
//                     return true;
//             }
//             else if(pathVisited[neighbour])
//                 return true;
                
//         }
        
//         pathVisited[i] = false;
//         return false;
//     }
    
//     public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
//         // code here
//         boolean[] visited = new boolean[V];
//         boolean[] pathVisited = new boolean[V];
        
//         for(int i = 0; i < V ; i++)
//         {
//             if(!visited[i])
//             {
//                 if(dfs(i, visited, pathVisited, adj))
//                     return true;
//             }
//         }
        
//         return false;
//     }
// }


class Solution {
    
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();
        int[] indegre = new int[n];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
        {
            for(int v : adj.get(i))
                indegre[v]++;
        }
        
        for(int i = 0; i < n; i++)
        {
            if(indegre[i] == 0)
            {
                q.add(i);
                ans.add(i);
            }
        }
        
        while(!q.isEmpty())
        {
            int node = q.poll();
            
            for(int v : adj.get(node))
            {
                indegre[v]--;
                if(indegre[v] == 0)
                {
                    q.add(v);
                    ans.add(v);
                }
            }
        }
        
        if(ans.size() == n)
            return false;
        else
            return true;
    }
}