.class public test18
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
.var 1 is vc$ Ltest18; from L0 to L1
	new test18
	dup
	invokenonvirtual test18/<init>()V
	astore_1
.var 2 is f [F from L0 to L1
	iconst_3
	newarray float
	dup
	iconst_0
	fconst_1
	fastore
	dup
	iconst_1
	fconst_2
	fastore
	dup
	iconst_2
	ldc 3.0
	fastore
	astore_2
	aload_2
	iconst_0
	invokestatic VC/lang/System/putFloatLn(F)V
	aload_2
	iconst_1
	invokestatic VC/lang/System/putFloatLn(F)V
	aload_2
	iconst_2
	invokestatic VC/lang/System/putFloatLn(F)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 22
.end method
