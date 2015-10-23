Compiled from "ac.java"
class ac {
  ac();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_0
       1: istore_1
       2: iload_1
       3: iconst_4
       4: if_icmpgt     22
       7: iload_1
       8: iconst_1
       9: iadd
      10: istore_1
      11: iload_1
      12: iconst_5
      13: if_icmpne     2
      16: bipush        6
      18: istore_1
      19: goto          2
      22: return
}
