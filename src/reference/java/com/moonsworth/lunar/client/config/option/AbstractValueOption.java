package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractValueOption<T> extends AbstractOption<T> {
   private T value;
   private T defaultValue;

   protected AbstractValueOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<T> var2, @Nullable T var3) {
      super(var1, var2);
      this.value = this.defaultValue = (T)var3;
   }

   @Override
   public T getDefaultValue() {
      return this.defaultValue;
   }

   @Override
   public final void method3(T var1) {
      this.defaultValue = (T)var1;
   }

   @Override
   public final T getValue() {
      return this.value;
   }

   @Override
   protected final void method4(T var1) {
      this.value = (T)var1;
   }
}
