package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

@VersionGate(max = 5)
public class GuiButtonImpl extends GuiButton {
   private static final int field1 = 20;
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("minecraft", "textures/gui/checkbox.png");
   private boolean selected;

   public GuiButtonImpl(int number1, int number2, int number3, String text4, boolean flag5) {
      super(number1, number2, number3, 0, 0, text4);
      this.selected = flag5;
      this.width = 24 + (int)Ref.method10().bridge$getStringWidth(text4);
      this.height = 20;
   }

   @VersionGate(max = 1)
   public void drawButton(Minecraft minecraft1, int number2, int number3) {
      if (this.visible) {
         this.hovered = number2 >= this.x && number3 >= this.y && number2 < this.x + 20 && number3 < this.y + 20;
         Ref.method3().bridge$getTextureManager().bridge$bindTexture(field2);
         if (Ref.MC_VERSION >= 1) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
         } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         }

         int number4 = this.hovered ? 20 : 0;
         int number5 = this.selected ? 20 : 0;
         drawModalRectWithCustomSizedTexture(this.x, this.y, number4, number5, 20, 20, 64.0F, 64.0F);
         Ref.method10()
            .bridge$drawString(AbstractRenderContext.method32(), this.displayString, this.x + 20 + 4, this.y + (this.height - 8) / 2, 14737632, false);
      }
   }

   @VersionGate(min = 5)
   public void drawButton(Minecraft minecraft1, int number2, int number3, float value4) {
      if (this.visible) {
         this.hovered = number2 >= this.x && number3 >= this.y && number2 < this.x + 20 && number3 < this.y + 20;
         Ref.method3().bridge$getTextureManager().bridge$bindTexture(field2);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         int number5 = this.hovered ? 20 : 0;
         int number6 = this.selected ? 20 : 0;
         drawModalRectWithCustomSizedTexture(this.x, this.y, number5, number6, 20, 20, 64.0F, 64.0F);
         Ref.method10()
            .bridge$drawString(AbstractRenderContext.method32(), this.displayString, this.x + 20 + 4, this.y + (this.height - 8) / 2, 14737632, false);
      }
   }

   public boolean mousePressed(Minecraft minecraft1, int number2, int number3) {
      boolean flag4 = number2 >= this.x && number3 >= this.y && number2 < this.x + 20 && number3 < this.y + 20;
      if (this.enabled && this.visible && flag4) {
         this.selected = !this.selected;
         return false;
      } else {
         return false;
      }
   }

   @Generated
   public boolean isSelected() {
      return this.selected;
   }
}
