.class public t5
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
.var 1 is vc$ Lt5; from L0 to L1
	new t5
	dup
	invokenonvirtual t5/<init>()V
	astore_1
.var 2 is flag Z from L0 to L1
.var 3 is x I from L0 to L1
	iconst_0
	istore_2
	iconst_0
	istore_3
L2:
	iload_2
	ifeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L3
L6:
	iload_3
	iconst_1
	iadd
	istore_3
	iload_3
	iconst_5
	if_icmpeq L10
	iconst_0
	goto L11
L10:
	iconst_1
L11:
	ifeq L8
L12:
	iconst_1
	istore_2
L13:
	goto L9
L8:
L9:
L7:
	goto L2
L3:
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 7
.end method
