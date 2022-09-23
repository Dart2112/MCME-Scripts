package com.mcmiddleearth.mcmescripts.trigger;

import com.mcmiddleearth.entities.EntitiesPlugin;
import com.mcmiddleearth.entities.events.listener.McmeEventListener;
import com.mcmiddleearth.mcmescripts.MCMEScripts;
import com.mcmiddleearth.mcmescripts.action.Action;

public abstract class EntitiesEventTrigger extends EventTrigger implements McmeEventListener {

    public EntitiesEventTrigger(Action action) {
        super(action);
    }

    @Override
    public void register(ITriggerContainer triggerContainer) {
        super.register(triggerContainer);
        EntitiesPlugin.getEntityServer().registerEvents(MCMEScripts.getInstance(),this);
    }

    @Override
    public void unregister() {
        super.unregister();
        EntitiesPlugin.getEntityServer().unregisterEvents(MCMEScripts.getInstance(),this);
    }
}
