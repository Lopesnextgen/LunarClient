package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

@GwtCompatible(emulated = true)
abstract class MixinHelper3124<K, V> extends ForwardingMap<K, V> implements MapExtension<K, V>, Serializable {
   private transient @Nullable Map<K, V> delegate;
   @RetainedWith
   transient @Nullable MixinHelper3124<V, K> field1;
   private transient @Nullable Set<K> keySet;
   private transient @Nullable Set<V> valueSet;
   private transient @Nullable Set<Entry<K, V>> entrySet;
   @Annotation3
   private static final long field2 = 0L;

   MixinHelper3124(Map<K, V> var1, Map<V, K> var2) {
      this.setDelegates(var1, var2);
   }

   private MixinHelper3124(Map<K, V> var1, MixinHelper3124<V, K> var2) {
      this.delegate = var1;
      this.field1 = var2;
   }

   @Override
   protected Map<K, V> delegate() {
      return this.delegate;
   }

   @CanIgnoreReturnValue
   K checkKey(@Nullable K var1) {
      return (K)var1;
   }

   @CanIgnoreReturnValue
   V checkValue(@Nullable V var1) {
      return (V)var1;
   }

   void setDelegates(Map<K, V> var1, Map<V, K> var2) {
      Preconditions.checkState(this.delegate == null);
      Preconditions.checkState(this.field1 == null);
      Preconditions.checkArgument(var1.isEmpty());
      Preconditions.checkArgument(var2.isEmpty());
      Preconditions.checkArgument(var1 != var2);
      this.delegate = var1;
      this.field1 = this.method1(var2);
   }

   MixinHelper3124<V, K> method1(Map<V, K> var1) {
      return new MixinHelper3124.Data3<>(var1, this);
   }

