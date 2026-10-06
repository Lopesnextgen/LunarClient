package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.AbstractFuture;

@GwtCompatible
public final class MixinHelper33<V> extends AbstractFuture.Data5<V> {
   public static <V> MixinHelper33<V> method1() {
      return new MixinHelper33<>();
   }

   @CanIgnoreReturnValue
   @Override
   public boolean set(@Nullable V var1) {
      return super.set((V)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean setException(Throwable var1) {
      return super.setException(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean method3(ListenableFuture<? extends V> var1) {
      return super.method3(var1);
   }

   private MixinHelper33() {
   }
}
