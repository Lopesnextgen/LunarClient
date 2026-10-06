package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.TextureManagerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GifTexture implements com.moonsworth.lunar.bridge.Bridge8Extension3 {
   public ResourceLocationBridge texture;
   public List<GifTexture.GifFrame> elements = new ArrayList<>();
   public int index;
   public int duration;
   public int width;
   public int height;

   public static void method1(ResourceLocationBridge horsestats140, int number1, float value2) {
      TextureManagerBridge bridge8handler23 = Ref.method3().bridge$getTextureManager();
      if (horsestats140.bridge$getPath().endsWith("gif") && bridge8handler23.bridge$getTexture(horsestats140) instanceof GifTexture bridge8extension35) {
         int number6 = bridge8extension35.index;
         if (number1 >= 0) {
            bridge8extension35.method3(number1, value2);
         }

         if (number1 >= 0) {
            bridge8extension35.index = number6;
         }
      }
   }

   public GifTexture(ResourceLocationBridge horsestats141) {
      this.texture = horsestats141;
   }

   public void method3() {
      this.duration = 0;

      for (GifTexture.GifFrame data2 : this.elements) {
         this.duration = this.duration + data2.delay;
      }
   }

   public void method3(int number1, float value2) {
      int number3 = (int)((number1 + value2) * 5.0F % this.duration);
      int number4 = 0;
      int index5 = 0;
      this.index = 0;

      for (GifTexture.GifFrame data7 : this.elements) {
         number4 += data7.delay;
         if (number3 < number4) {
            this.index = index5 == 0 ? 0 : index5 - 1;
            break;
         }

         index5++;
      }
   }

   public void bridge$setFilter(boolean flag, boolean flag2) {
   }

   public static class GifFrame {
      public int delay;

      public GifFrame(int number1, int value, int number3, ByteBuffer buffer4) {
         this.delay = number1;
      }
   }
}
