grammar CLP;
@header {
    import grammar.*;
    import java.util.Vector;
    import java.util.HashMap;
    import java.util.HashSet;
}

@members {
    int counter = 0;
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public GrammarToken lambda;
    static void wr(String x) {System.out.print(x);}
    static void tabs(int amount) {for(int i=0; i<amount; i++) System.out.print(' ');}
}

start
    : e = let{lambda = $e.rgt; lambda.num = counter;} EOF
    ;

let returns [GrammarToken rgt]
    :   'let' v1 = Name '=' l1 = let 'in' l2 = let {$rgt = new Let($l1.rgt, $l2.rgt, new Variable($v1.text));}
    |   e = expression{$rgt = $e.rgt;}
    ;

expression returns [GrammarToken rgt]
//    :   a1 = application '\\' v1 = Name '.' e1 = expression{$rgt = new Application($a1.rgt, new Lambda(new Variable($v1.text), $e1.rgt));}
//    |   '\\' n = Name '.' e2 = expression {if (!map.containsKey($n.text)) {map.put($n.text, counter++); } $rgt = new Lambda(new Variable(map.get($n.text)), $e2.rgt);}
    :   a1 = application '\\' v1 = Name '.' e1 = expression{$rgt = new Application($a1.rgt, new Lambda(new Variable($v1.text), $e1.rgt));}
    |   '\\' n = Name '.' e2 = expression {$rgt = new Lambda(new Variable($n.text), $e2.rgt);}
    |   a3 = application{$rgt = $a3.rgt;}
    ;


application  returns [GrammarToken rgt]
    :   ap1 = application at1 = atom{$rgt = new Application($ap1.rgt, $at1.rgt);}
    |   a2 = atom{$rgt = $a2.rgt;}
    ;

atom returns [GrammarToken rgt]
//    :  n = Name{if (!map.containsKey($n.text)) {map.put($n.text, counter++); } $rgt = new Variable(map.get($n.text));}
    :  n = Name{$rgt = new Variable($n.text);}
    |   '(' b = let{$rgt = $b.rgt;} ')'
    ;

Name
    : [a-z]([a-z0-9'_])*
    ;

Whitespace
    :   [ \t\r]+ -> skip;