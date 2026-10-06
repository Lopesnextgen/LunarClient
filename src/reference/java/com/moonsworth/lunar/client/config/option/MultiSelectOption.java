package com.moonsworth.lunar.client.config.option;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.CrosshairEditorWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class MultiSelectOption extends AbstractValueOption<Set<String>> {
   private final List<String> field7 = new ArrayList<>();
   private final @Nullable Consumer<String> field8;
   private final Function<String, String> field9;

   public MultiSelectOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<Set<String>> var2,
      Collection<String> var3,
      Set<String> var4,
      @Nullable Consumer<String> var5,
      Function<String, String> var6
   ) {
      super(var1, var2, new LinkedHashSet<>(var4));
      this.field8 = var5;
      this.field9 = var6;
      this.field7.addAll(var3);
      this.method10(new HashSet(var4));
   }

   public MultiSelectOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      Collection<String> var2,
      Set<String> var3,
      Function<String, String> var4
   ) {
      this(var1, null, var2, var3, null, var4);
   }

   public void method1(Set<String> var1) {
      super.method10(new LinkedHashSet(var1));
   }

   public boolean method10(String var1) {
      boolean var2 = this.get().add(var1);
      if (this.field8 != null) {
         this.field8.accept(var1);
      }

      return var2;
   }

   public boolean remove(String var1) {
      boolean var2 = this.get().remove(var1);
      if (this.field8 != null) {
         this.field8.accept(var1);
      }

      return var2;
   }

   public boolean contains(String var1) {
      return this.get().contains(var1);
   }

   @Override
   protected OptionWidget<MultiSelectOption> method25(GuiWidget var1) {
      return new CrosshairEditorWidget(this, var1);
   }

   @Override
   public void reset() {
      this.method1(new LinkedHashSet<>());
   }

   @Override
   public boolean isDefault() {
      return ((Set)this.getValue()).isEmpty();
   }

   @Generated
   public List<String> method7() {
      return this.field7;
   }

   @Generated
   public Function<String, String> method8() {
      return this.field9;
   }

   public static class Data<O extends MultiSelectOption, B extends MultiSelectOption.Data<O, B>> extends DefaultValueBuilder<B, O, Set<String>> {
      protected @Nullable Collection<String> field14;
      protected @Nullable Consumer<String> field15;
      protected @Nullable Function<String, String> field16;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.MODIFIABLE_LIST;
      }

      @Override
      protected @Nullable Codec<Set<String>> method3() {
         return Nameplate.method23(Codec.STRING);
      }

      @Contract("_->this")
      public B method3(Collection<String> var1) {
         this.field14 = var1;
         return (B)this;
      }

      @Contract("_->this")
      public B method4(String... var1) {
         return this.method3(List.of(var1));
      }

      @Contract("_->this")
      public B method5(Consumer<String> var1) {
         this.field15 = var1;
         return (B)this;
      }

      @Contract("_->this")
      public B method6(@Nullable Function<String, String> var1) {
         this.field16 = var1;
         return (B)this;
      }

      protected O method11() {
         return (O)(new MultiSelectOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.field14 == null ? List.of() : this.field14,
            this.defaultValue == null ? new HashSet<>() : this.defaultValue,
            this.field15,
            this.field16 == null ? var0 -> var0 : this.field16
         ));
      }
   }
}
