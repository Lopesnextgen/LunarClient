package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
public final class MixinHelper1342232<K, V> extends MixinHelper134223<K, V> {
   private static final int field4 = 2;
   @Annotation4
   transient int expectedValuesPerKey = 2;
   @Annotation3
   private static final long field5 = 0L;

   public static <K, V> MixinHelper1342232<K, V> method1() {
      return new MixinHelper1342232<>();
   }

   public static <K, V> MixinHelper1342232<K, V> method2(int var0, int var1) {
      return new MixinHelper1342232<>(var0, var1);
   }

   public static <K, V> MixinHelper1342232<K, V> method3(Multimap<? extends K, ? extends V> var0) {
      return new MixinHelper1342232<>(var0);
   }

   private MixinHelper1342232() {
      this(12, 2);
   }

   private MixinHelper1342232(int var1, int var2) {
      super(MixinHelper8_2.newHashMapWithExpectedSize(var1));
      Preconditions.checkArgument(var2 >= 0);
      this.expectedValuesPerKey = var2;
   }

   private MixinHelper1342232(Multimap<? extends K, ? extends V> var1) {
      super(MixinHelper8_2.newHashMapWithExpectedSize(var1.keySet().size()));
      this.method1(var1);
   }

   @Override
   Set<V> createCollection() {
      return MixinHelper8_2.newHashSetWithExpectedSize(this.expectedValuesPerKey);
   }

   @Annotation3
   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      MixinHelper7_10.method4(this, var1);
   }

   @Annotation3
   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.expectedValuesPerKey = 2;
      int var2 = MixinHelper7_10.readCount(var1);
      Map var3 = MixinHelper8_2.newHashMapWithExpectedSize(12);
      this.<init>(var3);
      MixinHelper7_10.method6(this, var1, var2);
   }
}
