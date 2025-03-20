// public Imagine you're a digital security analyst reviewing a suspicious email. The email’s 
// content is a continuous string of characters, and you have a list of keywords 
// commonly used in phishing scams. Your mission is to scan the email text and flag 
// every segment that exactly matches one of these keywords. In other words, identify 
// all index pairs [i, j] such that the substring from position i to j in the email 
// text is one of the suspicious keywords. Return these pairs sorted by their starting 
// index, and if two pairs share the same starting index, sort them by their ending index.

// Input Format
// ------------
// Line-1: string STR(without any space)
// Line-2: space separated strings, suspicious keywords[]

// Output Format
// -------------
// Print the pairs[i, j] in sorted order.


// Example 1:
// ----------
// Input:  
// cybersecuritybreachalert
// breach alert cyber

// Output: 
// 0 4
// 13 18
// 19 23

// Example 2:
// ----------
// Input:  
// phishphishingphish
// phish phishing

// Output:
// 0 4
// 5 9
// 5 12
// 13 17


// Explanation: Notice that keywords  overlap—for instance, the word "phish" appears 
// as part of the substring [5,9] in addition to the complete "phishing" substring [5,12].

// Constraints:

// - 1 <= emailText.length <= 100  
// - 1 <= suspiciousWords.length <= 20  
// - 1 <= suspiciousWords[i].length <= 50  
// - emailText and each suspicious word consist of lowercase English letters.  
// - All suspicious words are unique.






import java.util.*;

class TrieNode
{
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
 
    public TrieNode(){
        isEnd = false;
        for(int i=0;i<26;i++) children[i]=null;
    }
}

public class Trie{
    static TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    
    static void insert(String word)
    {
        TrieNode node = root;
    
        for(int i=0;i<word.length();i++)
        {
            int ind = word.charAt(i)-'a';
            if(node.children[ind]==null)
            {
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }
        node.isEnd = true;
        
    }
    
    static void search(String word){
        for(int i=0;i<word.length();i++)
        {
            TrieNode node = root;
            for(int j=i;j<word.length();j++){
                int ind = word.charAt(j)-'a';
                if(node.children[ind]==null) break;
                node = node.children[ind];
                if(node.isEnd) System.out.println(i+" "+j);
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String p[] = sc.nextLine().split(" ");
        Trie trie = new Trie();
        int ind=0;
        for(int i=0;i<p.length;i++)
        {
            trie.insert(p[i]);
        }
        
       search(s);
    }
} {
    
}
