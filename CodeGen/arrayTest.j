.class public arrayTest
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
.var 1 is vc$ LarrayTest; from L0 to L1
	new arrayTest
	dup
	invokenonvirtual arrayTest/<init>()V
	astore_1
.var 2 is anArray V from L0 to L1
	bipush 10
	newarray float
	astore_2
.var 3 is x F from L0 to L1
	aload_2
	iconst_0
	ldc 100.1
	fastore
	aload_2
	iconst_0
	faload
	ldc 1.4
	fadd
	fstore_3
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 7
.end method
