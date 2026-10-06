package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.Identifier;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.ClientOption;

public abstract class DefaultedOptionBuilder<B extends DefaultedOptionBuilder<B, O, T>, O extends ClientOption<T>, T> extends AbstractOptionBuilder<B, O, T> {
   protected @Nullable T defaultValue = this.method10();

   protected DefaultedOptionBuilder(@Identifier String text1) {
      super(text1);
   }

   protected @Nullable T method10() {
      return null;
   }

   @Contract("_->this")
   public B method2(T t) {
      this.defaultValue = (T)t;
      return (B)this;
   }
}
