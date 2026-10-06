package com.moonsworth.lunar.client.mod.render.particlechanger;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;

public class ParticleChangerBlood extends ParticleStyle {
   private final ToggleOption playerBloodParticles = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("playerBloodParticles")
         .method4(true))
      .method31();
   private final ToggleOption entityBloodParticles = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("entityBloodParticles")
         .method4(true))
      .method31();
   private final ToggleOption selfBloodParticles = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("selfBloodParticles")
         .method4(true))
      .method31();
   private final ToggleOption playBloodSound = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("playBloodSound")
      .method31();
   private BridgeExtension lastAttackedEntity = null;
   private Vec3Bridge lastAttackPosition = null;

   private ParticleChangerBlood(ParticleChanger particlechanger1) {
      super(particlechanger1, ParticleType.BLOOD);
      this.handle(EventPreAttackEntity.class, this::method3);
      this.method3(EventTick.class, this::spawnBloodParticles);
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method4(() -> this.getParticleChanger().method21("bloodDisplayName", new Object[0])).method11(this);
   }

   @Override
   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.playerBloodParticles, this.entityBloodParticles, this.selfBloodParticles, this.playBloodSound});
      super.method2(lightingextension231);
   }

   private void method3(EventPreAttackEntity highlightimpl5_21) {
      this.lastAttackedEntity = highlightimpl5_21.method2();
      this.lastAttackPosition = highlightimpl5_21.method3();
   }

   private void spawnBloodParticles() {
      if (Ref.method8() != null && !this.method14()) {
         for (BridgeExtension bridgeextension2 : Ref.method8().bridge$entitiesForRendering()) {
            if ((bridgeextension2 != Ref.method7() || (Boolean)this.selfBloodParticles.get() && !this.getParticleChanger().method13())
               && !bridgeextension2.bridge$isInvisible()
               && bridgeextension2.bridge$isAlive()
               && this.wasJustHurt(bridgeextension2)
               && (!(bridgeextension2 instanceof Bridge6_10) || (Boolean)this.playerBloodParticles.get())
               && (!(bridgeextension2 instanceof EntityLivingBridge) || (Boolean)this.entityBloodParticles.get())) {
               double value3;
               double value5;
               double value7;
               if (this.lastAttackedEntity == bridgeextension2) {
                  value3 = this.lastAttackPosition.bridge$xCoord();
                  value5 = this.lastAttackPosition.bridge$yCoord();
                  value7 = this.lastAttackPosition.bridge$zCoord();
               } else {
                  value3 = bridgeextension2.bridge$getPosX();
                  value5 = bridgeextension2.bridge$getPosY() + bridgeextension2.bridge$getHeight() / 2.0;
                  value7 = bridgeextension2.bridge$getPosZ();
               }

               Ref.method3().bridge$getEffectRenderer().bridge$spawnBloodParticles(bridgeextension2.bridge$getWorld(), value3, value5, value7);
            }
         }
      }

      this.lastAttackedEntity = null;
      this.lastAttackPosition = null;
   }

   private boolean wasJustHurt(BridgeExtension bridgeextension1) {
      return bridgeextension1 instanceof EntityLivingBridge bridgeextension2_52 ? bridgeextension2_52.bridge$wasJustHurt() : false;
   }

   public boolean isPlayBloodSoundEnabled() {
      return this.isEnabled() && (Boolean)this.playBloodSound.get();
   }

   public static ParticleChangerBlood create(ParticleChanger particlechanger0) {
      String text1 = "PARTICLE_CHANGER_BLOOD_CHILD";
      return new ParticleChangerBlood(particlechanger0) {
         @Override
         public String getId() {
            return "PARTICLE_CHANGER_BLOOD_CHILD";
         }
      };
   }
}
