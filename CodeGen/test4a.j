.class public test4a
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
.var 1 is vc$ Ltest4a; from L0 to L1
	new test4a
	dup
	invokenonvirtual test4a/<init>()V
	astore_1
.var 2 is a3 [I from L0 to L1
	iconst_1
	newarray int
	astore_2
	aload_2
	iconst_0
	iconst_3
	iastore
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 13
.end method
