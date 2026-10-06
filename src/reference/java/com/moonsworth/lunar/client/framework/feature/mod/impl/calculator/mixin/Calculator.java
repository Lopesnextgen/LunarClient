package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;

public class Calculator {
   private final String field1;
   private final Component field2;
   private final String field3;
   private final RaffleTaskDifficulty field4;
   private boolean completed;

   public Calculator(String text1, Component component2, String text, RaffleTaskDifficulty raffleTaskDifficulty, boolean flag) {
      this.field1 = text1;
      this.field2 = component2;
      this.field3 = text;
      this.field4 = raffleTaskDifficulty;
      this.completed = flag;
   }

   public boolean hasDescription() {
      return !this.field3.isEmpty();
   }

   @Nullable
   public static Calculator method1(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         String text1 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_40.bridge$getDisplayName()).trim();
         if (text1.isEmpty()) {
            return null;
         }

         RaffleTaskDifficulty calculatortype22 = null;
         boolean flag3 = false;
         Builder builder4 = Component.text();
         StringBuilder builder5 = new StringBuilder();

         for (Component component7 : SkyblockItemUtil.method16(bridgeextension_40)) {
            String text8 = TextBridge.getTextContent(component7).trim();
            if (!text8.isEmpty()) {
               RaffleTaskDifficulty calculatortype29 = RaffleTaskDifficulty.fromLoreLine(text8);
               if (calculatortype29 != null) {
                  calculatortype22 = calculatortype29;
               } else if (!text8.equalsIgnoreCase("COMPLETE") && !text8.equalsIgnoreCase("INCOMPLETE")) {
                  if (!builder5.isEmpty()) {
                     builder4.append(Component.space());
                     builder5.append(' ');
                  }

                  builder4.append(component7);
                  builder5.append(text8);
               } else {
                  flag3 = text8.equalsIgnoreCase("COMPLETE");
               }
            }
         }

         return calculatortype22 == null ? null : new Calculator(text1, builder4.build(), builder5.toString(), calculatortype22, flag3);
      } else {
         return null;
      }
   }

   @Generated
   public String getName() {
      return this.field1;
   }

   @Generated
   public Component method2() {
      return this.field2;
   }

   @Generated
   public String method3() {
      return this.field3;
   }

   @Generated
   public RaffleTaskDifficulty getDifficulty() {
      return this.field4;
   }

   @Generated
   public boolean isCompleted() {
      return this.completed;
   }

   @Generated
   public void setCompleted(boolean flag) {
      this.completed = flag;
   }
}
