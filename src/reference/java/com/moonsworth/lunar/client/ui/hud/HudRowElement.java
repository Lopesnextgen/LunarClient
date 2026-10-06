package com.moonsworth.lunar.client.ui.hud;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox2;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.Annotation6;
import com.moonsworth.lunar.client.util.ThreadModuleDump60;
import java.util.List;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public abstract class HudRowElement extends HudElementBase {
   private List<Hitbox2> field9;

   public HudRowElement(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) @NotNull HudAnchor var3
   ) {
      super(var1, var2, var3);
   }

   @Override
   public final void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      this.method2(var1.method2(), var2, var3, var4, ImmutableList.copyOf(this.field9));
   }

   public abstract void method2(MixinHelper_4 var1, float var2, float var3, boolean var4, List<Hitbox2> var5);

   protected final void method3(
      MixinHelper_4 var1,
      float var2,
      float var3,
      boolean var4,
      @NotNull HudPlacement var5,
      boolean var6,
      @NotNull ColorOption var7,
      boolean var8,
      float var9,
      @NotNull ColorOption var10
   ) {
      com.moonsworth.lunar.client.ui.hud.row.Gui2Extension var11 = Hitbox.method3(var4, this.method26(), var5);
      ThreadModuleDump60 var12 = Hitbox.method2(var1, this, var2, var3, var11, var6, var7, var8, var9, var10, this.field9);
      this.method16(var12.x, var12.y);
   }

   @Nullable
   protected abstract List<Hitbox2> method5(boolean var1);

   @OverridingMethodsMustInvokeSuper
   @Override
   public boolean method4(boolean var1) {
      this.field9 = this.method5(var1);
      return this.field9 != null && !this.field9.isEmpty();
   }
}
