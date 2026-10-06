package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge10$Data;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge13_3;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.font.TextRenderCache;
import com.moonsworth.lunar.client.render.font.CachedTextMesh;
import com.moonsworth.lunar.client.render.font.TextRenderPass;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.optifine.Config;
import net.optifine.util.FontUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Annotation2(min = 1)
@Mixin(FontRenderer.class)
public abstract class FontRendererCacheMixin implements Bridge10_2, Bridge13_3 {
   private static final WorldRenderer wr = lunar$getBuffer();
   private static final FloatBuffer currentColor = BufferUtils.createFloatBuffer(16);
   private ResourceLocation currentTexture;
   private int index;
   private int remaining;
   private boolean[] rendered;
   private TextRenderCache cache = new TextRenderCache();
   private List<TextRenderPass> renderPasses;
   private long currentTick;
   public float offsetBold = 1.0F;
   @Shadow
   public ResourceLocation locationFontTexture;
   @Shadow
   public int textColor;
   @Shadow
   public float red;
   @Shadow
   public float blue;
   @Shadow
   public float green;
   @Shadow
   public float alpha;
   @Shadow
   public float posX;
   @Shadow
   public float posY;
   @Final
   @Shadow
   public byte[] glyphWidth;
   @Shadow
   public boolean strikethroughStyle;
   @Shadow
   public boolean underlineStyle;
   @Shadow
   public int FONT_HEIGHT;
   @Shadow
   public boolean italicStyle;
   @Shadow
   public boolean boldStyle;
   @Shadow
   public boolean unicodeFlag;
   @Shadow
   public Random fontRandom;
   @Shadow
   public boolean randomStyle;
   @Final
   @Shadow
   public int[] colorCode;
   @Shadow
   public boolean bidiFlag;
   @Shadow
   public float[] charWidthFloat;
   public float startPosX = 0.0F;
   public float startPosY = 0.0F;

   private static WorldRenderer lunar$getBuffer() {
      Tessellator var0 = Tessellator.getInstance();
      return ThreadModuleDump63.MC_VERSION >= 5 ? var0.getBuffer$v1_12() : var0.getWorldRenderer();
   }

   @Shadow
   public abstract ResourceLocation getUnicodePageLocation(int var1);

   @Shadow
   public abstract void resetStyles();

   @Shadow
   public abstract String bidiReorder(String var1);

   @Shadow
   public abstract int getCharWidth(char var1);

   @Overwrite
   public float getCharWidthFloat(char var1) {
      if (var1 == 167) {
         return -1.0F;
      } else if (var1 == ' ') {
         return this.charWidthFloat[32];
      } else {
         int var2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .indexOf(var1);
         if (var1 > 0 && var2 != -1 && !this.unicodeFlag) {
            return this.charWidthFloat[var2];
         } else if (this.glyphWidth[var1] != 0) {
            int var3 = this.glyphWidth[var1] >>> 4;
            int var4 = this.glyphWidth[var1] & 15;
            var3 &= 15;
            var4++;
            return (var4 - var3) / 2 + 1;
         } else {
            return 0.0F;
         }
      }
   }

   @Overwrite
   public void readFontTexture() {
      BufferedImage var1;
      try {
         var1 = TextureUtil.readBufferedImage(this.getResourceInputStream(this.locationFontTexture));
      } catch (IOException var21) {
         throw new RuntimeException(var21);
      }

      Properties var2 = FontUtils.readFontProperties(this.locationFontTexture);
      int var3 = var1.getWidth();
      int var4 = var1.getHeight();
      int var5 = var3 / 16;
      int var6 = var4 / 16;
      float var7 = var3 / 128.0F;
      float var8 = Config.limit(var7, 1.0F, 2.0F);
      this.offsetBold = 1.0F / var8;
      float var9 = FontUtils.readFloat(var2, "offsetBold", -1.0F);
      if (var9 >= 0.0F) {
         this.offsetBold = var9;
      }

      int[] var10 = new int[var3 * var4];
      var1.getRGB(0, 0, var3, var4, var10, 0, var3);

      for (int var11 = 0; var11 < 256; var11++) {
         int var12 = var11 % 16;
         int var13 = var11 / 16;
         int var14 = 0;

         for (var14 = var5 - 1; var14 >= 0; var14--) {
            int var15 = var12 * var5 + var14;
            boolean var16 = true;

            for (int var17 = 0; var17 < var6 && var16; var17++) {
               int var18 = (var13 * var6 + var17) * var3;
               int var19 = var10[var15 + var18];
               int var20 = var19 >> 24 & 0xFF;
               if (var20 > 16) {
                  var16 = false;
               }
            }

            if (!var16) {
               break;
            }
         }

         if (var11 == 65) {
            var11 = var11;
         }

         if (var11 == 32) {
            if (var5 <= 8) {
               var14 = (int)(2.0F * var7);
            } else {
               var14 = (int)(1.5F * var7);
            }
         }

         this.charWidthFloat[var11] = (var14 + 1) / var7 + 1.0F;
      }

      FontUtils.readCustomCharWidths(var2, this.charWidthFloat);
   }

