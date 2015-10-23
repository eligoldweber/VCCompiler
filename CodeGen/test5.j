.class public test5
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
.method f(II)V
L0:
.var 0 is this Ltest5; from L0 to L1
.var 1 is x I from L0 to L1
.var 2 is y I from L0 to L1
.var 3 is i1 I from L0 to L1
.var 4 is i2 I from L0 to L1
.var 5 is b [B from L0 to L1
	bipush 10
	newarray boolean
	astore
.var 6 is i3 I from L0 to L1
.var 7 is a [F from L0 to L1
	iconst_3
	newarray float
	astore
.var 8 is i4 I from L0 to L1
L2:
.var 9 is i4 I from L2 to L3
	bipush 44
	istore 9
	iload 9
	invokestatic VC/lang/System/putIntLn(I)V
L4:
.var 10 is i4 I from L4 to L5
	sipush 444
	istore 10
	iload 10
	invokestatic VC/lang/System/putIntLn(I)V
L5:
L3:
	iconst_1
	istore_3
	iconst_2
	istore 4
	iconst_3
	istore 6
	iload_3
	iload 4
	iload 6
	imul
	iadd
	istore 8
	iload_1
	invokestatic VC/lang/System/putIntLn(I)V
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	iload_3
	invokestatic VC/lang/System/putIntLn(I)V
	iload 4
	invokestatic VC/lang/System/putIntLn(I)V
	iload 6
	invokestatic VC/lang/System/putIntLn(I)V
	iload 8
	invokestatic VC/lang/System/putIntLn(I)V
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 11
.limit stack 142
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest5; from L0 to L1
	new test5
	dup
	invokenonvirtual test5/<init>()V
	astore_1
	aload_1
	iconst_1
	iconst_2
	invokevirtual test5/f(II)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 9
.end method
