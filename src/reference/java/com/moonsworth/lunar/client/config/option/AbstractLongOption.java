package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractLongOption extends AbstractOption<Long> {
   private long value;
   private long field7;

   protected AbstractLongOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Long> var2, long var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final long method7() {
      return this.field7;
   }

   public final void method2(long var1) {
      this.field7 = var1;
   }

   public final long getLongValue() {
      return this.value;
   }

   public final void setValue(long var1) {
      this.value = var1;
   }

   @Deprecated
   public final Long method8() {
      return this.field7;
   }

   @Deprecated
   public final void method5(Long var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Long getValue2() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Long var1) {
      this.value = var1;
   }
}
