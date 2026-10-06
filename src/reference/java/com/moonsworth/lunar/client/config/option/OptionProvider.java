package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.Identifier;
import com.moonsworth.lunar.client.config.option.ClientOption;

public interface OptionProvider<O extends ClientOption<T>, T> {
   @Identifier
   String getId();

   O method1();

   static ClientOption<?>[] method2(OptionProvider<?, ?>... items0) {
      ClientOption[] items1 = new ClientOption[items0.length];

      for (int index2 = 0; index2 < items0.length; index2++) {
         items1[index2] = items0[index2].method1();
      }

      return items1;
   }
}
