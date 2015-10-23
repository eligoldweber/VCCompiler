/*
 * Emitter.java    15 -*- MAY -*- 2015
 * Jingling Xue, School of Computer Science, UNSW, Australia
 */

// A new frame object is created for every function just before the
// function is being translated in visitFuncDecl.
//
// All the information about the translation of a function should be
// placed in this Frame object and passed across the AST nodes as the
// 2nd argument of every visitor method in Emitter.java.

package VC.CodeGen;

import java.util.LinkedList;
import java.util.Enumeration;
import java.util.ListIterator;

import VC.ASTs.*;
import VC.ErrorReporter;
import VC.StdEnvironment;

public final class Emitter implements Visitor {

  private ErrorReporter errorReporter;
  private String inputFilename;
  private String classname;
  private String outputFilename;
  private int indeX = -1;
  private int arrayInit = 0;
  

  public Emitter(String inputFilename, ErrorReporter reporter) {
    this.inputFilename = inputFilename;
    errorReporter = reporter;
    
    int i = inputFilename.lastIndexOf('.');
    if (i > 0)
      classname = inputFilename.substring(0, i);
    else
      classname = inputFilename;
    
  }

  // PRE: ast must be a Program node

  public final void gen(AST ast) {
    ast.visit(this, null); 
    JVM.dump(classname + ".j");
  }
    
  // Programs
  public Object visitProgram(Program ast, Object o) {
     /** This method works for scalar variables only. You need to modify
         it to handle all array-related declarations and initialisations.
      **/ 

    // Generates the default constructor initialiser 
    emit(JVM.CLASS, "public", classname);
    emit(JVM.SUPER, "java/lang/Object");

    emit("");

    // Three subpasses:

    // (1) Generate .field definition statements since
    //     these are required to appear before method definitions
    List list = ast.FL;
    while (!list.isEmpty()) {
      DeclList dlAST = (DeclList) list;
      if (dlAST.D instanceof GlobalVarDecl) {
        GlobalVarDecl vAST = (GlobalVarDecl) dlAST.D;
       // System.out.println(VCtoJavaType(vAST.T));
        emit(JVM.STATIC_FIELD, vAST.I.spelling, VCtoJavaType(vAST.T));
        }
      list = dlAST.DL;
    }

    emit("");

    // (2) Generate <clinit> for global variables (assumed to be static)
 
    emit("; standard class static initializer ");
    emit(JVM.METHOD_START, "static <clinit>()V");
    emit("");
    Frame frame = new Frame(false);

    list = ast.FL;
    while (!list.isEmpty()) {
      DeclList dlAST = (DeclList) list;
      if (dlAST.D instanceof GlobalVarDecl) {
        dlAST.D.visit(this, frame);
      }
      list = dlAST.DL;
    }

    // create a Frame for <clinit>

    //Frame frame = new Frame(false);

    list = ast.FL;
    while (!list.isEmpty()) {
      DeclList dlAST = (DeclList) list;
      if (dlAST.D instanceof GlobalVarDecl) {
        GlobalVarDecl vAST = (GlobalVarDecl) dlAST.D;
        if (!vAST.E.isEmptyExpr()) {
          vAST.E.visit(this, frame);
        } else {
          if (vAST.T.equals(StdEnvironment.floatType))
            emit(JVM.FCONST_0);
          else
            emit(JVM.ICONST_0);
          frame.push();
          
        }
        emitPUTSTATIC(VCtoJavaType(vAST.T), vAST.I.spelling); 
        frame.pop();
      }
      list = dlAST.DL;
    }
   
    emit("");
    emit("; set limits used by this method");
    emit(JVM.LIMIT, "locals", frame.getNewIndex());

    emit(JVM.LIMIT, "stack", frame.getMaximumStackSize());
    emit(JVM.RETURN);
    emit(JVM.METHOD_END, "method");

    emit("");

    // (3) Generate Java bytecode for the VC program

    emit("; standard constructor initializer ");
    emit(JVM.METHOD_START, "public <init>()V");
    emit(JVM.LIMIT, "stack 1");
    emit(JVM.LIMIT, "locals 1");
    emit(JVM.ALOAD_0);
    emit(JVM.INVOKESPECIAL, "java/lang/Object/<init>()V");
    emit(JVM.RETURN);
    emit(JVM.METHOD_END, "method");

    
    
      list = ast.FL;
    while (!list.isEmpty()) {
      DeclList dlAST = (DeclList) list;
      if (dlAST.D instanceof GlobalVarDecl) {
       list = dlAST.DL;
      }else{
       break; 
      }
      
    }
    //return ast.FL.visit(this, o);
    return list.visit(this,o);
    
  }

  // Statements 

  public Object visitStmtList(StmtList ast, Object o) {
    //System.out.println("   STMT "  +  " "  + ast.S + " " + ast.position);
    ast.S.visit(this, o);
    ast.SL.visit(this, o);
    return null;
  }

