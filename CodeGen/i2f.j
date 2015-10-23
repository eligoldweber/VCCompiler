Compiled from "i2f.java"
class i2f {
  i2f();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_3
       1: istore_1
       2: ldc           #2                  // float 3.2f
       4: fstore_2
       5: iload_1
       6: i2f
       7: fload_2
       8: fadd
       9: fstore_3
      10: return
}
