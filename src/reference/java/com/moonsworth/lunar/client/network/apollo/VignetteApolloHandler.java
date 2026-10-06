package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.module.vignette.Vignette;
import com.lunarclient.apollo.vignette.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.vignette.v1.ResetVignetteMessage;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Set;
import lombok.Generated;

public class VignetteApolloHandler extends ApolloModuleHandler {
   private Vignette field4;
   private ResourceLocationBridge field5;
   private boolean field6;

   public VignetteApolloHandler() {
      super("vignette", "Vignette");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayVignetteMessage.class, ResetVignetteMessage.class);
   }

   protected void onEnable() {
      this.method3();
   }

   protected void onDisable() {
      this.method3();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(DisplayVignetteMessage.class).ifPresent(arg1x -> {
         this.field6 = false;
         this.field4 = Vignette.builder().resourceLocation(arg1x.getResourceLocation()).opacity(arg1x.getOpacity()).build();
         this.field5 = ResourceLocationBridge.create(this.field4.getResourceLocation());
      });
      highlightimpl_31.unpack(ResetVignetteMessage.class).ifPresent(arg1x -> this.method3());
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
   public void method7(Vignette vignette1) {
      this.field4 = vignette1;
   }

   @Generated
   public void method8(ResourceLocationBridge horsestats141) {
      this.field5 = horsestats141;
   }

   @Generated
   public void method9(boolean flag) {
      this.field6 = flag;
   }
}
