package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.Coordinates;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class SoundListOptionWidget extends OptionWidget<SoundOption> implements EditState {
   private static final float field16 = 14.0F;
   private static final float field17 = 12.0F;
   private static final float field18 = 48.0F;
   private static final float field19 = 13.0F;
   private static final float field20 = 7.0F;
   private static final float field21 = 4.0F;
   private static final float field22 = 5.0F;
   private static final float field23 = 5.0F;
   private static final float field24 = 12.0F;
   private static final int field25 = 536870912;
   private static final int field26 = 419430399;
   private static final int field27 = 285212671;
   private static final ResourceLocationBridge field28 = ResourceLocationBridge.create("lunar", "icons/assets/magnifying-glass-12x12.png");
   private static final ResourceLocationBridge field29 = ResourceLocationBridge.create("lunar", "icons/assets/play-16x16.png");
   private static final ResourceLocationBridge field30 = ResourceLocationBridge.create("lunar", "icons/folder-64x64.png");
   private static final ResourceLocationBridge field31 = ResourceLocationBridge.create("lunar", "icons/assets/arrow-down-17x17.png");
   private static final ResourceLocationBridge field32 = ResourceLocationBridge.create("lunar", "icons/assets/arrow-up-17x17.png");
   private static final ResourceLocationBridge field33 = ResourceLocationBridge.create("lunar", "icons/assets/accept-16x16.png");
   private final IconTextButton field34;
   private final DropdownWidget field35;
   private final NumberSliderWidget<Integer> field36;
   private final TextLabelWidget field37;
   private final TextLabelWidget field38;
   private final TextLabelWidget field39;
   private boolean field40;
   private boolean field41;
   private boolean field42;
   private List<String> field43 = new ArrayList<>();
   private final List<SoundListOptionWidget.Data> field44 = new ArrayList<>();

   public SoundListOptionWidget(SoundOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field35 = new DropdownWidget(this);
      this.field34 = new IconTextButton(this, field28, FontRegistry.method17(), method19("searchSounds"), 544831865, 1081702777);
      this.field34.field34 = () -> this.method3(this.field34.getText());
      this.field36 = new NumberSliderWidget<>(var1.method8(), this);
      this.field37 = this.method1(field29, method19("preview"), () -> Coordinates.method1(var1));
      this.field38 = this.method1(field30, method19("openFolder"), () -> Bridge.method8().method64(Coordinates.method9().toURI()));
      this.field39 = this.method1(LcuiScreen.field1, method19("refresh"), this::method2);
      this.method2();
   }

   private TextLabelWidget method1(ResourceLocationBridge var1, String var2, Runnable var3) {
      TextLabelWidget var4 = new TextLabelWidget(this, "", FontRegistry.method9());
      var4.method7(var1);
      var4.method10(7.0F);
      var4.method17(var2, new Object[0]);
      var4.method4((var2x, var3x) -> {
         if (var4.method3(var2x)) {
            var3.run();
            return true;
         } else {
            return false;
         }
      });
      return var4;
   }

   private void method2() {
      Coordinates.method12();
      this.field43 = Coordinates.method10();
      this.method3(this.field34.getText());
   }

   private void method3(@Nullable String var1) {
      this.field44.clear();
      String var2 = var1 == null ? "" : var1.toLowerCase();
      String var3 = (String)this.getOption().get();

      for (String var5 : this.field43) {
         if (var2.isEmpty() || var5.toLowerCase().contains(var2)) {
            this.field44.add(new SoundListOptionWidget.Data(this, var5, var5.equals(var3)));
         }
      }

      if (this.width > 0.0F) {
         this.method6();
      }
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      this.method2(var1, var2, var3 - 5.0F, this.getHeight());
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      float var5 = var1 + 5.0F;
      float var6 = var3 - 10.0F;
      float var7 = var2 + 14.0F + 5.0F;
      float var8 = var6 - 51.0F;
      this.field34.method2(var5, var7, var8, 13.0F);
      float var9 = var5 + var8 + 4.0F;
      var9 = this.method7(this.field37, var9, var7);
      var9 = this.method7(this.field38, var9, var7);
      this.method7(this.field39, var9, var7);
      float var10 = var7 + 13.0F + 4.0F;
      this.field35.method2(var5 + var6 - 4.0F, var10, 4.0F, 48.0F);
      this.method6();
      float var11 = var10 + 48.0F + 4.0F;
      float var12 = FontRegistry.field9.method4(this.getOption().method8().getName());
      float var13 = FontRegistry.field9.method4("100");
      this.field36.method2(var5 + var12 + 2.0F, var11, var6 - var12 - var13 - 4.0F, 12.0F);
   }

   private void method6() {
      float var1 = this.x + 5.0F;
      float var2 = this.field35.getX() - var1;
      int var3 = 0;

      for (SoundListOptionWidget.Data var5 : this.field44) {
         var5.method2(var1, this.field35.getY() + var3 * 12.0F, var2, 12.0F);
         var3++;
      }
   }

   private float method7(TextLabelWidget var1, float var2, float var3) {
      var1.method2(var2, var3, 13.0F, 13.0F);
      return var2 + 13.0F + 4.0F;
   }

   @Override
   public float getHeight() {
      if (this.getOption().isHidden()) {
         return 0.0F;
      } else {
         return !this.field40 ? 14.0F : 105.0F;
      }
   }

   @Override
   public boolean method3() {
      return false;
   }

   @Override
   public void update() {
      this.field36.update();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.method11(var1, var2, var3);
      if (!this.field40) {
         this.field42 = false;
      } else {
         LcuiScreen.method117(var1, this.x, this.y + 14.0F, this.width, this.getHeight() - 14.0F, 4.0F, 536870912);
         this.field34.method3(var1, var2, var3);
         float var4 = this.field35.getY();
         this.field42 = var3 && this.method3(var2) && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= var4 && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < var4 + 48.0F;
         var1.push();
         LcuiScreen.method111(var1, this.x + 5.0F, var4, this.width - 10.0F, 48.0F, 1.0F);
         this.field35.method15(12.0F * this.field44.size());
         this.field35.method5(var1, var2, var3);
         MarkerModel.Data2 var5 = this.field35.method4(var2);

         for (SoundListOptionWidget.Data var7 : this.field44) {
            boolean var8 = var7.getY() + var7.getHeight() + this.field35.method3() < var4;
            boolean var9 = var7.getY() + this.field35.method3() > var4 + 48.0F;
            if (!var8 && !var9) {
               var7.method3(var1, var5, true);
            }
         }

         this.field35.method7(var1, var2, var3 && var2.method9() > this.field35.getX());
         LcuiScreen.method112(var1);
         var1.pop();
         this.field37.method3(var1, var2, var3);
         this.field38.method3(var1, var2, var3);
         this.field39.method3(var1, var2, var3);
         this.method10(var1, var2, var3);
      }
   }

   private void method10(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = this.field35.getY() + 48.0F + 4.0F;
      FontRegistry.field9.method13(var1, this.getOption().method8().getName(), this.x + 5.0F, var4 + 1.0F, -4079426);
      this.field36.method3(var1, var2, var3);
      String var5 = this.getOption().method8().getValueAsString();
      FontRegistry.field9.method13(var1, var5, this.x + this.width - 5.0F - FontRegistry.field9.method4(var5), var4 + 1.0F, -4079426);
      boolean var6 = this.field36.method15();
      if (this.field41 && !var6) {
         Coordinates.method1(this.getOption());
      }

      this.field41 = var6;
   }

   private void method11(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.field9.method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      float var4 = FontRegistry.field9.method4(this.option.getName());
      float var5 = this.width - 24.0F - var4 - 10.0F;
      String var6 = this.method14();
      if (FontRegistry.field9.method4(var6) > var5) {
         var6 = "..." + FontRegistry.field9.method22(var6, var5 - FontRegistry.field9.method4("..."), true);
      }

      FontRegistry.field9.method13(var1, var6, this.x + this.width - 24.0F - FontRegistry.field9.method4(var6), this.y + 2.5F, -2134785858);
      if (!this.option.isDefault()) {
         boolean var7 = var3 && this.method13(var2);
         int var8 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var7 ? 0.9F : 0.65F);
         LcuiScreen.method31(var1, LcuiScreen.field1, this.x + this.width - 19.0F, this.y + 4.0F, 6.0F, 6.0F, var8);
      }

      LcuiScreen.method31(var1, this.field40 ? field32 : field31, this.x + this.width - 9.0F, this.y + 3.5F, 7.0F, 7.0F, -1593835521);
   }

   private String method14() {
      if (this.getOption().method7()) {
         return method19("none");
      }

      String var1 = (String)this.getOption().get();
      if (!this.getOption().method8().isDefault()) {
         var1 = var1 + " · " + this.getOption().method8().get() + "%";
      }

      return var1;
   }

   private boolean method13(MarkerModel.Data2 var1) {
      return this.method3(var1)
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 14.0F
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.x + this.width - 21.0F
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < this.x + this.width - 12.0F;
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field40 && this.field42 && this.field35.method5(var1);
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      if (this.method3(var1) && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 14.0F) {
         if (this.method13(var1) && !this.option.isDefault()) {
            this.method7();
            return true;
         }

         this.field40 = !this.field40;
         if (!this.field40) {
            this.field34.method17(false);
         }

         this.method16();
         return true;
      } else {
         if (!this.field40) {
            return super.method6(var1, var2);
         }

         if (this.field34.method3(var1)) {
            return this.field34.method6(var1, var2);
         }

         this.field34.method17(false);
         if (this.field37.method6(var1, var2)
            || this.field38.method6(var1, var2)
            || this.field39.method6(var1, var2)) {
            return true;
         }

         if (this.field36.method6(var1)) {
            return this.field36.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
         }

         if (this.field35.method1(var1) && this.field35.method6(var1, var2)) {
            return true;
         }

         if (var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= this.field35.getY() && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.field35.getY() + 48.0F) {
            MarkerModel.Data2 var3 = this.field35.method4(var1);

            for (SoundListOptionWidget.Data var5 : this.field44) {
               if (var5.method3(var3) && var5.method6(var3, var2)) {
                  return true;
               }
            }
         }

         return super.method6(var1, var2);
      }
   }

   private void method16() {
      GuiWidget var1 = this.field4;

      while (var1 != null && !(var1 instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget)) {
         var1 = var1.method12().orElse(null);
      }

      GuiWidget var2 = var1 != null ? var1 : this.field4;
      if (var2 != null) {
         var2.method2(var2.getX(), var2.getY(), var2.getWidth(), var2.getHeight());
         if (this.field40 && var1 != null) {
            ((com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget)var1).method14(this);
         }
      }
   }

   @Override
   public boolean method8(MarkerModel.Data2 var1, int var2) {
      if (!this.field34.method3(var1)) {
         this.field34.method17(false);
      }

      return super.method2(var1, var2);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field34.method18()) {
         this.field34.method4(var1, var2);
      }
   }

   @Override
   public void close() {
   }

   @Override
   public boolean isEditing() {
      return this.field40 && this.field34.isEditing();
   }

   private static String method19(String var0) {
      return ThreadModuleDump63.method4().method67().method2("gui.components", var0, new Object[0]);
   }

   @Generated
   public boolean isExtended() {
      return this.field40;
   }

   private class Data extends GuiWidget {
      private final String field16;
      private final String field17;
      private final boolean field18;
      private final CachedFontImpl field19 = FontRegistry.method17();

      private Data(GuiWidget var2, String var3, boolean var4) {
         super(var2);
         this.field16 = var3;
         this.field17 = "none".equals(var3) ? SoundListOptionWidget.method19("none") : var3;
         this.field18 = var4;
      }

      @Override
      public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
         boolean var4 = var3 && this.method3(var2);
         if (this.field18) {
            LcuiScreen.method117(var1, this.x, this.y, this.width, this.height, 3.0F, 419430399);
            LcuiScreen.method31(var1, SoundListOptionWidget.field33, this.x + 4.0F, this.y + 4.0F, this.height - 8.0F, this.height - 8.0F, -863961216);
         } else if (var4) {
            LcuiScreen.method117(var1, this.x, this.y, this.width, this.height, 3.0F, 285212671);
         }

         this.field19
            .method13(var1, this.field17, this.x + this.height, this.y + this.height / 2.0F - (this.field19.getHeight() + 4.0F) / 2.0F, var4 ? -1 : -1342177281);
      }

      @Override
      public boolean method6(MarkerModel.Data2 var1, int var2) {
         SoundListOptionWidget.this.getOption().OIRHOOIICOCIOOHICRRRICORIHHIHC(this.field16);
         SoundListOptionWidget.this.method3(SoundListOptionWidget.this.field34.getText());
         return true;
      }

      @Override
      public void update() {
      }

      @Override
      public void method4(char var1, KeyCode var2) {
      }

      @Override
      public void close() {
      }
   }
}
