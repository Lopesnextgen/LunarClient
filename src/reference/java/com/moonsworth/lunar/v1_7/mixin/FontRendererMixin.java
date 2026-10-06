package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.bridge.Bridge10$Data;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge4_11;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Bridge_27;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.font.TextRenderCache;
import com.moonsworth.lunar.client.render.font.CachedTextMesh;
import com.moonsworth.lunar.client.render.font.TextRenderPass;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin implements Bridge10_2 {
   private static final Bridge4_11 tess = (Bridge4_11)Tessellator.theMinecraft;
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
   @Shadow
   public boolean bidiFlag;
   @Shadow
   public boolean strikethroughStyle;
   @Shadow
   public boolean underlineStyle;
   @Shadow
   public int textColor;
   @Shadow
   public int FONT_HEIGHT;
   @Shadow
   public boolean italicStyle;
   @Shadow
   public ResourceLocation locationFontTexture;
   @Shadow
   public byte[] glyphWidth;
   @Shadow
   public boolean randomStyle;
   @Shadow
   public boolean boldStyle;
   @Shadow
   public int[] colorCode;
   @Shadow
   public Random fontRandom;
   @Shadow
   public boolean unicodeFlag;
   @Shadow
   public float[] charWidth;
   public float startPosX = 0.0F;
   public float startPosY = 0.0F;

   @Shadow
   public abstract String bidiReorder(String var1);

   @Shadow
   public abstract float renderUnicodeChar(char var1, boolean var2);

   @Shadow
   public abstract ResourceLocation getUnicodePageLocation(int var1);

   @Shadow
   public abstract void resetStyles();

   @Shadow
   public abstract float renderDefaultChar(int var1, boolean var2);

   @Shadow
   public abstract int getCharWidth(char var1);

   @Inject(method = "renderDefaultChar(IZ)F", at = @At("HEAD"), cancellable = true)
   private void impl$onRenderDefaultChar(int var1, boolean var2, CallbackInfoReturnable<Float> var3) {
      if (this.currentTexture == null && !this.rendered[this.index]) {
         this.currentTexture = this.locationFontTexture;
         if (!tess.bridge$isDrawing()) {
            int var4 = GL11.glGenLists(1);
            this.renderPasses.add(new TextRenderPass(var4, (ResourceLocationBridge)this.locationFontTexture));
            GL11.glNewList(var4, 4864);
            tess.bridge$begin(DrawMode.QUADS, Bridge_27.field8);
         }
      }

      if (this.currentTexture == this.locationFontTexture) {
         float var12 = var1 % 16 * 8;
         float var5 = var1 / 16 * 8;
         float var6 = var2 ? 1.0F : 0.0F;
         float var7 = 7.99F;
         float var8 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
         float var9 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
         float var10 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
         float var11 = this.alpha;
         tess.bridge$pos(this.posX - this.startPosX + var6, this.posY - this.startPosY, 0.0F)
            .bridge$uv(var12 / 128.0F, var5 / 128.0F)
            .bridge$color(var8, var9, var10, var11)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX - var6, this.posY - this.startPosY + 7.99F, 0.0F)
            .bridge$uv(var12 / 128.0F, (var5 + 7.99F) / 128.0F)
            .bridge$color(var8, var9, var10, var11)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX + var7 - 1.0F - var6, this.posY - this.startPosY + 7.99F, 0.0F)
            .bridge$uv((var12 + var7 - 1.0F) / 128.0F, (var5 + 7.99F) / 128.0F)
            .bridge$color(var8, var9, var10, var11)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX + var7 - 1.0F + var6, this.posY - this.startPosY, 0.0F)
            .bridge$uv((var12 + var7 - 1.0F) / 128.0F, var5 / 128.0F)
            .bridge$color(var8, var9, var10, var11)
            .bridge$endVertex();
         this.markRendered();
      }

      var3.setReturnValue(this.charWidth[var1]);
   }

   @Inject(method = "loadGlyphTexture(I)V", at = @At("HEAD"), cancellable = true)
   private void impl$onLoadGlyphTexture(CallbackInfo var1) {
      var1.cancel();
   }

   @Inject(method = "renderUnicodeChar(CZ)F", at = @At("HEAD"), cancellable = true)
   private void impl$onRenderUnicodeChar(char var1, boolean var2, CallbackInfoReturnable<Float> var3) {
      if (this.glyphWidth[var1] == 0) {
         this.markRendered();
         var3.setReturnValue(0.0F);
      }

      ResourceLocation var4 = this.getUnicodePageLocation(var1 / 256);
      int var5 = this.glyphWidth[var1] >>> 4;
      int var6 = this.glyphWidth[var1] & 15;
      var5 &= 15;
      float var7 = var5;
      float var8 = var6 + 1;
      if (this.currentTexture == null && !this.rendered[this.index]) {
         this.currentTexture = var4;
         if (!tess.bridge$isDrawing()) {
            int var9 = GL11.glGenLists(1);
            this.renderPasses.add(new TextRenderPass(var9, (ResourceLocationBridge)var4));
            GL11.glNewList(var9, 4864);
            tess.bridge$begin(DrawMode.QUADS, Bridge_27.field8);
         }
      }

      if (this.currentTexture == var4) {
         float var18 = var1 % 16 * 16 + var7;
         float var10 = (var1 & 255) / 16 * 16;
         float var11 = var8 - var7 - 0.02F;
         float var12 = var2 ? 1.0F : 0.0F;
         float var13 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
         float var14 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
         float var15 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
         float var16 = this.alpha;
         tess.bridge$pos(this.posX - this.startPosX + var12, this.posY - this.startPosY, 0.0F)
            .bridge$uv(var18 / 256.0F, var10 / 256.0F)
            .bridge$color(var13, var14, var15, var16)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX - var12, this.posY - this.startPosY + 7.99F, 0.0F)
            .bridge$uv(var18 / 256.0F, (var10 + 15.98F) / 256.0F)
            .bridge$color(var13, var14, var15, var16)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX + var11 / 2.0F - var12, this.posY - this.startPosY + 7.99F, 0.0F)
            .bridge$uv((var18 + var11) / 256.0F, (var10 + 15.98F) / 256.0F)
            .bridge$color(var13, var14, var15, var16)
            .bridge$endVertex();
         tess.bridge$pos(this.posX - this.startPosX + var11 / 2.0F + var12, this.posY - this.startPosY, 0.0F)
            .bridge$uv((var18 + var11) / 256.0F, var10 / 256.0F)
            .bridge$color(var13, var14, var15, var16)
            .bridge$endVertex();
         this.markRendered();
      }

      var3.setReturnValue((var8 - var7) / 2.0F + 1.0F);
   }

   @Redirect(method = "renderString", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
   public void impl$onRenderStringAtPos(FontRenderer var1, String var2, boolean var3, String var4, int var5, int var6, int var7, boolean var8) {
      this.renderStringAtPos(var2, var3, var7);
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

                  float var11 = this.renderCharAtPos(var7, var6, this.italicStyle);
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

                     this.renderCharAtPos(var7, var6, this.italicStyle);
                     this.posX -= var18;
                     if (var19) {
                        this.posX += var18;
                        this.posY += var18;
                     }

                     var11 += var18;
                  }

                  if (this.remaining == -1 && (this.strikethroughStyle || this.underlineStyle)) {
                     if (!tess.bridge$isDrawing()) {
                        int var12 = GL11.glGenLists(1);
                        this.renderPasses.add(new TextRenderPass(var12, null));
                        GL11.glNewList(var12, 4864);
                        tess.bridge$begin(DrawMode.QUADS, Bridge_27.field3);
                     }

                     float var20 = this.textColor == -1 ? this.red : (this.textColor >> 16) / 255.0F;
                     float var13 = this.textColor == -1 ? this.blue : (this.textColor >> 8 & 0xFF) / 255.0F;
                     float var14 = this.textColor == -1 ? this.green : (this.textColor & 0xFF) / 255.0F;
                     float var15 = this.alpha;
                     if (this.strikethroughStyle) {
                        tess.method1((double)this.posX - this.startPosX, this.posY - this.startPosY + this.FONT_HEIGHT / 2, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT / 2, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT / 2 - 1.0F, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1((double)this.posX - this.startPosX, this.posY - this.startPosY + this.FONT_HEIGHT / 2 - 1.0F, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                     }

                     if (this.underlineStyle) {
                        tess.method1(this.posX - this.startPosX - 1.0F, this.posY - this.startPosY + this.FONT_HEIGHT, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1(this.posX - this.startPosX + var11, this.posY - this.startPosY + this.FONT_HEIGHT - 1.0F, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                        tess.method1(this.posX - this.startPosX - 1.0F, this.posY - this.startPosY + this.FONT_HEIGHT - 1.0F, 0.0)
                           .bridge$color(var20, var13, var14, var15)
                           .bridge$endVertex();
                     }
                  }

                  this.posX += var11;
               }
            }

            if (tess.bridge$isDrawing()) {
               tess.bridge$end();
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

   @Inject(method = "renderStringAtPos(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
   private void impl$onRenderStringAtPos(String var1, boolean var2, CallbackInfo var3) {
      var3.cancel();
      this.renderStringAtPos(var1, var2, -1);
   }

   @Overwrite
   public float renderCharAtPos(int var1, char var2, boolean var3) {
      if (var2 == ' ') {
         this.markRendered();
         return !this.unicodeFlag ? this.charWidth[var2] : 4.0F;
      } else {
         int var4 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
            .indexOf(var2);
         return var4 != -1 && !this.unicodeFlag ? this.renderDefaultChar(var4, var3) : this.renderUnicodeChar(var2, var3);
      }
   }

   private void markRendered() {
      if (!this.rendered[this.index]) {
         this.rendered[this.index] = true;
         this.remaining--;
      }
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

   @Inject(
      method = "drawString(Ljava/lang/String;IIIZ)I",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;resetStyles()V", shift = Shift.BEFORE)
   )
   private void impl$onDrawString(CallbackInfoReturnable<Integer> var1) {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
   }

   @Inject(method = "drawString(Ljava/lang/String;IIIZ)I", at = @At("RETURN"))
   private void impl$postDrawString(CallbackInfoReturnable<Integer> var1) {
      GL11.glDisable(3042);
   }

   @Override
   public void clearCaches() {
      this.cache.values().forEach(CachedTextMesh::delete);
      this.cache.clear();
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
            return new Bridge10$Data(0.0F, this.charWidth[var6 & 0xFF], true, var6);
         }

         byte var3 = this.glyphWidth[var1 & 65535];
         int var4 = var3 >>> 4;
         int var5 = var3 & 15;
         return new Bridge10$Data(var4, var5, false, var6);
      } else {
         float var2 = !this.unicodeFlag ? this.charWidth[var1 & 0xFF] : 4.0F;
         return new Bridge10$Data(0.0F, var2, true, 32);
      }
   }
}
