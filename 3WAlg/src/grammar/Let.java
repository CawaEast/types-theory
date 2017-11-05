package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Cawa on 27.08.2017.
 */
public class Let extends GrammarToken {
    GrammarToken var;
    public Let(GrammarToken first, GrammarToken second, GrammarToken varA) {
        super(first, second);
        name = "let";
        var = varA;
    }

    @Override
    HashSet<String> getVars() {
        HashSet<String> vars = first.getVars();
        vars.addAll(second.getVars());
        vars.add(var.name);
        return vars;
    }

    @Override
    HashSet<String> getFree(HashSet<String> locked) {
        HashSet<String> vars = first.getFree(locked);
        vars.addAll(second.getFree(locked));
        return vars;
    }

    @Override
    GrammarToken changeLocked(HashSet<String> locked, HashMap<String, String> changed, Counter counter) {
        return new Let(first.changeLocked(locked, changed, counter), second.changeLocked(locked, changed, counter), var.changeLocked(locked, changed, counter));
    }

    @Override
    GrammarToken halfSimplify(HashMap<String, String> changings, Counter counter) {
        String tmp = "";
        if(changings.keySet().contains(var.name)) {
            tmp = changings.get(var.name);
        }
        GrammarToken variable = new Variable(nexVar(counter.inc()));
        changings.put(variable.name, variable.name);
        GrammarToken secondNew = second.halfSimplify(changings, counter);
        GrammarToken firstNew = first.halfSimplify(changings, counter);
        if (tmp.equals("")) {
            changings.remove(var.name);
        } else {
            changings.put(var.name, tmp);
        }
        return new Let(firstNew, secondNew, variable);
    }

    GrammarToken simplify(HashMap<String, String> changings, Counter counter) {
        String tmp = "";
        if(changings.keySet().contains(var.name)) {
            tmp = changings.get(var.name);
        }
        GrammarToken variable = new Variable(nexVar(counter.inc()));
        changings.put(variable.name, variable.name);
        GrammarToken secondNew = second.halfSimplify(changings, counter);
        GrammarToken firstNew = first.halfSimplify(changings, counter);
        if (tmp.equals("")) {
            changings.remove(var.name);
        } else {
            changings.put(var.name, tmp);
        }
        return new Let(firstNew, secondNew, variable);
    }

    public GrammarToken betaReductionFind(Counter counter) {
        return new Let(first.betaReductionFind(counter), second.betaReductionFind(counter), var);
    }

    @Override
    public void typeGenerate(HashMap<String, String> map, ArrayList<TypeToken> system) {
        first.typeGenerate(map, system);
        second.typeGenerate(map, system);
        var.typeGenerate(map, system);
        system.add(new Equals(first.type, var.type));
    }

    @Override
    TypeToken getType(HashMap<String, TypeToken> typeMap) {
        if (typeMap.keySet().contains(type.name)) {
            return typeMap.get(type.name);
        } else {
            return type.copy();
        }
    }

    @Override
    GrammarToken copy() {
        return new Let(first.copy(), second.copy(), var.copy());
    }

    @Override
    GrammarToken betaReductionDo(GrammarToken what, String varS) {
        GrammarToken newFirst = first.betaReductionDo(what, varS);
        GrammarToken newSecond = second.betaReductionDo(what, varS);
        return new Let(newFirst, newSecond, var);
    }

    @Override
    HashSet<String> getLocked() {
        HashSet<String> locked = second.getLocked();
        locked.addAll(first.getLocked());
        locked.add(var.name);
        return locked;
    }

    @Override
    public String toString() {
        return "let " + var.toString() + " " + first.toString() + "in" + second.toString();
    }
}
