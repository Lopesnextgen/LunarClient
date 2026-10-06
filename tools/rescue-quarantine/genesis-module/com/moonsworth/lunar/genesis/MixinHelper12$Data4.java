package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.util.Collection;
import com.google.common.io.ByteSource;
import com.google.common.base.Preconditions;

final class MixinHelper12$Data4 extends ByteSource {
   final Iterable<? extends ByteSource> field1;

   MixinHelper12$Data4(Iterable<? extends ByteSource> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public InputStream openStream() {
      return new InputStreamLoader(this.field1.iterator());
   }

   @Override
   public boolean isEmpty() {
      for (ByteSource var2 : this.field1) {
         if (!var2.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public SerializableBase_2<Long> method3() {
      if (!(this.field1 instanceof Collection)) {
         return SerializableBase_2.method1();
      }

      long var1 = 0L;

      for (ByteSource var4 : this.field1) {
         SerializableBase_2 var5 = var4.method3();
         if (!var5.isPresent()) {
            return SerializableBase_2.method1();
         }

         var1 += var5.get();
         if (var1 < 0L) {
            return SerializableBase_2.method2(Long.MAX_VALUE);
         }
      }

      return SerializableBase_2.method2(var1);
   }

   @Override
   public long size() {
      long var1 = 0L;

      for (ByteSource var4 : this.field1) {
         var1 += var4.size();
         if (var1 < 0L) {
            return Long.MAX_VALUE;
         }
      }

      return var1;
   }

   @Override
   public String toString() {
      return "ByteSource.concat(" + this.field1 + ")";
   }
}
