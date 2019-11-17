package binarySearch;

import java.util.*;

public class SmallestRectangleEnclosingBlackPixels {
/*
	https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/solution
	https://app.laicode.io/app/problem/413
*/

  private static final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  public int minArea(int[][] image, int x, int y) {
    // Write your solution here

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{x, y});
    image[x][y] = 0;
    int left = y, right = y, top = x, bottom = x;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();

      for (int[] e : directions) {
        int row = cur[0] + e[0], col = cur[1] + e[1];
        if (validPos(row, col, image) && image[row][col] == 1) {
          queue.offer(new int[]{row, col});
          image[row][col] = 0;

          left = left <= col ? left : col;
          right = col <= right ?  right : col;
          top = top <= row ? top : row;
          bottom = row <= bottom ? bottom : row;
        }
      }
    }
    return (- top + bottom + 1) * (right - left + 1);
  }
  private boolean validPos(int row, int col, int[][] image) {
    return 0 <= row && row < image.length && 0 <= col && col < image[0].length;
  }
  /* method1.
    find most top, bottom, left, right index 
    left: col
    right: col
    top: row
    bottom: row

    bfs: for each new element cur
        left = left <= cur.col ? left : cur.col
        right = cur.col <= right ?  right : cur.col
        top = top <= cur.row ? top : cur.row
        bottom = cur.row <= bottom ? bottom : cur.row
    
    bfs mark visited during generation: in place -- change to 0
  */
  
  public static void main(String[] args) {
	  SmallestRectangleEnclosingBlackPixels sol = new SmallestRectangleEnclosingBlackPixels();
	  int[][] image = { 
			  {0,0,1,0},
			  {0,1,1,0},
			  {0,1,0,0}
	  };
	  System.out.print(sol.minArea(image, 0, 2));
  }
}