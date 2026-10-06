package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.optifine.CustomItemsBridge;
import java.util.function.IntConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class EventRenderItemGlint extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EventRenderItemGlint.GlintTarget field1;
   @Nullable
   private final IntConsumer field2;
   private final BakedModelBridge field3;
   private final BridgeExtension field4;
   private final ItemStackRenderStateBridge field5;
   private final AbstractRenderContext field6;

   public static boolean method1() {
      return Bridge.method5()
         .map(
            arg0 -> (!arg0.getConfig().hasShaders() || !arg0.getShaders().hasShadowPass())
               && arg0.getCustomItems().<Boolean>map(CustomItemsBridge::useGlint).orElse(true)
         )
         .orElse(true);
   }

   @Generated
   public EventRenderItemGlint.GlintTarget method2() {
      return this.field1;
   }

   @Nullable
   @Generated
   public IntConsumer method3() {
      return this.field2;
   }

   @Generated
   public BakedModelBridge method4() {
      return this.field3;
   }

   @Generated
   public BridgeExtension method5() {
      return this.field4;
   }

   @Generated
   public ItemStackRenderStateBridge method6() {
      return this.field5;
   }

   @Generated
   public AbstractRenderContext method7() {
      return this.field6;
   }

   @Generated
   public EventRenderItemGlint(
      EventRenderItemGlint.GlintTarget glintTarget, @Nullable IntConsumer intconsumer2, BakedModelBridge mixinhelper4_53, BridgeExtension bridge, ItemStackRenderStateBridge mixinhelper_145, AbstractRenderContext bridgeextension_96
   ) {
      this.field1 = glintTarget;
      this.field2 = intconsumer2;
      this.field3 = mixinhelper4_53;
      this.field4 = bridge;
      this.field5 = mixinhelper_145;
      this.field6 = bridgeextension_96;
   }

   public enum GlintTarget {
      EQUIPPED_ARMOR,
      ITEM,
      GUI;

      GlintTarget() {
      }
   }
}
