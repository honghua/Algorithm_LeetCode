
package bfs;
import java.util.*;

public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<Integer> deadlocks = new HashSet<>();
        for (String deadend : deadends) {
        	if (deadend.equals("0000")) return -1;
            deadlocks.add(toNum(deadend));
        }
        
        int targetNum = toNum(target);
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        
        int level = 0;
        int origin = 0;
        queue.offer(origin);
        visited.add(origin);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : neighbors(cur, deadlocks, visited)) {
                    if (next == targetNum) return level;
                    
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return -1;
    }
    
    private List<Integer> neighbors(int cur, Set<Integer> deadlocks, Set<Integer> visited) {
        List<Integer> neighbors = new ArrayList<>();
        int[] digits = new int[4];
        for (int i = digits.length - 1; i >= 0; i--) {
        	digits[i] = cur %10;
        	cur /= 10;  
        }
        for (int i = 0; i < digits.length; i++) {
            int origin = digits[i];
            digits[i] = (origin + 1) % 10;
            int nei = toNum(digits);
            if (!deadlocks.contains(nei) && !visited.contains(nei)) {
                neighbors.add(nei);
            }
            
            digits[i] = (origin - 1 + 10) % 10;
            nei = toNum(digits);
            if (!deadlocks.contains(nei) && !visited.contains(nei)) {
                neighbors.add(nei);
            }
            
            digits[i] = origin;
        }
        
        return neighbors;
    }
    private int toNum(int[] digits) {
        int sum = 0;
        for (int e: digits) {
            sum = sum * 10 + e;
        }
        return sum;
    }
    private int toNum(String digits) {
        int sum = 0;
        for (char e: digits.toCharArray()) {
            sum = sum * 10 + (int)(e - '0');
        }
        return sum;
    }
    
    public static void main(String[] args) {
    	OpenTheLock sol = new OpenTheLock();
    	String[] deadends = new String[0];
    	String target = "2002";
    	System.out.print(sol.openLock(deadends, target));
    }
}