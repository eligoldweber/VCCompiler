/***
 * *
 * * Recogniser.java By Eli Goldwber            
 * *
 ***/

/* At this stage, this parser accepts a subset of VC defined by
 * the following grammar. 
 *
 * You need to modify the supplied parsing methods (if necessary) and 
 * add the missing ones to obtain a parser for the VC language.
 *
 * (23-*-March-*-2014)

program             ->  ( func-decl | var-decl )*

// declarations
func-decl           -> type identifier para-list compound-stmt
var-decl            -> type init-declarator-list ";"
init-declarator-list-> init-declarator ( "," init-declarator )*
init-declarator     -> declarator ( "=" initialiser )? 
declarator          -> identifier 
                    |  identifier "[" INTLITERAL? "]"
initialiser         -> expr 
                    |  "{" expr ( "," expr )* "}"

// primitive types
type                -> void | boolean | int | float

// identifiers
identifier          -> ID 

// statements 
compound-stmt       -> "{" var-decl* stmt* "}" 
stmt                -> compound-stmt
                    |  if-stmt 
                    |  for-stmt
                    |  while-stmt 
                    |  break-stmt
                    |  continue-stmt
                    |  return-stmt
                    |  expr-stmt
if-stmt             -> if "(" expr ")" stmt ( else stmt )?
for-stmt            -> for "(" expr? ";" expr? ";" expr? ")" stmt
while-stmt          -> while "(" expr ")" stmt
break-stmt          -> break ";"
continue-stmt       -> continue ";"
return-stmt         -> return expr? ";"
expr-stmt           -> expr? ";"


// expressions 
expr                -> assignment-expr
assignment-expr     -> ( cond-or-expr "=" )* cond-or-expr
cond-or-expr        -> cond-and-expr 
                    |  cond-or-expr "||" cond-and-expr
cond-and-expr       -> equality-expr 
                    |  cond-and-expr "&&" equality-expr
equality-expr       -> rel-expr
                    |  equality-expr "==" rel-expr
                    |  equality-expr "!=" rel-expr
rel-expr            -> additive-expr
                    |  rel-expr "<" additive-expr
                    |  rel-expr "<=" additive-expr
                    |  rel-expr ">" additive-expr
                    |  rel-expr ">=" additive-expr
additive-expr       -> multiplicative-expr
                    |  additive-expr "+" multiplicative-expr
                    |  additive-expr "-" multiplicative-expr
multiplicative-expr -> unary-expr
                    |  multiplicative-expr "*" unary-expr
                    |  multiplicative-expr "/" unary-expr
unary-expr          -> "+" unary-expr
                    |  "-" unary-expr
                    |  "!" unary-expr
                    |  primary-expr

primary-expr        -> identifier arg-list?
                    | identifier "[" expr "]"
                    | "(" expr ")"
                    | INTLITERAL
                    | FLOATLITERAL
                    | BOOLLITERAL
                    | STRINGLITERAL

// parameters
para-list           -> "(" proper-para-list? ")"
proper-para-list    -> para-decl ( "," para-decl )*
para-decl           -> type declarator
arg-list            -> "(" proper-arg-list? ")"
proper-arg-list     -> arg ( "," arg )*
arg                 -> expr
*/

package VC.Recogniser;

import VC.Scanner.Scanner;
import VC.Scanner.SourcePosition;
import VC.Scanner.Token;
import VC.ErrorReporter;

public class Recogniser {

  private Scanner scanner;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private boolean varFlag = false;

