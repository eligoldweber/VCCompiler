.class public eliTest1
.super java/lang/Object
	
.field static a I
.field static b I
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_0
	putstatic eliTest1/a I
	iconst_0
	putstatic eliTest1/b I
	
	; set limits used by this method
.limit locals 0
.limit stack 1
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
.method fib(I)I
L0:
.var 0 is this LeliTest1; from L0 to L1
.var 1 is n I from L0 to L1
	iload_1
	iconst_1
	if_icmple L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iload_1
	ireturn
	goto L3
L2:
	aload_0
	iload_1
	iconst_1
	isub
	invokevirtual eliTest1/fib(I)I
	aload_0
	iload_1
	iconst_2
	isub
	invokevirtual eliTest1/fib(I)I
	iadd
	ireturn
L3:
L1:
	nop
	
	; set limits used by this method
.limit locals 2
.limit stack 10
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ LeliTest1; from L0 to L1
	new eliTest1
	dup
	invokenonvirtual eliTest1/<init>()V
	astore_1
	invokestatic VC/lang/System.getInt()I
	putstatic eliTest1/a I
	aload_1
	getstatic eliTest1/a I
	invokevirtual eliTest1/fib(I)I
	invokestatic VC/lang/System/putIntLn(I)V
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 4
.end method
