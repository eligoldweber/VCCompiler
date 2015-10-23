.class public test54
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
.method prime(I)V
L0:
.var 0 is this Ltest54; from L0 to L1
.var 1 is n I from L0 to L1
.var 2 is i I from L0 to L1
.var 3 is flag Z from L0 to L1
	iconst_1
	istore_3
	ldc "Enter value of N: "
	invokestatic VC/lang/System/putString(Ljava/lang/String;)V
	iconst_2
	istore_2
	goto L3
L2:
L3:
	iload_2
	iload_1
	iconst_2
	idiv
	if_icmplt L7
	iconst_0
	goto L8
L7:
	iconst_1
L8:
	ifeq L5
	iload_3
	ifeq L5
	iconst_1
	goto L6
L5:
	iconst_0
L6:
	ifeq L4
L9:
	iload_1
	iload_2
	idiv
	iload_2
	imul
	iload_1
	if_icmpeq L13
	iconst_0
	goto L14
L13:
	iconst_1
L14:
	ifeq L11
	iconst_0
	istore_3
	goto L12
L11:
	iload_2
	iconst_1
	iadd
	istore_2
L12:
L10:
	goto L2
L4:
	iload_3
	ifeq L15
L17:
	iload_1
	invokestatic VC/lang/System.putInt(I)V
	ldc " is prime"
	invokestatic VC/lang/System/putStringLn(Ljava/lang/String;)V
L18:
	goto L16
L15:
L19:
	iload_1
	invokestatic VC/lang/System.putInt(I)V
	ldc " has "
	invokestatic VC/lang/System/putString(Ljava/lang/String;)V
	iload_2
	invokestatic VC/lang/System.putInt(I)V
	ldc " as a factor"
	invokestatic VC/lang/System/putStringLn(Ljava/lang/String;)V
L20:
L16:
L1:
	
	; return may not be present in a VC function returning void
	; The following return inserted by the VC compiler
	return
	
	; set limits used by this method
.limit locals 4
.limit stack 74
.end method
.method public static main([Ljava/lang/String;)V
L0:
.var 0 is argv [Ljava/lang/String; from L0 to L1
.var 1 is vc$ Ltest54; from L0 to L1
	new test54
	dup
	invokenonvirtual test54/<init>()V
	astore_1
	aload_1
	bipush 77
	invokevirtual test54/prime(I)V
	aload_1
	bipush 79
	invokevirtual test54/prime(I)V
	iconst_0
	return
L1:
	return
	
	; set limits used by this method
.limit locals 2
.limit stack 8
.end method
