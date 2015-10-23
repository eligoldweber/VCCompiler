.class public eliTest5
.super java/lang/Object
	
.field static x I
	
	; standard class static initializer 
.method static <clinit>()V
	
	iconst_5
	putstatic eliTest5/x I
	
	; set limits used by this method
.limit locals 0
.limit stack 2
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
.var 1 is vc$ LeliTest5; from L0 to L1
	new eliTest5
	dup
	invokenonvirtual eliTest5/<init>()V
	astore_1
	getstatic eliTest5/x I
	invokestatic VC/lang/System/putIntLn(I)V
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 2
.end method
