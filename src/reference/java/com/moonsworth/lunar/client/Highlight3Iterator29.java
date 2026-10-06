package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.module.vignette.Vignette;
import com.lunarclient.apollo.vignette.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.vignette.v1.ResetVignetteMessage;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Set;
import lombok.Generated;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator29 extends ApolloModuleHandler {
   private Vignette field4;
   private ResourceLocationBridge field5;
   private boolean field6;

   public Highlight3Iterator29() {
      super("vignette", "Vignette");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayVignetteMessage.class, ResetVignetteMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(DisplayVignetteMessage.class).ifPresent(var1x -> {
         this.field6 = false;
         this.field4 = Vignette.builder().resourceLocation(var1x.getResourceLocation()).opacity(var1x.getOpacity()).build();
         this.field5 = ResourceLocationBridge.create(this.field4.getResourceLocation());
      });
      var1.unpack(ResetVignetteMessage.class).ifPresent(var1x -> this.method3());
   }

   public void method3() {
      this.field6 = false;
      this.field4 = null;
      this.field5 = null;
   }

   @Generated
   public Vignette method4() {
      return this.field4;
   }

   @Generated
   public ResourceLocationBridge method6() {
      return this.field5;
   }

   @Generated
   public boolean method8() {
      return this.field6;
   }

   @Generated
   public void method7(Vignette var1) {
      this.field4 = var1;
   }

   @Generated
   public void method8(ResourceLocationBridge var1) {
      this.field5 = var1;
   }

   @Generated
   public void method9(boolean var1) {
      this.field6 = var1;
   }
}
