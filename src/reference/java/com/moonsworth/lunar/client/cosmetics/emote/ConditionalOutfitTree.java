package com.moonsworth.lunar.client.cosmetics.emote;

import com.lunarclient.websocket.cosmetic.v2.OutfitTree;
import com.lunarclient.websocket.cosmetic.v2.OutfitTree.Builder;
import com.lunarclient.websocket.cosmetic.v2.OutfitTree.ConditionalOutfit;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.OutfitManager;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ConditionalOutfitTree {
   private final List<ConditionalOutfitTree.ConditionalOutfitEntry> field1;
   private Outfit field2;

   @Nullable
   public Outfit method1(@Nullable Bridge6_10 bridge6_101) {
      for (ConditionalOutfitTree.ConditionalOutfitEntry data53 : this.field1) {
         if (bridge6_101 != null) {
            boolean flag4 = data53.method1().method1(bridge6_101);
            if (flag4) {
               return data53.method2();
            }
         }
      }

      return this.field2;
   }

   public List<Outfit> method2() {
      ArrayList list1 = new ArrayList();

      for (ConditionalOutfitTree.ConditionalOutfitEntry data53 : this.field1) {
         list1.add(data53.method2());
      }

      return list1;
   }

   public static ConditionalOutfitTree method3(OutfitTree outfittree0) {
      OutfitManager foghandler261 = Ref.method4().method55();
      ArrayList list2 = new ArrayList();

      for (ConditionalOutfit conditionaloutfit4 : outfittree0.getConditionalOutfitsList()) {
         EquipConditionPredicate rewindhandlers5 = EquipConditionPredicate.method4(conditionaloutfit4.getEquipCondition());
         UUID uuid6 = ProtoConverter.method1(conditionaloutfit4.getOutfitId());
         Outfit gui2iterator7 = (Outfit)foghandler261.method2().get(uuid6);
         list2.add(new ConditionalOutfitTree.ConditionalOutfitEntry(rewindhandlers5, gui2iterator7));
      }

      UUID uuid8 = outfittree0.hasDefaultOutfitId() ? ProtoConverter.method1(outfittree0.getDefaultOutfitId()) : null;
      Outfit gui2iterator9 = (Outfit)foghandler261.method2().get(uuid8);
      return new ConditionalOutfitTree(list2, gui2iterator9);
   }

   public OutfitTree method4() {
      Builder builder1 = OutfitTree.newBuilder();

      for (ConditionalOutfitTree.ConditionalOutfitEntry data53 : this.field1) {
         builder1.addConditionalOutfits(
            ConditionalOutfit.newBuilder().setEquipCondition(data53.method1().method5()).setOutfitId(ProtoConverter.method3(data53.method2().getId())).build()
         );
      }

      if (this.field2 != null) {
         builder1.setDefaultOutfitId(ProtoConverter.method3(this.field2.getId()));
      }

      return builder1.build();
   }

   @Generated
   public ConditionalOutfitTree(List<ConditionalOutfitTree.ConditionalOutfitEntry> list1, Outfit gui2iterator2) {
      this.field1 = list1;
      this.field2 = gui2iterator2;
   }

   @Generated
   public Outfit method5() {
      return this.field2;
   }

   @Generated
   public void method6(Outfit iterator) {
      this.field2 = iterator;
   }

   public static class ConditionalOutfitEntry {
      private final EquipConditionPredicate field1;
      private final Outfit field2;

      @Generated
      public ConditionalOutfitEntry(EquipConditionPredicate rewindhandlers1, Outfit gui2iterator2) {
         this.field1 = rewindhandlers1;
         this.field2 = gui2iterator2;
      }

      @Generated
      public EquipConditionPredicate method1() {
         return this.field1;
      }

      @Generated
      public Outfit method2() {
         return this.field2;
      }
   }
}
