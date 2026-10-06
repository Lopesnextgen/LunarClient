package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.ByteNumberRange;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.MustBeInvokedByOverriders;
import com.moonsworth.lunar.client.render.particle.Min;
import com.moonsworth.lunar.client.render.particle.Max;

public abstract class ByteRangeOptionBuilder<B extends ByteRangeOptionBuilder<B, O>, O extends ClientOption<Byte>>
   extends OptionBuilderBase<B, O, Byte>
   implements NumberRangeConfigurator<B, Byte> {
   protected byte field14 = this.method10();
   protected byte field15 = -128;
   protected byte field16 = 127;
   protected boolean field17 = true;
   protected boolean field18 = true;

   protected ByteRangeOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected byte method10() {
      return 0;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.SLIDER;
   }

   @Override
   protected Codec<Byte> method3() {
      return Codec.BYTE;
   }

   @Contract("_->this")
   public B method4(byte var1) {
      this.field14 = var1;
      return (B)this;
   }

   public B method5(boolean var1, boolean var2) {
      this.field17 = var1;
      this.field18 = var2;
      return (B)this;
   }

   @Deprecated
   public B method6(Byte var1, Byte var2) {
      return this.method7(var1, var2);
   }

   @Contract("_,_->this")
   public B method7(byte var1, byte var2) {
      this.field15 = var1;
      this.field16 = var2;
      if (this.field15 > this.field16) {
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
      var1.method21(OptionTraits.field7, ByteNumberRange.method1(this.field15, this.field16, this.field17, this.field18));
      return (O)var1;
   }
}
