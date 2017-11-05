package grammar;


import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Cawa on 17.07.2017.
 */
public class AnsGenerator {

    StringBuilder buffer;

    public void generate(String name, GrammarToken token) {
        try (Writer wr = Files.newBufferedWriter(Paths.get(name))) {
            buffer = new StringBuilder();
            write(token.toString());
            wr.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    void generate(Grammar grammar) {
        boolean b = true;
        for (Expression expr : grammar.g) {
            if (b) {
                write(expr.head.toString());
                b = false;
            } else {
                write( ", " + expr.head.toString());
            }
        }
        write("|-");
        writeln(grammar.toProves.head.toString());
        for (int i = 0; i < grammar.prove.size(); i++) {
            Expression e = grammar.prove.get(i);
            writeln("(" + (i + 1) + ") " + e.toString());
        }
    }*/

    void write(String str) {
        buffer.append(str);
    }

    void writeln(String str) {
        write(str + System.lineSeparator());
    }

    void writeln() {
        writeln("");
    }
}
