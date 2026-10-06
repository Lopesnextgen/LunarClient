package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionFlag;
import com.moonsworth.lunar.client.config.option.trait.Lightoverlay;
import com.moonsworth.lunar.client.config.option.trait.TraitRegistry;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.framework.Flag;
import java.util.Set;
import java.util.function.BiFunction;

public class OptionTraits {
   public static final TraitType<OptionUpdateListeners<?>> field1;
   public static final TraitType<OptionDisplay> field2;
   public static final TraitType<Set<OptionFlag>> field3;
   public static final TraitType<AlertExtension> field4;
   public static final TraitType<SettingIntercept<ClientOption<?>, ?>> field5;
   public static final TraitType<Flag> field6;
   public static final TraitType<com.moonsworth.lunar.client.config.option.NumberRule<?>> field7;
   public static final TraitType<OptionFeatureLink> field8;
   public static final TraitType<String> field9;
   public static final TraitType<OptionDataProvider> field10;
   public static final TraitType<String> field11;
   public static final TraitType<Flag> field12;
   public static final TraitType<BiFunction<ClientOption<?>, GuiWidget, OptionWidget<?>>> field13;
   public static final TraitType<Object> field14;
   public static final Lightoverlay field15;

   public OptionTraits() {
   }

   static {
      TraitRegistry lightoverlay60 = new TraitRegistry();
      field1 = lightoverlay60.method1("updates");
      field2 = lightoverlay60.method1("display");
      field3 = lightoverlay60.method1("badges");
      field4 = lightoverlay60.method1("children");
      field5 = lightoverlay60.method1("intercept");
      field6 = lightoverlay60.method1("is_global");
      field7 = lightoverlay60.method1("number_rules");
      field8 = lightoverlay60.method1("feature_link");
      field9 = lightoverlay60.method1("feature_id");
      field11 = lightoverlay60.method1("lang_id");
      field10 = lightoverlay60.method1("data_provider");
      field13 = lightoverlay60.method1("custom_component");
      field14 = lightoverlay60.method1("debug_object");
      field12 = lightoverlay60.method1("hide_from_api");
      field15 = Lightoverlay.method6(lightoverlay60);
   }
}
