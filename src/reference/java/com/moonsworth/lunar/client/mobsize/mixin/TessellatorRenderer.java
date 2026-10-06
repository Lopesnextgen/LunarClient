package com.moonsworth.lunar.client.mobsize.mixin;

import net.minecraft.client.renderer.Tessellator;

public class TessellatorRenderer extends Tessellator {
   private WorldRenderer field1;

   public TessellatorRenderer() {
      super(0);
      this.worldRenderer = this.field1 = new WorldRenderer(Tessellator.theMinecraft.worldRenderer);
   }

   public void draw() {
      this.field1.flush();
      Tessellator.theMinecraft.draw();
   }
}
