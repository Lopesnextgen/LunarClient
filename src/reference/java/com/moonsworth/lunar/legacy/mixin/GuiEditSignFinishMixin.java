package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventSignUpdate;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Arrays;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiEditSign.class)
public class GuiEditSignFinishMixin {
   public GuiEditSignFinishMixin() {
   }

   @VersionGate(max = 0)
   @ModifyArg(
      method = "onGuiClosed",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/client/CPacketUpdateSign;<init>(III[Ljava/lang/String;)V"),
      index = 3
   )
   private String[] lunar$onSignFinished$v1_7(String[] items1) {
      EventSignUpdate highlightimpl172 = (EventSignUpdate)LunarEventBus.method29().method12(EventSignUpdate.class, () -> new EventSignUpdate(items1));
      return highlightimpl172 != null ? highlightimpl172.method1() : items1;
   }

   @VersionGate(min = 1)
   @ModifyArg(
      method = "onGuiClosed",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/play/client/CPacketUpdateSign;<init>(Lnet/minecraft/util/math/BlockPos;[Lnet/minecraft/util/text/ITextComponent;)V"
      ),
      index = 1
   )
   private IChatComponent[] lunar$onSignFinished$v1_8(IChatComponent[] items1) {
      EventSignUpdate highlightimpl172 = (EventSignUpdate)LunarEventBus.method29()
         .method12(EventSignUpdate.class, () -> new EventSignUpdate(Arrays.stream(items1).map(IChatComponent::getUnformattedText).toArray(String[]::new)));
      return highlightimpl172 != null
         ? Arrays.stream(highlightimpl172.method1()).map(arg0 -> (IChatComponent)(new ChatComponentText(arg0 != null ? arg0 : ""))).toArray(IChatComponent[]::new)
         : items1;
   }
}
