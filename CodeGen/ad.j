Compiled from "ad.java"
class ad {
  ad();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: bipush        10
       2: newarray       int
       4: astore_1
       5: iconst_0
       6: istore_2
       7: iload_2
       8: bipush        11
      10: if_icmpge     24
      13: aload_1
      14: iload_2
      15: iload_2
      16: iastore
      17: iload_2
      18: iconst_1
      19: iadd
      20: istore_2
      21: goto          7
      24: return
}
