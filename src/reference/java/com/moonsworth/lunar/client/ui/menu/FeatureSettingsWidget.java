package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairEditorWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.widget.ScreenLifecycle;
import com.moonsworth.lunar.client.ui.widget.ToggleButtonWidget;
import com.moonsworth.lunar.client.ui.widget.CommandOptionWidget;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.ui.widget.ListOptionWidget;
import com.moonsworth.lunar.client.ui.widget.ListOptionRowWidget;
import com.moonsworth.lunar.client.ui.widget.AdvancedOptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindOptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindCaptureWidget;
import com.moonsworth.lunar.client.ui.widget.ColorPickerOptionWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.ui.widget.WidgetHooks;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework6;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.FeatureToggleKeybind;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump56;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;

public class FeatureSettingsWidget
   extends com.moonsworth.lunar.client.ui.widget.WidgetPanel<com.moonsworth.lunar.client.ui.widget.OptionWidget<?>>
   implements ScreenLifecycle {
   private final Framework7Extension field19;
   private final String field20;
   private final ResourceLocationBridge field21;
   private ToggleButtonWidget field22;
   private ToggleButtonWidget field23;
   private ToggleButtonWidget field24;
   private final FeatureToggleKeybind field25;
   private com.moonsworth.lunar.client.ui.widget.DropdownWidget field26;
   private GuiWidget field27;
   private boolean field28;
   private IconTextButton field29;
   private boolean first = true;
   private Set<ClientOption<?>> field30 = new HashSet<>();
   private Set<ClientOption<?>> field31 = new HashSet<>();

   public FeatureSettingsWidget(ModMenuWidget var1, Framework7Extension var2) {
      super(var1);
      this.field19 = var2;
      ModDetails var3 = (ModDetails)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      this.field20 = (var3 != null ? var3.getName().toUpperCase() : var2.getId().replace("_", " ")).replace("", " ").trim();
      this.field21 = ResourceLocationBridge.create("lunar", "icons/settings/arrow-left-32x32.png");
      this.field26 = new com.moonsworth.lunar.client.ui.widget.DropdownWidget(this);
      this.field28 = var2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field1)
         || var2.method3(Framework.field5)
            .map(var0 -> var0.method4(var0x -> var0x.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field1)) != null)
            .orElse(false);
      this.field29 = new IconTextButton(
         this, ResourceLocationBridge.create("lunar", "icons/assets/magnifying-glass-12x12.png"), FontRegistry.field14, "searchPlaceholder", 553648127, 905969663
      );
      this.field29.field34 = () -> {
         for (com.moonsworth.lunar.client.ui.widget.OptionWidget var2x : this.field16) {
            if (var2x instanceof ListOptionWidget var3x) {
               var3x.method19(this.field29.getText());
            }
         }
      };
      this.field22 = new ToggleButtonWidget(this, "resetToDefaults", ResourceLocationBridge.create("lunar", "icons/reset-settings-24x24.png"));
      this.field25 = (FeatureToggleKeybind)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field21);
      if (this.field25 != null && var1 != null) {
         this.field24 = new ToggleButtonWidget(this, "toggleKeybind", ResourceLocationBridge.create("lunar", "icons/keybind-24x24.png"));
         this.field24
            .method3(
               () -> {
                  StringBuilder var1x = new StringBuilder(this.OHROCHICOIOICHOCRROORRCIIICIHO("toggleKeybindTooltip", new Object[]{this.field25.method3()}));
                  if (this.field25.isSet()) {
                     var1x.append('\n').append(this.OHROCHICOIOICHOCRROORRCIIICIHO("toggleKeybindTooltipClear", new Object[0]));
                  }

                  if (this.field25.method5()) {
                     var1x.append('\n')
                        .append(AdventureChatFormatting.YELLOW)
                        .append(this.OHROCHICOIOICHOCRROORRCIIICIHO("toggleKeybindClash", new Object[]{this.field25.method4()}));
                  }

                  return var1x.toString();
               }
            );
         String var4 = var3 != null ? var3.getName() : var2.getId();
         this.field24.method4((var3x, var4x) -> {
            if (var4x == 1) {
               this.field25.clear();
            } else {
               var1.method16(new KeybindToggleRow(var1, this.field25, var4, () -> var1.method16(null)));
            }

            return true;
         });
      }

      if (this.field28) {
         this.field23 = new ToggleButtonWidget(this, "resetPosition", ResourceLocationBridge.create("lunar", "icons/reset-position-24x24.png"));
         this.field23.method4((var1x, var2x) -> {
            MixinCore9Extension var3x = (MixinCore9Extension)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
            if (var3x != null) {
               var3x.method18();
            }

            AlertExtension var4x = (AlertExtension)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var4x != null) {
               var4x.method2(var0x -> {
                  MixinCore9Extension var1xx = (MixinCore9Extension)var0x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
                  if (var1xx != null) {
                     var1xx.method18();
                  }
               });
            }

            Bridge5Extension62 var5x = (Bridge5Extension62)ThreadModuleDump63.method3().bridge$getCurrentScreen();
            var5x.method2().initGui();
            return true;
         });
      }

      this.field22.method4((var1x, var2x) -> {
         Framework5 var3x = (Framework5)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
         if (var3x != null) {
            for (ClientOption var5x : new ArrayList(var3x.method2())) {
               var5x.reset();
            }
         }

         AlertExtension var6 = (AlertExtension)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var6 != null) {
            var6.method2(var0x -> {
               var0x.method3(Framework.field6).flatMap(ModEnabledState::method1).ifPresent(ClientOption::reset);
               Framework5 var1xx = (Framework5)var0x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
               if (var1xx != null) {
                  for (ClientOption var3xx : var1xx.method2()) {
                     var3xx.reset();
                  }
               }

               var0x.method4();
            });
            com.moonsworth.lunar.client.ui.LcuiScreen.method145();
         }

         var2.method4();
         Bridge5Extension62 var7 = (Bridge5Extension62)ThreadModuleDump63.method3().bridge$getCurrentScreen();
         var7.method2().initGui();
         return true;
      });
      this.method4(
         (var2x, var3x) -> {
            for (GuiWidget var5x : this.field16) {
               if (var5x.method8(var2x, var3x)) {
                  return true;
               }
            }

            if (this.field22.method3(var2x)) {
               return this.field22.method18(var2x, var3x);
            }

            if (this.field28 && this.field23.method3(var2x)) {
               return this.field23.method18(var2x, var3x);
            }

            if (this.field24 != null && this.field24.method3(var2x)) {
               return this.field24.method18(var2x, var3x);
            }

            if (var3x == 1 && this.field29.method3(var2x)) {
               this.field29.setText("");
               return false;
            }

            if (this.field29.method3(var2x)) {
               return this.field29.method18(var2x, var3x);
            }

            if (this.method3(var2x)
               && var2x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y + 3.0F
               && var2x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 21.0F
               && var2x.HHHCHORHIHRCOHIOICICICHCRRICCI() >= 4.0
               && var2x.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + 25.0F) {
               var1.method14().method20().setText("");
               var1.method2(var1.method14());
               var1.method3(0);
               return true;
            }

            if (var2x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.field26.getY() - 1.0F) {
               for (com.moonsworth.lunar.client.ui.widget.OptionWidget var8 : this.field16) {
                  if (!var8.getOption().isHidden()) {
                     MarkerModel.Data2 var6 = this.field26.method4(var2x);
                     if (var8.method3(var6)) {
                        this.field29.method17(false);
                        return var8.method18(var6, var3x);
                     }
                  }
               }
            }

            return this.field26.method1(var2x) ? this.field26.method18(var2x, var3x) : false;
         }
      );
      this.method3((var1x, var2x) -> this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1x, var2x));
      this.method2();
      Framework10 var5 = (Framework10)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field15);
      if (var5 != null) {
         var5.method4(true);
      }
   }

   public void method2() {
      AlertExtension var1 = (AlertExtension)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      Framework6 var2 = (Framework6)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field11);
      Framework5 var3 = (Framework5)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      HashSet var4 = new HashSet();
      if (var3 != null) {
         for (ClientOption var6 : var3.method1()) {
            if (!this.method2(var6)) {
               com.moonsworth.lunar.client.ui.widget.OptionWidget var7 = var6.method18(this);
               this.field16.add(var7);
               if (var7 instanceof ListOptionRowWidget var8) {
                  var4.add(var8);
               }
            }
         }
      }

      if (var1 != null) {
         ArrayList var13 = new ArrayList();

         for (Framework7Extension var16 : var1.getChildren()) {
            if (!var16.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field17) && this.method4(var16)) {
               var13.add(new AdvancedOptionWidget(var16, this));
            }
         }

         if (var2 == null || var2.method1()) {
            this.field16.addAll(0, var13);
         } else if (!var2.method1()) {
            this.field16.addAll(var13);
         }

         for (Entry var17 : var1.method2().entrySet()) {
            SettingsPage var18 = (SettingsPage)var17.getKey();
            ArrayList var9 = new ArrayList();

            for (Framework7Extension var11 : (List)var17.getValue()) {
               if (this.method4(var11)) {
                  var9.add(new AdvancedOptionWidget(var11, this));
               }
            }

            boolean var19 = false;

            for (ListOptionRowWidget var12 : var4) {
               if (var12.getOption().getId().equals(var18.getName())) {
                  var12.IOHOIHHHHCCOHROROHRIIHOCOHHIRR().addAll(0, var9);
                  var19 = true;
                  break;
               }
            }

            if (!var19) {
               ListOptionRowWidget var21 = new ListOptionRowWidget(
                  (LabelOption)OptionFactory.method15(var18.getName()).method31(), this
               );
               var21.method1().addAll(var9);
               int var22 = this.method3(var18.getName());
               if (var22 != -1) {
                  this.field16.remove(var22);
                  this.field16.add(var22, var21);
               } else {
                  this.field16.add(var21);
               }
            }
         }
      }
   }

   private boolean method2(ClientOption<?> var1) {
      return this.field25 != null && var1 == this.field25.method9();
   }

   private int method3(String var1) {
      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var3 : this.field16) {
         if (var3 instanceof SpacerWidget var4 && var4.getOption().getId().equals(var1)) {
            return this.field16.indexOf(var3);
         }
      }

      return -1;
   }

   private boolean method4(Framework7Extension var1) {
      Framework4 var2 = (Framework4)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field16);
      return var2 != null && var2.method2() && var1.method3(Framework.field6).flatMap(ModEnabledState::method1).isPresent();
   }

   @Override
   public void update() {
      super.update();
      this.field29.update();
   }

   @Override
   protected List<com.moonsworth.lunar.client.ui.widget.OptionWidget<?>> method5() {
      return new ArrayList<>();
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      float var5 = var2 + 27.0F;
      float var6 = var1 + var3 - 4.0F - 18.0F;
      this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var6, var2 + 6.0F, 14.0F, 14.0F);
      if (this.field28) {
         var6 -= 18.0F;
         this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(var6, var2 + 6.0F, 14.0F, 14.0F);
      }

      if (this.field24 != null) {
         var6 -= 18.0F;
         this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(var6, var2 + 6.0F, 14.0F, 14.0F);
      }

      float var7 = 100.0F;
      float var8 = 14.0F;
      this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(var6 - 4.0F - var7, var2 + 6.0F, var7, var8);
      float var9 = 0.0F;
      ModDetails var10 = (ModDetails)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      if (var10 != null) {
         String var11 = var10.getDescription();
         if (var11 != null && !var11.equalsIgnoreCase("") && !var11.equals("description")) {
            List var12 = FontRegistry.method8().method25(var11, var3 - 12.0F);
            var9 += var12.size() * 8.0F + 8.0F;
            boolean var13 = var10.method3() != null && !var10.method3().isEmpty();
            if (var13) {
               String var14 = this.OHROCHICOIOICHOCRROORRCIIICIHO("originalAuthors", new Object[0]) + String.join(", ", var10.method3());
               List var15 = FontRegistry.method8().method25(var14, var3 - 12.0F);
               var9 += var15.size() * 8.0F + 4.0F;
            }
         }
      }

      var5 += var9;
      this.field26.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 6.0F, var5, 4.0F, var4 - 40.0F - var9);
      HashSet var25 = new HashSet();
      HashSet var26 = new HashSet();
      float var27 = 0.0F;
      var3 -= 30.0F;
      float var28 = 0.0F;
      int var29 = 0;
      float var16 = var5;
      ArrayList var17 = new ArrayList();
      if (!this.field29.getText().isEmpty()) {
         String var18 = ThreadModuleDump56.method4(this.field29.getText());

         for (com.moonsworth.lunar.client.ui.widget.OptionWidget var20 : this.field16) {
            if (this.method7(var18, var20)) {
               var17.add(var20);
            }
         }

         HashSet var33 = new HashSet();
         var17.removeIf(var1x -> {
            if (var1x instanceof ListOptionRowWidget var2x) {
               var2x.method25().removeIf(var1xx -> var1xx instanceof AdvancedOptionWidget var2xx && !var33.add(var2xx.method14()));
               return var2x.method25().isEmpty();
            } else {
               return false;
            }
         });
      }

      for (int var30 = 0; var30 < this.field16.size(); var30++) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var34 = (com.moonsworth.lunar.client.ui.widget.OptionWidget)this.field16
            .get(var30);
         if (!this.field29.getText().isEmpty() && !var17.contains(var34)) {
            var34.method1(var1 - var3, var2, var3);
         } else if (var34.getOption().isHidden()) {
            var25.add(var34.getOption());
         } else {
            if (var34 instanceof ColorPickerOptionWidget && ((ColorPickerOptionWidget)var34).isExtended()) {
               var26.add(var34.getOption());
               if (!this.field31.contains(var34.getOption())) {
                  float var37 = 14.0F;
                  var27 += var34.getHeight() - var37;
               }
            }

            boolean var38 = var34.method3();
            boolean var21 = var34.getOption() instanceof LabelOption;
            float var22 = var38 ? var3 / 2.0F : var3;
            if (var29 == 2) {
               var29 = 0;
               var16 += var28;
               var28 = 0.0F;
            }

            if (!var38) {
               var29 = 0;
               var16 += var28;
               var28 = 0.0F;
            }

            var34.method1(var1 + (var21 ? 0.0F : 15.0F) + var29 * (var3 / 2.0F), var16, var22);
            if (var34.getHeight() > var28) {
               var28 = var34.getHeight();
            }

            if (!var38) {
               var16 += var28;
               var28 = 0.0F;
            } else {
               if (var30 == this.field16.size() - 1) {
                  var16 += var28;
               }

               var29++;
            }
         }
      }

      if (!var25.equals(this.field30) && !this.first) {
         this.field16.removeIf(this::method8);
         this.field30.removeAll(var25);

         for (ClientOption var35 : this.field30) {
            this.field16.add(var35.method18(this));
         }

         Framework5 var32 = (Framework5)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
         if (var32 != null) {
            ArrayList var36 = new ArrayList(var32.method1());
            this.field16.sort(Comparator.comparingInt(var1x -> var36.indexOf(var1x.getOption())));
         }
      }

      if (!this.first && var27 > 0.0F) {
         this.field26.method14(this.field26.method3() - var27);
      }

      this.field31 = var26;
      if (!this.first) {
         this.field30 = var25;
      }

      this.first = false;
      this.field26.method15(var16 - var5 + 4.0F);
      if (this.field27 != null) {
         this.field26.method14(-(this.field27.getY() - var5));
         this.field27 = null;
      }
   }

   private boolean method7(String var1, com.moonsworth.lunar.client.ui.widget.OptionWidget<?> var2) {
      if (var2 instanceof SpacerWidget) {
         return false;
      } else if (var2 instanceof ListOptionWidget var3 && var3.method19(var1)) {
         return true;
      } else {
         ClientOption var9 = var2.getOption();
         if (var9.getName().toLowerCase().contains(var1)) {
            return true;
         }

         String[] var4 = var9.getName().split(" ");

         for (String var8 : var4) {
            var8 = ThreadModuleDump56.method4(var8);
            if (var8.startsWith(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean method8(com.moonsworth.lunar.client.ui.widget.OptionWidget<?> var1) {
      ClientOption var2 = var1.getOption();
      if (var2.isHidden()) {
         return true;
      }

      if (var2 instanceof LabelOption) {
         for (ClientOption var4 : var2.getChildren()) {
            if (!var4.isHidden()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(var1, this.x, this.y, this.width, 25.0F, 8.0F, 889192448, true, true, false, false);
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1, this.x, this.y + 25.0F, this.width, this.height - 25.0F, 12.0F, 536870912, false, false, true, true
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + 24.0F, this.width, 1.0F, 553648127);
      FontRegistry.method16().method13(var1, this.field20, this.x + 30.0F, this.y + 7.0F, -1);
      float var4 = 0.0F;
      ModDetails var5 = (ModDetails)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      if (var5 != null) {
         String var6 = var5.getDescription();
         if (var6 != null && !var6.isEmpty() && !var6.equals("description")) {
            List var7 = FontRegistry.method8().method25(var6, this.width - 12.0F);
            int var8 = 0;

            for (String var10 : var7) {
               FontRegistry.method8().method13(var1, var10, this.x + 6.0F, this.y + 28.0F + var8 * 8.0F + 0.5F, -1);
               var8++;
            }

            var4 += var8 * 8.0F + 8.0F;
            boolean var20 = var5.method3() != null && !var5.method3().isEmpty();
            int var23 = 0;
            if (var20) {
               String var11 = this.OHROCHICOIOICHOCRROORRCIIICIHO("originalAuthors", new Object[0]) + String.join(", ", var5.method3());

               for (String var14 : FontRegistry.method8().method25(var11, this.width - 12.0F)) {
                  FontRegistry.method8().method13(var1, var14, this.x + 6.0F, this.y + 24.0F + var4 + var23 * 8.0F + 0.5F, -1);
                  var23++;
               }

               var4 += var23 * 8.0F + 4.0F;
            }

            com.moonsworth.lunar.client.ui.LcuiScreen.method94(
               var1, this.x, this.y + 24.0F + (var20 ? 2.0F : 0.0F), this.width, var4, 352321536
            );
            com.moonsworth.lunar.client.ui.LcuiScreen.method94(
               var1, this.x, this.y + 25.0F + (var20 ? 3.0F : 0.0F) + var8 * 8.0F + var23 * 8.0F + 8.0F, this.width, 1.0F, 553648127
            );
         }
      }

      com.moonsworth.lunar.client.ui.LcuiScreen.method39(var1, this.field21, 8.0F, this.x + 7.0F, this.y + 4.0F, -1);
      if (this.method3(var2)
         && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.y + 3.0F
         && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + 21.0F
         && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + 4.0F
         && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + 25.0F) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method39(var1, this.field21, 8.0F, this.x + 7.0F, this.y + 4.0F, -1);
      }

      this.field22.method3(var1, var2, var3);
      if (this.field28) {
         this.field23.method3(var1, var2, var3);
      }

      if (this.field24 != null) {
         this.field24.method3(var1, var2, var3);
      }

      this.field29.method3(var1, var2, var3);
      var1.push();
      com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, this.x, this.y + 26.0F + var4, this.width, this.height - 26.0F - var4, 1.0F);
      this.field26.method5(var1, var2, var3);
      MarkerModel.Data2 var15 = this.field26.method4(var2);

      for (GuiWidget var18 : this.field16) {
         boolean var21 = var18.getY() + var18.getHeight() + this.field26.method3() < this.field26.getY();
         boolean var24 = var18.getY() + this.field26.method3() > this.field26.getY() + this.field26.getHeight();
         if (!var21 && !var24) {
            var18.method3(
               var1, var15, var3 && var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.field26.getY() - 1.0F && this.method4(var18, var2, new GuiWidget[0])
            );
         }
      }

      if (this.field18 != null) {
         this.field18.method3(var1, var15, var3 && this.method4(this.field18, var2, new GuiWidget[0]));
      }

      this.field26.method7(var1, var2, var3);
      com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      var1.pop();
      if (var3) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var17 = null;

         for (com.moonsworth.lunar.client.ui.widget.OptionWidget var22 : this.field16) {
            boolean var25 = var22.getY() + var22.getHeight() + this.field26.method3() < this.field26.getY();
            boolean var26 = var22.getY() + this.field26.method3() > this.field26.getY() + this.field26.getHeight();
            if (!var25 && !var26) {
               if (var17 == null) {
                  var17 = var22.method6(var15);
               }

               if (var22 instanceof KeybindOptionWidget var27 && var27.method2().method3(var15)) {
                  var27.method1(var1, var15);
               }

               if (var22 instanceof KeybindCaptureWidget var28 && var28.method2().method3(var15)) {
                  var28.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var1, var15);
               }
            }
         }

         if (var17 != null) {
            var17.method3(var1, var2);
         }
      }
   }

   public static boolean method10(com.moonsworth.lunar.client.ui.widget.OptionWidget<?> var0) {
      return var0 instanceof EditState && ((EditState)var0).isEditing()
         || var0 instanceof KeybindCaptureWidget var2 && var2.getOption().method8()
         || var0 instanceof CommandOptionWidget var1 && var1.getOption().method7();
   }

   private boolean method11(com.moonsworth.lunar.client.ui.widget.OptionWidget<?> var1) {
      return var1 instanceof WidgetHooks && ((WidgetHooks)var1).method2();
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      boolean var3 = false;

      for (com.moonsworth.lunar.client.ui.widget.OptionWidget var5 : this.field16) {
         if (this.method11(var5) || method10(var5)) {
            var3 = true;
            break;
         }
      }

      if (var3) {
         this.field29.method17(false);
         super.method4(var1, var2);
      } else if (var2 != KeyCode.KEY_ESCAPE) {
         if (!this.field29.method13(var1, var2)) {
            if (var2 != KeyCode.KEY_BACK && var2 != KeyCode.KEY_LEFT) {
               super.method4(var1, var2);
            } else {
               ModMenuWidget var7 = (ModMenuWidget)this.field27;
               var7.method14().method20().setText("");
               var7.method2(((ModMenuWidget)this.field27).method14());
               var7.method3(0);
            }
         }
      } else {
         if (this.field29.method18()) {
            this.field29.method17(false);
         } else {
            for (com.moonsworth.lunar.client.ui.widget.OptionWidget var8 : this.field16) {
               var8.method4(var1, var2);
            }
         }
      }
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field26.method5(var1);
   }

   public void method14(GuiWidget var1) {
      float var2 = this.field26.getY() + this.field26.getHeight();
      float var3 = this.field26.method3();
      float var4 = var1.getY() + var1.getHeight() + var3 - var2;
      if (var4 > 0.0F) {
         var3 = Math.max(var3 - var4, this.field26.getY() - var1.getY());
         this.field26.method14(var3);
      }
   }

   @Override
   public boolean isEditing() {
      return super.isEditing() || this.field29.isEditing();
   }

   public void method15() {
      int var1 = -1;
      int var2 = 0;

      for (Iterator var3 = this.field16.iterator(); var3.hasNext(); var2++) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var4 = (com.moonsworth.lunar.client.ui.widget.OptionWidget)var3.next();
         if (var4 instanceof AdvancedOptionWidget) {
            if (var1 == -1) {
               var1 = var2;
            }

            var3.remove();
         }
      }

      if (var1 == -1) {
         Framework6 var6 = (Framework6)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field11);
         if (var6 != null && !var6.method1()) {
            var1 = this.field16.size();
         } else {
            var1 = 0;
         }
      }

      AlertExtension var7 = (AlertExtension)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var7 != null) {
         for (Framework7Extension var5 : var7.getChildren()) {
            if (this.method4(var5)) {
               this.field16.add(var1, new AdvancedOptionWidget(var5, this));
               var1++;
            }
         }
      }
   }

   public void method17() {
      int var1 = -1;
      int var2 = 0;

      for (Iterator var3 = this.field16.iterator(); var3.hasNext(); var2++) {
         com.moonsworth.lunar.client.ui.widget.OptionWidget var4 = (com.moonsworth.lunar.client.ui.widget.OptionWidget)var3.next();
         if (!(var4 instanceof AdvancedOptionWidget) && !(var4 instanceof CrosshairEditorWidget)) {
            if (var1 == -1) {
               var1 = var2;
            }

            var3.remove();
         }
      }

      if (var1 == -1) {
         var1 = 0;
      }

      Framework5 var6 = (Framework5)this.field19.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var6 != null) {
         for (ClientOption var5 : var6.method1()) {
            if (!(var5 instanceof MultiSelectOption) && !this.method2(var5)) {
               this.field16.add(var1, var5.method18(this));
               var1++;
            }
         }
      }
   }

   @Override
   public void method19() {
      if (this.field19 instanceof ScreenLifecycle var1) {
         var1.method19();
      }
   }

   @Override
   public void onClose() {
      if (this.field19 instanceof ScreenLifecycle var1) {
         var1.onClose();
      }
   }

   @Generated
   public Framework7Extension getFeature() {
      return this.field19;
   }

   @Generated
   public com.moonsworth.lunar.client.ui.widget.DropdownWidget method18() {
      return this.field26;
   }

   @Generated
   public void method19(GuiWidget var1) {
      this.field27 = var1;
   }

   @Generated
   public IconTextButton method20() {
      return this.field29;
   }
}
