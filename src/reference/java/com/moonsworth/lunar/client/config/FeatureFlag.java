package com.moonsworth.lunar.client.config;

import com.moonsworth.lunar.client.mod.misc.EventChestRegistry;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.ipc.WebSocketClientIterator;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum FeatureFlag {
   ADVENT("Advent", false),
   ANNIVERSARY_PARTICLES("anniversaryParticles", true, arg0 -> {
      EventChestRegistry foghandler2591 = Ref.method4().method87();
      if (foghandler2591 != null) {
         foghandler2591.method1();
      }
   }),
   APRIL_FOOLS_ENTITIES("aprilFoolsEntities", true, arg0 -> {
      EventChestRegistry foghandler2591 = Ref.method4().method87();
      if (foghandler2591 != null) {
         foghandler2591.method1();
      }
   }),
   APRIL_FOOLS_MAIN_MENU("aprilFoolsMainMenu", true),
   DEV_TOOLS("devTools", false),
   DISCORD_CTA("DiscordCTA", false),
   GAME_PROMOTION_CTA("GamePromotionCTA", false, arg0 -> {
      if (arg0) {
         Ref.method6().ifPresent(WebSocketClientIterator::method6);
      }
   }),
   IGNORE_EXTERNAL_THREAD_BETA("ignoreExternalThreadBeta", true),
   LIVE_EXPERIENCE("LiveExperience", true),
   MEMORY_SAVINGS_DISABLED("MemorySavings", false),
   MOD_METADATA("ModMetadata", false),
   PENDING_REWARDS("PendingRewards", false),
   SENTRY_ACCOUNT_LOGIN_TRACING("SentryAccountLoginTracing", false),
   SENTRY_OPTION_PARSING("SentryOptionParsing", false),
   SENTRY_TRACE_SAMPLING("SentryTraceSampling", false),
   SHOW_OPTIFINE_CAPES("showOptifineCapes", true),
   THROW_ON_WRONG_THREAD_BETA("throwOnWrongThreadBeta", true),
   THROW_ON_WRONG_THREAD_DEV("throwOnWrongThreadDev", false),
   TRAILER("Trailer", false),
   TURBO_ENGINE("TurboEngine", false, arg0 -> {
      if (Ref.MC_VERSION >= 8) {
         TurboEngineManager fogiterator_31 = Ref.method4().method89();
         if (fogiterator_31 != null) {
            fogiterator_31.setEnabled(arg0);
         }
      }
   }),
   REWIND("Rewind", false),
   TURBO_ENTITIES("TurboEntities", false, arg0 -> {
      if (Ref.MC_VERSION >= 8) {
         TurboEngineManager fogiterator_31 = Ref.method4().method89();
         if (fogiterator_31 != null) {
            fogiterator_31.method5(true);
         }
      }
   }),
   TURBO_BLOCK_ENTITIES("TurboBlockEntities", false, arg0 -> {
      if (Ref.MC_VERSION >= 8) {
         TurboEngineManager fogiterator_31 = Ref.method4().method89();
         if (fogiterator_31 != null) {
            fogiterator_31.method7(true);
         }
      }
   }),
   WRAPPED("Wrapped", false),
   YOUTOOZ("YouTooz", false);

   private final String identifier;
   @Nullable
   private final BooleanConsumer dynamicReset;
   private boolean value;

   FeatureFlag(String text3, boolean flag4) {
      this(text3, flag4, null);
   }

   FeatureFlag(String text3, boolean flag4, @Nullable BooleanConsumer booleanconsumer5) {
      this.identifier = text3;
      this.dynamicReset = booleanconsumer5;
      this.value = flag4;
   }

   public boolean isEnabled() {
      return this.value;
   }

   @Generated
   public String getIdentifier() {
      return this.identifier;
   }

   @Nullable
   @Generated
   public BooleanConsumer getDynamicReset() {
      return this.dynamicReset;
   }

   @Generated
   public void setValue(boolean flag) {
      this.value = flag;
   }
}
