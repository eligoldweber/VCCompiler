.class public test34
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
.var 1 is vc$ Ltest34; from L0 to L1
	new test34
	dup
	invokenonvirtual test34/<init>()V
	astore_1
.var 2 is a [I from L0 to L1
	iconst_2
	newarray int
	dup
	iconst_0
	iconst_1
	iastore
	dup
	iconst_1
	iconst_1
	iastore
	astore_2
	aload_2
	iconst_0
	aload_2
	iconst_1
	if_icmpeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	goto L3
L2:
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
L3:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 22
.end method
