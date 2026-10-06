package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.mixin.Lotusfish;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class SkyblockScoreboardParser extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.framework.listener.TabListListener field7 = (com.moonsworth.lunar.client.framework.listener.TabListListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.TabListListener.class
   );
   private final Map<String, ScoreboardSection> field8 = new HashMap<>();

   public SkyblockScoreboardParser() {
      this.method12(EventTabListUpdate.class, this::method1, 190);
   }

   private void method1(EventTabListUpdate highlightimpl31) {
      this.field8.clear();
      ImmutableList list2 = this.field7.method6();
      int index3 = 0;

      while (index3 < list2.size()) {
         String text4 = (String)list2.get(index3);
         String text5 = text4.contains(":") ? text4.substring(0, text4.indexOf(58)) : text4;
         Lotusfish lotusfish6 = com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish.method2(text5);
         if (lotusfish6 != null) {
            ScoreboardSection lotusfish37 = ScoreboardSection.method1(this.field7, index3);
            this.field8.put(lotusfish6.id(), lotusfish37);
            index3 += lotusfish37.method3().size();
         } else {
            index3++;
         }
      }
   }

   protected void onEnable() {
      this.method1(null);
   }

   @Generated
   public com.moonsworth.lunar.client.framework.listener.TabListListener method5() {
      return this.field7;
   }

   @Generated
   public Map<String, ScoreboardSection> method6() {
      return this.field8;
   }
}