  public Recogniser (Scanner lexer, ErrorReporter reporter) {
    scanner = lexer;
    errorReporter = reporter;

    currentToken = scanner.getToken();
  }

// match checks to see f the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void match(int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      currentToken = scanner.getToken();
    } else {
      syntacticError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

 // accepts the current token and fetches the next
  void accept() {
    currentToken = scanner.getToken();
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }


// ========================== PROGRAMS ========================

  public void parseProgram() {

    try {
      while (currentToken.kind != Token.EOF) {
      parseTypes();
      parseIdent();
      
      if(currentToken.kind == Token.LPAREN){
        parseFuncDecl();
      }else{
        varFlag = true;
        parseVarStartDecl();
      }
      
      }
      if (currentToken.kind != Token.EOF) {
        syntacticError("\"%\" wrong result type for a function", currentToken.spelling);
      }
    }
    catch (SyntaxError s) {  }
}


// ========================== DECLARATIONS ========================
  
  void parseFuncDecl() throws SyntaxError{
    //parseTypes();
    //parseIdent();
    parseParaList();
    parseCompoundStmt();
    
  }
  
  void parseVarStartDecl() throws SyntaxError { // if the var decl is outside a func
    //parseTypes();
    parseInitDeclList();
    match(Token.SEMICOLON);
    varFlag = false;
    
  }
  
  void parseVarDecl() throws SyntaxError { // if the var decl is inside a func
    parseTypes();
    parseInitDeclList();
      match(Token.SEMICOLON);
  }
  
  void parseInitDeclList() throws SyntaxError{
    parseInitDecl();
    
      if(currentToken.kind == Token.COMMA){
      
        match(Token.COMMA);
        parseInitDecl();
      }
   
  }
  
  void parseInitDecl() throws SyntaxError{
    if(!varFlag || currentToken.kind == Token.LBRACKET){
      
    parseDecl();
    }
   varFlag = false;
      if(currentToken.kind == Token.EQ){
        match(Token.EQ);
        parseInitialiser();
      }
    
  }
  
  void parseDecl() throws SyntaxError{ 
    if(!varFlag){
      parseIdent();
    }
      if(currentToken.kind == Token.LBRACKET){
        match(Token.LBRACKET);
        if(currentToken.kind == Token.INTLITERAL){
          match(Token.INTLITERAL);
        }
        match(Token.RBRACKET);
      }
  }
  
  void parseInitialiser() throws SyntaxError{
    
    switch (currentToken.kind) {
      
      case Token.ID:
      case Token.INTLITERAL:
      case Token.MINUS:
      case Token.LPAREN:
        parseExpr(); 
        break;
        
      case Token.LCURLY:
        match(Token.LCURLY);
        parseExpr();
        while(currentToken.kind == Token.COMMA){
          match(Token.COMMA);
          parseExpr();
        }
        match(Token.RCURLY);     
        break;
    }
    
    
  }
  

// ======================= PRIMITIVE TYPES ========================
  
  void parseTypes() throws SyntaxError{
    switch(currentToken.kind){
      case Token.INT:
        match(Token.INT); 
        break;
      case Token.VOID:
         match(Token.VOID);
         break;
      case Token.FLOAT:
         match(Token.FLOAT);
         break;
      case Token.BOOLEAN:
         match(Token.BOOLEAN);
         break;
      default:
        syntacticError("\"%\" wrong result type for a function", currentToken.spelling);
    }
  }
  
// ======================= STATEMENTS ==============================


  void parseCompoundStmt() throws SyntaxError {

    match(Token.LCURLY);
    while(currentToken.kind == Token.INT || currentToken.kind == Token.VOID 
            || currentToken.kind == Token.FLOAT || currentToken.kind == Token.BOOLEAN){
      parseVarDecl();
    }
    parseStmtList();
    match(Token.RCURLY);
  }

 // Here, a new nontermial has been introduced to define { stmt } *
  void parseStmtList() throws SyntaxError {

    while (currentToken.kind != Token.RCURLY) 
      parseStmt();
  }

  void parseStmt() throws SyntaxError {
    switch (currentToken.kind) {
      
      case Token.LCURLY:
        parseCompoundStmt();
         break;
      case Token.IF:
        parseIfStmt();
        break;
      case Token.FOR:
        parseForStmt();
        break;
      case Token.WHILE:
        parseWhileStmt();
        break;
      case Token.BREAK:
        parseBreakStmt();
        break;
     

    case Token.CONTINUE:
      parseContinueStmt();
      break;
      
    case Token.RETURN:
      parseReturnStmt();
      break;
      
    default:
      parseExprStmt();
      break;

    }
  }

  void parseIfStmt() throws SyntaxError {
    match(Token.IF);
    match(Token.LPAREN);
    parseExpr();
    match(Token.RPAREN);
    parseStmt();
    if(currentToken.kind == Token.ELSE){
      match(Token.ELSE);
      parseStmt();
    }
  }
  
  void parseForStmt() throws SyntaxError {
    match(Token.FOR);
    match(Token.LPAREN);
    
   if (currentToken.kind != Token.SEMICOLON)
        parseExpr();
    match(Token.SEMICOLON);
    
  if (currentToken.kind != Token.SEMICOLON)
        parseExpr();
    match(Token.SEMICOLON);
    
   if (currentToken.kind != Token.SEMICOLON)
        parseExpr();
    match(Token.SEMICOLON);
    
    
    match(Token.RPAREN);
    parseStmt();
  }
  
  void parseWhileStmt() throws SyntaxError {
    match(Token.WHILE);
    match(Token.LPAREN);
    parseExpr();
    match(Token.RPAREN);
    parseStmt();
    
  }
  
  void parseBreakStmt() throws SyntaxError {
    match(Token.BREAK);
    match(Token.SEMICOLON);
  }
  
  void parseContinueStmt() throws SyntaxError {

    match(Token.CONTINUE);
    match(Token.SEMICOLON);

  }
  void parseReturnStmt() throws SyntaxError {
    match(Token.RETURN);
  if (currentToken.kind != Token.SEMICOLON)
        parseExpr();
    
    match(Token.SEMICOLON);
  }

   void parseExprStmt() throws SyntaxError {

    if (currentToken.kind != Token.SEMICOLON)
        parseExpr();
    match(Token.SEMICOLON);
  }



// ======================= IDENTIFIERS ======================

 // Call parseIdent rather than match(Token.ID). 
 // In Assignment 3, an Identifier node will be constructed in here.


  void parseIdent() throws SyntaxError {

    if (currentToken.kind == Token.ID) {

 currentToken = scanner.getToken();
    } else 
      syntacticError("identifier expected here", "");
  }

// ======================= OPERATORS ======================

 // Call acceptOperator rather than accept(). 
 // In Assignment 3, an Operator Node will be constructed in here.

  void acceptOperator() throws SyntaxError {

    currentToken = scanner.getToken();
  }


// ======================= EXPRESSIONS ======================

  void parseExpr() throws SyntaxError {
    parseAssignExpr();
  }


  void parseAssignExpr() throws SyntaxError {

   parseAdditiveExpr();
   parseCondOrExpr();
    while (currentToken.kind == Token.EQ) {
      acceptOperator();
      parseCondOrExpr();
    }
    

  }

  void parseCondOrExpr() throws SyntaxError{
      parseCondAndExpr();
      while (currentToken.kind == Token.OROR ) {
      acceptOperator();
      parseCondAndExpr();
    }
      

  }

  void parseCondAndExpr() throws SyntaxError{
      parseEqualityExpr();
      while (currentToken.kind == Token.ANDAND ) {
      acceptOperator();
      parseEqualityExpr();
    }

  }

  void parseEqualityExpr() throws SyntaxError{
      parseRelationExpr();
      while (currentToken.kind == Token.EQEQ ||  currentToken.kind == Token.NOTEQ) {
      acceptOperator();
      parseRelationExpr();
    }

  }

  void parseRelationExpr() throws SyntaxError{
      parseAdditiveExpr();
      while (currentToken.kind == Token.LT ||  currentToken.kind == Token.LTEQ ||
             currentToken.kind == Token.GT ||  currentToken.kind == Token.GTEQ) {
      acceptOperator();
      parseAdditiveExpr();
    }

  }

  void parseAdditiveExpr() throws SyntaxError {

    parseMultiplicativeExpr();
    while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS ) {
      acceptOperator();
      parseMultiplicativeExpr();
    }
  }

  void parseMultiplicativeExpr() throws SyntaxError {
    parseUnaryExpr();
    while (currentToken.kind == Token.MULT || currentToken.kind == Token.DIV) {
      acceptOperator();
      parseUnaryExpr();
    }
  }

  void parseUnaryExpr() throws SyntaxError {
    

    switch (currentToken.kind) {
      case Token.MINUS:
        {
          acceptOperator();
          parseUnaryExpr();
        }
        break;
      case Token.PLUS:
        {
          acceptOperator();
          parseUnaryExpr();
        }
        break;
      case Token.NOT:
        {
          acceptOperator();
          parseUnaryExpr();
        }
        break;  

      default:
        parsePrimaryExpr();
        break;
       
    }
  }

  void parsePrimaryExpr() throws SyntaxError {
    switch (currentToken.kind) {
      

      case Token.ID:
        parseIdent();
        if(currentToken.kind == Token.LPAREN){
          parseArgList();
        }
        if(currentToken.kind == Token.LBRACKET){
          match(Token.LBRACKET);
          parseExpr();
          match(Token.RBRACKET);
        }
        break;

      case Token.LPAREN:
        {
          accept();
          parseExpr();
          match(Token.RPAREN);
        }
        break;

      case Token.INTLITERAL:
        parseIntLiteral();
        break;

        case Token.FLOATLITERAL:
        parseFloatLiteral();
        break;
        
        case Token.BOOLEANLITERAL:
        parseBooleanLiteral();
        break;
        
        case Token.STRINGLITERAL:
        parseStringLiteral();
        break;
        

        
      default:
        //???????//syntacticError("illegal parimary expression", currentToken.spelling);
       
    }
  }

