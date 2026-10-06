package com.moonsworth.lunar.client.render.particle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class BedrockScheme {
   public static ResourceLocationBridge field1;
   public static Gson field2;
   public String identifier = "";
   public BedrockMaterial field3 = BedrockMaterial.OPAQUE;
   public ResourceLocationBridge texture;
   public Map<String, BedrockCurve> field4;
   public List<BedrockComponentBase> field5;
   public List<IComponentEmitterInitialize> field6;
   public List<IComponentEmitterUpdate> field7;
   public List<IComponentParticleInitialize> field8;
   public List<IComponentParticleUpdate> field9;
   public List<IComponentParticleRender> field10;
   private boolean field11;
   public String bone;
   public MolangParser field12;

   public BedrockScheme() {
      this.texture = field1;
      this.field4 = new HashMap<>();
      this.field5 = new ArrayList<>();
      this.field12 = new MolangParser();
   }

   public static void method1(ResourceLocationBridge horsestats140) {
      field1 = horsestats140;
   }

   public static void method2(BedrockParticleDeserializer jsondeserializeriterator0) {
      field2 = new GsonBuilder().registerTypeAdapter(BedrockScheme.class, jsondeserializeriterator0).create();
   }

   public static BedrockScheme method3(String text0) {
      return (BedrockScheme)field2.fromJson(text0, BedrockScheme.class);
   }

   public static BedrockScheme method4(JsonElement element0) {
      return (BedrockScheme)field2.fromJson(element0, BedrockScheme.class);
   }

   public static JsonElement method5(BedrockScheme glintcolorizer3_20) {
      return field2.toJsonTree(glintcolorizer3_20);
   }

   public static BedrockScheme method6(BedrockScheme glintcolorizer3_20) {
      return method4(method5(glintcolorizer3_20));
   }

   public BedrockScheme method7(boolean flag1) {
      this.field11 = flag1;
      return this;
   }

   public boolean method8() {
      return this.field11;
   }

   public void setup() {
      this.method12(com.moonsworth.lunar.client.render.particle.BedrockComponentInitialSpeed.class);
      this.field6 = this.method9(IComponentEmitterInitialize.class);
      this.field7 = this.method9(IComponentEmitterUpdate.class);
      this.field8 = this.method9(IComponentParticleInitialize.class);
      this.field9 = this.method9(IComponentParticleUpdate.class);
      this.field10 = this.method9(IComponentParticleRender.class);

      for (Entry entry2 : this.field4.entrySet()) {
         ((BedrockCurve)entry2.getValue()).field5 = (Variable)this.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get(entry2.getKey());
      }
   }

   public <T extends IComponentBase> List<T> method9(Class<T> clazz1) {
      ArrayList list2 = new ArrayList();

      for (BedrockComponentBase glintcolorizer2_34 : this.field5) {
         if (clazz1.isAssignableFrom(glintcolorizer2_34.getClass())) {
            list2.add((IComponentBase)glintcolorizer2_34);
         }
      }

      if (list2.size() > 1) {
         Collections.sort(list2, Comparator.comparingInt(IComponentBase::method1));
      }

      return list2;
   }

   public <T extends BedrockComponentBase> T method10(Class<T> clazz1) {
      for (BedrockComponentBase glintcolorizer2_33 : this.field5) {
         if (clazz1.isAssignableFrom(glintcolorizer2_33.getClass())) {
            return (T)glintcolorizer2_33;
         }
      }

      return null;
   }

   public <T extends BedrockComponentBase> T method11(Class<T> clazz1) {
      BedrockComponentBase glintcolorizer2_32 = null;

      try {
         glintcolorizer2_32 = (BedrockComponentBase)clazz1.getConstructor().newInstance();
         this.field5.add(glintcolorizer2_32);
         this.setup();
      } catch (Exception exception4) {
      }

      return (T)glintcolorizer2_32;
   }

   public <T extends BedrockComponentBase> T method12(Class<T> clazz1) {
      return this.method13(clazz1, clazz1);
   }

   public <T extends BedrockComponentBase> T method13(Class<T> clazz1, Class clazz2) {
      BedrockComponentBase glintcolorizer2_33 = this.method10(clazz1);
      if (glintcolorizer2_33 == null) {
         glintcolorizer2_33 = this.method11(clazz2);
      }

      return (T)glintcolorizer2_33;
   }

   public <T extends BedrockComponentBase> T method14(Class<T> clazz1) {
      Iterator iterator2 = this.field5.iterator();

      while (iterator2.hasNext()) {
         BedrockComponentBase glintcolorizer2_33 = (BedrockComponentBase)iterator2.next();
         if (clazz1.isAssignableFrom(glintcolorizer2_33.getClass())) {
            iterator2.remove();
            return (T)glintcolorizer2_33;
         }
      }

      return null;
   }

   public <T extends BedrockComponentBase> T method15(Class<T> clazz1, Class clazz2) {
      this.method14(clazz1);
      return this.method11(clazz2);
   }

   public void method16() {
      for (BedrockCurve glintcolorizer_32 : this.field4.values()) {
         if (glintcolorizer_32.field5 != null) {
            glintcolorizer_32.field5.set(glintcolorizer_32.method1());
         }
      }
   }

   public BedrockScheme method17() {
      BedrockScheme glintcolorizer3_21 = new BedrockScheme();
      glintcolorizer3_21.identifier = "";
      glintcolorizer3_21.field3 = BedrockMaterial.OPAQUE;
      glintcolorizer3_21.texture = field1;
      glintcolorizer3_21.bone = this.bone;
      glintcolorizer3_21.field4 = this.field4;
      glintcolorizer3_21.field5 = this.field5;
      glintcolorizer3_21.field6 = this.field6;
      glintcolorizer3_21.field7 = this.field7;
      glintcolorizer3_21.field8 = this.field8;
      glintcolorizer3_21.field9 = this.field9;
      glintcolorizer3_21.field10 = this.field10;
      glintcolorizer3_21.field11 = this.field11;
      return glintcolorizer3_21;
   }

   @Generated
   public String getBone() {
      return this.bone;
   }

   static {
      method2(new com.moonsworth.lunar.client.render.particle.BlockbusterParticleDeserializer());
      method1(ResourceLocationBridge.create("lunar", "particles/textures/default_particles.webp"));
   }
}
