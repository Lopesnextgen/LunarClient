package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase5;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ ⏣ Kuudra's Hollow \\((T[1-5])\\)$");
   private final GuiRewindhandlersHandler28 field8 = (GuiRewindhandlersHandler28)this.method3(GuiRewindhandlersHandler28.class);
   private HighlightType2 field9;

   public GuiRewindhandlersHandler2() {
      this.handle(EventWorldChanged.class, this::method1);
      this.handle(ScoreboardUpdateEvent.class, this::method2);
   }

   private void method1(EventWorldChanged var1) {
      if (this.field9 != HighlightType2.NONE) {
         ClientEventBus.method29().method12(HighlightBase5.Data.class, HighlightBase5.Data::new);
         this.field9 = HighlightType2.NONE;
      }
   }

   private void method2(ScoreboardUpdateEvent var1) {
      this.method5();
   }

   public void method5() {
      if (Click3.getIsland() != Gui2Extension3.NONE) {
         HighlightType2 var1 = this.field9;
         this.field9 = this.method4(this.field8.method6());
         if (var1 == HighlightType2.NONE && this.field9 != HighlightType2.NONE) {
            ClientEventBus.method29().method12(HighlightBase5.Data2.class, HighlightBase5.Data2::new);
         }
      }
   }

   private HighlightType2 method4(List<String> var1) {
      for (String var3 : var1) {
         Matcher var4 = field7.matcher(var3);
         if (var4.matches()) {
            try {
               return HighlightType2.valueOf(var4.group(1));
            } catch (IllegalArgumentException var6) {
               System.err.println("Could not find current Kuudra tier.");
               return HighlightType2.NONE;
            }
         }
      }

      return Click3.getIsland() == Gui2Extension3.KUUDRA ? this.method6() : HighlightType2.NONE;
   }

   protected void onEnable() {
      this.method5();
   }

   @Generated
   public HighlightType2 method6() {
      return this.field9;
   }
}
