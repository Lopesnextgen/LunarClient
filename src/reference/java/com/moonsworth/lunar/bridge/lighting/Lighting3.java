package com.moonsworth.lunar.bridge.lighting;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import net.kyori.adventure.text.Component;

public interface Lighting3 {
   AdventureChatFormatting bridge$getChatFormat();

   Bridge2_42 bridge$formatString(Bridge2_42 var1);

   Component bridge$formatString(Component var1);

   Component bridge$getPrefixAndSuffix();
}
