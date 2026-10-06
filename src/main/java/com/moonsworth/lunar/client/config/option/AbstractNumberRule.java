package com.moonsworth.lunar.client.config.option;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.function.IntFunction;
import lombok.Generated;

public abstract class AbstractNumberRule<T extends Number & Comparable<T>> implements NumberRule<T> {
   private static final boolean field1 = true;
   private static final Int2ObjectMap<AbstractNumberRule<?>> field2 = new Int2ObjectOpenHashMap();
   private final boolean field3;
   private final boolean field4;

   @Override
   public int method3() {
      return 0;
   }

   public static <A extends AbstractNumberRule<T>, T extends Number & Comparable<T>> A method2(int number0, IntFunction<A> intfunction1) {
      return (A)field2.computeIfAbsent(number0, intfunction1);
   }

   @Generated
   @Override
   public boolean method1() {
      return this.field3;
   }

   @Generated
   @Override
   public boolean method2() {
      return this.field4;
   }

   @Generated
   public AbstractNumberRule(boolean flag, boolean flag2) {
      this.field3 = flag;
      this.field4 = flag2;
   }
}
