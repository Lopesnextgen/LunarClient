package com.moonsworth.lunar.genesis;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableCollection;

@GwtCompatible(emulated = true)
abstract class MixinHelper3022<V, C> extends MixinHelper302<V, C> {
   private List<MixinHelper3022.Data<V>> values;

   MixinHelper3022(ImmutableCollection<? extends ListenableFuture<? extends V>> var1, boolean var2) {
      super(var1, var2, true);
      AbstractCollection var3 = var1.isEmpty() ? ImmutableList.method3() : Lists.newArrayListWithCapacity(var1.size());

      for (int var4 = 0; var4 < var1.size(); var4++) {
         var3.add(null);
      }

      this.values = var3;
   }

   @Override
   final void collectOneValue(int var1, @Nullable V var2) {
      List var3 = this.values;
      if (var3 != null) {
         var3.set(var1, new MixinHelper3022.Data<>(var2));
      }
   }

   @Override
   final void handleAllCompleted() {
      List var1 = this.values;
      if (var1 != null) {
         this.set(this.combine(var1));
      }
   }

   @Override
   void method4(MixinHelper302.Type var1) {
      super.method4(var1);
      this.values = null;
   }

   abstract C combine(List<MixinHelper3022.Data<V>> var1);

   private static final class Data<V> {
      V value;

      Data(V var1) {
         this.value = (V)var1;
      }
   }

   static final class Data2<V> extends MixinHelper3022<V, List<V>> {
      Data2(ImmutableCollection<? extends ListenableFuture<? extends V>> var1, boolean var2) {
         super(var1, var2);
         this.ICRRCOORHIIOHRCHHICCHOCOOOHCHH();
      }

      public List<V> combine(List<MixinHelper3022.Data<V>> var1) {
         ArrayList var2 = Lists.newArrayListWithCapacity(var1.size());

         for (MixinHelper3022.Data var4 : var1) {
            var2.add(var4 != null ? var4.value : null);
         }

         return Collections.unmodifiableList(var2);
      }
   }
}
