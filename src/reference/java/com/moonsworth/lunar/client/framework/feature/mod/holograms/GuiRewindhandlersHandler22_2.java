package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data9;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiRewindhandlersHandler22_2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ \ue067 The Catacombs \\(([FME1-7]+)\\)$");
   private final GuiRewindhandlersHandler28 field8 = (GuiRewindhandlersHandler28)this.method3(GuiRewindhandlersHandler28.class);
   private final TrackedValue<HighlightType> field9 = TrackedValue.method1(this, HighlightType.NONE);

   public GuiRewindhandlersHandler22_2() {
      this.handle(EventWorldChanged.class, this::method1);
      this.method4(ScoreboardUpdateEvent.class, this::method2, 101);
   }

   private void method1(EventWorldChanged var1) {
      if (this.field9.get() != HighlightType.NONE) {
         ClientEventBus.method29().method12(Rewindhandlers$Data9.class, Rewindhandlers$Data9::new);
         this.field9.set(HighlightType.NONE);
      }
   }

   private void method2(ScoreboardUpdateEvent var1) {
      this.method5();
   }

   private void method5() {
      Gui2Extension3 var1 = Click3.getIsland();
      if (var1 != Gui2Extension3.NONE) {
         HighlightType var2 = (HighlightType)this.field9.get();
         this.field9.set(this.method4(this.field8.method6(), var1));
         if (var2 == HighlightType.NONE && this.field9.get() != HighlightType.NONE) {
            ClientEventBus.method29().method12(Rewindhandlers$Data10.class, () -> new Rewindhandlers$Data10((HighlightType)this.field9.get()));
         }
      }
   }

   private HighlightType method4(List<String> var1, Gui2Extension3 var2) {
      for (String var4 : var1) {
         Matcher var5 = field7.matcher(var4);
         if (var5.find()) {
            try {
               return HighlightType.valueOf(var5.group(1));
            } catch (IllegalArgumentException var7) {
               System.err.println("Could not find current dungeon floor.");
               return HighlightType.NONE;
            }
         }
      }

      return var2 == Gui2Extension3.DUNGEON ? (HighlightType)this.field9.get() : HighlightType.NONE;
   }

   protected void onEnable() {
      this.method5();
   }

   public HighlightType method6() {
      return (HighlightType)this.field9.get();
   }
}
