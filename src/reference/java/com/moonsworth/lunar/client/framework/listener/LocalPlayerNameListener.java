package com.moonsworth.lunar.client.framework.listener;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class LocalPlayerNameListener extends DynamicListener {
   @Nullable
   private String field7;

   public LocalPlayerNameListener() {
      this.handle(EventSecond.class, this::method1);
   }

   @Override
   protected boolean isEnabled() {
      return ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(EventSecond highlightimpl41) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method3().bridge$getPlayer();
      Memory memory3 = Ref.method4().method31();
      if (bridge5extension_52 != null && memory3 != null) {
         String text4 = null;
         String text5 = memory3.method10().toString();

         for (PlayerInfoBridge bridge2_337 : bridge5extension_52.bridge$getSendQueue().bridge$getPlayerInfoMap()) {
            GameProfile gameprofile8 = bridge2_337.bridge$getGameProfile();
            if (gameprofile8.getId().toString().equals(text5)) {
               String text9 = gameprofile8.getName();
               if (text9.equals(memory3.getName())) {
                  break;
               }

               text4 = text9;
            }
         }

         this.field7 = text4;
      } else {
         this.field7 = null;
      }
   }

   public String method5() {
      if (this.field7 != null) {
         return this.field7;
      }

      Memory memory1 = Ref.method4().method31();
      return memory1 == null ? "" : memory1.getName();
   }

   @Nullable
   @Generated
   public String method6() {
      return this.field7;
   }
}
