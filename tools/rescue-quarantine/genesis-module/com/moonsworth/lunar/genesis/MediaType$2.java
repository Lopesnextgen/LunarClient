package com.moonsworth.lunar.genesis;
import com.google.common.base.Function;

class MediaType$2 implements Function<String, String> {
   MediaType$2(MixinHelper5_12 mixinhelper5_121) {
      this.field1 = mixinhelper5_121;
   }

   public String apply(String text1) {
      return MixinHelper5_12.method20().matchesAllOf(text1) && !text1.isEmpty() ? text1 : MixinHelper5_12.access$100(text1);
   }
}
