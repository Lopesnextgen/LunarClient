package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.lunarclient.BetterJson;
import com.lunarclient.generated.skyblockprofileresponse.Profile;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockProfileUtil;
import com.lunarclient.profiles.MaskedProfileResponse;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import lombok.Generated;

public class GuiRewindhandlersHandler22 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler212 field7 = (GuiRewindhandlersHandler212)this.method3(GuiRewindhandlersHandler212.class);
   private Profile field8;
   private Member field9;
   private long field10;

   public GuiRewindhandlersHandler22() {
      this.method11(Rewindhandlers.Data15.class, this::method1, 10);
   }

   private void method1(Rewindhandlers.Data15 var1) {
      this.method6();
   }

   private boolean method5() {
      return ThreadModuleDump63.method3().bridge$getSystemTime() - this.field10 < 30000L;
   }

   private void method6() {
      String var1 = this.field7.method5();
      if (!this.method5() && var1 != null) {
         ThreadModuleDump37.method6()
            .execute(
               () -> {
                  Memory var2 = ThreadModuleDump63.method4().method31();
                  if (var2 != null) {
                     this.field10 = ThreadModuleDump63.method3().bridge$getSystemTime();

                     try {
                        String var3 = var2.method10().toString();
                        String var4 = var3.replaceAll("-", "");
                        this.field8 = Optional.ofNullable(SkyBlockProfileUtil.getProfileSync(var1, var3))
                           .<Profile>map(MaskedProfileResponse::profile)
                           .orElse(new Profile(new BetterJson(null)));
                        this.field9 = (Member)this.field8.members().get(var4);
                     } catch (Exception var5) {
                        Inventorymod2.method5(var5, "SkyBlockProfilesCache");
                        return;
                     }

                     ThreadModuleDump37.method12(
                        () -> (Rewindhandlers.Data14)ClientEventBus.method29().method12(Rewindhandlers.Data14.class, Rewindhandlers.Data14::new)
                     );
                  }
               }
            );
      }
   }

   protected void onEnable() {
      this.method6();
   }

   @Generated
   public GuiRewindhandlersHandler212 method7() {
      return this.field7;
   }

   @Generated
   public Profile method8() {
      return this.field8;
   }

   @Generated
   public Member method9() {
      return this.field9;
   }

   @Generated
   public long method10() {
      return this.field10;
   }
}
