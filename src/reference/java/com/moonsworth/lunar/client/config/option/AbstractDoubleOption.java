package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractDoubleOption extends AbstractOption<Double> {
   private double value;
   private double field7;

   protected AbstractDoubleOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Double> var2, double var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final double method7() {
      return this.field7;
   }

   public final void method2(double var1) {
      this.field7 = var1;
   }

   public final double getValue2() {
      return this.value;
   }

   public final void setValue(double var1) {
      this.value = var1;
   }

   @Deprecated
   public final Double method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Double var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Double getValue3() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Double var1) {
      this.value = var1;
   }
}
