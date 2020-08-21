import java.util.Scanner;
class ques4{
	static boolean areAnagram(String str1, String str2) {
		if (str1.length()!=str2.length()) {
			return false;
		}
		int value=0;
		for (int i=0;i<str1.length();i++) {
			value=value^(int)str1.charAt(i);
			value=value^(int)str2.charAt(i);
			}
		return value==0;	
	}
	public static void main(String[] args) {
		String s1,s2;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter string 1:");
		s1=sc.nextLine();
		System.out.println("Enter string 2:");
		s2=sc.nextLine();
		boolean answer=areAnagram(s1,s2);
		if(answer==true)
			System.out.println("Strings are anagrams");
		else
			System.out.println("Strings are not anagrams");
	}
}
