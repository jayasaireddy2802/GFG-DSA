//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String[] wordList = new String[n];
            for(int i = 0; i < n; i++){
                wordList[i] = br.readLine().trim();
            }
            String startWord, targetWord;
            startWord = br.readLine().trim();
            targetWord = br.readLine().trim();
            Solution obj = new Solution();
            int ans = obj.wordLadderLength(startWord, targetWord, wordList);
            System.out.println(ans);
       
System.out.println("~");
}
    }
}

// } Driver Code Ends

class Pair
{
    int level;
    String str;
    
    public Pair(String str, int lvl)
    {
        this.level = lvl;
        this.str = str;
    }
}


class Solution
{
    public int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        // Code here
        int n = wordList.length;
        Set<String> set = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        
        for(int i = 0; i < n; i++)
            set.add(wordList[i]);
        
        queue.add(new Pair(startWord, 1));
        set.remove(startWord);
        
        while(!queue.isEmpty())
        {
            Pair p = queue.poll();
            String str = p.str;
            int lvl = p.level;
            int len = str.length();
            
            if(str.equals(targetWord))
                return lvl;
                
            for(int i = 0; i < len; i++)
            {
                char[] arr = str.toCharArray();
                
                for(char ch = 'a'; ch <= 'z'; ch++)
                {
                    arr[i] = ch;
                    String newStr = new String(arr);
                    if(set.contains(newStr))
                    {
                        queue.add(new Pair(newStr, lvl + 1));
                        set.remove(newStr);
                    }
                }
            }
        }
        
        return 0;
    }
}










