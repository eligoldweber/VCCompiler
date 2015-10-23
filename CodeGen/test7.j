.class public test7
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
.var 1 is vc$ Ltest7; from L0 to L1
	new test7
	dup
	invokenonvirtual test7/<init>()V
	astore_1
.var 2 is a1 [I from L0 to L1
	iconst_1
	newarray int
	dup
	iconst_0
	iconst_1
	iastore
	astore_2
.var 3 is a2 [I from L0 to L1
	iconst_2
	newarray int
	dup
	iconst_1
	bipush 22
	iastore
	dup
	iconst_2
	bipush 33
	iastore
	astore_3
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 12
.end method
