package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;

final class CaseFormat$StringConverter extends Converter<String, String> implements Serializable {
   private final MixinHelperType_5 field3;
   private final MixinHelperType_5 field4;
   private static final long field5 = 0L;

   CaseFormat$StringConverter(MixinHelperType_5 mixinhelpertype_51, MixinHelperType_5 mixinhelpertype_52) {
      this.field3 = Preconditions.checkNotNull(mixinhelpertype_51);
      this.field4 = Preconditions.checkNotNull(mixinhelpertype_52);
   }

   protected String doForward(String text1) {
      return this.field3.to(this.field4, text1);
   }

   protected String doBackward(String text1) {
      return this.field4.to(this.field3, text1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof CaseFormat$StringConverter)) {
         return false;
      }

      CaseFormat$StringConverter mixinhelpertype$data2 = (CaseFormat$StringConverter)obj1;
      return this.field3.equals(mixinhelpertype$data2.field3) && this.field4.equals(mixinhelpertype$data2.field4);
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
