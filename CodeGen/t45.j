.class public t45
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
.var 1 is vc$ Lt45; from L0 to L1
	new t45
	dup
	invokenonvirtual t45/<init>()V
	astore_1
.var 2 is i I from L0 to L1
	iconst_1
	istore_2
	iload_2
	invokestatic VC/lang/System/putIntLn(I)V
	goto L3
L2:
L3:
	ifeq L4
L5:
	iload_2
	bipush 10
	if_icmple L9
	iconst_0
	goto L10
L9:
	iconst_1
L10:
	ifeq L7
	goto L4
	goto L8
L7:
L8:
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	ldc " "
	invokestatic VC/lang/System/putString(Ljava/lang/String;)V
	iload_2
	iconst_1
	iadd
	istore_2
L6:
	goto L2
L4:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 29
.end method
