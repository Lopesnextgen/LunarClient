package com.moonsworth.lunar.client.mod.skyblock.anglehud;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class SkyblockAngleHud extends AbstractFeature {
   private final EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final IntegerOption field9 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "angleHudDecimalPlaces"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 10))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("angleHudHoldingTool").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("angleHudInGarden").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public SkyblockAngleHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockAngleHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
   }

   public String getId() {
      return "SKYBLOCK_ANGLE_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field9, this.field10, this.field11})
      );
   }

   private class Data extends TypedHudRenderer<List<TextComponent>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 30, 60, 20, 80, 200);
      }

      @Nullable
      public List<TextComponent> method2(boolean flag1) {
         if (flag1) {
            return this.getLines();
         } else if ((Boolean)SkyblockAngleHud.this.field11.get() && IslandUtils.getIsland() != SkyblockIsland.GARDEN) {
            return List.of();
         } else {
            return SkyblockAngleHud.this.field10.get() && !SkyblockAngleHud.this.field8.method6() ? List.of() : this.getLines();
         }
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      private List<TextComponent> getLines() {
         ArrayList list1 = new ArrayList();
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 == null) {
            return list1;
         }

         String text3 = "%." + SkyblockAngleHud.this.field9.get() + "f";
         double value4 = MathUtils.method14(bridge5extension_52.bridge$getRotationYaw());
         list1.add(TextComponentFactory.builder().method2("Yaw").method4(String.format(text3, value4) + "°").build());
         list1.add(TextComponentFactory.builder().method2("Pitch").method4(String.format(text3, bridge5extension_52.bridge$getRotationPitch()) + "°").build());
         return list1;
      }
   }
}
