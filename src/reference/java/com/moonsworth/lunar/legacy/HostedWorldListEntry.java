package com.moonsworth.lunar.legacy;

import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsResponse.HostedWorld;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldEntryRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;

public class HostedWorldListEntry implements IGuiListEntry {
   private final GuiMultiplayer field1;
   private final HostedWorld field2;
   public long field3;

   public HostedWorldListEntry(GuiMultiplayer guimultiplayer1, HostedWorld hostedworld2) {
      this.field1 = guimultiplayer1;
      this.field2 = hostedworld2;
   }

   public boolean method1() {
      return this.field2.getJoinability() == Joinability.JOINABILITY_ALLOWED && Ref.method3().bridge$getSession() != null;
   }

   public void updatePosition$v1_12(int number1, int number2, int number3, float value4) {
   }

   public void drawEntry(int number1, int number2, int number3, int number4, int number5, Tessellator tessellator6, int number7, int number8, boolean flag9) {
      HostedWorldEntryRenderer.method2(
         this.field2, BridgeExtension3_5.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3, number4, number5, number7, number8, flag9
      );
   }

   public void drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8) {
      HostedWorldEntryRenderer.method2(
         this.field2, BridgeExtension3_5.method32().RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3, number4, number5, number6, number7, flag8
      );
   }

   public void drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, float value9) {
      HostedWorldEntryRenderer.method2(this.field2, BridgeExtension3_5.method12(value9).RHCRIRCHOIICCIIIIHROORCHOHRIRO(), number2, number3, number4, number5, number6, number7, flag8);
   }

   public boolean mousePressed(int number1, int number2, int number3, int number4, int number5, int number6) {
      if (number5 <= 32.0 && number5 < 26.0 && number5 > 10.0 && this.method1()) {
         this.field1.serverListSelector.selectedSlotIndex = number1;
         this.field1.connectToSelected();
         return true;
      }

      this.field1.serverListSelector.selectedSlotIndex = number1;
      this.field1.btnSelectServer.field_178665_b = this.method1();
      this.field1.btnEditServer.field_178665_b = false;
      this.field1.btnDeleteServer.field_178665_b = false;
      if (Minecraft.getSystemTime() - this.field3 < 250L && this.method1()) {
         this.field1.connectToSelected();
      }

      this.field3 = Minecraft.getSystemTime();
      return true;
   }

   public void mouseReleased(int number1, int number2, int number3, int number4, int number5, int number6) {
   }

   public void setSelected(int number1, int number2, int number3) {
   }

   @Generated
   public HostedWorld method2() {
      return this.field2;
   }
}
