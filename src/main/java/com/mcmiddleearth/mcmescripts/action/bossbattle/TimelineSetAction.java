package com.mcmiddleearth.mcmescripts.action.bossbattle;

import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;
import com.mcmiddleearth.mcmescripts.trigger.TriggerContext;

public class TimelineSetAction extends BossBattleAction{

    private final int targetSlotIndex;
    private final String targetTimelineName;
    public TimelineSetAction(String targetTimelineName, int targetSlotIndex) {
        this.targetSlotIndex = targetSlotIndex;
        this.targetTimelineName = targetTimelineName;
        getDescriptor().indent().addLine("Timeline: " + targetTimelineName).addLine("Slot: " + targetSlotIndex).outdent();
    }

    @Override
    protected void handler(BossBattle instance, TriggerContext context) {
        instance.updateActiveTimeline(targetTimelineName,targetSlotIndex);
    }
}
