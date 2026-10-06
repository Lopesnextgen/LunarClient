package com.moonsworth.lunar.client.mod.skyblock.speedhud;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class SkyblockSpeedHud extends AbstractFeature {
   private final ToggleOption showIcon = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showIcon").method4(true))
      .method31();
   private TextComponent speedText = Component.empty();

   public SkyblockSpeedHud(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockSpeedHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSecond.class, this::method2);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.showIcon});
   }

   private void method2(EventSecond highlightimpl41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         double value3 = bridge5extension_52.bridge$getMovementSpeedAttribute();
         int number5 = (int)(value3 * 1000.0);
         if (bridge5extension_52.bridge$isSprinting()) {
            number5 = (int)Math.round(number5 / 1.3);
         }

         this.speedText = this.formatSpeed(number5);
      }
   }

   private TextComponent formatSpeed(int number1) {
      String text2 = this.showIcon.get() ? String.valueOf('✦') : "";
      return Component.text(text2 + number1);
   }

   public String getId() {
      return "SKYBLOCK_SPEED_HUD";
   }

   private class Data extends TypedHudRenderer<TextComponent> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
      }

      public HudSize method15() {
         return HudSize.method1(10, 15, 30, 25, 35, 50);
      }

      @Nullable
      public TextComponent method2(boolean flag1) {
         return flag1 && SkyblockSpeedHud.this.speedText.equals(Component.empty()) ? SkyblockSpeedHud.this.formatSpeed(400) : SkyblockSpeedHud.this.speedText;
      }
   }
}
