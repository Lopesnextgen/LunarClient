package com.moonsworth.lunar.client.mod.skyblock.fishingmarker;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.FishingHookTracker;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.joml.Vector3d;

public class SkyblockFishingMarker extends AbstractFeature {
   private final FishingHookTracker field8 = (FishingHookTracker)this.method63(FishingHookTracker.class);
   private final EnumOption<SkyblockFishingMarker.Type> field9 = (EnumOption<SkyblockFishingMarker.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "skyblockFishingMarkerType", SkyblockFishingMarker.Type.DISTANCE
      )
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockFishingMarkerColor"
         )
         .method4(-256))
      .method31();
   private final ColorOption field11 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockFishingMarkerCloseColor"
         )
         .method4(-65536))
      .method31();

   public SkyblockFishingMarker(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(HudRenderLegacyEvent.class, this::method1);
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      if (this.field8.method12()) {
         Vector3d vector3d2 = this.field8.method10();
         if (vector3d2 != null) {
            double value3 = vector3d2.x();
            double value5 = vector3d2.y();
            double value7 = vector3d2.z();
            double value9 = this.field8.method15().ORICHRORRORHORHOIHCRHOORCRRHOI(value3, value5, value7);
            int number11 = value9 < 1.0 ? this.field11.method14(0.0F) : this.field10.method14(0.0F);
            EntityRenderDispatcherBridge bridge2_4312 = Ref.method13();
            AbstractRenderContext bridgeextension_913 = highlightimpl21.method3();
            bridgeextension_913.push();
            bridgeextension_913.translate(-bridge2_4312.bridge$renderPosX(), -bridge2_4312.bridge$renderPosY(), -bridge2_4312.bridge$renderPosZ());
            switch ((SkyblockFishingMarker.Type)this.field9.get()) {
               case DISTANCE:
                  TextComponent text15 = Component.text((int)value9 + "m", TextColor.color(number11));
                  WorldRenderUtils.drawComponent(bridgeextension_913, text15, value3, value5 + 0.5, value7, true, 2.0F);
                  break;
               case DISTANCE:
                  TextComponent text14 = Component.text("!", TextColor.color(number11), new TextDecoration[]{TextDecoration.BOLD});
                  WorldRenderUtils.drawComponent(bridgeextension_913, text14, value3, value5 + 0.5, value7, true, 2.0F);
            }

            bridgeextension_913.pop();
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_FISHING_MARKER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10, this.field11});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private enum Type implements OptionEnumValue {
      DISTANCE("exclamationMark"),
      DISTANCE("distance");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
