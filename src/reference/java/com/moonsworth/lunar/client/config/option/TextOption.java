package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.CommandOptionWidget;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import it.unimi.dsi.fastutil.chars.Char2ObjectFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class TextOption extends AbstractValueOption<String> implements EditState {
   private boolean field7;
   private final int field8;
   private final List<Consumer<String>> field9;
   private final List<Consumer<String>> field10;
   private final Function<String, String> field11;
   private final Char2ObjectFunction<@Nullable Character> field12;

   public TextOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<String> var2,
      String var3,
      int var4,
      List<Consumer<String>> var5,
      List<Consumer<String>> var6,
      Function<String, String> var7,
      Char2ObjectFunction<@Nullable Character> var8
   ) {
      super(var1, var2, var3);
      this.field8 = var4;
      this.field9 = var5;
      this.field10 = var6;
      this.field11 = var7;
      this.field12 = var8;
   }

   public static boolean method1(char var0) {
      return var0 == '\b' || var0 == 0;
   }

   public boolean isEmpty() {
      return this.get() == null || this.get().trim().isEmpty();
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      CommandOptionWidget var2 = new CommandOptionWidget(this, this.field8, var1);
      var2.method4().method25().method39(this.field11);
      return var2;
   }

   @Override
   public boolean isEditing() {
      return this.field7;
   }

   @Generated
   public boolean method7() {
      return this.field7;
   }

   @Generated
   public void method4(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public List<Consumer<String>> method8() {
      return this.field9;
   }

   @Generated
   public List<Consumer<String>> method9() {
      return this.field10;
   }

   @Generated
   public Char2ObjectFunction<@Nullable Character> method10() {
      return this.field12;
   }

   public static class Data extends DefaultValueBuilder<TextOption.Data, TextOption, String> {
      private int field14 = 256;
      private final List<Consumer<String>> field15 = new ArrayList<>(0);
      private final List<Consumer<String>> field16 = new ArrayList<>(0);
      private Function<String, String> field17 = var0 -> var0;
      private Char2ObjectFunction<@Nullable Character> field18 = var0 -> var0;

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.TEXT;
      }

      @Nullable
      @Override
      protected Codec<String> method3() {
         return Codec.STRING;
      }

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Contract("_->this")
      public TextOption.Data method3(int var1) {
         this.field14 = var1;
         return this;
      }

      @Contract("_->this")
      public TextOption.Data method4(Consumer<String> var1) {
         this.field15.add(var1);
         return this;
      }

      @Contract("_->this")
      public TextOption.Data method5(Consumer<String> var1) {
         this.field16.add(var1);
         return this;
      }

      @Contract("_->this")
      public TextOption.Data method6(Function<String, String> var1) {
         this.field17 = var1;
         return this;
      }

      @Contract("_->this")
      public TextOption.Data method7(Char2ObjectFunction<@Nullable Character> var1) {
         this.field18 = var1;
         return this;
      }

      protected TextOption method11() {
         if (this.defaultValue == null) {
            this.defaultValue = "";
         }

         return new TextOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue, this.field14, this.field15, this.field16, this.field17, this.field18
         );
      }
   }
}
