package com.moonsworth.lunar.client.mod.skyblock.etherwarppreview;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.player.EventItemUse;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlock;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntities;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.audio.LunarSoundPlayer;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import java.util.HashSet;
import org.jetbrains.annotations.Nullable;

public class EtherwarpPreview extends AbstractFeature {
   EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final ColorOption field9 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "etherwarpPreviewColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562623))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("etherwarpPreviewInvalid")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "etherwarpPreviewInvalidColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ToggleOption field12 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("etherwarpPreviewShowThroughWalls")
      .method31();
   private final SoundOption field13 = (SoundOption)com.moonsworth.lunar.client.config.option.OptionFactory.method13("etherwarpValidSound")
      .method31();
   private final SoundOption field14 = (SoundOption)com.moonsworth.lunar.client.config.option.OptionFactory.method13("etherwarpInvalidSound")
      .method31();
   private final HashSet<Bridge3_23> field15;

   public EtherwarpPreview(Skyblock skyblock1) {
      super(true);
      this.method11(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method11(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method11(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntities.class, this::method1);
      this.handle(EventItemUse.class, this::method2);
      this.handle(EventUseItemOnBlock.class, this::method3);
      BlocksBridge bridge_562 = Bridge.method34();
      this.field15 = new HashSet<>();
      this.field15.add(bridge_562.method23());
      this.field15.add(bridge_562.method24());
      this.field15.add(bridge_562.method25());
      this.field15.add(bridge_562.method26());
      this.field15.add(bridge_562.method27());
      this.field15.add(bridge_562.method28());
      this.field15.add(bridge_562.method29());
      this.field15.add(bridge_562.method30());
      this.field15.add(bridge_562.method31());
      this.field15.add(bridge_562.method32());
      this.field15.add(bridge_562.method33());
      if (Ref.MC_VERSION >= 33) {
         this.field15.add(bridge_562.method68());
         this.field15.add(bridge_562.method108());
      }
   }

   private void method1(EventRenderEntities highlightimpl241) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null) {
         EtherwarpPreview.Data data3 = this.method5(highlightimpl241.method1().method28());
         if (data3 != null && data3.method1() != null) {
            Vec3iBridge horsestats204 = data3.method1();
            ColorOption lightingextension42225 = this.method10(data3.method2());
            if (lightingextension42225 != null) {
               AxisAlignedBBBridge horsestats126 = itemcounter6extension2.method4(horsestats204).bridge$getAABB(itemcounter6extension2, horsestats204);
               if (horsestats126 != null) {
                  Vec3iBridge horsestats207 = horsestats204.bridge$offset(0, 1, 0);
                  if (itemcounter6extension2.method4(horsestats207).bridge$isCarpet()) {
                     horsestats126 = horsestats126.method9(0.0, 1.1, 0.0);
                     horsestats126 = horsestats126.method9(1.0, 1.1, 1.0);
                  }

                  this.method8(highlightimpl241, horsestats126.method11(0.01).method5(horsestats204), lightingextension42225);
               }
            }
         }
      }
   }

   private void method2(EventItemUse highlightimpl181) {
      if (highlightimpl181.method2() == this.field8.method8()) {
         MovingObjectPositionBridge horsestats212 = this.mc.bridge$getObjectMouseOver();
         if (horsestats212 == null || !horsestats212.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
            this.method13();
         }
      }
   }

   private void method3(EventUseItemOnBlock highlightimpl1) {
      if (highlightimpl1.method2() == 0) {
         this.method13();
      }
   }

   private void method13() {
      EtherwarpPreview.Data data1 = this.method5(1.0F);
      EtherwarpPreview.Type type2 = data1 == null ? EtherwarpPreview.Type.NONE : data1.method2();
      if (type2 == EtherwarpPreview.Type.VALID) {
         LunarSoundPlayer.method2(this.field13, this);
      } else if (type2 == EtherwarpPreview.Type.INVALID) {
         LunarSoundPlayer.method2(this.field14, this);
      }
   }

