package com.moonsworth.lunar.client.mod.skyblock.crystalhollowsmap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockCrystalHollowsMap extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");
   private final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "ui/images/crystal-hollows-map.png");

   public SkyblockCrystalHollowsMap(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockCrystalHollowsMap.Data()));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
   }

   public String getId() {
      return "SKYBLOCK_CRYSTAL_HOLLOWS_MAP";
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.method58(128.0F, 128.0F);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         mixinhelper_45.method44(arg0 -> {
            arg0.method29().method11();
            arg0.method29().method33();
         });
         if (flag4) {
            this.method2(mixinhelper_45, value2, value3, SkyblockCrystalHollowsMap.field8, 468.0, 402.0, 135.0F);
         } else {
            Bridge5Extension_5 bridge5extension_56 = Ref.method7();
            if (bridge5extension_56 == null) {
               LcuiScreen.method31(mixinhelper_45, SkyblockCrystalHollowsMap.this.field9, value2, value3, this.getWidth(), this.getHeight(), -1);
            } else {
               this.method2(mixinhelper_45, value2, value3, bridge5extension_56.bridge$getLocationSkin(), bridge5extension_56.bridge$getPosX(), bridge5extension_56.bridge$getPosZ(), (float)bridge5extension_56.bridge$getRotationYaw());
            }
         }
      }

      private void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, ResourceLocationBridge horsestats144, double value5, double value7, float value9) {
         LcuiScreen.method31(mixinhelper_41, SkyblockCrystalHollowsMap.this.field9, value2, value3, this.getWidth(), this.getHeight(), -1);
         if (horsestats144 != null) {
            float value10 = (float)(value5 - 202.0) / 621.0F * this.getWidth();
            float value11 = (float)(value7 - 202.0) / 621.0F * this.getHeight();
            mixinhelper_41.push();
            mixinhelper_41.method38(value2 + value10, value3 + value11, 0.0F);
            mixinhelper_41.method42(value9 - 180.0F);
            LcuiScreen.method50(mixinhelper_41, horsestats144, -4.0F, -4.0F, -1, true);
            mixinhelper_41.pop();
         }
      }

      public boolean method4(boolean flag1) {
         return true;
      }
   }
}
