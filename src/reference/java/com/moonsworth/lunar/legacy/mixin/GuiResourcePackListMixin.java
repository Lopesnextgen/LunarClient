package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge10_4;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.minecraft.GuiResourcePackListBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiResourcePackList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiResourcePackList.class)
public abstract class GuiResourcePackListMixin extends GuiListExtended implements GuiResourcePackListBridge {
   @Mutable
   @Final
   @Shadow
   public List field_148204_l$v1_7;
   @Mutable
   @Final
   @Shadow
   public List<ResourcePackListEntry> field_148204_l;

   public GuiResourcePackListMixin(Minecraft minecraft1, int number2, int number3, int number4, int number5, int number6) {
      super(minecraft1, number2, number3, number4, number5, number6);
   }

   public void setUnderlyingList(List<Bridge10_4> list1) {
      if (Ref.MC_VERSION >= 1) {
         this.field_148204_l = list1;
      } else {
         this.field_148204_l$v1_7 = list1;
      }
   }

   public void overlayBackground(int number1, int number2, int number3, int number4) {
      if (Ref.method8() == null || !(Boolean)Ref.method4().method41().method6().method28().get()) {
         super.overlayBackground(number1, number2, number3, number4);
      }
   }

   public void drawScreen(int number1, int number2, float value3) {
      if (Ref.MC_VERSION <= 0 || this.field_178041_q) {
         if (!Mouse.isButtonDown(0)) {
            this.setEnabled(true);
         }

         this.mouseX = number1;
         this.mouseY = number2;
         this.drawBackground();
         int number4 = this.getScrollBarX();
         int number5 = number4 + 6;
         int number10 = this.getSize();
         if (Ref.MC_VERSION <= 0 && number1 > this.left && number1 < this.right && number2 > this.top && number2 < this.bottom) {
            if (!Mouse.isButtonDown(0) || !this.getEnabled()) {
               for (; !this.mc.gameSettings.touchscreen && Mouse.next(); this.mc.currentScreen.handleMouseInput()) {
                  int number18 = Mouse.getEventDWheel();
                  if (number18 != 0) {
                     if (number18 > 0) {
                        number18 = -1;
                     } else if (number18 < 0) {
                        number18 = 1;
                     }

                     this.amountScrolled = this.amountScrolled + number18 * this.slotHeight / 2;
                  }
               }

               this.initialClickY$v1_7 = -1.0F;
            } else if (this.initialClickY$v1_7 == -1.0F) {
               boolean flag11 = true;
               if (number2 >= this.top && number2 <= this.bottom) {
                  int number12 = this.width / 2 - this.getListWidth() / 2;
                  int number6 = this.width / 2 + this.getListWidth() / 2;
                  int number7 = number2 - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                  int number13 = number7 / this.slotHeight;
                  if (number1 >= number12 && number1 <= number6 && number13 >= 0 && number7 >= 0 && number13 < number10) {
                     boolean flag14 = number13 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                     this.elementClicked(number13, flag14, number1, number2);
                     this.selectedElement = number13;
                     this.lastClicked = Minecraft.getSystemTime();
                  } else if (number1 >= number12 && number1 <= number6 && number7 < 0) {
                     this.func_148132_a(number1 - number12, number2 - this.top + (int)this.amountScrolled - 4);
                     flag11 = false;
                  }

                  if (number1 >= number4 && number1 <= number5) {
                     this.scrollMultiplier = -1.0F;
                     int number9 = this.func_148135_f();
                     if (number9 < 1) {
                        number9 = 1;
                     }

                     int number8 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / this.getContentHeight());
                     if (number8 < 32) {
                        number8 = 32;
                     }

                     if (number8 > this.bottom - this.top - 8) {
                        number8 = this.bottom - this.top - 8;
                     }

                     this.scrollMultiplier = this.scrollMultiplier / ((float)(this.bottom - this.top - number8) / number9);
                  } else {
                     this.scrollMultiplier = 1.0F;
                  }

                  if (flag11) {
                     this.initialClickY$v1_7 = number2;
                  } else {
                     this.initialClickY$v1_7 = -2.0F;
                  }
               } else {
                  this.initialClickY$v1_7 = -2.0F;
               }
            } else if (this.initialClickY$v1_7 >= 0.0F) {
               this.amountScrolled = this.amountScrolled - (number2 - this.initialClickY$v1_7) * this.scrollMultiplier;
               this.initialClickY$v1_7 = number2;
            }
         }

         this.bindAmountScrolled();
         if (Ref.MC_VERSION >= 1) {
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
         } else {
            GL11.glDisable(2896);
            GL11.glDisable(2912);
         }

         Tessellator tessellator19 = Tessellator.theMinecraft;
         if (Ref.method8() == null || !(Boolean)Ref.method4().method41().method6().method28().get()) {
            this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
            if (Ref.MC_VERSION >= 1) {
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            } else {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            float value20 = 32.0F;
            LcuiScreen.method97(AbstractRenderContext.method32(), this.left, this.top, this.right - this.left, this.bottom - this.top, -1879048192);
         }

         int number21 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
         int number22 = this.top + 4 - (int)this.amountScrolled;
         if (this.hasListHeader) {
            this.drawListHeader(number21, number22, tessellator19);
         }

         if (Ref.MC_VERSION >= 5) {
            this.drawSelectionBox(number21, number22, number1, number2, value3);
         } else {
            this.drawSelectionBox(number21, number22, number1, number2);
         }

         if (Ref.MC_VERSION >= 1) {
            GlStateManager.disableDepth();
         } else {
            GL11.glDisable(2929);
         }

         this.overlayBackground(0, this.top, 255, 255);
         this.overlayBackground(this.bottom, this.height, 255, 255);
         if (Ref.MC_VERSION >= 1) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
            GlStateManager.disableTexture2D();
         } else {
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 0, 1);
            GL11.glDisable(3008);
            GL11.glShadeModel(7425);
            GL11.glDisable(3553);
         }

