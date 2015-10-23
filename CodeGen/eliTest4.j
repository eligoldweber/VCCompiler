.class public eliTest4
.super java/lang/Object
	
.field static x [I
	
	; standard class static initializer 
.method static <clinit>()V
	
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
	putstatic eliTest4/x [I
	
	; set limits used by this method
.limit locals 0
.limit stack 10
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
.var 1 is vc$ LeliTest4; from L0 to L1
	new eliTest4
	dup
	invokenonvirtual eliTest4/<init>()V
	astore_1
	getstatic eliTest4/x [I
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 4
.end method
