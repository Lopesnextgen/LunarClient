package com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.crosshair.mixin.Gui2Extension4;
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

public class Crosshairelytra2 {
   private final CrosshairStyle field1;
   protected final EnumOption<Gui2Extension4> field2 = (EnumOption<Gui2Extension4>)OptionFactory.method10("crosshairShape", Gui2Extension4.CROSS)
      .method31();
   protected final IntegerOption field3 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairThickness").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .method7(1, 5))
      .method31();
   protected final IntegerOption field4 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairWidth").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .method7(0, 16))
      .method31();
   protected final IntegerOption field5 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairHeight").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .method7(0, 16))
      .method31();
   protected final IntegerOption field6 = (IntegerOption)((Data)((Data)OptionFactory.method4("crosshairGap").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .method7(0, 8))
      .method31();
   protected final ToggleOption field7 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("crosshairDot").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final IntegerOption field8 = (IntegerOption)((Data)((Data)OptionFactory.method4("dotSize").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 16))
      .method31();
   protected final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dotCircle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dynamicDot").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("customDotColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "dotColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dotOutline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   protected final FloatOption field14 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "dotOutlineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.0F, 1.0F))
      .method31();

   protected void method1(MixinHelper_4 var1, float var2, float var3, float var4, float var5, Crosshairelytra var6) {
      var1.push();
      var1.method38(var2, var3, 0.0F);
      var1.scale(var4, var4, 1.0F);
      this.method2(var1, 0.0F, 0.0F, var5, var6, this.field1.method17(), true);
      var1.pop();
   }

   private void method2(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      switch ((Gui2Extension4)this.field2.get()) {
         case CROSS:
            this.method4(var1, var2, var3, var4, var5, var6, var7);
            break;
         case CIRCLE:
            this.method5(var1, var2, var3, var4, var5, var6, var7);
            break;
         case ARROW:
            this.method6(var1, var2, var3, var4, var5, var6, var7);
            break;
         case TRIANGLE:
            this.method7(var1, var2, var3, var4, var5, var6, var7);
            break;
         case SQUARE:
            this.method8(var1, var2, var3, var4, var5, var6, var7);
            break;
         case DOT:
            this.method9(var1, var2, var3, var4, var5, var6, var7);
            break;
         case CIRCLE_DOT:
            this.method10(var1, var2, var3, var4, var5, var6, var7);
            break;
         case X:
            this.method11(var1, var2, var3, var4, var5, var6, var7);
      }

      if ((Boolean)this.field7.get() && this.method15()) {
         this.method3(var1, var2, var3, var4, var5, var6, var7);
      }
   }

   private void method3(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      Crosshairelytra var8 = this.field11.get() ? new Crosshairelytra(this.field12, var5.method12(), var5.method13()) : var5;
      float var9 = ((Integer)this.field8.get()).intValue() / 2.0F + (this.field10.get() ? var4 / 2.0F : 0.0F) - 0.35F;
      Crosshairelytra var10 = this.field13.get() ? var6 : null;
      if ((Boolean)this.field9.get()) {
         if (var10 != null) {
            var10.method7(var1, var2 + 0.5F, var3 + 0.5F, var9 + 1.0F, (Float)this.field14.get() * 2.0F);
         }

         if (var7) {
            var8.method6(var1, var2 + 0.5F, var3 + 0.5F, var9 + 1.0F);
         }
      } else {
         if (var10 != null) {
            var10.method5(var1, var2 + 0.5F - var9 / 2.0F, var3 + 0.5F - var9 / 2.0F, var9, var9, (Float)this.field14.get());
         }

         if (var7) {
            var8.method4(var1, var2 + 0.5F - var9 / 2.0F, var3 + 0.5F - var9 / 2.0F, var9, var9);
         }
      }
   }

   private void method4(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      int var8 = (Integer)this.field4.get();
      int var9 = (Integer)this.field5.get();
      float var10 = ((Integer)this.field6.get()).intValue() + var4;
      int var11 = (Integer)this.field3.get();
      float var12 = var11 / 2.0F - 0.5F;
      float var13 = var11 / 2.0F + 0.5F;
      if (var6 != null) {
         float var14 = this.field1.method19();
         float var15 = var8;
         float var16 = var12 + var13;
         var6.method5(var1, var2 - var8 - var10 - var12, var3 - var12, var15, var16, var14);
         var6.method5(var1, var2 + var10 + var13, var3 - var12, var15, var16, var14);
         var6.method5(var1, var2 - var12, var3 - var9 - var10 - var12, var12 + var13, var9, var14);
         var6.method5(var1, var2 - var12, var3 + var10 + var13, var12 + var13, var9, var14);
      }

      if (var7) {
         var5.method4(var1, var2 - var8 - var10 - var12, var3 - var12, var8, var12 + var13);
         var5.method4(var1, var2 + var10 + var13, var3 - var12, var8, var12 + var13);
         var5.method4(var1, var2 - var12, var3 - var9 - var10 - var12, var12 + var13, var9);
         var5.method4(var1, var2 - var12, var3 + var10 + var13, var12 + var13, var9);
      }
   }

   private void method5(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var2 += 0.5F;
      var3 += 0.5F;
      float var8 = ((Integer)this.field3.get()).intValue();
      float var9 = ((Integer)this.field6.get()).intValue() + var4 + 1.0F;
      if (var6 != null) {
         var6.method7(var1, var2, var3, var9 + var8, this.field1.method21());
      }

      if (var7) {
         var5.method7(var1, var2, var3, var9, var8);
      }
   }

   private void method6(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var2 += 0.5F;
      float var8 = ((Integer)this.field3.get()).intValue();
      float var9 = var8 / 2.0F;
      float var10 = ((Integer)this.field4.get()).intValue();
      float var11 = ((Integer)this.field5.get()).intValue();
      float var12 = ((Integer)this.field6.get()).intValue() + var4;
      if (var6 != null) {
         float var13 = this.field1.method21() + 0.05F;
         float var14 = var13 / 2.0F;
         float var15 = var3 - var13;
         var6.method8(var1, var2 - var10 - var12, var15 + var11 + var14, var2, var15 + var14, var13);
         var6.method8(var1, var2, var15 + var14, var2 + var10 + var12, var15 + var11 + var14, var13);
         var15 += var13 + var8;
         var6.method8(var1, var2 - var10 - var12, var15 + var11 + var14, var2, var15 + var14, var13);
         var6.method8(var1, var2, var15 + var14, var2 + var10 + var12, var15 + var11 + var14, var13);
         var15 -= var8;
         float var16 = var14 * (-var11 / (var10 + var12));
         var6.method8(var1, var2 - var10 - var12 - var14, var15 + var11 + var9 - var16, var2 - var10 - var12, var15 + var11 + var9, var13 * 2.0F + var8);
         var6.method8(var1, var2 + var10 + var12, var15 + var11 + var9, var2 + var10 + var12 + var14, var15 + var11 + var9 - var16, var13 * 2.0F + var8);
      }

      if (var7) {
         var5.method8(var1, var2 - var10 - var12, var3 + var11 + var9, var2, var3 + var9, var8);
         var5.method8(var1, var2, var3 + var9, var2 + var10 + var12, var3 + var11 + var9, var8);
      }
   }

   private void method7(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      float var8 = ((Integer)this.field3.get()).intValue();
      float var9 = ((Integer)this.field6.get()).intValue() + var4;
      float var10 = ((Integer)this.field4.get()).intValue() + var9;
      float var11 = ((Integer)this.field5.get()).intValue() + var9;
      var2 += 0.5F;
      var3 -= var9 / 4.0F;
      if (var6 != null) {
         float var12 = var8 + this.field1.method21() * 2.0F;
         var6.method9(var1, var2, var3, var10, var11, var12);
      }

      if (var7) {
         var5.method9(var1, var2, var3, var10, var11, var8);
      }
   }

   private void method8(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var2 += 0.5F;
      var3 += 0.5F;
      float var8 = ((Integer)this.field3.get()).intValue() / 2.0F;
      float var9 = ((Integer)this.field6.get()).intValue() + var4;
      float var10 = ((Integer)this.field4.get()).intValue() + var9;
      float var11 = ((Integer)this.field5.get()).intValue() + var9;
      if (var6 != null) {
         var6.method5(var1, var2 - var10 - var8 / 2.0F, var3 - var11 - var8 / 2.0F, var10 * 2.0F + var8, var11 * 2.0F + var8, this.field1.method21());
      }

      if (var7) {
         var5.method4(var1, var2 - var10 - var8 / 2.0F, var3 - var11 - var8 / 2.0F, var8, var11 * 2.0F + var8);
         var5.method4(var1, var2 + var10 - var8 / 2.0F, var3 - var11 - var8 / 2.0F, var8, var11 * 2.0F + var8);
         var5.method4(var1, var2 - var10 - var8 / 2.0F, var3 - var11 - var8 / 2.0F, var10 * 2.0F + var8, var8);
         var5.method4(var1, var2 - var10 - var8 / 2.0F, var3 + var11 - var8 / 2.0F, var10 * 2.0F + var8, var8);
      }
   }

   private void method9(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var2 += 0.5F;
      var3 += 0.5F;
      float var8 = ((Integer)this.field4.get()).intValue() + var4;
      float var9 = ((Integer)this.field5.get()).intValue() + var4;
      if (var6 != null) {
         float var10 = this.field1.method21();
         var6.method5(var1, var2 - var8 / 2.0F, var3 - var9 / 2.0F, var8, var9, var10);
      }

      if (var7) {
         var5.method4(var1, var2 - var8 / 2.0F, var3 - var9 / 2.0F, var8, var9);
      }
   }

   private void method10(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var2 += 0.5F;
      var3 += 0.5F;
      float var8 = ((Integer)this.field3.get()).intValue() + var4 + 1.0F;
      if (var6 != null) {
         float var9 = this.field1.method21();
         var6.method7(var1, var2, var3, var8, var9);
      }

      if (var7) {
         var5.method6(var1, var2, var3, var8);
      }
   }

   private void method11(MixinHelper_4 var1, float var2, float var3, float var4, Crosshairelytra var5, @Nullable Crosshairelytra var6, boolean var7) {
      var1.push();
      var1.method38(var2 + 0.5F, var3 - 0.15F, 0.0F);
      var1.method42(45.0F);
      this.method4(var1, 0.0F, 0.0F, var4, var5, var6, var7);
      var1.pop();
   }

   protected boolean method12() {
      Gui2Extension4 var1 = (Gui2Extension4)this.field2.get();
      return var1 != Gui2Extension4.CIRCLE && var1 != Gui2Extension4.CIRCLE_DOT;
   }

   protected boolean method13() {
      Gui2Extension4 var1 = (Gui2Extension4)this.field2.get();
      return var1 != Gui2Extension4.DOT;
   }

   protected boolean method14() {
      Gui2Extension4 var1 = (Gui2Extension4)this.field2.get();
      return var1 != Gui2Extension4.DOT && var1 != Gui2Extension4.CIRCLE_DOT;
   }

   protected boolean method15() {
      Gui2Extension4 var1 = (Gui2Extension4)this.field2.get();
      return var1 != Gui2Extension4.ARROW && var1 != Gui2Extension4.DOT && var1 != Gui2Extension4.CIRCLE_DOT;
   }

   @Generated
   public Crosshairelytra2(CrosshairStyle var1) {
      this.field1 = var1;
   }

   @Generated
   public IntegerOption method16() {
      return this.field3;
   }
}
