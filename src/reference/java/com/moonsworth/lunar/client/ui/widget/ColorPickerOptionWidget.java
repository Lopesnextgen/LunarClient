package com.moonsworth.lunar.client.ui.widget;

import com.google.common.collect.Iterables;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.Generated;

public class ColorPickerOptionWidget extends OptionWidget<ColorOption> implements EditState {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "ui/components/hue_selector.png");
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "ui/components/alpha_selector.png");
   private static final int[] field18 = new int[]{
      -5636096, -43691, -22016, -171, -16733696, -11141291, -11141121, -16733526, -16777046, -11184641, -43521, -5635926, -1, -5592406
   };
   private boolean field19;
   private List<ColorPickerWidget> field20;
   private List<ColorPickerWidget> field21;
   private final ArrayDeque<ColorPickerWidget> field22;
   private String field23 = "";
   private CommandFieldWidget field24;
   private CheckboxWidget field25;
   private CycleOptionWidget<Gui2Extension> field26;
   private NumberSliderWidget<Integer> field27;
   private boolean field28 = false;
   private float field29 = -1.0F;
   private final BufferedImage field30 = new BufferedImage(114, 60, 1);
   private final ResourceLocationBridge field31 = ResourceLocationBridge.create("lunar", "dynamic_hue_image_" + this.option.getId());
   private boolean field32;
   private boolean field33;
   private boolean field34;
   private boolean field35;

   public ColorPickerOptionWidget(ColorOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field24 = new CommandFieldWidget(this, "");
      this.field25 = new CheckboxWidget(var1.method19(), this);
      this.field26 = new CycleOptionWidget(var1.method23(), this);
      this.field27 = new NumberSliderWidget<>(var1.method21(), this);
      this.field20 = new ArrayList<>(12);
      this.field21 = new ArrayList<>(12);
      this.field22 = new ArrayDeque<>(6);
      this.field20.clear();

      for (ColorOption var4 : Client.method109().method49().method3()) {
         this.field20.add(new ColorPickerWidget(this, var4));
      }

      for (int var5 = 0; var5 < 12; var5++) {
         this.field21
            .add(
               new ColorPickerWidget(
                  this,
                  (ColorOption)((Data)OptionFactory.method8("def" + var5).method4(field18[var5])).method31()
               )
            );
      }

      this.method3(
         (var1x, var2x) -> {
            boolean var3 = var1x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y
               && var1x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 14.0F
               && var1x.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 68.0F
               && var1x.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.width - 20.0F;
            if (this.field24.isActive() && !var3) {
               this.field24.method14(() -> false);
               this.field24.method8().accept(false);
            }

            return false;
         }
      );
      this.method4(this::method1);
   }

   protected boolean method1(MarkerModel.Data2 var1, int var2) {
      boolean var3 = this.method3(var1)
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 14.0F
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 10.0F;
      boolean var4 = !var3
         && this.method3(var1)
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 14.0F
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 20.0F;
      boolean var5 = !var3
         && !var4
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 14.0F
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + this.width - 68.0F;
      if (!this.field24.isActive() && var5) {
         this.field24.method1(this.field24.method10().get(), true);
         this.field24.method8().accept(true);
         this.field24.method14(() -> true);
         this.field24.setActive(true);
         return true;
      }

      if (var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 14.0F && !var3 && !var4 && !var5) {
         this.field19 = !this.field19;
         this.field32 = true;
         if (this.field4 != null) {
            this.field4
               .method2(
                  this.field4.getX(),
                  this.field4.getY(),
                  this.field4.getWidth(),
                  this.field4.getHeight()
               );
         }

         return true;
      } else {
         for (ColorPickerWidget var7 : this.field21) {
            if (var7.method3(var1)) {
               return var7.method6(var1, var2);
            }
         }

         for (ColorPickerWidget var12 : this.field22) {
            if (var12.method3(var1)) {
               return var12.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
            }
         }

         for (ColorPickerWidget var13 : this.field20) {
            if (var13.method3(var1)) {
               return var13.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
            }
         }

         if (var3) {
            this.method7();
         } else {
            if (var4) {
               boolean var11 = false;

               for (ColorPickerWidget var8 : this.field20) {
                  if (var8.field16.method2(this.option)) {
                     var11 = true;
                     break;
                  }
               }

               if (var11) {
                  Client.method109().method49().method3().removeIf(var1x -> var1x.method2(this.option));
                  Client.method109().method49().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
                  Client.method109().method49().method10();
                  this.method1(this.x, this.y, this.width);
                  return true;
               }

               if (Client.method109().method49().method3().size() >= 12) {
                  Client.method109()
                     .method49()
                     .method3()
                     .remove(Iterables.getFirst(Client.method109().method49().method3(), null));
               }

               Client.method109().method49().method4(this.option);
               this.method1(this.x, this.y, this.width);
               return true;
            }

            if (this.option.method9()) {
               if (this.field25.method3(var1)) {
                  return this.field25.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
               }

               if (this.field26.method3(var1)) {
                  return this.field26.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
               }

               if (this.field27.method6(var1)) {
                  return this.field27.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1, var2);
               }
            }
         }

         return false;
      }
   }

   public void method2(ColorPickerWidget var1) {
      if (!var1.field16.method2(this.option)) {
         if (this.field22.size() >= 6) {
            this.field22.poll();
         }

         this.option.method1(var1.method1(0.0F));
         this.option.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)var1.field16.method19().get());
         this.option.method21().method1((Integer)var1.field16.method21().get());
         this.option.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC((Gui2Extension)var1.field16.method23().get());
         this.field22.add(var1.method2());
         this.method1(this.x, this.y, this.width);
      }
   }

   private void method3(ColorOption var1, ColorOption var2) {
      var1.method1(var2.method13());
      var1.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC((Boolean)var2.method19().get());
      var1.method21().method1((Integer)var2.method21().get());
      var1.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC((Gui2Extension)var2.method23().get());
   }

   @Override
   public void method1(float var1, float var2, float var3) {
      super.method1(var1, var2, var3);
      this.field20.clear();

      for (ColorOption var5 : Client.method109().method49().method3()) {
         this.field20.add(new ColorPickerWidget(this, var5));
      }

      this.field24.method2(var1 + var3 - 68.0F, var2 + 2.5F, 45.0F, 10.0F);
      this.field24.setActive(false);
      this.field24.method17(() -> this.field23.toUpperCase(Locale.ROOT));
      this.field24.method25().RRORIIRROCCRRRRIIIIOIIRRIHICIH(8);
      this.field24.method25().method39(var0 -> var0.toUpperCase(Locale.ROOT).replaceAll("[^0-9A-F]", ""));
      this.field24
         .method15(
            var4 -> {
               if (!var4) {
                  ColorOption var5x = (ColorOption)((Data)OptionFactory.method8("recent").method4(this.option.method13()))
                     .method31();
                  this.method3(var5x, this.option);
                  ColorPickerWidget var6x = new ColorPickerWidget(this, var5x);
                  if (!this.field22.isEmpty() && this.field22.peekLast().field16.method14(0.0F).equals(var6x.field16.method14(0.0F))) {
                     return;
                  }

                  if (this.field22.size() >= 6) {
                     this.field22.poll();
                  }

                  this.field22.add(var6x);
                  this.method1(var1, var2, var3);
               }
            }
         );
      this.field24.method18(var1x -> {
         var1x = var1x.toUpperCase(Locale.ROOT).replaceAll("[^0-9A-F]", "");
         if (var1x.length() > 8) {
            var1x = var1x.substring(0, 8);
         }

         if (!var1x.equalsIgnoreCase(this.field23)) {
            this.field23 = var1x;

            try {
               StringBuilder var2x = new StringBuilder();

               for (int var3x = 0; var3x < 8; var3x++) {
                  if (var3x < var1x.length()) {
                     var2x.append(var1x.charAt(var3x));
                  } else {
                     var2x.append(0);
                  }
               }

               int var6x = (int)Long.parseLong(var2x.toString(), 16);
               if (!this.option.method10()) {
                  var6x = var6x & 16777215 | 0xFF000000;
               }

               this.option.method1(var6x);
            } catch (NumberFormatException var4) {
               var4.printStackTrace();
            }
         }
      });
      if (this.option.method9()) {
         this.field25.method2(var1 + 7.0F, var2 + 17.0F, 110.0F, 12.0F);
         this.field26.method1(var1 + 7.0F, var2 + 43.0F, 110.0F);
         this.field27.method2(var1 + 61.0F, var2 + 30.0F, 43.0F, 12.0F);
      }

      int var8 = 0;

      for (int var10 = 0; var10 < 6; var10++) {
         for (int var6 = 0; var6 < 2; var6++) {
            ColorPickerWidget var7 = this.field21.get(var8);
            var7.method2(var1 + 271.5F + 10.0F * var6, var2 + 15.0F + 10.0F * var10, 8.0F, 8.0F);
            var8++;
         }
      }

      int var11 = 0;

      for (ColorPickerWidget var15 : this.field22) {
         var15.method2(var1 + 301.0F, var2 + 15.0F + 10.0F * var11, 8.0F, 8.0F);
         var11++;
      }

      var8 = 0;

      for (int var12 = 0; var12 < 6; var12++) {
         for (int var14 = 0; var14 < 2; var14++) {
            if (this.field20.size() > var8) {
               ColorPickerWidget var16 = this.field20.get(var8);
               var16.method2(var1 + 318.5F + 10.0F * var14, var2 + 15.0F + 10.0F * var12, 8.0F, 8.0F);
               var8++;
            }
         }
      }
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : (this.field19 ? 80.0F : 14.0F);
   }

   @Override
   public void update() {
      this.field24.update();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (!this.field28 || this.option.ORHCIRCROHHHOICIRIICIICOORORIR() != this.field29) {
         for (int var4 = 0; var4 < this.field30.getWidth(); var4++) {
            for (int var5 = 0; var5 < this.field30.getHeight(); var5++) {
               float var6 = (float)var4 / this.field30.getWidth();
               float var7 = 1.0F - (float)var5 / this.field30.getHeight();
               int var8 = Color.HSBtoRGB(this.option.ORHCIRCROHHHOICIRIICIICOORORIR(), var6, var7);
               if (ThreadModuleDump63.MC_VERSION >= 7) {
                  var8 = ThreadModuleDump23.method17(var8);
               }

               this.field30.setRGB(var4, var5, var8);
            }
         }

         Bridge8Extension33 var29 = Bridge.method8().method22(this.field30);
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(this.field31, var29);
         this.field29 = this.option.ORHCIRCROHHHOICIRIICIICOORORIR();
         this.field28 = true;
      }

      FontRegistry.field9.method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      if (this.field24.isActive()) {
         this.field24.method3(var1, var2, var3);
      } else {
         this.field23 = String.format("%08x", this.option.method13());
         FontRegistry.field9.method13(var1, "#" + this.field23, this.x + this.width - 68.0F, this.y + 2.5F, -2134785858);
      }

      float var30 = this.x + this.width - 84.0F;
      float var31 = this.y + 2.0F;
      LcuiScreen.method101(var1, var30, var31, 10.0F, 10.0F, 8.0F, this.option.method14(var30 + var31), true, true, true, true);
      LcuiScreen.method51(var1, this.x + this.width - 83.0F, this.y + 3.0F, 8.0F, 8.0F, 3.0F, -1342177281, true, true, true, true);
      boolean var32 = false;

      for (ColorPickerWidget var35 : this.field20) {
         if (var35.field16.method2(this.option)) {
            var32 = true;
            break;
         }
      }

      boolean var34 = this.method3(var2);
      boolean var36 = var34
         && var3
         && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 14.0F
         && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.x + this.width - 10.0F;
      boolean var9 = !var36
         && var34
         && var3
         && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + 14.0F
         && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.x + this.width - 20.0F;
      if (!this.option.isDefault()) {
         int var10 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var36 ? 0.9F : 0.65F);
         LcuiScreen.method31(var1, LcuiScreen.field1, this.x + this.width - 8.0F, this.y + 4.0F, 6.0F, 6.0F, var10);
      }

      int var37 = ThreadModuleDump23.method11(var32 ? 0.8F : 0.0F, var32 ? 0.8F : 0.0F, 0.0F, var9 ? 0.8F : 0.5F);
      LcuiScreen.method31(var1, LcuiScreen.field2, this.x + this.width - 18.0F, this.y + 4.0F, 6.0F, 6.0F, var37);
      if (this.field19) {
         Bridge.method42().method56();
         if (this.option.method9()) {
            LcuiScreen.method117(var1, this.x, this.y + 14.0F, 112.0F, this.height - 20.0F, 4.0F, 536870912);
            FontRegistry.field9
               .method13(
                  var1,
                  this.option.method21().OHROCHICOIOICHOCRROORRCIIICIHO(this.option.method21().getId(), new Object[0]),
                  this.x + 7.0F,
                  this.y + 31.0F,
                  -4079426
               );
            this.field25.method3(var1, var2, var3);
            this.field26.method3(var1, var2, var3);
            this.field27.method3(var1, var2, var3);
            String var11 = this.option.method21().getValueAsString();
            float var12 = FontRegistry.field9.method4(var11);
            FontRegistry.field9.method13(var1, this.option.method21().getValueAsString(), this.x + 59.0F - var12, this.y + 31.0F, -4079426);
         }

         float var38 = this.x + 118.0F;
         float var39 = this.y + 14.0F;
         float var13 = 114.0F;
         float var14 = this.height - 20.0F;
         LcuiScreen.method117(var1, var38, this.y + 14.0F, var13, var14, 4.0F, -1);
         LcuiScreen.method46(var1, this.field31, var38 + 1.0F, var39 + 1.0F, 0.0F, 0.0F, (int)var13 - 2, (int)var14 - 2, var13, var14, -1);
         int var15 = (int)(var2.HHHCHORHIHRCOHIOICICICHCRRICCI() - var38);
         int var16 = (int)(var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - var39);
         float var17 = var15 / (var13 - 2.0F);
         float var18 = 1.0F - var16 / (var14 - 2.0F);
         boolean var19 = var17 >= 0.0 && var17 <= 1.0 && var18 >= 0.0 && var18 <= 1.0;
         boolean var20 = Bridge.method20().method1(0);
         if (!this.field32 && var20 && var19) {
            this.field33 = true;
         }

         if (this.field33) {
            var17 = Math.max(Math.min(var17, 1.0F), 0.0F);
            var18 = Math.max(Math.min(var18, 1.0F), 0.0F);
            this.option.OROIROOOOOORHCOHCOHCCRHOOOCOIC(var17);
            this.option.HCRCHORRHRRIHICOIIHCHRRHCICCRI(var18);
         }

         if (this.field32 && !var20 && (this.option.method10() && this.field35 || this.field34 || this.field33)) {
            LcuiScreen.method15();
            if (this.field22.size() >= 6) {
               this.field22.poll();
            }

            ColorOption var21 = (ColorOption)((Data)OptionFactory.method8("recent").method4(this.option.method13()))
               .method31();
            this.method3(var21, this.option);
            this.field22.add(new ColorPickerWidget(this, var21));
            this.method1(this.x, this.y, this.width);
         }

         float var42 = this.option.RCOIOHHCHRCCCRCRHIORIRHROIRCOO();
         float var22 = 1.0F - this.option.HHCRRHIOICRCHCCIRHIIIRICOHCIRO();
         LcuiScreen.method78(var1, var38 + 1.0F + (var13 - 2.0F) * var42, var39 + 1.0F + (var14 - 2.0F) * var22, 2.0, -16777216);
         LcuiScreen.method78(var1, var38 + 1.0F + (var13 - 2.0F) * var42, var39 + 1.0F + (var14 - 2.0F) * var22, 1.0, -1);
         LcuiScreen.method117(var1, this.x + 238.0F, this.y + 14.0F, 9.0F, this.height - 20.0F, 4.0F, -1);
         LcuiScreen.method31(var1, field16, this.x + 239.0F, this.y + 15.0F, 7.0F, this.height - 20.0F - 2.0F, -1);
         boolean var23 = this.method6(var2, this.x + 239.0F, this.y + 15.0F, this.x + 246.0F, this.y + 15.0F + var14 - 2.0F);
         if (!this.field32 && var20 && var23) {
            this.field34 = true;
         }

         if (this.field34) {
            float var24 = (var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - (this.y + 15.0F)) / (var14 - 2.0F);
            var24 = Math.max(Math.min(var24, 1.0F), 0.0F);
            this.option.CCOIHIIHIIOOIHCIORRCHCIRHIRCOR(var24);
         }

         float var44 = this.option.ORHCIRCROHHHOICIRIICIICOORORIR();
         LcuiScreen.method27(var1, this.x + 244.5F, var39 + 1.0F - 1.75F + (var14 - 2.0F) * var44, 3.5F, -1);
         boolean var25 = this.option.method10();
         boolean var26 = false;
         if (var25) {
            LcuiScreen.method117(var1, this.x + 252.0F, this.y + 14.0F, 9.0F, this.height - 20.0F, 4.0F, -1);
            var26 = this.method6(var2, this.x + 253.0F, this.y + 15.0F, this.x + 260.0F, this.y + 15.0F + var14 - 2.0F);
            LcuiScreen.method31(var1, field17, this.x + 253.0F, this.y + 15.0F, 7.0F, this.height - 20.0F - 2.0F, -1);
            if (!this.field32 && var20 && var26) {
               this.field35 = true;
            }

            if (this.field35) {
               float var27 = 1.0F - (var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - (this.y + 15.0F)) / (var14 - 2.0F);
               var27 = Math.max(Math.min(var27, 1.0F), 0.0F);
               this.option.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(var27);
            }

            float var46 = 1.0F - this.option.getAlpha() / 255.0F;
            LcuiScreen.method27(var1, this.x + 258.5F, var39 + 1.0F - 1.75F + (var14 - 2.0F) * var46, 3.5F, -52429);
         }

         LcuiScreen.method94(var1, this.x + 266.0F, this.y + 14.0F, 0.5F, this.height - 20.0F, 547529378);
         LcuiScreen.method94(var1, this.x + 314.0F, this.y + 14.0F, 0.5F, this.height - 20.0F, 547529378);
         LcuiScreen.method94(var1, this.x + 295.0F, this.y + 14.0F, 0.5F, this.height - 20.0F, 547529378);

         for (ColorPickerWidget var28 : this.field21) {
            var28.method3(var1, var2, var3);
         }

         for (ColorPickerWidget var52 : this.field22) {
            var52.method3(var1, var2, var3);
         }

         for (ColorPickerWidget var53 : this.field20) {
            var53.method3(var1, var2, var3);
         }

         for (int var50 = 0; var50 < 6; var50++) {
            LcuiScreen.method117(var1, this.x + 301.0F, this.y + 15.0F + 10.0F * var50, 8.0F, 8.0F, 4.0F, 889192448);
         }

         for (int var51 = 0; var51 < 6; var51++) {
            for (int var54 = 0; var54 < 2; var54++) {
               LcuiScreen.method117(var1, this.x + 318.5F + 10.0F * var54, this.y + 15.0F + 10.0F * var51, 8.0F, 8.0F, 4.0F, 889192448);
            }
         }

         if (!var20) {
            this.field33 = false;
            this.field34 = false;
            this.field35 = false;
         }

         this.field32 = var20;
         if (var26 && !var25) {
            this.method4(var1, var2, this.method13("infoAlphaSupport", new Object[0]));
         }
      }
   }

   private boolean method6(MarkerModel.Data2 var1, float var2, float var3, float var4, float var5) {
      return var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= var2
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= var4
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= var3
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= var5;
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field24.isActive()) {
         this.field24.method4(var1, var2);
      }
   }

   @Override
   public boolean isEditing() {
      return this.field24.isActive();
   }

   @Override
   public void close() {
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field31);
      this.field29 = -1.0F;
   }

   @Generated
   public boolean isExtended() {
      return this.field19;
   }

   @Generated
   public void method8(boolean var1) {
      this.field19 = var1;
   }
}
