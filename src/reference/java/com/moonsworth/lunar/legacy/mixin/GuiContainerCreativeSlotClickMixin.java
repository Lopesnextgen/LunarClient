package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ContainerClickType;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.SlotRenderEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Annotation2(max = 5)
@Mixin(GuiContainerCreative.class)
public class GuiContainerCreativeSlotClickMixin {
   @Annotation2(max = 1)
   @WrapMethod(method = "handleMouseClick$v1_7")
   private void lunar$handleMouseClick$v1_7(Slot var1, int var2, int var3, int var4, Operation<Void> var5) {
      SlotRenderEvent var6 = this.lunar$onSlotClick(var1, var2, var3, ContainerClickType.fromId(var4));
      if (var6 != null) {
         var2 = var6.getSlotId();
         if (var6.isCancelled() || var2 < 0) {
            return;
         }

         var1 = ((GuiContainer)this).inventorySlots.getSlot(var2);
         var3 = var6.method6();
         var4 = var6.method7().toId();
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(min = 5)
   @WrapMethod(method = "handleMouseClick$v1_12")
   private void lunar$handleMouseClick$v1_12(Slot var1, int var2, int var3, ClickType var4, Operation<Void> var5) {
      SlotRenderEvent var6 = this.lunar$onSlotClick(var1, var2, var3, ContainerClickType.fromVanilla(var4));
      if (var6 != null) {
         var2 = var6.getSlotId();
         if (var6.isCancelled() || var2 < 0) {
            return;
         }

         var1 = ((GuiContainer)this).inventorySlots.getSlot(var2);
         var3 = var6.method6();
         var4 = var6.method7().toVanilla(ClickType.values());
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Unique
   @Nullable
   private SlotRenderEvent lunar$onSlotClick(Slot var1, int var2, int var3, ContainerClickType var4) {
      SlotRenderEvent var5 = ClientEventBus.method29()
         .method12(SlotRenderEvent.class, () -> new SlotRenderEvent((Bridge5Extension_3)this, (Bridge3_18)var1, var2, var3, var4));
      if (var5 == null) {
         return null;
      } else if (var5.isCancelled()) {
         return var5;
      } else {
         return !var5.isModified() ? null : var5;
      }
   }
}
