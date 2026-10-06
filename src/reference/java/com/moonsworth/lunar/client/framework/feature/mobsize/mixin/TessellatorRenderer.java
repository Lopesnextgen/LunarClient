package com.moonsworth.lunar.client.framework.feature.mobsize.mixin;

import net.minecraft.client.renderer.Tessellator;

public class TessellatorRenderer extends Tessellator {
   private WorldRenderer wrappedRenderer;

   public TessellatorRenderer() {
      super(0);
      this.worldRenderer = this.wrappedRenderer = new WorldRenderer(Tessellator.theMinecraft.worldRenderer);
   }

   public void draw() {
      this.wrappedRenderer.flush();
      Tessellator.theMinecraft.draw();
   }
}
