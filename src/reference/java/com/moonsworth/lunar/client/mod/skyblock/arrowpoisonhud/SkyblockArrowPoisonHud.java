package com.moonsworth.lunar.client.mod.skyblock.arrowpoisonhud;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public class SkyblockArrowPoisonHud extends AbstractFeature {
   private static final NumberFormat field8 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final ItemStackBridge field9 = SkyblockItemUtil.method19(DyeColor.LIME);
   private static final ItemStackBridge field10 = SkyblockItemUtil.method19(DyeColor.PURPLE);
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showToxicArrowPoison").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTwilightArrowPoison").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private int field13;
   private int field14;

   public SkyblockArrowPoisonHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockArrowPoisonHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick highlightimpl21) {
      if (IslandUtils.isOnIsland()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            this.field13 = 0;
            this.field14 = 0;

            for (ItemStackBridge bridgeextension_45 : bridge5extension_52.bridge$getInventory().bridge$getMainInventory()) {
               String text6 = SkyblockItemUtil.method2(bridgeextension_45);
               if (text6.equals("TOXIC_ARROW_POISON")) {
                  this.field13 = this.field13 + bridgeextension_45.bridge$getStackSize();
               } else if (text6.equals("TWILIGHT_ARROW_POISON")) {
                  this.field14 = this.field14 + bridgeextension_45.bridge$getStackSize();
               }
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field11, this.field12})
      );
   }

   public String getId() {
      return "SKYBLOCK_ARROW_POISON_HUD";
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 40, 80, 80, 150, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1 ? this.method3(384, 128) : this.method3(SkyblockArrowPoisonHud.this.field13, SkyblockArrowPoisonHud.this.field14);
      }

      @Nullable
      private List<HudLine> method3(int number1, int number2) {
         ArrayList list3 = new ArrayList();
         if ((Boolean)SkyblockArrowPoisonHud.this.field11.get()) {
            list3.add(
               new HudLine(
                  SkyblockArrowPoisonHud.field9,
                  TextComponentFactory.builder()
                     .method2("Toxic Poison")
                     .method4(SkyblockArrowPoisonHud.field8.format(number1))
                     .method5(NamedTextColor.GREEN)
                     .method7(number1 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockArrowPoisonHud.this.field12.get()) {
            list3.add(
               new HudLine(
                  SkyblockArrowPoisonHud.field10,
                  TextComponentFactory.builder()
                     .method2("Twilight Poison")
                     .method4(SkyblockArrowPoisonHud.field8.format(number2))
                     .method5(NamedTextColor.LIGHT_PURPLE)
                     .method7(number2 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         return list3.isEmpty() ? null : list3;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
