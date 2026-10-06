package com.moonsworth.lunar.client.mod.skyblock.divanalltoolsalert;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDivanAllToolsAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private final EquippedItemListener field9 = (EquippedItemListener)this.method63(EquippedItemListener.class);

   public SkyblockDivanAllToolsAlert(Skyblock skyblock1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      if (this.field9.getId().equals("DWARVEN_METAL_DETECTOR")) {
         boolean flag2 = false;
         boolean flag3 = false;
         boolean flag4 = false;
         boolean flag5 = false;

         for (ItemStackBridge bridgeextension_47 : Ref.method7().bridge$getInventory().bridge$getMainInventory()) {
            if (bridgeextension_47 != null && !bridgeextension_47.bridge$isEmpty()) {
               String text8 = bridgeextension_47.bridge$getRawDisplayName();
               if (text8 != null) {
                  if (text8.contains("Scavenged Lapis")) {
                     flag2 = true;
                  }

                  if (text8.contains("Scavenged Diamond")) {
                     flag3 = true;
                  }

                  if (text8.contains("Scavenged Emerald")) {
                     flag4 = true;
                  }

                  if (text8.contains("Scavenged Golden")) {
                     flag5 = true;
                  }
               }
            }
         }

         if (flag5 && flag2 && flag3 && flag4) {
            this.field8
               .method2(ComparableImpl.method2().method1("DIVAN_ALL_TOOLS").method2(Component.text("All tools in inventory!")).method3(1500L).method6());
         }
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DIVAN_ALL_TOOLS_ALERT";
   }
}
