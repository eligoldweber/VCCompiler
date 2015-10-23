Compiled from "neg.java"
class neg {
  neg();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public float temp();
    Code:
       0: ldc           #2                  // float -5.1f
       2: fstore_1
       3: fload_1
       4: fneg
       5: freturn

  public static void main(java.lang.String[]);
    Code:
       0: iconst_5
       1: istore_2
       2: bipush        -42
       4: istore_1
       5: iload_1
       6: iload_2
       7: iadd
       8: istore_3
       9: iload_3
      10: bipush        -42
      12: if_icmpne     17
      15: iconst_0
      16: istore_3
      17: return
}
