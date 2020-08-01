grammar TimeExpr;

prog: stat+;

stat: expr NEWLINE          # printExpr
| ID '=' expr NEWLINE       # assign
| NEWLINE                   # blank
 ;
 expr: expr  op=(ADD|SUB) expr                  # AddSub
 | DATETIME                                     # datetime
 | DATE                                         # date
 | TIME                                         # time
 | ID                                           # id
 | INT                                          # int
 | '(' expr ')'               # parens
 ;
TIME: QUOTE FTIME QUOTE;
DATE:  QUOTE FDATE QUOTE;
DATETIME: QUOTE FDATE 'T' FTIME QUOTE;

fragment FTIME: [0-9]?[0-9] COLON [0-9][0-9] (COLON [0-9][0-9] | COLON [0-9][0-9]'.'[0-9][0-9][0-9])?;
fragment FDATE: [0-9][0-9][0-9][0-9] '-' [0-9][0-9] '-' [0-9][0-9];


//RESERVED: 'date' | 'time' | 'datetime' | 'interval';
//DATE_TYPE:  'date';
ID              : LETTER (LETTER | DIGIT)* ;
INT : [0-9]+ ; // match integers
fragment DIGIT  : '0'..'9' ;
fragment LETTER : 'a'..'z' | 'A'..'Z' ;





fragment QUOTE : '\'';
ADD : '+' ;
SUB : '-';
/*fragment PARENTHESIS1:'(';
fragment PARENTHESIS2:')';*/
fragment COLON: ':';
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal)
WS : [ \t]+ -> skip;