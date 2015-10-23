.class public test43
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
.var 1 is vc$ Ltest43; from L0 to L1
	new test43
	dup
	invokenonvirtual test43/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	iconst_1
	istore_2
.var 3 is j I from L0 to L1
	iconst_1
	istore_2
	goto L3
L2:
	iload_2
	iconst_1
	iadd
	istore_2
L3:
	iload_2
	iconst_5
	if_icmple L5
	iconst_0
	goto L6
L5:
	iconst_1
L6:
	ifeq L4
L7:
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	iload_2
	iconst_3
	if_icmpeq L11
	iconst_0
	goto L12
L11:
	iconst_1
L12:
	ifeq L9
	goto L2
	goto L10
L9:
L10:
	iconst_1
	istore_3
L13:
	iload_3
	iconst_3
	if_icmple L15
	iconst_0
	goto L16
L15:
	iconst_1
L16:
	ifeq L14
L17:
	iload_3
	invokestatic VC/lang/System/putIntLn(I)V
	iload_3
	iconst_3
	if_icmpeq L21
	iconst_0
	goto L22
L21:
	iconst_1
L22:
	ifeq L19
	goto L14
	goto L20
L19:
L20:
	iload_3
	iconst_1
	iadd
	istore_3
L18:
	goto L13
L14:
	invokestatic VC/lang/System/putLn()V
L8:
	goto L2
L4:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 81
.end method
