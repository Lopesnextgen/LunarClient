package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.render.particlechanger.ParticleChanger;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class ParticleChangerMigration implements ConfigMigration {
   public ParticleChangerMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof ParticleChanger) {
         ThreadModuleDump9.findJsonObject(json3, "options").ifPresent(arg2x -> {
            JsonObject json3x = new JsonObject();
            this.method2("showBloodParticles", arg2x, "enabled", json3x);
            JsonObject json4 = new JsonObject();
            this.method2("bloodMultiplier", arg2x, "particleMultiplier", json4);
            this.method3("playerBloodParticles", arg2x, json4);
            this.method3("entityBloodParticles", arg2x, json4);
            this.method3("playBloodSound", arg2x, json4);
            if (!json4.isEmpty()) {
               json3x.add("options", json4);
            }

            if (!json3x.isEmpty()) {
               json3.add("PARTICLE_CHANGER_BLOOD_CHILD", json3x);
            }
         });
      }
   }

   private void method2(String text1, JsonObject json2, String text3, JsonObject json4) {
      if (json2.has(text1)) {
         json4.add(text3, json2.remove(text1));
      }
   }

   private void method3(String text1, JsonObject json2, JsonObject json3) {
      this.method2(text1, json2, text1, json3);
   }
}
