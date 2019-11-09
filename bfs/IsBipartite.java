package bfs;

import java.util.*;

public class IsBipartite {
	/*
	 * Leetcode785. Is Graph Bipartite?
	 * https://leetcode.com/problems/is-graph-bipartite/
	*/ 
	
	// 以下是laiOffer 版本
 static class GraphNode {
   public int key;
   public List<GraphNode> neighbors;
   public GraphNode(int key) {
   this.key = key;
      this.neighbors = new ArrayList<GraphNode>();
   }
 }
 
  public boolean isBipartite(List<GraphNode> graph) {
    Map<GraphNode, Integer> color = new HashMap<>();
    for (GraphNode node : graph) {
      if (!color.containsKey(node) && ! isBipartite(node, color)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean isBipartite(GraphNode node, Map<GraphNode, Integer> color) {
    Queue<GraphNode> queue = new ArrayDeque<>();
    queue.offer(node);
    node.key = 1; // use node.key to store prevColor

    while (!queue.isEmpty()) {
      GraphNode cur = queue.poll(); 
      int curColor = color.getOrDefault(cur, 0);
      if (curColor == 0) {
        curColor = -cur.key;
        color.put(cur, curColor);
        for (GraphNode nei : cur.neighbors) {
          nei.key = curColor; // 这句话可能有错!!!
          // 被generate时改变了 prevColor，多次被generate 进queue 可能会有冲突
          // 在queue中的node的prevColor也被改变了
          queue.offer(nei);
        }
      } else if (curColor == cur.key) {
        return false;
      }
    }
    return true;
  }
}

