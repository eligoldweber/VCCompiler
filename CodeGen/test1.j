.class public test1
.super java/lang/Object
	
.field static i I
.field static b [B
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_2
	newarray boolean
	bipush 100
	putstatic test1/i I
	dup
	iconst_0
	iconst_1
	bastore
	dup
	iconst_1
	iconst_0
	bastore
	putstatic test1/b [B
	
	; set limits used by this method
.limit locals 0
.limit stack 5
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
.var 1 is vc$ Ltest1; from L0 to L1
	new test1
	dup
	invokenonvirtual test1/<init>()V
	astore_1
	getstatic test1/i I
	invokestatic VC/lang/System/putIntLn(I)V
	getstatic test1/b [B
	iconst_0
	invokestatic VC/lang/System/putBoolLn(Z)V
	getstatic test1/b [B
	iconst_1
	invokestatic VC/lang/System/putBoolLn(Z)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 9
.end method
