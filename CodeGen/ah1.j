.class public ah1
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
.method fact(I)I
L0:
.var 0 is this Lah1; from L0 to L1
.var 1 is n I from L0 to L1
	iload_1
	iconst_0
	if_icmpeq L4
	iconst_0
	goto L5
L4:
	iconst_1
L5:
	ifeq L2
	iconst_1
	ireturn
	goto L3
L2:
	iload_1
	aload_0
	iload_1
	iconst_1
	isub
	invokevirtual ah1/fact(I)I
	imul
	ireturn
L3:
L1:
	nop
	
	; set limits used by this method
.limit locals 2
.limit stack 4
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Lah1; from L0 to L1
	new ah1
	dup
	invokenonvirtual ah1/<init>()V
	astore_1
.var 2 is x I from L0 to L1
	aload_1
	iconst_4
	invokevirtual ah1/fact(I)I
	istore_2
L1:
	return
	
	; set limits used by this method
.limit locals 3
.limit stack 3
.end method
