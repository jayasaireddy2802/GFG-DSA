//{ Driver Code Starts


import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int V = Integer.parseInt(br.readLine().trim());
            int E = Integer.parseInt(br.readLine().trim());
            List<List<int[]>> list = new ArrayList<>();
            for (int i = 0; i < V; i++) list.add(new ArrayList<>());
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                list.get(a).add(new int[] {b, c});
                list.get(b).add(new int[] {a, c});
            }
            ot.println(new Solution().spanningTree(V, E, list));

            ot.println("~");
        }
        ot.close();
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Code Here.
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        int sum = 0;
        pq.add(new int[]{0, 0, -1});
        visited[0] = true;
        
        while(!pq.isEmpty())
        {
            int[] arr = pq.poll();
            int wt = arr[0];
            int node = arr[1];
            int parent = arr[2];
            
            if(!visited[node] && parent != -1)
            {
                sum = sum + wt;
            }
            visited[node] = true;
            
            for(int[] adjNodes : adj.get(node))
            {
                int neighbour = adjNodes[0];
                int edgewt = adjNodes[1];
                
                if(!visited[neighbour])
                {
                    pq.add(new int[]{edgewt, neighbour, node});
                }
            }
        }
        
        return sum;
    }
}