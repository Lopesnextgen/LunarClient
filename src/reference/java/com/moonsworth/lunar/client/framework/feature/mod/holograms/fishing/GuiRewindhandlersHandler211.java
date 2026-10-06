package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.lunarclient.election.ElectionResponse;
import com.lunarclient.election.mayor.Mayor;
import com.lunarclient.election.perk.Perk;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockElectionUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click11;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;

public class GuiRewindhandlersHandler211 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private volatile ElectionResponse field7;
   private long field8 = -1L;

   public GuiRewindhandlersHandler211() {
      this.handle(EventEverySecond.class, this::method1);
   }

   private void method1(EventEverySecond var1) {
      long var2 = Click11.getYearSeconds();
      if (this.field8 < 0L || this.field8 < 105900L && var2 >= 105900L) {
         ThreadModuleDump37.method6().execute(() -> this.field7 = SkyBlockElectionUtil.getElectionSync());
      }

      this.field8 = var2;
   }

   public boolean method2(String var1) {
      if (this.field7 == null) {
         return false;
      }

      Mayor var2 = this.field7.mayor();

      for (Perk var6 : var2.perks()) {
         if (var6.name().equals(var1)) {
            return true;
         }
      }

      return var2.minister() != null ? var2.minister().perk().name().equals(var1) : false;
   }

   protected void onEnable() {
      this.method1(null);
   }
}
