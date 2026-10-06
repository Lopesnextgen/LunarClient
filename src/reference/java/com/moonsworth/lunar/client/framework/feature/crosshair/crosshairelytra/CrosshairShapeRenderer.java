package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairShape;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import javax.annotation.Nullable;
import lombok.Generated;

public class CrosshairShapeRenderer {
   private final CrosshairStyle field1;
   protected final EnumOption<CrosshairShape> field2 = (EnumOption<CrosshairShape>)OptionFactory.method10("crosshairShape", CrosshairShape.CROSS)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final IntegerOption field3 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairThickness").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 5))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final IntegerOption field4 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairWidth").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 16))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final IntegerOption field5 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairHeight").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 16))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final IntegerOption field6 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairGap").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 8))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field7 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("crosshairDot").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final IntegerOption field8 = (IntegerOption)((Data)((Data)OptionFactory.method4("dotSize").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 16))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dotCircle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dynamicDot").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("customDotColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "dotColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dotOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final FloatOption field14 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "dotOutlineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 1.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   protected void method1(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, CrosshairShapeDrawer crosshairelytra6) {
      mixinhelper_41.push();
      mixinhelper_41.method38(value2, value3, 0.0F);
      mixinhelper_41.scale(value4, value4, 1.0F);
      this.method2(mixinhelper_41, 0.0F, 0.0F, value5, crosshairelytra6, this.field1.method17(), true);
      mixinhelper_41.pop();
   }

   private void method2(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      switch ((CrosshairShape)this.field2.get()) {
         case CROSS:
            this.method4(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case CIRCLE:
            this.method5(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case ARROW:
            this.method6(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case TRIANGLE:
            this.method7(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case SQUARE:
            this.method8(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case DOT:
            this.method9(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case CIRCLE_DOT:
            this.method10(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
            break;
         case X:
            this.method11(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
      }

      if ((Boolean)this.field7.get() && this.method15()) {
         this.method3(mixinhelper_41, value2, value3, value4, crosshairelytra5, crosshairelytra6, flag7);
      }
   }

   private void method3(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      CrosshairShapeDrawer crosshairelytra8 = this.field11.get() ? new CrosshairShapeDrawer(this.field12, crosshairelytra5.method12(), crosshairelytra5.method13()) : crosshairelytra5;
      float value9 = ((Integer)this.field8.get()).intValue() / 2.0F + (this.field10.get() ? value4 / 2.0F : 0.0F) - 0.35F;
      CrosshairShapeDrawer crosshairelytra10 = this.field13.get() ? crosshairelytra6 : null;
      if ((Boolean)this.field9.get()) {
         if (crosshairelytra10 != null) {
            crosshairelytra10.method7(mixinhelper_41, value2 + 0.5F, value3 + 0.5F, value9 + 1.0F, (Float)this.field14.get() * 2.0F);
         }

         if (flag7) {
            crosshairelytra8.method6(mixinhelper_41, value2 + 0.5F, value3 + 0.5F, value9 + 1.0F);
         }
      } else {
         if (crosshairelytra10 != null) {
            crosshairelytra10.method5(mixinhelper_41, value2 + 0.5F - value9 / 2.0F, value3 + 0.5F - value9 / 2.0F, value9, value9, (Float)this.field14.get());
         }

         if (flag7) {
            crosshairelytra8.method4(mixinhelper_41, value2 + 0.5F - value9 / 2.0F, value3 + 0.5F - value9 / 2.0F, value9, value9);
         }
      }
   }

   private void method4(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      int number8 = (Integer)this.field4.get();
      int number9 = (Integer)this.field5.get();
      float value10 = ((Integer)this.field6.get()).intValue() + value4;
      int number11 = (Integer)this.field3.get();
      float value12 = number11 / 2.0F - 0.5F;
      float value13 = number11 / 2.0F + 0.5F;
      if (crosshairelytra6 != null) {
         float value14 = this.field1.method19();
         float value15 = number8;
         float value16 = value12 + value13;
         crosshairelytra6.method5(mixinhelper_41, value2 - number8 - value10 - value12, value3 - value12, value15, value16, value14);
         crosshairelytra6.method5(mixinhelper_41, value2 + value10 + value13, value3 - value12, value15, value16, value14);
         crosshairelytra6.method5(mixinhelper_41, value2 - value12, value3 - number9 - value10 - value12, value12 + value13, number9, value14);
         crosshairelytra6.method5(mixinhelper_41, value2 - value12, value3 + value10 + value13, value12 + value13, number9, value14);
      }

      if (flag7) {
         crosshairelytra5.method4(mixinhelper_41, value2 - number8 - value10 - value12, value3 - value12, number8, value12 + value13);
         crosshairelytra5.method4(mixinhelper_41, value2 + value10 + value13, value3 - value12, number8, value12 + value13);
         crosshairelytra5.method4(mixinhelper_41, value2 - value12, value3 - number9 - value10 - value12, value12 + value13, number9);
         crosshairelytra5.method4(mixinhelper_41, value2 - value12, value3 + value10 + value13, value12 + value13, number9);
      }
   }

   private void method5(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      value2 += 0.5F;
      value3 += 0.5F;
      float value8 = ((Integer)this.field3.get()).intValue();
      float value9 = ((Integer)this.field6.get()).intValue() + value4 + 1.0F;
      if (crosshairelytra6 != null) {
         crosshairelytra6.method7(mixinhelper_41, value2, value3, value9 + value8, this.field1.method21());
      }

      if (flag7) {
         crosshairelytra5.method7(mixinhelper_41, value2, value3, value9, value8);
      }
   }

   private void method6(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      value2 += 0.5F;
      float value8 = ((Integer)this.field3.get()).intValue();
      float value9 = value8 / 2.0F;
      float value10 = ((Integer)this.field4.get()).intValue();
      float value11 = ((Integer)this.field5.get()).intValue();
      float value12 = ((Integer)this.field6.get()).intValue() + value4;
      if (crosshairelytra6 != null) {
         float value13 = this.field1.method21() + 0.05F;
         float value14 = value13 / 2.0F;
         float value15 = value3 - value13;
         crosshairelytra6.method8(mixinhelper_41, value2 - value10 - value12, value15 + value11 + value14, value2, value15 + value14, value13);
         crosshairelytra6.method8(mixinhelper_41, value2, value15 + value14, value2 + value10 + value12, value15 + value11 + value14, value13);
         value15 += value13 + value8;
         crosshairelytra6.method8(mixinhelper_41, value2 - value10 - value12, value15 + value11 + value14, value2, value15 + value14, value13);
         crosshairelytra6.method8(mixinhelper_41, value2, value15 + value14, value2 + value10 + value12, value15 + value11 + value14, value13);
         value15 -= value8;
         float value16 = value14 * (-value11 / (value10 + value12));
         crosshairelytra6.method8(mixinhelper_41, value2 - value10 - value12 - value14, value15 + value11 + value9 - value16, value2 - value10 - value12, value15 + value11 + value9, value13 * 2.0F + value8);
         crosshairelytra6.method8(mixinhelper_41, value2 + value10 + value12, value15 + value11 + value9, value2 + value10 + value12 + value14, value15 + value11 + value9 - value16, value13 * 2.0F + value8);
      }

      if (flag7) {
         crosshairelytra5.method8(mixinhelper_41, value2 - value10 - value12, value3 + value11 + value9, value2, value3 + value9, value8);
         crosshairelytra5.method8(mixinhelper_41, value2, value3 + value9, value2 + value10 + value12, value3 + value11 + value9, value8);
      }
   }

   private void method7(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      float value8 = ((Integer)this.field3.get()).intValue();
      float value9 = ((Integer)this.field6.get()).intValue() + value4;
      float value10 = ((Integer)this.field4.get()).intValue() + value9;
      float value11 = ((Integer)this.field5.get()).intValue() + value9;
      value2 += 0.5F;
      value3 -= value9 / 4.0F;
      if (crosshairelytra6 != null) {
         float value12 = value8 + this.field1.method21() * 2.0F;
         crosshairelytra6.method9(mixinhelper_41, value2, value3, value10, value11, value12);
      }

      if (flag7) {
         crosshairelytra5.method9(mixinhelper_41, value2, value3, value10, value11, value8);
      }
   }

   private void method8(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      value2 += 0.5F;
      value3 += 0.5F;
      float value8 = ((Integer)this.field3.get()).intValue() / 2.0F;
      float value9 = ((Integer)this.field6.get()).intValue() + value4;
      float value10 = ((Integer)this.field4.get()).intValue() + value9;
      float value11 = ((Integer)this.field5.get()).intValue() + value9;
      if (crosshairelytra6 != null) {
         crosshairelytra6.method5(mixinhelper_41, value2 - value10 - value8 / 2.0F, value3 - value11 - value8 / 2.0F, value10 * 2.0F + value8, value11 * 2.0F + value8, this.field1.method21());
      }

      if (flag7) {
         crosshairelytra5.method4(mixinhelper_41, value2 - value10 - value8 / 2.0F, value3 - value11 - value8 / 2.0F, value8, value11 * 2.0F + value8);
         crosshairelytra5.method4(mixinhelper_41, value2 + value10 - value8 / 2.0F, value3 - value11 - value8 / 2.0F, value8, value11 * 2.0F + value8);
         crosshairelytra5.method4(mixinhelper_41, value2 - value10 - value8 / 2.0F, value3 - value11 - value8 / 2.0F, value10 * 2.0F + value8, value8);
         crosshairelytra5.method4(mixinhelper_41, value2 - value10 - value8 / 2.0F, value3 + value11 - value8 / 2.0F, value10 * 2.0F + value8, value8);
      }
   }

   private void method9(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      value2 += 0.5F;
      value3 += 0.5F;
      float value8 = ((Integer)this.field4.get()).intValue() + value4;
      float value9 = ((Integer)this.field5.get()).intValue() + value4;
      if (crosshairelytra6 != null) {
         float value10 = this.field1.method21();
         crosshairelytra6.method5(mixinhelper_41, value2 - value8 / 2.0F, value3 - value9 / 2.0F, value8, value9, value10);
      }

      if (flag7) {
         crosshairelytra5.method4(mixinhelper_41, value2 - value8 / 2.0F, value3 - value9 / 2.0F, value8, value9);
      }
   }

   private void method10(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      value2 += 0.5F;
      value3 += 0.5F;
      float value8 = ((Integer)this.field3.get()).intValue() + value4 + 1.0F;
      if (crosshairelytra6 != null) {
         float value9 = this.field1.method21();
         crosshairelytra6.method7(mixinhelper_41, value2, value3, value8, value9);
      }

      if (flag7) {
         crosshairelytra5.method6(mixinhelper_41, value2, value3, value8);
      }
   }

   private void method11(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, CrosshairShapeDrawer crosshairelytra5, @Nullable CrosshairShapeDrawer crosshairelytra6, boolean flag7) {
      mixinhelper_41.push();
      mixinhelper_41.method38(value2 + 0.5F, value3 - 0.15F, 0.0F);
      mixinhelper_41.method42(45.0F);
      this.method4(mixinhelper_41, 0.0F, 0.0F, value4, crosshairelytra5, crosshairelytra6, flag7);
      mixinhelper_41.pop();
   }

   protected boolean method12() {
      CrosshairShape gui2extension41 = (CrosshairShape)this.field2.get();
      return gui2extension41 != CrosshairShape.CIRCLE && gui2extension41 != CrosshairShape.CIRCLE_DOT;
   }

   protected boolean method13() {
      CrosshairShape gui2extension41 = (CrosshairShape)this.field2.get();
      return gui2extension41 != CrosshairShape.DOT;
   }

   protected boolean method14() {
      CrosshairShape gui2extension41 = (CrosshairShape)this.field2.get();
      return gui2extension41 != CrosshairShape.DOT && gui2extension41 != CrosshairShape.CIRCLE_DOT;
   }

   protected boolean method15() {
      CrosshairShape gui2extension41 = (CrosshairShape)this.field2.get();
      return gui2extension41 != CrosshairShape.ARROW && gui2extension41 != CrosshairShape.DOT && gui2extension41 != CrosshairShape.CIRCLE_DOT;
   }

   @Generated
   public CrosshairShapeRenderer(CrosshairStyle crosshairchildmod1) {
      this.field1 = crosshairchildmod1;
   }

   @Generated
   public IntegerOption method16() {
      return this.field3;
   }
}
