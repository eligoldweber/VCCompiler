.class public test4
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
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest4; from L0 to L1
	new test4
	dup
	invokenonvirtual test4/<init>()V
	astore_1
.var 2 is i1 I from L0 to L1
	iconst_1
	istore_2
.var 3 is i2 I from L0 to L1
	iconst_2
	istore_3
.var 4 is a3 [I from L0 to L1
	iconst_2
	newarray int
	dup
	iconst_0
	iconst_3
	iastore
	dup
	iconst_1
	iconst_4
	iastore
	astore 4
	nop
.var 5 is i4 I from L0 to L1
	iload_2
	iload_3
	aload 4
	iconst_0
	iaload
	imul
	iadd
	aload 4
	iconst_1
	iaload
	iadd
	istore 5
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	iload_3
	invokestatic VC/lang/System/putIntLn(I)V
	aload 4
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
	aload 4
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	iload 5
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 6
.limit stack 58
.end method
