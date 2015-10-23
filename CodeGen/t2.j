.class public t2
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
.method gcd(II)I
L0:
.var 0 is this Lt2; from L0 to L1
.var 1 is a I from L0 to L1
.var 2 is b I from L0 to L1
	iload_2
	iconst_0
	if_icmpeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iload_1
	goto L3
L2:
	iload_2
L3:
L1:
	nop
	
	; set limits used by this method
.limit locals 3
.limit stack 6
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Lt2; from L0 to L1
	new t2
	dup
	invokenonvirtual t2/<init>()V
	astore_1
	bipush 42
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 2
.end method