   @Overwrite
   public float renderChar(char var1, boolean var2) {
      if (var1 == ' ') {
         this.markRendered();
         return !this.unicodeFlag ? this.charWidthFloat[var1] : 4.0F;
      } else {
         int var3 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .indexOf(var1);
         return var3 != -1 && !this.unicodeFlag ? this.renderDefaultChar(var3, var2) : this.renderUnicodeChar(var1, var2);
      }
   }

   @Overwrite
   public float renderDefaultChar(int var1, boolean var2) {
      if (this.currentTexture == null && !this.rendered[this.index]) {
         this.currentTexture = this.locationFontTexture;
         if (!wr.isDrawing) {
            int var3 = GL11.glGenLists(1);
            this.renderPasses.add(new TextRenderPass(var3, (ResourceLocationBridge)this.locationFontTexture));
            GL11.glNewList(var3, 4864);
            wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         }
      }

      if (this.currentTexture == this.locationFontTexture) {
         float var11 = var1 % 16 * 8;
         float var4 = var1 / 16 * 8;
         float var5 = var2 ? 1.0F : 0.0F;
         float var6 = 7.99F;
         float var7 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
         float var8 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
         float var9 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
         float var10 = this.alpha;
         wr.pos(this.posX - this.startPosX + var5, this.posY - this.startPosY, 0.0)
            .tex(var11 / 128.0F, var4 / 128.0F)
            .color(var7, var8, var9, var10)
            .endVertex();
         wr.pos(this.posX - this.startPosX - var5, this.posY - this.startPosY + 7.99F, 0.0)
            .tex(var11 / 128.0F, (var4 + 7.99F) / 128.0F)
            .color(var7, var8, var9, var10)
            .endVertex();
         wr.pos(this.posX - this.startPosX + var6 - 1.0F - var5, this.posY - this.startPosY + 7.99F, 0.0)
            .tex((var11 + var6 - 1.0F) / 128.0F, (var4 + 7.99F) / 128.0F)
            .color(var7, var8, var9, var10)
            .endVertex();
         wr.pos(this.posX - this.startPosX + var6 - 1.0F + var5, this.posY - this.startPosY, 0.0)
            .tex((var11 + var6 - 1.0F) / 128.0F, var4 / 128.0F)
            .color(var7, var8, var9, var10)
            .endVertex();
         this.markRendered();
      }

      return this.charWidthFloat[var1];
   }

   @Overwrite
   public void loadGlyphTexture(int var1) {
   }

   @Overwrite
   public float renderUnicodeChar(char var1, boolean var2) {
      if (this.glyphWidth[var1] == 0) {
         this.markRendered();
         return 0.0F;
      }

      ResourceLocation var3 = this.getUnicodePageLocation(var1 / 256);
      int var4 = this.glyphWidth[var1] >>> 4;
      int var5 = this.glyphWidth[var1] & 15;
      var4 &= 15;
      float var6 = var4;
      float var7 = var5 + 1;
      if (this.currentTexture == null && !this.rendered[this.index]) {
         this.currentTexture = var3;
         if (!wr.isDrawing) {
            int var8 = GL11.glGenLists(1);
            this.renderPasses.add(new TextRenderPass(var8, (ResourceLocationBridge)var3));
            GL11.glNewList(var8, 4864);
            wr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         }
      }

      if (this.currentTexture == var3) {
         float var17 = var1 % 16 * 16 + var6;
         float var9 = (var1 & 255) / 16 * 16;
         float var10 = var7 - var6 - 0.02F;
         float var11 = var2 ? 1.0F : 0.0F;
         float var12 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
         float var13 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
         float var14 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
         float var15 = this.alpha;
         wr.pos(this.posX - this.startPosX + var11, this.posY - this.startPosY, 0.0)
            .tex(var17 / 256.0F, var9 / 256.0F)
            .color(var12, var13, var14, var15)
            .endVertex();
         wr.pos(this.posX - this.startPosX - var11, this.posY - this.startPosY + 7.99F, 0.0)
            .tex(var17 / 256.0F, (var9 + 15.98F) / 256.0F)
            .color(var12, var13, var14, var15)
            .endVertex();
         wr.pos(this.posX - this.startPosX + var10 / 2.0F - var11, this.posY - this.startPosY + 7.99F, 0.0)
            .tex((var17 + var10) / 256.0F, (var9 + 15.98F) / 256.0F)
            .color(var12, var13, var14, var15)
            .endVertex();
         wr.pos(this.posX - this.startPosX + var10 / 2.0F + var11, this.posY - this.startPosY, 0.0)
            .tex((var17 + var10) / 256.0F, var9 / 256.0F)
            .color(var12, var13, var14, var15)
            .endVertex();
         this.markRendered();
      }

      return (var7 - var6) / 2.0F + 1.0F;
   }

