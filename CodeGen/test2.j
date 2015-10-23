.class public test2
.super java/lang/Object
	
.field static f [F
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_2
	newarray float
	dup
	iconst_0
	ldc 1.2
	fastore
	dup
	iconst_1
	ldc 3.4
	fastore
	putstatic test2/f [F
	
	; set limits used by this method
.limit locals 0
.limit stack 4
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
.var 1 is vc$ Ltest2; from L0 to L1
	new test2
	dup
	invokenonvirtual test2/<init>()V
	astore_1
	getstatic test2/f [F
	iconst_0
	invokestatic VC/lang/System/putFloatLn(F)V
	getstatic test2/f [F
	iconst_1
	invokestatic VC/lang/System/putFloatLn(F)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 8
.end method
