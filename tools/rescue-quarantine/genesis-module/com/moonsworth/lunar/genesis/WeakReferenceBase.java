package com.moonsworth.lunar.genesis;

import java.lang.ref.WeakReference;
import com.google.common.base.FinalizableReference;

@Annotation3
public abstract class WeakReferenceBase<T> extends WeakReference<T> implements FinalizableReference {
   protected WeakReferenceBase(T var1, CloseableLoader var2) {
      super((T)var1, var2.field4);
      var2.cleanUp();
   }
}
