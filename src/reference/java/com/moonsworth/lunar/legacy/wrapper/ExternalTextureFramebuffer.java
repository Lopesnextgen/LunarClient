package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.Bridge_52;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

public class ExternalTextureFramebuffer extends Framebuffer {
   private final boolean field1;
   private boolean field2 = false;
   private boolean field3;
   private boolean initialized = false;

   public ExternalTextureFramebuffer(Bridge_52 bridge_521) {
      super(bridge_521.width, bridge_521.height, bridge_521.useDepth);
      this.field3 = bridge_521.field2;
      this.field1 = bridge_521.field3;
      this.framebufferTexture = bridge_521.field1 == null ? GL11.glGenTextures() : ((Bridge8Extension2)bridge_521.field1).lunar$getHandle();
      this.initialized = true;
      this.createBindFramebuffer(bridge_521.width, bridge_521.height);
   }

   public void createBindFramebuffer(int value, int value2) {
      if (this.initialized) {
         super.createBindFramebuffer(value, value2);
      }
   }

   public boolean method1() {
      return this.field3 ? false : this.field2 || this.field1;
   }

   public void method2() {
      this.field2 = true;
   }
}
