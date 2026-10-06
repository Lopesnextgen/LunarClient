package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.base.Preconditions;

final class MixinHelper4$Data18 extends MixinHelper4_9 {
   private static final MixinHelper4$Data18 field1 = new MixinHelper4$Data18();

   private MixinHelper4$Data18() {
   }

   @Override
   void dispatch(Object var1, Iterator<MixinHelper6_6> var2) {
      Preconditions.checkNotNull(var1);

      while (var2.hasNext()) {
         ((MixinHelper6_6)var2.next()).method2(var1);
      }
   }
}
