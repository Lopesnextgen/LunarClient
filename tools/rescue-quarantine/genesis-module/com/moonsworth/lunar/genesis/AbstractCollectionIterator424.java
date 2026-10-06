package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

@GwtCompatible(emulated = true, serializable = true)
class AbstractCollectionIterator424<E> extends ImmutableMultiset<E> {
   static final ImmutableMultiset<Object> field5 = method1(ImmutableList.method3());
   @Annotation4
   static final double field6 = 1.0;
   @Annotation4
   static final double field7 = 0.001;
   @Annotation4
   static final int field8 = 9;
   private final transient MixinHelper33$Data7<E>[] field9;
   private final transient MixinHelper33.MixinHelper33$Data7<E> @Nullable [] field10;
   private final transient int field11;
   private final transient int field12;
   @LazyInit
   private transient ImmutableSet<E> field13;

   static <E> ImmutableMultiset<E> method1(Collection<? extends Multiset.Extension<? extends E>> var0) {
      int var1 = var0.size();
      MixinHelper33$Data7[] var2 = new MixinHelper33$Data7[var1];
      if (var1 == 0) {
         return new AbstractCollectionIterator424<>(var2, null, 0, 0, ImmutableSet.method3());
      }

      int var3 = MixinHelper36_2.closedTableSize(var1, 1.0);
      int var4 = var3 - 1;
      MixinHelper33$Data7[] var5 = new MixinHelper33$Data7[var3];
      int var6 = 0;
      int var7 = 0;
      long var8 = 0L;

      for (Multiset.Extension var11 : var0) {
         Object var12 = Preconditions.checkNotNull(var11.getElement());
         int var13 = var11.getCount();
         int var14 = var12.hashCode();
         int var15 = MixinHelper36_2.smear(var14) & var4;
         MixinHelper33$Data7 var16 = var5[var15];
         MixinHelper33$Data7 var17;
         if (var16 == null) {
            boolean var18 = var11 instanceof MixinHelper33$Data7 && !(var11 instanceof AbstractCollectionIterator424.Data);
            var17 = var18 ? (MixinHelper33$Data7)var11 : new MixinHelper33$Data7<>(var12, var13);
         } else {
            var17 = new AbstractCollectionIterator424.Data<>(var12, var13, var16);
         }

         var7 += var14 ^ var13;
         var2[var6++] = var17;
         var5[var15] = var17;
         var8 += var13;
      }

      return method2(var5)
         ? AbstractCollectionIterator423.method1(ImmutableList.method21(var2))
         : new AbstractCollectionIterator424<>(var2, var5, MixinHelper122.saturatedCast(var8), var7, null);
   }

   private static boolean method2(MixinHelper33$Data7<?>[] var0) {
      for (int var1 = 0; var1 < var0.length; var1++) {
         int var2 = 0;

         for (MixinHelper33$Data7 var3 = var0[var1]; var3 != null; var3 = var3.method1()) {
            if (++var2 > 9) {
               return true;
            }
         }
      }

      return false;
   }

   private AbstractCollectionIterator424(MixinHelper33$Data7<E>[] var1, MixinHelper33$Data7<E>[] var2, int var3, int var4, ImmutableSet<E> var5) {
      this.field9 = var1;
      this.field10 = var2;
      this.field11 = var3;
      this.field12 = var4;
      this.field13 = var5;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public int count(@Nullable Object var1) {
      MixinHelper33$Data7[] var2 = this.field10;
      if (var1 != null && var2 != null) {
         int var3 = MixinHelper36_2.smearedHash(var1);
         int var4 = var2.length - 1;

         for (MixinHelper33$Data7 var5 = var2[var3 & var4]; var5 != null; var5 = var5.method1()) {
            if (MixinHelper72.equal(var1, var5.getElement())) {
               return var5.getCount();
            }
         }

         return 0;
      } else {
         return 0;
      }
   }

   @Override
   public int size() {
      return this.field11;
   }

   @Override
   public ImmutableSet<E> method15() {
      ImmutableSet var1 = this.field13;
      return var1 == null ? (this.field13 = new ImmutableMultiset.Data<>(Arrays.asList(this.field9), this)) : var1;
   }

   @Override
   Multiset.Extension<E> method18(int var1) {
      return this.field9[var1];
   }

   @Override
   public int hashCode() {
      return this.field12;
   }

   private static final class Data<E> extends MixinHelper33$Data7<E> {
      private final MixinHelper33$Data7<E> field4;

      Data(E var1, int var2, MixinHelper33$Data7<E> var3) {
         super((E)var1, var2);
         this.field4 = var3;
      }

      @Override
      public MixinHelper33$Data7<E> method1() {
         return this.field4;
      }
   }
}
