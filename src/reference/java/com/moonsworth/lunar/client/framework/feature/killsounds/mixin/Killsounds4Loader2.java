package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import java.util.List;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.TranslationArgument;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class Killsounds4Loader2 implements Killsounds4 {
   @Override
   public Optional<String> parseTargetName(String var1, Data var2) {
      if (var2.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI() instanceof TranslatableComponent var4) {
         String var5 = var4.key();
         List var6 = var4.arguments();
         if (var5.startsWith("death.")
            && var6.size() >= 2
            && ((TranslationArgument)var4.arguments().get(0)).value() instanceof Component var7
            && ((TranslationArgument)var4.arguments().get(1)).value() instanceof Component var8) {
            String var12 = PlainTextComponentSerializer.plainText().serialize(var8);
            if (var1.equals(var12)) {
               String var10 = PlainTextComponentSerializer.plainText().serialize(var7);
               return Optional.of(var10);
            }
         }
      }

      return Optional.empty();
   }
}
