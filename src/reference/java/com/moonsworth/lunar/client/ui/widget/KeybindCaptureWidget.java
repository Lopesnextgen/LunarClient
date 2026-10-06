package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.autotextactions.AutoTextHotkey;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class KeybindCaptureWidget extends KeybindOptionWidget<AutoTextHotkeyOption> implements EditState {
   private boolean field17;
   private KeyCode field18 = null;
   private TextLabelWidget field19;
   private ProgressBarWidget field20;
   private ProgressBarWidget field21;
   private CommandFieldWidget field22;

   public KeybindCaptureWidget(AutoTextHotkeyOption var1, GuiWidget var2) {
      super(var1, var2);
      this.field19 = new TextLabelWidget(
         this,
         (
               ((KeyCombo)var1.method7().get()).method7()
                  ? "CTRL + "
                  : (((KeyCombo)var1.method7().get()).method5() ? "ALT + " : (((KeyCombo)var1.method7().get()).method6() ? "SHIFT + " : ""))
            )
            + ((KeyCombo)var1.method7().get()).method8().getName()
      );
      this.field19.method15(() -> !this.field17 && !var1.method7().isUnique() ? -2538942 : -4275267);
      this.field20 = new ProgressBarWidget(this, ResourceLocationBridge.create("lunar", "icons/assets/deny-16x16.png"));
      this.field20.method18(new AnimatedValue(-4644318, -2530718));
      this.field21 = new ProgressBarWidget(this, ResourceLocationBridge.create("lunar", "icons/cosmetics/padlock-16x16.png"));
      this.field21.method18(new AnimatedValue(-7829368, -3355444));
      this.field21.method17("hotkeyBtnLocked", new Object[0]);
      this.field22 = new CommandFieldWidget(this, (String)var1.get());
      this.field22.method19((String)var1.get());
      this.field22.method17(var1::get);
      this.field22.method18(var1::method10);
      this.field22.method14(var1::method8);
      this.field22.method15(var1::method5);
      this.field22.method16(() -> !this.field17);
      this.field22.method20((var1x, var2x) -> {
         ThreadModuleDump63.method4().method40().method19().method10(var1.getId());
         return true;
      });
      this.method3((var1x, var2x) -> this.field22.method2(var1x, var2x));
      this.method4(
         (var2x, var3) -> {
            if ((var3 == 4 || var3 == 5 || var3 == 3 || var3 == 2) && this.field17) {
               this.field17 = false;
               this.field19.setActive(false);
               var1.method7().method9(KeyCode.valueOf("KEY_MOUSE" + (var3 + 1)));
               this.field19.setText(((KeyCombo)var1.method7().get()).method8().getName());
               return true;
            }

            if (!this.field19.method3(var2x)) {
               if (this.field20.method3(var2x)) {
                  OptionFeatureLink var4 = (OptionFeatureLink)var1.method7(OptionTraits.field8);
                  if (var4 != null && var4.getFeature() instanceof AutoTextHotkey var5) {
                     var5.method11(var1.getId(), this);
                  }

                  return true;
               } else {
                  if (this.field22.method3(var2x)) {
                     this.field22.method6(var2x, var3);
                  } else if (var1.method8()) {
                     var1.method5(false);
                  }

                  return false;
               }
            } else {
               ThreadModuleDump63.method4().method40().method19().method10((String)var1.get());
               if (var3 == 1 && !this.field17) {
                  var1.method7().method9(KeyCode.KEY_NONE);
                  this.field19.setText(((KeyCombo)var1.method7().get()).method8().getName());
               } else if (var3 == 0) {
                  this.field17 = !this.field17;
                  this.field19.setActive(this.field17);
                  if (this.field17) {
                     this.field19.setText("?");
                  } else {
                     this.field19
                        .setText(
                           (
                                 ((KeyCombo)var1.method7().get()).method7()
                                    ? "CTRL + "
                                    : (((KeyCombo)var1.method7().get()).method5() ? "ALT + " : (((KeyCombo)var1.method7().get()).method6() ? "SHIFT + " : ""))
                              )
                              + ((KeyCombo)var1.method7().get()).method8().getName()
                        );
                  }
               }

               return true;
            }
         }
      );
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 14.0F;
   }

   @Override
   public void update() {
      this.field22.update();
      this.field19.update();
      this.field20.update();
      this.field21.update();
      if (this.field18 != null && this.field17 && !this.method5(this.field18)) {
         this.option.method7().method9(this.field18);
         this.field17 = false;
         this.field18 = null;
         this.field19.setText(((KeyCombo)this.option.method7().get()).method8().getName());
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method14().method13(var1, this.option.getName(), this.x, this.y + 1.5F, -4079426);
      this.field19.method2(this.x + this.width - 90.0F, this.y + 2.0F, 70.0F, 12.0F);
      this.field19.method3(var1, var2, var3);
      boolean var4 = true;
      OptionFeatureLink var5 = (OptionFeatureLink)this.option.method7(OptionTraits.field8);
      if (var5 != null
         && var5.getFeature() instanceof AutoTextHotkey var6
         && this.option.getIndex() <= var6.method21()
         && var6.method19().size() <= var6.method21()) {
         var4 = false;
      }

      byte var9 = 5;
      if (var4 || !((String)this.option.get()).equals("/Command") || this.option.method7().method8() != KeyCode.KEY_NONE) {
         this.field20.method2(this.x + this.width - 15.0F, this.y + 2.0F, 12.0F, 12.0F);
         this.field20.method3(var1, var2, var3);
         var9 = 0;
      }

      AutoTextHotkey var10 = ThreadModuleDump63.method4().method40().method19();
      boolean var8 = var10.method14() && !((String)this.option.get()).startsWith("/");
      if (var8 || var10.method15().stream().anyMatch(var1x -> ((String)this.option.get()).toLowerCase().startsWith(var1x.toLowerCase()))) {
         this.field21.method2(this.x + this.width - var9, this.y + 2.0F, 12.0F, 12.0F);
         this.field21.method3(var1, var2, var3);
         if (this.field21.method3(var2)) {
            this.field21.HRICOROOOCCOCOROCRHHCRRIRCOICO(var1, var2, var3);
         }
      }

      this.field22.method2(this.x + this.width - 235.0F, this.y + 2.0F, 140.0F, 12.0F);
      this.field22.method3(var1, var2, var3);
   }

   public void method4() {
      this.option.method7().method9(KeyCode.KEY_NONE);
      this.field17 = false;
      this.field18 = null;
      this.field19.setActive(false);
      this.field19.setText(((KeyCombo)this.option.method7().get()).method8().getName());
   }

   public void method5() {
      this.option.reset();
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field17) {
         if (var2 == KeyCode.KEY_ESCAPE) {
            this.method4();
         } else if (var2 != KeyCode.KEY_LSHIFT && var2 != KeyCode.KEY_LCONTROL && var2 != KeyCode.KEY_LMENU) {
            this.option
               .method7()
               .method8(
                  new KeyCombo(this.method5(KeyCode.KEY_LMENU), this.method5(KeyCode.KEY_LSHIFT), this.method5(KeyCode.KEY_LCONTROL), var2)
               );
            this.field17 = false;
            this.field18 = null;
            this.field19.setActive(false);
            this.field19
               .setText(
                  (((KeyCombo)this.option.method7().get()).method7() ? "CTRL + " : "")
                     + (((KeyCombo)this.option.method7().get()).method5() ? "ALT + " : "")
                     + (((KeyCombo)this.option.method7().get()).method6() ? "SHIFT + " : "")
                     + ((KeyCombo)this.option.method7().get()).method8().getName()
               );
         } else {
            this.field18 = var2;
         }
      } else {
         this.field22.method4(var1, var2);
      }
   }

   private boolean method5(KeyCode var1) {
      if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
         return var1 == KeyCode.KEY_LSHIFT;
      } else if (LcuiScreen.isCtrlKeyDown()) {
         return var1 == KeyCode.KEY_LCONTROL;
      } else {
         return Bridge.method18().method1(KeyCode.KEY_LMENU) ? var1 == KeyCode.KEY_LMENU : Bridge.method18().method1(var1);
      }
   }

   @Override
   public boolean method5(int var1) {
      return super.method5(var1) || this.field22.method5(var1);
   }

   @Override
   public boolean method5(MarkerModel.Data2 var1) {
      return super.method5(var1) && !this.field19.method3(var1) && !this.field20.method3(var1);
   }

   @Override
   public void close() {
   }

   @Override
   public boolean isEditing() {
      return this.field17 || this.field22.method7().getAsBoolean();
   }

   @Generated
   public boolean method8() {
      return this.field17;
   }

   @Generated
   @Override
   public TextLabelWidget method2() {
      return this.field19;
   }

   @Generated
   public ProgressBarWidget method10() {
      return this.field20;
   }

   @Generated
   public ProgressBarWidget method14() {
      return this.field21;
   }

   @Generated
   public CommandFieldWidget method15() {
      return this.field22;
   }
}
