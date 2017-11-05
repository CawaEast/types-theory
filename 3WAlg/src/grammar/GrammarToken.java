package grammar;

import sun.plugin.dom.css.Counter;

import java.util.*;

/**
 * Created by Cawa on 17.07.2017.
 */
public abstract class GrammarToken {

    Counter c  = null;
    TypeToken type;

    String name;
    GrammarToken first = null, second = null;
    public int num = -1;

    class Counter {
        int i;
        Counter() {
            i = 0;
        }

        Integer inc() {
            return i++;
        }
    }

    GrammarToken(String name) {
        this.name = name;
    }

    GrammarToken(GrammarToken first, GrammarToken second) {
        this.first = first;
        this.second = second;
        this.num = Math.max(first.num, second.num);
    }

    GrammarToken(GrammarToken first) {
        this.first = first;
        this.second = null;
        this.num = first.num;
    }


    abstract GrammarToken copy();
    abstract HashSet<String> getLocked();
    abstract HashSet<String> getVars();
    abstract HashSet<String> getFree(HashSet<String> locked);
    HashSet<String> getFree() {
        return getFree(new HashSet<>());
    }



    public abstract GrammarToken betaReductionFind(Counter counter);
    abstract GrammarToken betaReductionDo(GrammarToken what, String var);
    abstract GrammarToken simplify(HashMap<String, String> changings, Counter counter);
    abstract GrammarToken halfSimplify(HashMap<String, String> changings, Counter counter);
    abstract GrammarToken changeLocked(HashSet<String> locked, HashMap<String, String> changed, Counter counter);

    public abstract void typeGenerate(HashMap<String, String> map, ArrayList<TypeToken> system);
    abstract TypeToken getType(HashMap<String, TypeToken> typeMap);

    public GrammarToken simplify() {
        //System.out.println(toString());
        Counter counter = new Counter();
        GrammarToken ans = simplify(new HashMap<>(), counter);
        //System.out.println(ans.toString());
        ans.c = counter;
        return ans;
    }

    public GrammarToken halfSimplify() {
        //System.out.println(toString());
        Counter counter = new Counter();
        GrammarToken ans = halfSimplify(new HashMap<>(), counter);
        //System.out.println(ans.toString());
        ans.c = counter;
        return ans;
    }

    public GrammarToken betaReduction() {
        GrammarToken tmp = this;
        if (c == null) {
            tmp = simplify();
        }
        String str = tmp.toString();
        Counter counter = tmp.c;
        tmp = tmp.betaReductionFind(counter);
        while(!str.equals(tmp.toString())) {
            str = tmp.toString();
            tmp = tmp.betaReductionFind(counter);
        }
        tmp.c = counter;
        return tmp;
    }


    String nexVar(String str, HashSet<String> used) {
        int i = 0;
        String ans = "w";
        while(used.contains(str)) {
            str = ans + i;
            i++;
        }
        return str;
    }

    String nexVar(int c) {
        return "w" + c;
    }

    String nexType(int c) {
        return "t" + c;
    }

    public SystemEquations getSystem() {
        ArrayList<TypeToken> set = new ArrayList<TypeToken>();
        HashMap<String, String> map = new HashMap<>();
        typeGenerate(map, set);
        return new SystemEquations(set, map, this);
    }
}
