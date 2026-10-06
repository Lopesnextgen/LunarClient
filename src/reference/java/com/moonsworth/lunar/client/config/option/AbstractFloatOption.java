package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractFloatOption extends AbstractOption<Float> {
   private float value;
   private float field7;

   protected AbstractFloatOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Float> var2, float var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final float method7() {
      return this.field7;
   }

   public final void method2(float var1) {
      this.field7 = var1;
   }

   public final float getValue2() {
      return this.value;
   }

   public final void setValue(float var1) {
      this.value = var1;
   }

   @Deprecated
   public final Float method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Float var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Float getValue3() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Float var1) {
      this.value = var1;
   }
}
