package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Cawa on 24.08.2017.
 */
public class SystemEquations {
    public HashMap<String, String> types;
    public HashMap<String, TypeToken> variables;
    List<TypeToken> system;
    GrammarToken grammarToken;
    TypeToken type = null;
    public HashMap<String, TypeToken> context;

    boolean good = true;

    public SystemEquations(ArrayList<TypeToken> list, HashMap<String, String> t, GrammarToken gt) {
        types = t;
        grammarToken = gt;
        system = list;
        variables = new HashMap<String, TypeToken>();
    }

    public void solve() {
        while (good && (system.size() > 0)) {
            findAeA();
            findVeE();
        }
        simplify();
        if (good) {
            type = grammarToken.getType(variables);
            findContext();
        }
    }

    void findContext() {
        context = new HashMap<String, TypeToken>();
        HashSet<String> free = grammarToken.getFree();
        for (String key : free) {
            if (variables.containsKey(key)) {
                context.put(key, variables.get(key));
            } else {
                context.put(key, new TypeVariable(types.get(key)));
            }
        }
    }

    HashMap<String, TypeToken> findAeA() {
        List<TypeToken> list = system;
        for (int i = 0; i < list.size(); i++) {
            TypeToken token = list.get(i);
            if (token.first.toString().equals(token.second.toString())) {
                list.remove(i);
                i--;
            }
        }
        return variables;
    }

    void findVeE() {
        List<TypeToken> oldSys = system;
        system = new ArrayList<>();
        for (TypeToken token : oldSys) {
            if (token.first.isVar()) {
                String var = token.first.name;
                if (token.second.containsVar(var)) {
                    good = false;
                    return;
                }
                if (variables.containsKey(var)) {
                    addEquals(variables.get(var), token.second);
                } else {
                    variables.put(var, token.second);
                }
            } else {
                TypeToken a = token.first;
                TypeToken b = token.second;
                addEquals(a.first, b.first);
                addEquals(a.second, b.second);
            }
        }
    }

    void addEquals(TypeToken a, TypeToken b) {
        if (a.isVar()) {
            system.add(new Equals(a, b));
        } else {
            system.add(new Equals(b, a));
        }
    }

    void simplify() {
        boolean nothingNew = false;
        while (!nothingNew) {
            nothingNew = true;
            for (String key : variables.keySet()) {
                TypeToken type = variables.get(key);
                for (String otherKey : variables.keySet()) {
                    if (!key.equals(otherKey)) {
                        TypeToken otherType = variables.get(otherKey);
                        if (otherType.containsVar(key)) {
                            if (type.containsVar(otherKey)) {
                                if (type.name.equals(otherKey)) {
                                    variables.put(otherKey, new TypeVariable(otherKey));
                                    break;
                                } else {
                                    good = false;
                                    return;
                                }
                            } else {
                                //nothingNew = false;
                                TypeToken tt = otherType.insert(key, type);
                                variables.put(otherKey, tt);
                            }
                        }
                    }
                }
            }
        }
    }

}
