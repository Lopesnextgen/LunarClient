package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.function.Function;

@FunctionalInterface
interface CollectSpliterators$FlatMapSpliterator$Factory<InElementT, OutSpliteratorT extends Spliterator<?>> {
   OutSpliteratorT newFlatMapSpliterator(
      OutSpliteratorT outspliteratort1, Spliterator<InElementT> spliterator2, Function<? super InElementT, OutSpliteratorT> function3, int number4, long number5
   );
}
