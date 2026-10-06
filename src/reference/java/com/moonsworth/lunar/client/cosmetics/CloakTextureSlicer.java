package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.TextureQuality;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class CloakTextureSlicer {
   public static final int field1 = 8;

   public CloakTextureSlicer() {
   }

   public static BufferedImage method1(BufferedImage bufferedimage0, TextureQuality bridgetype2_51, boolean flag, int number3) {
      if (flag) {
         return method5(bufferedimage0, bridgetype2_51 == TextureQuality.LOW, number3);
      } else {
         return bridgetype2_51 == TextureQuality.LOW ? method6(bufferedimage0, number3) : bufferedimage0;
      }
   }

   public static boolean method2(ResourceLocationBridge horsestats140) {
      Client client1 = Client.method109();
      if (client1 == null) {
         return false;
      }

      CosmeticManager holograms122 = client1.method53();
      return holograms122 == null ? false : holograms122.method70().contains(horsestats140);
   }

   public static BufferedImage method3(BufferedImage bufferedimage0, int number1) {
      return method5(bufferedimage0, false, number1);
   }

   public static BufferedImage method4(BufferedImage bufferedimage0, int number1) {
      return method5(bufferedimage0, true, number1);
   }

   private static BufferedImage method5(BufferedImage bufferedimage0, boolean flag, int number2) {
      float value3 = (float)bufferedimage0.getWidth() / bufferedimage0.getHeight();
      if (Math.abs(value3 - 2.0F) > 0.01F) {
         return flag ? method6(bufferedimage0, number2) : bufferedimage0;
      }

      int number4 = bufferedimage0.getHeight();
      if (flag) {
         number4 = Math.min(bufferedimage0.getHeight(), 32 * number2);
      }

      int number5 = number4 * 2;
      int number6 = method7(number4, 17, 32);
      int number7 = method7(number5, 22, 64);
      BufferedImage bufferedimage8 = new BufferedImage(number7, number6, 2);
      Graphics graphics9 = bufferedimage8.getGraphics();
      graphics9.drawImage(bufferedimage0, 0, 0, number5, number4, null);
      graphics9.dispose();
      return bufferedimage8;
   }

   private static BufferedImage method6(BufferedImage bufferedimage0, int number1) {
      if (bufferedimage0.getWidth() <= 17 * number1) {
         return bufferedimage0;
      }

      int number2 = 22 * number1;
      int number3 = method7(bufferedimage0.getHeight(), number2, bufferedimage0.getWidth());
      BufferedImage bufferedimage4 = new BufferedImage(number2, number3, 2);
      Graphics graphics5 = bufferedimage4.getGraphics();
      graphics5.drawImage(bufferedimage0, 0, 0, number2, number3, null);
      graphics5.dispose();
      return bufferedimage4;
   }

   private static int method7(int value, int number1, int number2) {
      long number3 = (long)value * number1;
      number3 += number2 - 1;
      return (int)(number3 / number2);
   }
}
