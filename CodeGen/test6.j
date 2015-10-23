.class public test6
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
.var 1 is vc$ Ltest6; from L0 to L1
	new test6
	dup
	invokenonvirtual test6/<init>()V
	astore_1
.var 2 is i1 I from L0 to L1
.var 3 is i2 I from L0 to L1
.var 4 is i3 I from L0 to L1
.var 5 is i4 I from L0 to L1
	iconst_1
	istore 5
	istore 4
	istore_3
	istore_2
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	iload_3
	invokestatic VC/lang/System/putIntLn(I)V
	iload 4
	invokestatic VC/lang/System/putIntLn(I)V
	iload 5
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 6
.limit stack 66
.end method
