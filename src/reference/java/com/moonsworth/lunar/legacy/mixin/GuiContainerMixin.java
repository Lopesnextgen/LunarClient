package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge2_45;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ContainerClickType;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IWorldNameable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
public abstract class GuiContainerMixin extends GuiScreen implements Bridge5Extension_3 {
   @Shadow
   public Container inventorySlots;
   @Shadow
   public int guiTop;
   @Shadow
   public int guiLeft;
   @Shadow
   public Slot hoveredSlot;
   @Unique
   private GuiTextField multiver$textField;

   @Shadow
   public abstract boolean checkHotbarKeys(int var1);

   @Shadow
   public abstract Slot getSlotAtPosition(int var1, int var2);

   @Shadow
   public abstract void handleMouseClick(Slot var1, int var2, int var3, ClickType var4);

   @Shadow
   public abstract void handleMouseClick(Slot var1, int var2, int var3, int var4);

   @Inject(method = "mouseClicked", at = @At("TAIL"))
   private void lunar$onMouseClicked(int var1, int var2, int var3, CallbackInfo var4) {
      this.checkHotbarKeys(var3 - 100);
   }

   @Override
   public List<Bridge3_18> bridge$inventorySlots() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.inventorySlots.inventorySlots : this.inventorySlots.inventorySlots$v1_7;
   }

   @Override
   public Bridge2_42 bridge$title() {
      if (this.inventorySlots instanceof ContainerChest var1) {
         IInventory var3 = var1.lowerChestInventory;
         return ThreadModuleDump63.MC_VERSION >= 1
            ? (Bridge2_42)((IWorldNameable)var3).getDisplayName()
            : (Bridge2_42)(new ChatComponentText(var3.getInventoryName$v1_7()));
      } else {
         return (Bridge2_42)(new ChatComponentText(""));
      }
   }

   @Override
   public Bridge3_18 bridge$getSlotAtPosition(int var1, int var2) {
      return (Bridge3_18)this.getSlotAtPosition(var1, var2);
   }

   @Override
   public Bridge3_18 bridge$getHoveredSlot() {
      return (Bridge3_18)this.hoveredSlot;
   }

   @Override
   public int bridge$getGuiTop() {
      return this.guiTop;
   }

   @Override
   public int bridge$getGuiLeft() {
      return this.guiLeft;
   }

   @Override
   public void bridge$clickSlot(Bridge3_18 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.handleMouseClick((Slot)var1, var1.bridge$getIndex(), 0, ClickType.PICKUP);
      } else {
         this.handleMouseClick((Slot)var1, var1.bridge$getIndex(), 0, 0);
      }
   }

   @Override
   public void bridge$clickSlot(int var1, int var2, ContainerClickType var3) {
      Slot var4 = null;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         if (var1 >= 0 && var1 <= this.inventorySlots.inventorySlots$v1_7.size()) {
            var4 = this.inventorySlots.getSlot(var1);
         }
      } else if (var1 >= 0 && var1 <= this.inventorySlots.inventorySlots.size()) {
         var4 = this.inventorySlots.getSlot(var1);
      }

      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.handleMouseClick(var4, var1, var2, var3.toVanilla(ClickType.values()));
      } else {
         this.handleMouseClick(var4, var1, var2, var3.toId());
      }
   }

   @Override
   public ItemStackBridge bridge$getCursor() {
      if (ThreadModuleDump63.method3().bridge$getPlayer().bridge$getInventory() instanceof InventoryPlayer var1) {
         ItemStackBridge var3 = (ItemStackBridge)var1.getItemStack();
         if (var3 != null) {
            return var3;
         }
      }

      return Bridge.method8().method41();
   }

   @Override
   public void bridge$setHoveredSlot(Bridge3_18 var1) {
      this.hoveredSlot = (Slot)var1;
   }

   @Annotation2(min = 1)
   @Override
   public void lunar$handleKeyEventOnState(Bridge2_45 var1, Bridge_7 var2) {
      if (this.multiver$textField == null) {
         this.multiver$textField = new GuiTextField(0, this.fontRenderer, 0, 0, 0, 0);
      }

      ((GuiTextFieldMixin)this.multiver$textField).bridge$setHeight(10);
      ((GuiTextFieldMixin)this.multiver$textField).bridge$setWidth(var1.width * 10 / var1.height);
      this.multiver$textField.text = var1.text;
      this.multiver$textField.maxStringLength = var1.field2;
      this.multiver$textField.lineScrollOffset = var1.field3;
      this.multiver$textField.cursorPosition = var1.field4;
      this.multiver$textField.selectionEnd = var1.field5;
      this.multiver$textField.setFocused(true);
      if (var2 instanceof BridgeHandler var3 && var3.method2()) {
         this.multiver$textField.textboxKeyTyped(var3.method4(), var3.code());
      }

      var1.text = this.multiver$textField.text;
      var1.field3 = this.multiver$textField.lineScrollOffset;
      var1.field4 = this.multiver$textField.cursorPosition;
      var1.field5 = this.multiver$textField.selectionEnd;
   }

   @Annotation2(min = 1)
   @Override
   public void lunar$handleMousePressOnState(Bridge2_45 var1, int var2, int var3, int var4) {
      if (this.multiver$textField == null) {
         this.multiver$textField = new GuiTextField(0, this.fontRenderer, 0, 0, 0, 0);
      }

      ((GuiTextFieldMixin)this.multiver$textField).bridge$setHeight(10);
      ((GuiTextFieldMixin)this.multiver$textField).bridge$setWidth(var1.width * 10 / var1.height);
      this.multiver$textField.text = var1.text;
      this.multiver$textField.maxStringLength = var1.field2;
      this.multiver$textField.lineScrollOffset = var1.field3;
      this.multiver$textField.cursorPosition = var1.field4;
      this.multiver$textField.selectionEnd = var1.field5;
      this.multiver$textField.setFocused(true);
      if (ThreadModuleDump63.MC_VERSION == 1) {
         this.multiver$textField.mouseClicked(var3 * 10 / var1.height, var4, var2);
      } else {
         this.multiver$textField.mouseClicked(var3 * 10 / var1.height, var4, var2);
      }

      var1.text = this.multiver$textField.text;
      var1.field3 = this.multiver$textField.lineScrollOffset;
      var1.field4 = this.multiver$textField.cursorPosition;
      var1.field5 = this.multiver$textField.selectionEnd;
   }

   @Override
   public int bridge$getLowerChestSizeInventory() {
      return this.inventorySlots instanceof ContainerChest var1 ? var1.getLowerChestInventory().getSizeInventory() : 0;
   }
}
