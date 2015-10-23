.class public i22f
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
.var 1 is vc$ Li22f; from L0 to L1
	new i22f
	dup
	invokenonvirtual i22f/<init>()V
	astore_1
.var 2 is x I from L0 to L1
.var 3 is y F from L0 to L1
.var 4 is z F from L0 to L1
	iconst_3
	istore_2
	ldc 3.2
	fstore_3
	iload_2
	i2f
	fload_3
	fadd
	fstore 4
L1:
	return
	
	; set limits used by this method
.limit locals 5
.limit stack 5
.end method
