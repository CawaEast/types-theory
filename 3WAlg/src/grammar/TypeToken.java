package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 23.08.2017.
 */
public abstract class TypeToken {
    TypeToken first;
    TypeToken second;
    String name;

    TypeToken(TypeToken f, TypeToken s) {
        first = f;
        second = s;
    }

    boolean isVar() {
        return !(name.equals("=") || name.equals("->"));
    }

    TypeToken() {}

    abstract void getLeft(HashSet<String> left);
    abstract boolean checkRight(HashSet<String> left);
    abstract boolean containsVar(String left);
    abstract TypeToken insert(String var, TypeToken typeToken);
    abstract TypeToken copy();
}
