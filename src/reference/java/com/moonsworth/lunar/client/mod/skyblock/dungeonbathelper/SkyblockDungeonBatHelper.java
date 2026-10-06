package com.moonsworth.lunar.client.mod.skyblock.dungeonbathelper;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityBatBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.event.entity.EventLivingEntityBase.EventEntityScale;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonBatHelper extends AbstractFeature {
   private final HologramEntityListener field8 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final FloatOption field9 = (FloatOption)((Data)((Data)OptionFactory.method2("batScale").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.1F, 3.0F))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("batHitbox").method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "batHitboxColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140916223))
      .method31();
   private final EntitySubscription<EntityBatBridge> field12 = this.field8
      .method6()
      .method1(EntityBatBridge.class)
      .method2(arg0 -> !(arg0.bridge$getMaxHealth() < 100.0F) && !(arg0.bridge$getMaxHealth() > 1000.0F))
      .method4(this);

   public SkyblockDungeonBatHelper(Skyblock skyblock1) {
      super(false);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method12(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(HudRenderLegacyEvent.class, this::method2);
      this.handle(EventEntityScale.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_BAT_HELPER";
   }

   private void method1(EventEntityScale data71) {
      if (this.field12.method4(data71.method1())) {
         data71.method1((Float)this.field9.get());
      }
   }

   private void method2(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field10.get() && !this.field12.isEmpty()) {
         AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
         EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
         bridgeextension_92.push();
         bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
         DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
         bridge2_324.method1();

         for (BridgeExtension bridgeextension6 : this.field12) {
            AxisAlignedBBBridge horsestats127 = bridgeextension6.method11(highlightimpl21.method5());
            WorldRenderUtils.fillBox(
               bridge2_324,
               horsestats127.bridge$getMinX(),
               horsestats127.bridge$getMinY(),
               horsestats127.bridge$getMinZ(),
               horsestats127.bridge$getMaxX(),
               horsestats127.bridge$getMaxY(),
               horsestats127.bridge$getMaxZ(),
               this.field11.method1(0.0F)
            );
         }

         bridge2_324.method17(BufferMode.BATCHED);
         BufferBuilderBridge bridge_289 = bridgeextension_92.method11(1.0F);

         for (BridgeExtension bridgeextension11 : this.field12) {
            AxisAlignedBBBridge horsestats128 = bridgeextension11.method11(highlightimpl21.method5());
            WorldRenderUtils.drawBoxOutline(
               bridge_289,
               horsestats128.bridge$getMinX(),
               horsestats128.bridge$getMinY(),
               horsestats128.bridge$getMinZ(),
               horsestats128.bridge$getMaxX(),
               horsestats128.bridge$getMaxY(),
               horsestats128.bridge$getMaxZ(),
               ColorUtils.method31(this.field11.method1(0.0F))
            );
         }

         bridge_289.end();
         bridgeextension_92.pop();
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9});
      lightingextension231.method7(this.field10, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11}));
   }

   @Generated
   public HologramEntityListener method13() {
      return this.field8;
   }

   @Generated
   public FloatOption method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field10;
   }

   @Generated
   public ColorOption method16() {
      return this.field11;
   }

   @Generated
   public EntitySubscription<EntityBatBridge> method17() {
      return this.field12;
   }
}
