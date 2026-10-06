package com.moonsworth.lunar.genesis;
import com.google.common.collect.Range;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;

final class Cut$AboveValue<C extends Comparable> extends SerializableLoader<C> {
   private static final long field3 = 0L;

   Cut$AboveValue(C value1) {
      super((Comparable)Preconditions.checkNotNull(value1));
   }

   boolean isLessThan(C value1) {
      return Range.compareOrThrow(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI, value1) < 0;
   }

   BoundType method1() {
      return BoundType.OPEN;
   }

   BoundType method2() {
      return BoundType.CLOSED;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   SerializableLoader<C> method3(BoundType mixinhelpertype_31, MixinHelper40<C> mixinhelper402) {
      switch (this.field1[mixinhelpertype_31.ordinal()]) {
         case 1:
            Comparable comparable3 = mixinhelper402.next(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI);
            return comparable3 == null ? SerializableLoader.method9() : ICRHORIIHOHROHOHOCOOHOOCOORRHO(comparable3);
         case 2:
            return this;
         default:
            throw new AssertionError();
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   SerializableLoader<C> method4(BoundType mixinhelpertype_31, MixinHelper40<C> mixinhelper402) {
      switch (this.field1[mixinhelpertype_31.ordinal()]) {
         case 1:
            return this;
         case 2:
            Comparable comparable3 = mixinhelper402.next(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI);
            return comparable3 == null ? SerializableLoader.method10() : ICRHORIIHOHROHOHOCOOHOOCOORRHO(comparable3);
         default:
            throw new AssertionError();
      }
   }

   void describeAsLowerBound(StringBuilder builder1) {
      builder1.append('(').append(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI);
   }

   void describeAsUpperBound(StringBuilder builder1) {
      builder1.append(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI).append(']');
   }

   C method5(MixinHelper40<C> mixinhelper401) {
      return (C)mixinhelper401.next(this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI);
   }

   C method6(MixinHelper40<C> mixinhelper401) {
      return (C)this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI;
   }

   SerializableLoader<C> method7(MixinHelper40<C> mixinhelper401) {
      Comparable comparable2 = this.method5(mixinhelper401);
      return comparable2 != null ? ICRHORIIHOHROHOHOCOOHOOCOORRHO(comparable2) : SerializableLoader.method10();
   }

   public int hashCode() {
      return ~this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI.hashCode();
   }

   public String toString() {
      return "/" + this.HRIIIIIHOHOOCRIICIRIIOCIRHICHI + "\\";
   }
}
