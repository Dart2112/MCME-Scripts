package com.mcmiddleearth.mcmescripts.action.bossbattle;

import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;
import com.mcmiddleearth.mcmescripts.quest.tags.AbstractTag;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

public class TagSetAction extends BossBattleAction {

    private final AbstractTag<?> tag;

    public TagSetAction(AbstractTag<?> tag) {
        this.tag = tag;
    }

    @Override
    protected void handler(BossBattle bossBattle, TriggerContext context) {
        bossBattle.setTag(tag);
    }
}
