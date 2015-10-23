Compiled from "Fibonacci.java"
public class Fibonacci {
  public Fibonacci();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static int fib(int);
    Code:
       0: iload_0
       1: iconst_1
       2: if_icmpgt     7
       5: iload_0
       6: ireturn
       7: iload_0
       8: iconst_1
       9: isub
      10: invokestatic  #2                  // Method fib:(I)I
      13: iload_0
      14: iconst_2
      15: isub
      16: invokestatic  #2                  // Method fib:(I)I
      19: iadd
      20: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: iconst_0
       1: istore_1
       2: iload_1
       3: bipush        20
       5: if_icmpge     24
       8: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      11: iload_1
      12: invokestatic  #2                  // Method fib:(I)I
      15: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      18: iinc          1, 1
      21: goto          2
      24: return
}
