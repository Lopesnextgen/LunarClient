package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import java.util.Optional;

@FunctionalInterface
public interface Killsounds4 {
   Optional<String> parseTargetName(String var1, Data var2);
}
