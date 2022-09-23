package com.mcmiddleearth.mcmescripts.action.bossbattle;

import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

public class TagDeleteAction extends BossBattleAction {

    private final String tagName;

    public TagDeleteAction(String tagName) {
        this.tagName = tagName;
    }

    @Override
    protected void handler(BossBattle bossBattle, TriggerContext context) {
        bossBattle.deleteTag(tagName);
    }
}
