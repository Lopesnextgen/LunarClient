package com.moonsworth.lunar.legacy.wrapper;

import net.minecraft.client.shader.Framebuffer;

public class NoopFramebufferAdapter extends Framebuffer {
   public NoopFramebufferAdapter() {
      super(0, 0, true);
   }

   public void createFramebuffer(int var1, int var2) {
   }

   public void deleteFramebuffer() {
   }

   public void createBindFramebuffer(int var1, int var2) {
   }
}
