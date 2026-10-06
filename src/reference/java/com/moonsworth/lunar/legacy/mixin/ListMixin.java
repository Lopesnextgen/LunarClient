package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.calculator.CalculatorType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Map;
import net.minecraft.client.gui.GuiLanguage.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.Language_v1_7;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(List.class)
public abstract class ListMixin {
   @Final
   @Shadow
   public java.util.List field_148176_l$v1_7;
   @Final
   @Shadow
   public java.util.List<String> langCodeList;
   @Final
   @Shadow
   public Map<String, Language> languageMap;
   @Final
   @Shadow
   public Map field_148177_m$v1_7;

   @Annotation2(min = 5)
   @Inject(method = "drawSlot$v1_12", at = @At("TAIL"))
   private void lunar$drawSlot$v1_12(int var1, int var2, int var3, int var4, int var5, int var6, float var7, CallbackInfo var8) {
      this.lunar$onDrawSlot(var1, var2, var3);
   }

   @Annotation2(1)
   @Inject(method = "drawSlot$v1_8", at = @At("TAIL"))
   private void impl$drawSlot$v1_8(int var1, int var2, int var3, int var4, int var5, int var6, CallbackInfo var7) {
      this.lunar$onDrawSlot(var1, var2, var3);
   }

   @Annotation2(max = 0)
   @Inject(method = "drawSlot$v1_7", at = @At("TAIL"))
   private void lunar$onDrawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7, CallbackInfo var8) {
      this.lunar$onDrawSlot(var1, var2, var3);
   }

   @Unique
   private void lunar$onDrawSlot(int var1, int var2, int var3) {
      String var4;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Language var5 = this.languageMap.get(this.langCodeList.get(var1));
         var4 = var5.languageCode;
      } else {
         Language_v1_7 var10 = (Language_v1_7)this.field_148177_m$v1_7.get(this.field_148176_l$v1_7.get(var1));
         var4 = var10.languageCode;
      }

      boolean var11 = false;

      for (CalculatorType var9 : CalculatorType.values()) {
         if (var9.getFileName().equals(var4)) {
            var11 = true;
            break;
         }
      }

      if (var11) {
         LcuiScreen.method30(AbstractRenderContext.method32(), CosmeticManager.field39, var2, var3, 14.0F, 14.0F, -1);
      }
   }
}
