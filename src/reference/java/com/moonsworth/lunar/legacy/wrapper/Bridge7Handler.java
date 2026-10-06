package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.DirectBufferImageBridge;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.Bridge7_3;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class Bridge7Handler implements Bridge7_3 {
   public void method1(Bridge6_9 var1, Bridge8Extension3 var2) {
      if (var1.method6() != ColorChannelOrder.ARGB) {
         throw new IllegalArgumentException("Wrong texture format " + var1.method6() + ", expected ARGB.");
      }

      TextureUtil.allocateTexture(((Bridge8Extension2)var2).lunar$getHandle(), var1.getWidth(), var1.getHeight());
      TextureUtil.uploadTextureSub(0, var1.getData(), var1.getWidth(), var1.getHeight(), 0, 0, var1.method2(), var1.isClamp(), false);
   }

   public AutoCloseableExtension method2(int var1, int var2, int var3) {
      TextureUtil.bindTexture(var1);
      ByteBuffer var4 = DirectBufferImageBridge.method3(var2, var3);
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         GlStateManager.glGetTexImage$v1_12(3553, 0, 6408, 5121, var4.asIntBuffer());
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         GL11.glPixelStorei(3317, 1);
         GL11.glGetTexImage(3553, 0, 32993, 33639, var4.asIntBuffer());
      } else {
         GL11.glGetTexImage(3553, 0, 6408, 5121, var4.asIntBuffer());
      }

      return new DirectBufferImageBridge(var4, var2, var3);
   }
}
