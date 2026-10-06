package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterator.OfInt;
import java.util.function.Function;
import java.util.function.IntConsumer;

final class MixinHelper3$Data36<InElementT> extends MixinHelper3$Data35<InElementT, Integer, IntConsumer, OfInt> implements OfInt {
   MixinHelper3$Data36(OfInt var1, Spliterator<InElementT> var2, Function<? super InElementT, OfInt> var3, int var4, long var5) {
      super(var1, var2, var3, MixinHelper3$Data36::new, var4, var5);
   }
}
