package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterator.OfDouble;
import java.util.function.DoubleConsumer;
import java.util.function.Function;

final class MixinHelper3$Data32<InElementT> extends MixinHelper3$Data35<InElementT, Double, DoubleConsumer, OfDouble> implements OfDouble {
   MixinHelper3$Data32(OfDouble var1, Spliterator<InElementT> var2, Function<? super InElementT, OfDouble> var3, int var4, long var5) {
      super(var1, var2, var3, MixinHelper3$Data32::new, var4, var5);
   }
}
