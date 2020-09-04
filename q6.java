import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class q6{
    
    public Integer[] hailstone(int n){
        if(n <= 0)
            throw new IllegalArgumentException("n should be a positive integer");
        
        ArrayList<Integer> list = new ArrayList<>();
        while(n != 1){
            list.add(n);
            n = (n%2 == 0)? n/2: n*3+1;
        }
        list.add(1);
        return list.toArray(new Integer[list.size()]);
    }
    public static void main(String[] args) {
        System.out.println("Programe to print Hailstone Sequence");
        System.out.print("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print("Hailstone sequence for " + n +": ");
        System.out.println(Arrays.toString((new q6()).hailstone(n)));
    }
    
}
