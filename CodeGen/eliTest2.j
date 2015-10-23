.class public eliTest2
.super java/lang/Object
	
.field static i I
.field static b [B
	
	; standard class static initializer 
.method static <clinit>()V
	
	bipush 100
	putstatic eliTest2/i I
	dup
	iconst_0
	iconst_1
	bastore
	dup
	iconst_1
	iconst_0
	bastore
	putstatic eliTest2/b [B
	
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
.var 1 is vc$ LeliTest2; from L0 to L1
	new eliTest2
	dup
	invokenonvirtual eliTest2/<init>()V
	astore_1
	getstatic eliTest2/i I
	invokestatic VC/lang/System/putIntLn(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 2
.end method
