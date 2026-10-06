package com.moonsworth.lunar.client.guiRewindhandlers;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler25 extends DynamicListener {
   @Nullable
   private String field7;

   public GuiRewindhandlersHandler25() {
      this.handle(EventEverySecond.class, this::method1);
   }

   @Override
   protected boolean isEnabled() {
      return Highlight3Iterator.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(EventEverySecond var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method3().bridge$getPlayer();
      Memory var3 = ThreadModuleDump63.method4().method31();
      if (var2 != null && var3 != null) {
         String var4 = null;
         String var5 = var3.method10().toString();

         for (Bridge2_33 var7 : var2.bridge$getSendQueue().bridge$getPlayerInfoMap()) {
            GameProfile var8 = var7.bridge$getGameProfile();
            if (var8.getId().toString().equals(var5)) {
               String var9 = var8.getName();
               if (var9.equals(var3.getName())) {
                  break;
               }

               var4 = var9;
            }
         }

         this.field7 = var4;
      } else {
         this.field7 = null;
      }
   }

   public String method5() {
      if (this.field7 != null) {
         return this.field7;
      }

      Memory var1 = ThreadModuleDump63.method4().method31();
      return var1 == null ? "" : var1.getName();
   }

   @Nullable
   @Generated
   public String method6() {
      return this.field7;
   }
}
