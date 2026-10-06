package com.moonsworth.lunar.client.mod.skyblock.wormscathaalert;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.Data;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.WormScathaSpawnEvent;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockWormScathaAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);

   public SkyblockWormScathaAlert(Skyblock skyblock1) {
      super(true);
      this.method1(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.handle(Data.class, this::method1);
      this.handle(WormScathaSpawnEvent.class, this::method2);
   }

   private void method1(Data data1) {
      this.field8
         .method2(
            ComparableImpl.method2()
               .method1("WORM_SPAWNING")
               .method2(Component.text(this.method14("wormSpawning", new Object[0]), NamedTextColor.RED))
               .method6()
         );
   }

   private void method2(WormScathaSpawnEvent data21) {
      this.field8
         .method2(
            ComparableImpl.method2()
               .method1("WORM_SPAWNING")
               .method2(
                  Component.text(
                     data21.method1()
                        ? this.method14("scathaSpawned", new Object[0])
                        : this.method14("wormSpawned", new Object[0]),
                     NamedTextColor.RED
                  )
               )
               .method6()
         );
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_WORM_SCATHA_ALERT";
   }
}
