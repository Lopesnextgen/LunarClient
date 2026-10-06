package com.moonsworth.lunar.client.fishing;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.TransparencyMode;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.mod.render.lighting.Lighting;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;
import org.jspecify.annotations.Nullable;

public interface Fishing2Extension extends Fishing2 {
   String field1 = "(off)";

   default void beforeOBJMeshRender() {
   }

   default void beginBeacon() {
   }

   default void endBeacon() {
   }

   default boolean method1(Runnable var1, Runnable var2) {
      Bridge5_12 var3 = Objects.requireNonNull(ThreadModuleDump63.method3());
      Bridge5Extension6 var4 = var3.bridge$getCurrentScreen();
      return this.method3(var1, var2, var4);
   }

   default boolean method2(Runnable var1, Runnable var2) {
      return this.method3(var1, var2, null);
   }

   default boolean method3(Runnable var1, Runnable var2, Bridge5Extension6 var3) {
      Lighting var4 = ThreadModuleDump63.method4().method40().method56();
      TranslationManager var5 = ThreadModuleDump63.method4().method67();
      BridgeImplementation var6 = Bridge.method8();
      Bridge5_12 var7 = Objects.requireNonNull(ThreadModuleDump63.method3());
      boolean var8 = ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(ServerRuleModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.DISABLE_SHADERS))
         .isPresent();
      if (var8) {
         var7.bridge$displayScreen(var6.method35(() -> var7.bridge$displayScreen(var3), "", var5.method2("gui.apollo", "disabledShadersServerRule")));
         var1.run();
         return true;
      } else if (var4.isEnabled()) {
         String var9 = "gui.lightingMod.mod_enabled_cannot_enable_shaders";
         var7.bridge$displayScreen(
            var6.method34(
               var5.method2(var9, "header"), var5.method2(var9, "warning"), var5.method2(var9, "confirmButton"), var5.method2(var9, "denyButton"), () -> {
                  ((ModEnabledState)var4.method7(Framework.field6)).setEnabled(false);
                  var7.bridge$displayScreen(var3);
                  var2.run();
               }, () -> var7.bridge$displayScreen(var3)
            )
         );
         var1.run();
         return true;
      } else {
         return false;
      }
   }

   boolean lunar$areShadersEnabledInConfig();

   boolean lunar$isShadowPass();

   String lunar$getShaderPack();

   boolean lunar$setShaderPack(String var1);

   void lunar$toggleShaders(boolean var1);

   boolean lunar$isUsingExtendedVertexFormat();

   default boolean lunar$isRenderingLevel() {
      return true;
   }

   Bridge_63 lunar$getExtendedEntityVertexFormat();

   @Nullable TransparencyMode lunar$getTransparencyType(RenderLayerBridge var1);

   RenderLayerBridge lunar$unwrapRenderType(RenderLayerBridge var1);

   default BatchingBufferSourceBridge lunar$unwrapMultiBufferSource(BatchingBufferSourceBridge var1) {
      return var1;
   }

   default @Nullable BatchingBufferSourceBridge lunar$getHandBufferSource() {
      return null;
   }
}
