package com.moonsworth.lunar.client.mod.skyblock.fishingreeltimerhud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.FishingHookTracker;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockFishingReelTimerHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^\\d\\.\\d|!!!$");
   private final FishingHookTracker field9 = (FishingHookTracker)this.method63(FishingHookTracker.class);
   private final HologramEntityListener field10 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "timerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("boldTimer").method31();
   private final EntitySubscription<EntityArmorStandBridge> field14 = this.field10.method7().method2(arg0 -> {
      Component component1x = arg0.bridge$getCustomName();
      if (component1x == null) {
         return false;
      }

      String text2 = TextBridge.getTextContent(component1x);
      return field8.matcher(text2).matches();
   }).method4(this);

   public SkyblockFishingReelTimerHud(Skyblock skyblock1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockFishingReelTimerHud.Data()));
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.HUD, arg1x -> arg1x.method9(new ClientOption[]{this.field13}));
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field11, this.field12})
      );
   }

   public String getId() {
      return "SKYBLOCK_FISHING_REEL_TIMER_HUD";
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(15, 30, 50, 60, 120, 300);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (flag1) {
            return this.method3("2.5");
         } else {
            return SkyblockFishingReelTimerHud.this.field9.method11() && !SkyblockFishingReelTimerHud.this.field14.isEmpty()
               ? this.method3(TextBridge.getTextContent(((EntityArmorStandBridge)SkyblockFishingReelTimerHud.this.field14.method2().get()).bridge$getCustomName()))
               : null;
         }
      }

      private HudLine method3(String text1) {
         TextComponent text2 = Component.text(text1, TextColor.color(SkyblockFishingReelTimerHud.this.field12.method14(0.0F)));
         if ((Boolean)SkyblockFishingReelTimerHud.this.field13.get()) {
            text2 = (TextComponent)text2.decorate(TextDecoration.BOLD);
         }

         if (this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH != null && (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get()) {
            text2 = (TextComponent)Component.text(
                  SkyblockFishingReelTimerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("reelTimer", new Object[0]) + ": ",
                  TextColor.color(SkyblockFishingReelTimerHud.this.field11.method14(0.0F))
               )
               .append(text2);
         }

         return new HudLine(Bridge.method28().method2(), text2);
      }

      protected boolean method23() {
         return true;
      }
   }
}
