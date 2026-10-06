package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum MixinHelperType_3 {
   OPEN(false),
   CLOSED(true);

   final boolean inclusive;

   MixinHelperType_3(boolean var3) {
      this.inclusive = var3;
   }

   static MixinHelperType_3 forBoolean(boolean var0) {
      return var0 ? CLOSED : OPEN;
   }

   MixinHelperType_3 flip() {
      return forBoolean(!this.inclusive);
   }
}
