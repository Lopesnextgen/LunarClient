package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import java.util.Optional;

@FunctionalInterface
public interface KillMessageParser {
   Optional<String> parseTargetName(String text1, TypedChatMessage data2);
}
