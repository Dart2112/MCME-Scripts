package com.mcmiddleearth.mcmescripts.trigger;

import com.mcmiddleearth.mcmescripts.IEntityContainer;
import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface ITriggerContainer {

    String getName();
    void addTrigger(Trigger trigger);
    void removeTrigger(Trigger trigger);

    Set<Trigger> getTriggers();

    default IEntityContainer getEntityContainer() {
        return (IEntityContainer) this;
    }

    default BossBattle getBossBattle() {
        return (BossBattle) this;
    }

    /***
     * Get triggers with a specific name. It is also possible to specify the beginning of a word using '*' to indicate a wildcard. For instance "foo*" will also return both "foo" and "foobar"
     * @param name name to match
     * @return list of triggers matching name
     */
    default Set<Trigger> getTriggers(String name) {
        Set<Trigger> triggers = getTriggers();
        if(name.equals("*")) return new HashSet<>(triggers);
        if(name.endsWith("*")) {
            return triggers.stream().filter(trigger->trigger.getName()!=null
                            && trigger.getName().startsWith(name.substring(0,name.length()-1)))
                    .collect(Collectors.toSet());
        } else {
            return triggers.stream().filter(trigger->trigger.getName()!=null && trigger.getName().equals(name))
                    .collect(Collectors.toSet());
        }
    }
}
