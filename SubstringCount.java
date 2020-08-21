/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.util.Scanner;

/**
 *
 * @author Suhel Naryal
 */

public class SubstringCount {
    
    private boolean isCountZero(int[] chars){
        for(int i: chars)
            if(i != 0)
                return false;
        
        return true;
    }
    
    public int substringCount(String str, String subStr){
        if(str == null || str.length() < 1 || subStr == null || subStr.length() < 1)
            return 0;
        
        if(str.length() < subStr.length())
            return 0;
        
        int[] strWinCC = new int[26];
        int winSize = subStr.length(), count = 0;
   
        for(char c: subStr.toCharArray())
            strWinCC[c - 'a']++;
        for(char c: str.substring(0, winSize).toCharArray())
            strWinCC[c - 'a']--;
        
        count += (isCountZero(strWinCC))? 1: 0;
        
        for(int i=winSize; i<str.length(); ++i){
            
            strWinCC[str.charAt(i) - 'a']--;
            
            strWinCC[str.charAt(i-winSize) - 'a']++;
            
            count += (isCountZero(strWinCC))? 1: 0;
        }
        
        return count;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.println("Program to count the sub-string in a string if the order of characters doesn't matter\n");
        System.out.println("Enter string(only lowercase alphabets[a-z]):");
        String str = sc.nextLine();
        System.out.println("Enter sub-string:");
        String substr = sc.nextLine();
        
        System.out.print("Count: ");
        System.out.println((new SubstringCount()).substringCount(str.toLowerCase(), substr.toLowerCase()));
        
    }
    
}
