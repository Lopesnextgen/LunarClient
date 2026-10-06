package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import java.util.List;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.TranslationArgument;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class DeathMessageKillParser implements KillMessageParser {
   public DeathMessageKillParser() {
   }

   @Override
   public Optional<String> parseTargetName(String text1, TypedChatMessage data2) {
      if (data2.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI() instanceof TranslatableComponent translatablecomponent4) {
         String text5 = translatablecomponent4.key();
         List list6 = translatablecomponent4.arguments();
         if (text5.startsWith("death.")
            && list6.size() >= 2
            && ((TranslationArgument)translatablecomponent4.arguments().get(0)).value() instanceof Component component7
            && ((TranslationArgument)translatablecomponent4.arguments().get(1)).value() instanceof Component component8) {
            String text12 = PlainTextComponentSerializer.plainText().serialize(component8);
            if (text1.equals(text12)) {
               String text10 = PlainTextComponentSerializer.plainText().serialize(component7);
               return Optional.of(text10);
            }
         }
      }

      return Optional.empty();
   }
}
