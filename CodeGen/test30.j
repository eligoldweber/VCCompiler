.class public test30
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
.method f(F)V
L0:
.var 0 is this Ltest30; from L0 to L1
.var 1 is a [F from L0 to L1
.var 2 is tmp F from L0 to L1
	aload_1
	iconst_1
	faload
	fstore_2
	aload_1
	iconst_1
	aload_1
	iconst_0
	faload
	fastore
	aload_1
	iconst_0
	fload_2
	fastore
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 29
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest30; from L0 to L1
	new test30
	dup
	invokenonvirtual test30/<init>()V
	astore_1
.var 2 is a [F from L0 to L1
	iconst_2
	newarray float
	dup
	iconst_0
	fconst_1
	fastore
	dup
	iconst_1
	fconst_2
	fastore
	astore_2
	aload_1
	iload_2
	invokevirtual test30/f(F)V
	aload_2
	iconst_0
	invokestatic VC/lang/System/putFloatLn(F)V
	aload_2
	iconst_1
	invokestatic VC/lang/System/putFloatLn(F)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 19
.end method
