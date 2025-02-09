//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends
// User function Template for Java
class Solution {
    
    public List<Integer> topoSort(List<List<Integer>> adj, Set<Integer> set)
    {
        List<Integer> ans = new ArrayList<>();
        int[] indegre = new int[26];
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i : set)
        {
            for(int neighbour : adj.get(i))
            {
                indegre[neighbour]++;
            }
        }
        
        for(int i : set)
        {
            if(indegre[i] == 0)
            {
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty())
        {
            int node = queue.poll();
            ans.add(node);
            
            for(int neighbour : adj.get(node) )
            {
                indegre[neighbour]--;
                if(indegre[neighbour] == 0)
                {
                    queue.add(neighbour);
                }
            }
            
        }
        
        if(ans.size() != set.size())
            return new ArrayList<>();
         
        return ans;
    }
    
    public String findOrder(String[] words) {
        // code here
        int l = words.length;
        List<List<Integer>> adj = new ArrayList<>();
        Set<Integer> set = new HashSet<>(); 
        
        for(int i = 0; i < 26; i++)
        {
            adj.add(new ArrayList<>());
        }
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                set.add(c - 'a');
            }
        }
        
        for(int i = 0; i < l-1; i++)
        {
            String s1 = words[i];
            String s2 = words[i+1];
            int l1 = s1.length();
            int l2 = s2.length();
            int len = Math.min(l1, l2);
            int j = 0;
            // If no difference was found and s1 is longer than s2, it's an invalid order
            boolean found = false;
            
            while(j < len)
            {
                if(s1.charAt(j) != s2.charAt(j))
                {
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    found = true;
                    break;
                }
                j++;
            }
            
            if(!found && l1 > l2)
                return "";
            
        }
        
        List<Integer> ans = topoSort(adj, set);
        
        String result = "";
        for(int ch : ans)
            result = result + (char)(ch + 'a');
            
        return result;
    }
}

//{ Driver Code Starts.

public class GFG {
    private static boolean validate(String[] original, String order) {
        Map<Character, Integer> mp = new HashMap<>();
        for (String word : original) {
            for (char ch : word.toCharArray()) {
                mp.put(ch, 1);
            }
        }
        for (char ch : order.toCharArray()) {
            if (!mp.containsKey(ch)) {
                return false;
            }
            mp.remove(ch);
        }
        if (!mp.isEmpty()) {
            return false;
        }

        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            indexMap.put(order.charAt(i), i);
        }

        for (int i = 0; i < original.length - 1; i++) {
            String a = original[i];
            String b = original[i + 1];
            int k = 0, n = a.length(), m = b.length();

            while (k < n && k < m && a.charAt(k) == b.charAt(k)) {
                k++;
            }

            if (k < n && k < m &&
                indexMap.get(a.charAt(k)) > indexMap.get(b.charAt(k))) {
                return false;
            }
            if (k != n && k == m) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine()); // Number of test cases

        while (t-- > 0) {
            String[] words = sc.nextLine().split(" ");
            String[] original = Arrays.copyOf(words, words.length);

            Solution ob = new Solution();
            String order = ob.findOrder(words);

            if (order.isEmpty()) {
                System.out.println("\"\"");
            } else {
                System.out.println(validate(original, order) ? "true" : "false");
            }
            System.out.println("~");
        }

        sc.close();
    }
}

// } Driver Code Ends