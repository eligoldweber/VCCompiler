.class public fuck1
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
.var 1 is vc$ Lfuck1; from L0 to L1
	new fuck1
	dup
	invokenonvirtual fuck1/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	bipush 10
	istore_2
.var 3 is f F from L0 to L1
	bipush 100
	istore_2
	iload_2
	iconst_1
	iadd
	istore_2
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	fconst_1
	fstore_3
	iload_2
	i2f
	fload_3
	fadd
	fstore_3
	iload_3
	invokestatic VC/lang/System/putFloatLn(F)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 59
.end method
