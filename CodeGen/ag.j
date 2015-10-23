Compiled from "ag.java"
class ag {
  ag();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static boolean isFirstBigger(int[]);
    Code:
       0: iconst_1
       1: istore_1
       2: aload_0
       3: iconst_0
       4: iaload
       5: istore_2
       6: aload_0
       7: iconst_1
       8: iaload
       9: istore_3
      10: iload_2
      11: iload_3
      12: if_icmple     18
      15: goto          20
      18: iconst_0
      19: istore_1
      20: iload_1
      21: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: bipush        8
       2: newarray       int
       4: dup
       5: iconst_0
       6: iconst_5
       7: iastore
       8: dup
       9: iconst_1
      10: iconst_1
      11: iastore
      12: dup
      13: iconst_2
      14: iconst_2
      15: iastore
      16: dup
      17: iconst_3
      18: iconst_3
      19: iastore
      20: dup
      21: iconst_4
      22: iconst_4
      23: iastore
      24: dup
      25: iconst_5
      26: iconst_1
      27: iastore
      28: dup
      29: bipush        6
      31: iconst_2
      32: iastore
      33: dup
      34: bipush        7
      36: iconst_3
      37: iastore
      38: astore_1
      39: aload_1
      40: invokestatic  #2                  // Method isFirstBigger:([I)Z
      43: istore_2
      44: return
}
