//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            int vertices = Integer.parseInt(read.readLine());
            int edges = Integer.parseInt(read.readLine());

            for (int i = 0; i < vertices; i++) adj.add(i, new ArrayList<Integer>());

            int p = 0;
            for (int i = 1; i <= edges; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
            }

            ArrayList<Integer> res = new Solution().topologicalSort(adj);

            if (check(adj, vertices, res) == true)
                System.out.println("1");
            else
                System.out.println("0");
            System.out.println("~");
        }
    }

    static boolean check(ArrayList<ArrayList<Integer>> adj, int V,
                         ArrayList<Integer> res) {

        if (V != res.size()) return false;

        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res.get(i)] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}

// } Driver Code Ends


// class Solution {
//     // Function to return list containing vertices in Topological order.
    
//     static void dfs(int i, Stack<Integer> st, boolean[] visited, ArrayList<ArrayList<Integer>> adj)
//     {
//         visited[i] = true;
        
//         for(int neighbour : adj.get(i))
//         {
//             if(!visited[neighbour])
//             {
//                 dfs(neighbour, st, visited, adj);
//             }
//         }
        
//         st.push(i);
//     }
//     static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
//         // Your code here
//         int v = adj.size();
//         ArrayList<Integer> list = new ArrayList<>();
//         boolean[] visited = new boolean[v];
//         Stack<Integer> st = new Stack<>();
        
//         for(int i = 0; i < v; i++)
//         {
//             if(!visited[i])
//             {
//                 dfs(i, st, visited, adj);
                
//             }
//         }
         
//         while(!st.isEmpty())
//         {
//             list.add(st.pop());
//         }
        
//         return list;
//     }
// }

// Kahns algorithm
class Solution {
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        
        int V = adj.size();
        int[] indegre = new int[V];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        // indegree calculation
        for(int i = 0; i < V; i++)
        {
            for(int v : adj.get(i))
            {
                indegre[v]++;
            }
        }
        
        for(int i = 0; i < V; i++)
        {
            if(indegre[i] == 0){
                q.add(i);
                ans.add(i);
            }
        }
        
        while(!q.isEmpty())
        {
            int n = q.poll();
            
            for(int v : adj.get(n))
            {
                indegre[v]--;
                if(indegre[v] == 0)
                {
                    q.add(v);
                    ans.add(v);
                }
            }
        }
        
        return ans;
    }
}








