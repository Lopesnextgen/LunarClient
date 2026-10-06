package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindToggleWidget;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers3;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class ModifierKeybindOption extends AbstractKeybindOption<KeyCombo> {
   public static final Set<ModifierKeybindOption> field15 = new HashSet<>();
   private final boolean field16;

   protected ModifierKeybindOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<KeyCombo> var2,
      KeyCombo var3,
      boolean var4,
      boolean var5,
      boolean var6
   ) {
      super(var1, var2, var3, var4, var5);
      field15.add(this);
      this.field16 = var6;
   }

   @NotNull
   public String method17() {
      return (this.get().method7() ? "CTRL + " : (this.get().method5() ? "ALT + " : (this.get().method6() ? "SHIFT + " : ""))) + this.get().method8().getName();
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new KeybindToggleWidget(this, var1);
   }

   @Override
   public boolean isKeyDown() {
      KeyCombo var1 = this.get();
      if (var1.method6() && !Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
         return false;
      } else if (var1.method7() && !LcuiScreen.isCtrlKeyDown()) {
         return false;
      } else {
         return var1.method5() && !Bridge.method18().method1(KeyCode.KEY_LMENU) ? false : Bridge.method18().method1(var1.method8());
      }
   }

   public static int method18() {
      return method4(Bridge.method18().method1(KeyCode.KEY_LSHIFT), LcuiScreen.isCtrlKeyDown(), Bridge.method18().method1(KeyCode.KEY_LMENU));
   }

   public static int method4(boolean var0, boolean var1, boolean var2) {
      return (var0 ? 1 : 0) | (var1 ? 2 : 0) | (var2 ? 4 : 0);
   }

   public boolean method5(KeyCode var1, int var2) {
      KeyCombo var3 = this.get();
      if (var3.method6() == ((var2 & 1) == 0)) {
         return false;
      } else if (var3.method7() == ((var2 & 2) == 0)) {
         return false;
      } else {
         return var3.method5() == ((var2 & 4) == 0) ? false : var3.method8() == var1;
      }
   }

   @Override
   public boolean method7() {
      KeyCombo var1 = this.get();
      if (var1.method6() && !Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
         return false;
      } else {
         return var1.method7() && !LcuiScreen.isCtrlKeyDown() ? false : !var1.method5() || Bridge.method18().method1(KeyCode.KEY_LMENU);
      }
   }

   @Override
   public KeyCode method8() {
      return this.get().method8();
   }

   public void method8(KeyCombo var1) {
      super.method10(var1);
      ThreadModuleDump63.method28(this);
   }

   public void method9(KeyCode var1) {
      this.method8(KeyCombo.method1(var1));
   }

   @Override
   public String toString() {
      return (this.get().method7() ? "CTRL + " : (this.get().method5() ? "ALT + " : (this.get().method6() ? "SHIFT + " : ""))) + this.get().method8().getName();
   }

   public boolean method19() {
      OptionFeatureLink var1 = (OptionFeatureLink)this.method7(OptionTraits.field8);
      if (var1 != null && !var1.<Framework7Extension>getFeature().isEnabled()) {
         return false;
      } else if (this.method11() && ThreadModuleDump63.method11() != null) {
         return false;
      } else {
         return this.method12() && GuiRewindhandlers3.method4() ? false : this.isKeyDown();
      }
   }

   public void remove() {
      field15.remove(this);
   }

   @Generated
   public boolean method21() {
      return this.field16;
   }

   public static class Data extends AbstractKeybindOption.KeybindOptionBuilder<ModifierKeybindOption.Data, ModifierKeybindOption, KeyCombo> {
      private boolean field16 = true;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected @Nullable Codec<KeyCombo> method3() {
         return KeyCombo.field1;
      }

      @Override
      protected @Nullable Function<ModifierKeybindOption, OptionDataProvider> method4() {
         return var1 -> new AbstractKeybindOption.Data(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               ModifierKeybindOption var2 = (ModifierKeybindOption)this.option;
               var1x.add("value", this.method1(new JsonObject(), (KeyCombo)var2.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()));
               var1x.add("default", this.method1(new JsonObject(), var2.getDefaultValue()));
               return var1x;
            }

            private JsonObject method1(JsonObject var1, KeyCombo var2) {
               var1.addProperty("value", var2.method8().getName());
               var1.addProperty("shift", var2.method6());
               var1.addProperty("control", var2.method7());
               var1.addProperty("alt", var2.method5());
               return var1;
            }
         };
      }

      @Contract("_->this")
      public ModifierKeybindOption.Data method3(boolean var1) {
         this.field16 = var1;
         return this;
      }

      @Contract("->this")
      public ModifierKeybindOption.Data method11() {
         this.field16 = false;
         return this;
      }

      @Contract("_->this")
      public ModifierKeybindOption.Data method5(KeyCode var1) {
         return (ModifierKeybindOption.Data)this.HIIIOHRRROCICIOIORRRIRCRCHHIII(KeyCombo.method1(var1));
      }

      protected ModifierKeybindOption method12() {
         if (this.defaultValue == null) {
            this.defaultValue = KeyCombo.method1(KeyCode.KEY_NONE);
         }

         return new ModifierKeybindOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.defaultValue,
            this.disabledInGui,
            this.ignoreRecentKeyPress,
            this.field16
         );
      }
   }
}