  public Object visitCompoundStmt(CompoundStmt ast, Object o) {
    Frame frame = (Frame) o; 
    //System.out.println("Cmopund Stmt " + frame);

    String scopeStart = frame.getNewLabel();
    String scopeEnd = frame.getNewLabel();
    frame.scopeStart.push(scopeStart);
    frame.scopeEnd.push(scopeEnd);
   
    emit(scopeStart + ":");
    if (ast.parent instanceof FuncDecl) {
      if (((FuncDecl) ast.parent).I.spelling.equals("main")) {
        emit(JVM.VAR, "0 is argv [Ljava/lang/String; from " + (String) frame.scopeStart.peek() + " to " +  (String) frame.scopeEnd.peek());
        emit(JVM.VAR, "1 is vc$ L" + classname + "; from " + (String) frame.scopeStart.peek() + " to " +  (String) frame.scopeEnd.peek());
        // Generate code for the initialiser vc$ = new classname();
        emit(JVM.NEW, classname);
        emit(JVM.DUP);
        frame.push(2);  
        emit("invokenonvirtual", classname + "/<init>()V");
        frame.pop();
        emit(JVM.ASTORE_1);
        frame.pop();
      } else {
        emit(JVM.VAR, "0 is this L" + classname + "; from " + (String) frame.scopeStart.peek() + " to " +  (String) frame.scopeEnd.peek());
        ((FuncDecl) ast.parent).PL.visit(this, o);
      }
    }
    ast.DL.visit(this, o);
    //System.out.println("After DL VISIT " + this);////
    ast.SL.visit(this, o);
    emit(scopeEnd + ":");

    frame.scopeStart.pop();
    frame.scopeEnd.pop();
    return null;
  }

public Object visitReturnStmt(ReturnStmt ast, Object o) {
  
   Frame frame = (Frame)o;
   //String op = ast.E.spelling;
   //System.out.println("RETURN "+ ast.E);
/*
  int main() { return 0; } must be interpretted as 
  public static void main(String[] args) { return ; }
  Therefore, "return expr", if present in the main of a VC program
  must be translated into a RETURN rather than IRETURN instruction.
*/
   ast.E.visit(this,o);
   
     if (frame.isMain())  {
        emit(JVM.RETURN);
        return null;
     }
   
   if(ast.E instanceof BinaryExpr){
     BinaryExpr temp  = (BinaryExpr) ast.E; 
     if(temp.O.spelling.contains("i")){
       emit(JVM.IRETURN);
     }
     else{
       emit(JVM.FRETURN);
     }
   }
   
   if(ast.E instanceof VarExpr){
     VarExpr test = (VarExpr) ast.E;
     if(test.type instanceof IntType || test.type instanceof BooleanType){
       emitILOAD(indeX);
     emit(JVM.IRETURN);
    }
    if(test.type instanceof FloatType){
      emitILOAD(indeX);
     emit(JVM.FRETURN);
    }
     
   }else if (ast.E instanceof ArrayExpr){
      ArrayExpr array = (ArrayExpr) ast.E;
      if(array.type instanceof FloatType){
         emit(JVM.FALOAD);
          emit(JVM.FRETURN);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BALOAD);
          emit(JVM.IRETURN);
      }else{
        emit(JVM.IALOAD);
         emit(JVM.IRETURN);
      }
      
    }
   
   if(ast.E instanceof CallExpr){
     
     CallExpr ce = (CallExpr) ast.E;
       if(ce.type instanceof IntType || ce.type instanceof BooleanType){
      // emitILOAD(indeX);
     emit(JVM.IRETURN);
    }
    if(ce.type instanceof FloatType){
     // emitILOAD(indeX);
     emit(JVM.FRETURN);
    }
   }
   
   if (ast.E instanceof UnaryExpr){
     UnaryExpr temp = (UnaryExpr) ast.E;
    if(temp.E instanceof IntExpr || ast.E instanceof BooleanExpr){
     emit(JVM.IRETURN);
    }
    if(temp.E instanceof FloatExpr){
     emit(JVM.FRETURN);
    }
    
    
    if(temp.E instanceof VarExpr){
     VarExpr test = (VarExpr) temp.E;
     //System.out.println("VAR ==> " + test.type);
     if(test.type instanceof IntType || test.type instanceof BooleanType){
     emit(JVM.IRETURN);
    }
    if(test.type instanceof FloatType){
     emit(JVM.FRETURN);
    }
     
   }
        
       
   }
   
