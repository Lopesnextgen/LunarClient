package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import com.moonsworth.lunar.client.util.colorsaturation.ColorsaturationTask;

public interface BOBJMesh {
   int method1();

   @VersionGate(max = 7)
   void method2(AbstractRenderContext bridgeextension_91, ResourceLocationBridge horsestats142);

   @VersionGate(min = 6)
   void method3(RenderTypeBridge bridge201, BridgeExtension2_11 bridgeextension2_112, RenderSystemBridge bridge123, int number4);

   void delete();

   AxisAlignedBBBridge method4();

   static BOBJMesh method5(CompiledData compileddata0) {
      return Bridge.getMinecraftVersion().method23() ? new ColorsaturationTask(compileddata0) : new LegacyBOBJMesh(compileddata0);
   }
}
