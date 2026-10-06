package com.moonsworth.lunar.genesis;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

class Synchronized$SynchronizedObject implements Serializable {
   final Object field1;
   final Object field2;
   @GwtIncompatible
   private static final long field3 = 0L;

   Synchronized$SynchronizedObject(Object obj1, @Nullable Object obj2) {
      this.field1 = Preconditions.checkNotNull(obj1);
      this.field2 = obj2 == null ? this : obj2;
   }

   Object delegate() {
      return this.field1;
   }

   @Override
   public String toString() {
      synchronized (this.field2) {
         return this.field1.toString();
      }
   }

   @GwtIncompatible
   private void writeObject(ObjectOutputStream objectoutputstream1) {
      synchronized (this.field2) {
         objectoutputstream1.defaultWriteObject();
      }
   }
}
