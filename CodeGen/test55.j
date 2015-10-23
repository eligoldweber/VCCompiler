.class public test55
.super java/lang/Object
	
	
	; standard class static initializer 
.method static <clinit>()V
	
	
	; set limits used by this method
.limit locals 0
.limit stack 0
	return
.end method
	
	; standard constructor initializer 
.method public <init>()V
.limit stack 1
.limit locals 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method
.method printIntArray(FI)V
L0:
.var 0 is this Ltest55; from L0 to L1
.var 1 is a [I from L0 to L1
.var 2 is n I from L0 to L1
.var 3 is i I from L0 to L1
	iconst_0
	istore_3
	goto L3
L2:
	iload_3
	iconst_1
	iadd
	istore_3
L3:
	iload_3
	iload_2
	if_icmplt L5
	iconst_0
	goto L6
L5:
	iconst_1
L6:
	ifeq L4
L7:
	aload_1
	iload_3
	invokestatic VC/lang/System.putInt(I)V
	ldc " "
	invokestatic VC/lang/System/putString(Ljava/lang/String;)V
L8:
	goto L2
L4:
	invokestatic VC/lang/System/putLn()V
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 43
.end method
.method bubbleSort(FI)V
L0:
.var 0 is this Ltest55; from L0 to L1
.var 1 is a [I from L0 to L1
.var 2 is n I from L0 to L1
.var 3 is lcv I from L0 to L1
.var 4 is limit I from L0 to L1
	iload_2
	iconst_1
	isub
	istore 4
.var 5 is temp I from L0 to L1
.var 6 is lastChange I from L0 to L1
L2:
	iload 4
	iconst_0
	if_icmpgt L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L3
L6:
	iconst_0
	istore 6
	iconst_0
	istore_3
	goto L9
L8:
	iload_3
	iconst_1
	iadd
	istore_3
L9:
	iload_3
	iload 4
	if_icmplt L11
	iconst_0
	goto L12
L11:
	iconst_1
L12:
	ifeq L10
	aload_1
	iload_3
	aload_1
	iload_3
	iconst_1
	iadd
	if_icmpgt L15
	iconst_0
	goto L16
L15:
	iconst_1
L16:
	ifeq L13
L17:
	aload_1
	iload_3
	iaload
	istore 5
	aload_1
	iload_3
	aload_1
	iload_3
	iconst_1
	iadd
	iaload
	iastore
	aload_1
	iload_3
	iconst_1
	iadd
	iload 5
	iastore
	iload_3
	istore 6
L18:
	goto L14
L13:
L14:
	goto L8
L10:
	iload 6
	istore 4
L7:
	goto L2
L3:
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 7
.limit stack 165
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest55; from L0 to L1
	new test55
	dup
	invokenonvirtual test55/<init>()V
	astore_1
.var 2 is x [I from L0 to L1
	bipush 10
	newarray int
	dup
	iconst_0
	iconst_3
	iastore
	dup
	iconst_1
	bipush 10
	iastore
	dup
	iconst_2
	iconst_1
	iastore
	dup
	iconst_3
	iconst_5
	iastore
	dup
	iconst_4
	bipush 8
	iastore
	dup
	iconst_5
	iconst_0
	iastore
	dup
	bipush 6
	bipush 20
	iastore
	dup
	bipush 7
	iconst_1
	iastore
	dup
	bipush 8
	iconst_4
	iastore
	dup
	bipush 9
	bipush 100
	iastore
	astore_2
.var 3 is hmny I from L0 to L1
	bipush 10
	istore_3
.var 4 is who I from L0 to L1
.var 5 is where I from L0 to L1
	ldc "The array was:"
	invokestatic VC/lang/System/putStringLn(Ljava/lang/String;)V
	aload_1
	iload_2
	iload_3
	invokevirtual test55/printIntArray(FI)V
	aload_1
	iload_2
	iload_3
	invokevirtual test55/bubbleSort(FI)V
	ldc "The sorted array is:"
	invokestatic VC/lang/System/putStringLn(Ljava/lang/String;)V
	aload_1
	iload_2
	iload_3
	invokevirtual test55/printIntArray(FI)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 6
.limit stack 54
.end method
