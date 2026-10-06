package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.FloatNumberRange;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import com.moonsworth.lunar.client.render.particle.Min;
import com.moonsworth.lunar.client.render.particle.Max;

public abstract class FloatSliderOptionBuilder<B extends FloatSliderOptionBuilder<B, O>, O extends ClientOption<Float>>
   extends OptionBuilderBase<B, O, Float>
   implements SteppedNumberRangeConfigurator<B, Float> {
   protected float field14 = this.method10();
   protected float min = -Float.MAX_VALUE;
   protected float max = Float.MAX_VALUE;
   protected int field15;
   protected boolean field16 = true;
   protected boolean field17 = true;

   protected FloatSliderOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected float method10() {
      return 0.0F;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.SLIDER;
   }

   @Override
   protected Codec<Float> method3() {
      return Codec.FLOAT;
   }

   @Contract("_->this")
   public B method4(float var1) {
      this.field14 = var1;
      return (B)this;
   }

   public B method5(boolean var1, boolean var2) {
      this.field16 = var1;
      this.field17 = var2;
      return (B)this;
   }

   public B method6(int var1) {
      this.field15 = var1;
      return (B)this;
   }

   @Deprecated
   public B method7(Float var1, Float var2) {
      return this.method8(var1, var2);
   }

   @Contract("_,_->this")
   public B method8(float var1, float var2) {
      this.min = var1;
      this.max = var2;
      if (this.min > this.max) {
         throw new IllegalStateException("Min cannot be more than Max!");
      } else {
         return (B)this;
      }
   }

   @Contract("_->param1")
   @MustBeInvokedByOverriders
   @Override
   protected O method30(O var1) {
      super.method30((O)var1);
      var1.method21(OptionTraits.field7, FloatNumberRange.method6(this.min, this.max, this.field16, this.field17, this.field15));
      return (O)var1;
   }
}
