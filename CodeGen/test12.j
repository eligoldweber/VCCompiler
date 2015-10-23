.class public test12
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
.var 1 is vc$ Ltest12; from L0 to L1
	new test12
	dup
	invokenonvirtual test12/<init>()V
	astore_1
	iconst_1
	iconst_2
	iconst_3
	imul
	iadd
	iconst_4
	iconst_2
	idiv
	iconst_1
	imul
	isub
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 15
.end method
