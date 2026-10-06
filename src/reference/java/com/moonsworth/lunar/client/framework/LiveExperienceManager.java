package com.moonsworth.lunar.client.framework;

import com.lunarclient.websocket.liveexperience.v1.LiveExperience;
import com.lunarclient.websocket.liveexperience.v1.LoadLiveExperienceRequest;
import com.lunarclient.websocket.liveexperience.v1.LoadLiveExperienceResponse;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class LiveExperienceManager implements LoadableHandler, EventRegistrar {
   private LiveExperience field1 = null;
   private boolean field2 = false;
   private boolean field3 = false;

   public LiveExperienceManager() {
      this.handle(ServerChangeEvent.class, var1 -> this.field2 = false);
      this.handle(DisconnectEvent.class, var1 -> this.field2 = false);
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
      if (this.field1 == null) {
         ThreadModuleDump63.method5()
            .ifPresent(var1 -> var1.method101().loadLiveExperience(null, LoadLiveExperienceRequest.getDefaultInstance(), this::method1));
      }
   }

   private void method1(LoadLiveExperienceResponse var1) {
      if (!var1.hasExperience()) {
         this.field1 = null;
      } else {
         this.field1 = var1.getExperience();
         ThreadModuleDump63.method3().bridge$submit(() -> {});
      }
   }

   @Generated
   public LiveExperience method2() {
      return this.field1;
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }

   @Generated
   public boolean method4() {
      return this.field3;
   }

   @Generated
   public void method5(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public void method6(boolean var1) {
      this.field3 = var1;
   }
}
