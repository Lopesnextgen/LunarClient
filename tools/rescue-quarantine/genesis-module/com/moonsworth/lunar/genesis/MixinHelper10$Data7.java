package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

final class MixinHelper10$Data7<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
   private final transient ImmutableList<ImmutableSet<E>> field1;
   private final transient AbstractListIterator<E> field2;

   static <E> Set<List<E>> create(List<? extends Set<? extends E>> var0) {
      ImmutableList.Data2 var1 = new ImmutableList.Data2(var0.size());

      for (Set var3 : var0) {
         ImmutableSet var4 = ImmutableSet.method10(var3);
         if (var4.isEmpty()) {
            return ImmutableSet.method3();
         }

         var1.method2(var4);
      }

      final ImmutableList var5 = var1.method6();
      ImmutableList var6 = new ImmutableList<List<E>>() {
         @Override
         public int size() {
            return var5.size();
         }

         public List<E> get(int var1) {
            return ((ImmutableSet)var5.get(var1)).method2();
         }

         @Override
         boolean isPartialView() {
            return true;
         }
      };
      return new MixinHelper10$Data7<>(var5, new AbstractListIterator<>(var6));
   }

   private MixinHelper10$Data7(ImmutableList<ImmutableSet<E>> var1, AbstractListIterator<E> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   protected Collection<List<E>> delegate() {
      return this.field2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper10$Data7) {
         MixinHelper10$Data7 var2 = (MixinHelper10$Data7)var1;
         return this.field1.equals(var2.field1);
      } else {
         return super.equals(var1);
      }
   }

   @Override
   public int hashCode() {
      int var1 = this.size() - 1;

      for (int var2 = 0; var2 < this.field1.size(); var2++) {
         var1 *= 31;
         var1 = ~(~var1);
      }

      int var6 = 1;
      MixinHelperIterator3 var3 = this.field1.method1();

      while (var3.hasNext()) {
         Set var4 = (Set)var3.next();
         var6 = 31 * var6 + this.size() / var4.size() * var4.hashCode();
         var6 = ~(~var6);
      }

      var6 += var1;
      return ~(~var6);
   }
}
