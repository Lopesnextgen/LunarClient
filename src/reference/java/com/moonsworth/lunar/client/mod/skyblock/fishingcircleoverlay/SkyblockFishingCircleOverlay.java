package com.moonsworth.lunar.client.mod.skyblock.fishingcircleoverlay;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.ParticleSample;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import toxi.geom.Vec2D;

public class SkyblockFishingCircleOverlay extends AbstractFeature {
   private final EnumOption<SkyblockFishingCircleOverlay.Type> field8 = (EnumOption<SkyblockFishingCircleOverlay.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "overlayShape", SkyblockFishingCircleOverlay.Type.CYLINDER
      )
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hotspotOverlay")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("hotspotOverlayColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(822040063))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("wormholeOverlay")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("wormholeOverlayColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2134900800))
      .method31();
   private List<com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data> field13 = Collections.emptyList();
   private List<com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data> field14 = Collections.emptyList();
   private List<ParticleSample> field15 = new ArrayList<>();
   private List<ParticleSample> field16 = new ArrayList<>();

   public SkyblockFishingCircleOverlay(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(
         ModTraits.field17,
         ModCategories.method3(new SettingsPage[]{SettingsPage.FISHING, SettingsPage.CRIMSON_ISLE, SettingsPage.LOTUS_ATOLL})
      );
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method1(this::onDisable);
      this.handle(EventSecond.class, this::method1);
      this.handle(EventSpawnParticle.class, arg1x -> {
         this.method2(arg1x);
         this.method3(arg1x);
      });
      this.handle(HudRenderLegacyEvent.class, this::method4);
   }

   private void onDisable() {
      this.field13 = Collections.emptyList();
      this.field14 = Collections.emptyList();
      this.field15.clear();
      this.field16.clear();
   }

   private void method1(EventSecond highlightimpl41) {
      List list2 = this.field15;
      List list3 = this.field16;
      this.field15 = new ArrayList<>();
      this.field16 = new ArrayList<>();
      if ((Boolean)this.field9.get()) {
         this.field13 = CircleFitter.findCircles(list2, 1.0F, 4.0F, 0.1, 100).stream().filter(arg1x -> this.method7(arg1x.method4())).toList();
      }

      if ((Boolean)this.field11.get()) {
         this.field14 = CircleFitter.findCircles(list3, 1.0F, 3.0F, 0.1, 100);
      }
   }

   private void method2(EventSpawnParticle highlightimpl151) {
      if ((Boolean)this.field9.get()) {
         if (this.method6(highlightimpl151)) {
            this.field15
               .add(
                  new ParticleSample(
                     new Vec2D((float)highlightimpl151.getPosX(), (float)highlightimpl151.getPosZ()), (float)highlightimpl151.getPosY(), Ref.method3().bridge$getSystemTime()
                  )
               );
         }
      }
   }

   private void method3(EventSpawnParticle highlightimpl151) {
      if ((Boolean)this.field11.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.LOTUS_ATOLL) {
            if (highlightimpl151.method2() == ParticleType.PORTAL) {
               if (highlightimpl151.getPosY() % 1.0 == 0.0) {
                  this.field16
                     .add(
                        new ParticleSample(
                           new Vec2D((float)highlightimpl151.getPosX(), (float)highlightimpl151.getPosZ()), (float)highlightimpl151.getPosY(), Ref.method3().bridge$getSystemTime()
                        )
                     );
               }
            }
         }
      }
   }

   public void method4(HudRenderLegacyEvent highlightimpl21) {
      boolean flag2 = (Boolean)this.field9.get() && !this.field13.isEmpty();
      boolean flag3 = (Boolean)this.field11.get() && !this.field14.isEmpty();
      if (flag2 || flag3) {
         EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
         AbstractRenderContext bridgeextension_95 = highlightimpl21.method3();
         bridgeextension_95.push();
         bridgeextension_95.translate(-bridge2_434.bridge$renderPosX(), -bridge2_434.bridge$renderPosY(), -bridge2_434.bridge$renderPosZ());
         if (flag2) {
            for (com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data data7 : this.field13) {
               this.method5(bridgeextension_95, data7, this.field10.method14(0.0F));
            }
         }

         if (flag3) {
            for (com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data data9 : this.field14) {
               this.method5(bridgeextension_95, data9, this.field12.method14(0.0F));
            }
         }

         bridgeextension_95.pop();
      }
   }

   private void method5(AbstractRenderContext bridgeextension_91, com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data data2, int number3) {
      switch ((SkyblockFishingCircleOverlay.Type)this.field8.get()) {
         case CYLINDER:
            WorldRenderUtils.draw3DCylinder(bridgeextension_91, data2.method1(), 0.0, data2.method2(), data2.method4(), 255.0, number3, false);
            break;
         case CIRCLE:
            WorldRenderUtils.drawFilled3DCircle(bridgeextension_91, data2.method1(), data2.method3(), data2.method2(), data2.method4(), number3);
      }
   }

   private boolean method6(EventSpawnParticle highlightimpl151) {
      double value2 = highlightimpl151.getPosY() % 1.0;
      if (value2 != 0.0 && value2 != 0.5) {
         return false;
      } else {
         return IslandUtils.getIsland() == SkyblockIsland.CRIMSON_ISLES ? highlightimpl151.method2() == ParticleType.SMOKE : highlightimpl151.method2() == ParticleType.DUST;
      }
   }

   private boolean method7(double value1) {
      double value3 = value1 * 2.0;
      return Math.abs(value3 - Math.rint(value3)) <= 0.001;
   }

   public String getId() {
      return "SKYBLOCK_FISHING_CIRCLE_OVERLAY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field8});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field10}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12}));
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private enum Type implements OptionEnumValue {
      CYLINDER("cylinder"),
      CIRCLE("circle");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method1(this.id(), new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
