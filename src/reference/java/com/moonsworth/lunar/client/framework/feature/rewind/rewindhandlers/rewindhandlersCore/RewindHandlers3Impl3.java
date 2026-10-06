package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.account.skin.SkinLoadException;
import com.moonsworth.lunar.client.account.skin.SkinType;
import com.moonsworth.lunar.client.account.skin.SavedSkinManager;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.mixin.highlight.NameTagRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityRenderBaseEvent.EntityRenderEvent;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers4;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class RewindHandlers3Impl3 extends RewindHandlers3 {
   private final Rewindhandlers_4 field9;
   private final Cache<String, Future<Optional<MinecraftProfileTexture>>> field10 = CacheBuilder.newBuilder()
      .maximumSize(100L)
      .expireAfterAccess(1L, TimeUnit.HOURS)
      .build();

   public RewindHandlers3Impl3(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(EntityRenderEvent.class, this::method1);
      this.handle(NameTagRenderEvent.class, this::method2);
      this.field9 = ((Nameplate4)var1.get()).method6().method43();
   }

   private void method1(EntityRenderEvent var1) {
      this.field9.apply(var1.method1(), "hide", var1x -> {
         if ((Boolean)var1x.get()) {
            var1.setCancelled(true);
         }
      });
      if (var1.method1() instanceof Bridge5_11 var2) {
         this.field9.apply(var2, "skin", var2x -> {
            try {
               this.method3(var2, var2x);
            } catch (Exception var4) {
            }

            ThreadModuleDump63.method3().bridge$schedule(() -> var2.bridge$setSkinLocationOverride(null, null));
         });
      }
   }

   private void method2(NameTagRenderEvent var1) {
      this.field9.apply(var1.method2(), "name", var1x -> var1.method1(AdventureTextBridge.asAdventure((String)var1x.get())));
   }

   private void method3(Bridge5_11 var1, TextOption var2) {
      String var3 = (String)var2.get();
      if (!var3.isEmpty()) {
         Future var4 = (Future)this.field10.get(var3, () -> CompletableFuture.supplyAsync(() -> {
            GameProfile var1x = null;
            UUID var2x = null;

            try {
               var2x = UUID.fromString(var3);
            } catch (Exception var5x) {
            }

            if (var2x != null) {
               var1x = new GameProfile(var2x, "");
            } else {
               try {
                  var1x = SavedSkinManager.method7(var3);
               } catch (SkinLoadException var4x) {
               }
            }

            if (var1x == null) {
               return Optional.empty();
            }

            var1x = SavedSkinManager.method8(var1x);
            return Optional.ofNullable(SavedSkinManager.method16(var1x));
         }, ThreadModuleDump37.method6()));
         RewindHandlers4 var5 = ((Nameplate4)this.field8.get()).method6().method57();
         if (var5.method25() || var4.isDone()) {
            Optional var6 = (Optional)var4.get();
            if (!var6.isEmpty()) {
               SkinType var7 = SavedSkinManager.getProvider((MinecraftProfileTexture)var6.get());
               Optional var8 = HologramsIterator2.method19(
                  FilenameUtils.getBaseName(((MinecraftProfileTexture)var6.get()).getUrl()), ((MinecraftProfileTexture)var6.get()).getUrl()
               );
               var8.ifPresent(var2x -> var1.bridge$setSkinLocationOverride(var2x, var7.toString().equals("classic") ? "default" : var7.toString()));
            }
         }
      }
   }

   @Generated
   public Rewindhandlers_4 method14() {
      return this.field9;
   }

   @Generated
   public Cache<String, Future<Optional<MinecraftProfileTexture>>> method15() {
      return this.field10;
   }
}
