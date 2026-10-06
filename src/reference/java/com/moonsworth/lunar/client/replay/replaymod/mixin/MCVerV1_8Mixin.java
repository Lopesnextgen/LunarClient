package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.replaymod.core.versions.MCVer;
import java.net.URI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MCVer.class)
public class MCVerV1_8Mixin {
   public MCVerV1_8Mixin() {
   }

   @Overwrite
   public static void addButton(GuiScreen screen0, GuiButton guibutton1) {
      screen0.buttonList.add(guibutton1);
   }

   @Overwrite
   public static boolean hasOptifine() {
      return Bridge.method5().isPresent();
   }

   @Overwrite
   public static void openURL(URI uri0) {
      BrowserUtils.method5(uri0, Initiator.INITIATOR_UNSPECIFIED);
   }
}
