package com.moonsworth.lunar.genesis;

import java.lang.ref.SoftReference;
import com.google.common.base.FinalizableReference;

@Annotation3
public abstract class SoftReferenceBase<T> extends SoftReference<T> implements FinalizableReference {
   protected SoftReferenceBase(T var1, CloseableLoader var2) {
      super((T)var1, var2.field4);
      var2.cleanUp();
   }
}
