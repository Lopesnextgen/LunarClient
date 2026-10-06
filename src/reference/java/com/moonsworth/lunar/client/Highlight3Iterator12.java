package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.entity.v1.FlipEntityMessage;
import com.lunarclient.apollo.entity.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.entity.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.entity.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator12 extends ApolloModuleHandler {
   private Set<Object> field4 = new HashSet<>();
   private Set<Object> field5 = new HashSet<>();

   public Highlight3Iterator12() {
      super("entity", "Entity");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideRainbowSheepMessage.class, ResetRainbowSheepMessage.class, FlipEntityMessage.class, ResetFlipedEntityMessage.class);
   }

   @Override
   protected void onEnable() {
      this.field4.clear();
      this.field5.clear();
   }

   @Override
   protected void onDisable() {
      this.field4.clear();
      this.field5.clear();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideRainbowSheepMessage.class).ifPresent(var1x -> this.field4.addAll(this.method8(var1x.getEntityIdsList())));
      var1.unpack(ResetRainbowSheepMessage.class).ifPresent(var1x -> this.field4.removeAll(this.method8(var1x.getEntityIdsList())));
      var1.unpack(FlipEntityMessage.class).ifPresent(var1x -> this.field5.addAll(this.method8(var1x.getEntityIdsList())));
      var1.unpack(ResetFlipedEntityMessage.class).ifPresent(var1x -> this.field5.removeAll(this.method8(var1x.getEntityIdsList())));
   }

   public boolean method3() {
      return ThreadModuleDump63.MC_VERSION >= 5 && ThreadModuleDump63.MC_VERSION <= 25;
   }

   public boolean method4(UUID var1) {
      return this.field4.contains(var1);
   }

   public boolean method5(int var1) {
      return this.field4.contains(var1);
   }

   public boolean method6(UUID var1) {
      return this.field5.contains(var1);
   }

   public boolean method7(int var1) {
      return this.field5.contains(var1);
   }

   private Set<Object> method8(List<EntityId> var1) {
      boolean var2 = this.method3();
      return var1.stream()
         .<ApolloEntity>map(NetworkTypes::fromProtobuf)
         .map(var1x -> var2 ? var1x.getEntityUuid() : var1x.getEntityId())
         .collect(Collectors.toSet());
   }

   @Generated
   public Set<Object> method9() {
      return this.field4;
   }

   @Generated
   public Set<Object> method10() {
      return this.field5;
   }

   @Generated
   public void method11(Set<Object> var1) {
      this.field4 = var1;
   }

   @Generated
   public void method12(Set<Object> var1) {
      this.field5 = var1;
   }
}
