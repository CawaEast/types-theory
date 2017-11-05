package grammar;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Cawa on 10.08.2017.
 */
public class Application extends GrammarToken {

    public Application(GrammarToken first, GrammarToken second) {
        super(first, second);
        name = " ";
    }

    @Override
    public GrammarToken betaReductionFind(Counter counter) {
        if (first.name.equals("\\")) {
            GrammarToken gt = second.changeLocked(first.getLocked(), new HashMap<>(), counter);
            return first.betaReductionDo(gt, "");
        }
        GrammarToken firstNew = first.betaReductionFind(counter);
        String str = "";
        while (!str.equals(firstNew.toString())) {
            str = firstNew.toString();
            firstNew = firstNew.betaReductionFind(counter);
            if (firstNew.name.equals("\\")) {
                break;
            }
        }
        if (firstNew.name.equals("\\")) {
            GrammarToken gt = second.changeLocked(firstNew.getLocked(), new HashMap<>(), counter);
            return firstNew.betaReductionDo(gt, "");
        }
        GrammarToken secondNew = second.betaReductionFind(counter);
        return new Application(firstNew, secondNew);
    }


    GrammarToken betaReductionDo(GrammarToken what, String var) {
        GrammarToken newFirst = first.betaReductionDo(what, var);
        GrammarToken newSecond = second.betaReductionDo(what, var);
        return new Application(newFirst, newSecond);
    }

    GrammarToken copy() {
        return new Application(first.copy(),second.copy());
    }

    public String toString() {
        return "(" + first.toString() + " " + second.toString() + ")";
    }

    @Override
    HashSet<String> getLocked() {
        HashSet<String> ans = first.getLocked();
        ans.addAll(second.getLocked());
        return ans;
    }

    HashSet<String> getVars() {
        HashSet<String> ans = first.getVars();
        ans.addAll(second.getVars());
        return ans;
    }

    GrammarToken changeLocked(HashSet<String> locked, HashMap<String, String> changed, Counter counter) {
        return new Application(first.changeLocked(locked, changed, counter), second.changeLocked(locked, changed, counter));
    }

    @Override
    GrammarToken simplify(HashMap<String, String> changings, Counter counter) {
        return new Application(first.simplify(changings, counter), second.simplify(changings, counter));
    }
}
