package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.TextureUtilBridge;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureUtil;

public class TextureUtilBridgeImpl implements TextureUtilBridge {
   public TextureUtilBridgeImpl() {
   }

   @Override
   public int method1() {
      return TextureUtil.glGenTextures();
   }

   @Override
   public void method2(int number1, BufferedImage bufferedimage2) {
      TextureUtil.uploadTextureImage(number1, bufferedimage2);
   }

   @Override
   public void method3(int number1) {
      TextureUtil.deleteTexture(number1);
   }
}
