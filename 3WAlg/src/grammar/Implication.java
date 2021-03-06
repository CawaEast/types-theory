package grammar;

import java.util.HashSet;

/**
 * Created by Cawa on 23.08.2017.
 */
public class Implication extends TypeToken {

    Implication(TypeToken f, TypeToken s) {
        super(f, s);
        name = "->";
    }

    @Override
    public String toString() {
        String ans;
        if (first.name.equals("->")) {
            ans = "(" +first.toString() + ")";
        } else  {
            ans = first.toString();
        }
        ans += name + second.toString();
        return ans;
    }

    @Override
    void getLeft(HashSet<String> left) {
        first.getLeft(left);
        second.getLeft(left);
    }

    @Override
    boolean checkRight(HashSet<String> left) {
        return first.checkRight(left) && second.checkRight(left);
    }

    @Override
    boolean containsVar(String left) {
        return first.containsVar(left) || second.containsVar(left);
    }

    @Override
    TypeToken insert(String var, TypeToken typeToken) {
        if (first.name.equals(var)) {
            first = typeToken;
        } else {
            first = first.insert(var, typeToken);
        }
        if (second.name.equals(var)) {
            second = typeToken;
        } else {
            second = second.insert(var, typeToken);
        }
        return this;
    }

    @Override
    TypeToken copy() {
        return new Implication(first.copy(), second.copy());
    }

}
