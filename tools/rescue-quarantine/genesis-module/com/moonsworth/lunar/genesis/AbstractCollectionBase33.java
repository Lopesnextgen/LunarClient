package com.moonsworth.lunar.genesis;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

@GwtCompatible(serializable = true, emulated = true)
public final class AbstractCollectionBase33<E> extends AbstractCollectionBase3<E> {
   @Annotation3
   private static final long field2 = 0L;

   public static <E> AbstractCollectionBase33<E> method1() {
      return new AbstractCollectionBase33<>();
   }

   public static <E> AbstractCollectionBase33<E> method2(int var0) {
      return new AbstractCollectionBase33<>(var0);
   }

   public static <E> AbstractCollectionBase33<E> method3(Iterable<? extends E> var0) {
      AbstractCollectionBase33 var1 = method2(Multisets.inferDistinctElements(var0));
      Iterables.addAll(var1, var0);
      return var1;
   }

   private AbstractCollectionBase33() {
      super(new LinkedHashMap<>());
   }

   private AbstractCollectionBase33(int var1) {
      super(Maps.newLinkedHashMapWithExpectedSize(var1));
   }

   @Annotation3
   private void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      MixinHelper7_10.method1(this, var1);
   }

   @Annotation3
   private void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      int var2 = MixinHelper7_10.readCount(var1);
      this.setBackingMap(new LinkedHashMap<>());
      MixinHelper7_10.method3(this, var1, var2);
   }
}
