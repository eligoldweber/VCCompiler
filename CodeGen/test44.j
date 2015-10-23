.class public test44
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
.var 1 is vc$ Ltest44; from L0 to L1
	new test44
	dup
	invokenonvirtual test44/<init>()V
	astore_1
.var 2 is i I from L0 to L1
.var 3 is a [I from L0 to L1
	bipush 10
	newarray int
	astore_3
	iconst_0
	istore_2
	goto L3
L2:
	iload_2
	iconst_1
	iadd
	istore_2
L3:
	iload_2
	bipush 10
	if_icmplt L5
	iconst_0
	goto L6
L5:
	iconst_1
L6:
	ifeq L4
L7:
	aload_3
	iload_2
	iload_2
	iastore
L8:
	goto L2
L4:
	bipush 9
	istore_2
	goto L10
L9:
	iload_2
	iconst_1
	isub
	istore_2
L10:
	iload_2
	iconst_0
	if_icmpge L12
	iconst_0
	goto L13
L12:
	iconst_1
L13:
	ifeq L11
L14:
	iload_2
	iconst_2
	if_icmpeq L18
	iconst_0
	goto L19
L18:
	iconst_1
L19:
	ifeq L16
	goto L9
	goto L17
L16:
L17:
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
L15:
	goto L9
L11:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 80
.end method
