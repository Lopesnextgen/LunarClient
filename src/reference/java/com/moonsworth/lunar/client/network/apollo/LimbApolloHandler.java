package com.moonsworth.lunar.client.network.apollo;

import com.google.common.collect.Sets;
import com.google.protobuf.Message;
import com.lunarclient.apollo.limb.v1.HideArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.HideBodyPartMessage;
import com.lunarclient.apollo.limb.v1.ResetArmorPiecesMessage;
import com.lunarclient.apollo.limb.v1.ResetBodyPartMessage;
import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.BodyPart;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.highlight.ModelRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;

public class LimbApolloHandler extends ApolloModuleHandler {
   private final Map<UUID, Set<ArmorPiece>> field4 = new HashMap<>();
   private final Map<UUID, Set<BodyPart>> field5 = new HashMap<>();

   public LimbApolloHandler() {
      super("limb", "Limb");
      this.handle(ModelRenderEvent.BipedModelRenderEvent.class, this::method3);
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(HideArmorPiecesMessage.class, ResetArmorPiecesMessage.class, HideBodyPartMessage.class, ResetBodyPartMessage.class);
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
      var1.unpack(HideArmorPiecesMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            Set var3 = this.method4(var1x.getArmorPiecesList());
            this.field4.computeIfAbsent(var2, var0 -> Sets.newHashSet()).addAll(var3);
         }
      });
      var1.unpack(ResetArmorPiecesMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            Set var3 = this.method4(var1x.getArmorPiecesList());
            this.field4.computeIfAbsent(var2, var0 -> Sets.newHashSet()).removeAll(var3);
         }
      });
      var1.unpack(HideBodyPartMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            Set var3 = this.method5(var1x.getBodyPartsList());
            this.field5.computeIfAbsent(var2, var0 -> Sets.newHashSet()).addAll(var3);
         }
      });
      var1.unpack(ResetBodyPartMessage.class).ifPresent(var1x -> {
         if (var1x.hasPlayerUuid()) {
            UUID var2 = NetworkTypes.fromProtobuf(var1x.getPlayerUuid());
            Set var3 = this.method5(var1x.getBodyPartsList());
            this.field5.computeIfAbsent(var2, var0 -> Sets.newHashSet()).removeAll(var3);
         }
      });
   }

   private void method3(ModelRenderEvent.BipedModelRenderEvent var1) {
      if (var1.method2() instanceof BridgeExtension2_7 var2 && (ThreadModuleDump63.MC_VERSION > 0 || var2.bridge$isMainModel())) {
         ThreadModuleDump63.method4().method84().<ApolloModuleHandler>method3(LimbModule.class).ifPresent(var1x -> {
            LimbApolloHandler var2x = (LimbApolloHandler)var1x;
            Set var3 = var2x.method8().get(var1.method1().bridge$getUniqueID());
            if (var3 != null && !var3.isEmpty()) {
               var1.method4().addAll(var3);
            }
         });
      }
   }

   private Set<ArmorPiece> method4(List<com.lunarclient.apollo.limb.v1.ArmorPiece> var1) {
      ArmorPiece[] var2 = ArmorPiece.values();
      return var1.stream().map(var1x -> var2[var1x.ordinal() - 1]).collect(Collectors.toSet());
   }

   private Set<BodyPart> method5(List<com.lunarclient.apollo.limb.v1.BodyPart> var1) {
      BodyPart[] var2 = BodyPart.values();
      return var1.stream().map(var1x -> var2[var1x.ordinal() - 1]).collect(Collectors.toSet());
   }

   @Generated
   public Map<UUID, Set<ArmorPiece>> method6() {
      return this.field4;
   }

   @Generated
   public Map<UUID, Set<BodyPart>> method8() {
      return this.field5;
   }
}
