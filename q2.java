class q1
{
    public static void main(String[] args)
    {
        int num = -1;
        byte b = (byte)num;
        int n = b;
        char c = (char)b, c1 = (char)n;
        int final_num = c;
        System.out.println("Initial integer: "+ num); // -1
        System.out.println("Byte: "+ b + " " + n + " " + c1); // -1
        System.out.println("Character: "+ c); // a rectangle char
        System.out.println("Final integer: "+ final_num); // 65535
    }
}