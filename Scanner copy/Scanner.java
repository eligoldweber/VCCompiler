/**
 ** Scanner.java                        
 **/

package VC.Scanner;

import VC.ErrorReporter;

public final class Scanner { 

  private SourceFile sourceFile;
  private boolean debug;

  private ErrorReporter errorReporter;
  private StringBuffer currentSpelling;
  private char currentChar;
  private char nextChar;
  private SourcePosition sourcePos;

  //added helper variables
  private  int lineNum = 1;
  private  int tStart = 1;
  private  int tEnd = 1; 
  private int charNum = 1;
  
  private String temp = ""; //helps create id's and strings
  private char test = ' '; //used to identify ints, and floats
// =========================================================

  public Scanner(SourceFile source, ErrorReporter reporter) {
    sourceFile = source;
    errorReporter = reporter;
    currentChar = sourceFile.getNextChar();
    debug = false;

    // you may initialise your counters for line and column numbers here
  }

  public void enableDebugging() {
    debug = true;
  }

  // accept gets the next character from the source program.

  private void accept() {

    currentChar = sourceFile.getNextChar();

  // you may save the lexeme of the current token incrementally here
  // you may also increment your line and column counters here
  }

  // inspectChar returns the n-th character after currentChar
  // in the input stream. 
  //
  // If there are fewer than nthChar characters between currentChar 
  // and the end of file marker, SourceFile.eof is returned.
  // 
  // Both currentChar and the current position in the input stream
  // are *not* changed. Therefore, a subsequent call to accept()
  // will always return the next char after currentChar.

  private char inspectChar(int nthChar) {
    return sourceFile.inspectChar(nthChar);
  }

  private int nextToken() {
    
      if(Character.isDigit(currentChar)){ //if the leading char is a digit, it will set currentChar 
                                          //as a place holder to signify there is an int or float
        test = currentChar;
        temp = temp + currentChar;
        currentChar = '#';
      }
      
    switch (currentChar) {
//--------------------- reserved words ---------------------------//

      case 'b':    //BOOLEAN && BREAK    
       while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("boolean")){
          currentSpelling.append(Token.spell(Token.BOOLEAN));
          temp = "";
          return Token.BOOLEAN;
        }else if(temp.equals("break")){
          currentSpelling.append(Token.spell(Token.BREAK));
          temp = "";
          return Token.BREAK;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }

  
      case 'c': // CONTINUE
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("continue")){
          currentSpelling.append(Token.spell(Token.CONTINUE));
          temp = "";
          return Token.CONTINUE;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }
        
      case 'e': // ELSE
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("else")){
          currentSpelling.append(Token.spell(Token.ELSE));
          temp = "";
          return Token.ELSE;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }
        
      case 'f':    //FLOAT && FOR && FALSE 
       while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("float")){
          currentSpelling.append(Token.spell(Token.FLOAT));
          temp = "";
          return Token.FLOAT;
        }else if(temp.equals("for")){
          currentSpelling.append(Token.spell(Token.FOR));
          temp = "";
          return Token.FOR;
        }
        else if(temp.equals("false")){
          currentSpelling.append(temp);
          temp = "";
          return Token.BOOLEANLITERAL;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }
        
      case 'i':    //IF && INT    
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("if")){
          currentSpelling.append(Token.spell(Token.IF));
          temp = "";
          return Token.IF;
        }else if(temp.equals("int")){
          currentSpelling.append(Token.spell(Token.INT));
          temp = "";
          return Token.INT;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }
        
      case 'r': // RETURN
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("return")){
          currentSpelling.append(Token.spell(Token.RETURN));
          temp = "";
          return Token.RETURN;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }

      case 'v': // VOID
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("void")){
          currentSpelling.append(Token.spell(Token.VOID));
          temp = "";
          return Token.VOID;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }
        
      case 'w': // WHILE
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
        if(temp.equals("while")){
          currentSpelling.append(Token.spell(Token.WHILE));
          temp = "";
          return Token.WHILE;
        }else{
          currentSpelling.append(temp);
          temp = "";
          return Token.ID;
        }

      
 //--------------------- operators ---------------------------//

    case '+':  //plus
 accept();
 tEnd++;
 currentSpelling.append(Token.spell(Token.PLUS));
 return Token.PLUS;

    case '-': //minus
 accept();
 tEnd++;
 currentSpelling.append(Token.spell(Token.MINUS));
 return Token.MINUS;

    case '*': //mult
 accept();
 tEnd++;
 currentSpelling.append(Token.spell(Token.MULT));
 return Token.MULT;

    case '/': //divide
 accept();
 tEnd++;
 currentSpelling.append(Token.spell(Token.DIV));
 return Token.DIV;

    case '!': // not && not equal
 accept();
 tEnd++;
 if(currentChar == '='){
     accept();
     tEnd++;
     currentSpelling.append(Token.spell(Token.NOTEQ));
     return Token.NOTEQ;
 }else{
     currentSpelling.append(Token.spell(Token.NOT));
     return Token.NOT;
 }

    case '=': // equal && Equalequal    
      accept();
      tEnd++;
      if(currentChar == '='){
        accept();
        tEnd++;
        currentSpelling.append(Token.spell(Token.EQEQ));
        return Token.EQEQ;
        }else{
            currentSpelling.append(Token.spell(Token.EQ));
            return Token.EQ;
        }


    case '<': // LT and LTEQ
      accept();
      tEnd++;
      if(currentChar == '='){
        accept();
        tEnd++;
        currentSpelling.append(Token.spell(Token.LTEQ));
        return Token.LTEQ;
      }else{
        currentSpelling.append(Token.spell(Token.LT));
        return Token.LT;
      }
      
    case '>': // GT and GTEQ
      accept();
      tEnd++;
      if(currentChar == '='){
        accept();
        tEnd++;
        currentSpelling.append(Token.spell(Token.GTEQ));
        return Token.GTEQ;
      }else{
        currentSpelling.append(Token.spell(Token.GT));
        return Token.GT;
      }      
        
      case '&':
        accept();
        tEnd++;
 if (currentChar == '&') {
     accept();
     tEnd++;
     currentSpelling.append(Token.spell(Token.ANDAND));
     return Token.ANDAND;
 } else {
     currentSpelling.append('&');
     return Token.ERROR;
        }
 
    case '|':
        accept();
        tEnd++;
 if (currentChar == '|') {
     accept();
     tEnd++;
     currentSpelling.append(Token.spell(Token.OROR));
     return Token.OROR;
 } else {
     currentSpelling.append('|');
     return Token.ERROR;
        }

