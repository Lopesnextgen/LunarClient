package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.DirectImageBridge;
import com.moonsworth.lunar.bridge.DecodedTextureDataBridge;
import com.moonsworth.lunar.bridge.Bridge7_3;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.PixelFormat;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class TextureDataBridgeImpl implements Bridge7_3 {
   public TextureDataBridgeImpl() {
   }

   public void method1(DecodedTextureDataBridge bridge6_91, Bridge8Extension3 bridge8extension32) {
      if (bridge6_91.method6() != PixelFormat.ARGB) {
         throw new IllegalArgumentException("Wrong texture format " + bridge6_91.method6() + ", expected ARGB.");
      }

      TextureUtil.allocateTexture(((Bridge8Extension2)bridge8extension32).lunar$getHandle(), bridge6_91.getWidth(), bridge6_91.getHeight());
      TextureUtil.uploadTextureSub(0, bridge6_91.getData(), bridge6_91.getWidth(), bridge6_91.getHeight(), 0, 0, bridge6_91.method2(), bridge6_91.isClamp(), false);
   }

   public AutoCloseableExtension method2(int number1, int number2, int number3) {
      TextureUtil.bindTexture(number1);
      ByteBuffer buffer4 = DirectImageBridge.method3(number2, number3);
      if (Ref.MC_VERSION >= 5) {
         GlStateManager.glGetTexImage$v1_12(3553, 0, 6408, 5121, buffer4.asIntBuffer());
      } else if (Ref.MC_VERSION >= 1) {
         GL11.glPixelStorei(3317, 1);
         GL11.glGetTexImage(3553, 0, 32993, 33639, buffer4.asIntBuffer());
      } else {
         GL11.glGetTexImage(3553, 0, 6408, 5121, buffer4.asIntBuffer());
      }

      return new DirectImageBridge(buffer4, number2, number3);
   }
}
