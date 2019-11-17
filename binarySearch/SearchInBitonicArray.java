package binarySearch;

public class SearchInBitonicArray {
  public int search(int[] array, int target) {
    // Write your solution here
    if (array.length == 0) return -1;
    if (array.length == 1) return array[0] == target ? 0 : -1;

    int left = 1, right = array.length - 1; // 这里要特别注意，left start at 1!!!
    // int maxIndex = peakElement(array);

    int maxIndex = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] - array[mid - 1] == 0) {
        maxIndex = mid - 1;
        break;
      } else if (array[mid] - array[mid - 1] > 0) {
        maxIndex = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    int candidate = binarySearch(array, 0, maxIndex, target, true);
    if (candidate != -1) return candidate;

    return binarySearch(array, maxIndex + 1, array.length - 1, target, false);
  }
  //  ascending or descending
  // if ascending sign = +1
  // if descending sign = -1;
  private int binarySearch(int[] array, int left, int right, int target, boolean ascending) {
    int sign = ascending ? 1 : -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (sign * (array[mid] - target) < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

//  private int peakElementIndex(int[] array) {
//    int left = 0, right = array.length - 1;
//    int candidate = 0;
//    while (left <= right) {
//      int mid = left + (right - left) / 2;
//      // if (array[mid - 1] == array[mid]) return mid - 1;
//      if (array[mid - 1] <= array[mid]) {
//        candidate = mid;
//        left = mid + 1;
//      } else {
//        right = mid - 1;
//      }
//    }
//    return candidate;
//  }
  
  public static void main(String[] args) {
	  SearchInBitonicArray sol = new SearchInBitonicArray();
	  int[] array = new int[] {9,5,3,2,-4,-5};
	  int target = 6;
	  System.out.print(sol.search(array, target));
	  
  }
}