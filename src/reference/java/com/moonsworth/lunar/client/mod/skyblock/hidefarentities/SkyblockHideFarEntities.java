package com.moonsworth.lunar.client.mod.skyblock.hidefarentities;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockHideFarEntities extends AbstractFeature {
   private final IntegerOption field8 = (IntegerOption)((Data)((Data)OptionFactory.method4("hideFarEntitiesDistance")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(7))
         .method7(1, 100))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hidePlayers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideNpcs").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideEntities").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public SkyblockHideFarEntities(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.RENDER));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.HUB || IslandUtils.getIsland() == SkyblockIsland.DUNGEON_HUB));
      this.handle(EventRenderEntity.class, this::method1);
   }

   private void method1(EventRenderEntity data81) {
      BridgeExtension bridgeextension2 = data81.method1();
      if (!bridgeextension2.equals(Ref.method7())) {
         if (!(bridgeextension2.method13(Ref.method7()) < (Integer)this.field8.get() * (Integer)this.field8.get())) {
            if (bridgeextension2 instanceof Bridge6_10 bridge6_103) {
               if (NpcUtils.method2(bridge6_103, true)) {
                  if ((Boolean)this.field10.get()) {
                     data81.cancel();
                  }
               } else if ((Boolean)this.field9.get()) {
                  data81.cancel();
               }
            } else if ((Boolean)this.field11.get()) {
               bridgeextension2.bridge$stopTurbo(null);
               data81.cancel();
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_FAR_ENTITIES";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field8, this.field9, this.field10, this.field11})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
