package bfs;
import java.util.*;

public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] result = s.toCharArray();
        
        // step1. buildGraph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> pair : pairs) {
            int one = pair.get(0);
            int two = pair.get(1);
            graph.putIfAbsent(one, new ArrayList<>());
            graph.putIfAbsent(two, new ArrayList<>());
            graph.get(one).add(two);
            graph.get(two).add(one);
        }
        
        // step2. traversal graph DFS + mark visited
        boolean[] visited = new boolean[s.length()];
        for (int i = 0; i < visited.length; i++) {
            List<Character> chars = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();
            
            DFS(s, i, graph, visited, chars, indices);
            // chars.sort();
             // indices.sort();
            Collections.sort(chars);
            Collections.sort(indices);
            for (int j = 0; j < indices.size(); j++) {
                result[indices.get(j)] = chars.get(j);
            }
        }
        return new String(result);
    }
    
    private void DFS(String s, int id, Map<Integer, List<Integer>> graph, boolean[] visited, List<Character> chars, List<Integer> indices) {
        if (visited[id]) return;
        visited[id] = true;
        indices.add(id);
        chars.add(s.charAt(id));
        for (int nei : graph.get(id)) {
            DFS(s, nei, graph, visited, chars, indices);
        }
    }
    
    public static void main(String[] args) {
    	List<Character> list = new ArrayList<>();
    	list.add('a');
    	list.add('c');
    }
}