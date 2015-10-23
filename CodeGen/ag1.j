.class public ag1
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
.method isFirstBigger(F)Z
L0:
.var 0 is this Lag1; from L0 to L1
.var 1 is list V from L0 to L1
.var 2 is temp Z from L0 to L1
.var 3 is a I from L0 to L1
.var 4 is b I from L0 to L1
	iconst_1
	istore_2
	aload_1
	iconst_0
	iaload
	istore_3
	aload_1
	iconst_1
	iaload
	istore 4
	iload_3
	iload 4
	if_icmpgt L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
L6:
L7:
	goto L3
L2:
L8:
	iconst_0
	istore_2
L9:
L3:
	iload_2
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 5
.limit stack 7
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Lag1; from L0 to L1
	new ag1
	dup
	invokenonvirtual ag1/<init>()V
	astore_1
.var 2 is list V from L0 to L1
	bipush 8
	newarray int
	dup
	iconst_0
	iconst_5
	iastore
	dup
	iconst_1
	iconst_1
	iastore
	dup
	iconst_2
	iconst_2
	iastore
	dup
	iconst_3
	iconst_3
	iastore
	dup
	iconst_4
	iconst_4
	iastore
	dup
	iconst_5
	iconst_1
	iastore
	dup
	bipush 6
	iconst_2
	iastore
	dup
	bipush 7
	iconst_3
	iastore
	istore_2
.var 3 is eli Z from L0 to L1
	aload_1
	iload_2
	invokevirtual ag1/isFirstBigger(F)Z
	istore_3
L1:
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 11
.end method