   @Nullable
   private EtherwarpPreview.Data method5(float value1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (bridge5extension_52 != null && itemcounter6extension3 != null) {
         ItemStackBridge bridgeextension_44 = this.field8.method8();
         if (bridgeextension_44 == null) {
            return null;
         }

         CompoundTagComponent mixinhelper_105 = (CompoundTagComponent)bridgeextension_44.bridge$getDataComponent(DataComponentTypes.field1);
         return !this.method6(mixinhelper_105, bridge5extension_52.bridge$isSneaking())
            ? null
            : this.method7(bridge5extension_52, itemcounter6extension3, mixinhelper_105, value1)
               .method3()
               .map(
                  arg2x -> {
                     Horsestats20Extension2 horsestats20extension23x = Bridge.method8()
                        .method4(arg2x.method6().bridge$getX(), arg2x.method6().bridge$getY(), arg2x.method6().bridge$getZ());
                     int number4x = (int)Math.ceil(itemcounter6extension3.method2(horsestats20extension23x).bridge$getCollisionShapeMaxY(itemcounter6extension3, horsestats20extension23x));
                     Vec3iBridge horsestats205x = horsestats20extension23x.bridge$offset(0, number4x, 0);
                     Vec3iBridge horsestats206 = horsestats20extension23x.bridge$offset(0, number4x + 1, 0);
                     return new EtherwarpPreview.Data(horsestats20extension23x, this.method11(horsestats20extension23x, horsestats205x, horsestats206));
                  }
               )
               .orElseGet(() -> new EtherwarpPreview.Data(null, EtherwarpPreview.Type.INVALID));
      } else {
         return null;
      }
   }

   private boolean method6(CompoundTagComponent mixinhelper_101, boolean flag2) {
      return this.field8.method9().equals("ETHERWARP_CONDUIT") ? true : SkyblockItemUtil.method20(mixinhelper_101) && flag2;
   }

   private MissResult method7(Bridge6_10 bridge6_101, Itemcounter6 itemcounter62, CompoundTagComponent mixinhelper_103, float value4) {
      int number5 = 57 + this.method9(mixinhelper_103);
      return (MissResult)Ray.method9(Raycaster.field2)
         .method8(bridge6_101, number5, value4)
         .method14((arg2x, arg3x) -> this.method12(itemcounter62, arg2x, arg3x))
         .method18()
         .method8(itemcounter62);
   }

