.class public test48
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
.method f()V
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 1
.limit stack 0
.end method
.method g()F
L0:
.var 0 is this Ltest48; from L0 to L1
	fconst_1
	freturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 1
.end method
.method h()Z
L0:
.var 0 is this Ltest48; from L0 to L1
	iconst_0
	ireturn
L1:
	nop
	
	; set limits used by this method
.limit locals 1
.limit stack 1
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest48; from L0 to L1
	new test48
	dup
	invokenonvirtual test48/<init>()V
	astore_1
	iconst_1
	aload_1
	invokevirtual test48/f()V
	aload_1
	invokevirtual test48/g()F
	aload_1
	invokevirtual test48/g()F
	invokestatic VC/lang/System/putFloatLn(F)V
	aload_1
	invokevirtual test48/h()Z
	invokestatic VC/lang/System/putBoolLn(Z)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 9
.end method
