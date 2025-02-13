//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class DriverClass {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<iPair>> adj = new ArrayList<ArrayList<iPair>>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<iPair>());
            }

            int i = 0;
            while (i++ < E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                iPair t1 = new iPair(v, w);
                iPair t2 = new iPair(u, w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }

            int src = Integer.parseInt(read.readLine());

            Solution ob = new Solution();

            ArrayList<Integer> res = ob.dijkstra(adj, src);

            for (i = 0; i < V; i++) System.out.print(res.get(i) + " ");
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


/*
class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
    
     public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            iPair other = (iPair) obj;
            return first == other.first && second == other.second;
        }
}
*/

// User function Template for Java
// class Solution {
//     // Function to find the shortest distance of all the vertices
//     // from the source vertex src.
//     ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
//         // Write your code here
//         int V = adj.size();
//         int[] dist = new int[V];
//         PriorityQueue<iPair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
//         ArrayList<Integer> ans = new ArrayList<Integer>();
//         Arrays.fill(dist, (int)1e7);
//         dist[src] = 0;
        
//         pq.add(new iPair(0, src));
        
//         while(!pq.isEmpty())
//         {
//             iPair p = pq.poll();
//             int dis = p.first;
//             int node = p.second;
            
//             for(iPair neighbours : adj.get(node))
//             {
//                 int neighbour = neighbours.first;
//                 int edgeWeight = neighbours.second;
                
//                 if(dis + edgeWeight < dist[neighbour])
//                 {
//                     dist[neighbour] = dis + edgeWeight;
//                     pq.add(new iPair(dis + edgeWeight, neighbour));
//                 }
//             }
//         }
        
//         for(int dis : dist)
//             ans.add(dis);
//         return ans;
//     }
// }


class Solution {
    // Function to find the shortest distance of all the vertices
    // from the source vertex src.
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        // Write your code here
        int V = adj.size();
        int[] dist = new int[V];
        
        TreeSet<iPair> set = new TreeSet<>((x, y) -> x.first == y.first ? x.second - y.second : x.first - y.first);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Arrays.fill(dist, (int)1e7);
        dist[src] = 0;
        
        set.add(new iPair(0, src));
        
        while(!set.isEmpty())
        {
            iPair p = set.pollFirst();
            int dis = p.first;
            int node = p.second;
            
            for(iPair neighbours : adj.get(node))
            {
                int neighbour = neighbours.first;
                int edgeWeight = neighbours.second;
                
                if(dis + edgeWeight < dist[neighbour])
                {
                    set.remove(new iPair(dist[neighbour], neighbour));
                    dist[neighbour] = dis + edgeWeight;
                    set.add(new iPair(dis + edgeWeight, neighbour));
                }
            }
        }
        
        for(int dis : dist)
            ans.add(dis);
        return ans;
    }
}