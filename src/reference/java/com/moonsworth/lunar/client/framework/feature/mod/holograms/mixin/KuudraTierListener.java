package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.KuudraTier;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.KuudraEvent;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class KuudraTierListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^ ⏣ Kuudra's Hollow \\((T[1-5])\\)$");
   private final ScoreboardListener field8 = (ScoreboardListener)this.method3(ScoreboardListener.class);
   private KuudraTier field9;

   public KuudraTierListener() {
      this.handle(EventWorldChange.class, this::method1);
      this.handle(EventScoreboardUpdate.class, this::method2);
   }

   private void method1(EventWorldChange event) {
      if (this.field9 != KuudraTier.NONE) {
         LunarEventBus.method29().method12(KuudraEvent.Data.class, KuudraEvent.Data::new);
         this.field9 = KuudraTier.NONE;
      }
   }

   private void method2(EventScoreboardUpdate event) {
      this.method5();
   }

   public void method5() {
      if (IslandUtils.getIsland() != SkyblockIsland.NONE) {
         KuudraTier highlighttype21 = this.field9;
         this.field9 = this.method4(this.field8.method6());
         if (highlighttype21 == KuudraTier.NONE && this.field9 != KuudraTier.NONE) {
            LunarEventBus.method29().method12(KuudraEvent.KuudraEnterEvent.class, KuudraEvent.KuudraEnterEvent::new);
         }
      }
   }

   private KuudraTier method4(List<String> list) {
      for (String text3 : list) {
         Matcher matcher4 = field7.matcher(text3);
         if (matcher4.matches()) {
            try {
               return KuudraTier.valueOf(matcher4.group(1));
            } catch (IllegalArgumentException illegalargumentexception6) {
               System.err.println("Could not find current Kuudra tier.");
               return KuudraTier.NONE;
            }
         }
      }

      return IslandUtils.getIsland() == SkyblockIsland.KUUDRA ? this.method6() : KuudraTier.NONE;
   }

   protected void onEnable() {
      this.method5();
   }

   @Generated
   public KuudraTier method6() {
      return this.field9;
   }
}
