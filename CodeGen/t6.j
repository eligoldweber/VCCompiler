.class public t6
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
.var 1 is vc$ Lt6; from L0 to L1
	new t6
	dup
	invokenonvirtual t6/<init>()V
	astore_1
.var 2 is ar V from L0 to L1
	bipush 10
	newarray int
	astore_2
.var 3 is i I from L0 to L1
	iconst_0
	istore_3
L2:
	iload_3
	bipush 11
	if_icmplt L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L3
L6:
	aload_2
	iload_3
	iload_3
	iastore
L7:
	iload_3
	iconst_1
	iadd
	istore_3
	goto L2
L3:
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 6
.end method
