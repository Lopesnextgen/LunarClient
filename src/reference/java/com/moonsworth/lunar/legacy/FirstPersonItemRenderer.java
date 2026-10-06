package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.RenderPlayerBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartVisibility;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91.Type.Data;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FirstPersonItemRenderer {
   public FirstPersonItemRenderer() {
   }

   public static void method1(ItemStack stack0, Type type1, CallbackInfo callback2) {
      Optional optional3 = PlayerModelPartMap.method3((ItemStackRenderStateBridge)stack0);
      if (optional3.isPresent()) {
         FirstPersonHandTransform.method1(type1 == Type.FIRST_PERSON_LEFT_HAND);
         method2((CosmeticMetadata)optional3.get(), stack0, type1);
         callback2.cancel();
      }
   }

   private static void method2(CosmeticMetadata gui2handler30, ItemStack stack1, Type type2) {
      EmoteModel gui2iterator3 = (EmoteModel)gui2handler30.method4();
      Gui2Handler gui2handler4 = (Gui2Handler)gui2iterator3.method6().orElseThrow();
      boolean flag5 = type2 == Type.FIRST_PERSON_RIGHT_HAND;
      Bridge5Extension_5 bridge5extension_56 = Ref.method7();
      BridgeExtension3_5 bridgeextension3_57 = BridgeExtension3_5.method32();
      bridgeextension3_57.push();
      RenderPlayerBridge mixinhelper_68 = (RenderPlayerBridge)Bridge.method9().bridge$getEntityRenderDispatcher().bridge$getSkinMap().get(bridge5extension_56.bridge$getSkinType());
      if (mixinhelper_68 != null) {
         ModelPlayerBridge bridgeextension2_79 = mixinhelper_68.bridge$getMainModel();
         boolean flag10 = Ref.method4().method45().method9(bridge5extension_56);
         bridgeextension3_57.translate(flag5 ? 0.58F : -0.58, -0.53F, -0.77F);
         Consumer consumer11 = PlayerModelPartMap.method36(gui2handler4, !flag5, bridgeextension3_57, type2);
         RenderContext fov1012 = RenderContext.method12(bridge5extension_56, bridgeextension2_79);
         fov1012.method20((ItemStackBridge)stack1);
         PlayerModelPartVisibility.method2(bridgeextension3_57, fov1012, gui2handler30, bridge5extension_56, flag10, bridgeextension2_79, !flag5, false, (ItemStackBridge)stack1, Data.method2(bridge5extension_56, gui2handler30, type2), consumer11);
      }

      bridgeextension3_57.pop();
   }
}
