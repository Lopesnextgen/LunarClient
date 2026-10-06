package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.command.CommandCompleter;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.world.pathfinder.NodeProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(5)
@Mixin(NodeProcessor.class)
public class NodeProcessorMixin {
   @Shadow
   public List<String> completions$v1_12;
   @Unique
   private String lunar$lastRequestedInput;

   public NodeProcessorMixin() {
   }

   @Inject(method = "requestCompletions$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$requestCompletions(String text, CallbackInfo callback2) {
      this.lunar$lastRequestedInput = text;
      List list3 = CommandCompleter.method1(text);
      if (!list3.isEmpty()) {
         this.completions$v1_12.addAll(list3);
         callback2.cancel();
      }
   }

   @ModifyVariable(method = "setCompletions$v1_12", at = @At("HEAD"), argsOnly = true)
   private String[] lunar$mergeCustomSuggestions(String[] items1) {
      return CommandCompleter.method2(items1, this.lunar$lastRequestedInput);
   }
}
