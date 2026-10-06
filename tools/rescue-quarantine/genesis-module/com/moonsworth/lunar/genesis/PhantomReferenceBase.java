package com.moonsworth.lunar.genesis;

import java.lang.ref.PhantomReference;
import com.google.common.base.FinalizableReference;

@Annotation3
public abstract class PhantomReferenceBase<T> extends PhantomReference<T> implements FinalizableReference {
   protected PhantomReferenceBase(T var1, CloseableLoader var2) {
      super((T)var1, var2.field4);
      var2.cleanUp();
   }
}
