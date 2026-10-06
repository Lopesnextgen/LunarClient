package com.moonsworth.lunar.legacy;

import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsResponse.HostedWorld;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.coordinates.mixin.Coordinates;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;

public class GuiListExtendedImpl2 implements IGuiListEntry {
   private final GuiMultiplayer field1;
   private final HostedWorld field2;
   public long field3;

   public GuiListExtendedImpl2(GuiMultiplayer var1, HostedWorld var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public boolean method1() {
      return this.field2.getJoinability() == Joinability.JOINABILITY_ALLOWED && ThreadModuleDump63.method3().bridge$getSession() != null;
   }

   public void updatePosition$v1_12(int var1, int var2, int var3, float var4) {
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      Coordinates.method2(
         this.field2, BridgeExtension3_5.method32().method42(), var2, var3, var4, var5, var7, var8, var9
      );
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      Coordinates.method2(
         this.field2, BridgeExtension3_5.method32().method42(), var2, var3, var4, var5, var6, var7, var8
      );
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9) {
      Coordinates.method2(this.field2, BridgeExtension3_5.method12(var9).method42(), var2, var3, var4, var5, var6, var7, var8);
   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (var5 <= 32.0 && var5 < 26.0 && var5 > 10.0 && this.method1()) {
         this.field1.serverListSelector.selectedSlotIndex = var1;
         this.field1.connectToSelected();
         return true;
      }

      this.field1.serverListSelector.selectedSlotIndex = var1;
      this.field1.btnSelectServer.field_178665_b = this.method1();
      this.field1.btnEditServer.field_178665_b = false;
      this.field1.btnDeleteServer.field_178665_b = false;
      if (Minecraft.getSystemTime() - this.field3 < 250L && this.method1()) {
         this.field1.connectToSelected();
      }

      this.field3 = Minecraft.getSystemTime();
      return true;
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public void setSelected(int var1, int var2, int var3) {
   }

   @Generated
   public HostedWorld method2() {
      return this.field2;
   }
}
