package com.moonsworth.lunar.client.replay.render;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.client.account.skin.SkinLoadException;
import com.moonsworth.lunar.client.account.skin.SkinType;
import com.moonsworth.lunar.client.account.skin.SavedSkinManager;
import com.moonsworth.lunar.client.replay.gui.EntityOptionOverrides;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRenderQueue;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class EntityOverrideRenderer extends RewindHandler {
   private final EntityOptionOverrides field9;
   private final Cache<String, Future<Optional<MinecraftProfileTexture>>> field10 = CacheBuilder.newBuilder()
      .maximumSize(100L)
      .expireAfterAccess(1L, TimeUnit.HOURS)
      .build();

   public EntityOverrideRenderer(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventRenderEntity.class, this::method1);
      this.handle(EventRenderNameTag.class, this::method2);
      this.field9 = ((ReplayContext)threadmoduledump61.get()).method6().method43();
   }

   private void method1(EventRenderEntity data81) {
      this.field9.method3(data81.method1(), "hide", arg1x -> {
         if ((Boolean)arg1x.get()) {
            data81.setCancelled(true);
         }
      });
      if (data81.method1() instanceof Bridge5_11 bridge5_112) {
         this.field9.method3(bridge5_112, "skin", arg2x -> {
            try {
               this.method3(bridge5_112, arg2x);
            } catch (Exception exception4) {
            }

            Ref.method3().bridge$schedule(() -> bridge5_112.bridge$setSkinLocationOverride(null, null));
         });
      }
   }

   private void method2(EventRenderNameTag highlightimpl111) {
      this.field9.method3(highlightimpl111.method2(), "name", arg1x -> highlightimpl111.method1(TextBridge.asAdventure((String)arg1x.get())));
   }

   private void method3(Bridge5_11 bridge5_111, TextOption lightingextension49152) {
      String text3 = (String)lightingextension49152.get();
      if (!text3.isEmpty()) {
         Future future4 = (Future)this.field10.get(text3, () -> CompletableFuture.supplyAsync(() -> {
            GameProfile gameprofile1x = null;
            UUID uuid2x = null;

            try {
               uuid2x = UUID.fromString(text3);
            } catch (Exception exception5x) {
            }

            if (uuid2x != null) {
               gameprofile1x = new GameProfile(uuid2x, "");
            } else {
               try {
                  gameprofile1x = SavedSkinManager.method7(text3);
               } catch (SkinLoadException fishingexception4x) {
               }
            }

            if (gameprofile1x == null) {
               return Optional.empty();
            }

            gameprofile1x = SavedSkinManager.method8(gameprofile1x);
            return Optional.ofNullable(SavedSkinManager.method16(gameprofile1x));
         }, BackgroundExecutor.method6()));
         RewindRenderQueue rewindhandlers45 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method57();
         if (rewindhandlers45.method25() || future4.isDone()) {
            Optional optional6 = (Optional)future4.get();
            if (!optional6.isEmpty()) {
               SkinType gui2type7 = SavedSkinManager.getProvider((MinecraftProfileTexture)optional6.get());
               Optional optional8 = HologramsIterator2.method19(
                  FilenameUtils.getBaseName(((MinecraftProfileTexture)optional6.get()).getUrl()), ((MinecraftProfileTexture)optional6.get()).getUrl()
               );
               optional8.ifPresent(arg2x -> bridge5_111.bridge$setSkinLocationOverride(arg2x, gui2type7.toString().equals("classic") ? "default" : gui2type7.toString()));
            }
         }
      }
   }

   @Generated
   public EntityOptionOverrides method14() {
      return this.field9;
   }

   @Generated
   public Cache<String, Future<Optional<MinecraftProfileTexture>>> method15() {
      return this.field10;
   }
}
