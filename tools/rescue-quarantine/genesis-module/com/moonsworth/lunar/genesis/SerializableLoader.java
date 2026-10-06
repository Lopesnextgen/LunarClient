package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Range;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;

@GwtCompatible
abstract class SerializableLoader<C extends Comparable> implements Serializable, Comparable<SerializableLoader<C>> {
   final @Nullable C field1;
   private static final long field2 = 0L;

   SerializableLoader(@Nullable C var1) {
      this.field1 = (C)var1;
   }

   abstract boolean isLessThan(C var1);

   abstract MixinHelperType_3 method1();

   abstract MixinHelperType_3 method2();

   abstract SerializableLoader<C> method3(MixinHelperType_3 var1, MixinHelper40<C> var2);

   abstract SerializableLoader<C> method4(MixinHelperType_3 var1, MixinHelper40<C> var2);

   abstract void describeAsLowerBound(StringBuilder var1);

   abstract void describeAsUpperBound(StringBuilder var1);

   abstract C method5(MixinHelper40<C> var1);

   abstract C method6(MixinHelper40<C> var1);

   SerializableLoader<C> method7(MixinHelper40<C> var1) {
      return this;
   }

   public int method8(SerializableLoader<C> var1) {
      if (var1 == method9()) {
         return 1;
      }

      if (var1 == method10()) {
         return -1;
      }

      int var2 = Range.compareOrThrow(this.field1, var1.field1);
      return var2 != 0 ? var2 : Booleans.compare(this instanceof SerializableLoader.Data3, var1 instanceof SerializableLoader.Data3);
   }

   C endpoint() {
      return this.field1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 instanceof SerializableLoader) {
         SerializableLoader var2 = (SerializableLoader)var1;

         try {
            int var3 = this.method8(var2);
            return var3 == 0;
         } catch (ClassCastException var4) {
         }
      }

