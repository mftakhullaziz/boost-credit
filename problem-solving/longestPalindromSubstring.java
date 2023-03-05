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
        String result = longestPalindrome("babad");
        System.out.println(result);
    }

    /**
     * Longest Palindrome SubString
     * Time Complexity : O(n^2)
     * @param word
     * @return
     */
    public static String longestPalindrome(String word) {
        String maxStr = "";
        int maxLength = 0;
        for(int i=0; i<word.length(); i++){
            for(int j=i; j<word.length(); j++){
                if(isPalindrome(word.substring(i, j+1))){
                    if((j+1-i) > maxLength){
                        maxLength = j+1-i;
                        maxStr = word.substring(i, j+1);
                    }
                }
            }
        }
        return maxStr;
    }
    
    /**
     * Check Palindrome 
     * @param word
     * @return
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return s.equals(sb.toString());
    }
    

}