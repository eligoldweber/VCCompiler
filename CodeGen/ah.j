Compiled from "ah.java"
class ah {
  ah();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static int fact(int);
    Code:
       0: iload_0
       1: ifne          6
       4: iconst_1
       5: ireturn
       6: iload_0
       7: iload_0
       8: iconst_1
       9: isub
      10: invokestatic  #2                  // Method fact:(I)I
      13: imul
      14: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: iconst_4
       1: invokestatic  #2                  // Method fact:(I)I
       4: istore_1
       5: return
}
