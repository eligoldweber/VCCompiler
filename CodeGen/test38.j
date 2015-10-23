.class public test38
.super java/lang/Object
	
.field static a [I
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_2
	newarray int
	astore_0
	iconst_0
	putstatic test38/a [I
	
	; set limits used by this method
.limit locals 0
.limit stack 3
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
.var 1 is vc$ Ltest38; from L0 to L1
	new test38
	dup
	invokenonvirtual test38/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	iconst_5
	istore_2
	iload_2
	iconst_1
	if_icmpgt L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iload_2
	bipush 10
	if_icmplt L8
	iconst_0
	goto L9
L8:
	iconst_1
L9:
	ifeq L6
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	goto L7
L6:
L10:
	iconst_2
	invokestatic VC/lang/System/putIntLn(I)V
	getstatic test38/a [I
	iconst_0
	iconst_2
	iastore
L11:
L7:
	goto L3
L2:
	iconst_3
	invokestatic VC/lang/System/putIntLn(I)V
L3:
	getstatic test38/a [I
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
	getstatic test38/a [I
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 35
.end method
