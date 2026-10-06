package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.render.turbo.FragDataFactory;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.FragData;
import com.moonsworth.lunar.client.render.turbo.PathSearchContext;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Annotation2(min = 8)
public interface SpriteSource {
   default void method1(FragData var1) {
   }

   default void method2(Vector3iBridge var1, Bridge2_43 var2, BridgeExtension var3, Map<RenderLayerBridge, Bridge4Extension> var4) {
      Bridge5_16 var5 = Bridge.method8().method61();
      var5.bridge$pushPose();
      var5.bridge$translate(var3.bridge$getPosX() - var1.bridge$getX(), var3.bridge$getPosY() - var1.bridge$getY(), var3.bridge$getPosZ() - var1.bridge$getZ());
      Object var6;
      if (ThreadModuleDump63.MC_VERSION >= 33) {
         var6 = Bridge.method8().method76(var2x -> this.method7(var4, var2x));
      } else {
         var6 = Bridge.method8().method75(var2x -> this.method7(var4, var2x));
      }

      var2.bridge$render(
         var3,
         0.0,
         0.0,
         0.0,
         (float)var3.bridge$getRotationYaw(),
         1.0F,
         var5,
         (BatchingBufferSourceBridge)var6,
         var2.bridge$getRenderer(var3).bridge$getPackedLightCoords(var3, 1.0F)
      );
      if (ThreadModuleDump63.MC_VERSION >= 39) {
         try {
            ((AutoCloseable)var6).close();
         } catch (Exception var8) {
         }
      }

      var5.bridge$popPose();
   }

   void method3(BatchEntityType var1, RenderLayerBridge var2, Runnable var3, boolean var4);

   void method4(BatchEntityType var1);

   default void method5() {
   }

   default void method6() {
   }

   default Bridge4_6 method7(Map<RenderLayerBridge, Bridge4Extension> var1, @NotNull RenderLayerBridge var2) {
      return (Bridge4_6)var1.computeIfAbsent(PathSearchContext.method1(var2), var0 -> Bridge.method8().method73(var0));
   }

   default <T> FragData method8(@Nullable Object var1, FragDataFactory<T> var2, List<T> var3) {
      return var2.createFragData(var3, null);
   }

   default void method9() {
   }
}
