package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

final class MoreCollectors$ToOptionalState {
   static final int field1 = 4;
   @Nullable Object element = null;
   @Nullable List<Object> extras = null;

   MoreCollectors$ToOptionalState() {
   }

   IllegalArgumentException multiples(boolean flag1) {
      StringBuilder builder2 = new StringBuilder().append("expected one element but was: <").append(this.element);

      for (Object obj4 : this.extras) {
         builder2.append(", ").append(obj4);
      }

      if (flag1) {
         builder2.append(", ...");
      }

      builder2.append('>');
      throw new IllegalArgumentException(builder2.toString());
   }

   void add(Object obj1) {
      Preconditions.checkNotNull(obj1);
      if (this.element == null) {
         this.element = obj1;
      } else if (this.extras == null) {
         this.extras = new ArrayList<>(4);
         this.extras.add(obj1);
      } else {
         if (this.extras.size() >= 4) {
            throw this.multiples(true);
         }

         this.extras.add(obj1);
      }
   }

   MoreCollectors$ToOptionalState method1(MoreCollectors$ToOptionalState mixinhelper21$data1) {
      if (this.element == null) {
         return mixinhelper21$data1;
      }

      if (mixinhelper21$data1.element == null) {
         return this;
      }

      if (this.extras == null) {
         this.extras = new ArrayList<>();
      }

      this.extras.add(mixinhelper21$data1.element);
      if (mixinhelper21$data1.extras != null) {
         this.extras.addAll(mixinhelper21$data1.extras);
      }

      if (this.extras.size() > 4) {
         this.extras.subList(4, this.extras.size()).clear();
         throw this.multiples(true);
      } else {
         return this;
      }
   }

   Optional<Object> getOptional() {
      if (this.extras == null) {
         return Optional.ofNullable(this.element);
      } else {
         throw this.multiples(false);
      }
   }

   Object getElement() {
      if (this.element == null) {
         throw new NoSuchElementException();
      } else if (this.extras == null) {
         return this.element;
      } else {
         throw this.multiples(false);
      }
   }
}
