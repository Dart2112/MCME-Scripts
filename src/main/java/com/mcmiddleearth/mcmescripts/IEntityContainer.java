package com.mcmiddleearth.mcmescripts;

import com.mcmiddleearth.entities.entities.McmeEntity;
import com.mcmiddleearth.mcmescripts.trigger.Trigger;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface IEntityContainer {

    void addEntity(McmeEntity entity);

    void removeEntity(McmeEntity entity);
}
