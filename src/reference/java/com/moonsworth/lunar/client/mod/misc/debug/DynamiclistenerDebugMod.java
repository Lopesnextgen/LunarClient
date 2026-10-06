package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.listener.GuiRewindhandlers;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.framework.listener.TrackedValueImpl;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.files.ValuePair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DynamiclistenerDebugMod extends AbstractFeature {
   private static final List<ValuePair<Object, GuiRewindhandlers>> field8 = new ArrayList<>();
   private final ColorOption field9 = (ColorOption)((Data)OptionFactory.method8("enabledColor").method4(-16736512))
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("disabledColor").method4(-6356992))
      .method31();
   private final HudComponentGroup field11 = new HudComponentGroup(true);

   public DynamiclistenerDebugMod() {
      super(false);
      this.method12(
         ModTraits.field1,
         MixinCore9Base.method9(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, WidgetFactory.withBackground(new ScrollableHudComponent(this.field11).method1(300.0F).method3(true)))
      );
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      this.field11.method8();
      this.field11.method7(DynamicListener.method15(this.field9, this.field10));
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
   }

   public String getId() {
      return "DYNAMICLISTENER_DEBUG_MOD";
   }

   protected String method18() {
      return "Dynamic Listener";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   public static void method5(Object obj0, GuiRewindhandlers guirewindhandlers1) {
      field8.add(ValuePair.method1(obj0, guirewindhandlers1));
   }

   private static List<Object> method6(GuiRewindhandlers guirewindhandlers0) {
      return field8.stream().filter(arg1 -> arg1.field2 == guirewindhandlers0).map(arg0x -> arg0x.field1).toList();
   }

   public static List<String> method7(DynamicListener guirewindhandlershandler20) {
      return method8(guirewindhandlershandler20, false);
   }

   public static List<String> method8(DynamicListener guirewindhandlershandler20, boolean flag1) {
      List list2 = method6(guirewindhandlershandler20);
      return list2.stream().map(arg1x -> method9(arg1x, flag1)).filter(Objects::nonNull).toList();
   }

   private static String method9(Object obj0, boolean flag1) {
      if (obj0 instanceof String text6) {
         return text6;
      } else if (obj0 instanceof DynamicListener guirewindhandlershandler25) {
         return !guirewindhandlershandler25.isEnabled2() ? null : "DynamicListener<" + method10(guirewindhandlershandler25, flag1) + ">";
      } else if (obj0 instanceof AbstractFeature framework7extension24) {
         boolean flag3 = framework7extension24.method3(ModTraits.field12).<Boolean>map(ModLifecycle::method12).orElse(false);
         return !flag3 ? null : "AbstractFeature<" + method10(framework7extension24, flag1) + ">";
      } else if (obj0 instanceof TrackedValueImpl guirewindhandlers2extension22) {
         return !guirewindhandlers2extension22.method3() ? null : "Live";
      } else {
         return "Unknown<" + method10(obj0, flag1) + ">";
      }
   }

   private static String method10(Object obj0, boolean flag1) {
      return flag1 ? obj0.getClass().getName() : obj0.getClass().getSimpleName();
   }
}
