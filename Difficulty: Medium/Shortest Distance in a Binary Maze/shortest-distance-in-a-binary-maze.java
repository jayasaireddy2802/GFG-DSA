//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[] source = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                source[i] = x;
            }
            int[] dest = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                dest[i] = x;
            }
            Solution ob = new Solution();
            int ans = ob.shortestPath(grid, source, dest);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


// User function Template for Java

// class Solution {
//     int[] dir_x = {-1, 0, 1, 0};
//     int[] dir_y = {0, 1, 0, -1};
    
//      int dfs(int[][] grid, boolean[][] visited, int i, int j, int x, int y, int rows, int cols)
//     {
//         if(i == x && j == y)
//             return 0;
//         visited[i][j] = true;
        
//         int ans = (int) 1e7;
//         for(int dir = 0; dir < 4; dir++)
//         {
//             int new_x = i + dir_x[dir];
//             int new_y = j + dir_y[dir];
            
//             if((new_x >= 0 && new_x < rows && new_y >= 0 && new_y < cols) && (!visited[new_x][new_y] && grid[new_x][new_y] == 1))
//             {
//                 ans = Math.min(ans, 1 + dfs(grid, visited, new_x, new_y, x, y, rows, cols));
//             }
//         }
        
//         visited[i][j] = false;
//         return ans;
        
//     }
    
//     int shortestPath(int[][] grid, int[] source, int[] destination) {

//         // Your code here
        
//         int rows = grid.length;
//         int cols = grid[0].length;
//         boolean[][] visited = new boolean[rows][cols];
//         int ans = -1;;
//         if(grid[source[0]][source[1]] == 1)
//              ans = dfs(grid, visited, source[0], source[1], destination[0], destination[1], rows, cols);
//         if(ans == (int)1e7)
//             return -1;
//         else
//             return ans;
//     }
// }


class Solution {
    int[] dir_x = {-1, 0, 1, 0};
    int[] dir_y = {0, 1, 0, -1};
    
    int shortestPath(int[][] grid, int[] source, int[] destination) {

        // Your code here
        
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        
        if(grid[source[0]][source[1]] == 0 || grid[destination[0]][destination[1]] == 0)
            return -1;
        if(Arrays.equals(source, destination))
            return 0;
            
        queue.add(new int[]{source[0], source[1], 0});
        visited[source[0]][source[1]] = true;
        
        while(!queue.isEmpty())
        {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            int lvl = arr[2];
            
            if(x == destination[0] && y == destination[1])
                return lvl;
                
            for(int dir = 0; dir < 4; dir++)
            {
                int new_x = x + dir_x[dir];
                int new_y = y + dir_y[dir];
                
                if((new_x >= 0 && new_x < rows && new_y >= 0 && new_y < cols) && (!visited[new_x][new_y] && grid[new_x][new_y] == 1))
                {
                    queue.add(new int[]{new_x, new_y, lvl + 1});
                    visited[new_x][new_y] = true;
                }
            }
                
        }
        
        return -1;
    }
}

