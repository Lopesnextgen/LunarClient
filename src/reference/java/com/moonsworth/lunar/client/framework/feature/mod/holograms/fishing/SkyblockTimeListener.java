package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockWeather;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class SkyblockTimeListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ (?<time>\\d{1,2}:\\d{2}(?:am|pm)) (?<icon>.)$");
   private final com.moonsworth.lunar.client.framework.listener.ScoreboardListener field8 = (com.moonsworth.lunar.client.framework.listener.ScoreboardListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.ScoreboardListener.class
   );
   private String field9;
   private SkyblockWeather field10;

   public SkyblockTimeListener() {
      this.handle(EventScoreboardUpdate.class, this::method1);
   }

   private void method1(EventScoreboardUpdate event) {
      this.update();
   }

   protected void onEnable() {
      this.update();
   }

   public void update() {
      if (IslandUtils.isOnIsland()) {
         for (String text3 : this.field8.method6()) {
            Matcher matcher4 = field7.matcher(text3);
            if (matcher4.matches()) {
               this.field9 = matcher4.group("time");
               this.field10 = SkyblockWeather.getFromIcon(matcher4.group("icon"));
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
   public SkyblockWeather method6() {
      return this.field10;
   }
}
