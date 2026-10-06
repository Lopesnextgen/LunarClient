package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.NativeHandle;
import com.moonsworth.webosr.handler.FunctionJS;
import com.moonsworth.webosr.input.Keyboard;
import com.moonsworth.webosr.input.Mouse;

public final class Browser extends NativeHandle {
   private Browser(long var1) {
      super(var1);
   }

   public native void loadURL(String var1);

   public native String getURL();

   public native void next();

   public native void previous();

   public native void reload();

   public native void stopLoad();

   public native boolean hasFocus();

   public native void setFocus(boolean var1);

   public native boolean hasInputFocus();

   public native void triggerResize(int var1, int var2);

   public void triggerKeyEvent(String var1, int var2, String var3, int var4, Keyboard.Action var5, int var6) {
      this.triggerKeyEvent(var1, var2, var3, var4, var5.getValue(), var6);
   }

   public void triggerMouseEvent(Mouse.Button var1, Mouse.Action var2, int var3, int var4, int var5) {
      this.triggerMouseEvent(var1.getValue(), var2.getValue(), var3, var4, var5);
   }

   private native void triggerMouseEvent(int var1, int var2, int var3, int var4, int var5);

   private native void triggerKeyEvent(String var1, int var2, String var3, int var4, int var5, int var6);

   public native void triggerScrollEvent(int var1, int var2, int var3, int var4, int var5);

   public native int getTexture();

   public native void setDeviceScaleFactor(double var1);

   public native int getWidth();

   public native int getHeight();

   public native NativeFuture eval(String var1);

   public native void evalNoResult(String var1);

   public native void bindFunction(String var1, FunctionJS var2);

   public native void unbindFunction(String var1);

   public native void createInspector();

   @Override
   protected native void destroy();

   public interface Listener {
      default void onBeginLoad(Browser var1, long var2, boolean var4, String var5) {
      }

      default void onFinishLoad(Browser var1, long var2, boolean var4, String var5) {
      }

      default void onFailLoading(Browser var1, long var2, boolean var4, String var5, String var6, String var7, int var8) {
      }

      default void onWindowObjectReady(Browser var1, long var2, boolean var4, String var5) {
      }

      default void onDocumentReady(Browser var1, long var2, boolean var4, String var5) {
      }

      default void onUpdateHistory(Browser var1) {
      }

      default boolean onNetworkRequest(Browser var1, String var2, String var3, String var4, String var5, String var6, String var7) {
         return true;
      }

      default String onEnforcePublicKey(Browser var1, String var2, String var3, String var4, String var5, String var6, String var7) {
         return null;
      }

      default void onChangeTitle(Browser var1, String var2) {
      }

      default void onChangeURL(Browser var1, String var2) {
      }

      default void onChangeTooltip(Browser var1, String var2) {
      }

      default void onRequestClose(Browser var1) {
      }

      default void onCursorChange(Browser var1, Mouse.Cursor var2) {
      }

      default void onCursorChange(Browser var1, int var2) {
         this.onCursorChange(var1, Mouse.Cursor.fromValue(var2));
      }

      default Browser onCreateChild(Browser var1, String var2, String var3, boolean var4, int var5, int var6, int var7, int var8) {
         return var1;
      }
   }
}
