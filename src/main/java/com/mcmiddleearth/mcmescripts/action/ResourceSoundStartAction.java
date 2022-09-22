package com.mcmiddleearth.mcmescripts.action;

import com.mcmiddleearth.mcmescripts.selector.Selector;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;

public class ResourceSoundStartAction extends SelectingAction<Player> {

    public ResourceSoundStartAction(Selector<Player> selector, String musicFile) {
        super(selector, (player, context) -> player.playSound(Sound.sound(Key.key(musicFile), Sound.Source.VOICE, 1f, 1f)));
    }
}
