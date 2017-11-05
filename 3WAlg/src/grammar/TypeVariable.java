package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 24.08.2017.
 */
public class TypeVariable extends TypeToken{
    public TypeVariable(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    boolean checkRight(HashSet<String> left) {
        return !left.contains(name);
    }


    boolean containsVar(String left) {
        return left.equals(name);
    }

    @Override
    void getLeft(HashSet<String> left) {
        left.add(name);
    }

    @Override
    TypeToken insert(String var, TypeToken typeToken) {
        if (var.equals(name)) {
            return typeToken;
        } else {
            return this;
        }
    }

    @Override
    TypeToken copy() {
        return new TypeVariable(name);
    }
}
