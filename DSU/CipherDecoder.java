import java.util.*;

class DSU {
    List<Integer> parent;
    List<Integer> rank;

    DSU(int n) {
        parent = new ArrayList<>(n + 1);
        rank = new ArrayList<>(n + 1);
        for (int i = 0; i < n; i++) {
            parent.add(i);
            rank.add(0);
        }
    }

    int findP(int node) {
        if (node != parent.get(node)) {
            parent.set(node, findP(parent.get(node))); // Path compression
        }
        return parent.get(node);
    }

    void unionByRank(int u, int v) {
        int ulp_u = findP(u);
        int ulp_v = findP(v);

        if (ulp_u == ulp_v) {
            return; // Already in the same component
        }

        if (ulp_u < ulp_v) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_u, ulp_v);
        }
    }
}

public class CipherDecoder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key1 = sc.next();
        String key2 = sc.next();
        String cipherText = sc.next();
        sc.close();

        DSU ds = new DSU(26); // DSU for 26 lowercase letters

        // Build equivalence classes
        for (int i = 0; i < key1.length(); i++) {
            ds.unionByRank(key1.charAt(i) - 'a', key2.charAt(i) - 'a');
        }

        // Find the smallest lexicographically equivalent character for each letter
        char[] smallestChar = new char[26];
        for (char c = 'a'; c <= 'z'; c++) {
            smallestChar[c - 'a'] = (char) (ds.findP(c - 'a') + 'a');
        }

        // Decode the cipherText
        // System.out.println(ds.parent);
        StringBuilder decodedMessage = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            decodedMessage.append(smallestChar[c - 'a']);
        }

        // Output the decoded message
        System.out.println(decodedMessage.toString());
    }
}
