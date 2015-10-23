.class public negTest
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
.method temp()F
L0:
.var 0 is this LnegTest; from L0 to L1
.var 1 is c F from L0 to L1
	ldc -5.2
	fstore_1
	iload_1
	freturn
L1:
	nop
	
	; set limits used by this method
.limit locals 2
.limit stack 1
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ LnegTest; from L0 to L1
	new negTest
	dup
	invokenonvirtual negTest/<init>()V
	astore_1
.var 2 is x I from L0 to L1
.var 3 is y I from L0 to L1
.var 4 is z I from L0 to L1
	iconst_5
	istore_3
	bipush -42
	istore_2
	iload_2
	iload_3
	iadd
	istore 4
	iload 4
	bipush -42
	if_icmpeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
L6:
	iconst_0
	istore 4
L7:
	goto L3
L2:
L3:
L1:
	return
	
	; set limits used by this method
.limit locals 5
.limit stack 5
.end method
