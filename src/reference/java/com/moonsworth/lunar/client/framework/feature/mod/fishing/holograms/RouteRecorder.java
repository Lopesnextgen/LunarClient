package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.PickaxeTier;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class RouteRecorder {
   private final RouteManager field1;
   private PickaxeTier field2 = PickaxeTier.NONE;
   @Nullable
   private DungeonRoute field3 = null;

   public void startRecording() {
      this.field3 = new DungeonRoute();
      this.field2 = PickaxeTier.NONE;
   }

   public void method1() {
      this.field3 = null;
      this.field2 = PickaxeTier.NONE;
   }

   public Optional<DungeonRoute> method2(String text, String text2) {
      Optional optional3 = Optional.ofNullable(this.field3);
      if (this.field3 != null) {
         this.field3.method5(this.field2);
         RouteManager holograms3_34 = SkyblockDungeonRoutes.method13().method16();
         holograms3_34.method10(this.field3, text, text2);
         this.field3 = null;
         this.field2 = PickaxeTier.NONE;
      }

      return optional3;
   }

   public void method3(int[] items1, NameplateType nameplatetype2) {
      if (this.field3 != null) {
         RouteSection holograms73 = this.field3.method2();
         this.method4(items1, nameplatetype2, holograms73);
      }
   }

   public void method4(int[] items1, NameplateType nameplatetype2, RouteSection holograms73) {
      if (this.field3 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = this.field1.method17().orElse(null);
         if (holograms34 != null) {
            int[] items5 = holograms34.method20(items1);
            if (!holograms73.method5(nameplatetype2, items5)) {
               holograms73.method1(nameplatetype2, items5);
               if (nameplatetype2.isSectionSeparator()) {
                  this.field3.method4();
               }

               if (nameplatetype2 == NameplateType.ETHERWARP) {
                  this.field3.method6();
               }
            }
         }
      }
   }

   public void method5(PickaxeTier pickaxeTier) {
      if (this.field3 != null && this.field2 != PickaxeTier.DUNGEONBREAKER) {
         this.field2 = pickaxeTier;
      }
   }

   public void method6() {
      if (this.field3 != null) {
         this.field3.method7();
      }
   }

   public void method7() {
      if (this.field3 != null) {
         this.field3.method8();
      }
   }

   public void method8() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (this.field3 != null && bridge5extension_51 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms32 = this.field1.method17().orElse(null);
         if (holograms32 != null) {
            int[] items3 = new int[]{(int)Math.floor(bridge5extension_51.bridge$getPosX()), (int)bridge5extension_51.bridge$getPosY(), (int)Math.floor(bridge5extension_51.bridge$getPosZ())};
            int[] items4 = holograms32.method20(items3);
            this.field3.method2().method2(items4);
         }
      }
   }

   public Optional<DungeonRoute> method9() {
      return Optional.ofNullable(this.field3);
   }

   public boolean method10() {
      return this.field3 != null;
   }

   public Optional<RouteSection> method11() {
      return this.method9().map(DungeonRoute::method2);
   }

   @Generated
   public RouteRecorder(RouteManager holograms3_31) {
      this.field1 = holograms3_31;
   }
}
