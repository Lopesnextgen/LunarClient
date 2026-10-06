package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class DebugTimerHud extends AbstractFeature {
   private HudTimer timer;

   public DebugTimerHud(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.registerOptions(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.registerOptions(ModTraits.field1, HudVisibilityWrapper.method4(new DebugTimerHud.Data()));
   }

   @ConstantName
   public String getId() {
      return "DEBUG_TIMER_HUD";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new OptionProvider[]{
            OptionFactory.method14("start").method4(this::start), OptionFactory.method14("stop").method4(this::stopTimer), OptionFactory.method14("reset").method4(this::reset)
         }
      );
   }

   private boolean reset() {
      this.timer = null;
      return true;
   }

   private boolean start() {
      if (this.timer == null) {
         this.timer = new com.moonsworth.lunar.client.framework.hud.HudTimer.Data().method5(0L).method3().method6(true).method7();
      }

      this.timer.method2();
      return true;
   }

   private boolean stopTimer() {
      this.timer.stop();
      return true;
   }

   private class Data extends TypedHudRenderer<TextComponent> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
      }

      public HudSize getSize() {
         return HudSize.method1(10, 15, 30, 70, 120, 170);
      }

      @Nullable
      public TextComponent registerOptions(boolean flag1) {
         if (flag1) {
            return this.createText("13.37s");
         } else {
            return DebugTimerHud.this.timer == null ? this.createText("Debug Timer") : this.createText(DebugTimerHud.this.timer.method1());
         }
      }

      private TextComponent createText(String text1) {
         return Component.text(text1);
      }
   }
}
