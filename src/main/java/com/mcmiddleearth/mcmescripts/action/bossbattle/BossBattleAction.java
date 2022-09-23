package com.mcmiddleearth.mcmescripts.action.bossbattle;

import com.mcmiddleearth.mcmescripts.action.Action;
import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

public abstract class BossBattleAction extends Action {

    @Override
    protected final void handler(TriggerContext context) {
        handler(context.getTriggerContainer().getBossBattle(), context);
    }

    protected abstract void handler(BossBattle bossBattle, TriggerContext context);
}
