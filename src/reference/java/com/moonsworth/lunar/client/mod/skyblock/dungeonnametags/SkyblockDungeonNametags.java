package com.moonsworth.lunar.client.mod.skyblock.dungeonnametags;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import net.kyori.adventure.text.Component;

public class SkyblockDungeonNametags extends AbstractFeature {
   private final HologramEntityListener field8 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private final EntitySubscription<EntityArmorStandBridge> field10 = this.field8.method7().method2(arg0 -> {
      Component component1x = arg0.bridge$getCustomName();
      if (component1x == null) {
         return false;
      }

      String text2 = TextBridge.getTextContent(component1x);
      return text2.endsWith("❤") && !text2.contains("✯");
   }).method4(this);

   public SkyblockDungeonNametags(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && !this.field9.method10()));
      this.handle(EventRenderEntity.class, this::method1);
   }

   private void method1(EventRenderEntity data81) {
      if (this.field10.method4(data81.method1())) {
         data81.cancel();
      }
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_NAMETAGS";
   }
}
