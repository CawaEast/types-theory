package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Cawa on 10.08.2017.
 */
public class Lambda extends GrammarToken{
    public Lambda(GrammarToken var, GrammarToken expr) {
        super(var, expr);
        name = "\\";
    }

    GrammarToken copy() {
        return new Lambda(first.copy(),second.copy());
    }

    GrammarToken betaReductionDo(GrammarToken what, String var) {

        if (var.equals("")) {
            GrammarToken ans = second.betaReductionDo(what, first.name);
            return ans;
        } else {
            GrammarToken newSecond = second.betaReductionDo(what, var);
            return new Lambda(first, newSecond);

        }
    }


    public String toString() {
        return  "(" + name + first.toString() + "." + second.toString() + ")";
    }

    @Override
    HashSet<String> getLocked() {
        HashSet<String> locked = second.getLocked();
        locked.add(first.name);
        return locked;
    }

    @Override
    HashSet<String> getFree(HashSet<String> locked) {
        if (locked.contains(first.name)) {
            return second.getFree(locked);
        } else {
            locked.add(first.name);
            HashSet<String> ans = second.getFree(locked);
            locked.remove(first.name);
            return ans;
        }
    }

    HashSet<String> getVars() {
        HashSet<String> locked = second.getVars();
        locked.add(first.name);
        return locked;
    }

    GrammarToken changeLocked(HashSet<String> locked, HashMap<String, String> changed, Counter counter) {
        return new Lambda(first.changeLocked(locked, changed, counter), second.changeLocked(locked, changed, counter));
    }



    @Override
    GrammarToken simplify(HashMap<String, String> changings, Counter counter) {
        String tmp = "";
        if(changings.keySet().contains(first.name)) {
            tmp = changings.get(first.name);
        }
        GrammarToken firstNew = new Variable(nexVar(counter.inc()));
        changings.put(first.name, firstNew.name);
        GrammarToken secondNew = second.simplify(changings, counter);
        if (tmp.equals("")) {
            changings.remove(first.name);
        } else {
            changings.put(first.name, tmp);
        }
        return new Lambda(firstNew, secondNew);
    }

    @Override
    GrammarToken halfSimplify(HashMap<String, String> changings, Counter counter) {
        String tmp = "";
        if(changings.keySet().contains(first.name)) {
            tmp = changings.get(first.name);
        }
        GrammarToken firstNew = new Variable(nexVar(counter.inc()));
        changings.put(first.name, firstNew.name);
        GrammarToken secondNew = second.halfSimplify(changings, counter);
        if (tmp.equals("")) {
            changings.remove(first.name);
        } else {
            changings.put(first.name, tmp);
        }
        return new Lambda(firstNew, secondNew);
    }

    @Override
    public GrammarToken betaReductionFind(Counter counter) {
        return new Lambda(first, second.betaReductionFind(counter));
    }

    @Override
    public void typeGenerate(HashMap<String, String> map, ArrayList<TypeToken> system) {
        first.typeGenerate(map, system);
        second.typeGenerate(map, system);
        type = new Implication(first.type, second.type);
    }

    TypeToken getType(HashMap<String, TypeToken> typeMap) {
        return new Implication(first.getType(typeMap), second.getType(typeMap));
    }
}
