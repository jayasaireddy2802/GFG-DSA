//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] edge = new int[m][3];
            for (int i = 0; i < m; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
                edge[i][2] = sc.nextInt();
            }
            Solution obj = new Solution();
            int res[] = obj.shortestPath(n, m, edge);
            for (int i = 0; i < n; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java
class Solution {
    
    public void dfs(int i, boolean[] visited, Stack<Integer> st, List<List<int[]>> adj)
    {
        visited[i] = true;
        
        for(int[] neighbour : adj.get(i))
        {
            if(!visited[neighbour[0]])
                dfs(neighbour[0], visited, st, adj);
        }
        
        st.add(i);
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        
        List<List<int[]>> adj = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];
        int[] dis = new int[V];
        Arrays.fill(dis, (int)1e8);
        
        for(int i = 0; i < V; i++)
        {
            adj.add(new ArrayList<>());
        }
        
        for(int i = 0; i < E; i++)
        {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            
            adj.get(u).add(new int[]{v, wt});
        }
        
        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
            {
                dfs(i, visited, st, adj);
            }
        }
        
        int src = 0;
        dis[src] = 0;
        while(!st.isEmpty())
        {
            int node = st.pop();
            int prevTillNodeDis = dis[node];
            
            for(int[] neighbour : adj.get(node))
            {
                int totalDis = prevTillNodeDis + neighbour[1];
                
                dis[neighbour[0]] = Math.min(dis[neighbour[0]], totalDis);
            }
        }
        
        for(int i = 0; i < V; i++)
        {
            if(dis[i] == (int)1e8)
                dis[i] = -1;
        }
        
        return dis;
    }
}


// 10
// 24
// 0 2 6
// 0 3 7
// 0 4 9
// 0 6 8
// 0 7 6
// 1 2 6
// 1 3 7
// 1 5 10
// 1 6 1
// 1 7 4
// 2 3 3
// 2 6 10
// 2 8 8
// 2 9 10
// 3 5 3
// 3 6 10
// 3 7 5
// 5 6 9
// 5 7 7
// 6 7 7
// 6 8 8
// 6 9 8
// 7 9 1
// 8 9 6
// Your Code's output is: 
// 100000000 0 6 7 100000000 10 1 4 9 5
// It's Correct output is: 
// 0 -1 6 7 9 10 8 6 14 7
















