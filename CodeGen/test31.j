.class public test31
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
.method f()I
L0:
.var 0 is this Ltest31; from L0 to L1
	bipush 99
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 2
.end method
.method g()I
L0:
.var 0 is this Ltest31; from L0 to L1
	iconst_1
	aload_0
	invokevirtual test31/f()I
	iadd
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 3
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest31; from L0 to L1
	new test31
	dup
	invokenonvirtual test31/<init>()V
	astore_1
.var 2 is a [I from L0 to L1
	iconst_1
	newarray int
	dup
	iconst_0
	bipush 100
	iastore
	astore_2
	aload_2
	aload_1
	invokevirtual test31/g()I
	bipush 100
	isub
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 12
.end method
