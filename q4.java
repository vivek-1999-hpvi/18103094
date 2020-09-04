public class q4 {
    
    public int findN(){
        long sum = 0;
        int i = 1;
        while(sum <= Integer.MAX_VALUE)
            sum += i++;
        
        return i;
    }
    public static void main(String[] args) {
        System.out.println(new q4().findN());
    }
    
}
