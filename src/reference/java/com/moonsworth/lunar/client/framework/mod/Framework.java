package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.framework.feature.Staffxray;
import com.moonsworth.lunar.client.config.option.trait.Lightoverlay;
import com.moonsworth.lunar.client.config.option.trait.TraitRegistry;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class Framework {
   public static final TraitType<MixinCore9Extension> field1;
   public static final TraitType<Staffxray> field2;
   public static final TraitType<Framework7Loader> field3;
   public static final TraitType<Alert2<Framework7Extension, Boolean>> field4;
   public static final TraitType<AlertExtension> field5;
   public static final TraitType<ModEnabledState> field6;
   public static final TraitType<Boolean> field7;
   public static final TraitType<FrameworkType> field8;
   public static final TraitType<ModSearchIndex> field9;
   public static final TraitType<Framework12> field10;
   public static final TraitType<Framework6> field11;
   public static final TraitType<Framework10Extension> field12;
   public static final TraitType<ModDetails> field13;
   public static final TraitType<Framework5> field14;
   public static final TraitType<Framework10> field15;
   public static final TraitType<Framework4> field16;
   public static final TraitType<Framework2> field17;
   public static final BuilderTraitType<ModSupport, ModSupport.Data> field18;
   public static final TraitType<Framework11> field19;
   public static final TraitType<Framework7> field20;
   public static final TraitType<FeatureToggleKeybind> field21;
   public static final Lightoverlay field22;

   static {
      TraitRegistry var0 = new TraitRegistry();
      field1 = var0.method1("hud_renderer");
      field2 = var0.method1("staff_mod");
      field3 = var0.method1("ichor_keybind");
      field4 = var0.method1("intercept");
      field5 = var0.method1("children");
      field6 = var0.method1("enabled");
      field7 = var0.method1("enable_listener");
      field8 = var0.method1("load_state");
      field9 = var0.method1("index");
      field10 = var0.method1("panel");
      field11 = var0.method1("page");
      field12 = var0.method1("events");
      field13 = var0.method1("details");
      field14 = var0.method1("options");
      field15 = var0.method1("display");
      field16 = var0.method2("child", var0x -> var0x.method3(new TraitType[]{field15, field3}));
      field17 = var0.method2("label", var0x -> var0x.method2(new TraitType[]{field16}));
      field18 = var0.method3("support", ModSupport::method5);
      field19 = var0.method1("dynamic");
      field20 = var0.method1("restriction");
      field21 = var0.method1("toggle_keybind");
      field22 = Lightoverlay.method6(var0);
   }
}
