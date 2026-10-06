package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public abstract class CyclingDropdownOption<T> extends NamedDropdownOption<T> {
   private List<T> options;

   protected CyclingDropdownOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<T> var2,
      T var3,
      List<T> var4,
      @Nullable Function<T, String> var5
   ) {
      super(var1, var2, (T)var3, var4, var5);
      this.options = var4;
   }

   @Override
   public List<T> getOptions() {
      return this.options;
   }

   public void setOptions(List<T> var1) {
      this.options = var1;
   }

   public void method8() {
      List var1 = this.getOptions();
      if (var1.size() > 1) {
         int var2 = var1.indexOf(this.get());
         this.method10(var1.get((var2 + 1) % var1.size()));
      }
   }
}
