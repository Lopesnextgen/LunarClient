package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Predicate;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

@GwtIncompatible
class Predicates$ContainsPatternPredicate implements Predicate<CharSequence>, Serializable {
   final CommonPattern field1;
   private static final long field2 = 0L;

   Predicates$ContainsPatternPredicate(CommonPattern mixinhelper22_21) {
      this.field1 = (CommonPattern)Preconditions.checkNotNull(mixinhelper22_21);
   }

   public boolean apply(CharSequence text1) {
      return this.field1.method1(text1).find();
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1.pattern(), this.field1.flags()});
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Predicates$ContainsPatternPredicate)) {
         return false;
      }

      Predicates$ContainsPatternPredicate mixinhelper20$data62 = (Predicates$ContainsPatternPredicate)obj1;
      return Objects.equal(this.field1.pattern(), mixinhelper20$data62.field1.pattern()) && this.field1.flags() == mixinhelper20$data62.field1.flags();
   }

   @Override
   public String toString() {
      String text1 = MoreObjects.method1(this.field1).method2("pattern", this.field1.pattern()).method7("pattern.flags", this.field1.flags()).toString();
      return "Predicates.contains(" + text1 + ")";
   }
}
