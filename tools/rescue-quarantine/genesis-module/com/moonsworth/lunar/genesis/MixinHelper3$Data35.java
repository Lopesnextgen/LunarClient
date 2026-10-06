package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterator.OfPrimitive;
import java.util.function.Function;

abstract class MixinHelper3$Data35<InElementT, OutElementT, OutConsumerT, OutSpliteratorT extends OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT>>
   extends MixinHelper3$Data31<InElementT, OutElementT, OutSpliteratorT>
   implements OfPrimitive<OutElementT, OutConsumerT, OutSpliteratorT> {
   MixinHelper3$Data35(
      OutSpliteratorT var1,
      Spliterator<InElementT> var2,
      Function<? super InElementT, OutSpliteratorT> var3,
      MixinHelper3$Data31.Extension<InElementT, OutSpliteratorT> var4,
      int var5,
      long var6
   ) {
      super((OutSpliteratorT)var1, var2, var3, var4, var5, var6);
   }

   @Override
   public final boolean tryAdvance(OutConsumerT var1) {
      while (this.prefix == null || !this.prefix.tryAdvance((OutConsumerT)var1)) {
         this.prefix = null;
         if (!this.HIRHICOCROIRIROOOCHIIORCIIRIIC.tryAdvance(var1x -> {
            Spliterator var10002 = (Spliterator)this.field2.apply(var1x);
         })) {
            return false;
         }
      }

      if (this.estimatedSize != Long.MAX_VALUE) {
         this.estimatedSize--;
      }

      return true;
   }

   @Override
   public final void forEachRemaining(OutConsumerT var1) {
      if (this.prefix != null) {
         this.prefix.forEachRemaining((OutConsumerT)var1);
         this.prefix = null;
      }

      this.HIRHICOCROIRIROOOCHIIORCIIRIIC.forEachRemaining(var2 -> {
         OfPrimitive var3 = (OfPrimitive)this.field2.apply(var2);
         if (var3 != null) {
            var3.forEachRemaining(var1);
         }
      });
      this.estimatedSize = 0L;
   }
}
