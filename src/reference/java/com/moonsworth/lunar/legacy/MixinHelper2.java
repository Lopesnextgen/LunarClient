package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartVisibility;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class MixinHelper2 {
   public static void method1(ItemStack var0, Type var1, CallbackInfo var2) {
      Optional var3 = PlayerModelPartMap.method3((ItemStackRenderStateBridge)var0);
      if (var3.isPresent()) {
         MixinHelper3.method1(var1 == Type.FIRST_PERSON_LEFT_HAND);
         method2((CosmeticMetadata)var3.get(), var0, var1);
         var2.cancel();
      }
   }

   private static void method2(CosmeticMetadata var0, ItemStack var1, Type var2) {
      EmoteModel var3 = (EmoteModel)var0.method4();
      Gui2Handler var4 = var3.method6().orElseThrow();
      boolean var5 = var2 == Type.FIRST_PERSON_RIGHT_HAND;
      Bridge5Extension_5 var6 = ThreadModuleDump63.method7();
      BridgeExtension3_5 var7 = BridgeExtension3_5.method32();
      var7.push();
      MixinHelper_6 var8 = (MixinHelper_6)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$getSkinMap().get(var6.bridge$getSkinType());
      if (var8 != null) {
         BridgeExtension2_7 var9 = var8.bridge$getMainModel();
         boolean var10 = ThreadModuleDump63.method4().method45().method9(var6);
         var7.translate(var5 ? 0.58F : -0.58, -0.53F, -0.77F);
         Consumer var11 = PlayerModelPartMap.method36(var4, !var5, var7, var2);
         RenderContext var12 = RenderContext.method12(var6, var9);
         var12.method20((ItemStackBridge)var1);
         PlayerModelPartVisibility.method2(
            var7, var12, var0, var6, var10, var9, !var5, false, (ItemStackBridge)var1, ThreadModuleDump91.Type.Data.method2(var6, var0, var2), var11
         );
      }

      var7.pop();
   }
}
