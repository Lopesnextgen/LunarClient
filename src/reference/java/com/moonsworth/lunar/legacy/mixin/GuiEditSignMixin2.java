package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.SignUpdateEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Arrays;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiEditSign.class)
public class GuiEditSignMixin2 {
   @Annotation2(max = 0)
   @ModifyArg(
      method = "onGuiClosed",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/client/CPacketUpdateSign;<init>(III[Ljava/lang/String;)V"),
      index = 3
   )
   private String[] lunar$onSignFinished$v1_7(String[] var1) {
      SignUpdateEvent var2 = (SignUpdateEvent)ClientEventBus.method29().method12(SignUpdateEvent.class, () -> new SignUpdateEvent(var1));
      return var2 != null ? var2.method1() : var1;
   }

   @Annotation2(min = 1)
   @ModifyArg(
      method = "onGuiClosed",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/play/client/CPacketUpdateSign;<init>(Lnet/minecraft/util/math/BlockPos;[Lnet/minecraft/util/text/ITextComponent;)V"
      ),
      index = 1
   )
   private IChatComponent[] lunar$onSignFinished$v1_8(IChatComponent[] var1) {
      SignUpdateEvent var2 = (SignUpdateEvent)ClientEventBus.method29()
         .method12(SignUpdateEvent.class, () -> new SignUpdateEvent(Arrays.stream(var1).map(IChatComponent::getUnformattedText).toArray(String[]::new)));
      return var2 != null
         ? Arrays.stream(var2.method1()).map(var0 -> (IChatComponent)(new ChatComponentText(var0 != null ? var0 : ""))).toArray(IChatComponent[]::new)
         : var1;
   }
}
