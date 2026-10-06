package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge10$Data;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.function.Function;
import net.kyori.adventure.text.Component;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin implements Bridge10_2 {
   @Shadow
   public int textColor;
   @Shadow
   public float red;
   @Shadow
   public float green;
   @Shadow
   public float blue;
   @Shadow
   public float alpha;
   @Shadow
   public float posX;
   @Shadow
   public float posY;
   @Shadow
   public boolean unicodeFlag;
   @Final
   @Shadow
   public byte[] glyphWidth;
   @Unique
   private Function<Float, Integer> lunar$color = null;
   @Unique
   private boolean lunar$shadow = false;

   @Shadow
   public abstract int drawString(String var1, float var2, float var3, int var4, boolean var5);

   @Shadow
   public abstract int drawString(String var1, int var2, int var3, int var4, boolean var5);

   @Shadow
   public abstract int renderString(String var1, float var2, float var3, int var4, boolean var5);

   @Shadow
   public abstract int renderString(String var1, int var2, int var3, int var4, boolean var5);

   @Shadow
   public abstract List<String> listFormattedStringToWidth(String var1, int var2);

   @Shadow
   public abstract List listFormattedStringToWidth(String var1, int var2);

   @Shadow
   public abstract int getStringWidth(String var1);

   @Override
   public void lunar$setColorHolder(@Nullable Function<Float, Integer> var1) {
      this.lunar$color = var1;
   }

   @Nullable
   @Override
   public Function<Float, Integer> lunar$getColorHolder() {
      return this.lunar$color;
   }

   @Override
   public void lunar$setShadowState(boolean var1) {
      this.lunar$shadow = var1;
   }

   @Override
   public boolean lunar$getShadowState() {
      return this.lunar$shadow;
   }

   @Override
   public String bridge$getFormattedText(Bridge2_42 var1) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? ((IChatComponent)var1).getFormattedText() : ((IChatComponent)var1).getFormattedText();
   }

   @Override
   public String bridge$getUnFormattedText(Bridge2_42 var1) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? ((IChatComponent)var1).getUnformattedText() : ((IChatComponent)var1).getUnformattedText();
   }

   @Override
   public float bridge$getStringWidth(String var1) {
      return this.getStringWidth(var1);
   }

   @Override
   public float bridge$drawShadow(AbstractRenderContext var1, String var2, float var3, float var4, int var5) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.renderString(var2, var3 + 1.0F, var4 + 1.0F, var5, true)
         : this.renderString(var2, MathHelper.floor_float(var3 + 1.0F), MathHelper.floor_float(var4 + 1.0F), var5, true);
   }

   @Override
   public float bridge$drawString(AbstractRenderContext var1, String var2, float var3, float var4, Function<Float, Integer> var5, boolean var6) {
      try {
         this.lunar$color = var5;
         int var7 = (Integer)var5.apply(var3 + var4);
         if (var6) {
            this.lunar$shadow = true;
            int var8 = ThreadModuleDump23.method32(var7);
            this.bridge$drawString(var1, var2, var3 + 1.0F, var4 + 1.0F, var8, false);
         }

         this.lunar$shadow = false;
         return (int)this.bridge$drawString(var1, var2, var3, var4, var7, false);
      } finally {
         this.lunar$color = null;
      }
   }

   @Override
   public float bridge$getStringWidth(Bridge2_42 var1) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.getStringWidth(((IChatComponent)var1).getFormattedText())
         : this.getStringWidth(((IChatComponent)var1).getFormattedText());
   }

   @Override
   public float bridge$drawString(AbstractRenderContext var1, Bridge2_42 var2, float var3, float var4, int var5, boolean var6) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.bridge$drawString(var1, ((IChatComponent)var2).getFormattedText(), var3, var4, var5, var6)
         : this.bridge$drawString(var1, ((IChatComponent)var2).getFormattedText(), var3, var4, var5, var6);
   }

   @Override
   public float bridge$drawString(AbstractRenderContext var1, String var2, float var3, float var4, int var5, boolean var6) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.drawString(var2, var3, var4, var5, var6);
      }

      int var7 = MathHelper.floor_float(var3);
      int var8 = MathHelper.floor_float(var4);
      float var9 = var3 - var7;
      float var10 = var4 - var8;
      if (var9 != 0.0F || var10 != 0.0F) {
         GL11.glTranslatef(var9, var10, 0.0F);
      }

      float var11 = this.drawString(var2, var7, var8, var5, var6);
      if (var9 != 0.0F || var10 != 0.0F) {
         GL11.glTranslatef(-var9, -var10, 0.0F);
      }

      return var11;
   }

   @Override
   public List<Bridge2_42> bridge$wrapLines(Bridge2_42 var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? this.listFormattedStringToWidth(((IChatComponent)var1).getFormattedText(), var2)
            .stream()
            .map(var0 -> (Bridge2_42)(new ChatComponentText(var0)))
            .toList()
         : this.listFormattedStringToWidth(((IChatComponent)var1).getFormattedText(), var2)
            .stream()
            .map(var0 -> (Bridge2_42)(new ChatComponentText((String)var0)))
            .toList();
   }

   @Annotation2(min = 0, max = 1)
   @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true)
   private String lunar$renderString(String var1) {
      return ThreadModuleDump63.method4().method40().method41().method9(var1);
   }

   @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), argsOnly = true)
   private String lunar$getStringWidth(String var1) {
      return ThreadModuleDump63.method4().method40() != null ? ThreadModuleDump63.method4().method40().method41().method9(var1) : var1;
   }

   @Override
   public float bridge$getStringWidth(Component var1) {
      Bridge2_42 var2 = AdventureTextBridge.asBridge(var1);
      String var3;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var3 = ((IChatComponent)var2).getFormattedText();
      } else {
         var3 = ((IChatComponent)var2).getFormattedText();
      }

      return this.getStringWidth(var3);
   }

   @Inject(method = {"renderChar$v1_8(CZ)F", "renderCharAtPos$v1_7(ICZ)F"}, at = @At("HEAD"))
   private void lunar$renderChar(CallbackInfoReturnable<Float> var1) {
      if (this.lunar$color != null) {
         int var2 = this.lunar$color.apply(this.posX + this.posY);
         if (this.lunar$shadow) {
            var2 = ThreadModuleDump23.method32(var2);
         }

         this.red = (var2 >> 16 & 0xFF) / 255.0F;
         this.blue = (var2 >> 8 & 0xFF) / 255.0F;
         this.green = (var2 & 0xFF) / 255.0F;
         if (this.textColor != -1) {
            this.textColor = var2;
         } else {
            this.alpha = (var2 >> 24 & 0xFF) / 255.0F;
         }

         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.color(this.red, this.green, this.blue, this.alpha);
         } else {
            GL11.glColor4f(this.red, this.green, this.blue, this.alpha);
         }
      }
   }

   @Override
   public Bridge10$Data bridge$getCodepointCharData(int var1) {
      if (var1 == 32) {
         return new Bridge10$Data(0.0F, 4.0F, true, 32);
      }

      int var2 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000"
         .indexOf((char)var1);
      if (var2 != -1 && !this.unicodeFlag) {
         return new Bridge10$Data(0.0F, ((FontRenderer)this).charWidth[var2 & 0xFF], true, var2);
      }

      byte var3 = this.glyphWidth[var1 & 65535];
      int var4 = var3 >>> 4;
      int var5 = var3 & 15;
      return new Bridge10$Data(var4, var5, false, -1);
   }
}
