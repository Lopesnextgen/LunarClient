package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.Bridge2Handler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList.KeyEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyEntry.class)
public abstract class KeyEntryMixin {
   @Final
   @Shadow
   public GuiButton btnChangeKeyBinding;
   @Final
   @Shadow
   public KeyBinding keybinding;
   @Final
   @Shadow
   public KeyBinding_v1_7 field_148282_b$v1_7;

   @Annotation2(min = 5)
   @Inject(
      method = "drawEntry$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;drawButton$v1_12(Lnet/minecraft/client/Minecraft;IIF)V", ordinal = 1)
   )
   private void lunar$onDrawEntry$v1_12(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9, CallbackInfo var10) {
      this.lunar$onDrawEntry();
   }

   @Annotation2(1)
   @Inject(
      method = "drawEntry$v1_8",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;drawButton$v1_7(Lnet/minecraft/client/Minecraft;II)V", ordinal = 1)
   )
   private void lunar$onDrawEntry$v1_8(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, CallbackInfo var9) {
      this.lunar$onDrawEntry();
   }

   @Annotation2(max = 0)
   @Inject(
      method = "drawEntry$v1_7",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiButton;drawButton$v1_7(Lnet/minecraft/client/Minecraft;II)V", ordinal = 1)
   )
   private void lunar$onDrawEntry$v1_7(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9, CallbackInfo var10) {
      this.lunar$onDrawEntry();
   }

   @Unique
   private void lunar$onDrawEntry() {
      MixinHelper_15 var1 = ThreadModuleDump63.MC_VERSION >= 1 ? (MixinHelper_15)this.keybinding : (MixinHelper_15)this.field_148282_b$v1_7;
      int var2 = ThreadModuleDump63.MC_VERSION >= 1 ? this.keybinding.keyCode : this.field_148282_b$v1_7.keyCode;
      boolean var3 = this.btnChangeKeyBinding.displayString.startsWith("§c");
      if (var3) {
         boolean var4 = var2 == 0;
         boolean var5 = var1.bridge$getClashesWith().isEmpty();
         if (var4 || var5) {
            this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(var2);
         }
      }

      if (!var3 && !this.btnChangeKeyBinding.displayString.startsWith("§f>")) {
         KeyCode var6 = Bridge2Handler.method7(var2);
         if (var6 == KeyCode.KEY_NONE) {
            return;
         }

         if (!var1.bridge$getClashesWith().isEmpty()) {
            this.btnChangeKeyBinding.displayString = "§c" + this.btnChangeKeyBinding.displayString;
         }
      }
   }
}
