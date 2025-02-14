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
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {

        // Your code here
        
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(new int[]{start, 0});
        int mod = 100000;
        while(!queue.isEmpty())
        {
            int[] arr1 = queue.poll();
            int val = arr1[0];
            int lvl = arr1[1];
            if(val == end)
                return lvl;
            for(int x : arr)
            {
                int new_val = (x*val)%mod;
                if(set.contains(new_val))
                    continue;
                set.add(new_val);
                queue.add(new int[]{new_val, lvl + 1});
            }
        }
        return -1;
    }
}