///--------------------separators -------------------------------///

 case '{':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.LCURLY));
    return Token.LCURLY;
 
  case '}':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.RCURLY));
    return Token.RCURLY;
 
  case '(':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.LPAREN));
    return Token.LPAREN;
 
  case ')':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.RPAREN));
    return Token.RPAREN;
    
  case '[':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.LBRACKET));
    return Token.LBRACKET;
 
  case ']':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.RBRACKET));
    return Token.RBRACKET;  
    
  case ';':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.SEMICOLON));
    return Token.SEMICOLON;
 
  case ',':
    accept();
    tEnd++;
    currentSpelling.append(Token.spell(Token.COMMA));
    return Token.COMMA;  
    
 // ------------------------- Identifiers ----------------------------
//    
     case 't': //(also TRUE)
      while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n')){
          temp = temp + currentChar;
          tEnd++;
          accept();
          
        }
      if(temp.equals("true")){
        currentSpelling.append(temp);temp = "";return Token.BOOLEANLITERAL;
      }else{
         currentSpelling.append(temp);temp = "";return Token.ID;
      }
     //any other id begining with a char ( that is also not the beginign of a reserved word)
    case 'a':
    case 'd':
    case 'g': 
    case 'h':
    case 'j':
    case 'k': 
    case 'l':
    case 'm': 
    case 'n':
    case 'o': 
    case 'p':
    case 'q':
    case 's': 
    case 'u': 
    case 'x':
    case 'y': 
    case 'z':
    case 'A': 
    case 'B': 
    case 'C': 
    case 'D':
    case 'E': 
    case 'F':
    case 'G': 
    case 'H':
    case 'I': 
    case 'J':
    case 'K': 
    case 'L':
    case 'M': 
    case 'N':
    case 'O': 
    case 'P':
    case 'Q': 
    case 'R':
    case 'S': 
    case 'T':
    case 'U': 
    case 'V':
    case 'W': 
    case 'X':
    case 'Y': 
    case 'Z':
        while((currentChar != ' ') && (currentChar != SourceFile.eof) && (currentChar != '\n') &&
              ((Character.isDigit(currentChar)) || (Character.isLetter(currentChar))) ){
          temp = temp + currentChar;
          tEnd++;
          accept();
        }
         currentSpelling.append(temp);temp = "";return Token.ID;
         
 // -------  ----------------- Literals --------------------------------
