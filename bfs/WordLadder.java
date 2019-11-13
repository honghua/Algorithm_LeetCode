package bfs;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        
        Set<String> visited = new HashSet<>(); // mark visit
        Queue<String> queue = new ArrayDeque<>(); // queue for BFS
        int level = 0; // level 
        
        queue.offer(beginWord);
        visited.add(beginWord); // mark visited at generation
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) { // expand at each level
                String cur = queue.poll();
                
                for (String next : getNextWords(cur, wordSet, visited)) {
                    if (next.equals(endWord)) { return level; }
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        
        return -1;
    }
    private List<String> getNextWords(String cur, Set<String> wordSet, Set<String> visited) {
        List<String> nextWords = new ArrayList<>();
        
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < 26; j++) {
                chars[i] = (char)('a' + j);
                if (chars[i] == cur.charAt(i)) continue;
                String next = new String(chars);
                if (wordSet.contains(next) && !visited.contains(next)) {
                    nextWords.add(next);
                }
            }
            chars[i] = cur.charAt(i);
        }
        System.out.println(nextWords);
        return nextWords;
    }
    
    public static void main(String[] args) {
    	WordLadder sol = new WordLadder();
    	String beginWord = "hit";
    	String endWord = "cog";
    	List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
    	System.out.print(sol.ladderLength(beginWord, endWord, wordList));
    }
}