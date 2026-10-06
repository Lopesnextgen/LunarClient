package com.moonsworth.lunar.client.mod.skyblock.mutebeeheemothsounds;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.Set;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockMuteBeeheemothSounds extends AbstractFeature {
   private static final Set<String> field8 = Sets.newHashSet(new String[]{"entity.bee.loop_aggressive", "entity.bee.death", "entity.bee.hurt"});

   public SkyblockMuteBeeheemothSounds(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.TORRHUS_CANYON));
      this.handle(EventPlaySound.class, this::method1);
   }

   private void method1(EventPlaySound highlightimpl131) {
      if (field8.contains(highlightimpl131.getPath())) {
         highlightimpl131.cancel();
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_MUTE_BEEHEEMOTH_SOUNDS";
   }
}
