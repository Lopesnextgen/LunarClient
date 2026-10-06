package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge3Extension_7;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert6;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class JitEmoteResource extends JitResource<Bridge8Extension3> implements Bridge3Extension_7 {
   private static final JitResource.Data6 field7 = new JitResource.Data6(false, false, false);
   private final Consumer<EventClientTick> field8;
   private final Consumer<EventRenderTickBegin> field9;
   private final ShaderCloakRenderer field10;
   private UUID field11 = null;

   public JitEmoteResource(JitAssetKey var1, Alert6 var2) {
      super(var1, field7);
      this.field8 = this::method4;
      if (var2.isRenderOnTick()) {
         this.field9 = null;
      } else {
         this.field9 = this::method3;
      }

      this.field10 = new ShaderCloakRenderer(var2);
   }

   @Override
   protected CompletableFuture<Bridge8Extension3> method8() {
      return CompletableFuture.supplyAsync(
         () -> ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field3.method3(), this),
         ThreadModuleDump37.method8()
      );
   }

   public void method1(Bridge11_2 var1, Bridge8Extension34 var2) {
      ClientEventBus.method29().method2(EventClientTick.class, this.field8);
      if (this.field9 != null) {
         ClientEventBus.method29().method2(EventRenderTickBegin.class, this.field9);
      }

      this.field10.method7(var2);
      this.method4(this.field10.method20());
   }

   private void method3(EventRenderTickBegin var1) {
      this.field10.method15();
   }

   private void method4(EventClientTick var1) {
      if (this.field10.method22()) {
         this.field10.method6();
         this.field10.method9(this.field11);
         if (this.field10.method23()) {
            this.field10.method16();
         }
      }
   }

   public void method22() {
      this.field10.cleanup();
      ClientEventBus.method29().method6(EventClientTick.class, this.field8);
      if (this.field9 != null) {
         ClientEventBus.method29().method6(EventRenderTickBegin.class, this.field9);
      }

      this.method4(0);
   }

   public boolean method4() {
      return true;
   }

   public boolean method13() {
      return true;
   }

   public void method3(boolean var1) {
   }

   public void method9(@Nullable EntityPlayerBridge var1) {
      if (var1 != null) {
         if (var1.method2() && ThreadModuleDump63.method7() != null) {
            this.field11 = null;
         } else {
            this.field11 = var1.bridge$getUniqueID();
         }
      }
   }

   @Generated
   public ShaderCloakRenderer method12() {
      return this.field10;
   }
}