   private void renderStringAtPos(String var1, boolean var2, int var3) {
      CachedTextMesh var4 = this.cache.method1(var1, var3, var2);
      if (var4 != null) {
         var4.method3(this.currentTick);
         GL11.glGetFloat(2816, currentColor);
         GL11.glPushMatrix();
         GL11.glTranslatef(this.posX, this.posY, 0.0F);
         var4.method1();
         GL11.glPopMatrix();
         GL11.glColor4f(currentColor.get(0), currentColor.get(1), currentColor.get(2), currentColor.get(3));
         this.posX = this.posX + var4.getWidth();
      } else {
         this.startPosX = this.posX;
         this.startPosY = this.posY;
         this.renderPasses = new ArrayList<>();
         this.rendered = new boolean[var1.length()];
         this.remaining = var1.length();
         boolean var5 = false;

         while (this.remaining >= 0) {
            if (this.remaining == 0) {
               this.remaining = -1;
            }

            this.posX = this.startPosX;
            this.currentTexture = null;
            this.textColor = -1;
            this.resetStyles();

            for (this.index = 0; this.index < var1.length(); this.index++) {
               char var6 = var1.charAt(this.index);
               if (var6 == 167 && this.index + 1 < var1.length()) {
                  int var17 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var1.charAt(this.index + 1)));
                  if (var17 < 16) {
                     this.randomStyle = false;
                     this.boldStyle = false;
                     this.strikethroughStyle = false;
                     this.underlineStyle = false;
                     this.italicStyle = false;
                     if (var17 < 0 || var17 > 15) {
                        var17 = 15;
                     }

                     if (var2) {
                        var17 += 16;
                     }

                     int var8 = this.colorCode[var17];
                     this.textColor = var8;
                  } else if (var17 == 16) {
                     this.randomStyle = true;
                     var5 = true;
                  } else if (var17 == 17) {
                     this.boldStyle = true;
                  } else if (var17 == 18) {
                     this.strikethroughStyle = true;
                  } else if (var17 == 19) {
                     this.underlineStyle = true;
                  } else if (var17 == 20) {
                     this.italicStyle = true;
                  } else if (var17 == 21) {
                     this.randomStyle = false;
                     this.boldStyle = false;
                     this.strikethroughStyle = false;
                     this.underlineStyle = false;
                     this.italicStyle = false;
                     this.textColor = -1;
                  }

                  this.markRendered();
                  this.index++;
                  this.markRendered();
               } else {
                  int var7 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                     .indexOf(var6);
                  if (this.randomStyle && var7 != -1) {
                     int var9 = this.getCharWidth(var6);

                     char var10;
                     do {
                        var7 = this.fontRandom
                           .nextInt(
                              "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                                 .length()
                           );
                        var10 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
                           .charAt(var7);
                     } while (var9 != this.getCharWidth(var10));

                     var6 = var10;
                  }

                  float var18 = var7 != -1 && !this.unicodeFlag ? this.offsetBold : 0.5F;
                  boolean var19 = (var6 == 0 || var7 == -1 || this.unicodeFlag) && var2;
                  if (var19) {
                     this.posX -= var18;
                     this.posY -= var18;
                  }

                  float var11 = this.renderChar(var6, this.italicStyle);
                  if (var19) {
                     this.posX += var18;
                     this.posY += var18;
                  }

                  if (this.boldStyle) {
                     this.posX += var18;
                     if (var19) {
                        this.posX -= var18;
                        this.posY -= var18;
                     }

                     this.renderChar(var6, this.italicStyle);
                     this.posX -= var18;
                     if (var19) {
                        this.posX += var18;
                        this.posY += var18;
                     }

                     var11 += var18;
                  }

                  if (this.remaining == -1 && (this.strikethroughStyle || this.underlineStyle)) {
                     if (!wr.isDrawing) {
                        int var12 = GL11.glGenLists(1);
                        this.renderPasses.add(new TextRenderPass(var12, null));
                        GL11.glNewList(var12, 4864);
                        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
                     }

                     float var20 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
                     float var13 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
                     float var14 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
                     float var15 = this.alpha;
                     if (this.strikethroughStyle) {
                        wr.pos((double)this.posX - this.startPosX, this.posY - this.startPosY + this.FONT_HEIGHT / 2, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT / 2, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT / 2 - 1.0F, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos((double)this.posX - this.startPosX, this.posY - this.startPosY + this.FONT_HEIGHT / 2 - 1.0F, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                     }

                     if (this.underlineStyle) {
                        wr.pos(this.posX - this.startPosX - 1.0F, this.posY - this.startPosY + this.FONT_HEIGHT, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT - 1.0F, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                        wr.pos(this.posX - this.startPosX - 1.0F, this.posY - this.startPosY + this.FONT_HEIGHT - 1.0F, 0.0)
                           .color(var20, var13, var14, var15)
                           .endVertex();
                     }
                  }

                  this.posX += var11;
               }
            }

            if (wr.isDrawing) {
               Tessellator.getInstance().draw();
               GL11.glEndList();
            }
         }

         var4 = new CachedTextMesh(this.renderPasses, this.posX - this.startPosX, this.currentTick, var5);
         this.cache.put(new TextRenderCache.Data(var1, var3, var2), var4);
         GL11.glPushMatrix();
         GL11.glTranslatef(this.startPosX, this.startPosY, 0.0F);
         var4.method1();
         GL11.glPopMatrix();
         this.renderPasses = null;
      }
   }

   @Overwrite
   public void renderStringAtPos(String var1, boolean var2) {
      this.renderStringAtPos(var1, var2, -1);
   }

   private void markRendered() {
      if (!this.rendered[this.index]) {
         this.rendered[this.index] = true;
         this.remaining--;
      }
   }

   @Redirect(method = "renderString", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
   public void impl$onRenderStringAtPos(FontRenderer var1, String var2, boolean var3, String var4, float var5, float var6, int var7, boolean var8) {
      this.renderStringAtPos(var2, var3, var7);
   }

   @Override
   public void tick() {
      byte var1 = 10;
      long var2 = this.currentTick - 20 * var1;
      ObjectIterator var4 = this.cache.values().iterator();

      while (var4.hasNext()) {
         CachedTextMesh var5 = (CachedTextMesh)var4.next();
         if (var5.method4() || var5.method2() < var2) {
            var5.delete();
            var4.remove();
         }
      }

      if (this.currentTick % 50L == 0L) {
         this.cache.trim();
      }

      this.currentTick++;
   }

   @Override
   public void clearCaches() {
      ObjectIterator var1 = this.cache.values().iterator();

      while (var1.hasNext()) {
         CachedTextMesh var2 = (CachedTextMesh)var1.next();
         var2.delete();
      }

      this.cache.clear();
   }

   @Unique
   private InputStream getResourceInputStream(ResourceLocation var1) {
      return Minecraft.getMinecraft().getResourceManager().getResource(var1).getInputStream();
   }

   @Override
   public float bridge$boldWidth() {
      return this.offsetBold;
   }

   @Override
   public Bridge10$Data bridge$getCodepointCharData(int var1) {
      if (var1 != 32 && var1 != 160) {
         int var6 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .indexOf((char)var1);
         if (var6 != -1 && !this.unicodeFlag) {
            return new Bridge10$Data(0.0F, this.charWidthFloat[var6 & 0xFF], true, var6);
         }

         byte var3 = this.glyphWidth[var1 & 65535];
         int var4 = var3 >>> 4;
         int var5 = var3 & 15;
         return new Bridge10$Data(var4, var5, false, -1);
      } else {
         float var2 = !this.unicodeFlag ? this.charWidthFloat[var1 & 0xFF] : 4.0F;
         return new Bridge10$Data(0.0F, var2, true, 32);
      }
   }
}
