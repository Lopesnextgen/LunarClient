package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge_52;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

public class ExternalTextureFramebufferAdapter extends Framebuffer {
   private final boolean field1;
   private boolean field2 = false;
   private boolean field3;
   private boolean initialized = false;

   public ExternalTextureFramebufferAdapter(Bridge_52 var1) {
      super(var1.width, var1.height, var1.useDepth);
      this.field3 = var1.field2;
      this.field1 = var1.field3;
      this.framebufferTexture = var1.field1 == null ? GL11.glGenTextures() : ((Bridge8Extension2)var1.field1).lunar$getHandle();
      this.initialized = true;
      this.createBindFramebuffer(var1.width, var1.height);
   }

   public void createBindFramebuffer(int var1, int value) {
      if (this.initialized) {
         super.createBindFramebuffer(var1, value);
      }
   }

   public boolean method1() {
      return this.field3 ? false : this.field2 || this.field1;
   }

   public void method2() {
      this.field2 = true;
   }
}
