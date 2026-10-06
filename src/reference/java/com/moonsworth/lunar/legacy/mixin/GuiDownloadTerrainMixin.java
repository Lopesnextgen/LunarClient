package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDownloadTerrain.class)
public abstract class GuiDownloadTerrainMixin extends GuiScreen implements GuiScreenBridge {
   @Unique
   @VersionGate(max = 1)
   private long lunar$screenCreatedAt;

   public GuiDownloadTerrainMixin() {
   }

   @VersionGate(max = 1)
   @Inject(method = "initGui", at = @At("HEAD"))
   private void lunar$initGui(CallbackInfo callback1) {
      this.lunar$screenCreatedAt = System.currentTimeMillis();
   }

   @VersionGate(max = 1)
   @Inject(method = "updateScreen$v1_7", at = @At("HEAD"))
   private void lunar$updateScreen(CallbackInfo callback1) {
      List list2 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
      if (System.currentTimeMillis() - this.lunar$screenCreatedAt >= 3000L && list2.isEmpty()) {
         list2.add(new GuiButton(2, this.width / 2 - 100, this.height / 2 + 20, 200, 20, "Disconnect"));
      }
   }

   public void actionPerformed(GuiButton guibutton1) {
      if (this.mc.getCurrentServerData() != null && this.mc.theWorld != null) {
         this.mc.theWorld.sendQuittingDisconnectingPacket();
         this.mc.loadWorld(null);
      }

      if (guibutton1.id == 2) {
         this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
      }
   }
}
