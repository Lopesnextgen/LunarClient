package com.moonsworth.lunar.genesis;

import java.util.Collection;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.base.Function;

class MediaType$1 implements Function<Collection<String>, ImmutableMultiset<String>> {
   MediaType$1(MixinHelper5_12 mixinhelper5_121) {
      this.field1 = mixinhelper5_121;
   }

   public ImmutableMultiset<String> method1(Collection<String> list1) {
      return ImmutableMultiset.method9(list1);
   }
}
