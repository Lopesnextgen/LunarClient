package com.moonsworth.lunar.genesis;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class Striped$SmallLazyStriped$ArrayReference<L> extends WeakReference<L> {
   final int field1;

   Striped$SmallLazyStriped$ArrayReference(L l1, int number2, ReferenceQueue<L> referencequeue3) {
      super((L)l1, referencequeue3);
      this.field1 = number2;
   }
}
