package com.mcmiddleearth.mcmescripts.bossbattle;

import org.bukkit.entity.Boss;

import java.util.List;

public class Timeline {

    private final String name;
    private final List<TimelineSlot> slots;
    private final EEndOfTimelineBehaviour endOfTimelineBehaviour;
    private final String endOfTimelineNextTimelineName;

    private final double duration;

    private final BossBattle bossBattle;

    public Timeline(String name, BossBattle bossBattle, List<TimelineSlot> slots, double duration, EEndOfTimelineBehaviour endOfTimelineBehaviour, String endOfTimelineNextTimelineName) {
        this.bossBattle = bossBattle;
        this.name = name;
        this.slots = slots;
        this.endOfTimelineBehaviour = endOfTimelineBehaviour;
        this.duration = duration;
        this.endOfTimelineNextTimelineName = endOfTimelineNextTimelineName;
    }

    public Timeline(String name, BossBattle bossBattle, List<TimelineSlot> slots, double duration, EEndOfTimelineBehaviour endOfTimelineBehaviour) {
        this(name, bossBattle, slots,duration,endOfTimelineBehaviour,"");
    }

    public String getName(){
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public List<TimelineSlot> getSlots(){
        return slots;
    }

    public EEndOfTimelineBehaviour getEndOfTimelineBehaviour() {
        return endOfTimelineBehaviour;
    }

    public String getEndOfTimelineNextTimelineName() {
        return endOfTimelineNextTimelineName;
    }

    public BossBattle getBossBattle(){
        return bossBattle;
    }
}
