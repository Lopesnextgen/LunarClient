package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.DoubleNumberRule;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import com.moonsworth.lunar.client.render.particle.Min;
import com.moonsworth.lunar.client.render.particle.Max;

public abstract class DoubleRangeOptionBuilder<B extends DoubleRangeOptionBuilder<B, O>, O extends ClientOption<Double>>
   extends OptionBuilderBase<B, O, Double>
   implements SteppedNumberRangeConfigurator<B, Double> {
   protected double field14 = this.method10();
   protected double min = -Double.MAX_VALUE;
   protected double max = Double.MAX_VALUE;
   protected int field15;
   protected boolean field16 = true;
   protected boolean field17 = true;

   protected DoubleRangeOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected double method10() {
      return 0.0;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.SLIDER;
   }

   @Override
   protected Codec<Double> method3() {
      return Codec.DOUBLE;
   }

   @Contract("_->this")
   public B method4(double var1) {
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
   public B method7(Double var1, Double var2) {
      return this.method8(var1, var2);
   }

   @Contract("_,_->this")
   public B method8(double var1, double var3) {
      this.min = var1;
      this.max = var3;
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
      var1.method21(OptionTraits.field7, DoubleNumberRule.method6(this.min, this.max, this.field16, this.field17, this.field15));
      return (O)var1;
   }
}
