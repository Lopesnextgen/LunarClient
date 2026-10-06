package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.IntegerNumberRange;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import com.moonsworth.lunar.client.render.particle.Min;
import com.moonsworth.lunar.client.render.particle.Max;

public abstract class IntegerRangeOptionBuilder<B extends IntegerRangeOptionBuilder<B, O>, O extends ClientOption<Integer>>
   extends OptionBuilderBase<B, O, Integer>
   implements NumberRangeConfigurator<B, Integer> {
   protected int field14 = this.method10();
   protected int min = Integer.MIN_VALUE;
   protected int max = Integer.MAX_VALUE;
   protected boolean field15 = true;
   protected boolean field16 = true;

   protected IntegerRangeOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected int method10() {
      return 0;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.SLIDER;
   }

   @Override
   protected Codec<Integer> method3() {
      return Codec.INT;
   }

   @Contract("_->this")
   public B method4(int var1) {
      this.field14 = var1;
      return (B)this;
   }

   public B method5(boolean var1, boolean var2) {
      this.field15 = var1;
      this.field16 = var2;
      return (B)this;
   }

   @Deprecated
   public B method6(Integer var1, Integer var2) {
      return this.method7(var1, var2);
   }

   @Contract("_,_->this")
   public B method7(int var1, int var2) {
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
      var1.method21(OptionTraits.field7, IntegerNumberRange.method5(this.min, this.max, this.field15, this.field16));
      return (O)var1;
   }
}
