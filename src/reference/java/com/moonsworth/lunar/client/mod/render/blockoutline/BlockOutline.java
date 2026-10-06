package com.moonsworth.lunar.client.mod.render.blockoutline;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.blockoutline.Blockoutline;
import com.moonsworth.lunar.client.framework.feature.blockoutline.BlockOutlineMode;
import com.moonsworth.lunar.client.framework.feature.blockoutline.BlockOverlayMode;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderBlockOutline;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderBlockOutline.EventRenderBlockOutlineModern;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderBlockOutline.EventRenderBlockOutlineLegacy;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.List;
import lombok.Generated;

public class BlockOutline extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutline")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field9 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("blockOutlineWidth")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(2.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final EnumOption<BlockOutlineMode> field10 = (EnumOption<BlockOutlineMode>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "blockOutlineMode", BlockOutlineMode.STATIC
      )
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "blockOutlineColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1711276032))
      .method4(arg1 -> arg1.method17(() -> this.field10.get() != BlockOutlineMode.RAINBOW))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "blockOutlineColorEnd"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1711276032))
      .method31();
   private final ToggleOption field13 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutlineInterpolateAlpha")
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutlineAccurate")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutlineTraversal")
      .method31();
   private final FloatOption field16 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "blockOutlineTraversalSpeed"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.15F, 5.0F))
      .method31();
   private final ToggleOption field17 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOverlay")
      .method31();
   private final EnumOption<BlockOverlayMode> field18 = (EnumOption<BlockOverlayMode>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "blockOverlayMode", BlockOverlayMode.STATIC
      )
      .method31();
   private final ColorOption field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "blockOverlayColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436207616))
      .method4(arg1 -> arg1.method17(() -> this.field18.get() != BlockOverlayMode.RAINBOW))
      .method31();
   private final ColorOption field20 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "blockOverlayColorEnd"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436207616))
      .method31();
   private final ToggleOption field21 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOverlayInterpolateAlpha")
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOverlayAccurate")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field23 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOverlayTraversal")
      .method31();
   private final FloatOption field24 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "blockOverlayTraversalSpeed"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.15F, 5.0F))
      .method31();
   private final ToggleOption field25 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutlineSide")
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "blockOutlineShowHiddenFoliage"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("blockOutlineMultiBlock")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showInSpectator")
      .method31();
   private int field29;
   private int field30;

   public BlockOutline() {
      super(false);
      this.handle(EventRenderBlockOutlineLegacy.class, this::method2);
      this.handle(EventRenderBlockOutlineModern.class, this::method2);
      this.handle(EventTick.class, this::method1);
   }

   public String getId() {
      return "BLOCK_OUTLINE";
   }

   private void method1(EventTick highlightimpl21) {
      this.field29++;
      if (this.field29 >= 60.0F * (Float)this.field16.get()) {
         this.field29 = 0;
      }

      this.field30++;
      if (this.field30 >= 60.0F * (Float)this.field24.get()) {
         this.field30 = 0;
      }
   }

   private void method2(EventRenderBlockOutline highlightimpl171) {
      highlightimpl171.cancel();
      if (!(Boolean)this.field26.get() && Ref.method32()) {
         MovingObjectPositionBridge horsestats212 = this.mc.bridge$getObjectMouseOver();
         Bridge3_23 bridge3_233 = Ref.method8().RHIRRICCRHHHIIHHIHHOHRCHIOORCC(horsestats212.bridge$getBlockPosition());
         if (bridge3_233.bridge$isFoliage()) {
            return;
         }
      }

      if ((Boolean)this.field17.get()) {
         switch ((BlockOverlayMode)this.field18.get()) {
            case STATIC:
               this.method3(highlightimpl171, this.field19, this::method9);
               break;
            case RAINBOW:
               this.method7(highlightimpl171, this.field19, (Boolean)this.field23.get(), this::method9);
               break;
            case BLEND:
               this.method6(
                  highlightimpl171,
                  this.field19,
                  this.field20,
                  (Boolean)this.field23.get(),
                  this.field30,
                  (Float)this.field24.get(),
                  (Boolean)this.field21.get(),
                  this::method9
               );
               break;
            case INVERTED:
               this.method4(highlightimpl171, this::method9);
               break;
            case DARKEN:
               this.method5(highlightimpl171, this::method9);
         }
      }

      if ((Boolean)this.field8.get()) {
         switch ((BlockOutlineMode)this.field10.get()) {
            case STATIC:
               this.method3(highlightimpl171, this.field11, this::method8);
               break;
            case RAINBOW:
               this.method7(highlightimpl171, this.field11, (Boolean)this.field15.get(), this::method8);
               break;
            case BLEND:
               this.method6(
                  highlightimpl171,
                  this.field11,
                  this.field12,
                  (Boolean)this.field15.get(),
                  this.field29,
                  (Float)this.field16.get(),
                  (Boolean)this.field13.get(),
                  this::method8
               );
         }
      }
   }

   private void method3(EventRenderBlockOutline highlightimpl171, ColorOption lightingextension42222, BlockOutline.Extension extension3) {
      int number4 = lightingextension42222.method1(0.0F);
      float value5 = ColorUtils.method5(number4);
      float value6 = ColorUtils.method6(number4);
      float value7 = ColorUtils.method7(number4);
      float value8 = ColorUtils.method8(number4);
      AbstractRenderContext bridgeextension_99 = highlightimpl171.method1();
      LcuiScreen.method89(bridgeextension_99);
      extension3.render(highlightimpl171, bridgeextension_99, value5, value6, value7, value8, value5, value6, value7, value8, false);
      LcuiScreen.method90(bridgeextension_99);
   }

   private void method4(EventRenderBlockOutline highlightimpl171, BlockOutline.Extension extension2) {
      AbstractRenderContext bridgeextension_93 = highlightimpl171.method1();
      bridgeextension_93.method14();
      bridgeextension_93.method33();
      bridgeextension_93.method2(GlBlendFactor.GL_DST_COLOR, GlBlendFactor.GL_ONE_MINUS_DST_COLOR);
      extension2.render(highlightimpl171, bridgeextension_93, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, false);
      bridgeextension_93.method15();
   }

   private void method5(EventRenderBlockOutline highlightimpl171, BlockOutline.Extension extension2) {
      AbstractRenderContext bridgeextension_93 = highlightimpl171.method1();
      LcuiScreen.method89(bridgeextension_93);
      extension2.render(highlightimpl171, bridgeextension_93, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, false);
      LcuiScreen.method90(bridgeextension_93);
   }

   private void method6(
      EventRenderBlockOutline highlightimpl171,
      ColorOption lightingextension42222,
      ColorOption lightingextension42223,
      boolean flag4,
      int number5,
      float value6,
      boolean flag7,
      BlockOutline.Extension extension8
   ) {
      int number9 = lightingextension42222.method1(0.0F);
      float value10 = ColorUtils.method5(number9);
      float value11 = ColorUtils.method6(number9);
      float value12 = ColorUtils.method7(number9);
      float value13 = ColorUtils.method8(number9);
      int number14 = lightingextension42223.method1(50.0F);
      float value15 = ColorUtils.method5(number14);
      float value16 = ColorUtils.method6(number14);
      float value17 = ColorUtils.method7(number14);
      float value18 = ColorUtils.method9(lightingextension42223.CCOIHCHRIHICROIOOCRRRHORHIRIOO(0.0F));
      boolean flag21 = flag7 && value13 != value18;
      float value19;
      float value20;
      if (!flag4 && !flag21) {
         value20 = value13;
         value19 = value13;
      } else {
         float value22 = (number5 + highlightimpl171.method1().method28()) / (30.0F * value6);
         value22 = value22 >= 1.0F ? 2.0F - value22 : value22;
         if (flag21) {
            value19 = MathUtils.lerp(value13, value18, value22);
            value20 = MathUtils.lerp(value13, value18, 1.0F - value22);
         } else {
            value20 = value13;
            value19 = value13;
         }

         if (flag4) {
            float value23 = MathUtils.lerp(value10, value15, value22);
            value15 = MathUtils.lerp(value15, value10, value22);
            value10 = value23;
            float value24 = MathUtils.lerp(value11, value16, value22);
            value16 = MathUtils.lerp(value16, value11, value22);
            value11 = value24;
            float value25 = MathUtils.lerp(value12, value17, value22);
            value17 = MathUtils.lerp(value17, value12, value22);
            value12 = value25;
         }
      }

      AbstractRenderContext bridgeextension_927 = highlightimpl171.method1();
      LcuiScreen.method89(bridgeextension_927);
      extension8.render(highlightimpl171, bridgeextension_927, value10, value11, value12, value19, value15, value16, value17, value20, true);
      LcuiScreen.method90(bridgeextension_927);
   }

   private void method7(EventRenderBlockOutline highlightimpl171, ColorOption lightingextension42222, boolean flag3, BlockOutline.Extension extension4) {
      int number5 = lightingextension42222.method1(0.0F);
      float value6 = ColorUtils.method5(number5);
      float value7 = ColorUtils.method6(number5);
      float value8 = ColorUtils.method7(number5);
      float value9 = ColorUtils.method8(number5);
      float value10;
      float value11;
      float value12;
      if (flag3) {
         int number13 = lightingextension42222.method1(50.0F);
         value10 = ColorUtils.method5(number13);
         value11 = ColorUtils.method6(number13);
         value12 = ColorUtils.method7(number13);
      } else {
         value10 = 1.0F - value6;
         value11 = 1.0F - value7;
         value12 = 1.0F - value8;
      }

      AbstractRenderContext bridgeextension_914 = highlightimpl171.method1();
      LcuiScreen.method89(bridgeextension_914);
      extension4.render(highlightimpl171, bridgeextension_914, value6, value7, value8, value9, value10, value11, value12, value9, true);
      LcuiScreen.method90(bridgeextension_914);
   }

   private void method8(
      EventRenderBlockOutline highlightimpl171,
      AbstractRenderContext bridgeextension_92,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      float value10,
      boolean flag11
   ) {
      HorsestatsType_2 horsestatstype_212 = null;
      if ((Boolean)this.field25.get()) {
         MovingObjectPositionBridge horsestats2113 = this.mc.bridge$getObjectMouseOver();
         if (horsestats2113.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
            horsestatstype_212 = horsestats2113.bridge$getSideHit();
         }
      }

      if (Ref.MC_VERSION <= 5) {
         BufferBuilderBridge bridge_2816 = bridgeextension_92.method11((Float)this.field9.get());
         bridge_2816.method2(value3, value4, value5, value6);
         Blockoutline.method1(bridge_2816, horsestatstype_212, ((EventRenderBlockOutlineLegacy)highlightimpl171).method2(), value3, value4, value5, value6, value7, value8, value9, value10);
         bridge_2816.end();
      } else {
         EventRenderBlockOutlineModern data317 = (EventRenderBlockOutlineModern)highlightimpl171;
         Itemcounter_4 itemcounter_414 = data317.method3();
         if ((Boolean)this.field27.get()) {
            itemcounter_414 = Blockoutline.method11(itemcounter_414);
         }

         bridgeextension_92.method18();
         if ((Boolean)this.field14.get()) {
            if (flag11) {
               BufferBuilderBridge bridge_2818 = bridgeextension_92.method11((Float)this.field9.get());
               Blockoutline.method4(data317.method2(), bridge_2818, horsestatstype_212, itemcounter_414, value3, value4, value5, value6, value7, value8, value9, value10);
               bridge_2818.end();
               return;
            }

            BufferBuilderBridge bridge_2815 = bridgeextension_92.method11((Float)this.field9.get());
            Blockoutline.method5(data317.method2(), bridge_2815, horsestatstype_212, itemcounter_414, value3, value4, value5, value6, value7, value8, value9, value10);
            bridge_2815.end();
         } else {
            BufferBuilderBridge bridge_2819 = bridgeextension_92.method11((Float)this.field9.get());
            bridge_2819.method2(value3, value4, value5, value6);
            Blockoutline.method1(bridge_2819, horsestatstype_212, itemcounter_414.bridge$bounds(), value3, value4, value5, value6, value7, value8, value9, value10);
            bridge_2819.end();
         }

         bridgeextension_92.method19();
      }
   }

   private void method9(
      EventRenderBlockOutline highlightimpl171,
      AbstractRenderContext bridgeextension_92,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      float value10,
      boolean flag11
   ) {
      HorsestatsType_2 horsestatstype_212 = null;
      if ((Boolean)this.field25.get()) {
         MovingObjectPositionBridge horsestats2113 = this.mc.bridge$getObjectMouseOver();
         if (horsestats2113.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK)) {
            horsestatstype_212 = horsestats2113.bridge$getSideHit();
         }
      }

      if (Ref.MC_VERSION <= 5) {
         Blockoutline.method9(
            bridgeextension_92, horsestatstype_212, ((EventRenderBlockOutlineLegacy)highlightimpl171).method2(), value3, value4, value5, value6, value7, value8, value9, value10, ((BlockOverlayMode)this.field18.get()).renderType()
         );
      } else {
         EventRenderBlockOutlineModern data318 = (EventRenderBlockOutlineModern)highlightimpl171;
         Itemcounter_4 itemcounter_414 = data318.method3();
         if ((Boolean)this.field27.get()) {
            itemcounter_414 = Blockoutline.method11(itemcounter_414);
         }

         if ((Boolean)this.field22.get()) {
            if (!flag11) {
               for (AxisAlignedBBBridge horsestats1217 : itemcounter_414.bridge$toAabbs()) {
                  Blockoutline.method9(bridgeextension_92, horsestatstype_212, horsestats1217, value3, value4, value5, value6, value7, value8, value9, value10, ((BlockOverlayMode)this.field18.get()).renderType());
               }

               return;
            }

            List list15 = itemcounter_414.bridge$toAabbs();
            if (list15.size() != 1) {
               Blockoutline.method7(bridgeextension_92, horsestatstype_212, itemcounter_414, value3, value4, value5, value6, value7, value8, value9, value10, ((BlockOverlayMode)this.field18.get()).renderType());
               return;
            }
         }

         Blockoutline.method9(
            bridgeextension_92, horsestatstype_212, itemcounter_414.bridge$bounds(), value3, value4, value5, value6, value7, value8, value9, value10, ((BlockOverlayMode)this.field18.get()).renderType()
         );
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "blockOutline",
         arg1x -> arg1x.method7(
            this.field8,
            arg1xx -> {
               arg1xx.method9(new ClientOption[]{this.field9, this.field10, this.field11});
               arg1xx.method9(new ClientOption[]{this.field12}).method3(() -> this.field10.get() != BlockOutlineMode.BLEND);
               arg1xx.method9(new ClientOption[]{this.field13});
               arg1xx.method9(new ClientOption[]{this.field14}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
               arg1xx.method7(this.field15, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field16}))
                  .method3(() -> this.field10.get() != BlockOutlineMode.RAINBOW && this.field10.get() != BlockOutlineMode.BLEND);
            }
         )
      );
      lightingextension231.method1(
         "blockOverlay",
         arg1x -> arg1x.method7(
            this.field17,
            arg1xx -> {
               arg1xx.method9(new ClientOption[]{this.field18});
               arg1xx.method9(new ClientOption[]{this.field19})
                  .method3(() -> this.field18.get() == BlockOverlayMode.INVERTED || this.field18.get() == BlockOverlayMode.DARKEN);
               arg1xx.method9(new ClientOption[]{this.field20, this.field21})
                  .method3(() -> this.field18.get() != BlockOverlayMode.BLEND);
               arg1xx.method9(new ClientOption[]{this.field22}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
               arg1xx.method7(this.field23, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field24}))
                  .method3(() -> this.field18.get() != BlockOverlayMode.RAINBOW && this.field18.get() != BlockOverlayMode.BLEND);
            }
         )
      );
      ((SettingsSectionImpl)lightingextension231.method1("extraRenderOptions", arg1x -> {
         arg1x.method9(new ClientOption[]{this.field25, this.field26});
         arg1x.method9(new ClientOption[]{this.field27, this.field28}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
      })).method2(new ClientOption[]{this.field8, this.field17});
      this.field10.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x == BlockOutlineMode.RAINBOW) {
            this.field11.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(true);
         }
      });
      this.field18.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x == BlockOverlayMode.RAINBOW) {
            this.field19.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(true);
         }
      });
   }

   @Generated
   public ToggleOption method13() {
      return this.field28;
   }

   @FunctionalInterface
   private interface Extension {
      void render(
         EventRenderBlockOutline highlightimpl171,
         AbstractRenderContext bridgeextension_92,
         float value3,
         float value4,
         float value5,
         float value6,
         float value7,
         float value8,
         float value9,
         float value10,
         boolean flag11
      );
   }
}
