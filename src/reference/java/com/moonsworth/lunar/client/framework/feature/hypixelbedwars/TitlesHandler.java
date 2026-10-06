package com.moonsworth.lunar.client.framework.feature.hypixelbedwars;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.titles.Titles;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class TitlesHandler implements Titles {
   private final HypixelBedwars field1;

   @Nullable
   @Override
   public Integer method1(@Nullable Component component1, @Nullable Component component2) {
      if (this.field1.isEnabled() && (Boolean)this.field1.method29().get() && this.field1.method21()) {
         if (component1 != null) {
            String text3 = ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent(component1));
            if (text3.startsWith("TRAP TRIGGERED!") || text3.startsWith("ALARM!!!")) {
               return this.field1.method30().method13();
            }
         }

         if (component2 != null) {
            String text4 = ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent(component2));
            if (text4.startsWith("Your") && text4.endsWith("Trap has been set off!") || text4.startsWith("Reveal trap set off by")) {
               return this.field1.method34().method13();
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Generated
   public TitlesHandler(HypixelBedwars hypixelbedwars1) {
      this.field1 = hypixelbedwars1;
   }
}
