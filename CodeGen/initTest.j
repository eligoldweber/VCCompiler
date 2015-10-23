.class public initTest
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
.var 1 is vc$ LinitTest; from L0 to L1
	new initTest
	dup
	invokenonvirtual initTest/<init>()V
	astore_1
.var 2 is ar V from L0 to L1
	iconst_3
	newarray boolean
	astore_2
	dup
	iconst_0
	iconst_1
	bastore
	dup
	iconst_1
	iconst_0
	bastore
	dup
	iconst_2
	iconst_1
	bastore
	istore_2
.var 3 is a Z from L0 to L1
	aload_2
	iconst_2
	baload
	istore_3
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 6
.end method
