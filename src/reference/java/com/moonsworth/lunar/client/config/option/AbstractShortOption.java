package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractShortOption extends AbstractOption<Short> {
   private short value;
   private short field7;

   protected AbstractShortOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Short> var2, short var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final short method7() {
      return this.field7;
   }

   public final void method2(short var1) {
      this.field7 = var1;
   }

   public final short getValue2() {
      return this.value;
   }

   public final void setValue(short var1) {
      this.value = var1;
   }

   @Deprecated
   public final Short method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Short var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Short getValue3() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Short var1) {
      this.value = var1;
   }
}
