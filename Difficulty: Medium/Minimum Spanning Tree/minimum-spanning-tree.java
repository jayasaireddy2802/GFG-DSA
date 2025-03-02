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

// class Solution {
//     static int spanningTree(int V, int E, List<List<int[]>> adj) {
//         // Code Here.
//         boolean[] visited = new boolean[V];
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
//         int sum = 0;
//         pq.add(new int[]{0, 0, -1});
//         visited[0] = true;
        
//         while(!pq.isEmpty())
//         {
//             int[] arr = pq.poll();
//             int wt = arr[0];
//             int node = arr[1];
//             int parent = arr[2];
            
//             if(!visited[node] && parent != -1)
//             {
//                 sum = sum + wt;
//             }
//             visited[node] = true;
            
//             for(int[] adjNodes : adj.get(node))
//             {
//                 int neighbour = adjNodes[0];
//                 int edgewt = adjNodes[1];
                
//                 if(!visited[neighbour])
//                 {
//                     pq.add(new int[]{edgewt, neighbour, node});
//                 }
//             }
//         }
        
//         return sum;
//     }
// }



class Solution {
    static List<List<Integer>> edges;
    static int[] rank;
    static int[] parent;
    
    static int findParent(int x)
    {
        if(parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]);
    }
    
    static void findURank(int u, int v)
    {
        int parent_u = findParent(u);
        int parent_v = findParent(v);
        if(parent_u == parent_v)
            return ;
        if(rank[parent_u] > rank[parent_v])
        {
            parent[parent_v] = parent_u;
        }
        else if(rank[parent_u] < rank[parent_v])
        {
            parent[parent_u] = parent_v;
        }
        else
        {
            parent[parent_v] = parent_u;
        }
        
        
    }
    
    
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Code Here.
        edges = new ArrayList<>();
        rank = new int[V];
        parent = new int[V];
        
        for(int i = 0; i < V; i++)
        {
            parent[i] = i;
        }
        
        for(int i = 0; i < V; i++)
        {
            for(int[] arr : adj.get(i))
            {
                List l = new ArrayList<>();
                l.add(arr[1]);
                l.add(i);
                l.add(arr[0]);
                edges.add(l);
            }
        }
        Collections.sort(edges, (a, b) -> a.get(0) - b.get(0));
        
        int cost = 0;
        for(List<Integer> edge : edges)
        {
            int wt = edge.get(0);
            int u = edge.get(1);
            int v = edge.get(2);
            
            if(findParent(u) != findParent(v))
            {
                cost = cost + wt;
                findURank(u, v);
            }
        }
        
        return cost;
        
        
    }
}