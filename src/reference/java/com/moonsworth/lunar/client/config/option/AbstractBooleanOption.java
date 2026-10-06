package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractBooleanOption extends AbstractOption<Boolean> {
   private boolean value;
   private boolean field7;

   protected AbstractBooleanOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Boolean> var2, boolean var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final boolean method7() {
      return this.field7;
   }

   public final void method2(boolean var1) {
      this.field7 = var1;
   }

   public final boolean isValue() {
      return this.value;
   }

   public final void setValue(boolean var1) {
      this.value = var1;
   }

   @Deprecated
   public final Boolean method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Boolean var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Boolean getValue2() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Boolean var1) {
      this.value = var1;
   }
}
