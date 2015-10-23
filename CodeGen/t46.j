.class public t46
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
.var 1 is vc$ Lt46; from L0 to L1
	new t46
	dup
	invokenonvirtual t46/<init>()V
	astore_1
.var 2 is b Z from L0 to L1
.var 3 is i I from L0 to L1
	iconst_1
	istore_3
	goto L3
L2:
L3:
	iload_3
	iconst_1
	if_icmpeq L5
	iconst_0
	goto L6
L5:
	iconst_1
L6:
	ifeq L4
L7:
	ldc "one iteraton only "
	invokestatic VC/lang/System/putStringLn(Ljava/lang/String;)V
	iconst_0
	istore_3
L8:
	goto L2
L4:
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 24
.end method
