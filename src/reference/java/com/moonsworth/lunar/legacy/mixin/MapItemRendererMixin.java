package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_7;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.MapItemRenderer.Instance;
import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MapItemRenderer.class)
public abstract class MapItemRendererMixin implements Bridge6_7 {
   @Shadow
   public abstract void func_148250_a$v1_7(MapData var1, boolean var2);

   @Shadow
   public abstract void renderMap(MapData var1, boolean var2);

   @Shadow
   public abstract Instance getMapRendererInstance(MapData var1);

   @Override
   public void bridge$renderMap(AbstractRenderContext var1, int var2, Itemcounter2_3 var3) {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         this.func_148250_a$v1_7((MapData)var3, false);
      } else {
         this.renderMap((MapData)var3, false);
      }
   }

   @Override
   public ResourceLocationBridge bridge$getMapTexture(Itemcounter2_3 var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (ResourceLocationBridge)this.getMapRendererInstance((MapData)var1).location : null;
   }
}