// any kind of # starting with an int    
      case '#':
        currentChar = test;
        test =' ';
        accept();
        tEnd++;
        while(Character.isDigit(currentChar)){
          temp = temp + currentChar;
          accept();
          tEnd++;
        }
        
        if(currentChar == 'e' || currentChar == 'E'){//scientific notation
          
            if((!(inspectChar(1) == '+' || inspectChar(1) == '-' || Character.isDigit(inspectChar(1))))
              || ((inspectChar(1) == '+' || inspectChar(1) == '-' ) && (! Character.isDigit(inspectChar(2))))){ //not a correct exp
            
            currentSpelling.append(temp); test = ' ';temp = "";
            return Token.INTLITERAL;
            
          } 
          // If the scientific notation is correct so far..
             temp = temp + currentChar;
             accept();
             tEnd++;
             if(currentChar == '+' || currentChar == '-'){
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             while(Character.isDigit(currentChar)){ //getting the power of the exponent
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             currentSpelling.append(temp); test = ' ';temp = "";
             return Token.FLOATLITERAL;
        }
        
        if(currentChar == '.'){ // float with no scientific notation (ie.. the token doesnt start with a ".")
          temp = temp + currentChar;
          accept();
          tEnd++;
          while(Character.isDigit(currentChar)){
            temp = temp + currentChar;
            accept();
            tEnd++;
          }
          if(currentChar == 'e' || currentChar == 'E'){//scientific notation
            if((!(inspectChar(1) == '+' || inspectChar(1) == '-' || Character.isDigit(inspectChar(1))))
              || ((inspectChar(1) == '+' || inspectChar(1) == '-' ) && (! Character.isDigit(inspectChar(2))))){ //not a correct exp
            
            currentSpelling.append(temp); test = ' ';temp = "";
            return Token.INTLITERAL;
            
          } 
          // If the scientific notation is correct so far..
             temp = temp + currentChar;
             accept();
             tEnd++;
             if(currentChar == '+' || currentChar == '-'){
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             while(Character.isDigit(currentChar)){
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             currentSpelling.append(temp); test = ' ';temp = "";
             return Token.FLOATLITERAL;
        }
          currentSpelling.append(temp); test = ' ';temp = "";
          return Token.FLOATLITERAL;
        }
        
        //plain int
        currentSpelling.append(temp); test = ' ';temp = "";
        return Token.INTLITERAL;
         
/// start with "." ///////////   
    case '.':
        //  attempting to recognise a float
      temp = temp + currentChar;
          accept();
          tEnd++;
          if(!Character.isDigit(currentChar)){
            currentSpelling.append('.');
            return Token.ERROR;
          }
          while(Character.isDigit(currentChar)){
            temp = temp + currentChar;
            accept();
            tEnd++;
          }
          
          if(currentChar == 'e' || currentChar == 'E'){//scientific notation
            if((!(inspectChar(1) == '+' || inspectChar(1) == '-' || Character.isDigit(inspectChar(1))))
              || ((inspectChar(1) == '+' || inspectChar(1) == '-' ) && (! Character.isDigit(inspectChar(2))))){ //not a correct exp
            
            currentSpelling.append(temp); test = ' ';temp = "";
            return Token.INTLITERAL;
            
          } 
          /// If the scientific notation is correct so far..
             temp = temp + currentChar;
             accept();
             tEnd++;
             if(currentChar == '+' || currentChar == '-'){
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             while(Character.isDigit(currentChar)){
               temp = temp + currentChar;
               accept();
               tEnd++;
             }
             currentSpelling.append(temp); test = ' ';temp = "";
             return Token.FLOATLITERAL;
        }
          
          currentSpelling.append(temp); test = ' ';temp = "";
          return Token.FLOATLITERAL;
      
      case '"': // recognising a string
      accept();
      tEnd++;
       while((currentChar != '"')){
         
         if(currentChar == SourceFile.eof || currentChar == '\n'){
           System.out.println("ERROR: UN-CLOSED String" + lineNum +"("+ tStart + ") .. " + lineNum +"("+ tEnd + ")");
           currentSpelling.append(temp);temp = "";return Token.STRINGLITERAL;
           
       }
        
       
         if(currentChar == '\\' && inspectChar(1)== 'n' ){// takes care \n
           temp = temp+ "\n";
           accept(); 
           accept();
           tEnd = tEnd+2;
         }else if(currentChar == '\\' && inspectChar(1)== 't' ){// takes care \t
           temp = temp+ "\t";
           accept();
           accept();
           tEnd = tEnd+2;
         }else if(currentChar == '\\' && inspectChar(1)== 'r' ){// takes care \r
           temp = temp+ "\r";
           accept();
           accept();
         }else if(currentChar == '\\' && inspectChar(1)== 'b' ){// takes care \b
           temp = temp+ "\b";
           accept();
           accept();
         }else if(currentChar == '\\' && inspectChar(1)== 'f' ){// takes care \f
           temp = temp+ "\f";
           accept();
           accept();
         }else if(currentChar == '\\' && inspectChar(1)== '\'' ){// takes care \'
           temp = temp+ "\'";
           accept();
           accept();
         }else if(currentChar == '\\' && inspectChar(1)== '"' ){// takes care \"
           temp = temp+ "\"";
           accept();
           accept();
         }else if(currentChar == '\\' && inspectChar(1)== '\\' ){// takes care \\
           temp = temp+ "\\";
           accept();
           accept();
         }else if(currentChar == '\\' && !(inspectChar(1)== ' ') ){// takes care \\
           accept();
           accept();
           System.out.println("ERROR: INVALID CHAR");
         }else{
          temp = temp + currentChar;
          accept(); tEnd++; 
         }
        }
       accept();
       tEnd++;
         currentSpelling.append(temp);temp = "";return Token.STRINGLITERAL;

         //boolean literals are earlier part of reserved words and ID section
       
    // -------------------Special Tokens -------------------------------
    case SourceFile.eof: 
  tEnd++;
 currentSpelling.append(Token.spell(Token.EOF));
 return Token.EOF;

    default:
 break;
    }

    accept(); 
    System.out.println("The Error Occured on this Char: "+ currentChar + " at: " + lineNum +"("+ tStart + ") .. " + lineNum +"("+ tEnd + ")");
    return Token.ERROR;
  }
//--/-/-/-/-/-/-/--/--/-/-/-/-/-/-/----/-/-/-/-/-/-/--/--/-/-/-/-/-/-/--/--/-/-/-/-/-/-/--/--/-/-/-/-/-/-/--//-/-/-/-/-/-/--/
  void skipSpaceAndComments() {
    if(currentChar == '\\' && inspectChar(1)== 't'){
      accept();
    }
    
      // if comment starts with //
      if((inspectChar(1) == '/' && inspectChar(2) == '/')||(currentChar == '/' && inspectChar(1)=='/')){ //takes care of "//" comments                                           
          while(currentChar != '\n'){
       currentChar = sourceFile.getNextChar();
          } 
      }

      if((inspectChar(1) == '/' && inspectChar(2) == '*')||(currentChar == '/' && inspectChar(1)=='*')){ //takes care of "/*" comments                                                                                                                                                                                                                     
      
          while(!((currentChar == '*') && (inspectChar(1) == '/'))){
       if(currentChar == SourceFile.eof){
         System.out.println("ERROR");
       }
       if(currentChar == '\n'){
         lineNum++;
       }
       tStart++; tEnd++;  charNum++;
       currentChar = sourceFile.getNextChar();
          }
   accept();
   accept(); 
   tStart++; tEnd++;  charNum++;tStart++; tEnd++;  charNum++;
      }
      
//gets rid of white space
      while(currentChar == ' ' || currentChar == '\n'){
 if( currentChar == '\n'){
     lineNum++;
     tStart = 0;
     tEnd = 0;
 }

 tStart++; tEnd++;  charNum++;

      currentChar = sourceFile.getNextChar();
      if((inspectChar(1) == '/' && inspectChar(2) == '/')||(currentChar == '/' && inspectChar(1)=='/')){ //takes care of "//" comments
   while(currentChar != '\n'){
       tStart++; tEnd++;  charNum++;
       currentChar = sourceFile.getNextChar();
   }
      }

      //take care of /* */ comments
      if((inspectChar(1) == '/' && inspectChar(2) == '*')||(currentChar == '/' && inspectChar(1)=='*')){ //takes care of "//" comments                                                                                                                                                                                                                           

          while(!((currentChar == '*') && (inspectChar(1) == '/'))){
            if(currentChar == SourceFile.eof){
              System.out.println("ERROR "+ lineNum +"("+ tStart + ") .. " + lineNum +"("+ tEnd + ") untermintated comment");
              return;
            }
            
       tStart++; tEnd++;  charNum++;
       accept();
                                                                                                                                       
          }
          accept();
          accept();
          tStart++; tEnd++;  charNum++;tStart++; tEnd++;  charNum++;
      }

    }

  }

  public Token getToken() {
    Token tok;
    int kind;

    // skip white space and comments

   skipSpaceAndComments();

  currentSpelling = new StringBuffer("");
 

   sourcePos = new SourcePosition();

   // You must record the position of the current token somehow

   kind = nextToken();
   
   sourcePos.lineStart = lineNum;
   sourcePos.lineFinish = lineNum;
   sourcePos.charStart = tStart;
   sourcePos.charFinish = tEnd-1;//becuse my counter adds an aditional value to sepaertate it from tStart
   
   tStart = tEnd;


   tok = new Token(kind, currentSpelling.toString(), sourcePos);

   // * do not remove these three lines
   if (debug)
     System.out.println(tok);
   return tok;
   }

}