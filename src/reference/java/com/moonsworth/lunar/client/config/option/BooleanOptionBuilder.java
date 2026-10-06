package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import org.jetbrains.annotations.Contract;

public abstract class BooleanOptionBuilder<B extends BooleanOptionBuilder<B, O>, O extends ClientOption<Boolean>> extends OptionBuilderBase<B, O, Boolean> {
   protected boolean field14 = this.method10();

   protected BooleanOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
      super(var1);
   }

   protected boolean method10() {
      return false;
   }

   @Override
   protected DriverFieldTypeLegacy method2() {
      return DriverFieldTypeLegacy.CHECKBOX;
   }

   @Override
   protected Codec<Boolean> method3() {
      return Nameplate.field35;
   }

   @Contract("_->this")
   public B method4(boolean var1) {
      this.field14 = var1;
      return (B)this;
   }
}
