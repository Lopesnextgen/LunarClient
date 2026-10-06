package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.Annotation6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump85;
import java.util.function.Predicate;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import org.jetbrains.annotations.NotNull;

public abstract class MixinCore9Base extends HudElementBase {
   private MixinCore5 field9;
   private MixinCore5 field10;
   protected boolean field11 = false;
   private Predicate<Boolean> field12;

   public MixinCore9Base(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) @NotNull HudAnchor var3
   ) {
      super(var1, var2, var3);
   }

   public void method1(MixinCore5 var1) {
      this.field9 = var1;
      this.field10 = var1;
   }

   public void method2(MixinCore5 var1, MixinCore5 var2) {
      this.field9 = var1;
      this.field10 = var2;
   }

   public void method3(Predicate<Boolean> var1) {
      this.field12 = var1;
   }

   @Override
   public final void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      this.field11 = var4;
      MixinCore5 var5 = this.method5(var4);
      var5.clearCache();
      float var6 = var5.getWidth();
      float var7 = var5.getHeight();
      float var8 = this.getScale();
      this.method16(var6, var7);
      boolean var9 = false;
      MarkerModel.Data2 var10 = new MarkerModel.Data2(-9999.0, -9999.0);
      if (ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
         var9 = true;
         var10 = (MarkerModel.Data2)ThreadModuleDump85.get().method5().IHCORIOHOHHOIORHCCOOIIIHOCROOI(var8);
      }

      MarkerModel.Data2 var11 = var1.method3().method5();
      HudRenderContext var12 = new HudRenderContext(var1.method2(), var9, var10, var8, var11.HHHCHORHIHRCOHIOICICICHCRRICCI(), var11.IHRCCHHROHIRCOOOHRRIHOORRHIOHO());
      var5.method1(var2, var3, var12);
      var1.method2().push();
      var1.method2().method40(1.0F / var8, 1.0F / var8);
      var1.method2().method38(0.0F, 0.0F, 300.0F);
      var12.method1();
      var1.method2().pop();
   }

   private MixinCore5 method5(boolean var1) {
      return var1 ? this.field10 : this.field9;
   }

   @OverridingMethodsMustInvokeSuper
   @Override
   public boolean method4(boolean var1) {
      return this.method5(var1) != null && (this.field12 == null || this.field12.test(var1));
   }

   public static MixinCore9Base method7(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final MixinCore5 var3
   ) {
      return new MixinCore9Base(var0, var1, var2) {
         {
            this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var3);
         }
      };
   }

   public static MixinCore9Base method8(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final MixinCore5 var3,
      final MixinCore5 var4
   ) {
      return new MixinCore9Base(var0, var1, var2) {
         {
            this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var3, var4);
         }
      };
   }

   public static MixinCore9Base method9(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final boolean var3,
      final MixinCore5 var4
   ) {
      return new MixinCore9Base(var0, var1, var2) {
         {
            this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var4);
         }

         @Override
         public boolean method31() {
            return var3;
         }
      };
   }

   public static MixinCore9Base method10(
      @Annotation6(method1 = Annotation6.Type.X) float var0,
      @Annotation6(method1 = Annotation6.Type.Y) float var1,
      @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var2,
      final MixinCore5 var3,
      final boolean var4,
      final MixinCore5 var5
   ) {
      return new MixinCore9Base(var0, var1, var2) {
         {
            this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var3, var5);
         }

         @Override
         public boolean method31() {
            return var4;
         }
      };
   }
}
