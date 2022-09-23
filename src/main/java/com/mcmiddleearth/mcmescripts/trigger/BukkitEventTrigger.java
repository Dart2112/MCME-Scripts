package com.mcmiddleearth.mcmescripts.trigger;

import com.mcmiddleearth.mcmescripts.MCMEScripts;
import com.mcmiddleearth.mcmescripts.action.Action;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class BukkitEventTrigger extends EventTrigger implements Listener {

    public BukkitEventTrigger(Action action) {
        super(action);
    }

    @Override
    public void register(ITriggerContainer triggerContainer) {
        Bukkit.getPluginManager().registerEvents(this, MCMEScripts.getInstance());
        super.register(triggerContainer);
    }

    @Override
    public void unregister() {
        HandlerList.unregisterAll(this);
        super.unregister();
    }
}
