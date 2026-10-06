package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.KeyBindingClashEntry;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers6Task;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public abstract class AbstractKeybindOption<T> extends AbstractValueOption<T> {
   private final Set<KeyBindingClashEntry> keyBindingClashes = new HashSet<>(0);
   private final boolean disabledInGui;
   private final boolean ignoreRecentKeyPress;
   private long lastPressTime;
   private boolean down;
   private int holdThreshold = 200;
   private final List<Runnable> pressActions = new ArrayList<>();
   private final List<BooleanConsumer> field14 = new ArrayList<>();

   public AbstractKeybindOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<T> var2, T var3, boolean var4, boolean var5
   ) {
      super(var1, var2, (T)var3);
      this.disabledInGui = var4;
      this.ignoreRecentKeyPress = var5;
      GuiRewindhandlers6Task.method1(this);
   }

   public AbstractKeybindOption<T> method3(Runnable var1) {
      this.pressActions.add(var1);
      return this;
   }

   public AbstractKeybindOption<T> method2(BooleanConsumer var1) {
      this.holdListeners.add(var1);
      return this;
   }

   public abstract boolean isKeyDown();

   public abstract boolean method7();

   public abstract KeyCode method8();

   public boolean isUnique() {
      return this.keyBindingClashes == null || this.keyBindingClashes.size() <= 1;
   }

   public Set<KeyBindingClashEntry> method9() {
      return this.keyBindingClashes != null && !this.isUnique() ? this.keyBindingClashes : Collections.emptySet();
   }

   public boolean method6(Set<KeyBindingClashEntry> var1) {
      if (this.keyBindingClashes.equals(var1)) {
         return false;
      }

      this.keyBindingClashes.clear();
      this.keyBindingClashes.addAll(var1);
      return true;
   }

   public void method10() {
      if (this.isUnique()) {
         Set var1 = (Set)this.method7(OptionTraits.field3);
         if (var1 != null) {
            var1.remove(AdvancedOptionFlag.ADVANCED);
            if (var1.isEmpty()) {
               this.method2(OptionTraits.field3);
            }
         }
      } else {
         ((Set)this.HHRROIIHRRICIIHIIHICRHHRHOHHOO(OptionTraits.field3, var0 -> new HashSet(2))).add(AdvancedOptionFlag.ADVANCED);
      }

      OptionDataProvider var2 = (OptionDataProvider)this.method7(OptionTraits.field10);
      if (var2 != null && var2.method2()) {
         var2.method5();
      }
   }

   @Generated
   public boolean method11() {
      return this.disabledInGui;
   }

   @Generated
   public boolean method12() {
      return this.ignoreRecentKeyPress;
   }

   @Generated
   public long method13() {
      return this.lastPressTime;
   }

   @Generated
   public void method11(long var1) {
      this.lastPressTime = var1;
   }

   @Generated
   public boolean isDown() {
      return this.down;
   }

   @Generated
   public void method12(boolean var1) {
      this.down = var1;
   }

   @Generated
   public int method14() {
      return this.holdThreshold;
   }

   @Generated
   public void method14(int var1) {
      this.holdThreshold = var1;
   }

   @Generated
   public List<Runnable> method15() {
      return this.pressActions;
   }

   @Generated
   public List<BooleanConsumer> method16() {
      return this.holdListeners;
   }

   public static class Data extends OptionJsonProvider {
      public Data(ClientOption<?> var1) {
         super(var1);
      }

      @Override
      public JsonElement provide() {
         JsonObject var1 = super.provide().getAsJsonObject();
         var1.add("clashesWith", this.method3());
         return var1;
      }

      private JsonArray method3() {
         JsonArray var1 = new JsonArray();

         for (KeyBindingClashEntry var3 : ((AbstractKeybindOption)this.option).method9()) {
            JsonObject var4 = new JsonObject();
            String var5 = var3.id();
            var4.addProperty("id", ThreadModuleDump63.method44().getOrDefault(var5, var5));
            var4.addProperty("source", var3.method1().name());
            var1.add(var4);
         }

         return var1;
      }
   }

   public abstract static class KeybindOptionBuilder<B extends AbstractKeybindOption.KeybindOptionBuilder<B, O, T>, O extends AbstractKeybindOption<T>, T> extends DefaultValueBuilder<B, O, T> {
      protected boolean field14 = true;
      protected boolean field15 = false;

      protected KeybindOptionBuilder(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.KEYBIND;
      }

      @Override
      protected @Nullable Codec<T> method3() {
         return null;
      }

      @Override
      protected @Nullable Function<O, OptionDataProvider> method4() {
         return AbstractKeybindOption.Data::new;
      }

      @Contract("_->this")
      public B method4(boolean var1) {
         this.holdListeners = var1;
         return (B)this;
      }

      @Contract("_->this")
      public B method5(boolean var1) {
         this.field15 = var1;
         return (B)this;
      }
   }
}
