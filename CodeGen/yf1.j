.class public yf1
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
.var 1 is vc$ Lyf1; from L0 to L1
	new yf1
	dup
	invokenonvirtual yf1/<init>()V
	astore_1
.var 2 is a I from L0 to L1
	iconst_0
	istore_2
.var 3 is b I from L0 to L1
	iconst_0
	istore_3
	iload_2
	iload_3
	iadd
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 9
.end method
