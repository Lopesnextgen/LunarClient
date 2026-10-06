package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.PersistentTextureImpl;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class TextureHandlerLegacy implements com.moonsworth.webosr.TextureHandler {
   private static final TextureHandlerLegacy field1 = new TextureHandlerLegacy();
   private final Map<Integer, Bridge8Extension3> field2 = new HashMap<>();
   private int field3 = 1;

   public TextureHandlerLegacy() {
   }

   public int createTexture(int number1, int number2, int number3) {
      ResourceLocationBridge horsestats144 = ResourceLocationBridge.create("webosr", "texture-" + this.field3);
      Bridge8Extension34 bridge8extension345 = Ref.method3().bridge$getTextureManager().method3(horsestats144, new PersistentTextureImpl());
      int number6 = this.field3++;
      this.field2.put(number6, bridge8extension345);
      bridge8extension345.bridge$allocate(false, false, number1, number2, TextureFormat.RGBA8, false, false);
      return number6;
   }

   public void resizeTexture(int index1, int number2, int number3, int number4) {
      Bridge8Extension3 bridge8extension35 = this.field2.get(index1);
      bridge8extension35.bridge$allocate(false, false, number2, number3, TextureFormat.RGBA8, false, false);
   }

   public void uploadTexture(int index1, ByteBuffer buffer2, int number3, int number4, int number5, int number6, int value) {
      if (number5 != 0 && number6 != 0) {
         Bridge8Extension3 bridge8extension38 = this.field2.get(index1);
         bridge8extension38.bridge$uploadBgra(number3, number4, number5, number6, buffer2, number3, number4, value);
      }
   }

   public void deleteTexture(int index1) {
      Bridge8Extension3 bridge8extension32 = this.field2.remove(index1);
      bridge8extension32.method1();
   }

   public void readTexture(int number1, ByteBuffer buffer2, int number3, int number4, int number5) {
      throw new UnsupportedOperationException();
   }

   public Bridge8Extension3 method1(int index1) {
      return this.field2.get(index1);
   }

   public static TextureHandlerLegacy method2() {
      return field1;
   }
}
