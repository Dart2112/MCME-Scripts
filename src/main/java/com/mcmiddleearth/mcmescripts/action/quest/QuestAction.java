package com.mcmiddleearth.mcmescripts.action.quest;

import com.mcmiddleearth.mcmescripts.action.Action;
import com.mcmiddleearth.mcmescripts.quest.Quest;
import com.mcmiddleearth.mcmescripts.quest.Stage;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

public abstract class QuestAction extends Action {

    @Override
    protected final void handler(TriggerContext context) {
        if(context.getTriggerContainer() instanceof Stage) {
            handler(((Stage) context.getTriggerContainer()).getQuest(), context);
        }
    }

    protected abstract void handler(Quest quest, TriggerContext context);
}
