package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import com.google.common.collect.Lists;

final class CycleDetectingLockFactory$1 extends ThreadLocal<ArrayList<MixinHelper9$Data16>> {
   CycleDetectingLockFactory$1() {
   }

   protected ArrayList<MixinHelper9$Data16> initialValue() {
      return Lists.newArrayListWithCapacity(3);
   }
}
