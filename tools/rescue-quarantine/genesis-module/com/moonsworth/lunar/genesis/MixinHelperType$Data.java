package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Converter;

final class MixinHelperType$Data extends Converter<String, String> implements Serializable {
   private final CaseFormat field3;
   private final CaseFormat field4;
   private static final long field5 = 0L;

   MixinHelperType$Data(CaseFormat var1, CaseFormat var2) {
      this.field3 = Preconditions.checkNotNull(var1);
      this.field4 = Preconditions.checkNotNull(var2);
   }

   protected String doForward(String var1) {
      return this.field3.to(this.field4, var1);
   }

   protected String doBackward(String var1) {
      return this.field4.to(this.field3, var1);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelperType$Data)) {
         return false;
      }

      MixinHelperType$Data var2 = (MixinHelperType$Data)var1;
      return this.field3.equals(var2.field3) && this.field4.equals(var2.field4);
   }

   @Override
   public int hashCode() {
      return this.field3.hashCode() ^ this.field4.hashCode();
   }

   @Override
   public String toString() {
      return this.field3 + ".converterTo(" + this.field4 + ")";
   }
}
