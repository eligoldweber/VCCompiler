.class public test16
.super java/lang/Object
	
.field static i I
	
	; standard class static initializer 
.method static <clinit>()V
	
	bipush 100
	putstatic test16/i I
	
	; set limits used by this method
.limit locals 0
.limit stack 2
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
.var 1 is vc$ Ltest16; from L0 to L1
	new test16
	dup
	invokenonvirtual test16/<init>()V
	astore_1
.var 2 is f F from L0 to L1
	iload -1
	bipush 20
	iadd
	putstatic test16/i I
	i2f
	fstore_2
.var 3 is x F from L0 to L1
	fload_2
	iconst_2
	i2f
	fdiv
	fstore_3
	fload_3
	invokestatic VC/lang/System/putFloatLn(F)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 27
.end method
