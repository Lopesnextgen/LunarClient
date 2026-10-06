package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.BridgeValueCodec;

public final class PromiseJS<T> {
   private volatile long handle;
   private BridgeValueCodec bridgeCodec;
   private Class<?> bridgeValueType;

   private PromiseJS(long value) {
      this.handle = value;
   }

   public void resolve(T t) {
      if (this.handle == 0L) {
         throw new IllegalStateException("Future already completed");
      }

      if (t == null) {
         this._resolve((String)null);
      } else if (this.bridgeCodec != null && this.bridgeValueType != null) {
         this._resolve(this.bridgeCodec.objectToString(t, this.bridgeValueType));
      } else if (t instanceof String) {
         this._resolve((String)t);
      } else {
         throw new IllegalStateException("No type serializer for " + t.getClass().getName());
      }
   }

   public void _resolve(String text1) {
      if (this.handle == 0L) {
         throw new IllegalStateException("Future already completed");
      }

      this.resolve0(text1);
      this.handle = 0L;
      this.clearBridgeCodec();
   }

   public void resolve() {
      if (this.handle == 0L) {
         throw new IllegalStateException("Future already completed");
      }

      this.resolve1();
      this.handle = 0L;
      this.clearBridgeCodec();
   }

   public void reject(String text1) {
      if (this.handle == 0L) {
         throw new IllegalStateException("Future already completed");
      }

      this.reject0(text1);
      this.handle = 0L;
      this.clearBridgeCodec();
   }

   public void reject() {
      if (this.handle == 0L) {
         throw new IllegalStateException("Future already completed");
      }

      this.reject1();
      this.handle = 0L;
      this.clearBridgeCodec();
   }

   public void bindBridgeCodec(BridgeValueCodec bridgevaluecodec1, Class<?> clazz2) {
      this.bridgeCodec = bridgevaluecodec1;
      this.bridgeValueType = clazz2;
   }

   private void clearBridgeCodec() {
      this.bridgeCodec = null;
      this.bridgeValueType = null;
   }

   private native void resolve0(String text1);

   private native void resolve1();

   private native void reject0(String text1);

   private native void reject1();
}
