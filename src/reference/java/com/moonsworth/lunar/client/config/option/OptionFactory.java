package com.moonsworth.lunar.client.config.option;

import com.google.common.collect.Lists;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.ScrollListWidget;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation3;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Contract;

public interface OptionFactory {
   @Contract("_->new")
   static DoubleOption.Data method1(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new DoubleOption.Data(var0);
   }

   @Contract("_->new")
   static FloatOption.Data method2(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new FloatOption.Data(var0);
   }

   @Contract("_->new")
   static LongOption.Data method3(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new LongOption.Data(var0);
   }

   @Contract("_->new")
   static IntegerOption.Data method4(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new IntegerOption.Data(var0);
   }

   @Contract("_->new")
   static ShortOption.Data method5(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ShortOption.Data(var0);
   }

   @Contract("_->new")
   static ByteOption.Data method6(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ByteOption.Data(var0);
   }

   @Contract("_->new")
   static ToggleOption.ToggleOptionBuilder method7(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ToggleOption.ToggleOptionBuilder(var0);
   }

   @Contract("_->new")
   static ColorOption.Data method8(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ColorOption.Data(var0);
   }

   @Contract("_->new")
   static <T extends Enum<T> & OptionEnumValue> EnumOption.Data<T> method9(
      @Annotation(method1 = Annotation.Type.SETTING) String var0
   ) {
      return new EnumOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <T extends Enum<T> & OptionEnumValue> EnumOption.Data<T> method10(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, T var1
   ) {
      return (EnumOption.Data<T>)new EnumOption.Data(var0).method2(var1);
   }

   @Contract("_->new")
   static TriStateOption.Data method11(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new TriStateOption.Data(var0);
   }

   @Contract("_->new")
   static TextOption.Data method12(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new TextOption.Data(var0);
   }

   @Contract("_->new")
   static SoundOption.Data method13(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new SoundOption.Data(var0);
   }

   @Contract("_->new")
   static ButtonOption.Data method14(@Annotation(method1 = Annotation.Type.SETTING_BUTTONS) String var0) {
      return new ButtonOption.Data(var0);
   }

   @Contract("_->new")
   static LabelOption.Data method15(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var0) {
      return new LabelOption.Data(var0);
   }

   @Contract("_->new")
   static AutoTextHotkeyOption.Data method16(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new AutoTextHotkeyOption.Data(var0);
   }

   @Contract("_->new")
   static SimpleKeybindOption.Data method17(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new SimpleKeybindOption.Data(var0);
   }

   @Contract("_->new")
   static ModifierKeybindOption.Data method18(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ModifierKeybindOption.Data(var0);
   }

   @Contract("_->new")
   static DynamicDropdownOption.Data method19(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new DynamicDropdownOption.Data(var0);
   }

   @Contract("_->new")
   static <T extends Number & Comparable<T>> MultiNumberOption.Data<T> method20(
      @Annotation(method1 = Annotation.Type.SETTING) String var0
   ) {
      return new MultiNumberOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <T extends Number & Comparable<T>> MultiNumberOption.Data<T> method21(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, List<T> var1
   ) {
      return (MultiNumberOption.Data<T>)method20(var0).method2(var1);
   }

   @SafeVarargs
   @Contract("_,_->new")
   static <T extends Number & Comparable<T>> MultiNumberOption.Data<T> method22(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, T... var1
   ) {
      return method21(var0, Lists.newArrayList(var1));
   }

   @Contract("_->new")
   static <T> DropdownOption.Data<T> method23(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new DropdownOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <T> DropdownOption.Data<T> method24(@Annotation(method1 = Annotation.Type.SETTING) String var0, T var1) {
      return (DropdownOption.Data<T>)method23(var0).method2(var1);
   }

   @Contract("_->new")
   static <T> NamedDropdownOption.Data<T> method25(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new NamedDropdownOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <T> NamedDropdownOption.Data<T> method26(@Annotation(method1 = Annotation.Type.SETTING) String var0, T var1) {
      return (NamedDropdownOption.Data<T>)method25(var0).method2(var1);
   }

   @Contract("_->new")
   static MultiSelectOption.Data<MultiSelectOption, ?> method27(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new MultiSelectOption.Data<>(var0);
   }

   @Contract("_->new")
   static ItemSelectOption.Data method28(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ItemSelectOption.Data(var0);
   }

   @Contract("_->new")
   static <T> ListOption.Data<?, T> method29(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new ListOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <T> ListOption.Data<?, T> method30(@Annotation(method1 = Annotation.Type.SETTING) String var0, List<T> var1) {
      return (ListOption.Data<?, T>)method29(var0).method2(var1);
   }

   @Contract("_,_->new")
   static <T> ListOption.Data<?, T> method31(@Annotation(method1 = Annotation.Type.SETTING) String var0, Codec<List<T>> var1) {
      return (ListOption.Data<?, T>)method29(var0).method10(var1);
   }

   @Contract("_,_->new")
   static <T> ListOption.Data<?, T> method32(@Annotation(method1 = Annotation.Type.SETTING) String var0, Codec<List<T>> var1) {
      return (ListOption.Data<?, T>)((ListOption.Data)method29(var0).method10(var1)).method7(ScrollListWidget::new);
   }

   @Contract("_->new")
   static <V, S extends Set<V>> SetOption.Data<V, S> method33(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new SetOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <V, S extends Set<V>> SetOption.Data<V, S> method34(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, S var1
   ) {
      return (SetOption.Data<V, S>)method33(var0).method2(var1);
   }

   @Contract("_,_->new")
   static <V, S extends Set<V>> SetOption.Data<V, S> method35(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, Codec<S> var1
   ) {
      return (SetOption.Data<V, S>)method33(var0).method20(var1);
   }

   @Contract("_->new")
   static <K, V, M extends Map<K, V>> MapOption.Data<K, V, M> method36(
      @Annotation(method1 = Annotation.Type.SETTING) String var0
   ) {
      return new MapOption.Data<>(var0);
   }

   @Contract("_,_->new")
   static <K, V, M extends Map<K, V>> MapOption.Data<K, V, M> method37(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, M var1
   ) {
      return (MapOption.Data<K, V, M>)method36(var0).method2(var1);
   }

   @Contract("_,_->new")
   static <K, V, M extends Map<K, V>> MapOption.Data<K, V, M> method38(
      @Annotation(method1 = Annotation.Type.SETTING) String var0, Codec<M> var1
   ) {
      return (MapOption.Data<K, V, M>)method36(var0).method20(var1);
   }

   @Contract("_->new")
   static OptionFactory.Data method39(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new OptionFactory.Data(var0);
   }

   @Contract("_->new")
   static <T> OptionFactory.SimpleOptionBuilder<T> method40(@Annotation(method1 = Annotation.Type.SETTING) String var0) {
      return new OptionFactory.SimpleOptionBuilder<>(var0);
   }

   @Contract("_,_->new")
   static <T> OptionFactory.SimpleOptionBuilder<T> method41(@Annotation(method1 = Annotation.Type.SETTING) String var0, T var1) {
      return (OptionFactory.SimpleOptionBuilder<T>)new OptionFactory.SimpleOptionBuilder(var0).method2(var1);
   }

   @Contract("_,_->new")
   static <T> OptionFactory.SimpleOptionBuilder<T> method42(@Annotation(method1 = Annotation.Type.SETTING) String var0, Codec<T> var1) {
      return (OptionFactory.SimpleOptionBuilder<T>)new OptionFactory.SimpleOptionBuilder(var0).method20(var1);
   }

   class Data extends OptionBuilderBase<OptionFactory.Data, ClientOption<Void>, Void> {
      protected Data(@Annotation3 String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.UNKNOWN;
      }

      @Override
      protected Codec<Void> method3() {
         return null;
      }

      @Override
      protected ClientOption<Void> method31() {
         return new AbstractOption<Void>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, null) {
            public Void method7() {
               return null;
            }

            public void method2(Void var1) {
            }

            public Void method8() {
               return null;
            }

            protected void method4(Void var1) {
            }
         };
      }
   }

   class SimpleOptionBuilder<T> extends DefaultValueBuilder<OptionFactory.SimpleOptionBuilder<T>, ClientOption<T>, T> {
      protected SimpleOptionBuilder(@Annotation3 String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.UNKNOWN;
      }

      @Override
      protected Codec<T> method3() {
         return null;
      }

      @Override
      protected ClientOption<T> method31() {
         return new AbstractValueOption<T>(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue) {};
      }
   }
}
