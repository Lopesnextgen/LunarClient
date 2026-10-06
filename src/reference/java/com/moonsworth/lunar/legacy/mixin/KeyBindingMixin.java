package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.minecraft.KeyBindingEntry;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.KeyboardBridgeImpl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin implements KeyBindingBridge {
   @Final
   @Shadow
   public String keyDescription;
   @Shadow
   public int keyCode;
   @Shadow
   public boolean pressed;
   @Final
   @Shadow
   public String keyCategory;
   @Unique
   private Set<KeyBindingEntry> lunar$clashesWith = new HashSet<>();
   @Unique
   private String lunar$siblingName = "";

   public KeyBindingMixin() {
   }

   @Shadow
   public abstract boolean isKeyDown();

   public KeyCode bridge$getKey() {
      return KeyboardBridgeImpl.method7(this.keyCode);
   }

   public void bridge$setKey(KeyCode bridgetype_81) {
      this.keyCode = KeyboardBridgeImpl.method6(bridgetype_81);
   }

   public boolean bridge$isKeyDown() {
      return this.isKeyDown();
   }

   public String bridge$getKeyName() {
      if (this.keyCode < 0) {
         return I18n.format("key.mouseButton", new Object[]{this.keyCode + 101});
      }

      try {
         return Keyboard.getKeyName(this.keyCode);
      } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception2) {
         return "UNK";
      }
   }

   public String bridge$getKeyDescription() {
      return I18n.format(this.keyDescription, new Object[0]);
   }

   public String bridge$getUntranslatedKeyDescription() {
      return this.keyDescription;
   }

   public void bridge$setKeyBindState(boolean flag1) {
      KeyBinding.setKeyBindState(this.keyCode, flag1);
   }

   public void bridge$setKeyBindPressed(boolean flag1) {
      this.pressed = flag1;
   }

   public Set<KeyBindingEntry> bridge$getClashesWith() {
      return this.lunar$clashesWith.size() <= 1 ? Collections.emptySet() : this.lunar$clashesWith;
   }

   public void bridge$setSiblingName(String text) {
      this.lunar$siblingName = text;
   }

   public void bridge$setClashesWith(Set<KeyBindingEntry> set) {
      this.lunar$clashesWith = new HashSet<>(set);
      this.lunar$clashesWith.removeIf(arg1x -> arg1x.id().equals(this.keyDescription) || arg1x.id().equals(this.lunar$siblingName));
   }

   public String bridge$getCategory() {
      return this.keyCategory;
   }

   @Inject(method = "setKeyCode", at = @At("TAIL"))
   private void impl$onSetKeyCode(int value, CallbackInfo callback2) {
      SimpleKeybindOption lightingextension491323 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get(this);
      if (lightingextension491323 != null) {
         lightingextension491323.method10(KeyCode.valueOf(KeyboardBridgeImpl.method7(value).name().toUpperCase()));
      }

      Ref.method28(this);
   }
}
