package bfs;

import java.util.*;

public class SlidingPuzzle {
	private static final int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public int sliding(int[][] initPattern, int[][] finalPattern) {
		Queue<int[][]> queue = new ArrayDeque<>();
		Set<int[][]> visited = new HashSet<>();
		
		queue.offer(initPattern);
		visited.add(initPattern);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) {
				int[][] cur = queue.poll();
				for (int[][] nei : neighbors(cur, visited)) {
					if (isSamePattern(nei, finalPattern)) {
						return level;
					}
					queue.offer(nei);
					visited.add(nei);
				}
			}
		}
		return -1;
	}
	
	private List<int[][]> neighbors(int[][] cur, Set<int[][]> visited) {
		List<int[][]> neighbors =  new ArrayList<>();
		
		int[] pos = findZero(cur);
		for (int[] e : directions) {
			int row = pos[0] + e[0], col = pos[1] + e[1];
			if (0 <= row && row < cur.length && 0 <= col && col < cur[0].length) {
				int[] newPos = new int[] {row, col};
				int[][] nei = swapNeighbor(cur, pos, newPos);
				if (!visited.contains(nei)) {
					neighbors.add(nei);
				}
			}
		}
		
		return neighbors;
	}
	
	private int[] findZero(int[][] cur) {
		for (int i = 0; i < cur.length; i++) {
			for (int j = 0; j < cur[0].length; j++) {
				if (cur[i][j] == 0) return new int[] {i, j};
			}
		}
		return new int[] {-1, -1};
	}
	
	private int[][] swapNeighbor(int[][] cur, int[] pos, int[] newPos) {
		int[][] copy = new int[cur.length][cur[0].length];
		for (int i = 0; i < cur.length; i++) {
			for (int j = 0; j < cur.length; j++) {
				copy[i][j] = cur[i][j];
			}
		}
		int tmp = copy[pos[0]][pos[1]];
		copy[pos[0]][pos[1]] = copy[newPos[0]][newPos[1]];
		copy[newPos[0]][newPos[1]] = tmp;
		return copy;
	}
	
	private boolean isSamePattern(int[][] cur, int[][] finalPattern) {
		for (int i = 0; i < cur.length; i++) {
			for (int j = 0; j < cur[0].length; j++) {
				if (cur[i][j] != finalPattern[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Set<List<Integer>> set = new HashSet<>();
		List<Integer> a = new ArrayList<>();
		a.add(1);
		List<Integer> b = new ArrayList<>();
		b.add(1);
		set.add(a);
		set.add(b);
		System.out.println(set);
		
	}
	
}
