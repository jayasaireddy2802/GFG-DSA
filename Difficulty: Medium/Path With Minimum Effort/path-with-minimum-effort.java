//{ Driver Code Starts
import java.io.*;
import java.util.*;

class IntMatrix {
    public static int[][] input(BufferedReader br, int n, int m) throws IOException {
        int[][] mat = new int[n][];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().trim().split(" ");
            mat[i] = new int[s.length];
            for (int j = 0; j < s.length; j++) mat[i][j] = Integer.parseInt(s[j]);
        }

        return mat;
    }

    public static void print(int[][] m) {
        for (var a : m) {
            for (int e : a) System.out.print(e + " ");
            System.out.println();
        }
    }

    public static void print(ArrayList<ArrayList<Integer>> m) {
        for (var a : m) {
            for (int e : a) System.out.print(e + " ");
            System.out.println();
        }
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            int rows;
            rows = Integer.parseInt(br.readLine());

            int columns;
            columns = Integer.parseInt(br.readLine());

            int[][] heights = IntMatrix.input(br, rows, columns);

            Solution obj = new Solution();
            int res = obj.MinimumEffort(rows, columns, heights);

            System.out.println(res);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


// Using Pure DFS

// Runtime Error 
// Test Cases Passed: 
// 10 /1115
// class Solution {
//     static int res = Integer.MAX_VALUE;
//     static int[] dir_x = {-1, 0, 1, 0};
//     static int[] dir_y = {0, 1, 0, -1};
    
//     public static void dfs(int i, int j, int rows, int cols, int[][] heights, boolean[][] visited, int effort)
//     {
//         if(i == rows -1 && j == cols - 1)
//         {
//             res = Math.min(res, effort);
//             return ;
//         } 
        
//         visited[i][j] = true;
        
//         for(int dir = 0; dir < 4; dir++)
//         {
//             int new_x = i + dir_x[dir];
//             int new_y = j + dir_y[dir];
            
//             if(new_x >= 0 && new_x < rows && new_y >= 0 && new_y < cols && !visited[new_x][new_y])
//             {
//                 int  new_effort = Math.max(effort, Math.abs(heights[i][j] - heights[new_x][new_y]));
//                 dfs(new_x, new_y, rows, cols, heights, visited, new_effort);
//             }
//         }
        
//         visited[i][j] = false;
//     }
    
//     public static int MinimumEffort(int rows, int columns, int[][] heights) {
//         // code here
//         boolean[][] visited = new boolean[rows][columns];
//         visited[0][0] = true;
//         res = Integer.MAX_VALUE;
//         dfs(0, 0, rows, columns, heights, visited, 0);
//         return res;
//     }
// }

class Solution {
    static int res = Integer.MAX_VALUE;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    
    public static int MinimumEffort(int rows, int columns, int[][] heights) {
        // code here
        boolean[][] visited = new boolean[rows][columns];
        visited[0][0] = true;
        res = Integer.MAX_VALUE;
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        
        queue.add(new int[]{0, 0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty())
        {
            int[] arr = queue.poll();
            int x = arr[1];
            int y = arr[2];
            int effort = arr[0];
            visited[x][y] = true;
            if(x == rows - 1 && y == columns -1)
            {
                return effort;
            }
                
            for(int dir = 0; dir < 4; dir++)
            {
                int new_x = x + dir_x[dir];
                int new_y = y + dir_y[dir];
                
                if(new_x >= 0 && new_x < rows && new_y >= 0 && new_y < columns && !visited[new_x][new_y])
                {
                    int  new_effort = Math.max(effort, Math.abs(heights[x][y] - heights[new_x][new_y]));
                    queue.add(new int[]{new_effort, new_x, new_y});
                }
            }
        }
        
        return res;
    }
}
