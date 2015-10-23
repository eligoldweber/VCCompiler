Compiled from "ab.java"
class ab {
  ab();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_0
       1: istore_1
       2: iconst_0
       3: istore_2
       4: iload_1
       5: ifne          22
       8: iload_2
       9: iconst_1
      10: iadd
      11: istore_2
      12: iload_2
      13: iconst_5
      14: if_icmpne     4
      17: iconst_1
      18: istore_1
      19: goto          4
      22: return
}
