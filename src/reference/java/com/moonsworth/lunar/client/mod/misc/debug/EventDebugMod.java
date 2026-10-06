package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.DebuggingEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.util.math.MathUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;

public class EventDebugMod extends AbstractFeature {
   private final HudComponentGroup field8 = new HudComponentGroup(true);

   public EventDebugMod() {
      super(false);
      this.method4(
         ModTraits.field1,
         MixinCore9Base.method9(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, WidgetFactory.withBackground(new ScrollableHudComponent(this.field8).method1(300.0F)))
      );
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      this.field8.method8();

      for (Component component3 : this.method13()) {
         this.field8.method5(new TextHudComponent(component3));
      }
   }

   private List<Component> method13() {
      if (!(LunarEventBus.method29() instanceof DebuggingEventBus highlight2iterator1)) {
         return List.of(Component.text(this.method14("mustUseDebuggingBus", new Object[0])));
      } else {
         ArrayList list8 = new ArrayList();
         ArrayList list3 = new ArrayList(highlight2iterator1.method1().object2IntEntrySet());
         list3.sort(Comparator.comparingInt(arg0 -> -arg0.getIntValue()));

         for (Entry entry5 : list3) {
            float value6 = MathUtils.lerp(0.0F, 0.28F, 1.0F - Math.min(50, entry5.getIntValue()) / 50.0F);
            HSVLike hsvlike7 = HSVLike.hsvLike(value6, 1.0F, 0.7F);
            list8.add(Component.text(((String)entry5.getKey()).replace("$", ".") + " (" + entry5.getIntValue() + ")", TextColor.color(hsvlike7)));
         }

         return list8;
      }
   }

   public String getId() {
      return "EVENT_DEBUG_MOD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }
}
