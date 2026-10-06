package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.BedrockMaterial;
import com.moonsworth.lunar.client.render.particle.CameraFacing;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import mchorse.mclib.utils.Interpolations;
import mchorse.mclib.utils.resources.RLUtils;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;

public class NameplateCollisionAppearanceComponent extends BedrockComponentBase implements NameplateGlintRenderer {
   public BedrockMaterial field1 = BedrockMaterial.OPAQUE;
   public ResourceLocationBridge texture = BedrockScheme.field1;
   public MolangExpression field2;
   public MolangExpression field3;
   public MolangExpression field4;
   public CameraFacing field5;
   public int field6;
   public int field7;
   public MolangExpression field8;
   public MolangExpression field9;
   public MolangExpression field10;
   public MolangExpression field11;
   public boolean field12;
   public boolean field13;
   public float field14;
   public float field15;
   public float field16;
   public MolangExpression field17;
   public boolean field18;
   public boolean loop;
   private float field19;
   private float field20;
   private float field21;
   private float field22;
   private float field23;
   private float field24;
   private Matrix4f field25;
   private Matrix4f field26;
   private Vector4f[] field27;
   private Vector3f field28;

   public NameplateCollisionAppearanceComponent() {
      this.field2 = MolangParser.field3;
      this.field3 = MolangParser.field3;
      this.field4 = MolangParser.field3;
      this.field5 = CameraFacing.LOOKAT_XYZ;
      this.field6 = 128;
      this.field7 = 128;
      this.field8 = MolangParser.field3;
      this.field9 = MolangParser.field3;
      this.field10 = MolangParser.field3;
      this.field11 = MolangParser.field3;
      this.field13 = false;
      this.field17 = MolangParser.field3;
      this.field18 = false;
      this.loop = false;
      this.field25 = new Matrix4f();
      this.field26 = new Matrix4f();
      this.field27 = new Vector4f[]{
         new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)
      };
      this.field28 = new Vector3f();
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("enabled")) {
         this.field2 = glintcolorizer3iterator2.method5(json3.get("enabled"));
      }

      if (json3.has("lit")) {
         this.field12 = json3.get("lit").getAsBoolean();
      }

      if (json3.has("material")) {
         this.field1 = BedrockMaterial.fromString(json3.get("material").getAsString());
      }

      if (json3.has("texture")) {
         String text4 = json3.get("texture").getAsString();
         if (!text4.equals("textures/particle/particles")) {
            this.texture = RLUtils.create(text4);
         }
      }

      if (json3.has("size") && json3.get("size").isJsonArray()) {
         JsonArray array5 = json3.getAsJsonArray("size");
         if (array5.size() >= 2) {
            this.field3 = glintcolorizer3iterator2.method5(array5.get(0));
            this.field4 = glintcolorizer3iterator2.method5(array5.get(1));
         }
      }

      if (json3.has("facing_camera_mode")) {
         this.field5 = CameraFacing.fromString(json3.get("facing_camera_mode").getAsString());
      }

      if (json3.has("uv") && json3.get("uv").isJsonObject()) {
         this.method2(json3.get("uv").getAsJsonObject(), glintcolorizer3iterator2);
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   private void method2(JsonObject json1, MolangParser glintcolorizer3iterator2) {
      if (json1.has("texture_width")) {
         this.field6 = json1.get("texture_width").getAsInt();
      }

      if (json1.has("texture_height")) {
         this.field7 = json1.get("texture_height").getAsInt();
      }

      if (json1.has("uv") && json1.get("uv").isJsonArray()) {
         JsonArray array3 = json1.getAsJsonArray("uv");
         if (array3.size() >= 2) {
            this.field8 = glintcolorizer3iterator2.method5(array3.get(0));
            this.field9 = glintcolorizer3iterator2.method5(array3.get(1));
         }
      }

      if (json1.has("uv_size") && json1.get("uv_size").isJsonArray()) {
         JsonArray array4 = json1.getAsJsonArray("uv_size");
         if (array4.size() >= 2) {
            this.field10 = glintcolorizer3iterator2.method5(array4.get(0));
            this.field11 = glintcolorizer3iterator2.method5(array4.get(1));
         }
      }

      if (json1.has("flipbook") && json1.get("flipbook").isJsonObject()) {
         this.field13 = true;
         this.method3(json1.get("flipbook").getAsJsonObject(), glintcolorizer3iterator2);
      }
   }

   private void method3(JsonObject json1, MolangParser glintcolorizer3iterator2) {
      if (json1.has("base_UV") && json1.get("base_UV").isJsonArray()) {
         JsonArray array3 = json1.getAsJsonArray("base_UV");
         if (array3.size() >= 2) {
            this.field8 = glintcolorizer3iterator2.method5(array3.get(0));
            this.field9 = glintcolorizer3iterator2.method5(array3.get(1));
         }
      }

      if (json1.has("size_UV") && json1.get("size_UV").isJsonArray()) {
         JsonArray array4 = json1.getAsJsonArray("size_UV");
         if (array4.size() >= 2) {
            this.field10 = glintcolorizer3iterator2.method5(array4.get(0));
            this.field11 = glintcolorizer3iterator2.method5(array4.get(1));
         }
      }

      if (json1.has("step_UV") && json1.get("step_UV").isJsonArray()) {
         JsonArray array5 = json1.getAsJsonArray("step_UV");
         if (array5.size() >= 2) {
            this.field14 = array5.get(0).getAsFloat();
            this.field15 = array5.get(1).getAsFloat();
         }
      }

      if (json1.has("frames_per_second")) {
         this.field16 = json1.get("frames_per_second").getAsFloat();
      }

      if (json1.has("max_frame")) {
         this.field17 = glintcolorizer3iterator2.method5(json1.get("max_frame"));
      }

      if (json1.has("stretch_to_lifetime")) {
         this.field18 = json1.get("stretch_to_lifetime").getAsBoolean();
      }

      if (json1.has("loop")) {
         this.loop = json1.get("loop").getAsBoolean();
      }
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();
      JsonObject json3 = new JsonObject();
      array2.add(this.field3.method5());
      array2.add(this.field4.method5());
      json1.add("enabled", this.field2.method5());
      json3.addProperty("texture_width", this.field6);
      json3.addProperty("texture_height", this.field7);
      json1.addProperty("lit", this.field12);
      json1.addProperty("material", this.field1.id);
      if (this.texture != null && !this.texture.equals(BedrockScheme.field1)) {
         json1.addProperty("texture", this.texture.toString());
      }

      if (!this.field13 && !MolangExpression.method1(this.field8) || !MolangExpression.method1(this.field9)) {
         JsonArray array4 = new JsonArray();
         array4.add(this.field8.method5());
         array4.add(this.field9.method5());
         json3.add("uv", array4);
      }

      if (!this.field13 && !MolangExpression.method1(this.field10) || !MolangExpression.method1(this.field11)) {
         JsonArray array6 = new JsonArray();
         array6.add(this.field10.method5());
         array6.add(this.field11.method5());
         json3.add("uv_size", array6);
      }

      if (this.field13) {
         JsonObject json7 = new JsonObject();
         if (!MolangExpression.method1(this.field8) || !MolangExpression.method1(this.field9)) {
            JsonArray array5 = new JsonArray();
            array5.add(this.field8.method5());
            array5.add(this.field9.method5());
            json7.add("base_UV", array5);
         }

         if (!MolangExpression.method1(this.field10) || !MolangExpression.method1(this.field11)) {
            JsonArray array8 = new JsonArray();
            array8.add(this.field10.method5());
            array8.add(this.field11.method5());
            json7.add("size_UV", array8);
         }

         if (this.field14 != 0.0F || this.field15 != 0.0F) {
            JsonArray array9 = new JsonArray();
            array9.add(this.field14);
            array9.add(this.field15);
            json7.add("step_UV", array9);
         }

         if (this.field16 != 0.0F) {
            json7.addProperty("frames_per_second", this.field16);
         }

         if (!MolangExpression.method1(this.field17)) {
            json7.add("max_frame", this.field17.method5());
         }

         if (this.field18) {
            json7.addProperty("stretch_to_lifetime", true);
         }

         if (this.loop) {
            json7.addProperty("loop", true);
         }

         json3.add("flipbook", json7);
      }

      json1.add("size", array2);
      json1.addProperty("facing_camera_mode", this.field5.id);
      json1.add("uv", json3);
      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public void method1(HologramParticleEmitter glintcolorizer5iterator1, BedrockParticle glintcolorizer4_22, DrawBufferBridge bridge2_323, AbstractRenderContext bridgeextension_94, float value5, ResourceLocationBridge horsestats146) {
      boolean flag7 = false;
      float value8 = bridgeextension_94.method28();
      if (!glintcolorizer4_22.field18) {
         if (glintcolorizer4_22.field19) {
            flag7 = glintcolorizer5iterator1.field6;
            glintcolorizer5iterator1.field6 = this.field12;
            glintcolorizer5iterator1.field6 = flag7;
         }
      } else {
         if (!glintcolorizer4_22.field19) {
            flag7 = this.field12;
            this.field12 = glintcolorizer5iterator1.field6;
         }

         this.method10(glintcolorizer4_22, value8);
         double value9 = Interpolations.lerp(glintcolorizer4_22.field27.x, glintcolorizer4_22.field25.x, value8);
         double value11 = Interpolations.lerp(glintcolorizer4_22.field27.y, glintcolorizer4_22.field25.y, value8);
         double value13 = Interpolations.lerp(glintcolorizer4_22.field27.z, glintcolorizer4_22.field25.z, value8);
         float value15 = Interpolations.lerp(glintcolorizer4_22.field21, glintcolorizer4_22.rotation, value8);
         Vector3d vector3d16 = this.method8(glintcolorizer5iterator1, glintcolorizer4_22, value9, value11, value13);
         value9 = vector3d16.x;
         value11 = vector3d16.y;
         value13 = vector3d16.z;
         float value17 = glintcolorizer5iterator1.field33;
         float value18 = glintcolorizer5iterator1.field34;
         double value19 = glintcolorizer5iterator1.field35;
         double value21 = glintcolorizer5iterator1.field36;
         double value23 = glintcolorizer5iterator1.field37;
         boolean flag25 = this.field5 == CameraFacing.LOOKAT_XYZ || this.field5 == CameraFacing.LOOKAT_Y;
         if (glintcolorizer5iterator1.perspective == 2) {
            this.field19 = -this.field19;
         } else if (glintcolorizer5iterator1.perspective == 100 && !flag25) {
            value17 = 180.0F - value17;
         }

         if (flag25) {
            double value26 = value19 - value9;
            double value28 = value21 - value11;
            double value30 = value23 - value13;
            double value32 = Math.sqrt(value26 * value26 + value30 * value30);
            value17 = 180.0F - (float)(Math.atan2(value30, value26) * (180.0 / Math.PI)) - 90.0F;
            value18 = (float)(-(Math.atan2(value28, value32) * (180.0 / Math.PI))) + 180.0F;
         }

         int number38 = this.field12 ? Bridge.method8().method92() : glintcolorizer5iterator1.method17(value8, value9, value11, value13);
         this.method7(glintcolorizer5iterator1, glintcolorizer4_22);
         this.field25.setIdentity();
         if (this.field5 == CameraFacing.ROTATE_XYZ || this.field5 == CameraFacing.LOOKAT_XYZ) {
            this.field26.rotY(value17 / 180.0F * (float) Math.PI);
            this.field25.mul(this.field26);
            this.field26.rotX(value18 / 180.0F * (float) Math.PI);
            this.field25.mul(this.field26);
         } else if (this.field5 == CameraFacing.ROTATE_Y || this.field5 == CameraFacing.LOOKAT_Y) {
            this.field26.rotY(value17 / 180.0F * (float) Math.PI);
            this.field25.mul(this.field26);
         }

         this.field26.rotZ(value15 / 180.0F * (float) Math.PI);
         this.field25.mul(this.field26);
         this.field25.setTranslation(new Vector3f((float)(value9 - value19), (float)(value11 - value21), (float)(value13 - value23)));
         this.field25.setScale(value5);

         for (Vector4f vector4f43 : this.field27) {
            this.field25.transform(vector4f43);
         }

         float value39 = this.field21 / this.field6;
         float value41 = this.field23 / this.field6;
         float value42 = this.field22 / this.field7;
         float value44 = this.field24 / this.field7;
         bridge2_323.method2(this.field27[0].x, this.field27[0].y, this.field27[0].z)
            .method10(value39, value42)
            .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
            .method11(number38)
            .method16();
         bridge2_323.method2(this.field27[1].x, this.field27[1].y, this.field27[1].z)
            .method10(value41, value42)
            .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
            .method11(number38)
            .method16();
         bridge2_323.method2(this.field27[2].x, this.field27[2].y, this.field27[2].z)
            .method10(value41, value44)
            .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
            .method11(number38)
            .method16();
         bridge2_323.method2(this.field27[3].x, this.field27[3].y, this.field27[3].z)
            .method10(value39, value44)
            .method8(glintcolorizer4_22.field35, glintcolorizer4_22.field36, glintcolorizer4_22.field37, glintcolorizer4_22.field38)
            .method11(number38)
            .method16();
         if (!glintcolorizer4_22.field19) {
            this.field12 = flag7;
         }
      }
   }

   protected void method7(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      float value3 = this.field19 * 0.5F;
      float value4 = this.field20 * 0.5F;
      if (glintcolorizer4_22.field11) {
         float value5 = glintcolorizer5_21.getScale();
         value3 *= value5;
         value4 *= value5;
      }

      this.field27[0].set(-value3, -value4, 0.0F, 1.0F);
      this.field27[1].set(value3, -value4, 0.0F, 1.0F);
      this.field27[2].set(value3, value4, 0.0F, 1.0F);
      this.field27[3].set(-value3, value4, 0.0F, 1.0F);
   }

   protected Vector3d method8(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22, double value3, double value5, double value7) {
      if (glintcolorizer4_22.field7 && glintcolorizer4_22.field8) {
         this.field28.set((float)value3, (float)value5, (float)value7);
         if (glintcolorizer4_22.field10 && glintcolorizer5_21.getScale() != 1.0F) {
            Vector3d vector3d14 = new Vector3d(value3, value5, value7);
            Matrix3d matrix3d15 = new Matrix3d(glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale());
            matrix3d15.transform(vector3d14);
            this.field28.x = (float)vector3d14.x;
            this.field28.y = (float)vector3d14.y;
            this.field28.z = (float)vector3d14.z;
         }

         glintcolorizer5_21.field10.transform(this.field28);
         value3 = this.field28.x;
         value5 = this.field28.y;
         value7 = this.field28.z;
         value3 += glintcolorizer5_21.field8.x;
         value5 += glintcolorizer5_21.field8.y;
         value7 += glintcolorizer5_21.field8.z;
      } else if (glintcolorizer4_22.field10 && glintcolorizer5_21.getScale() != 1.0F) {
         Vector3d vector3d9 = new Vector3d(value3, value5, value7);
         Matrix3d matrix3d10 = new Matrix3d(glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale(), 0.0, 0.0, 0.0, glintcolorizer5_21.getScale());
         vector3d9.sub(glintcolorizer5_21.field8);
         matrix3d10.transform(vector3d9);
         vector3d9.add(glintcolorizer5_21.field8);
         value3 = vector3d9.x;
         value5 = vector3d9.y;
         value7 = vector3d9.z;
      }

      return new Vector3d(value3, value5, value7);
   }

   @Override
   public void method2(BedrockParticle glintcolorizer4_21, int number2, int number3, float value4, AbstractRenderContext bridgeextension_95, ResourceLocationBridge horsestats146) {
      float value7 = bridgeextension_95.method28();
      if (glintcolorizer4_21.field18) {
         this.method10(glintcolorizer4_21, value7);
         this.field19 = this.field20 = 0.5F;
         float value8 = Interpolations.lerp(glintcolorizer4_21.field21, glintcolorizer4_21.rotation, value7);
         this.field27[0].set(-this.field19 / 2.0F, -this.field20 / 2.0F, 0.0F, 1.0F);
         this.field27[1].set(this.field19 / 2.0F, -this.field20 / 2.0F, 0.0F, 1.0F);
         this.field27[2].set(this.field19 / 2.0F, this.field20 / 2.0F, 0.0F, 1.0F);
         this.field27[3].set(-this.field19 / 2.0F, this.field20 / 2.0F, 0.0F, 1.0F);
         this.field25.setIdentity();
         this.field25.setScale(value4 * 2.75F);
         this.field25.setTranslation(new Vector3f(number2, number3 - value4 / 2.0F, 0.0F));
         this.field26.rotZ(value8 / 180.0F * (float) Math.PI);
         this.field25.mul(this.field26);

         for (Vector4f vector4f12 : this.field27) {
            this.field25.transform(vector4f12);
         }

         float value14 = this.field21 / this.field6;
         float value15 = this.field23 / this.field6;
         float value16 = this.field22 / this.field7;
         float value17 = this.field24 / this.field7;
         DrawBufferBridge bridge2_3213 = bridgeextension_95.method10(LunarRenderTypes.field33.get(horsestats146));
         bridge2_3213.method1();
         bridge2_3213.method2(this.field27[0].x, this.field27[0].y, this.field27[0].z)
            .method10(value14, value16)
            .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
            .method16();
         bridge2_3213.method2(this.field27[1].x, this.field27[1].y, this.field27[1].z)
            .method10(value15, value16)
            .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
            .method16();
         bridge2_3213.method2(this.field27[2].x, this.field27[2].y, this.field27[2].z)
            .method10(value15, value17)
            .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
            .method16();
         bridge2_3213.method2(this.field27[3].x, this.field27[3].y, this.field27[3].z)
            .method10(value14, value17)
            .method8(glintcolorizer4_21.field35, glintcolorizer4_21.field36, glintcolorizer4_21.field37, glintcolorizer4_21.field38)
            .method16();
         bridge2_3213.method17(BufferMode.BATCHED);
      }
   }

   public void method10(BedrockParticle glintcolorizer4_21, float value2) {
      this.field19 = (float)this.field3.get() * 2.25F;
      this.field20 = (float)this.field4.get() * 2.25F;
      float value3 = (float)this.field8.get();
      float value4 = (float)this.field9.get();
      float value5 = (float)this.field10.get();
      float value6 = (float)this.field11.get();
      if (this.field13) {
         int number7 = (int)(glintcolorizer4_21.method1(value2) * this.field16);
         int number8 = (int)this.field17.get();
         if (this.field18) {
            float value9 = 0.0F;
            if (glintcolorizer4_21.lifetime != 0) {
               value9 = 0.0F;
            }

            number7 = (int)(value9 * number8);
         }

         if (this.loop && number8 != 0) {
            number7 %= number8;
         }

         if (number7 > number8) {
            number7 = number8;
         }

         value3 += this.field14 * number7;
         value4 += this.field15 * number7;
      }

      this.field21 = value3;
      this.field22 = value4;
      this.field23 = value3 + value5;
      this.field24 = value4 + value6;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public int method1() {
      return 200;
   }
}
