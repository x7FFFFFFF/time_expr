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
 | INTERVAL                                     # interval
 | ID                                           # id
 | INT                                          # int
 | '(' expr ')'                                 # parens
 ;
TIME:  F_TIME ;
DATE:   F_DATE ;
DATETIME:  F_DATE 'T' F_TIME ;
//INTERVAL:  INT UNIT('_'?INT UNIT)* ;
INTERVAL:  PT[0-9]+UNIT([0-9]+UNIT)*;
//fragment F_INTERVAL: [0-9]+ F_UNIT ([0-9]+ F_UNIT)+;

//UNIT: 'microsecond' | 'millisecond' | 'second' | 'minute' |  'hour'  | 'day'  | 'week'| 'month' | 'year'| 'decade' | 'century' | 'millennium';
PT:[Pp][Tt];
UNIT:[Dd]|[Hh]|[Mm]|[Ss];

fragment F_TIME: [0-9]?[0-9] COLON [0-9][0-9] (COLON [0-9][0-9] | COLON [0-9][0-9]'.'[0-9][0-9][0-9])?;
fragment F_DATE: [0-9][0-9][0-9][0-9] '-' [0-9][0-9] '-' [0-9][0-9];


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