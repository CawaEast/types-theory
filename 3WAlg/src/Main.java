import grammar.AnsGenerator;
import grammar.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.CLPLexer;
import parser.CLPParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        GrammarToken g;
        try {
            g = getGrammar("task2.in");
        } catch (IOException e) {
            System.out.print("Error in file");
            e.printStackTrace();
            return;
        }
        //System.out.println(g.toString());
        //GrammarToken simpl = g.simplify();
        //GrammarToken ans = simpl.betaReduction().simplify();
        SystemEquations system = g.halfSimplify().getSystem();
        system.solve();
        AnsGenerator generator = new AnsGenerator();
        generator.generate("task2.out", system);
    }

    static GrammarToken getGrammar(String input) throws IOException {
        CLPLexer lexer = new CLPLexer(CharStreams.fromStream(new FileInputStream(new File(input))));
        TokenStream ctk = new CommonTokenStream(lexer);
        CLPParser parser = new CLPParser(ctk);
        CLPParser.StartContext prc = parser.start();
        GrammarToken gt = parser.lambda;
        return gt;
    }
}
