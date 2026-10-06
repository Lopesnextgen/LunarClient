package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.NameplateBillboardComponent;
import com.moonsworth.lunar.client.render.particle.NameplateTintingComponent;

public class BlockbusterParticleDeserializer extends com.moonsworth.lunar.client.render.particle.BedrockParticleDeserializer {
   public BlockbusterParticleDeserializer() {
      this.HIHHCCHICRCCCRCIROROCCHCICCCOH.put("minecraft:particle_appearance_billboard", NameplateBillboardComponent.class);
      this.HIHHCCHICRCCCRCIROROCCHCICCCOH.put("minecraft:particle_appearance_tinting", NameplateTintingComponent.class);
      this.HIHHCCHICRCCCRCIROROCCHCICCCOH
         .put("blockbuster:particle_collision_appearance", com.moonsworth.lunar.client.render.particle.NameplateCollisionAppearanceComponent.class);
      this.HIHHCCHICRCCCRCIROROCCHCICCCOH
         .put("blockbuster:particle_collision_tinting", com.moonsworth.lunar.client.render.particle.NameplateCollisionTintingComponent.class);
   }
}
