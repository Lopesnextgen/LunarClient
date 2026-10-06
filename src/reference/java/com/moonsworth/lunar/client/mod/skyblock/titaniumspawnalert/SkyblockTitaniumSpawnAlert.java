package com.moonsworth.lunar.client.mod.skyblock.titaniumspawnalert;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockTitaniumSpawnAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private static final TextComponent field9 = Component.text("Titanium Spawned!", NamedTextColor.RED);
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("playChime").method31();

   public SkyblockTitaniumSpawnAlert(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DWARVEN_MINES));
      this.handle(EventBlockChange.class, this::method2);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10});
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_TITANIUM_SPAWN_ALERT";
   }

   private void method2(EventBlockChange highlightimpl91) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (bridge5extension_52 != null
         && itemcounter6extension3 != null
         && !highlightimpl91.method2().bridge$isSmoothDiorite()
         && highlightimpl91.method3().bridge$isSmoothDiorite()
         && bridge5extension_52.method2(highlightimpl91.method1()) < 100.0
         && (
            !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(1, 0, 0)).bridge$isSolid()
               || !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(-1, 0, 0)).bridge$isSolid()
               || !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(0, 1, 0)).bridge$isSolid()
               || !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(0, -1, 0)).bridge$isSolid()
               || !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(0, 0, 1)).bridge$isSolid()
               || !itemcounter6extension3.method2(highlightimpl91.method1().bridge$offset(0, 0, -1)).bridge$isSolid()
         )) {
         this.field8.method2(ComparableImpl.method2().method1("TITANIUM_SPAWN").method2(field9).method3(1000L).method6());
         if ((Boolean)this.field10.get()) {
            IslandUtils.playSound();
         }
      }
   }
}
