package com.moonsworth.lunar.client.util.colorsaturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;

public interface Colorsaturation {
   int method1();

   @Annotation2(max = 7)
   void method2(AbstractRenderContext var1, ResourceLocationBridge var2);

   @Annotation2(min = 6)
   void method3(RenderLayerBridge var1, BridgeExtension2_11 var2, RenderSystemBridge var3, int var4);

   void delete();

   AxisAlignedBBBridge method4();

   static Colorsaturation method5(CompiledData data) {
      return Bridge.getMinecraftVersion().method23() ? new ColorsaturationTask(data) : new ColorsaturationHandler(data);
   }
}
