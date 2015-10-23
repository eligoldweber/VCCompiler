.class public shortC
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
.var 1 is vc$ LshortC; from L0 to L1
	new shortC
	dup
	invokenonvirtual shortC/<init>()V
	astore_1
.var 2 is a I from L0 to L1
	bipush 100
	istore_2
.var 3 is b Z from L0 to L1
	iconst_1
	istore_3
.var 4 is c Z from L0 to L1
	iconst_1
	istore 4
.var 5 is d Z from L0 to L1
	iconst_1
	istore 5
	iload_3
	ifeq L6
	iload 4
	ifeq L6
	iconst_1
	goto L7
L6:
	iconst_0
L7:
	ifeq L4
	iload 5
	ifeq L4
	iconst_1
	goto L5
L4:
	iconst_0
L5:
	ifeq L2
	sipush 500
	istore_2
	goto L3
L2:
	sipush 150
	istore_2
L3:
L1:
	return
	
	; set limits used by this method
.limit locals 6
.limit stack 6
.end method