   void method2(MixinHelper3124<V, K> var1) {
      this.field1 = var1;
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      return this.field1.containsKey(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public V put(@Nullable K var1, @Nullable V var2) {
      return this.putInBothMaps((K)var1, (V)var2, false);
   }

   @CanIgnoreReturnValue
   @Override
   public V forcePut(@Nullable K var1, @Nullable V var2) {
      return this.putInBothMaps((K)var1, (V)var2, true);
   }

   private V putInBothMaps(@Nullable K var1, @Nullable V var2, boolean var3) {
      this.checkKey((K)var1);
      this.checkValue((V)var2);
      boolean var4 = this.containsKey(var1);
      if (var4 && MixinHelper72.equal(var2, this.get(var1))) {
         return (V)var2;
      }

      if (var3) {
         this.method2().remove(var2);
      } else {
         Preconditions.checkArgument(!this.containsValue(var2), "value already present: %s", var2);
      }

      Object var5 = this.delegate.put((K)var1, (V)var2);
      this.updateInverseMap((K)var1, var4, (V)var5, (V)var2);
      return (V)var5;
   }

   private void updateInverseMap(K var1, boolean var2, V var3, V var4) {
      if (var2) {
         this.removeFromInverseMap((V)var3);
      }

      this.field1.delegate.put((V)var4, (K)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public V remove(@Nullable Object var1) {
      return this.containsKey(var1) ? this.removeFromBothMaps(var1) : null;
   }

   @CanIgnoreReturnValue
   private V removeFromBothMaps(Object var1) {
      Object var2 = this.delegate.remove(var1);
      this.removeFromInverseMap((V)var2);
      return (V)var2;
   }

   private void removeFromInverseMap(V var1) {
      this.field1.delegate.remove(var1);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      for (Entry var3 : var1.entrySet()) {
         this.put((K)var3.getKey(), (V)var3.getValue());
      }
   }

   @Override
   public void replaceAll(BiFunction<? super K, ? super V, ? extends V> var1) {
      this.delegate.replaceAll(var1);
      this.field1.delegate.clear();
      Entry var2 = null;
      Iterator var3 = this.delegate.entrySet().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         Object var5 = var4.getKey();
         Object var6 = var4.getValue();
         Object var7 = this.field1.delegate.putIfAbsent((V)var6, (K)var5);
         if (var7 != null) {
            var2 = var4;
            var3.remove();
         }
      }

      if (var2 != null) {
         throw new IllegalArgumentException("value already present: " + var2.getValue());
      }
   }

   @Override
   public void clear() {
      this.delegate.clear();
      this.field1.delegate.clear();
   }

   @Override
   public MapExtension<V, K> method2() {
      return this.field1;
   }

   @Override
   public Set<K> keySet() {
      Set var1 = this.keySet;
      return var1 == null ? (this.keySet = new MixinHelper3124.Data4()) : var1;
   }

   @Override
   public Set<V> values() {
      Set var1 = this.valueSet;
      return var1 == null ? (this.valueSet = new MixinHelper3124.Data5()) : var1;
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      Set var1 = this.entrySet;
      return var1 == null ? (this.entrySet = new MixinHelper3124.Data()) : var1;
   }

   Iterator<Entry<K, V>> entrySetIterator() {
      final Iterator var1 = this.delegate.entrySet().iterator();
      return new Iterator<Entry<K, V>>() {
         @Nullable Entry<K, V> entry;

         @Override
         public boolean hasNext() {
            return var1.hasNext();
         }

         public Entry<K, V> next() {
            this.entry = (Entry<K, V>)var1.next();
            return MixinHelper3124.this.new Data2(this.entry);
         }

         @Override
         public void remove() {
            MixinHelper18_3.checkRemove(this.entry != null);
            Object var1x = this.entry.getValue();
            var1.remove();
            MixinHelper3124.this.removeFromInverseMap(var1x);
            this.entry = null;
         }
      };
   }

   private class Data extends MixinHelper3165<Entry<K, V>> {
      final Set<Entry<K, V>> field1 = MixinHelper3124.this.delegate.entrySet();

      private Data() {
      }

      @Override
      protected Set<Entry<K, V>> delegate() {
         return this.field1;
      }

      @Override
      public void clear() {
         MixinHelper3124.this.clear();
      }

      @Override
      public boolean remove(Object var1) {
         if (!this.field1.contains(var1)) {
            return false;
         }

         Entry var2 = (Entry)var1;
         MixinHelper3124.this.field1.delegate.remove(var2.getValue());
         this.field1.remove(var2);
         return true;
      }

      @Override
      public Iterator<Entry<K, V>> iterator() {
         return MixinHelper3124.this.entrySetIterator();
      }

      @Override
      public Object[] toArray() {
         return this.standardToArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])this.standardToArray((T[])var1);
      }

      @Override
      public boolean contains(Object var1) {
         return Maps.containsEntryImpl(this.delegate(), var1);
      }

      @Override
      public boolean containsAll(Collection<?> var1) {
         return this.standardContainsAll(var1);
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         return this.standardRemoveAll(var1);
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         return this.standardRetainAll(var1);
      }
   }

   class Data2 extends MixinHelper315<K, V> {
      private final Entry<K, V> field1;

      Data2(Entry<K, V> var2) {
         this.field1 = var2;
      }

      @Override
      protected Entry<K, V> delegate() {
         return this.field1;
      }

      @Override
      public V setValue(V var1) {
         MixinHelper3124.this.checkValue((V)var1);
         Preconditions.checkState(MixinHelper3124.this.entrySet().contains(this), "entry no longer in map");
         if (MixinHelper72.equal(var1, this.getValue())) {
            return (V)var1;
         }

         Preconditions.checkArgument(!MixinHelper3124.this.containsValue(var1), "value already present: %s", var1);
         Object var2 = this.field1.setValue((V)var1);
         Preconditions.checkState(MixinHelper72.equal(var1, MixinHelper3124.this.get(this.getKey())), "entry no longer in map");
         MixinHelper3124.this.updateInverseMap((K)this.getKey(), true, (V)var2, (V)var1);
         return (V)var2;
      }
   }

   static class Data3<K, V> extends MixinHelper3124<K, V> {
      @Annotation3
      private static final long field3 = 0L;

      Data3(Map<K, V> var1, MixinHelper3124<V, K> var2) {
         super(var1, var2);
      }

      @Override
      K checkKey(K var1) {
         return (K)this.field1.checkValue(var1);
      }

      @Override
      V checkValue(V var1) {
         return (V)this.field1.checkKey(var1);
      }

      @Annotation3
      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         var1.writeObject(this.method2());
      }

      @Annotation3
      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         this.HORHROIOIOICIRHIOCOICHHHIHCIIO((MixinHelper3124)var1.readObject());
      }

      @Annotation3
      Object readResolve() {
         return this.method2().method2();
      }
   }

   private class Data4 extends MixinHelper3165<K> {
      private Data4() {
      }

      @Override
      protected Set<K> delegate() {
         return MixinHelper3124.this.delegate.keySet();
      }

      @Override
      public void clear() {
         MixinHelper3124.this.clear();
      }

      @Override
      public boolean remove(Object var1) {
         if (!this.contains(var1)) {
            return false;
         }

         MixinHelper3124.this.removeFromBothMaps(var1);
         return true;
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         return this.standardRemoveAll(var1);
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         return this.standardRetainAll(var1);
      }

      @Override
      public Iterator<K> iterator() {
         return Maps.keyIterator(MixinHelper3124.this.entrySet().iterator());
      }
   }

   private class Data5 extends MixinHelper3165<V> {
      final Set<V> field1 = MixinHelper3124.this.field1.keySet();

      private Data5() {
      }

      @Override
      protected Set<V> delegate() {
         return this.field1;
      }

      @Override
      public Iterator<V> iterator() {
         return Maps.valueIterator(MixinHelper3124.this.entrySet().iterator());
      }

      @Override
      public Object[] toArray() {
         return this.standardToArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])this.standardToArray((T[])var1);
      }

      @Override
      public String toString() {
         return this.standardToString();
      }
   }
}
