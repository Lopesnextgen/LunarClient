package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.lunarclient.election.ElectionResponse;
import com.lunarclient.election.mayor.Mayor;
import com.lunarclient.election.perk.Perk;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockElectionUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;

public class SkyblockElectionListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private volatile ElectionResponse field7;
   private long field8 = -1L;

   public SkyblockElectionListener() {
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond event) {
      long number2 = SkyblockCalendar.getElapsedYearSeconds();
      if (this.field8 < 0L || this.field8 < 105900L && number2 >= 105900L) {
         BackgroundExecutor.method6().execute(() -> this.field7 = SkyBlockElectionUtil.getElectionSync());
      }

      this.field8 = number2;
   }

   public boolean method2(String text) {
      if (this.field7 == null) {
         return false;
      }

      Mayor mayor2 = this.field7.mayor();

      for (Perk perk6 : mayor2.perks()) {
         if (perk6.name().equals(text)) {
            return true;
         }
      }

      return mayor2.minister() != null ? mayor2.minister().perk().name().equals(text) : false;
   }

   protected void onEnable() {
      this.method1(null);
   }
}
