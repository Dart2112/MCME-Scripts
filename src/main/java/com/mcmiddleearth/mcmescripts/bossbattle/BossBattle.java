package com.mcmiddleearth.mcmescripts.bossbattle;

import com.mcmiddleearth.entities.entities.McmeEntity;
import com.mcmiddleearth.mcmescripts.IEntityContainer;
import com.mcmiddleearth.mcmescripts.action.bossbattle.TimelineSetAction;
import com.mcmiddleearth.mcmescripts.quest.tags.AbstractTag;
import com.mcmiddleearth.mcmescripts.trigger.DecisionTreeTrigger;
import com.mcmiddleearth.mcmescripts.trigger.ITriggerContainer;
import com.mcmiddleearth.mcmescripts.trigger.Trigger;
import com.mcmiddleearth.mcmescripts.trigger.timed.OnceRealTimeTrigger;

import java.util.*;

/**
 * Represents a quest of a party of players. If there are two parties doing the same quest at the same time
 * there will be two quest objects. One for each party.
 *
 * A quest consists of one or several stages. Each stage is a script. If a stage is enabled it can be triggered and loaded
 * like any script depending on given conditions.
 */
public class BossBattle implements ITriggerContainer, IEntityContainer {

    private final String name;
    private final Map<String,Timeline> timelines = new HashMap<>();
    private final Map<String, AbstractTag<?>> tags = new HashMap<>();

    private final Set<McmeEntity> entities = new HashSet<>();

    private String currentTimelineName;
    private int currentTimelineSlotIndex = 0;

    /**
     * Triggers for switching between timeline slots
     */
    private Set<Trigger> timelineTriggers;

    public BossBattle(String name){
        this.name = name;
    }

    public BossBattle(String name, List<Timeline> timelinesList){
        this.name = name;
        setTimelines(timelinesList);
    }

    public void setTimelines(List<Timeline> timelinesList){
        timelines.clear();
        for (Timeline t : timelinesList) {
            timelines.put(t.getName(),t);
        }
        updateActiveTimeline(timelinesList.get(0).getName(),0);
    }

    public void updateActiveTimeline(String newTimelineName, int newSlotIndex) {

        // Deactivate triggers associated with the old timeline slot
        timelines.get(currentTimelineName).getSlots().get(currentTimelineSlotIndex).deactivateTriggers();

        currentTimelineSlotIndex = newSlotIndex;
        currentTimelineName = newTimelineName;

        // Activate triggers associated with the new timeline slot
        timelines.get(currentTimelineName).getSlots().get(currentTimelineSlotIndex).activateTriggers();

        queueNextTimelineConfiguration();
    }

    private void queueNextTimelineConfiguration(){
        Timeline currentTimeline = timelines.get(currentTimelineName);

        currentTimelineSlotIndex++;

        int targetTimelineSlotIndex = currentTimelineSlotIndex + 1;
        String targetTimelineName = "";

        // If the current timeline slot is the last timeline slot of the timeline, the timeline's end behaviour determines what happens next
        if(currentTimelineSlotIndex >= currentTimeline.getSlots().size()){

            switch (currentTimeline.getEndOfTimelineBehaviour()){
                case CONTINUE       -> targetTimelineSlotIndex = currentTimelineSlotIndex;
                case LOOP           -> targetTimelineSlotIndex = 0;
                case GO_TO_TIMELINE -> {
                    targetTimelineName = currentTimeline.getEndOfTimelineNextTimelineName();
                    targetTimelineSlotIndex = 0;
                }
            }
        }
        queueTimelineConfiguration(targetTimelineName,targetTimelineSlotIndex);
    }

    private void queueTimelineConfiguration(String targetTimelineName, int targetTimelineSlotIndex){
        Timeline currentTimeline = timelines.get(currentTimelineName);

        // Set up an event for switching the current timeline
        long time = System.currentTimeMillis() + (long) currentTimeline.getDuration();
        DecisionTreeTrigger trigger = new OnceRealTimeTrigger(new TimelineSetAction(targetTimelineName,targetTimelineSlotIndex), time);
        trigger.setCallOnce(true);
        trigger.register(this);
    }

    public void setTag(AbstractTag<?> tag) {
        tags.put(tag.getName(), tag);
    }

    public void deleteTag(String name) {
        tags.remove(name);
    }

    public boolean hasTag(String name) {
        return tags.containsKey(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addTrigger(Trigger trigger) {
        timelineTriggers.add(trigger);
    }

    @Override
    public void removeTrigger(Trigger trigger) {
        timelineTriggers.remove(trigger);
    }

    @Override
    public IEntityContainer getEntityContainer() {
        return this;
    }

    @Override
    public Set<Trigger> getTriggers() {
        return timelineTriggers;
    }

    @Override
    public void addEntity(McmeEntity entity) {
        entities.add(entity);
    }

    @Override
    public void removeEntity(McmeEntity entity) {
        entities.remove(entity);
    }
}
