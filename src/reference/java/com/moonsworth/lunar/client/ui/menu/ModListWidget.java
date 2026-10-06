package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.ui.widget.ListOptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindOptionWidget;
import com.moonsworth.lunar.client.ui.widget.ColorPickerOptionWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.config.SettingsContainer;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.Calculator2Type;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class ModListWidget extends com.moonsworth.lunar.client.ui.widget.WidgetPanel<GuiWidget> {
   private List<TypeLabelWidget> field19;
   private Map<Calculator2Type, List<com.moonsworth.lunar.client.ui.widget.OptionWidget<?>>> field20;
   private Calculator2Type field21 = Calculator2Type.GENERAL;
   private IconTextButton field22;
   private com.moonsworth.lunar.client.ui.widget.DropdownWidget field23;
   private Set<ColorOption> field24 = new HashSet<>();
   private boolean first = true;

   public ModListWidget(GuiWidget var1) {
      super(var1);
      this.field23 = new com.moonsworth.lunar.client.ui.widget.DropdownWidget(this);
      this.method4((var1x, var2) -> {
         for (TypeLabelWidget var4 : this.field19) {
            if (var4.method3(var1x)) {
               return var4.method18(var1x, var2);
            }
         }

         for (GuiWidget var8x : this.field19) {
            if (var8x.method8(var1x, var2)) {
               return true;
            }
         }

         if (this.field22.method3(var1x)) {
            if (var2 == 1) {
               this.field22.setText("");
               return true;
            } else {
               return this.field22.method18(var1x, var2);
            }
         } else {
            MarkerModel.Data2 var7 = this.field23.method4(var1x);
            if (var1x.method9() > this.field22.getY() + this.field22.getHeight() + 3.0F) {
               for (com.moonsworth.lunar.client.ui.widget.OptionWidget var5x : this.field20.get(this.field21)) {
                  if (var5x.RIIICIRHRCIHOOOORHOICRIICCCRHR(var7, var2)) {
                     return true;
                  }

                  if (var5x.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var7)) {
                     this.field22.method17(false);
                     return var5x.method18(var7, var2);
                  }
               }
            }

            return this.field23.method1(var1x) && this.field23.method18(var1x, var2);
         }
      });
      this.method3((var1x, var2) -> this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1x, var2));
      this.field19 = new ArrayList<>();

      for (Calculator2Type var5 : Calculator2Type.values()) {
         TypeLabelWidget var6;
         this.field19.add(var6 = new TypeLabelWidget(var5, this));
         if (var5 == Calculator2Type.GENERAL) {
            var6.setActive(true);
         }

         var6.method4((var2, var3) -> {
            for (TypeLabelWidget var5x : this.field19) {
               var5x.setActive(false);
            }

            var6.setActive(true);
            this.field21 = var6.method3();
            this.method2(this.x, this.y, this.width, this.height);
            return true;
         });
      }

      this.field20 = new HashMap<>();

      for (Entry var8 : this.field15.method41().IORHHHROCRRHORHRCHCCHHIHICCRCO().entrySet()) {
         this.field20.putIfAbsent((Calculator2Type)var8.getKey(), new ArrayList<>());
         SettingsContainer var9 = (SettingsContainer)var8.getValue();

         for (ClientOption var11 : var9.initAndGet0()) {
            if (!var11.isHidden()) {
               this.field20.get(var8.getKey()).add(var11.method18(this));
            }
         }
      }
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      float var5 = 100.0F;
      float var6 = 14.0F;
      this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - var5 - 4.0F, var2 + 1.0F, var5, var6);
      float var7 = 0.0F;

      for (TypeLabelWidget var9 : this.field19) {
         var9.initAndGet(var1 + var7, var2 + 1.0F);
         var7 += var9.getWidth() + 4.0F;
      }

      this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 6.0F, var2 + 20.0F, 4.0F, var4 - 20.0F);
      HashSet var23 = new HashSet();
      var3 -= 30.0F;
      float var24 = var2 + 27.0F;
      float var10 = 0.0F;
      int var11 = 0;
      ArrayList var12 = new ArrayList();
      if (!this.field22.getText().isEmpty()) {
         String var13 = ThreadModuleDump56.method4(this.field22.getText());

         for (com.moonsworth.lunar.client.ui.widget.OptionWidget var15 : this.field20.get(this.field21)) {
            if (!(var15 instanceof SpacerWidget)) {
               if (var15 instanceof ListOptionWidget var31) {
                  if (var31.method19(this.field22.getText())) {
                     var12.add(var15);
                  }
               } else {
                  ClientOption var16 = var15.getOption();
                  if (var16.getName().toLowerCase().contains(this.field22.getText().toLowerCase())) {
                     var12.add(var15);
                  } else {
                     String[] var17 = var16.getName().split(" ");

                     for (String var21 : var17) {
                        var21 = ThreadModuleDump56.method4(var21);
                        if (var21.startsWith(var13)) {
                           var12.add(var15);
                        }
                     }
                  }
               }
            }
         }
      } else {
         for (com.moonsworth.lunar.client.ui.widget.OptionWidget var27 : this.field20.get(this.field21)) {
            if (var27 instanceof ListOptionWidget var29) {
               var29.method19("");
            }
         }
      }

      List var26 = this.field20.get(this.field21);

      for (int var28 = 0; var28 < var26.size(); var28++) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var30 = (com.moonsworth.lunar.client.ui.widget.OptionWidget)var26.get(
            var28
         );
         if (!this.field22.getText().isEmpty() && !var12.contains(var30)) {
            var30.method1(var1 - var3, var2, var3);
         } else {
            if (var30 instanceof ColorPickerOptionWidget && ((ColorPickerOptionWidget)var30).isExtended()) {
               var23.add(((ColorPickerOptionWidget)var30).getOption());
            }

            boolean var32 = var30.method3();
            boolean var33 = var30.getOption() instanceof LabelOption;
            float var34 = var32 ? var3 / 2.0F : var3;
            if (var11 == 2) {
               var11 = 0;
               var24 += var10;
               var10 = 0.0F;
            }

            if (!var32) {
               var11 = 0;
               var24 += var10;
               var10 = 0.0F;
            }

            var30.method1(var1 + (var33 ? 0.0F : 15.0F) + var11 * (var3 / 2.0F), var24, var34);
            if (var30.getHeight() > var10) {
               var10 = var30.getHeight();
            }

            if (!var32) {
               var24 += var10;
               var10 = 0.0F;
            } else {
               if (var28 == var26.size() - 1) {
                  var24 += var10;
               }

               var11++;
            }
         }
      }

      if (!this.field24.equals(var23) && !this.first && this.field24.size() <= var23.size()) {
         this.field23.method14(this.field23.method3() - 65.0F);
      }

      this.field24 = var23;
      this.first = false;
      this.field23.method15(var24 - (var2 + 27.0F) + 6.0F);
      Bridge.method18().method3(true);
   }

   @Override
   protected List<GuiWidget> method5() {
      return Arrays.asList(
         this.field22 = new IconTextButton(
            this, ResourceLocationBridge.create("lunar", "icons/assets/magnifying-glass-12x12.png"), FontRegistry.field14, "searchPlaceholder", 553648127, 905969663
         )
      );
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      for (GuiWidget var5 : this.field19) {
         var5.method3(var1, var2, var3);
      }

      var1.push();
      com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, this.x - 2.0F, this.y + 20.0F, this.width + 4.0F, this.height - 15.0F, 1.0F);
      this.field23.method5(var1, var2, var3);
      MarkerModel.Data2 var11 = this.field23.method4(var2);

      for (GuiWidget var6 : this.field20.get(this.field21)) {
         boolean var7 = var6.getY() + var6.getHeight() + this.field23.method3() < this.field23.getY();
         boolean var8 = var6.getY() + this.field23.method3() > this.field23.getY() + this.field23.getHeight();
         if (var6.getX() >= this.x && !var7 && !var8 && var6.method13()) {
            var6.method3(var1, var11, var3 && !this.field23.method17());
         }
      }

      this.field23.method7(var1, var2, var3);
      com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      var1.pop();
      com.moonsworth.lunar.client.ui.widget.OptionWidget var13 = null;

      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var15 : this.field20.get(this.field21)) {
         boolean var16 = var15.getY() + var15.getHeight() + this.field23.method3() < this.field23.getY();
         boolean var9 = var15.getY() + this.field23.method3() > this.field23.getY() + this.field23.getHeight();
         if (!var16 && !var9) {
            if (var13 == null) {
               var13 = var15.method6(var11);
            }

            if (var15 instanceof KeybindOptionWidget var10 && var10.method2().method3(var11)) {
               var10.method1(var1, var2);
            }
         }
      }

      if (var13 != null) {
         var13.method3(var1, var2);
      }

      super.method3(var1, var2, var3);
   }

   @Override
   public void update() {
      super.update();

      for (GuiWidget var2 : this.field20.get(this.field21)) {
         var2.update();
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      boolean var3 = false;

      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var5 : this.field20.get(this.field21)) {
         if (FeatureSettingsWidget.method10(var5)) {
            var3 = true;
            break;
         }
      }

      for (GuiWidget var7 : this.field20.get(this.field21)) {
         var7.method4(var1, var2);
      }

      if (var3) {
         this.field22.method17(false);
      } else if (this.field22.method18()) {
         if (var2 == KeyCode.KEY_ESCAPE) {
            this.field22.setText("");
            this.field22.method17(false);
            return;
         }

         this.field22.method4(var1, var2);
      }

      this.method2(this.x, this.y, this.width, this.height);
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field23.method5(var1);
   }

   @Override
   public void close() {
      super.close();
      Bridge.method18().method3(false);
   }

   @Override
   public boolean isEditing() {
      return super.isEditing() || this.field20.get(this.field21).stream().anyMatch(var0 -> var0 instanceof EditState && ((EditState)var0).isEditing());
   }

   @Generated
   public IconTextButton method6() {
      return this.field22;
   }

   @Generated
   public com.moonsworth.lunar.client.ui.widget.DropdownWidget method7() {
      return this.field23;
   }
}
