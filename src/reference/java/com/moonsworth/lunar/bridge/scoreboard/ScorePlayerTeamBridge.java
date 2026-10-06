package com.moonsworth.lunar.bridge.scoreboard;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import net.kyori.adventure.text.Component;

public interface ScorePlayerTeamBridge {
   ChatFormatting bridge$getChatFormat();

   Bridge2_42 bridge$formatString(Bridge2_42 bridge2_421);

   Component bridge$formatString(Component component1);

   Component bridge$getPrefixAndSuffix();
}
