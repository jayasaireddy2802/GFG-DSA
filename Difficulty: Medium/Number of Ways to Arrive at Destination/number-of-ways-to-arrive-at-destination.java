//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
// Position this line where user code will be pasted.

class GFG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    int x = sc.nextInt();
                    temp.add(x);
                }
                adj.add(temp);
            }

            Solution obj = new Solution();
            System.out.println(obj.countPaths(n, adj));
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {

    static int countPaths(int n, List<List<Integer>> roads) {
        // Your code here
         int edges = roads.size();
        List<List<int[]>> adj = new ArrayList<>();
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> 
            x[0] == y[0] ? Long.compare(x[1], y[1]) : Long.compare(x[0], y[0]));
        long[] dist = new long[n];
        int[] ways = new int[n];
        int mod = (int)1e9 + 7;
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        ways[0] = 1;

        for(int i = 0; i < n; i++)
        {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < edges; i++)
        {
            int u = roads.get(i).get(0);
            int v = roads.get(i).get(1);
            int cost = roads.get(i).get(2);

            adj.get(u).add(new int[]{v, cost});
            adj.get(v).add(new int[]{u, cost});
        }

        pq.add(new long[]{0, 0});

        while(!pq.isEmpty())
        {
            long[] arr = pq.poll();
            long dis = arr[0];
            int node = (int)arr[1];

            if(dis > dist[node])
                 continue ;
            for(int[] adjNodes : adj.get(node))
            {
                int edgeWeight = adjNodes[1];
                int neighbour = adjNodes[0];

                if(dis + edgeWeight == dist[neighbour])
                {
                     ways[neighbour] = (ways[neighbour] + ways[node])%mod;
                }
                else if(dis + edgeWeight < dist[neighbour])
                {
                    dist[neighbour] = dis + edgeWeight;
                    pq.add(new long[]{dist[neighbour], neighbour});
                    ways[neighbour] = ways[node];
                }
            }
        }

        return ways[n-1];
    }
}
