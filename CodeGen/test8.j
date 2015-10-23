.class public test8
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
.var 1 is vc$ Ltest8; from L0 to L1
	new test8
	dup
	invokenonvirtual test8/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	bipush 100
	istore_2
.var 3 is j I from L0 to L1
	iload_2
	istore_3
.var 4 is k I from L0 to L1
	iload_2
	iload_3
	iadd
	bipush 100
	isub
	istore 4
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	iload_3
	invokestatic VC/lang/System/putIntLn(I)V
	iload 4
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 5
.limit stack 28
.end method
