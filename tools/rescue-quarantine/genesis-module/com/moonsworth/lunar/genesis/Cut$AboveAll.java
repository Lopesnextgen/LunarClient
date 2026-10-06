package com.moonsworth.lunar.genesis;
import com.google.common.collect.BoundType;

final class Cut$AboveAll extends SerializableLoader<Comparable<?>> {
   private static final Cut$AboveAll field3 = new Cut$AboveAll();
   private static final long field4 = 0L;

   private Cut$AboveAll() {
      super(null);
   }

   Comparable<?> endpoint() {
      throw new IllegalStateException("range unbounded on this side");
   }

   boolean isLessThan(Comparable<?> comparable1) {
      return false;
   }

   BoundType method1() {
      throw new AssertionError("this statement should be unreachable");
   }

   BoundType method2() {
      throw new IllegalStateException();
   }

   SerializableLoader<Comparable<?>> method3(BoundType mixinhelpertype_31, MixinHelper40<Comparable<?>> mixinhelper402) {
      throw new AssertionError("this statement should be unreachable");
   }

   SerializableLoader<Comparable<?>> method4(BoundType mixinhelpertype_31, MixinHelper40<Comparable<?>> mixinhelper402) {
      throw new IllegalStateException();
   }

   void describeAsLowerBound(StringBuilder builder1) {
      throw new AssertionError();
   }

   void describeAsUpperBound(StringBuilder builder1) {
      builder1.append("+∞)");
   }

   Comparable<?> method5(MixinHelper40<Comparable<?>> mixinhelper401) {
      throw new AssertionError();
   }

   Comparable<?> method6(MixinHelper40<Comparable<?>> mixinhelper401) {
      return mixinhelper401.maxValue();
   }

   public int method8(SerializableLoader<Comparable<?>> serializableloader1) {
      return serializableloader1 == this ? 0 : 1;
   }

   public int hashCode() {
      return System.identityHashCode(this);
   }

   public String toString() {
      return "+∞";
   }

   private Object readResolve() {
      return field3;
   }
}
