package com.moonsworth.lunar.client.mod.skyblock.gravitystormpreview;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import org.joml.Vector3i;

public class GravityStormPreview extends AbstractFeature {
   EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final ColorOption field9 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("gyroPreviewColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5418333))
      .method31();
   private final FloatOption field10 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "gyroLineWidth"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(5.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private Vec3iBridge field11;

   public GravityStormPreview(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTick.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method2);
   }

   private void method1(EventTick highlightimpl21) {
      this.field11 = null;
      if (IslandUtils.isOnIsland()) {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (itemcounter6extension2 != null && bridge5extension_53 != null) {
            if (this.field8.method9().equals("GYROKINETIC_WAND")) {
               Ray sextension4 = Ray.method9(Raycaster.field4).method8(bridge5extension_53, 24.0, 0.0F).method14((arg0, arg1x) -> !arg1x.bridge$isAir()).method18();
               ((MissResult)sextension4.method8(itemcounter6extension2)).method5(arg2x -> {
                  Vec3iBridge horsestats203x = arg2x.method6();
                  Vec3iBridge horsestats204x = horsestats203x.bridge$add(new Vector3i(0, 1, 0));
                  if (itemcounter6extension2.method4(horsestats204x).bridge$isAir()) {
                     this.field11 = horsestats203x;
                  }
               });
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if (this.field11 != null) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
         WorldRenderUtils.draw3DCircle(
            bridgeextension_93,
            this.field11.bridge$getX() + 0.5,
            this.field11.bridge$getY() + 1.1,
            this.field11.bridge$getZ() + 0.5,
            10.0,
            (Float)this.field10.get(),
            this.field9.method14(0.0F)
         );
         bridgeextension_93.pop();
      }
   }

   public String getId() {
      return "GRAVITY_STORM_PREVIEW";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
