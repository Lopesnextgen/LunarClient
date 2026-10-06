package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType6;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class GuiRewindhandlersHandler210 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("[^a-z A-Z:0-9_/'.!]");
   private static final Set<Character> field8 = Sets.newHashSet(new Character[]{'⏣', 'ф', '\ue067', '\ue020'});
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28 field9 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28.class
   );
   private final TrackedValue<HighlightType6> field10 = TrackedValue.method1(this, null);

   public GuiRewindhandlersHandler210() {
      this.handle(ScoreboardUpdateEvent.class, this::method1);
   }

   private void method1(ScoreboardUpdateEvent var1) {
      if (Click3.hasIsland() && var1.method1() != null) {
         this.update();
      }
   }

   protected void onEnable() {
      this.update();
   }

   private void update() {
      ImmutableList var1 = this.field9.method6();
      String var2 = null;

      for (String var4 : var1) {
         if (field8.stream().anyMatch(var1x -> var4.contains(String.valueOf(var1x)))) {
            String var5 = field7.matcher(var4).replaceAll("");
            var2 = var5.trim();
            if (Click3.getIsland() == Gui2Extension3.RIFT) {
               var2 = "Rift " + var2;
            }
            break;
         }
      }

      if (var2 == null) {
         this.field10.set(null);
      } else {
         this.field10.set(HighlightType6.fromScoreboard(var2).orElse(null));
      }
   }

   public Optional<HighlightType6> method5() {
      return !Click3.hasIsland() ? Optional.empty() : Optional.ofNullable((HighlightType6)this.field10.get());
   }
}
