// Imagine you are designing a grand castle where each room holds a specific amount 
// of treasure. The castle is built in a binary layout, meaning every room may lead 
// to two adjacent wings—a left wing and a right wing. 

// An "organized section" of the castle follows this rule: for any given room, 
// every room in its left wing contains a treasure value that is strictly less 
// than the current room’s value, and every room in its right wing contains a 
// value that is strictly greater. Additionally, each wing must itself be organized
// according to the same rule.

// Your challenge is to determine the maximum total treasure (i.e., the sum of 
// treasure values) that can be gathered from any such organized section of the castle.

// Example 1:
// input=
// 1 4 3 2 4 2 5 -1 -1 -1 -1 -1 -1 4 6
// output=
// 20

// Castle:
//           1
//         /   \
//        4     3
//       / \   / \
//      2   4 2   5
//               / \
//              4   6

// Explanation: The best organized section starts at the room with a treasure value 
// of 3, yielding a total treasure of 20.

// Example 2:
// input=
// 4 3 -1 1 2
// output=
// 2

// Castle:
//     4
//    /
//   3
//  / \
// 1   2

// Explanation: The optimal organized section is just the single room with a 
// treasure value of 2.

// Example 3:
// input=
// -4 -2 -5
// output=
// 0

// Castle:
//    -4
//   /  \
// -2   -5
 
// Explanation: Since all rooms contain negative treasure values, no beneficial 
// organized section exists, so the maximum total treasure is 0.

// Constraints:

// - The number of rooms in the castle ranges from 1 to 40,000.
// - Each room’s treasure value is an integer between -40,000 and 40,000.
import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class p1 {
    static int maxSumBST = 0;  // Global variable to track max BST sum

    public static Node buildTree(int[] arr) {
        if (arr.length == 0) return null;

        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(arr[0]);
        queue.add(root);
        int i = 1;

        while (i < arr.length) {
            Node node = queue.poll();
            if (node == null) continue;

            if (i < arr.length) {
                if (arr[i] != -1) {
                    node.left = new Node(arr[i]);
                    queue.add(node.left);
                }
                i++;
            }

            if (i < arr.length) {
                if (arr[i] != -1) {
                    node.right = new Node(arr[i]);
                    queue.add(node.right);
                }
                i++;
            }
        }
        return root;
    }

    public static int[] findMaxSumBST(Node root) {
        if (root == null) {
            return new int[]{1, 0, Integer.MIN_VALUE, Integer.MAX_VALUE}; // {isBST, sum, maxVal, minVal}
        }

        int[] left = findMaxSumBST(root.left);
        int[] right = findMaxSumBST(root.right);

        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[3]) {
            int sum = left[1] + right[1] + root.val;
            maxSumBST = Math.max(maxSumBST, sum);
            return new int[]{1, sum, Math.max(root.val, right[2]), Math.min(root.val, left[3])};
        }

        return new int[]{0, 0, 0, 0};  // Not a BST
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] sa = sc.nextLine().split(" ");
        int[] arr = new int[sa.length];

        for (int i = 0; i < sa.length; i++) {
            arr[i] = Integer.parseInt(sa[i]);
        }

        Node root = buildTree(arr);
        findMaxSumBST(root);
        System.out.println(maxSumBST);
    }
}

