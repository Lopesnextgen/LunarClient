package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import java.util.function.IntConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ItemGlintRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final ItemGlintRenderEvent.Type field1;
   @Nullable
   private final IntConsumer field2;
   private final BakedModelExtension field3;
   private final BridgeExtension field4;
   private final ItemStackRenderStateBridge field5;
   private final AbstractRenderContext field6;

   public static boolean method1() {
      return Bridge.method5()
         .map(
            var0 -> (!var0.getConfig().hasShaders() || !var0.getShaders().hasShadowPass())
               && var0.getCustomItems().<Boolean>map(Slayer6::useGlint).orElse(true)
         )
         .orElse(true);
   }

   @Generated
   public ItemGlintRenderEvent.Type method2() {
      return this.field1;
   }

   @Nullable
   @Generated
   public IntConsumer method3() {
      return this.field2;
   }

   @Generated
   public BakedModelExtension method4() {
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
   public ItemGlintRenderEvent(
      ItemGlintRenderEvent.Type var1, @Nullable IntConsumer var2, BakedModelExtension var3, BridgeExtension var4, ItemStackRenderStateBridge var5, AbstractRenderContext var6
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
   }

   public enum Type {
      EQUIPPED_ARMOR,
      ITEM,
      GUI;
   }
}
