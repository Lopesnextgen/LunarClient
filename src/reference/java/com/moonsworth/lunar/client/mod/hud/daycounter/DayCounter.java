package com.moonsworth.lunar.client.mod.hud.daycounter;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;

public class DayCounter extends AbstractFeature {
   private final ToggleOption useWorldType = (ToggleOption)OptionFactory.method7("useWorldType").method31();

   public DayCounter() {
      super(false);
      this.registerOptions(ModTraits.field1, new DayCounter.Data());
   }

   public String getId() {
      return "DAY_COUNTER";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> arg1x.method9(new ClientOption[]{this.useWorldType}));
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER);
      }

      public HudSize getSize() {
         return HudSize.method1(10, 18, 22, 46, 56, 62);
      }

      @Nullable
      public String registerOptions(boolean flag1) {
         if (Ref.method8() != null && Ref.method8().bridge$getWorldInfo() != null) {
            long number2;
            if ((Boolean)DayCounter.this.useWorldType.get()) {
               number2 = Ref.method8().bridge$getDayTime() / 24000L;
            } else {
               number2 = Ref.method8().bridge$getWorldInfo().bridge$getGameTime() / 24000L;
            }

            return DayCounter.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(number2 != 1L ? "days" : "day", new Object[]{number2});
         } else {
            return flag1 ? DayCounter.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("days", new Object[]{21}) : null;
         }
      }

      public HudConditionSet getConditions() {
         return HudConditionSet.method5().method1(false).method8();
      }
   }
}