   private void method8(EventRenderEntities highlightimpl241, AxisAlignedBBBridge horsestats122, ColorOption lightingextension42223) {
      boolean flag4 = (Boolean)this.field12.get();
      AbstractRenderContext bridgeextension_95 = highlightimpl241.method1();
      bridgeextension_95.push();
      bridgeextension_95.translate(-highlightimpl241.getX(), -highlightimpl241.getY(), -highlightimpl241.getZ());
      DrawBufferBridge bridge2_326 = bridgeextension_95.method10(flag4 ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
      bridge2_326.method1();
      WorldRenderUtils.fillBox(
         bridge2_326,
         horsestats122.bridge$getMinX(),
         horsestats122.bridge$getMinY(),
         horsestats122.bridge$getMinZ(),
         horsestats122.bridge$getMaxX(),
         horsestats122.bridge$getMaxY(),
         horsestats122.bridge$getMaxZ(),
         lightingextension42223.method1(0.0F)
      );
      bridge2_326.method17(BufferMode.BATCHED);
      Skyblock skyblock7 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      BufferBuilderBridge bridge_288 = bridgeextension_95.method12((Float)skyblock7.method19().get(), flag4);
      WorldRenderUtils.drawBoxOutline(
         bridge_288,
         horsestats122.bridge$getMinX(),
         horsestats122.bridge$getMinY(),
         horsestats122.bridge$getMinZ(),
         horsestats122.bridge$getMaxX(),
         horsestats122.bridge$getMaxY(),
         horsestats122.bridge$getMaxZ(),
         ColorUtils.method31(lightingextension42223.method1(0.0F))
      );
      bridge_288.end();
      bridgeextension_95.pop();
   }

   private int method9(CompoundTagComponent mixinhelper_101) {
      if (mixinhelper_101 == null) {
         return 0;
      }

      CompoundTagBridge bridge_572 = mixinhelper_101.bridge$getData();
      return bridge_572 == null ? 0 : bridge_572.bridge$getInteger("tuned_transmission");
   }

   @Nullable
   private ColorOption method10(EtherwarpPreview.Type type1) {
      return switch (type1) {
         case VALID -> this.field9;
         case INVALID -> this.field10.get() ? this.field11 : null;
         case NONE -> null;
      };
   }

   private EtherwarpPreview.Type method11(Vec3iBridge horsestats201, Vec3iBridge horsestats202, Vec3iBridge horsestats203) {
      WorldBridgeExtension itemcounter6extension4 = Ref.method8();
      Bridge5Extension_5 bridge5extension_55 = Ref.method7();
      if (itemcounter6extension4 != null && bridge5extension_55 != null) {
         MovingObjectPositionBridge horsestats216 = this.mc.bridge$getObjectMouseOver();
         if (horsestats216 != null) {
            if (horsestats216.bridge$isTypeOfHit(MovingObjectTypeBridge.ENTITY)) {
               return EtherwarpPreview.Type.NONE;
            }

            if (horsestats216.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
               Bridge3_23 bridge3_237 = itemcounter6extension4.method4(horsestats216.bridge$getBlockPosition());
               if (this.field15.contains(bridge3_237) || bridge3_237.bridge$isHandOpenableTrapDoor() || bridge3_237.bridge$isFenceGate() || bridge3_237.bridge$isCauldron()) {
                  return EtherwarpPreview.Type.INVALID;
               }
            }
         }

         BlockStateBridge bridge2_178 = itemcounter6extension4.method2(horsestats201);
         if (bridge2_178.bridge$isFluid()) {
            return EtherwarpPreview.Type.NONE;
         } else {
            return !(itemcounter6extension4.method2(horsestats202).bridge$getCollisionShapeMaxY(itemcounter6extension4, horsestats202) > 0.0) && !(itemcounter6extension4.method2(horsestats203).bridge$getCollisionShapeMaxY(itemcounter6extension4, horsestats203) > 0.0)
               ? EtherwarpPreview.Type.VALID
               : EtherwarpPreview.Type.INVALID;
         }
      } else {
         return EtherwarpPreview.Type.NONE;
      }
   }

   private boolean method12(Itemcounter6 itemcounter61, Vec3iBridge horsestats202, BlockStateBridge bridge2_173) {
      return bridge2_173.bridge$isSign() ? true : bridge2_173.bridge$getCollisionShapeMaxY(itemcounter61, horsestats202) > 0.0 && !this.method13(bridge2_173);
   }

   private boolean method13(BlockStateBridge bridge2_171) {
      return bridge2_171.bridge$isCandle()
         || bridge2_171.bridge$getBlock() == Bridge.method34().method22()
         || bridge2_171.bridge$getBlock() == Bridge.method34().method9()
         || bridge2_171.bridge$isFlowerPot()
         || Ref.MC_VERSION >= 16 && bridge2_171.bridge$getBlock() == Bridge.method34().method106();
   }

   @ConstantName
   public String getId() {
      return "ETHERWARP_PREVIEW";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11})).method2(() -> !(Boolean)this.field10.get());
      lightingextension231.method9(new ClientOption[]{this.field12});
      lightingextension231.method9(new ClientOption[]{this.field13, this.field14});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data {
      @Nullable
      private final Vec3iBridge field1;
      private final EtherwarpPreview.Type field2;

      private Data(@Nullable Vec3iBridge horsestats201, EtherwarpPreview.Type type2) {
         this.field1 = horsestats201;
         this.field2 = type2;
      }

      @Nullable
      public Vec3iBridge method1() {
         return this.field1;
      }

      public EtherwarpPreview.Type method2() {
         return this.field2;
      }
   }

   private enum Type {
      NONE,
      VALID,
      INVALID;

      Type() {
      }
   }
}
