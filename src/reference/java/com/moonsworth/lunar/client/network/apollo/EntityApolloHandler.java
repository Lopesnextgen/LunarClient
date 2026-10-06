package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.entity.v1.FlipEntityMessage;
import com.lunarclient.apollo.entity.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.entity.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.entity.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;

public class EntityApolloHandler extends ApolloModuleHandler {
   private Set<Object> field4 = new HashSet<>();
   private Set<Object> field5 = new HashSet<>();

   public EntityApolloHandler() {
      super("entity", "Entity");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideRainbowSheepMessage.class, ResetRainbowSheepMessage.class, FlipEntityMessage.class, ResetFlipedEntityMessage.class);
   }

   protected void onEnable() {
      this.field4.clear();
      this.field5.clear();
   }

   protected void onDisable() {
      this.field4.clear();
      this.field5.clear();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideRainbowSheepMessage.class).ifPresent(arg1x -> this.field4.addAll(this.method8(arg1x.getEntityIdsList())));
      highlightimpl_31.unpack(ResetRainbowSheepMessage.class).ifPresent(arg1x -> this.field4.removeAll(this.method8(arg1x.getEntityIdsList())));
      highlightimpl_31.unpack(FlipEntityMessage.class).ifPresent(arg1x -> this.field5.addAll(this.method8(arg1x.getEntityIdsList())));
      highlightimpl_31.unpack(ResetFlipedEntityMessage.class).ifPresent(arg1x -> this.field5.removeAll(this.method8(arg1x.getEntityIdsList())));
   }

   public boolean method3() {
      return Ref.MC_VERSION >= 5 && Ref.MC_VERSION <= 25;
   }

   public boolean method4(UUID uuid1) {
      return this.field4.contains(uuid1);
   }

   public boolean method5(int number1) {
      return this.field4.contains(number1);
   }

   public boolean method6(UUID uuid1) {
      return this.field5.contains(uuid1);
   }

   public boolean method7(int number1) {
      return this.field5.contains(number1);
   }

   private Set<Object> method8(List<EntityId> list) {
      boolean flag2 = this.method3();
      return list.stream()
         .<ApolloEntity>map(NetworkTypes::fromProtobuf)
         .map(arg1x -> flag2 ? arg1x.getEntityUuid() : arg1x.getEntityId())
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
   public void method11(Set<Object> set1) {
      this.field4 = set1;
   }

   @Generated
   public void method12(Set<Object> set1) {
      this.field5 = set1;
   }
}
