.class public test22
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
.method f()Z
L0:
.var 0 is this Ltest22; from L0 to L1
	bipush 111
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_1
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 4
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest22; from L0 to L1
	new test22
	dup
	invokenonvirtual test22/<init>()V
	astore_1
	iconst_1
	ifeq L2
	aload_1
	invokevirtual test22/f()Z
	ifeq L2
	iconst_1
	goto L3
L2:
	iconst_0
L3:
	invokestatic VC/lang/System/putBoolLn(Z)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 5
.end method
