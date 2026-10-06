package com.moonsworth.lunar.client.config.option;

import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.KeyBindingClashEntry;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindResetWidget;
import com.moonsworth.lunar.client.framework.mod.Framework7Loader;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class SimpleKeybindOption extends AbstractKeybindOption<KeyCode> {
   public static final HashBiMap<SimpleKeybindOption, MixinHelper_15> keybindRegistry = HashBiMap.create();
   private final boolean field16;
   private final boolean field17;
   private String category = "Lunar Client";
   private MixinHelper_15 keyBinding;
   private boolean registered = false;

   protected SimpleKeybindOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<KeyCode> var2,
      KeyCode var3,
      boolean var4,
      boolean var5,
      boolean var6,
      boolean var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.showInControls = var6;
      this.ignoreClashing = var7;
      this.HORHIRROCIOIICIOHCOCCOOHIRCCRI(this::method2);
   }

   public SimpleKeybindOption method1(@NotNull MixinHelper_15 var1) {
      this.keyBinding = var1;
      field15.put(this, var1);
      Bridge.method8().method67();
      return this;
   }

   private void method2(KeyCode var1) {
      if (this.keyBinding == null) {
         this.method1(
            Bridge.method8()
               .method12(this.getName(), Bridge.method18().method4((KeyCode)this.getValue()), this.category, this.showInControls)
         );
         this.registered = true;
      }

      this.keyBinding.bridge$setKey(var1);
      Bridge.method8().method67();
   }

   public void method3(@NotNull Framework7Loader var1) {
      String var2 = "Lunar Client";
      if (var1.method6() != null) {
         var2 = var1.method6();
      }

      this.category = var2;
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new KeybindResetWidget(this, var1);
   }

   public void method5(KeyCode var1, boolean var2) {
      super.method11(var1, var2);
      ThreadModuleDump63.method28(this);
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      this.method2((KeyCode)this.getValue());
   }

   @Override
   public boolean isKeyDown() {
      return Bridge.method18().method1(this.get());
   }

   @Override
   public boolean method7() {
      return true;
   }

   @Override
   public KeyCode method8() {
      return this.get();
   }

   @Override
   public boolean method6(Set<KeyBindingClashEntry> var1) {
      if (this.ignoreClashing) {
         var1 = Collections.emptySet();
      }

      boolean var2 = super.method6(var1);
      MixinHelper_15 var3 = (MixinHelper_15)field15.get(this);
      if (var3 != null) {
         var3.bridge$setSiblingName(this.getId());
         var3.bridge$setClashesWith(var1);
      }

      return var2;
   }

   public boolean method17() {
      return this.registered;
   }

   @Generated
   public boolean method18() {
      return this.ignoreClashing;
   }

   @Generated
   public MixinHelper_15 method19() {
      return this.keyBinding;
   }

   public static class Data extends AbstractKeybindOption.KeybindOptionBuilder<SimpleKeybindOption.Data, SimpleKeybindOption, KeyCode> {
      private boolean field16 = false;
      private boolean field17 = false;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected @Nullable Function<SimpleKeybindOption, OptionDataProvider> method4() {
         return var1 -> new AbstractKeybindOption.Data(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               SimpleKeybindOption var2 = (SimpleKeybindOption)this.option;
               var1x.addProperty("value", var2.get().getName());
               var1x.addProperty("default", var2.getDefaultValue().getName());
               var1x.addProperty("ignoreClashing", Data.this.ignoreClashing);
               return var1x;
            }
         };
      }

      @Contract("_->this")
      public SimpleKeybindOption.Data method2(boolean var1) {
         this.ignoreClashing = var1;
         return this;
      }

      @Contract("->this")
      public SimpleKeybindOption.Data method11() {
         this.ignoreClashing = false;
         return this;
      }

      @Contract("_->this")
      public SimpleKeybindOption.Data method6(boolean var1) {
         this.showInControls = var1;
         return this;
      }

      @Contract("->this")
      public SimpleKeybindOption.Data method12() {
         this.showInControls = true;
         return this;
      }

      protected SimpleKeybindOption method13() {
         if (this.defaultValue == null) {
            this.defaultValue = KeyCode.KEY_NONE;
         }

         if (this.codec == null) {
            this.codec = Codec.stringResolver(Enum::name, var0 -> KeyCode.valueOf(var0.toUpperCase()));
         }

         return new SimpleKeybindOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.defaultValue,
            this.disabledInGui,
            this.ignoreRecentKeyPress,
            this.showInControls,
            this.ignoreClashing
         );
      }
   }
}
