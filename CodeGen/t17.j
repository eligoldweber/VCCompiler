.class public t17
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
.var 1 is vc$ Lt17; from L0 to L1
	new t17
	dup
	invokenonvirtual t17/<init>()V
	astore_1
.var 2 is a [I from L0 to L1
	aload_2
	iconst_0
	aload_2
	iconst_1
	bipush 100
	sipush 200
	iadd
	iastore
	iastore
	aload_2
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
	aload_2
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 33
.end method
