package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.KeyBindingClashEntry;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.Bridge2Handler;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding_v1_7;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(KeyBinding_v1_7.class)
public abstract class KeyBinding_v1_7Mixin implements MixinHelper_15 {
   @Shadow
   public int keyCode;
   @Shadow
   public boolean pressed;
   @Final
   @Shadow
   public String keyDescription;
   @Final
   @Shadow
   public String keyCategory;
   @Unique
   private Set<KeyBindingClashEntry> lunar$clashesWith = new HashSet<>();
   @Unique
   private String lunar$siblingName = "";

   @Shadow
   public abstract boolean getIsKeyPressed();

   @Override
   public KeyCode bridge$getKey() {
      return Bridge2Handler.method7(this.keyCode);
   }

   @Override
   public void bridge$setKey(KeyCode var1) {
      this.keyCode = Bridge2Handler.method6(var1);
   }

   @Override
   public boolean bridge$isKeyDown() {
      return this.getIsKeyPressed();
   }

   @Override
   public String bridge$getKeyName() {
      if (this.keyCode < 0) {
         return I18n.format("key.mouseButton", new Object[]{this.keyCode + 101});
      }

      try {
         return Keyboard.getKeyName(this.keyCode);
      } catch (ArrayIndexOutOfBoundsException var2) {
         return "UNK";
      }
   }

   @Override
   public String bridge$getKeyDescription() {
      return I18n.format(this.keyDescription, new Object[0]);
   }

   @Override
   public String bridge$getUntranslatedKeyDescription() {
      return this.keyDescription;
   }

   @Override
   public void bridge$setKeyBindState(boolean var1) {
      KeyBinding_v1_7.setKeyBindState(this.keyCode, var1);
   }

   @Override
   public void bridge$setKeyBindPressed(boolean var1) {
      this.pressed = var1;
   }

   @Override
   public Set<KeyBindingClashEntry> bridge$getClashesWith() {
      return this.lunar$clashesWith.size() <= 1 ? Collections.emptySet() : this.lunar$clashesWith;
   }

   @Override
   public void bridge$setSiblingName(String var1) {
      this.lunar$siblingName = var1;
   }

   @Override
   public void bridge$setClashesWith(Set<KeyBindingClashEntry> var1) {
      this.lunar$clashesWith = new HashSet<>(var1);
      this.lunar$clashesWith.removeIf(var1x -> var1x.id().equals(this.keyDescription) || var1x.id().equals(this.lunar$siblingName));
   }

   @Override
   public String bridge$getCategory() {
      return this.keyCategory;
   }

   @Inject(method = "setKeyCode", at = @At("TAIL"))
   private void impl$onSetKeyCode(int var1, CallbackInfo var2) {
      SimpleKeybindOption var3 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get(this);
      if (var3 != null) {
         var3.method10(KeyCode.valueOf(Bridge2Handler.method7(var1).name().toUpperCase()));
      }

      ThreadModuleDump63.method28(this);
   }
}
