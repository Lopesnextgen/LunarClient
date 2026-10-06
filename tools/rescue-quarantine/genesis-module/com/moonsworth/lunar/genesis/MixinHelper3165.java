package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingCollection;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;

@GwtCompatible
public abstract class MixinHelper3165<E> extends ForwardingCollection<E> implements Set<E> {
   protected MixinHelper3165() {
   }

   protected abstract Set<E> delegate();

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 == this || this.delegate().equals(var1);
   }

   @Override
   public int hashCode() {
      return this.delegate().hashCode();
   }

   @Override
   protected boolean standardRemoveAll(Collection<?> var1) {
      return Sets.removeAllImpl(this, Preconditions.checkNotNull(var1));
   }

   protected boolean standardEquals(@Nullable Object var1) {
      return Sets.equalsImpl(this, var1);
   }

   protected int standardHashCode() {
      return Sets.hashCodeImpl(this);
   }
}
