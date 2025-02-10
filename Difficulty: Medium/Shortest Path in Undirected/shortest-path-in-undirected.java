//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
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
                adj.get(v).add(u); // Since the graph is undirected
            }

            int src = sc.nextInt();

            Solution obj = new Solution();
            int[] res = obj.shortestPath(adj, src);

            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
            System.out.println("~");
        }
    }
}

// } Driver Code Ends



class Solution {
    // Function to find the shortest path from a source node to all other nodes
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here
        
        int v = adj.size();
        int[] dis = new int[v];
        Arrays.fill(dis, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        dis[src] = 0;
        queue.add(src);
        
        while(!queue.isEmpty())
        {
            int node = queue.poll();
            
            for(int neighbour : adj.get(node))
            {
                if(dis[neighbour] == -1)
                {
                    dis[neighbour] = dis[node] + 1;
                    queue.add(neighbour);
                }
            }
        }
        
        for(int i = 0; i < v; i++)
        {
            if(dis[i] == (int)1e8)
                dis[i] = -1;
        }
        
        return dis;
    }
}
 