package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderVanillaHud;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderArmor;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockDebugHideHud extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugHideArmor").method4(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugHideHearts").method4(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugHideHunger").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugHideAbsorption").method4(true))
      .method31();

   public SkyblockDebugHideHud(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(EventRenderArmor.class, arg1x -> {
         if ((Boolean)this.field8.get()) {
            arg1x.setCancelled(true);
         }
      });
      this.handle(EventRenderVanillaHud.class, arg1x -> {
         if ((Boolean)this.field10.get()) {
            arg1x.setCancelled(true);
         }
      });
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_HIDE_HUD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field10, this.field11});
   }

   public boolean method13() {
      return this.isEnabled() && (Boolean)this.field9.get();
   }

   public boolean method14() {
      return this.isEnabled() && (Boolean)this.field11.get();
   }

   public static boolean method15() {
      SkyblockDebugMod skyblockdebugmod0 = Ref.method4().method40().method77();
      if (skyblockdebugmod0 != null && skyblockdebugmod0.method15().method13()) {
         return true;
      }

      if (!IslandUtils.isOnIsland()) {
         return false;
      }

      Skyblock skyblock1 = Ref.method4().method40().method82();
      return skyblock1.isEnabled() && (Boolean)skyblock1.method25().get();
   }

   public static boolean method16() {
      SkyblockDebugMod skyblockdebugmod0 = Ref.method4().method40().method77();
      if (skyblockdebugmod0 != null && skyblockdebugmod0.method15().method14()) {
         return true;
      }

      if (!IslandUtils.isOnIsland()) {
         return false;
      }

      Skyblock skyblock1 = Ref.method4().method40().method82();
      return skyblock1.isEnabled() && (Boolean)skyblock1.method26().get();
   }
}