      return false;
   }

   @Override
   public abstract int hashCode();

   static <C extends Comparable> SerializableLoader<C> method9() {
      return SerializableLoader.Data5.field3;
   }

   static <C extends Comparable> SerializableLoader<C> method10() {
      return SerializableLoader.Data4.field3;
   }

   static <C extends Comparable> SerializableLoader<C> method11(C var0) {
      return new SerializableLoader.Data6<>((C)var0);
   }

   static <C extends Comparable> SerializableLoader<C> method12(C var0) {
      return new SerializableLoader.Data3<>((C)var0);
   }

   private static final class Data3<C extends Comparable> extends SerializableLoader<C> {
      private static final long field3 = 0L;

      Data3(C var1) {
         super(Preconditions.checkNotNull((C)var1));
      }

      @Override
      boolean isLessThan(C var1) {
         return Range.compareOrThrow(this.field1, var1) < 0;
      }

      @Override
      MixinHelperType_3 method1() {
         return MixinHelperType_3.OPEN;
      }

      @Override
      MixinHelperType_3 method2() {
         return MixinHelperType_3.CLOSED;
      }

      @Override
      SerializableLoader<C> method3(MixinHelperType_3 var1, MixinHelper40<C> var2) {
         switch (var1) {
            case CLOSED:
               Comparable var3 = var2.next(this.field1);
               return var3 == null ? SerializableLoader.method9() : ICRHORIIHOHROHOHOCOOHOOCOORRHO(var3);
            case OPEN:
               return this;
            default:
               throw new AssertionError();
         }
      }

      @Override
      SerializableLoader<C> method4(MixinHelperType_3 var1, MixinHelper40<C> var2) {
         switch (var1) {
            case CLOSED:
               return this;
            case OPEN:
               Comparable var3 = var2.next(this.field1);
               return var3 == null ? SerializableLoader.method10() : ICRHORIIHOHROHOHOCOOHOOCOORRHO(var3);
            default:
               throw new AssertionError();
         }
      }

      @Override
      void describeAsLowerBound(StringBuilder var1) {
         var1.append('(').append(this.field1);
      }

      @Override
      void describeAsUpperBound(StringBuilder var1) {
         var1.append(this.field1).append(']');
      }

      @Override
      C method5(MixinHelper40<C> var1) {
         return (C)var1.next(this.field1);
      }

      @Override
      C method6(MixinHelper40<C> var1) {
         return (C)this.field1;
      }

      @Override
      SerializableLoader<C> method7(MixinHelper40<C> var1) {
         Comparable var2 = this.method5(var1);
         return var2 != null ? ICRHORIIHOHROHOHOCOOHOOCOORRHO(var2) : SerializableLoader.method10();
      }

      @Override
      public int hashCode() {
         return ~this.field1.hashCode();
      }

      @Override
      public String toString() {
         return "/" + this.field1 + "\\";
      }
   }

   private static final class Data4 extends SerializableLoader<Comparable<?>> {
      private static final SerializableLoader.Data4 field3 = new SerializableLoader.Data4();
      private static final long field4 = 0L;

      private Data4() {
         super(null);
      }

      @Override
      Comparable<?> endpoint() {
         throw new IllegalStateException("range unbounded on this side");
      }

      @Override
      boolean isLessThan(Comparable<?> var1) {
         return false;
      }

      @Override
      MixinHelperType_3 method1() {
         throw new AssertionError("this statement should be unreachable");
      }

      @Override
      MixinHelperType_3 method2() {
         throw new IllegalStateException();
      }

      @Override
      SerializableLoader<Comparable<?>> method3(MixinHelperType_3 var1, MixinHelper40<Comparable<?>> var2) {
         throw new AssertionError("this statement should be unreachable");
      }

      @Override
      SerializableLoader<Comparable<?>> method4(MixinHelperType_3 var1, MixinHelper40<Comparable<?>> var2) {
         throw new IllegalStateException();
      }

      @Override
      void describeAsLowerBound(StringBuilder var1) {
         throw new AssertionError();
      }

      @Override
      void describeAsUpperBound(StringBuilder var1) {
         var1.append("+∞)");
      }

      @Override
      Comparable<?> method5(MixinHelper40<Comparable<?>> var1) {
         throw new AssertionError();
      }

      @Override
      Comparable<?> method6(MixinHelper40<Comparable<?>> var1) {
         return var1.maxValue();
      }

      @Override
      public int method8(SerializableLoader<Comparable<?>> var1) {
         return var1 == this ? 0 : 1;
      }

      @Override
      public int hashCode() {
         return System.identityHashCode(this);
      }

      @Override
      public String toString() {
         return "+∞";
      }

      private Object readResolve() {
         return field3;
      }
   }

   private static final class Data5 extends SerializableLoader<Comparable<?>> {
      private static final SerializableLoader.Data5 field3 = new SerializableLoader.Data5();
      private static final long field4 = 0L;

      private Data5() {
         super(null);
      }

      @Override
      Comparable<?> endpoint() {
         throw new IllegalStateException("range unbounded on this side");
      }

      @Override
      boolean isLessThan(Comparable<?> var1) {
         return true;
      }

      @Override
      MixinHelperType_3 method1() {
         throw new IllegalStateException();
      }

      @Override
      MixinHelperType_3 method2() {
         throw new AssertionError("this statement should be unreachable");
      }

      @Override
      SerializableLoader<Comparable<?>> method3(MixinHelperType_3 var1, MixinHelper40<Comparable<?>> var2) {
         throw new IllegalStateException();
      }

      @Override
      SerializableLoader<Comparable<?>> method4(MixinHelperType_3 var1, MixinHelper40<Comparable<?>> var2) {
         throw new AssertionError("this statement should be unreachable");
      }

      @Override
      void describeAsLowerBound(StringBuilder var1) {
         var1.append("(-∞");
      }

      @Override
      void describeAsUpperBound(StringBuilder var1) {
         throw new AssertionError();
      }

      @Override
      Comparable<?> method5(MixinHelper40<Comparable<?>> var1) {
         return var1.minValue();
      }

      @Override
      Comparable<?> method6(MixinHelper40<Comparable<?>> var1) {
         throw new AssertionError();
      }

      @Override
      SerializableLoader<Comparable<?>> method7(MixinHelper40<Comparable<?>> var1) {
         try {
            return SerializableLoader.method11(var1.minValue());
         } catch (NoSuchElementException var3) {
            return this;
         }
      }

      @Override
      public int method8(SerializableLoader<Comparable<?>> var1) {
         return var1 == this ? 0 : -1;
      }

      @Override
      public int hashCode() {
         return System.identityHashCode(this);
      }

      @Override
      public String toString() {
         return "-∞";
      }

      private Object readResolve() {
         return field3;
      }
   }

   private static final class Data6<C extends Comparable> extends SerializableLoader<C> {
      private static final long field3 = 0L;

      Data6(C var1) {
         super(Preconditions.checkNotNull((C)var1));
      }

      @Override
      boolean isLessThan(C var1) {
         return Range.compareOrThrow(this.field1, var1) <= 0;
      }

      @Override
      MixinHelperType_3 method1() {
         return MixinHelperType_3.CLOSED;
      }

      @Override
      MixinHelperType_3 method2() {
         return MixinHelperType_3.OPEN;
      }

      @Override
      SerializableLoader<C> method3(MixinHelperType_3 var1, MixinHelper40<C> var2) {
         switch (var1) {
            case CLOSED:
               return this;
            case OPEN:
               Comparable var3 = var2.previous(this.field1);
               return var3 == null ? SerializableLoader.method9() : new SerializableLoader.Data3<>((C)var3);
            default:
               throw new AssertionError();
         }
      }

      @Override
      SerializableLoader<C> method4(MixinHelperType_3 var1, MixinHelper40<C> var2) {
         switch (var1) {
            case CLOSED:
               Comparable var3 = var2.previous(this.field1);
               return var3 == null ? SerializableLoader.method10() : new SerializableLoader.Data3<>((C)var3);
            case OPEN:
               return this;
            default:
               throw new AssertionError();
         }
      }

      @Override
      void describeAsLowerBound(StringBuilder var1) {
         var1.append('[').append(this.field1);
      }

      @Override
      void describeAsUpperBound(StringBuilder var1) {
         var1.append(this.field1).append(')');
      }

      @Override
      C method5(MixinHelper40<C> var1) {
         return (C)this.field1;
      }

      @Override
      C method6(MixinHelper40<C> var1) {
         return (C)var1.previous(this.field1);
      }

      @Override
      public int hashCode() {
         return this.field1.hashCode();
      }

      @Override
      public String toString() {
         return "\\" + this.field1 + "/";
      }
   }
}
