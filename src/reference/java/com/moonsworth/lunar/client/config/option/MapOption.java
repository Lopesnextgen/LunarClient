package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.util.Map;
import org.jspecify.annotations.Nullable;

public class MapOption<K, V, M extends Map<K, V>> extends AbstractValueOption<M> {
   protected MapOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<M> var2, M var3) {
      super(var1, var2, (M)var3);
   }

   public boolean contains(K var1) {
      return ((Map)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).containsKey(var1);
   }

   public void method1(K var1, V var2) {
      ((Map)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).put(var1, var2);
      this.method2((Map)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
   }

   public void method2(K var1) {
      ((Map)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()).remove(var1);
      this.method2((Map)this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
   }

   public static class Data<K, V, M extends Map<K, V>> extends DefaultValueBuilder<MapOption.Data<K, V, M>, MapOption<K, V, M>, M> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.UNKNOWN;
      }

      @Override
      protected Codec<M> method3() {
         return null;
      }

      protected MapOption<K, V, M> method11() {
         if (this.defaultValue == null) {
            throw new OptionConfigException(this, "Value must be set!");
         } else {
            return new MapOption<>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue);
         }
      }
   }
}
