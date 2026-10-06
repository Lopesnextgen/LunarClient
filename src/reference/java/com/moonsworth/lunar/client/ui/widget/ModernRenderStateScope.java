package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.render.font.CachedFont;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class ModernRenderStateScope implements GuiRenderState {
   private final MixinHelper_4 field1;

   public ModernRenderStateScope(MixinHelper_4 var1) {
      this.field1 = var1;
   }

   @Override
   public void push() {
      this.field1.push();
   }

   @Override
   public void method1(float var1, float var2) {
      this.field1.method40(var1, var2);
   }

   @Override
   public void pop() {
      this.field1.pop();
   }

   @Override
   public void method2(CachedFontImpl var1, Map<ResourceLocationBridge, List<CachedFontImpl.Data2>> var2, int var3) {
      for (Entry var5 : var2.entrySet()) {
         ResourceLocationBridge var6 = (ResourceLocationBridge)var5.getKey();
         List var7 = (List)var5.getValue();
         CachedFont.Data[] var8 = var1.method19(var6);
         RenderLayerBridge var9 = LunarRenderTypes.field33.get(var6);
         CachedFontImpl.Data2 var10 = (CachedFontImpl.Data2)var7.get(0);
         CachedFontImpl.Data2 var11 = (CachedFontImpl.Data2)var7.get(var7.size() - 1);
         CachedFont.Data var12 = var8[var11.character()];
         float var13 = var11.x() - var10.x() + var12.width;
         this.field1.method9(var9, var6, var10.x(), var10.y(), var13, var3, var3x -> {
            for (CachedFontImpl.Data2 var5x : var7) {
               this.method4(var3x, var8[var5x.character()], var5x.x(), var5x.y(), var5x.method1());
            }
         });
      }
   }

   @Override
   public void method3(List<CachedFontImpl.Data> var1) {
      for (CachedFontImpl.Data var3 : var1) {
         CachedFont.Data var4 = var3.method3();
         double var5 = var3.method1();
         double var7 = var3.method2();
         double var9 = var3.method4() ? var4.height / 2.0F : var4.height - 2.0;
         LcuiScreen.method94(this.field1, (float)var5, (float)(var7 + var9), var4.width - 8, 1.0F, var3.method5());
      }
   }

   private void method4(Bridge2_32 var1, CachedFont.Data var2, float var3, float var4, int var5) {
      int var6 = var2.width;
      int var7 = var2.height;
      var1.method2(var3, var4, 0.0).method10(var2.field3, var2.field4).method9(var5).method16();
      var1.method2(var3, var4 + var7, 0.0).method10(var2.field3, var2.field6).method9(var5).method16();
      var1.method2(var3 + var6, var4 + var7, 0.0).method10(var2.field5, var2.field6).method9(var5).method16();
      var1.method2(var3 + var6, var4, 0.0).method10(var2.field5, var2.field4).method9(var5).method16();
   }
}
