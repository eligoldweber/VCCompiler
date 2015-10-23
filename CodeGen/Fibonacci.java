public class Fibonacci {
    public static int fib(int n) {
        if (n <= 1) return n;
        else return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        //int x = fib(100);
        for (int i = 0; i< 20; i++){
        System.out.println(fib(i));
    }
        
    }

}