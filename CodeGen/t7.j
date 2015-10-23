.class public t7
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
.method fact(I)I
L0:
.var 0 is this Lt7; from L0 to L1
.var 1 is n I from L0 to L1
	iload_1
	iconst_0
	if_icmpeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iconst_1
	ireturn
	goto L3
L2:
	iload_1
	aload_0
	iload_1
	iconst_1
	isub
	invokevirtual t7/fact(I)I
	imul
	ireturn
L3:
L1:
	nop
	
	; set limits used by this method
.limit locals 2
.limit stack 7
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Lt7; from L0 to L1
	new t7
	dup
	invokenonvirtual t7/<init>()V
	astore_1
	aload_1
	iconst_4
	invokevirtual t7/fact(I)I
	ireturn
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 2
.end method
