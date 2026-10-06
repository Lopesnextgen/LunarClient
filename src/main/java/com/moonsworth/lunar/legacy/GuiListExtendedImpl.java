package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;

public class GuiListExtendedImpl implements IGuiListEntry {
   private final Minecraft minecraft = Minecraft.getMinecraft();

   public GuiListExtendedImpl() {
   }

   private void method1(int number1, int number2) {
      if (this.minecraft.currentScreen != null) {
         FontRenderer font3 = this.minecraft.fontRendererObj;
         String text4 = "Hosted Worlds";
         font3.drawString(text4, this.minecraft.currentScreen.width / 2 - font3.getStringWidth(text4) / 2, number1 + number2 / 2 - 2, 16777215);

         String text5 = switch ((int)(Minecraft.getSystemTime() / 300L % 4L)) {
            case 1, 3 -> "o O o";
            case 2 -> "o o O";
            default -> "O o o";
         };
         font3.drawString(text5, this.minecraft.currentScreen.width / 2 - font3.getStringWidth(text5) / 2, number1 + number2 / 2 + 9 - 2, 8421504);
      }
   }

   @VersionGate(max = 0)
   public void drawEntry(int number1, int number2, int number3, int number4, int number5, Tessellator tessellator6, int number7, int value, boolean flag) {
      this.method1(number3, number5);
   }

   public boolean mousePressed(int number1, int number2, int number3, int number4, int number5, int number6) {
      return false;
   }

   public void mouseReleased(int number1, int number2, int number3, int number4, int number5, int number6) {
   }

   @VersionGate(1)
   public void setSelected(int number1, int number2, int number3) {
   }

   @VersionGate(1)
   public void drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8) {
      this.method1(number3, number5);
   }

   @VersionGate(min = 5)
   public void updatePosition$v1_12(int number1, int number2, int number3, float value) {
   }

   @VersionGate(min = 5)
   public void drawEntry(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, float value) {
      this.method1(number3, number5);
   }
}
