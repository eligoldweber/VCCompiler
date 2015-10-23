.class public eliTest6
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
.var 1 is vc$ LeliTest6; from L0 to L1
	new eliTest6
	dup
	invokenonvirtual eliTest6/<init>()V
	astore_1
.var 2 is x [I from L0 to L1
	iconst_5
	newarray int
	dup
	iconst_0
	iconst_1
	iastore
	dup
	iconst_1
	iconst_2
	iastore
	dup
	iconst_2
	iconst_3
	iastore
	dup
	iconst_3
	iconst_4
	iastore
	dup
	iconst_4
	iconst_5
	iastore
	istore_2
	aload_2
	iconst_2
	invokestatic VC/lang/System/putIntLn(I)V
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 18
.end method
