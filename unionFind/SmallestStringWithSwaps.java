package unionFind;
import java.util.*;

public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind uf = new UnionFind(s.length());
        
        for (List<Integer> e : pairs) {
            uf.union(e.get(0), e.get(1));
        }
        
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int a = 0; a < s.length(); a++) {
            int aBoss = uf.find(a);
            List<Integer> group = groups.getOrDefault(aBoss, new ArrayList<>());
            group.add(a);
            groups.put(aBoss, group);
        }        
        
        // step3. for each group: sort, put char in corresponding position
        char[] result = new char[s.length()];
        char[] charArray = s.toCharArray();
        
        for (Map.Entry<Integer,List<Integer>> set : groups.entrySet()) {
            List<Integer> group = set.getValue(); // 语法！！！ Map.Entry<K,V>
            
            char[] chars = new char[group.size()];
            int t = 0;
            for (int e : group) {
                chars[t++] = charArray[e];
            }
            Arrays.sort(chars);
            
            for (int i = 0; i < group.size(); i++) {
                int index = group.get(i);
                result[index] = chars[i];
            }
        }
        
        return new String(result);
    }
    
    static class UnionFind {
        int[] id;
        int[] size;
        
        public UnionFind(int n) {
            id = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }
        
        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
        
        public void union(int a, int b) {
            int aBoss = find(a);
            int bBoss = find(b);
            if (aBoss == bBoss) return;
            
            if (size[aBoss] <= size[bBoss]) {
                id[aBoss] = bBoss;
                size[bBoss] += size[aBoss];
            } else {
                id[bBoss] = aBoss;
                size[aBoss] += size[bBoss];
            }
        }
        
        public int find(int a) {
            int aBoss = a;
            while (id[aBoss] != aBoss) {
                aBoss = id[aBoss];
            }
            
            int exBoss = id[a];
            while (exBoss != aBoss) {
                a = id[exBoss];
                id[exBoss] = aBoss;
                exBoss = a;
            }
                
            return aBoss;
        }
    }
}