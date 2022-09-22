package com.mcmiddleearth.mcmescripts.action;

import com.mcmiddleearth.mcmescripts.selector.Selector;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.SoundStop;
import org.bukkit.entity.Player;

public class ResourceSoundStopAction extends SelectingAction<Player> {

    public ResourceSoundStopAction(Selector<Player> selector, String musicFile) {
        super(selector, (player, context) -> player.stopSound(SoundStop.named(Key.key(musicFile))));
    }
}
