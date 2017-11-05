package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Cawa on 17.07.2017.
 */
public class Variable extends GrammarToken {
    public Variable(String name) {
        super(name);
    }
    public Variable(Integer name) {
        super("w" + name);
    }

    public String toString() {
        return name;
    }

    GrammarToken copy() {
        Variable var = new Variable(name);
        var.num = num;
        return var;
    }

    GrammarToken betaReductionDo(GrammarToken what, String var) {
        if (var.equals(name)) {
            return what;
        }
        return this;
    }

    @Override
    HashSet<String> getLocked() {
        return new HashSet<String>();
    }
    HashSet<String> getVars() {
        HashSet<String> ans = new HashSet<String>();
        ans.add(name);
        return ans;
    }

    @Override
    HashSet<String> getFree(HashSet<String> locked) {
        HashSet<String> ans = new HashSet<>();
        if (!locked.contains(name)) {
            ans.add(name);
        }
        return ans;
    }

    GrammarToken changeLocked(HashSet<String> locked, HashMap<String, String> changed, Counter counter) {
        if (!locked.contains(name)) {
            return this;
        } else {
            if (changed.containsKey(name)) {
                return new Variable(changed.get(name));
            } else  {
                GrammarToken ans = new Variable(nexVar(counter.inc()));
                changed.put(name, ans.name);
                return ans;
            }
        }
    }

    @Override
    GrammarToken simplify(HashMap<String, String> changings, Counter counter) {
        if (!changings.containsKey(name)) {
            GrammarToken gt = new Variable(nexVar(counter.inc()));
            changings.put(name, gt.name);
            return gt;
        } else {
            return new Variable(changings.get(name));
        }
    }

    GrammarToken halfSimplify(HashMap<String, String> changings, Counter counter) {
        if (!changings.containsKey(name)) {
            return this;
        } else {
            return new Variable(changings.get(name));
        }
    }

    @Override
    public GrammarToken betaReductionFind(Counter counter) {
        return this;
    }

    @Override
    public void typeGenerate(HashMap<String, String> map, ArrayList<TypeToken> system) {
        if (!map.containsKey(name)) {
            String typeVar = "t" + map.size();
            map.put(name, typeVar);
        }
        type = new TypeVariable(map.get(name));
    }

    @Override
    TypeToken getType(HashMap<String, TypeToken> typeMap) {
        if (typeMap.keySet().contains(type.name)) {
            return typeMap.get(type.name);
        } else {
            return type.copy();
        }
    }
}