         int number23 = Ref.MC_VERSION >= 5 ? this.getMaxScroll$v1_12() : this.func_148135_f();
         if (number23 > 0) {
            int number15 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
            if (Ref.MC_VERSION >= 5) {
               number15 = MathHelper.clamp$v1_12(number15, 32, this.bottom - this.top - 8);
            } else {
               if (number15 < 32) {
                  number15 = 32;
               }

               if (number15 > this.bottom - this.top - 8) {
                  number15 = this.bottom - this.top - 8;
               }
            }

            int number16 = (int)this.amountScrolled * (this.bottom - this.top - number15) / number23 + this.top;
            if (number16 < this.top) {
               number16 = this.top;
            }

            if (Ref.MC_VERSION >= 1) {
               WorldRenderer worldrenderer17 = Ref.MC_VERSION >= 5 ? tessellator19.getBuffer$v1_12() : tessellator19.getWorldRenderer();
               worldrenderer17.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               worldrenderer17.pos(number4, this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
               worldrenderer17.pos(number5, this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
               worldrenderer17.pos(number5, this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
               worldrenderer17.pos(number4, this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
               tessellator19.draw();
               worldrenderer17.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               worldrenderer17.pos(number4, number16 + number15, 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
               worldrenderer17.pos(number5, number16 + number15, 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
               worldrenderer17.pos(number5, number16, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
               worldrenderer17.pos(number4, number16, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
               tessellator19.draw();
               worldrenderer17.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
               worldrenderer17.pos(number4, number16 + number15 - 1, 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
               worldrenderer17.pos(number5 - 1, number16 + number15 - 1, 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
               worldrenderer17.pos(number5 - 1, number16, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
               worldrenderer17.pos(number4, number16, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
               tessellator19.draw();
            } else {
               tessellator19.startDrawingQuads$v1_7();
               tessellator19.setColorRGBA_I$v1_7(0, 255);
               tessellator19.addVertexWithUV$v1_7(number4, this.bottom, 0.0, 0.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5, this.bottom, 0.0, 1.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5, this.top, 0.0, 1.0, 0.0);
               tessellator19.addVertexWithUV$v1_7(number4, this.top, 0.0, 0.0, 0.0);
               tessellator19.draw();
               tessellator19.startDrawingQuads$v1_7();
               tessellator19.setColorRGBA_I$v1_7(8421504, 255);
               tessellator19.addVertexWithUV$v1_7(number4, number16 + number15, 0.0, 0.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5, number16 + number15, 0.0, 1.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5, number16, 0.0, 1.0, 0.0);
               tessellator19.addVertexWithUV$v1_7(number4, number16, 0.0, 0.0, 0.0);
               tessellator19.draw();
               tessellator19.startDrawingQuads$v1_7();
               tessellator19.setColorRGBA_I$v1_7(12632256, 255);
               tessellator19.addVertexWithUV$v1_7(number4, number16 + number15 - 1, 0.0, 0.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5 - 1, number16 + number15 - 1, 0.0, 1.0, 1.0);
               tessellator19.addVertexWithUV$v1_7(number5 - 1, number16, 0.0, 1.0, 0.0);
               tessellator19.addVertexWithUV$v1_7(number4, number16, 0.0, 0.0, 0.0);
               tessellator19.draw();
            }
         }

         if (Ref.MC_VERSION >= 5) {
            this.renderDecorations$v1_12(number1, number2);
         } else {
            this.func_148142_b(number1, number2);
         }

         if (Ref.MC_VERSION >= 1) {
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
         } else {
            GL11.glEnable(3553);
            GL11.glShadeModel(7424);
            GL11.glEnable(3008);
            GL11.glDisable(3042);
         }
      }
   }
}
