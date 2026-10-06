package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.EntityMagmaCubeBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.KuudraTier;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import lombok.Generated;
import org.joml.Vector3d;

public class KuudraBossHealth extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final KuudraTierListener field7 = (KuudraTierListener)this.method3(KuudraTierListener.class);
   private EntityMagmaCubeBridge field8;
   private boolean field9;

   public KuudraBossHealth() {
      this.handle(EventTick.class, this::method2);
      this.handle(TypedChatMessage.class, this::method3);
      this.handle(EventWorldChange.class, this::method4);
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         if (this.field8 != null) {
            if (!this.field8.bridge$isRemoved()) {
               return;
            }

            this.field8 = null;
         }

         for (BridgeExtension bridgeextension4 : Ref.method8().bridge$getEntities()) {
            if (bridgeextension4 != null && bridgeextension4 instanceof EntityMagmaCubeBridge bridge5extension35 && bridge5extension35.bridge$getSize() == 30 && bridge5extension35.bridge$getUnboundedHealth() <= 100000.0F) {
               this.field8 = bridge5extension35;
               return;
            }
         }
      }
   }

   private void method2(EventTick highlightimpl21) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         if (this.field8 != null && this.method7() && !this.field9) {
            float value2 = this.field8.bridge$getUnboundedHealth();
            if (value2 < 25000.0F && value2 > 1024.0F) {
               this.field9 = true;
            }
         }
      }
   }

   private void method3(TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.KUUDRA) {
         if (this.method7() && !this.field9) {
            String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
            if (text2.equals("[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!")) {
               this.field9 = true;
            }
         }
      }
   }

   private void method4(EventWorldChange data31) {
      this.field8 = null;
      this.field9 = false;
   }

   public float getHealth() {
      if (this.field8 == null) {
         return 0.0F;
      }

      float value1 = this.field8.bridge$getUnboundedHealth();
      if (this.method7()) {
         if (this.field9) {
            value1 = (value1 - 1.0F) * 9600.0F;
            if (value1 < 0.0F) {
               value1 = 0.0F;
            }
         } else {
            value1 = (value1 - 25000.0F) / 3.0F * 4.0F;
            if (value1 < 0.0F) {
               value1 = 0.0F;
            }
         }
      }

      return value1;
   }

   public int method5() {
      return this.field9 ? 240000000 : 100000;
   }

   public Vector3d method6(float value1) {
      return this.field8 == null
         ? null
         : new Vector3d(
            MathUtils.method15(this.field8.CIHHIOCRRIHCHRHHCRCRHORHIHCCIO(), this.field8.bridge$getPosX(), value1),
            MathUtils.method15(this.field8.IHCIRIRCORRCOIHOHRRCOCOCOHIHCH(), this.field8.bridge$getPosY(), value1),
            MathUtils.method15(this.field8.CHCOHHHICHIICOHRIHRCOIOHCHROCH(), this.field8.bridge$getPosZ(), value1)
         );
   }

   private boolean method7() {
      return this.field7.method6() == KuudraTier.T5;
   }

   @Generated
   public KuudraTierListener method8() {
      return this.field7;
   }

   @Generated
   public EntityMagmaCubeBridge method9() {
      return this.field8;
   }

   @Generated
   public boolean method10() {
      return this.field9;
   }
}
