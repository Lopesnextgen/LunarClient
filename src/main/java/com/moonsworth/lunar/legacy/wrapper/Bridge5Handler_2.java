package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge5_7;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureUtil;

public class Bridge5Handler_2 implements Bridge5_7 {
   public int method1() {
      return TextureUtil.glGenTextures();
   }

   public void method2(int var1, BufferedImage bufferedImage) {
      TextureUtil.uploadTextureImage(var1, bufferedImage);
   }

   public void method3(int var1) {
      TextureUtil.deleteTexture(var1);
   }
}
