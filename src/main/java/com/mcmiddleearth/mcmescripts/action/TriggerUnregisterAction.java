package com.mcmiddleearth.mcmescripts.action;

import com.mcmiddleearth.mcmescripts.debug.DebugManager;
import com.mcmiddleearth.mcmescripts.debug.Modules;
import com.mcmiddleearth.mcmescripts.trigger.Trigger;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

import java.util.HashSet;
import java.util.Set;

public class TriggerUnregisterAction implements Action {

    private final Set<String> triggerNames = new HashSet<>();
    private final Set<Trigger> triggers = new HashSet<>();

    public TriggerUnregisterAction(String triggerName) {
        triggerNames.add(triggerName);
        DebugManager.log(Modules.Action.create(this.getClass()),"Trigger: "+triggerName);
    }

    public TriggerUnregisterAction(Set<String> triggerNames) {
        this.triggerNames.addAll(triggerNames);
        this.triggerNames.forEach(trigger -> DebugManager.log(Modules.Action.create(this.getClass()),
                "Trigger: "+trigger));
    }

    public TriggerUnregisterAction(Trigger trigger) {
        triggers.add(trigger);
        DebugManager.log(Modules.Action.create(this.getClass()),"Trigger: "+trigger.getClass().getSimpleName());
    }

    @Override
    public void execute(TriggerContext context) {
        triggerNames.forEach(name-> context.getScript().getTriggers(name).forEach(Trigger::unregister));
        triggers.forEach(Trigger::unregister);
        DebugManager.log(Modules.Action.execute(this.getClass()),"Unregistering "+ triggerNames.size()+" trigger names.");
    }

}