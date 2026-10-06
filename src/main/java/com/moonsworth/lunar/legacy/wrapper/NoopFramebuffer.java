package com.moonsworth.lunar.legacy.wrapper;

import net.minecraft.client.shader.Framebuffer;

public class NoopFramebuffer extends Framebuffer {
   public NoopFramebuffer() {
      super(0, 0, true);
   }

   public void createFramebuffer(int number1, int number2) {
   }

   public void deleteFramebuffer() {
   }

   public void createBindFramebuffer(int number1, int number2) {
   }
}
