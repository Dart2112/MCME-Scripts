package com.mcmiddleearth.mcmescripts.action;

import com.mcmiddleearth.entities.entities.McmeEntity;
import com.mcmiddleearth.mcmescripts.selector.Selector;
import org.bukkit.util.Vector;

public class EntityVelocityAction extends SelectingAction<McmeEntity> {

    public EntityVelocityAction(Selector<McmeEntity> selector, Vector velocity) {
        super(selector, (entity, context) -> {
            //DebugManager.verbose(Modules.Action.execute(EntityVelocityAction.class),"Velocity added to entity: "+entity.getEntityId() +" "+velocity);
            //Add the provided velocity to the entities current velocity
            entity.setVelocity(entity.getVelocity().add(velocity));
        });
        getDescriptor().indent().addLine("Velocity: " + velocity).outdent();
        //DebugManager.info(Modules.Action.create(this.getClass()),"Selector: "+selector.getSelector()+" Velocity: "+velocity);
    }
}