    if(ast.E instanceof IntExpr || ast.E instanceof BooleanExpr){
     emit(JVM.IRETURN);
    }
    if(ast.E instanceof FloatExpr){
     emit(JVM.FRETURN);
    }
     return null;
// Your other code goes here
         
  }

  public Object visitEmptyStmtList(EmptyStmtList ast, Object o) {
    return null;
  }

  public Object visitEmptyCompStmt(EmptyCompStmt ast, Object o) {
    return null;
  }

  public Object visitEmptyStmt(EmptyStmt ast, Object o) {
    return null;
  }
  
  public Object visitIfStmt(IfStmt ast, Object o){
    
    Frame frame = (Frame) o;
    String L1 = frame.getNewLabel();
    String L2 = frame.getNewLabel(); 
    
    ast.E.visit(this,o);
    
    if(ast.E instanceof VarExpr){
      emitILOAD(indeX);
    }
    emit(JVM.IFEQ, L1);
    //frame.push();
    ast.S1.visit(this,o);
    
    emit(JVM.GOTO, L2);
    emit(L1 + ":");
    ast.S2.visit(this,o);
    emit(L2 + ":");
    
    return null;
  }
  
  public Object visitWhileStmt(WhileStmt ast, Object o){
    //System.out.println("\n While\n");
    Frame frame = (Frame) o;
    String L1 = frame.getNewLabel();
    String L2 = frame.getNewLabel(); 
    
    frame.conStack.push(L1);
    frame.brkStack.push(L2);
    
    emit(L1 + ":");
    ast.E.visit(this,o);
    if(ast.E instanceof VarExpr){
      emitILOAD(indeX);
    }
    emit(JVM.IFEQ, L2);
    ast.S.visit(this,o);
    
    emit(JVM.GOTO, L1);
    emit(L2 + ":");
    frame.conStack.pop();
    frame.brkStack.pop();
    
    return null;
  }
  
  public Object visitForStmt(ForStmt ast, Object o){
//    Frame frame = (Frame) o;
//    String L1 = frame.getNewLabel();
//    String L2 = frame.getNewLabel(); 
//    
//    frame.conStack.push(L1);
//    frame.brkStack.push(L2);
//    ast.E1.visit(this,o);
//    emit(L1 + ":");
//        ast.E2.visit(this,o);
//        emit(JVM.IFEQ, L2);
//        ast.S.visit(this,o);
//        
//        ast.E3.visit(this,o);
//        emit(JVM.GOTO, L1);
//   emit(L2 + ":");
//        
//    frame.conStack.pop();
//    frame.brkStack.pop();
    
    Frame frame = (Frame) o;
    String L1 = frame.getNewLabel();
    String L2 = frame.getNewLabel(); 
    String L3 = frame.getNewLabel(); 
    
    frame.conStack.push(L1);
    frame.brkStack.push(L3);
    ast.E1.visit(this,o);
    emit(JVM.GOTO, L2);
    emit(L1 + ":");
    ast.E3.visit(this,o);
    emit(L2 + ":");
        ast.E2.visit(this,o);
        emit(JVM.IFEQ, L3);
        ast.S.visit(this,o);
        emit(JVM.GOTO, L1);
   emit(L3 + ":");
        
    frame.conStack.pop();
    frame.brkStack.pop();
    
    
    return null;
  }
  public Object visitBreakStmt(BreakStmt ast, Object o){
    Frame frame = (Frame) o;
    //System.out.println("\n\n" + ast + " " + frame.brkStack.peek()+  "\n\n");
    emit(JVM.GOTO, frame.brkStack.peek());
    
    return null;
  }
  public Object visitContinueStmt(ContinueStmt ast, Object o){
    Frame frame = (Frame) o;
    emit(JVM.GOTO, frame.conStack.peek());
    return null;
  }
  
  public Object visitExprStmt(ExprStmt ast, Object o){
    ast.E.visit(this,o);
    //pop if it has a value left on the stack
    return null;
  }
  
  
  

  // Expressions

  public Object visitCallExpr(CallExpr ast, Object o) {
    Frame frame = (Frame) o;
    String fname = ast.I.spelling;

    if (fname.equals("getInt")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System.getInt()I");
      frame.push();
    } else if (fname.equals("putInt")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System.putInt(I)V");
      frame.pop();
    } else if (fname.equals("putIntLn")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putIntLn(I)V");
      frame.pop();
    } else if (fname.equals("getFloat")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/getFloat()F");
      frame.push();
    } else if (fname.equals("putFloat")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putFloat(F)V");
      frame.pop();
    } else if (fname.equals("putFloatLn")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putFloatLn(F)V");
      frame.pop();
    } else if (fname.equals("putBool")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putBool(Z)V");
      frame.pop();
    } else if (fname.equals("putBoolLn")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putBoolLn(Z)V");
      frame.pop();
    } else if (fname.equals("putString")) {
      ast.AL.visit(this, o);
      emit(JVM.INVOKESTATIC, "VC/lang/System/putString(Ljava/lang/String;)V");
      frame.pop();
    } else if (fname.equals("putStringLn")) {
      ast.AL.visit(this, o);
      emit(JVM.INVOKESTATIC, "VC/lang/System/putStringLn(Ljava/lang/String;)V");
      frame.pop();
    } else if (fname.equals("putLn")) {
      ast.AL.visit(this, o); // push args (if any) into the op stack
      emit("invokestatic VC/lang/System/putLn()V");
    } else { // programmer-defined functions

      FuncDecl fAST = (FuncDecl) ast.I.decl;

      // all functions except main are assumed to be instance methods
      if (frame.isMain()) 
        emit("aload_1"); // vc.funcname(...)
      else
        emit("aload_0"); // this.funcname(...)
      frame.push();

      ast.AL.visit(this, o);
    
      String retType = VCtoJavaType(fAST.T);
      
      // The types of the parameters of the called function are not
      // directly available in the FuncDecl node but can be gathered
      // by traversing its field PL.

      StringBuffer argsTypes = new StringBuffer("");
      List fpl = fAST.PL;
      while (! fpl.isEmpty()) {
        if (((ParaList) fpl).P.T.equals(StdEnvironment.booleanType))
          argsTypes.append("Z");         
        else if (((ParaList) fpl).P.T.equals(StdEnvironment.intType))
          argsTypes.append("I");         
        else
          argsTypes.append("F");         
        fpl = ((ParaList) fpl).PL;
      }
      
      emit("invokevirtual", classname + "/" + fname + "(" + argsTypes + ")" + retType);
      frame.pop(argsTypes.length() + 1);

      if (! retType.equals("V"))
        frame.push();
    }
    return null;
  }

  public Object visitEmptyExpr(EmptyExpr ast, Object o) {
    return null;
  }
   public Object visitEmptyExprList(EmptyExprList ast, Object o) {
    return null;
  }
 

  public Object visitIntExpr(IntExpr ast, Object o) {
        Frame frame = (Frame) o;
    ast.IL.visit(this, o);
            frame.push(1);
    
    return null;
  }

  public Object visitFloatExpr(FloatExpr ast, Object o) {
    ast.FL.visit(this, o);
    return null;
  }

  public Object visitBooleanExpr(BooleanExpr ast, Object o) {
    //System.out.println("BLAH  ;gouugugbljkvkjkbhgccjvhjyufgikhjkytuitg\n\n\n");
    ast.BL.visit(this, o);
    return null;
  }

  public Object visitStringExpr(StringExpr ast, Object o) {
    ast.SL.visit(this, o);
    return null;
  }
  
  public Object visitUnaryExpr(UnaryExpr ast, Object o){

    Frame frame = (Frame) o;
    String op = ast.O.spelling;
    //System.out.println("\n\nUNARY " + op + " "  + ast.E);
        
        if(op.equals("i!")){
          String L1 = frame.getNewLabel();
          String L2 = frame.getNewLabel();   
          ast.E.visit(this,o);
          if(ast.E instanceof VarExpr){
            emitILOAD(indeX);
          }
          emit(JVM.IFEQ, L1);
          emit(JVM.ICONST_0);
          emit(JVM.GOTO, L2);
          emit(L1 + ":");
          emit(JVM.ICONST_1);
          emit(L2 + ":");
        }
        
         if(op.equals("i2f")){
           ast.E.visit(this,o);
            if(ast.E instanceof VarExpr){
            emitILOAD(indeX);
          }
           emit(JVM.I2F);
           
         }
        
        if(op.equals("i-")){
          if(ast.E instanceof IntExpr){
            IntExpr temp = (IntExpr) ast.E;
            emitICONST(-Integer.parseInt(temp.IL.spelling));
          }
          if(ast.E instanceof FloatExpr){
            FloatExpr temp = (FloatExpr) ast.E;
            emitFCONST(-Float.parseFloat(temp.FL.spelling));
          }
          //ast.E.visit(this,o);
          
        }
        if(op.equals("f-")){
          if(ast.E instanceof IntExpr){
            IntExpr temp = (IntExpr) ast.E;
            emitICONST(-Integer.parseInt(temp.IL.spelling));
          }
          if(ast.E instanceof FloatExpr){
            FloatExpr temp = (FloatExpr) ast.E;
            emitFCONST(-Float.parseFloat(temp.FL.spelling));
          }
          //ast.E.visit(this,o);
          
        }
        if(op.equals("f+") || op.equals("i+")){
          if(ast.E instanceof IntExpr){
            IntExpr temp = (IntExpr) ast.E;
            emitICONST(Integer.parseInt(temp.IL.spelling));
          }
          if(ast.E instanceof FloatExpr){
            FloatExpr temp = (FloatExpr) ast.E;
            emitFCONST(Float.parseFloat(temp.FL.spelling));
          }
          
        }
        
        
    return null;
  }
  public Object visitBinaryExpr(BinaryExpr ast, Object o){

    Frame frame = (Frame) o;
    //System.out.println(frame);
    String op = ast.O.spelling;
    //System.out.println("Binary " + op + " " + frame.scopeStart + "  //==// " + ast.E1 + ", " + ast.E2);
    
     
     
    if(op.equals("i&&")){   //E1 && E2
      String L1 = frame.getNewLabel();
      String L2 = frame.getNewLabel();     
      ast.E1.visit(this, o);
      if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      emit(JVM.IFEQ, L1);
      ast.E2.visit(this, o);
      if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      emit(JVM.IFEQ, L1);
      emit(JVM.ICONST_1);
      emit(JVM.GOTO, L2);
      emit(L1 + ":");
      emit(JVM.ICONST_0);
      emit(L2 + ":");
    }else if(op.equals("i||")){ // E1 i|| E2
      String L1 = frame.getNewLabel();
      String L2 = frame.getNewLabel();     
      ast.E1.visit(this, o);
      if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      emit(JVM.IFGE, L1);
      ast.E2.visit(this, o); 
      if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      emit(JVM.IFGE, L1);
      emit(JVM.ICONST_0);
      emit(JVM.GOTO, L2);
      emit(L1 + ":");
      emit(JVM.ICONST_1);
      emit(L2 + ":");
    }else if(op.equals("i==")){ // E1 i== E2
      String L1 = frame.getNewLabel();
      String L2 = frame.getNewLabel();     
      ast.E1.visit(this, o);
      if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      ast.E2.visit(this, o); 
      if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
      emit(JVM.IF_ICMPEQ, L1);
      emit(JVM.ICONST_0);
      emit(JVM.GOTO, L2);
      emit(L1 + ":");
      emit(JVM.ICONST_1);
      emit(L2 + ":");
    }else if(op.equals("i>")){ // E1 i> E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o);
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.IF_ICMPGT, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
      // frame.pop();
      // frame.pop();
    }else if(op.equals("i<")){ // E1 i< E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o);
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.IF_ICMPLT, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("i>=")){ // E1 i>= E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o);
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.IF_ICMPGE, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("i<=")){ // E1 i<= E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
     VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o);
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.IF_ICMPLE, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("f>")){ // E1 f> E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o); 
       if(ast.E2 instanceof VarExpr){
      VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.FCMPG);
       emit(JVM.IFGT, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("f<")){ // E1 f< E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o); 
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.FCMPL);
       emit(JVM.IFLT, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("f<=")){ // E1 f<= E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o); 
       if(ast.E2 instanceof VarExpr){
      VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.FCMPL);
       emit(JVM.IFLE, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else if(op.equals("f>=")){ // E1 f>= E2
       String L1 = frame.getNewLabel();
       String L2 = frame.getNewLabel();
       ast.E1.visit(this, o);
       if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       ast.E2.visit(this, o); 
       if(ast.E2 instanceof VarExpr){
       VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }
       emit(JVM.FCMPG);
       emit(JVM.IFGE, L1);
       emit(JVM.ICONST_0);
       emit(JVM.GOTO, L2);
       emit(L1 + ":");
       emit(JVM.ICONST_1);
       emit(L2 + ":");
    }else{ //just arithmetic opperations fi+-*/
      
      ast.E1.visit(this, o);
      if(ast.E1 instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E1;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }else if (ast.E1 instanceof ArrayExpr){
      ArrayExpr array = (ArrayExpr) ast.E1;
      if(array.type instanceof FloatType){
         emit(JVM.FALOAD);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BALOAD);
      }else{
        emit(JVM.IALOAD);
      }
      
    }
      
    
      ast.E2.visit(this, o); 
      if(ast.E2 instanceof VarExpr){
        VarExpr temp = (VarExpr) ast.E2;
        if(temp.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }else if (ast.E2 instanceof ArrayExpr){
      ArrayExpr array = (ArrayExpr) ast.E2;
      if(array.type instanceof FloatType){
         emit(JVM.FALOAD);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BALOAD);
      }else{
        emit(JVM.IALOAD);
      }
      
    }
      
      
      if (op.equals("i+")){
        emit(JVM.IADD);
      }else if(op.equals("i-")){
        emit(JVM.ISUB);
      }else if(op.equals("i*")){
        emit(JVM.IMUL);
      }else if(op.equals("i/")){
        emit(JVM.IDIV);
      }else if(op.equals("f-")){
        emit(JVM.FSUB);
      }else if(op.equals("f+")){
        emit(JVM.FADD);
      }else if(op.equals("f*")){
        emit(JVM.FMUL);
      }else if(op.equals("f/")){
        emit(JVM.FDIV);
      }
    }
    
    return null;
  }
  public Object visitInitExpr(InitExpr ast, Object o){
    Frame frame = (Frame) o;
    ast.IL.visit(this,o);
        //frame.pop(3);
    return null;
  }
  
  
  public Object visitExprList(ExprList ast, Object o){
    emit(JVM.DUP);
    emitICONST(arrayInit);
    arrayInit++;
    ast.E.visit(this,o);
    if(ast.E.type instanceof FloatType){
         emit(JVM.FASTORE);
      }else if (ast.E.type instanceof BooleanType){
         emit(JVM.BASTORE);
      }else{
        emit(JVM.IASTORE);
      }
    ast.EL.visit(this,o);
    return null;
  }
  public Object visitArrayExpr(ArrayExpr ast, Object o){ //add pop
    Frame frame = (Frame) o;
    ast.V.visit(this,o);
    //System.out.println("HERE "  + indeX +  " "  + ast.V + " " + ast.position);
    SimpleVar eli = (SimpleVar) ast.V;
    AST decl = eli.I.decl;
    if(decl instanceof GlobalVarDecl){
     
      GlobalVarDecl globalDecl = (GlobalVarDecl) decl;
       emitGETSTATIC(VCtoJavaType(globalDecl.T), globalDecl.I.spelling); 
    }else{
    emitALOAD(indeX);
    }
    ast.E.visit(this,o);
    if(ast.E instanceof VarExpr){
        VarExpr test = (VarExpr) ast.E;
     if(test.type instanceof IntType || test.type instanceof BooleanType){
       emitILOAD(indeX);
    }
    if(test.type instanceof FloatType){
      emitFLOAD(indeX);
    }
      
    }
    //frame.pop();
    return null;
  }
  public Object visitVarExpr(VarExpr ast, Object o){
    Frame frame = (Frame) o;
    ast.V.visit(this,o);
   // emitILOAD(indeX);
    frame.push();
    return null;
  }
  public Object visitAssignExpr(AssignExpr ast, Object o){
    Frame frame = (Frame) o;
    System.out.println("Assign : "+ast.E1 + " " + ast.E2);
    frame.push(2);
    ast.E1.visit(this,o);
    ast.E2.visit(this,o);
    
    if(ast.E2 instanceof VarExpr){
     
      VarExpr temp1 = (VarExpr) ast.E2;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }else if (ast.E2 instanceof ArrayExpr){
      //System.out.println(ast.E2);
      ArrayExpr array = (ArrayExpr) ast.E2;
      if(array.type instanceof FloatType){
         emit(JVM.FALOAD);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BALOAD);
      }else{
        emit(JVM.IALOAD);
      }
      
    }
//    else if(!(ast.E2 instanceof BinaryExpr)|| !(ast.E2 instanceof BooleanExpr)){
//      emit(JVM.DUP);
//    }
//    
   
    
    if(ast.E1 instanceof VarExpr){
      //frame.push();
     VarExpr test = (VarExpr) ast.E1;
     //AST decl = test.I.decl; 
     // emit(JVM.DUP);
     Ident ex  = (Ident)test.V.visit(this,o);
     AST decl = ex.decl; 
     //System.out.println("   DECL " +decl);
     if(decl instanceof GlobalVarDecl){
       emitPUTSTATIC(VCtoJavaType(test.type), ex.spelling); 
        //frame.pop();
        return null;
     }
      if(test.type instanceof FloatType){
         emitFSTORE(ex);
      }else{
         emitISTORE(ex);
      }
    }else if(ast.E1 instanceof ArrayExpr){
       ArrayExpr array = (ArrayExpr) ast.E1;
       Ident ex  = (Ident)array.V.visit(this,o);
       AST decl = ex.decl;
      //System.out.println(array.type  + " asfasdfasdf" );
      if(array.type instanceof FloatType){
         emit(JVM.FASTORE);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BASTORE);
      }else{
        emit(JVM.IASTORE);
      }
    }
     else{
       emit(JVM.DUP);
    }
   
    
    //emitISTORE(indeX);
    return null;
  }
  
  

  // Declarations

  public Object visitDeclList(DeclList ast, Object o) {
   // System.out.println("Decl List " + o + ast.D);
    ast.D.visit(this, o);
    ast.DL.visit(this, o);
    return null;
  }

  public Object visitEmptyDeclList(EmptyDeclList ast, Object o) {
    return null;
  }

  public Object visitFuncDecl(FuncDecl ast, Object o) {

    Frame frame; 

    if (ast.I.spelling.equals("main")) {

       frame = new Frame(true);

      // Assume that main has one String parameter and reserve 0 for it
      frame.getNewIndex(); 

      emit(JVM.METHOD_START, "public static main([Ljava/lang/String;)V"); 
      // Assume implicitly that
      //      classname vc$; 
      // appears before all local variable declarations.
      // (1) Reserve 1 for this object reference.

      frame.getNewIndex(); 

    } else {

       frame = new Frame(false);

      // all other programmer-defined functions are treated as if
      // they were instance methods
      frame.getNewIndex(); // reserve 0 for "this"

      String retType = VCtoJavaType(ast.T);

      // The types of the parameters of the called function are not
      // directly available in the FuncDecl node but can be gathered
      // by traversing its field PL.

      StringBuffer argsTypes = new StringBuffer("");
      List fpl = ast.PL;
      while (! fpl.isEmpty()) {
        if (((ParaList) fpl).P.T.equals(StdEnvironment.booleanType))
          argsTypes.append("Z");         
        else if (((ParaList) fpl).P.T.equals(StdEnvironment.intType))
          argsTypes.append("I");         
        else
          argsTypes.append("F");         
        fpl = ((ParaList) fpl).PL;
      }

      emit(JVM.METHOD_START, ast.I.spelling + "(" + argsTypes + ")" + retType);
    }

    ast.S.visit(this, frame);

    // JVM requires an explicit return in every method. 
    // In VC, a function returning void may not contain a return, and
    // a function returning int or float is not guaranteed to contain
    // a return. Therefore, we add one at the end just to be sure.

    if (ast.T.equals(StdEnvironment.voidType)) {
      emit("");
      emit("; return may not be present in a VC function returning void"); 
      emit("; The following return inserted by the VC compiler");
      emit(JVM.RETURN); 
    } else if (ast.I.spelling.equals("main")) {
      // In case VC's main does not have a return itself
      emit(JVM.RETURN);
    } else
      emit(JVM.NOP); 

    emit("");
    emit("; set limits used by this method");
    emit(JVM.LIMIT, "locals", frame.getNewIndex());

    emit(JVM.LIMIT, "stack", frame.getMaximumStackSize());
    emit(".end method");

    return null;
  }

  public Object visitGlobalVarDecl(GlobalVarDecl ast, Object o) {
   // System.out.println("GLOBAL " + ast.T);
        Frame frame = (Frame) o;
        String T = VCtoJavaType(ast.T);
      if(ast.T instanceof ArrayType){
        if( !(ast.E instanceof InitExpr)){
          ast.T.visit(this,o);
          if (ast.index >= 0 && ast.index <= 3) {
            emit(JVM.ASTORE + "_" + ast.index);
          }else{
            emit(JVM.ASTORE); 
          }
          
          
        }else{
          ast.T.visit(this,o);
        }
    }

    // nothing to be done
    return null;
  }

  public Object visitLocalVarDecl(LocalVarDecl ast, Object o) {
    //System.out.println("   Local VAr " + ast.I.spelling + " " + ast.E + "  "  + ast.E.position  +ast.T);
    Frame frame = (Frame) o;
    ast.index = frame.getNewIndex();
    String T = VCtoJavaType(ast.T);

    emit(JVM.VAR + " " + ast.index + " is " + ast.I.spelling + " " + T + " from " + (String) frame.scopeStart.peek() + " to " +  (String) frame.scopeEnd.peek());
    frame.push();
    if(ast.T instanceof ArrayType){
      if( !(ast.E instanceof InitExpr)){
      ast.T.visit(this,o);
      if (ast.index >= 0 && ast.index <= 3) {
           emit(JVM.ASTORE + "_" + ast.index);
      }else{
           emit(JVM.ASTORE); 
      }
      
      
      }else{
        ast.T.visit(this,o);
      }
    }

    if (!ast.E.isEmptyExpr()) {
      ast.E.visit(this, o);///dsfa/sdfa/sdf
  
      if(ast.E instanceof VarExpr){
      VarExpr temp1 = (VarExpr) ast.E;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }else if (ast.E instanceof ArrayExpr){
      //System.out.println(ast.E2);
      ArrayExpr array = (ArrayExpr) ast.E;
      if(array.type instanceof FloatType){
         emit(JVM.FALOAD);
      }else if (array.type instanceof BooleanType){
         emit(JVM.BALOAD);
      }else{
        emit(JVM.IALOAD);
      }
      
    }
      
      if (ast.T.equals(StdEnvironment.floatType)) {
        // cannot call emitFSTORE(ast.I) since this I is not an
        // applied occurrence 
        if (ast.index >= 0 && ast.index <= 3) 
          emit(JVM.FSTORE + "_" + ast.index); 
        else
          emit(JVM.FSTORE, ast.index); 
        frame.pop();
      } else if(ast.T instanceof ArrayType){
        //emit(JVM.ASTORE + "_" + ast.index);
           if (ast.index >= 0 && ast.index <= 3) {
          emit(JVM.ASTORE + "_" + ast.index); 

        }
           else{
          emit(JVM.ASTORE, ast.index); 
         emit(JVM.NOP);
           }
        frame.pop();
        
      }else {
        // cannot call emitISTORE(ast.I) since this I is not an
        // applied occurrence 
        if (ast.index >= 0 && ast.index <= 3) 
          emit(JVM.ISTORE + "_" + ast.index); 
        else
          emit(JVM.ISTORE, ast.index); 
        frame.pop();
      }
    }

    return null;
  }

  // Parameters

  public Object visitParaList(ParaList ast, Object o) {
    ast.P.visit(this, o);
    ast.PL.visit(this, o);
    return null;
  }

  public Object visitParaDecl(ParaDecl ast, Object o) {
    Frame frame = (Frame) o;
    
    ast.index = frame.getNewIndex();
    //System.out.println("para DECL " + ast.I.spelling + " : " + ast.index);
    String T = VCtoJavaType(ast.T);

    emit(JVM.VAR + " " + ast.index + " is " + ast.I.spelling + " " + T + " from " + (String) frame.scopeStart.peek() + " to " +  (String) frame.scopeEnd.peek());
    return null;
  }

  public Object visitEmptyParaList(EmptyParaList ast, Object o) {
    return null;
  }

  // Arguments 

  public Object visitArgList(ArgList ast, Object o) {
    ast.A.visit(this, o);
    ast.AL.visit(this, o);
    return null;
  }

  public Object visitArg(Arg ast, Object o) {
    //ast.E.visit(this, o);
        Frame frame = (Frame) o;
    if(ast.E instanceof VarExpr){

     VarExpr test = (VarExpr) ast.E;
     Ident ex  = (Ident)test.V.visit(this,o);
     AST decl = ex.decl; 
     if(decl instanceof GlobalVarDecl){
       
       emitGETSTATIC(VCtoJavaType(test.type), ex.spelling);
             frame.push(2);
        return null;
     }
           frame.push(2);
      VarExpr temp1 = (VarExpr) ast.E;
        if(temp1.type instanceof FloatType){
          emitFLOAD(indeX);
        }else{
      emitILOAD(indeX);
        }
    }else{
      frame.push(2);
      ast.E.visit(this, o);
    }
    
    //.......
    return null;
  }

  public Object visitEmptyArgList(EmptyArgList ast, Object o) {
    return null;
  }

  // Types 

  public Object visitIntType(IntType ast, Object o) {
    return null;
  }

  public Object visitFloatType(FloatType ast, Object o) {
    return null;
  }

  public Object visitBooleanType(BooleanType ast, Object o) {
    return null;
  }

  public Object visitVoidType(VoidType ast, Object o) {
    return null;
  }

  public Object visitErrorType(ErrorType ast, Object o) {
    return null;
  }
  
  public Object visitStringType(StringType ast, Object o){
    return null;
  }
  public Object visitArrayType(ArrayType ast, Object o){
    //System.out.println("\nArrayType - - - " + ast.E + " " + ast.T);
    ast.E.visit(this,o);
      //  System.out.println("HERE1111x");
    String T;
    if (ast.T.equals(StdEnvironment.booleanType))
      T = "boolean";
    else if (ast.T.equals(StdEnvironment.intType))
      T = "int";
    else if (ast.T.equals(StdEnvironment.floatType))
      T = "float";
    else // if (t.equals(StdEnvironment.voidType))
      T = "void";
    emit(JVM.NEWARRAY, T);
    //System.out.println("HERE");
    return null;
  }

  // Literals, Identifiers and Operators 

  public Object visitIdent(Ident ast, Object o) {
    return null;
  }

  public Object visitIntLiteral(IntLiteral ast, Object o) {
    Frame frame = (Frame) o;
    emitICONST(Integer.parseInt(ast.spelling));
    frame.push();
    return null;
  }

  public Object visitFloatLiteral(FloatLiteral ast, Object o) {
    Frame frame = (Frame) o;
    emitFCONST(Float.parseFloat(ast.spelling));
    frame.push();
    return null;
  }

  public Object visitBooleanLiteral(BooleanLiteral ast, Object o) {
    Frame frame = (Frame) o;
    emitBCONST(ast.spelling.equals("true"));
    frame.push();
    return null;
  }

  public Object visitStringLiteral(StringLiteral ast, Object o) {
    Frame frame = (Frame) o;
    emit(JVM.LDC, "\"" + ast.spelling + "\"");
    frame.push();
    return null;
  }

  public Object visitOperator(Operator ast, Object o) {
    return null;
  }

  // Variables 

  public Object visitSimpleVar(SimpleVar ast, Object o) {
     Frame frame = (Frame) o;
    //Decl temp = frame.pop();
    AST decl = ast.I.decl; 
    if(decl instanceof LocalVarDecl){
      LocalVarDecl localDecl = (LocalVarDecl) decl;
      //System.out.println("  LocalDecl " + ast.I.spelling+ "       blah blah blah " + localDecl.index);
      indeX = localDecl.index;
      frame.push(localDecl.index);
      //frame.pop();
    }
    if(decl instanceof ParaDecl){
      ParaDecl paraDecl = (ParaDecl) decl;
      //System.out.println(" paraDecl " + ast.I.spelling +"        blah blah blah " + paraDecl.index);
      frame.push(paraDecl.index);
      //frame.pop();
      
      
      indeX = paraDecl.index;
     // System.out.println(frame.pop());
    }
    
    if(decl instanceof GlobalVarDecl){
      GlobalVarDecl globalDecl = (GlobalVarDecl) decl;
            frame.push(globalDecl.index);
     // emitGETSTATIC(VCtoJavaType(globalDecl.T), globalDecl.I.spelling); 
      
    }
    return ast.I;
  }

  // Auxiliary methods for byte code generation

  // The following method appends an instruction directly into the JVM 
  // Code Store. It is called by all other overloaded emit methods.

  private void emit(String s) {
    JVM.append(new Instruction(s)); 
  }

  private void emit(String s1, String s2) {
    emit(s1 + " " + s2);
  }

  private void emit(String s1, int i) {
    emit(s1 + " " + i);
  }

  private void emit(String s1, float f) {
    emit(s1 + " " + f);
  }

  private void emit(String s1, String s2, int i) {
    emit(s1 + " " + s2 + " " + i);
  }

  private void emit(String s1, String s2, String s3) {
    emit(s1 + " " + s2 + " " + s3);
  }

  private void emitIF_ICMPCOND(String op, Frame frame) {
    String opcode;

    if (op.equals("i!="))
      opcode = JVM.IF_ICMPNE;
    else if (op.equals("i=="))
      opcode = JVM.IF_ICMPEQ;
    else if (op.equals("i<"))
      opcode = JVM.IF_ICMPLT;
    else if (op.equals("i<="))
      opcode = JVM.IF_ICMPLE;
    else if (op.equals("i>"))
      opcode = JVM.IF_ICMPGT;
    else // if (op.equals("i>="))
      opcode = JVM.IF_ICMPGE;

    String falseLabel = frame.getNewLabel();
    String nextLabel = frame.getNewLabel();

    emit(opcode, falseLabel);
    frame.pop(2); 
    emit("iconst_0");
    emit("goto", nextLabel);
    emit(falseLabel + ":");
    emit(JVM.ICONST_1);
    frame.push(); 
    emit(nextLabel + ":");
  }

  private void emitFCMP(String op, Frame frame) {
    String opcode;

    if (op.equals("f!="))
      opcode = JVM.IFNE;
    else if (op.equals("f=="))
      opcode = JVM.IFEQ;
    else if (op.equals("f<"))
      opcode = JVM.IFLT;
    else if (op.equals("f<="))
      opcode = JVM.IFLE;
    else if (op.equals("f>"))
      opcode = JVM.IFGT;
    else // if (op.equals("f>="))
      opcode = JVM.IFGE;

    String falseLabel = frame.getNewLabel();
    String nextLabel = frame.getNewLabel();

    emit(JVM.FCMPG);
    frame.pop(2);
    emit(opcode, falseLabel);
    emit(JVM.ICONST_0);
    emit("goto", nextLabel);
    emit(falseLabel + ":");
    emit(JVM.ICONST_1);
    frame.push();
    emit(nextLabel + ":");

  }
  
  private void emitALOAD(int index){
    if (index >= 0 && index <= 3) 
      emit(JVM.ALOAD + "_" + index); 
    else
      emit(JVM.ALOAD, index); 
  }

  private void emitILOAD(int index) {
    if (index >= 0 && index <= 3) 
      emit(JVM.ILOAD + "_" + index); 
    else
      emit(JVM.ILOAD, index); 
  }

  private void emitFLOAD(int index) {
    if (index >= 0 && index <= 3) 
      emit(JVM.FLOAD + "_"  + index); 
    else
      emit(JVM.FLOAD, index); 
  }

  private void emitGETSTATIC(String T, String I) {
    emit(JVM.GETSTATIC, classname + "/" + I, T); 
  }

  private void emitISTORE(Ident ast) {
    int index;
    if (ast.decl instanceof ParaDecl)
      index = ((ParaDecl) ast.decl).index; 
    else
      index = ((LocalVarDecl) ast.decl).index; 
    
    if (index >= 0 && index <= 3) 
      emit(JVM.ISTORE + "_" + index); 
    else
      emit(JVM.ISTORE, index); 
  }

  private void emitFSTORE(Ident ast) {
    int index;
    if (ast.decl instanceof ParaDecl)
      index = ((ParaDecl) ast.decl).index; 
    else
      index = ((LocalVarDecl) ast.decl).index; 
    if (index >= 0 && index <= 3) 
      emit(JVM.FSTORE + "_" + index); 
    else
      emit(JVM.FSTORE, index); 
  }

  private void emitPUTSTATIC(String T, String I) {
    emit(JVM.PUTSTATIC, classname + "/" + I, T); 
  }

  private void emitICONST(int value) {
    if (value == -1)
      emit(JVM.ICONST_M1); 
    else if (value >= 0 && value <= 5) 
      emit(JVM.ICONST + "_" + value); 
    else if (value >= -128 && value <= 127) 
      emit(JVM.BIPUSH, value); 
    else if (value >= -32768 && value <= 32767)
      emit(JVM.SIPUSH, value); 
    else 
      emit(JVM.LDC, value); 
  }

  private void emitFCONST(float value) {
    if(value == 0.0)
      emit(JVM.FCONST_0); 
    else if(value == 1.0)
      emit(JVM.FCONST_1); 
    else if(value == 2.0)
      emit(JVM.FCONST_2); 
    else 
      emit(JVM.LDC, value); 
  }

  private void emitBCONST(boolean value) {
    if (value)
      emit(JVM.ICONST_1);
    else
      emit(JVM.ICONST_0);
  }

  private String VCtoJavaType(Type t) {
    if (t instanceof ArrayType){
      ArrayType at = (ArrayType) t;
    if (at.T instanceof IntType)
      return "[I";
    else if (at.T instanceof FloatType)
      return "[F";
    else if (at.T instanceof BooleanType)
      return "[B";
    else if (at.T instanceof VoidType)
      return "[V";   // will be detected as semantic errors
    else  // should not happen
      return "[*";
    }
    if (t.equals(StdEnvironment.booleanType))
      return "Z";
    else if (t.equals(StdEnvironment.intType))
      return "I";
    else if (t.equals(StdEnvironment.floatType))
      return "F";
    else // if (t.equals(StdEnvironment.voidType))
      return "V";
  }

}
