package com.moonsworth.lunar.replaymod.mixin;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.replaymod.core.versions.MCVer;
import java.net.URI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MCVer.class)
public class MCVerMixin_v1_8 {
   @Overwrite
   public static void addButton(GuiScreen var0, GuiButton var1) {
      var0.buttonList.add(var1);
   }

   @Overwrite
   public static boolean hasOptifine() {
      return Bridge.method5().isPresent();
   }

   @Overwrite
   public static void openURL(URI var0) {
      ThreadModuleDump61.method5(var0, Initiator.INITIATOR_UNSPECIFIED);
   }
}
