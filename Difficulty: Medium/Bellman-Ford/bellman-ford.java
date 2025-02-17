//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class DriverClass {
    public static void main(String args[]) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            // ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            int[][] edges = new int[E][3];

            int i = 0;
            for (i = 0; i < E; i++) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                // ArrayList<Integer> t1 = new ArrayList<>();
                // t1.add(u);
                // t1.add(v);
                // t1.add(w);
                // edges.add(t1);
                edges[i][0] = u;
                edges[i][1] = v;
                edges[i][2] = w;
            }

            int S = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            int[] ptr = ob.bellmanFord(V, edges, S);

            for (i = 0; i < ptr.length; i++) System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


// User function Template for Java

/*   Function to implement Bellman Ford
 *   edges: 2D array which represents the graph
 *   src: source vertex
 *   V: number of vertices
 */
class Solution {
    static int[] bellmanFord(int V, int[][] edges, int src) {
        // Write your code here
        
        int e = edges.length;
        int dist[] = new int[V];
        
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;
        
        for(int j = 0; j < V; j++)
        {
            for(int i = 0; i < e; i++)
            {
                int u = edges[i][0];
                int v = edges[i][1];
                int cost = edges[i][2];
                
                if(dist[u] != (int)1e8 && dist[u] + cost < dist[v] )
                {
                    if(j == V-1)
                    {
                        int[] arr = new int[1];
                        arr[0] = -1;
                        return arr;
                    }
                    dist[v] = dist[u] + cost;
                }
            }
        }
        
        
        return dist;
    }
}
