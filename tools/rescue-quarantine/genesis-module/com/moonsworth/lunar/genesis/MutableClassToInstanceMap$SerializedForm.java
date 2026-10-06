package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Map;

final class MutableClassToInstanceMap$SerializedForm<B> implements Serializable {
   private final Map<Class<? extends B>, B> field1;
   private static final long field2 = 0L;

   MutableClassToInstanceMap$SerializedForm(Map<Class<? extends B>, B> map1) {
      this.field1 = map1;
   }

   Object readResolve() {
      return MixinHelper3122.method2(this.field1);
   }
}
