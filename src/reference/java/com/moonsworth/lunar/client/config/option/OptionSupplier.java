package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.util.Annotation3;

public interface OptionSupplier<O extends ClientOption<T>, T> {
   @Annotation3
   String getId();

   O method1();

   static ClientOption<?>[] method2(OptionSupplier<?, ?>... items) {
      ClientOption[] var1 = new ClientOption[items.length];

      for (int var2 = 0; var2 < items.length; var2++) {
         var1[var2] = items[var2].method1();
      }

      return var1;
   }
}
