package com.mcmiddleearth.mcmescripts.compiler;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;
import com.mcmiddleearth.mcmescripts.bossbattle.EEndOfTimelineBehaviour;
import com.mcmiddleearth.mcmescripts.bossbattle.Timeline;
import com.mcmiddleearth.mcmescripts.bossbattle.TimelineSlot;

import java.util.*;

public class BossBattleCompiler {

    private static final String
            KEY_TIMELINES                           = "timelines",
            KEY_TIMELINE_SLOTS                      = "timeline_slots",
            KEY_TRIGGERS                            = "triggers",
            VALUE_DURATION                          = "duration",
            VALUE_NAME                              = "name",
            VALUE_END_OF_TIMELINE_BEHAVIOUR         = "end_of_timeline_behaviour",
            VALUE_END_OF_TIMELINE_NEXT_TIMELINE     = "end_of_timeline_next_timeline";


    public static BossBattle compile(JsonObject jsonData) {
        JsonElement name = jsonData.get(VALUE_NAME);
        JsonElement timelinesJson = jsonData.get(KEY_TIMELINES);

        BossBattle bossBattle = new BossBattle(name.getAsString());
        bossBattle.setTimelines(new ArrayList<>(compileTimelines(timelinesJson, bossBattle)));

        return bossBattle;
    }

    private static List<Timeline> compileTimelines(JsonElement timelinesData, BossBattle bossBattle) {
        ArrayList<Timeline> timelines = new ArrayList<>();
        if(timelinesData==null) return timelines;
        if(timelinesData.isJsonArray()) {
            for(int i = 0; i< timelinesData.getAsJsonArray().size(); i++) {
                timelines.add(compileTimeline(timelinesData.getAsJsonArray().get(i).getAsJsonObject(),bossBattle));
            }
        } else {
            timelines.add(compileTimeline(timelinesData.getAsJsonObject(),bossBattle));
        }
        return timelines;
    }

    private static List<TimelineSlot> compileTimelineSlots(JsonElement timelineSlotsData, BossBattle bossBattle) {
        ArrayList<TimelineSlot> timelineSlots = new ArrayList<>();
        if(timelineSlotsData==null) return timelineSlots;
        if(timelineSlotsData.isJsonArray()) {
            for(int i = 0; i< timelineSlotsData.getAsJsonArray().size(); i++) {
                timelineSlots.add(compileTimelineSlot(timelineSlotsData.getAsJsonArray().get(i).getAsJsonObject(),bossBattle));
            }
        } else {
            timelineSlots.add(compileTimelineSlot(timelineSlotsData.getAsJsonObject(),bossBattle));
        }
        return timelineSlots;
    }

    private static TimelineSlot compileTimelineSlot(JsonObject jsonObject, BossBattle bossBattle) {
        JsonElement name = jsonObject.get(VALUE_NAME);
        JsonElement duration = jsonObject.get(VALUE_DURATION);
        JsonElement triggers = jsonObject.get(KEY_TRIGGERS);

        return new TimelineSlot(
                name.getAsString(),
                bossBattle,
                duration.getAsDouble(),
                triggers.getAsJsonArray());
    }

    private static Timeline compileTimeline(JsonObject jsonObject, BossBattle bossBattle) {
        JsonElement name = jsonObject.get(VALUE_NAME);
        JsonElement duration = jsonObject.get(VALUE_DURATION);
        JsonElement endOfTimelineBehaviour = jsonObject.get(VALUE_END_OF_TIMELINE_BEHAVIOUR);
        JsonElement endOfTimelineBehaviourTimeline = jsonObject.get(VALUE_END_OF_TIMELINE_NEXT_TIMELINE);
        JsonElement timelineSlotsJson = jsonObject.get(KEY_TIMELINE_SLOTS);
        List<TimelineSlot> timelineSlots = new ArrayList<>(compileTimelineSlots(timelineSlotsJson,bossBattle));

        return new Timeline(name.getAsString(),
                bossBattle,
                timelineSlots,
                duration.getAsDouble(),
                EEndOfTimelineBehaviour.valueOf(endOfTimelineBehaviour.getAsString()),
                endOfTimelineBehaviourTimeline.getAsString());

    }
}
