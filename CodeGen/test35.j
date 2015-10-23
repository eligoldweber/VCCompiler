.class public test35
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
.var 1 is vc$ Ltest35; from L0 to L1
	new test35
	dup
	invokenonvirtual test35/<init>()V
	astore_1
.var 2 is f F from L0 to L1
	bipush 11
	i2f
	fstore_2
	fload_2
	bipush 11
	i2f
	ifeq L2
	fload_2
	invokestatic VC/lang/System/putFloatLn(F)V
	goto L3
L2:
	iconst_0
	i2f
	invokestatic VC/lang/System/putFloatLn(F)V
L3:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 15
.end method
