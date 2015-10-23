Compiled from "ae.java"
class ae {
  ae();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  int fact(int);
    Code:
       0: iload_1
       1: ifne          6
       4: iconst_1
       5: ireturn
       6: iload_1
       7: aload_0
       8: iload_1
       9: iconst_1
      10: isub
      11: invokevirtual #2                  // Method fact:(I)I
      14: imul
      15: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: return
}
