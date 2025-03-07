//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


// } Driver Code Ends

//User function Template for Java


class DisjointSet
{
    int[] rank;
    int[] parent;
    
    public DisjointSet(int V)
    {
        rank = new int[V];
        parent = new int[V];
        
        for(int i = 0;i < V; i++)
        {
            parent[i] = i;
        }
    }
    
    public int findParent(int x)
    {
        if(parent[x] == x)
            return x;
        return parent[x] = findParent(parent[x]);
    }
    
    public void findURank(int u, int v)
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
        else if(rank[parent_u] == rank[parent_v])
        {
            parent[parent_v] = parent_u;
            rank[parent_u]++;
        }
    }
    
}
class Solution {
    
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        //Your code here
        int[] dir_x = {-1, 0, 1, 0};
        int[] dir_y = {0, 1, 0, -1};
        boolean[][] visited = new boolean[rows][cols];
        DisjointSet ds = new DisjointSet(rows*cols);
        int ct = 0;
        List<Integer> list = new ArrayList<>();
        
        for(int[] operator : operators)
        {
            int i = operator[0];
            int j = operator[1];
            
            if(visited[i][j])
            {
                list.add(ct);
                continue;
            }
            
            ct++;
            visited[i][j] = true;
            for(int dir = 0; dir < 4; dir++)
            {
                int x = i + dir_x[dir];
                int y = j + dir_y[dir];
                
                if(x >= 0 && x < rows && y >= 0 && y < cols && visited[x][y])
                {
                    if(ds.findParent(i*cols + j) != ds.findParent(x*cols + y))
                    {
                        ds.findURank(i*cols + j, x*cols + y);
                        ct--;
                    }
                }
            }
            list.add(ct);
        }
        
        return list;
    }
    
}


//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int  k= sc.nextInt();
            int[][] a = new int[k][2];
            for(int i=0;i<k;i++){
            
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
            }
            
            Solution obj = new Solution();
            List<Integer> ans = obj.numOfIslands(n,m,a);
           
            for(int i:ans){
                System.out.print(i+" ");
            }
            System.out.println();
        
System.out.println("~");
}
    }
}

// } Driver Code Ends