package com.mcmiddleearth.mcmescripts.trigger.timed;

import com.mcmiddleearth.mcmescripts.MCMEScripts;
import com.mcmiddleearth.mcmescripts.action.Action;

public class PeriodicServerTimeTrigger extends PeriodicTrigger {

    public PeriodicServerTimeTrigger(Action action, long timeMillis) {
        super(action, timeMillis);
        //DebugManager.info(Modules.Trigger.create(this.getClass()),
        //        "Time: "+timeMillis+" Action: " + (action!=null?action.getClass().getSimpleName():null));
    }

    @Override
    public long getCurrentTime() {
        return MCMEScripts.getCurrentTick();

    }
}
