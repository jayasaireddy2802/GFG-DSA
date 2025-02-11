//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class comp implements Comparator<ArrayList<String>> {
    // override the compare() method
    public int compare(ArrayList<String> a, ArrayList<String> b) {
        String x = "";
        String y = "";
        for (int i = 0; i < a.size(); i++) x += a.get(i);
        for (int i = 0; i < b.size(); i++) y += b.get(i);
        return x.compareTo(y);
    }
}

public class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] wordList = new String[n];
            for (int i = 0; i < n; i++) {
                wordList[i] = br.readLine().trim();
            }
            String startWord, targetWord;
            startWord = br.readLine().trim();
            targetWord = br.readLine().trim();
            Solution obj = new Solution();
            ArrayList<ArrayList<String>> ans =
                obj.findSequences(startWord, targetWord, wordList);
            if (ans.size() == 0)
                System.out.println(-1);
            else {
                Collections.sort(ans, new comp());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < ans.size(); i++) {
                    for (int j = 0; j < ans.get(i).size(); j++) {
                        sb.append(ans.get(i).get(j) + " ");
                    }
                    if (i != ans.size() - 1) sb.append("\n");
                }
                System.out.println(sb);
            }
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public ArrayList<ArrayList<String>> findSequences(String startWord,
                                                      String targetWord,
                                                      String[] wordList) {
        // Code here
        
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        Queue<ArrayList<String>> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        Set<String> usedWords = new HashSet<>();
        boolean found = false;
        
        for(String str : wordList)
            set.add(str);
            
        ArrayList<String> list = new ArrayList<>();
        list.add(startWord);
        queue.add(list);
        set.remove(startWord);
        
        while(!queue.isEmpty() && !found)
        {
            int size = queue.size();
            usedWords.clear();
            
            for(int i = 0; i < size; i++)
            {
                list = queue.poll();
                String prevStr = list.get(list.size() - 1);
                
                if(prevStr.equals(targetWord))
                {
                    ans.add(new ArrayList<>(list));
                    found = true;
                    
                }
                
                char[] arr = prevStr.toCharArray();
                for(int j = 0; j < prevStr.length(); j++)
                {
                    char prevChar = arr[j];
                    for(char ch = 'a'; ch <= 'z'; ch++)
                    {
                        arr[j] = ch;
                        String newWord = new String(arr);
                        
                        if(set.contains(newWord))
                        {
                            usedWords.add(newWord);
                            ArrayList<String> newList = new ArrayList<>(list);
                            newList.add(newWord);
                            queue.add(newList);
                        }
                    }
                    arr[j] = prevChar;
                }
            }
            
            for(String str : usedWords)
            {
                set.remove(str);
            }
        }
        
        return ans;
    }
}