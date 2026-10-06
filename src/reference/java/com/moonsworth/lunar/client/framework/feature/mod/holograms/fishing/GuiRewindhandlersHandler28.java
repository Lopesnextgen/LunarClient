package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType5;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class GuiRewindhandlersHandler28 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ (?<time>\\d{1,2}:\\d{2}(?:am|pm)) (?<icon>.)$");
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28 field8 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28.class
   );
   private String field9;
   private HighlightType5 field10;

   public GuiRewindhandlersHandler28() {
      this.handle(ScoreboardUpdateEvent.class, this::method1);
   }

   private void method1(ScoreboardUpdateEvent highlightImpl2) {
      this.update();
   }

   protected void onEnable() {
      this.update();
   }

   public void update() {
      if (Click3.hasIsland()) {
         for (String var3 : this.field8.method6()) {
            Matcher var4 = field7.matcher(var3);
            if (var4.matches()) {
               this.field9 = var4.group("time");
               this.field10 = HighlightType5.getFromIcon(var4.group("icon"));
               return;
            }
         }
      }
   }

   @Generated
   public String method5() {
      return this.field9;
   }

   @Generated
   public HighlightType5 method6() {
      return this.field10;
   }
}
