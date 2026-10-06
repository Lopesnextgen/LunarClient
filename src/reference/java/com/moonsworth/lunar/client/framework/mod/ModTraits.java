package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.framework.feature.StaffXrayState;
import com.moonsworth.lunar.client.config.option.trait.Lightoverlay;
import com.moonsworth.lunar.client.config.option.trait.TraitRegistry;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;

public class ModTraits {
   public static final TraitType<MixinCore9Extension> field1;
   public static final TraitType<StaffXrayState> field2;
   public static final TraitType<Framework7Loader> field3;
   public static final TraitType<SettingIntercept<Framework7Extension, Boolean>> field4;
   public static final TraitType<ModChildren> field5;
   public static final TraitType<ModEnabledState> field6;
   public static final TraitType<Boolean> field7;
   public static final TraitType<ModLoadState> field8;
   public static final TraitType<ModSearchIndex> field9;
   public static final TraitType<PanelPosition> field10;
   public static final TraitType<PageState> field11;
   public static final TraitType<ModLifecycle> field12;
   public static final TraitType<ModDetails> field13;
   public static final TraitType<OptionContainer> field14;
   public static final TraitType<ModDisplay> field15;
   public static final TraitType<ChildModBinding> field16;
   public static final TraitType<ModCategories> field17;
   public static final BuilderTraitType<ModSupport, ModSupport.Data> field18;
   public static final TraitType<DynamicCondition> field19;
   public static final TraitType<ModRestriction> field20;
   public static final TraitType<FeatureToggleKeybind> field21;
   public static final Lightoverlay field22;

   public ModTraits() {
   }

   static {
      TraitRegistry lightoverlay60 = new TraitRegistry();
      field1 = lightoverlay60.method1("hud_renderer");
      field2 = lightoverlay60.method1("staff_mod");
      field3 = lightoverlay60.method1("ichor_keybind");
      field4 = lightoverlay60.method1("intercept");
      field5 = lightoverlay60.method1("children");
      field6 = lightoverlay60.method1("enabled");
      field7 = lightoverlay60.method1("enable_listener");
      field8 = lightoverlay60.method1("load_state");
      field9 = lightoverlay60.method1("index");
      field10 = lightoverlay60.method1("panel");
      field11 = lightoverlay60.method1("page");
      field12 = lightoverlay60.method1("events");
      field13 = lightoverlay60.method1("details");
      field14 = lightoverlay60.method1("options");
      field15 = lightoverlay60.method1("display");
      field16 = lightoverlay60.method2("child", arg0x -> arg0x.method3(new TraitType[]{field15, field3}));
      field17 = lightoverlay60.method2("label", arg0x -> arg0x.method2(new TraitType[]{field16}));
      field18 = lightoverlay60.method3("support", ModSupport::method5);
      field19 = lightoverlay60.method1("dynamic");
      field20 = lightoverlay60.method1("restriction");
      field21 = lightoverlay60.method1("toggle_keybind");
      field22 = Lightoverlay.method6(lightoverlay60);
   }
}
