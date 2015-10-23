.class public fib
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
.method fib(I)I
L0:
.var 0 is this Lfib; from L0 to L1
.var 1 is x I from L0 to L1
	iload_1
	iconst_1
	if_icmple L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
L6:
	iload_1
	ireturn
L7:
	goto L3
L2:
L8:
	aload_0
	iload_1
	iconst_1
	isub
	invokevirtual fib/fib(I)I
	aload_0
	iload_1
	iconst_2
	isub
	invokevirtual fib/fib(I)I
	iadd
	ireturn
L9:
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
.var 1 is vc$ Lfib; from L0 to L1
	new fib
	dup
	invokenonvirtual fib/<init>()V
	astore_1
.var 2 is a I from L0 to L1
	aload_1
	iconst_5
	invokevirtual fib/fib(I)I
	istore_2
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 9
.end method
