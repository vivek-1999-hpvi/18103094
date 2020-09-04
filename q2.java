import java.util.Arrays;
public class q2 {
    
    public void sort(int[] arr){
        int[] count = new int[21];
        
        for(int i: arr)
            count[i]++;
        
        int lo = 0;
        for(int i=0; i<21; ++i){
            int temp = lo;
            while(lo < temp+count[i])
                arr[lo++] = i;
        }
        
    }
    public static void main(String[] args) {
        int[] arr = {4,2,5,6,1,2,9,0,2,10};
        (new q2()).sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
}
