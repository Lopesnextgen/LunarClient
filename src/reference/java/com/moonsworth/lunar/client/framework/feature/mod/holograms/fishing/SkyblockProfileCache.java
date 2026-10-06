package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.lunarclient.BetterJson;
import com.lunarclient.generated.skyblockprofileresponse.Profile;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.minecraft.hypixel.skyblock.SkyBlockProfileUtil;
import com.lunarclient.profiles.MaskedProfileResponse;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import lombok.Generated;

public class SkyblockProfileCache extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final ProfileIdListener field7 = (ProfileIdListener)this.method3(ProfileIdListener.class);
   private Profile field8;
   private Member field9;
   private long field10;

   public SkyblockProfileCache() {
      this.method12(SkyblockProfileEvents.SkyblockProfileChangeEvent.class, this::method1, 10);
   }

   private void method1(SkyblockProfileEvents.SkyblockProfileChangeEvent data151) {
      this.method6();
   }

   private boolean method5() {
      return Ref.method3().bridge$getSystemTime() - this.field10 < 30000L;
   }

   private void method6() {
      String text1 = this.field7.method5();
      if (!this.method5() && text1 != null) {
         BackgroundExecutor.method6()
            .execute(
               () -> {
                  Memory memory2 = Ref.method4().method31();
                  if (memory2 != null) {
                     this.field10 = Ref.method3().bridge$getSystemTime();

                     try {
                        String text3 = memory2.method10().toString();
                        String text4 = text3.replaceAll("-", "");
                        this.field8 = Optional.ofNullable(SkyBlockProfileUtil.getProfileSync(text1, text3))
                           .<Profile>map(MaskedProfileResponse::profile)
                           .orElse(new Profile(new BetterJson(null)));
                        this.field9 = (Member)this.field8.members().get(text4);
                     } catch (Exception exception5) {
                        CrashReporter.method5(exception5, "SkyBlockProfilesCache");
                        return;
                     }

                     BackgroundExecutor.method12(
                        () -> (SkyblockProfileEvents.SkyblockProfileLoadEvent)LunarEventBus.method29().method12(SkyblockProfileEvents.SkyblockProfileLoadEvent.class, SkyblockProfileEvents.SkyblockProfileLoadEvent::new)
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
   public ProfileIdListener method7() {
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
