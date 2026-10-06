package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.texture.TextureProcessor.TextureProvider;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import lombok.Generated;

public class BufferedImageTexture implements TextureProvider {
   private final String field1;
   private final String field2;
   private final int field3;
   private final int field4;
   private final BufferedImage field5;

   public String name() {
      return this.field1;
   }

   public String method1() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }

   public int method3() {
      return this.field4;
   }

   public int method4(int number1, int number2) {
      return this.field5.getRGB(number1, number2);
   }

   public void method5(int number1, int number2, int number3) {
      this.field5.setRGB(number1, number2, number3);
   }

   public static void method6(BufferedImage bufferedimage0, String text1, String text2) {
      Client client3 = Ref.method4();
      if (bufferedimage0 != null && text1 != null && client3 != null && client3.method40() != null && client3.method40().method84() != null) {
         client3.method40().method84().method71().method5(text1, () -> new BufferedImageTexture(text1, text2, bufferedimage0.getWidth(), bufferedimage0.getHeight(), bufferedimage0));
      }
   }

   @Generated
   private BufferedImageTexture(String text1, String text2, int number3, int number4, BufferedImage bufferedimage5) {
      this.field1 = text1;
      this.field2 = text2;
      this.field3 = number3;
      this.field4 = number4;
      this.field5 = bufferedimage5;
   }
}
