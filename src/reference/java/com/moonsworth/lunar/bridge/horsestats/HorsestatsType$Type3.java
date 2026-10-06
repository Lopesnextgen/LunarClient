package com.moonsworth.lunar.bridge.horsestats;

import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;

public enum HorsestatsType$Type3 implements Iterable<HorsestatsType_2>, Predicate<HorsestatsType_2> {
   HORIZONTAL(
      new HorsestatsType_2[]{HorsestatsType_2.NORTH, HorsestatsType_2.EAST, HorsestatsType_2.SOUTH, HorsestatsType_2.WEST},
      new HorsestatsType$Type[]{HorsestatsType$Type.X, HorsestatsType$Type.Z}
   ),
   VERTICAL(new HorsestatsType_2[]{HorsestatsType_2.UP, HorsestatsType_2.DOWN}, new HorsestatsType$Type[]{HorsestatsType$Type.Y});

   private final HorsestatsType_2[] facingArray;
   private final HorsestatsType$Type[] axisArray;

   HorsestatsType$Type3(HorsestatsType_2[] items3, HorsestatsType$Type[] items4) {
      this.facingArray = items3;
      this.axisArray = items4;
   }

   public boolean test(HorsestatsType_2 horsestatstype_21) {
      return horsestatstype_21 != null && horsestatstype_21.getAxis().getType() == this;
   }

   @NotNull
   @Override
   public Iterator<HorsestatsType_2> iterator() {
      return Iterators.forArray(this.facingArray);
   }

   public Stream<HorsestatsType_2> stream() {
      return Arrays.stream(this.facingArray);
   }

   public int size() {
      return this.facingArray.length;
   }
}
