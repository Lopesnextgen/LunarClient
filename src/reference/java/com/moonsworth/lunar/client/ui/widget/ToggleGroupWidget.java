package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.DefaultedBoolean;
import com.moonsworth.lunar.client.config.option.TriStateOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class ToggleGroupWidget extends OptionWidget<TriStateOption> {
   private static final DefaultedBoolean[] field16 = DefaultedBoolean.values();
   private static final float field17 = 40.0F;
   private static final float field18 = 10.0F;
   private static final float field19 = 40.0F / field16.length;
   private final HoverAnimation field20;
   private final boolean field21;
   private int field22;

   public ToggleGroupWidget(TriStateOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field21 = var1.method7();
      this.field20 = new HoverAnimation(100L);
      this.field20.start();
      this.field22 = ((DefaultedBoolean)var1.get()).ordinal();
      this.method4((var2x, var3) -> {
         DefaultedBoolean var4 = (DefaultedBoolean)var1.get();
         DefaultedBoolean var5 = field16[(var4.ordinal() + 1) % field16.length];
         if (var2x.method9() >= this.x && var2x.method9() < this.x + 40.0F) {
            int var6 = (int)((var2x.method9() - this.x) / field19);
            var5 = field16[Math.min(var6, field16.length - 1)];
         }

         if (var5 != var4) {
            var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var5);
            this.field22 = var4.ordinal();
            this.field20.start();
         }

         return true;
      });
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method2(var1, var2, var3, this.getHeight());
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public boolean method3() {
      return this.field21;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x + 40.0F + 6.0F, this.y + 1.5F, -4079426);
      LcuiScreen.method101(var1, this.x, this.y + 2.0F, 40.0F, 10.0F, 5.0F, var3 && this.method3(var2) ? 1088611042 : 551740130, true, true, true, true);
      LcuiScreen.method51(var1, this.x + 1.0F, this.y + 3.0F, 38.0F, 8.0F, 2.5F, 905969663, true, true, true, true);
      DefaultedBoolean var4 = (DefaultedBoolean)this.option.get();
      float var5 = this.field22 + (var4.ordinal() - this.field22) * this.field20.method9();
      float var6 = this.x + var5 * field19;
      LcuiScreen.method101(var1, var6, this.y + 2.0F, field19, 10.0F, 5.0F, this.method6(var4), true, true, true, true);
      LcuiScreen.method51(var1, var6 + 1.0F, this.y + 3.0F, field19 - 2.0F, 8.0F, 2.5F, 905969663, true, true, true, true);

      for (int var7 = 0; var7 < field16.length; var7++) {
         DefaultedBoolean var8 = field16[var7];
         CachedFontImpl var9 = var8 == DefaultedBoolean.DEFAULT ? FontRegistry.method9() : FontRegistry.method6();
         var9.method14(
            var1,
            this.method5(var8),
            this.x + var7 * field19 + field19 / 2.0F,
            this.y + (var8 == DefaultedBoolean.DEFAULT ? 3.0F : 3.6F),
            var8 == var4 ? -1 : 1358954495
         );
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      String var3 = this.option.method8();
      if (var3 != null && var2.method9() >= this.x + field19 && var2.method9() < this.x + field19 * 2.0F) {
         this.method4(var1, var2, this.option.OHROCHICOIOICHOCRROORRCIIICIHO(var3, new Object[0]));
      } else {
         super.method3(var1, var2);
      }
   }

   private String method5(DefaultedBoolean var1) {
      return switch (var1) {
         case FALSE -> this.method5("off", new Object[0]);
         case DEFAULT -> "/";
         case TRUE -> this.method5("on", new Object[0]);
         default -> throw new IncompatibleClassChangeError();
      };
   }

   private int method6(DefaultedBoolean var1) {
      return switch (var1) {
         case FALSE -> -1344396974;
         case DEFAULT -> -1349611890;
         case TRUE -> -1356212614;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
