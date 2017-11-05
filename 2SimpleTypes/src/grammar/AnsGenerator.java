package grammar;


import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Cawa on 17.07.2017.
 */
public class AnsGenerator {

    StringBuilder buffer;

    public void generate(String name, SystemEquations system) {
        try (Writer wr = Files.newBufferedWriter(Paths.get(name))) {
            buffer = new StringBuilder();
            generate(system);
            wr.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void generate(SystemEquations system) {
        if (system.good) {
            writeln(system.type.toString());
            for (String key : system.context.keySet()) {
                writeln(key + " : " + system.context.get(key));
            }
        } else {
            writeln("Лямбда-выражение не имеет типа.");
        }
    }

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
