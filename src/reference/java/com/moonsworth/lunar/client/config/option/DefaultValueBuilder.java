package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.util.Annotation3;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public abstract class DefaultValueBuilder<B extends DefaultValueBuilder<B, O, T>, O extends ClientOption<T>, T> extends OptionBuilderBase<B, O, T> {
   protected @Nullable T defaultValue = this.method10();

   protected DefaultValueBuilder(@Annotation3 String var1) {
      super(var1);
   }

   protected @Nullable T method10() {
      return null;
   }

   @Contract("_->this")
   public B method2(T var1) {
      this.defaultValue = (T)var1;
      return (B)this;
   }
}
