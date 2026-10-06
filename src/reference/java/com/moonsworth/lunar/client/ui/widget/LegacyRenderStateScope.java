package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.render.font.CachedFont;

public class LegacyRenderStateScope implements GuiRenderState {
   private final AbstractRenderContext field1;

   public LegacyRenderStateScope(AbstractRenderContext var1) {
      this.field1 = var1;
   }

   @Override
   public void push() {
      this.field1.push();
   }

   @Override
   public void method1(float var1, float var2) {
      this.field1.scale(var1, var2, 1.0F);
   }

   @Override
   public void pop() {
      this.field1.pop();
   }

   @Override
   public void method2(CachedFontImpl var1, Map<ResourceLocationBridge, List<CachedFontImpl.Data2>> var2, int var3) {
      for (Entry var5 : var2.entrySet()) {
         ResourceLocationBridge var6 = (ResourceLocationBridge)var5.getKey();
         CachedFont.Data[] var7 = var1.method19(var6);
         RenderLayerBridge var8 = LunarRenderTypes.field33.get(var6);
         Bridge2_32 var9 = this.field1.method10(var8);
         var9.method1();

         for (CachedFontImpl.Data2 var11 : (List)var5.getValue()) {
            this.method5(var9, var7[var11.character()], var11.x(), var11.y(), var11.method1());
         }

         var9.method17(BufferBuildMode.BATCHED);
      }
   }

   @Override
   public void method3(List<CachedFontImpl.Data> var1) {
      if (!var1.isEmpty()) {
         Bridge2_32 var2 = this.field1.method10(LunarRenderTypes.field29);
         var2.method1();

         for (CachedFontImpl.Data var4 : var1) {
            CachedFont.Data var5 = var4.method3();
            double var6 = var4.method1();
            double var8 = var4.method2();
            double var10 = var4.method4() ? var5.height / 2.0F : var5.height - 2.0;
            this.method4(var2, var6, var8 + var10, var6 + var5.width - 8.0, var8 + var10, 1.0F, var4.method5());
         }

         var2.method17(BufferBuildMode.BATCHED);
      }
   }

   private void method4(Bridge2_32 var1, double var2, double var4, double var6, double var8, float var10, int var11) {
      var1.method6(var2, var4).method9(var11).method16();
      var1.method6(var2, var8 + var10).method9(var11).method16();
      var1.method6(var6, var8 + var10).method9(var11).method16();
      var1.method6(var6, var4).method9(var11).method16();
   }

   private void method5(Bridge2_32 var1, CachedFont.Data var2, float var3, float var4, int var5) {
      int var6 = var2.width;
      int var7 = var2.height;
      var1.method5(var3, var4).method10(var2.field3, var2.field4).method9(var5).method16();
      var1.method5(var3, var4 + var7).method10(var2.field3, var2.field6).method9(var5).method16();
      var1.method5(var3 + var6, var4 + var7).method10(var2.field5, var2.field6).method9(var5).method16();
      var1.method5(var3 + var6, var4).method10(var2.field5, var2.field4).method9(var5).method16();
   }
}
