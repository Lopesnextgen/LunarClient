package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum MixinHelperType {
   EXPLICIT,
   REPLACED,
   COLLECTED,
   EXPIRED,
   SIZE;

   MixinHelperType() {
   }

   abstract boolean wasEvicted();
}
