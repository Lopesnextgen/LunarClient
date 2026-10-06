package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge.Extension;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleUpdate;
import com.moonsworth.lunar.client.render.particle.Operation;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import java.util.List;
import javax.vecmath.Vector3d;

public class BedrockComponentMotionCollision extends BedrockComponentBase implements IComponentParticleUpdate {
   public MolangExpression field1 = MolangParser.field4;
   public float field2 = 0.0F;
   public float field3 = 1.0F;
   public float radius = 0.01F;
   public boolean field4;
   public boolean field5;
   private final Vector3d field6 = new Vector3d();
   private final Vector3d field7 = new Vector3d();
   private final Extension field8 = Bridge.method8().method9(0, 0, 0);

   public BedrockComponentMotionCollision() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("enabled")) {
         this.field1 = glintcolorizer3iterator2.method5(json3.get("enabled"));
      }

      if (json3.has("collision_drag")) {
         this.field2 = json3.get("collision_drag").getAsFloat();
      }

      if (json3.has("coefficient_of_restitution")) {
         this.field3 = json3.get("coefficient_of_restitution").getAsFloat();
      }

      if (json3.has("collision_radius")) {
         this.radius = json3.get("collision_radius").getAsFloat();
      }

      if (json3.has("expire_on_contact")) {
         this.field4 = json3.get("expire_on_contact").getAsBoolean();
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (MolangExpression.method1(this.field1)) {
         return json1;
      }

      if (!MolangExpression.method2(this.field1)) {
         json1.add("enabled", this.field1.method5());
      }

      if (this.field2 != 0.0F) {
         json1.addProperty("collision_drag", this.field2);
      }

      if (this.field3 != 1.0F) {
         json1.addProperty("coefficient_of_restitution", this.field3);
      }

      if (this.radius != 0.01F) {
         json1.addProperty("collision_radius", this.radius);
      }

      if (this.field4) {
         json1.addProperty("expire_on_contact", true);
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      if (glintcolorizer5_21.field2 != null) {
         if (!glintcolorizer4_22.field17 && !Operation.equals(this.field1.get(), 0.0)) {
            float value3 = this.radius;
            this.field6.set(glintcolorizer4_22.method3(glintcolorizer5_21, glintcolorizer4_22.field27));
            this.field7.set(glintcolorizer4_22.method2(glintcolorizer5_21));
            Vector3d vector3d4 = this.field6;
            Vector3d vector3d5 = this.field7;
            double value6 = vector3d5.x - vector3d4.x;
            double value8 = vector3d5.y - vector3d4.y;
            double value10 = vector3d5.z - vector3d4.z;
            boolean flag12 = Math.abs(value6) > 10.0 || Math.abs(value8) > 10.0 || Math.abs(value10) > 10.0;
            this.field8.method1(vector3d5.x, vector3d5.y, vector3d5.z);
            boolean flag13 = glintcolorizer5_21.field2 == HologramsIterator2.method14().bridge$getWorld();
            if (flag13) {
               return;
            }

            if (flag12 || !glintcolorizer5_21.field2.bridge$isBlockLoaded(this.field8)) {
               return;
            }

            AxisAlignedBBBridge horsestats1214 = Bridge.method8().method45(vector3d4.x - value3, vector3d4.y - value3, vector3d4.z - value3, vector3d4.x + value3, vector3d4.y + value3, vector3d4.z + value3);
            double value15 = value8;
            double value17 = value6;
            double value19 = value10;
            List list21 = glintcolorizer5_21.field2.bridge$getCollisionBoxes(glintcolorizer5_21.field1, horsestats1214.bridge$expand(value6, value8, value10)).stream().toList();

            for (AxisAlignedBBBridge horsestats1223 : list21) {
               value8 = horsestats1223.bridge$calculateYOffset(horsestats1214, value8);
            }

            horsestats1214 = horsestats1214.bridge$offset(0.0, value8, 0.0);

            for (AxisAlignedBBBridge horsestats1229 : list21) {
               value6 = horsestats1229.bridge$calculateXOffset(horsestats1214, value6);
            }

            horsestats1214 = horsestats1214.bridge$offset(value6, 0.0, 0.0);

            for (AxisAlignedBBBridge horsestats1230 : list21) {
               value10 = horsestats1230.bridge$calculateZOffset(horsestats1214, value10);
            }

            horsestats1214 = horsestats1214.bridge$offset(0.0, 0.0, value10);
            if (value15 != value8 || value17 != value6 || value19 != value10) {
               if (this.field4) {
                  glintcolorizer4_22.field6 = true;
                  return;
               }

               if (glintcolorizer4_22.field7) {
                  glintcolorizer4_22.field7 = false;
                  glintcolorizer4_22.field27.set(vector3d4);
               }

               vector3d5.set(horsestats1214.bridge$getMinX() + value3, horsestats1214.bridge$getMinY() + value3, horsestats1214.bridge$getMinZ() + value3);
               if (value15 != value8) {
                  glintcolorizer4_22.field32.y = glintcolorizer4_22.field32.y * -this.field3;
                  vector3d5.y += value15 < value8 ? value3 : -value3;
               }

               if (value17 != value6) {
                  glintcolorizer4_22.field32.x = glintcolorizer4_22.field32.x * -this.field3;
                  vector3d5.x += value17 < value6 ? value3 : -value3;
               }

               if (value19 != value10) {
                  glintcolorizer4_22.field32.z = glintcolorizer4_22.field32.z * -this.field3;
                  vector3d5.z += value19 < value10 ? value3 : -value3;
               }

               glintcolorizer4_22.field25.set(vector3d5);
               glintcolorizer4_22.field34 = glintcolorizer4_22.field34 + this.field2;
            }
         }
      }
   }

   @Override
   public int method1() {
      return 50;
   }
}
