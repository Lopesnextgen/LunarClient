package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.Maps;

@GwtCompatible(serializable = true, emulated = true)
public final class MixinHelper1342322<K, V> extends MixinHelper134232<K, V> {
   private static final int field4 = 3;
   @Annotation4
   transient int expectedValuesPerKey;
   @Annotation3
   private static final long field5 = 0L;

   public static <K, V> MixinHelper1342322<K, V> method1() {
      return new MixinHelper1342322<>();
   }

   public static <K, V> MixinHelper1342322<K, V> method2(int var0, int var1) {
      return new MixinHelper1342322<>(var0, var1);
   }

   public static <K, V> MixinHelper1342322<K, V> method3(Multimap<? extends K, ? extends V> var0) {
      return new MixinHelper1342322<>(var0);
   }

   private MixinHelper1342322() {
      this(12, 3);
   }

   private MixinHelper1342322(int var1, int var2) {
      super(MixinHelper8_2.newHashMapWithExpectedSize(var1));
      MixinHelper18_3.checkNonnegative(var2, "expectedValuesPerKey");
      this.expectedValuesPerKey = var2;
   }

   private MixinHelper1342322(Multimap<? extends K, ? extends V> var1) {
      this(var1.keySet().size(), var1 instanceof MixinHelper1342322 ? ((MixinHelper1342322)var1).expectedValuesPerKey : 3);
      this.method1(var1);
   }

   @Override
   List<V> createCollection() {
      return new ArrayList<>(this.expectedValuesPerKey);
   }

   @Deprecated
   public void trimToSize() {
      for (Collection var2 : this.backingMap().values()) {
         ArrayList var3 = (ArrayList)var2;
         var3.trimToSize();
      }
   }

   @Annotation3
   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      MixinHelper7_10.method4(this, var1);
   }

   @Annotation3
   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.expectedValuesPerKey = 3;
      int var2 = MixinHelper7_10.readCount(var1);
      HashMap var3 = Maps.newHashMap();
      this.<init>(var3);
      MixinHelper7_10.method6(this, var1, var2);
   }
}
