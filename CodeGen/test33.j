.class public test33
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
.method h()I
L0:
.var 0 is this Ltest33; from L0 to L1
	iconst_1
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 2
.end method
.method g(IFZ)V
L0:
.var 0 is this Ltest33; from L0 to L1
.var 1 is i I from L0 to L1
.var 2 is f F from L0 to L1
.var 3 is b Z from L0 to L1
	iload_1
	invokestatic VC/lang/System/putIntLn(I)V
	fload_2
	invokestatic VC/lang/System/putFloatLn(F)V
	iload_3
	invokestatic VC/lang/System/putBoolLn(Z)V
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 10
.end method
.method f(IF)V
L0:
.var 0 is this Ltest33; from L0 to L1
.var 1 is i I from L0 to L1
.var 2 is f F from L0 to L1
	iload_1
	invokestatic VC/lang/System/putIntLn(I)V
	fload_2
	invokestatic VC/lang/System/putFloatLn(F)V
	aload_0
	iload_1
	iconst_1
	iadd
	istore_1
	fload_2
	iconst_1
	i2f
	fadd
	aload_0
	invokevirtual test33/h()I
	i2f
	fadd
	iconst_1
	invokevirtual test33/g(IFZ)V
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 28
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest33; from L0 to L1
	new test33
	dup
	invokenonvirtual test33/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	iconst_1
	istore_2
.var 3 is j I from L0 to L1
	iconst_2
	istore_3
	aload_1
	iload_2
	iload_3
	i2f
	invokevirtual test33/f(IF)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 15
.end method
