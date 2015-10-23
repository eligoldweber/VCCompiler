Compiled from "aa.java"
class aa {
  aa();
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
       4: iload_2
       5: bipush        11
       7: if_icmpge     31
      10: iload_1
      11: iload_2
      12: iadd
      13: istore_1
      14: iload_2
      15: iconst_2
      16: if_icmpne     22
      19: goto          31
      22: iconst_3
      23: istore_2
      24: iload_2
      25: iconst_1
      26: iadd
      27: istore_2
      28: goto          4
      31: return
}
