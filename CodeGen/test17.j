.class public test17
.super java/lang/Object
	
.field static x [I
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_3
	newarray int
	astore_0
	iconst_0
	putstatic test17/x [I
	
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
.var 1 is vc$ Ltest17; from L0 to L1
	new test17
	dup
	invokenonvirtual test17/<init>()V
	astore_1
.var 2 is a [I from L0 to L1
	iconst_2
	newarray int
	astore_2
	getstatic test17/x [I
	iconst_1
	aload_2
	iconst_0
	iaload
	iconst_1
	iadd
	iastore
L2:
.var 3 is a [I from L2 to L3
	iconst_2
	newarray int
	dup
	iconst_0
	iconst_5
	iastore
	dup
	iconst_1
	bipush 15
	iastore
	astore_3
	aload_3
	iconst_0
	getstatic test17/x [I
	iconst_1
	iaload
	sipush 200
	imul
	getstatic test17/x [I
	iconst_1
	iaload
	iadd
	iastore
	aload_3
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
	aload_3
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
L3:
	aload_2
	iconst_0
	invokestatic VC/lang/System/putIntLn(I)V
	aload_2
	iconst_1
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 59
.end method