// ========================== LITERALS ========================

  // Call these methods rather than accept().  In Assignment 3, 
  // literal AST nodes will be constructed inside these methods. 

  void parseIntLiteral() throws SyntaxError {

    if (currentToken.kind == Token.INTLITERAL) {
      currentToken = scanner.getToken();
    } else 
      syntacticError("integer literal expected here", "");
  }

  void parseFloatLiteral() throws SyntaxError {

    if (currentToken.kind == Token.FLOATLITERAL) {
      currentToken = scanner.getToken();
    } else 
      syntacticError("float literal expected here", "");
  }

  void parseBooleanLiteral() throws SyntaxError {

    if (currentToken.kind == Token.BOOLEANLITERAL) {
      currentToken = scanner.getToken();
    } else 
      syntacticError("boolean literal expected here", "");
  }
  
  void parseStringLiteral() throws SyntaxError {
    
    if (currentToken.kind == Token.STRINGLITERAL) {
      currentToken = scanner.getToken();
    } else 
      syntacticError("String literal expected here", "");
  }
  
// ======================== PARAMETERS ===========================
  
  
  
  void parseParaList() throws SyntaxError {
    match(Token.LPAREN);
    if(currentToken.kind == Token.BOOLEAN || currentToken.kind == Token.INT || currentToken.kind == Token.FLOAT){
      parseProperParaList();
    }
    match(Token.RPAREN);
  }
  
  void parseProperParaList() throws SyntaxError{
    parseParaDecl();
    while(currentToken.kind == Token.COMMA){
      
      match(Token.COMMA);
      parseParaDecl();
    }
    
  }
  
  void parseParaDecl() throws SyntaxError{
    
    parseTypes();
    parseDecl();
  }
  
  void parseArgList() throws SyntaxError{
    match(Token.LPAREN);
    if(currentToken.kind == Token.ID
        || currentToken.kind == Token.INTLITERAL
        || currentToken.kind == Token.MINUS
        || currentToken.kind == Token.LPAREN){
      parseProperArgList();
    }
    match(Token.RPAREN);
  }
  
  void parseProperArgList() throws SyntaxError{
    parseArg();
    while(currentToken.kind == Token.COMMA){
      match(Token.COMMA);
      parseArg();
    }
  }
  
  
  void parseArg() throws SyntaxError{
     parseExpr();
  }
  
  ///////////////// issues /23/31

}
