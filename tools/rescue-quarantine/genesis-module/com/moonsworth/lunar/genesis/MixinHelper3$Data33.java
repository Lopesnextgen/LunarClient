package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterator.OfLong;
import java.util.function.Function;
import java.util.function.LongConsumer;

final class MixinHelper3$Data33<InElementT> extends MixinHelper3$Data35<InElementT, Long, LongConsumer, OfLong> implements OfLong {
   MixinHelper3$Data33(OfLong var1, Spliterator<InElementT> var2, Function<? super InElementT, OfLong> var3, int var4, long var5) {
      super(var1, var2, var3, MixinHelper3$Data33::new, var4, var5);
   }
}
