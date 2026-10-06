package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.util.Annotation;
import org.jspecify.annotations.Nullable;

public abstract class AbstractByteOption extends AbstractOption<Byte> {
   private byte value;
   private byte field7;

   protected AbstractByteOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<Byte> var2, byte var3) {
      super(var1, var2);
      this.value = this.field7 = var3;
   }

   public final byte method7() {
      return this.field7;
   }

   public final void method2(byte var1) {
      this.field7 = var1;
   }

   public final byte getValue2() {
      return this.value;
   }

   public final void setValue(byte var1) {
      this.value = var1;
   }

   @Deprecated
   public final Byte method8() {
      return this.field7;
   }

   @Deprecated
   public final void method6(Byte var1) {
      this.field7 = var1;
   }

   @Deprecated
   public final Byte getValue3() {
      return this.value;
   }

   @Deprecated
   protected final void setValue(Byte var1) {
      this.value = var1;
   }
}
