package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractIntegerOption extends AbstractOption<Integer> {
   private int value;
   private int field7;

   protected AbstractIntegerOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Integer> var2, int var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final int method7() {
      return this.field7;
   }

   public final void method2(int var1) {
      this.field7 = var1;
   }

   public final int getValue2() {
      return this.value;
   }

   public final void setValue(int var1) {
      this.value = var1;
   }

   @Deprecated
   public final Integer method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Integer var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Integer getValue3() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Integer var1) {
      this.value = var1;
   }
}
