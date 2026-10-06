package com.moonsworth.lunar.genesis;

import java.util.AbstractMap.SimpleImmutableEntry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public final class AbstractMapImpl<K, V> extends SimpleImmutableEntry<K, V> {
   private final MixinHelperType field1;
   private static final long field2 = 0L;

   public static <K, V> AbstractMapImpl<K, V> method1(@Nullable K var0, @Nullable V var1, MixinHelperType var2) {
      return new AbstractMapImpl<>((K)var0, (V)var1, var2);
   }

   private AbstractMapImpl(@Nullable K var1, @Nullable V var2, MixinHelperType var3) {
      super((K)var1, (V)var2);
      this.field1 = Preconditions.checkNotNull(var3);
   }

   public MixinHelperType method2() {
      return this.field1;
   }

   public boolean wasEvicted() {
      return this.field1.wasEvicted();
   }
}
