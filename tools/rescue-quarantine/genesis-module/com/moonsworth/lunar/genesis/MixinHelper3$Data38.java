package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.Funnel;
import com.google.common.base.Preconditions;

class MixinHelper3$Data38 implements Funnel<CharSequence>, Serializable {
   private final Charset field1;

   MixinHelper3$Data38(Charset var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   public void funnel(CharSequence var1, MixinHelper4_3 var2) {
      var2.method13(var1, this.field1);
   }

   @Override
   public String toString() {
      return "Funnels.stringFunnel(" + this.field1.name() + ")";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper3$Data38) {
         MixinHelper3$Data38 var2 = (MixinHelper3$Data38)var1;
         return this.field1.equals(var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return MixinHelper3$Data38.class.hashCode() ^ this.field1.hashCode();
   }

   Object writeReplace() {
      return new MixinHelper3$Data38.Data(this.field1);
   }

   private static class Data implements Serializable {
      private final String field1;
      private static final long field2 = 0L;

      Data(Charset var1) {
         this.field1 = var1.name();
      }

      private Object readResolve() {
         return MixinHelper3_6.method3(Charset.forName(this.field1));
      }
   }
}
