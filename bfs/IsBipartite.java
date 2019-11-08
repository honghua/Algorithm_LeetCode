package bfs;

import java.util.*;

public class IsBipartite {
	/*
	 * Leetcode785. Is Graph Bipartite?
	 * https://leetcode.com/problems/is-graph-bipartite/
	*/ 
	
	public boolean isBipartite(int[][] graph) {
        Map<Integer, Integer> color = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!color.containsKey(i) && !isBipartite(i, color, graph)) return false;
            // not visited  && Not a Bipartite ==> return false
        }
        return true;
    }
    private boolean isBipartite(Integer node, Map<Integer, Integer> color, int[][] graph) {
        Queue<Integer> queue = new ArrayDeque<>();
        color.put(node, -1); // mark visited at generation
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curColor = color.get(cur);
            
            for (int nei : graph[cur]) {
                int neiColor = color.getOrDefault(nei, 0);
                if (neiColor == 0) {
                    color.put(nei, -curColor); // mark visited at generation
                    queue.offer(nei);
                } else if (neiColor == curColor) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	int[][] graph = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
    	IsBipartite sol = new IsBipartite();
    	System.out.println(sol.isBipartite(graph));
    }
}

