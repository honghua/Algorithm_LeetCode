package bfs;

import java.util.*;

public class SlidingPuzzle {
	static class Pattern {
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pattern other = (Pattern) obj;
			return Arrays.deepEquals(pattern, other.pattern);
		}

		int[][] pattern;
		public Pattern(int[][] pattern) {
			this.pattern = pattern;
		}		
	}
	
	private static final int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public int sliding(int[][] initPattern, int[][] finalPattern) {
		Queue<int[][]> queue = new ArrayDeque<>(); // queue for bfs
		Set<Pattern> visited = new HashSet<>(); // set for mark visit
		
		queue.offer(initPattern);
		visited.add(new Pattern(initPattern));
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) { // expand at current level
				int[][] cur = queue.poll();
				for (int[][] nei : neighbors(cur, visited)) {
//					if (isSamePattern(nei, finalPattern)) {
//						return level;
//					}
					if (Arrays.deepEquals(nei, finalPattern)) {
						return level;
					}
					queue.offer(nei);
					visited.add(new Pattern(nei));
				}
			}
		}
		return -1;
	}
	
	private List<int[][]> neighbors(int[][] cur, Set<Pattern> visited) {
		List<int[][]> neighbors =  new ArrayList<>();
		
		int[] zeorPos = findZero(cur);
		for (int[] e : directions) {
			int row = zeorPos[0] + e[0], col = zeorPos[1] + e[1];
			if (0 <= row && row < cur.length && 0 <= col && col < cur[0].length) {
				int[] newPos = new int[] {row, col};
				int[][] nei = swapNeighbor(cur, zeorPos, newPos);
				if (!visited.contains(new Pattern(nei))) {
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
			for (int j = 0; j < cur[0].length; j++) {
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
		int[][] initPattern = new int[][] {
			{4,1,2},
			{5,0,3}
		};
//		initPattern = new int[][] {
//			{1,2,3},
//			{4,0,5}
//		};
		initPattern = new int[][] {
			{1,2,3},
			{5,4,0}
		};
		int[][] finalPattern = new int[][]{
            {1,2,3},
            {4,5,0}
        };
        
        SlidingPuzzle sol = new SlidingPuzzle();
        System.out.println(sol.sliding(initPattern, finalPattern));
        
        boolean check = (new SlidingPuzzle.Pattern(initPattern)).equals((new SlidingPuzzle.Pattern(initPattern)));
//        check = sol.isSamePattern(initPattern, finalPattern);
        System.out.println(check);
	}
	
}
