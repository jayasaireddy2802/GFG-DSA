//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String s[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                s = br.readLine().trim().split(" ");
                edges[i][0] = Integer.parseInt(s[0]);
                edges[i][1] = Integer.parseInt(s[1]);
                edges[i][2] = Integer.parseInt(s[2]);
            }

            List<Integer> list = new Solution().shortestPath(n, m, edges);

            ot.println(list.get(0));
            if (list.get(0) != -1 && !check(list, edges)) ot.println(-1);
        }
        ot.close();
    }

    static boolean check(List<Integer> list, int edges[][]) {
        Set<Integer> hs = new HashSet<>();
        Map<Integer, Map<Integer, Integer>> hm = new HashMap<>();
        for (int i = 1; i < list.size(); i++) hs.add(list.get(i));
        for (int x[] : edges) {
            if (hs.contains(x[0]) || hs.contains(x[1])) {
                if (!hm.containsKey(x[0])) hm.put(x[0], new HashMap<>());
                if (!hm.containsKey(x[1])) hm.put(x[1], new HashMap<>());
                hm.get(x[0]).put(x[1], x[2]);
                hm.get(x[1]).put(x[0], x[2]);
            }
        }
        int sum = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            if (!hm.containsKey(list.get(i)) ||
                !hm.get(list.get(i)).containsKey(list.get(i + 1)))
                return false;
            sum += hm.get(list.get(i)).get(list.get(i + 1));
        }
        return sum == list.get(0);
    }
}

// } Driver Code Ends


class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        //  Code Here.
        
        List<List<int[]>> adj = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        int[] parent = new int[n+1];
        int[] dist = new int[n+1];
        int src = 1;
        int dest = n;
        
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
            parent[i] = -1;
            dist[i] = (int)1e8;
        }
        
        for(int i = 0; i < m; i++)
        {
            adj.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
            adj.get(edges[i][1]).add(new int[]{edges[i][0], edges[i][2]});
        }
        
        pq.add(new int[]{0, src});
        dist[src] = 0;
        parent[src] = src;
        while(!pq.isEmpty())
        {
            int[] arr = pq.poll();
            int node = arr[1];
            int dis = arr[0];
            
            for(int[] adjNode : adj.get(node))
            {
                int neighbour = adjNode[0];
                int edgeWeight = adjNode[1];
                
                if(dis + edgeWeight < dist[neighbour])
                {
                    dist[neighbour] = dis + edgeWeight;
                    pq.add(new int[]{dist[neighbour], neighbour});
                    parent[neighbour] = node;
                }
            }
        }
        
     
            
        int node = dest;
        List<Integer> ans = new ArrayList<>();
        if (dist[dest] == (int) 1e8) return Arrays.asList(-1);
        while (node != src) { 
            ans.add(node);
            node = parent[node];
        }
        
        ans.add(src);
        ans.add(dist[dest]);
        Collections.reverse(ans);
        
        return ans;
        
    }
}

