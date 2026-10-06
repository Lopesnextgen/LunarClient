package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.client.command.CommandCompleter;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventTabComplete;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiSleepMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChat.class)
public abstract class GuiChatMixin implements Bridge5Extension612 {
   @VersionGate(max = 1)
   @Shadow
   public boolean waitingOnAutocomplete;
   @Shadow
   public String defaultInputFieldText;
   @VersionGate(max = 1)
   @Unique
   private String lunar$lastRequestedInput;

   public GuiChatMixin() {
   }

   @VersionGate(max = 1)
   @Shadow
   public abstract void onAutocompleteResponse(String[] items1);

   @VersionGate(max = 1)
   @Inject(method = "sendAutocompleteRequest$v1_7", at = @At("HEAD"), cancellable = true)
   private void lunar$sendAutocompleteRequest(String text1, String text2, CallbackInfo callback3) {
      this.lunar$lastRequestedInput = text1;
      List list4 = CommandCompleter.method1(text1);
      if (!list4.isEmpty()) {
         this.waitingOnAutocomplete = true;
         this.onAutocompleteResponse(list4.toArray(new String[0]));
         this.waitingOnAutocomplete = false;
         callback3.cancel();
      } else {
         EventTabComplete highlightimpl65 = (EventTabComplete)LunarEventBus.method29().method12(EventTabComplete.class, () -> new EventTabComplete(text1, text2));
         if (highlightimpl65 != null && highlightimpl65.method3() != null) {
            this.waitingOnAutocomplete = true;
            this.onAutocompleteResponse(highlightimpl65.method3());
            this.waitingOnAutocomplete = false;
            callback3.cancel();
         }
      }
   }

   @VersionGate(max = 1)
   @ModifyVariable(method = "onAutocompleteResponse$v1_7", at = @At("HEAD"), argsOnly = true)
   private String[] lunar$mergeCustomSuggestions(String[] items1) {
      return CommandCompleter.method2(items1, this.lunar$lastRequestedInput);
   }

   public String bridge$getInitialText() {
      return this.defaultInputFieldText;
   }

   public boolean bridge$isSuggestionOverlayVisible() {
      return false;
   }

   public boolean bridge$isBedChat() {
      return this instanceof GuiSleepMP;
   }
}
