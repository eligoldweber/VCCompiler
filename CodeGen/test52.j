.class public test52
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
.method even(I)Z
L0:
.var 0 is this Ltest52; from L0 to L1
.var 1 is n I from L0 to L1
	iload_1
	iconst_2
	idiv
	iconst_2
	imul
	iconst_2
	if_icmpeq L2
	iconst_0
	goto L3
L2:
	iconst_1
L3:
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 2
.limit stack 8
.end method
.method pow(II)I
L0:
.var 0 is this Ltest52; from L0 to L1
.var 1 is x I from L0 to L1
.var 2 is n I from L0 to L1
	iload_2
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
	iload_2
	iconst_1
	if_icmpeq L8
	iconst_0
	goto L9
L8:
	iconst_1
L9:
	ifeq L6
	iload_1
	ireturn
	goto L7
L6:
	aload_0
	iload_2
	invokevirtual test52/even(I)Z
	ifeq L10
	aload_0
	iload_1
	iload_1
	imul
	iload_2
	iconst_2
	idiv
	invokevirtual test52/pow(II)I
	ireturn
	goto L11
L10:
	aload_0
	iload_1
	iload_1
	imul
	iload_2
	iconst_2
	idiv
	invokevirtual test52/pow(II)I
	iload_1
	imul
	ireturn
L11:
L7:
L3:
L1:
	nop
	
	; set limits used by this method
.limit locals 3
.limit stack 44
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest52; from L0 to L1
	new test52
	dup
	invokenonvirtual test52/<init>()V
	astore_1
	aload_1
	iconst_2
	bipush 10
	invokevirtual test52/pow(II)I
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 11
.end method
