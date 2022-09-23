package com.mcmiddleearth.mcmescripts.bossbattle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mcmiddleearth.mcmescripts.IEntityContainer;
import com.mcmiddleearth.mcmescripts.compiler.TriggerCompiler;
import com.mcmiddleearth.mcmescripts.trigger.ITriggerContainer;
import com.mcmiddleearth.mcmescripts.trigger.Trigger;

import java.util.HashSet;
import java.util.Set;

public class TimelineSlot implements ITriggerContainer {

    private final String name;
    private final double duration;

    private boolean isActive;

    // TODO: Does it make more sense to simply keep all triggers on the boss battle object? I'm not exactly sure yet.
    private Set<Trigger> triggers = new HashSet<>();

    private final JsonElement triggerJson;

    private final BossBattle bossBattle;

    public TimelineSlot(String name, BossBattle bossBattle, double duration, JsonElement triggerJson) {
        this.bossBattle = bossBattle;
        this.name = name;
        this.duration = duration;
        this.triggerJson = triggerJson;
    }

    public void activateTriggers(){
        // Triggers must be recompiled every time since not all triggers are stateless.
        triggers = TriggerCompiler.compileTriggers(triggerJson);

        triggers.forEach(trigger -> trigger.register(this));
    }

    public void deactivateTriggers(){
        triggers.forEach(Trigger::unregister);
        triggers.clear();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addTrigger(Trigger trigger) {
        triggers.add(trigger);
    }

    @Override
    public void removeTrigger(Trigger trigger) {
        triggers.remove(trigger);
    }

    @Override
    public IEntityContainer getEntityContainer() {
        return bossBattle;
    }

    @Override
    public BossBattle getBossBattle() {
        return bossBattle;
    }

    @Override
    public Set<Trigger> getTriggers() {
        return triggers;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isActive() {
        return isActive;
    }
}
