package com.moonsworth.lunar.client.mod.render.chunkborders;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Color;
import org.joml.Vector3i;

public class ChunkBorders extends AbstractFeature {
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18("toggleChunkBorders")
         .method18(this))
      .method5(KeyCode.KEY_NONE)
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("grid")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field10 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "gridSize"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(1.0F, 5.0F))
         .method6(1))
      .method31();
   private final FloatOption field11 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "gridLineThickness"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(1.0F, 5.0F))
         .method6(1))
      .method31();
   private final ColorOption field12 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("gridColor")
      .method9(Color.YELLOW)
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("innerCorners")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field14 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "innerCornerThickness"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(1.0F, 5.0F))
         .method6(1))
      .method31();
   private final ColorOption field15 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("innerChunkCornerColor")
      .method9(Color.BLUE)
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("outerCorners")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                  "outerCornerThickness"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(1.0F, 5.0F))
         .method6(1))
      .method31();
   private final ColorOption field18 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("outerChunkCornerColor")
      .method9(Color.RED)
      .method31();
   private boolean field19 = true;

   public ChunkBorders() {
      super(false);
      if (Bridge.getMinecraftVersion().method22()) {
         LunarEventBus.method29().method2(EventKeybind.class, arg1 -> {
            if (arg1.method11() == InputAction.DOWN && arg1.method10() == KeyCode.KEY_G && Bridge.method18().method1(KeyCode.KEY_F3)) {
               ModEnabledState framework32 = (ModEnabledState)this.method7(ModTraits.field6);
               if (framework32 != null) {
                  framework32.setEnabled(!framework32.isEnabled());
               }
            }
         });
      }

      this.handle(
         HudRenderLegacyEvent.class,
         arg1 -> {
            EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
            if (this.field19 && bridge2_432 != null) {
               Bridge5Extension_5 bridge5extension_53 = Ref.method7();
               ChunkBridge itemcounter24 = bridge5extension_53.bridge$getWorld()
                  .bridge$getChunkFromBlockCoords(
                     new Vector3i(
                        MathHelperBridge.method4(bridge5extension_53.bridge$getPosX()), MathHelperBridge.method4(bridge5extension_53.bridge$getPosY()), MathHelperBridge.method4(bridge5extension_53.bridge$getPosZ())
                     )
                  );
               AbstractRenderContext bridgeextension_95 = arg1.method3();
               bridgeextension_95.method13();
               bridgeextension_95.push();
               bridgeextension_95.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
               if ((Boolean)this.field16.get()) {
                  this.method2(bridgeextension_95, itemcounter24);
               }

               if ((Boolean)this.field9.get()) {
                  this.method3(bridgeextension_95, itemcounter24);
               }

               if ((Boolean)this.field13.get()) {
                  this.method1(bridgeextension_95, itemcounter24);
               }

               bridgeextension_95.pop();
               bridgeextension_95.method12();
            }
         }
      );
   }

   private void method1(AbstractRenderContext bridgeextension_91, ChunkBridge itemcounter22) {
      int number3 = Ref.method8().bridge$getMinBuildHeight();
      int number4 = Ref.method8().bridge$getMaxBuildHeight();
      double value5 = Ref.method13().bridge$renderPosY();
      int number7 = itemcounter22.bridge$getX() << 4;
      int number8 = itemcounter22.bridge$getZ() << 4;
      BufferBuilderBridge bridge_289 = bridgeextension_91.method11((Float)this.field14.get());
      bridge_289.method1(this.field15.method1(0.0F));
      if (Ref.MC_VERSION > 7 && value5 < number4 && value5 > number3) {
         bridge_289.method3(number7, number3, number8, number7, value5, number8);
         bridge_289.method3(number7 + 16, number3, number8, number7 + 16, value5, number8);
         bridge_289.method3(number7 + 16, number3, number8 + 16, number7 + 16, value5, number8 + 16);
         bridge_289.method3(number7, number3, number8 + 16, number7, value5, number8 + 16);
         bridge_289.method3(number7, value5, number8, number7, number4, number8);
         bridge_289.method3(number7 + 16, value5, number8, number7 + 16, number4, number8);
         bridge_289.method3(number7 + 16, value5, number8 + 16, number7 + 16, number4, number8 + 16);
         bridge_289.method3(number7, value5, number8 + 16, number7, number4, number8 + 16);
      } else {
         bridge_289.method3(number7, number3, number8, number7, number4, number8);
         bridge_289.method3(number7 + 16, number3, number8, number7 + 16, number4, number8);
         bridge_289.method3(number7 + 16, number3, number8 + 16, number7 + 16, number4, number8 + 16);
         bridge_289.method3(number7, number3, number8 + 16, number7, number4, number8 + 16);
      }

      bridge_289.end();
   }

   private void method2(AbstractRenderContext bridgeextension_91, ChunkBridge itemcounter22) {
      int number3 = Ref.method8().bridge$getMinBuildHeight();
      int number4 = Ref.method8().bridge$getMaxBuildHeight();
      double value5 = Ref.method13().bridge$renderPosY();
      int number7 = itemcounter22.bridge$getX() - 1;
      int number8 = itemcounter22.bridge$getZ() - 1;
      BufferBuilderBridge bridge_289 = bridgeextension_91.method11((Float)this.field17.get());
      bridge_289.method1(this.field18.method1(0.0F));

      for (int index10 = 0; index10 <= 2; index10++) {
         for (int index11 = 0; index11 <= 2; index11++) {
            if (index10 != 1 || index11 != 1) {
               int number12 = number7 + index10 << 4;
               int number13 = number8 + index11 << 4;
               if (Ref.MC_VERSION > 7 && value5 < number4 && value5 > number3) {
                  bridge_289.method3(number12, number3, number13, number12, value5, number13);
                  bridge_289.method3(number12 + 16, number3, number13, number12 + 16, value5, number13);
                  bridge_289.method3(number12 + 16, number3, number13 + 16, number12 + 16, value5, number13 + 16);
                  bridge_289.method3(number12, number3, number13 + 16, number12, value5, number13 + 16);
                  bridge_289.method3(number12, value5, number13, number12, number4, number13);
                  bridge_289.method3(number12 + 16, value5, number13, number12 + 16, number4, number13);
                  bridge_289.method3(number12 + 16, value5, number13 + 16, number12 + 16, number4, number13 + 16);
                  bridge_289.method3(number12, value5, number13 + 16, number12, number4, number13 + 16);
               } else {
                  bridge_289.method3(number12, number3, number13, number12, number4, number13);
                  bridge_289.method3(number12 + 16, number3, number13, number12 + 16, number4, number13);
                  bridge_289.method3(number12 + 16, number3, number13 + 16, number12 + 16, number4, number13 + 16);
                  bridge_289.method3(number12, number3, number13 + 16, number12, number4, number13 + 16);
               }
            }
         }
      }

      bridge_289.end();
   }

   private void method3(AbstractRenderContext bridgeextension_91, ChunkBridge itemcounter22) {
      int number3 = Ref.method8().bridge$getMinBuildHeight();
      int number4 = Ref.method8().bridge$getMaxBuildHeight();
      double value5 = Ref.method13().bridge$renderPosY();
      int number7 = (itemcounter22.bridge$getX() - 1) * 16 + 16;
      int number8 = (itemcounter22.bridge$getZ() - 1) * 16 + 16;
      float value9 = ColorUtils.method9(this.field12.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F));
      float value10 = ColorUtils.method9(this.field12.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F));
      float value11 = ColorUtils.method9(this.field12.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F));
      float value12 = ColorUtils.method9(this.field12.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F));
      double value13 = (Float)this.field10.get() / 2.0F;
      BufferBuilderBridge bridge_2815 = bridgeextension_91.method11((Float)this.field11.get());
      bridge_2815.method2(value9, value10, value11, value12);
      if (Ref.MC_VERSION > 7 && value5 < number4 && value5 > number3) {
         for (double value23 = value13; value23 < 16.0; value23 += value13) {
            bridge_2815.method5(number7 + value23, number3, number8, number7 + value23, value5, number8, value9, value10, value11, value12);
            bridge_2815.method5(number7, number3, number8 + value23, number7, value5, number8 + value23, value9, value10, value11, value12);
            bridge_2815.method5(number7 + 16, number3, number8 + value23, number7 + 16, value5, number8 + value23, value9, value10, value11, value12);
            bridge_2815.method5(number7 + value23, number3, number8 + 16, number7 + value23, value5, number8 + 16, value9, value10, value11, value12);
            bridge_2815.method5(number7 + value23, value5, number8, number7 + value23, number4, number8, value9, value10, value11, value12);
            bridge_2815.method5(number7, value5, number8 + value23, number7, number4, number8 + value23, value9, value10, value11, value12);
            bridge_2815.method5(number7 + 16, value5, number8 + value23, number7 + 16, number4, number8 + value23, value9, value10, value11, value12);
            bridge_2815.method5(number7 + value23, value5, number8 + 16, number7 + value23, number4, number8 + 16, value9, value10, value11, value12);
         }
      } else {
         for (double value16 = value13; value16 < 16.0; value16 += value13) {
            bridge_2815.method5(number7 + value16, number3, number8, number7 + value16, number4, number8, value9, value10, value11, value12);
            bridge_2815.method5(number7, number3, number8 + value16, number7, number4, number8 + value16, value9, value10, value11, value12);
            bridge_2815.method5(number7 + 16, number3, number8 + value16, number7 + 16, number4, number8 + value16, value9, value10, value11, value12);
            bridge_2815.method5(number7 + value16, number3, number8 + 16, number7 + value16, number4, number8 + 16, value9, value10, value11, value12);
         }
      }

      for (double value24 = number3; value24 < number4; value24 += value13) {
         boolean flag18 = value24 % 16.0 == 0.0 && (Boolean)this.field13.get();
         value9 = ColorUtils.method9(flag18 ? this.field15.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F) : this.field12.IROHICIOOHIRCOCHOOCROHROIIRRIC(0.0F));
         value10 = ColorUtils.method9(flag18 ? this.field15.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F) : this.field12.HHIRRCHCHIIHIOHICHOOOHIRHRRCCR(0.0F));
         value11 = ColorUtils.method9(flag18 ? this.field15.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F) : this.field12.IHIRROIOORHHCOOCCOOHHHCHOCCORR(0.0F));
         value12 = ColorUtils.method9(flag18 ? this.field15.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F) : this.field12.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F));
         bridge_2815.method5(number7, value24, number8, number7 + 16, value24, number8, value9, value10, value11, value12);
         bridge_2815.method5(number7, value24, number8, number7, value24, number8 + 16, value9, value10, value11, value12);
         bridge_2815.method5(number7 + 16, value24, number8, number7 + 16, value24, number8 + 16, value9, value10, value11, value12);
         bridge_2815.method5(number7, value24, number8 + 16, number7 + 16, value24, number8 + 16, value9, value10, value11, value12);
      }

      bridge_2815.end();
   }

   public String getId() {
      return "CHUNK_BORDERS";
   }

   public void method3(boolean flag1) {
      Bridge.method9().bridge$setRenderChunkBorder(false);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      lightingextension231.method7(
         this.field9, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10, this.field11, this.field12})
      );
      lightingextension231.method7(this.field13, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14, this.field15}));
      lightingextension231.method7(this.field16, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17, this.field18}));
      this.field8.method3(() -> this.field19 = !this.field19);
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }
}
