package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert5Impl;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class TextureHandlerLegacy implements com.moonsworth.webosr.TextureHandler {
   private static final TextureHandlerLegacy field1 = new TextureHandlerLegacy();
   private final Map<Integer, Bridge8Extension3> field2 = new HashMap<>();
   private int field3 = 1;

   public int createTexture(int var1, int var2, int var3) {
      ResourceLocationBridge var4 = ResourceLocationBridge.create("webosr", "texture-" + this.field3);
      Bridge8Extension34 var5 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(var4, new Alert5Impl());
      int var6 = this.field3++;
      this.field2.put(var6, var5);
      var5.bridge$allocate(false, false, var1, var2, TexturePixelFormat.RGBA8, false, false);
      return var6;
   }

   public void resizeTexture(int var1, int var2, int var3, int var4) {
      Bridge8Extension3 var5 = this.field2.get(var1);
      var5.bridge$allocate(false, false, var2, var3, TexturePixelFormat.RGBA8, false, false);
   }

   public void uploadTexture(int var1, ByteBuffer var2, int var3, int var4, int var5, int var6, int var7) {
      if (var5 != 0 && var6 != 0) {
         Bridge8Extension3 var8 = this.field2.get(var1);
         var8.bridge$uploadBgra(var3, var4, var5, var6, var2, var3, var4, var7);
      }
   }

   public void deleteTexture(int var1) {
      Bridge8Extension3 var2 = this.field2.remove(var1);
      var2.method1();
   }

   public void readTexture(int var1, ByteBuffer var2, int var3, int var4, int var5) {
      throw new UnsupportedOperationException();
   }

   public Bridge8Extension3 method1(int var1) {
      return this.field2.get(var1);
   }

   public static TextureHandlerLegacy method2() {
      return field1;
   }
}
