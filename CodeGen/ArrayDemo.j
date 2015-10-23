Compiled from "ArrayDemo.java"
class ArrayDemo {
  ArrayDemo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: bipush        10
       2: newarray       double
       4: astore_1
       5: aload_1
       6: iconst_0
       7: ldc2_w        #2                  // double 100.1d
      10: dastore
      11: aload_1
      12: iconst_0
      13: daload
      14: ldc2_w        #4                  // double 1.4d
      17: dadd
      18: dstore_2
      19: return
}
