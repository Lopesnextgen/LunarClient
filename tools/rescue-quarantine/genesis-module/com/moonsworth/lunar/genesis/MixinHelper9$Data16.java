package com.moonsworth.lunar.genesis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.io.CharSource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

class MixinHelper9$Data16 {
   final Map<MixinHelper9$Data16, MixinHelper9$Data15> field1 = new MixinHelper26().method5().makeMap();
   final Map<MixinHelper9$Data16, MixinHelper9$Data10> field2 = new MixinHelper26().method5().makeMap();
   final String field3;

   MixinHelper9$Data16(String var1) {
      this.field3 = Preconditions.checkNotNull(var1);
   }

   String getLockName() {
      return this.field3;
   }

   void method1(MixinHelper9$Extension2 var1, List<MixinHelper9$Data16> var2) {
      int var3 = 0;

      for (int var4 = var2.size(); var3 < var4; var3++) {
         this.method2(var1, (MixinHelper9$Data16)var2.get(var3));
      }
   }

   void method2(MixinHelper9$Extension2 var1, MixinHelper9$Data16 var2) {
      Preconditions.checkState(this != var2, "Attempted to acquire multiple locks with the same rank %s", var2.getLockName());
      if (!this.field1.containsKey(var2)) {
         MixinHelper9$Data10 var3 = this.field2.get(var2);
         if (var3 != null) {
            MixinHelper9$Data10 var7 = new MixinHelper9$Data10(var2, this, var3.method1());
            var1.handlePotentialDeadlock(var7);
         } else {
            Set var4 = Sets.newIdentityHashSet();
            MixinHelper9$Data15 var5 = var2.method3(this, var4);
            if (var5 == null) {
               this.field1.put(var2, new MixinHelper9$Data15(var2, this));
            } else {
               MixinHelper9$Data10 var6 = new MixinHelper9$Data10(var2, this, var5);
               this.field2.put(var2, var6);
               var1.handlePotentialDeadlock(var6);
            }
         }
      }
   }

   private @Nullable CharSource.MixinHelper9$Data15 method3(MixinHelper9$Data16 var1, Set<MixinHelper9$Data16> var2) {
      if (!var2.add(this)) {
         return null;
      }

      MixinHelper9$Data15 var3 = this.field1.get(var1);
      if (var3 != null) {
         return var3;
      }

      for (Entry var5 : this.field1.entrySet()) {
         MixinHelper9$Data16 var6 = (MixinHelper9$Data16)var5.getKey();
         var3 = var6.method3(var1, var2);
         if (var3 != null) {
            MixinHelper9$Data15 var7 = new MixinHelper9$Data15(var6, this);
            var7.setStackTrace(((MixinHelper9$Data15)var5.getValue()).getStackTrace());
            var7.initCause(var3);
            return var7;
         }
      }

      return null;
   }
}
