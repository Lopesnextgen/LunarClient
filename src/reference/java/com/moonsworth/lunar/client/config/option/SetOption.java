package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.Set;
import org.jspecify.annotations.Nullable;

public class SetOption<V, S extends Set<V>> extends AbstractValueOption<S> {
   public SetOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<S> var2, S var3) {
      super(var1, var2, (S)var3);
   }

   public void method1(V var1) {
      if (((Set)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).add(var1)) {
         this.OIRHOOIICOCIOOHICRRRICORIHHIHC((Set)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
      }
   }

   public void method2(V var1) {
      if (((Set)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).remove(var1)) {
         this.OIRHOOIICOCIOOHICRRRICORIHHIHC((Set)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
      }
   }

   public static class Data<V, S extends Set<V>> extends DefaultValueBuilder<SetOption.Data<V, S>, SetOption<V, S>, S> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.UNKNOWN;
      }

      @Override
      protected @Nullable Codec<S> method3() {
         return null;
      }

      protected SetOption<V, S> method11() {
         if (this.defaultValue == null) {
            throw new OptionConfigException(this, "Value must be set!");
         } else {
            return new SetOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue);
         }
      }
   }
}
