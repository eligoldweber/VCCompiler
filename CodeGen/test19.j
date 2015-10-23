.class public test19
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
.var 1 is vc$ Ltest19; from L0 to L1
	new test19
	dup
	invokenonvirtual test19/<init>()V
	astore_1
.var 2 is a [I from L0 to L1
	iconst_2
	newarray int
	dup
	iconst_0
	iconst_1
	iastore
	dup
	iconst_1
	iconst_0
	iastore
	astore_2
.var 3 is i I from L0 to L1
	aload_2
	aload_2
	iconst_0
	iaload
	iconst_1
	isub
	aload_2
	aload_2
	iconst_1
	aload_2
	iconst_0
	iaload
	imul
	iaload
	iadd
	iaload
	istore_3
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 36
.end method
