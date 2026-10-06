package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.LongNumberRange;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation3;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import com.moonsworth.lunar.client.render.particle.Min;
import com.moonsworth.lunar.client.render.particle.Max;

public abstract class LongSliderOptionBuilder<B extends LongSliderOptionBuilder<B, O>, O extends ClientOption<Long>>
   extends OptionBuilderBase<B, O, Long>
   implements NumberRangeConfigurator<B, Long> {
   protected long field14 = this.method10();
   protected long min = Long.MIN_VALUE;
   protected long field15 = Long.MAX_VALUE;
   protected boolean field16 = true;
   protected boolean field17 = true;

   protected LongSliderOptionBuilder(@Annotation3 @Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected long method10() {
      return 0L;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.SLIDER;
   }

   @Override
   protected Codec<Long> method3() {
      return Codec.LONG;
   }

   @Contract("_->this")
   public B method4(long var1) {
      this.field14 = var1;
      return (B)this;
   }

   public B method5(boolean var1, boolean var2) {
      this.field16 = var1;
      this.field17 = var2;
      return (B)this;
   }

   @Deprecated
   public B method6(Long var1, Long var2) {
      return this.method7(var1, var2);
   }

   @Contract("_,_->this")
   public B method7(long var1, long var3) {
      this.min = var1;
      this.field15 = var3;
      if (this.min > this.field15) {
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
      var1.method21(OptionTraits.field7, LongNumberRange.method5(this.min, this.field15, this.field16, this.field17));
      return (O)var1;
   }
}
