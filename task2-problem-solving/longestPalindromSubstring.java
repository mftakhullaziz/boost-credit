/**
 * How to run :
 * Cd to directory
 * Run : javac  longestPalindromSubstring.java
 * And after that
 * Run : java longestPalindromSubstring
 * / 

/**
 * longestPalindromSubstring
 */
public class longestPalindromSubstring {

    public static void main(String[] args) {
        String result = longestPalindrome("BABad");
        System.out.println(result);
    }

    /**
     * Longest Palindrome SubString
     * Time Complexity : O(n^2)
     * @param word
     * @return
     */
    public static String longestPalindrome(String s) {
        if(s.length() < 2) return s.toLowerCase();
        String res = "";
        for(int i = 0;i < s.length(); i++) {
            String odd = expandFromMiddle(s.toLowerCase(),i,i);
            if(odd.length() > res.length()) res = odd;
            String even = expandFromMiddle(s.toLowerCase(),i,i+1);
            if(even.length() > res.length()) res = even;            
        }
        return res;
    }

    /**
     * @param s
     * @param left
     * @param right
     * @return
     */
    public static String expandFromMiddle(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

}
