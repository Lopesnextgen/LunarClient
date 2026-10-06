package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.base.Preconditions;

final class TreeRangeMap$1 implements RangeMap {
   TreeRangeMap$1() {
   }

   @Override
   public @Nullable Object get(Comparable comparable1) {
      return null;
   }

   @Override
   public @Nullable Entry<Range, Object> getEntry(Comparable comparable1) {
      return null;
   }

   @Override
   public Range method1() {
      throw new NoSuchElementException();
   }

   @Override
   public void method2(Range serializablebase21, Object obj2) {
      Preconditions.checkNotNull(serializablebase21);
      throw new IllegalArgumentException("Cannot insert range " + serializablebase21 + " into an empty subRangeMap");
   }

   @Override
   public void method3(Range serializablebase21, Object obj2) {
      Preconditions.checkNotNull(serializablebase21);
      throw new IllegalArgumentException("Cannot insert range " + serializablebase21 + " into an empty subRangeMap");
   }

   @Override
   public void method4(RangeMap comparableextension1) {
      if (!comparableextension1.asMapOfRanges().isEmpty()) {
         throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void method5(Range serializablebase21) {
      Preconditions.checkNotNull(serializablebase21);
   }

   @Override
   public void method6(Range serializablebase21, @Nullable Object obj2, BiFunction function3) {
      Preconditions.checkNotNull(serializablebase21);
      throw new IllegalArgumentException("Cannot merge range " + serializablebase21 + " into an empty subRangeMap");
   }

   @Override
   public Map<Range, Object> asMapOfRanges() {
      return Collections.emptyMap();
   }

   @Override
   public Map<Range, Object> asDescendingMapOfRanges() {
      return Collections.emptyMap();
   }

   @Override
   public RangeMap method7(Range serializablebase21) {
      Preconditions.checkNotNull(serializablebase21);
      return this;
   }
}
