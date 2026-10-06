package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.bridge.CameraBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import lombok.Generated;

public abstract class BedrockEmitter {
   public BridgeExtension field1;
   public Itemcounter6 field2;
   public BedrockScheme field3;
   public List<BedrockParticle> field4 = new ArrayList<>();
   public Map<String, IValue> field5;
   public boolean field6;
   public boolean running = true;
   public int field7;
   public Vector3d field8 = new Vector3d();
   public Vector3d field9 = new Vector3d();
   public Matrix3f field10 = new Matrix3f(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   public Matrix3f field11 = new Matrix3f(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   public Vector3d field12 = new Vector3d();
   public int field13;
   public int lifetime;
   public double field14;
   public boolean playing = true;
   ThreadLocalRandom field15 = ThreadLocalRandom.current();
   public float field16 = this.field15.nextFloat();
   public float field17 = this.field15.nextFloat();
   public float field18 = this.field15.nextFloat();
   public float field19 = this.field15.nextFloat();
   private Variable field20;
   private Variable field21;
   private Variable field22;
   private Variable field23;
   private Variable field24;
   private Variable field25;
   private Variable field26;
   private Variable field27;
   private Variable field28;
   private Variable field29;
   private Variable field30;
   private Variable field31;
   protected float scale = 1.0F;

   public BedrockEmitter() {
   }

   public boolean isFinished() {
      return !this.running && this.field4.isEmpty();
   }

   public double method1() {
      return this.method2(0.0F);
   }

   public double method2(float value1) {
      return (this.field13 + value1) / 20.0;
   }

   public void method3(BedrockScheme glintcolorizer3_21) {
      this.field3 = glintcolorizer3_21;
      if (this.field3 != null) {
         this.field6 = true;
         this.stop();
         this.start();
         this.method5();
         this.method7(0.0F);

         for (IComponentEmitterInitialize glintcolorizerextension3 : this.field3.field6) {
            glintcolorizerextension3.method1(this);
         }
      }
   }

   public void method4() {
      this.field10.setIdentity();
   }

   public void method5() {
      this.field20 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_age");
      this.field21 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_lifetime");
      this.field22 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_random_1");
      this.field23 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_random_2");
      this.field24 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_random_3");
      this.field25 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.particle_random_4");
      this.field26 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_age");
      this.field27 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_lifetime");
      this.field28 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_random_1");
      this.field29 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_random_2");
      this.field30 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_random_3");
      this.field31 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get("variable.emitter_random_4");
   }

   public void method6(BedrockParticle glintcolorizer4_21, float value2) {
      if (this.field20 != null) {
         this.field20.set(glintcolorizer4_21.method1(value2));
      }

      if (this.field21 != null) {
         this.field21.set(glintcolorizer4_21.lifetime / 20.0);
      }

      if (this.field22 != null) {
         this.field22.set(glintcolorizer4_21.field1);
      }

      if (this.field23 != null) {
         this.field23.set(glintcolorizer4_21.field2);
      }

      if (this.field24 != null) {
         this.field24.set(glintcolorizer4_21.field3);
      }

      if (this.field25 != null) {
         this.field25.set(glintcolorizer4_21.field4);
      }

      this.field3.method16();
   }

   public void method7(float value1) {
      if (this.field26 != null) {
         this.field26.set(this.method2(value1));
      }

      if (this.field27 != null) {
         this.field27.set(this.lifetime / 20.0);
      }

      if (this.field28 != null) {
         this.field28.set(this.field16);
      }

      if (this.field29 != null) {
         this.field29.set(this.field17);
      }

      if (this.field30 != null) {
         this.field30.set(this.field18);
      }

      if (this.field31 != null) {
         this.field31.set(this.field19);
      }

      this.field3.method16();
   }

   public void method8(Map<String, String> map1) {
      this.field5 = new HashMap<>();

      for (Entry entry3 : map1.entrySet()) {
         this.method9((String)entry3.getKey(), (String)entry3.getValue());
      }
   }

   public void method9(String text1, String text2) {
      try {
         this.field5.put(text1, this.field3.field12.method4(text2));
      } catch (Exception exception4) {
      }
   }

   public void method10() {
      if (this.field5 != null) {
         for (Entry entry2 : this.field5.entrySet()) {
            Variable glintcolorizer2handler223 = (Variable)this.field3.field12.HHCCROCRRCCOCHCOHIRHHIHHOCRRCH.get(entry2.getKey());
            if (glintcolorizer2handler223 != null) {
               glintcolorizer2handler223.set(((IValue)entry2.getValue()).method1().doubleValue());
            }
         }
      }
   }

   public void start() {
      if (!this.playing) {
         this.field13 = 0;
         this.field14 = 0.0;
         this.playing = true;
      }
   }

   public void stop() {
      if (this.playing) {
         this.field14 = 0.0;
         this.playing = false;
         ThreadLocalRandom threadlocalrandom1 = ThreadLocalRandom.current();
         this.field16 = threadlocalrandom1.nextFloat();
         this.field17 = threadlocalrandom1.nextFloat();
         this.field18 = threadlocalrandom1.nextFloat();
         this.field19 = threadlocalrandom1.nextFloat();
      }
   }

   public void update() {
      if (this.field3 != null) {
         this.method7(0.0F);

         for (IComponentEmitterUpdate glintcolorizerextension22 : this.field3.field7) {
            glintcolorizerextension22.method2(this);
         }

         this.method7(0.0F);
         this.method11();
         this.field13++;
      }
   }

   private void method11() {
      ArrayList list1 = new ArrayList();

      for (BedrockParticle glintcolorizer4_24 : new ArrayList<>(this.field4)) {
         this.method12(glintcolorizer4_24);
         if (glintcolorizer4_24.field6) {
            list1.add(glintcolorizer4_24);
         }
      }

      this.field4.removeAll(list1);
   }

   private void method12(BedrockParticle glintcolorizer4_21) {
      if (glintcolorizer4_21 != null) {
         glintcolorizer4_21.method4(this);
         this.method6(glintcolorizer4_21, 0.0F);

         for (IComponentParticleUpdate glintcolorizerextension43 : this.field3.field9) {
            glintcolorizerextension43.method1(this, glintcolorizer4_21);
         }
      }
   }

   public void method13() {
      if (this.running) {
         this.field4.add(Objects.requireNonNull(this.method14(false)));
      }
   }

   protected BedrockParticle method14(boolean flag1) {
      BedrockParticle glintcolorizer4_22 = new BedrockParticle();
      this.method6(glintcolorizer4_22, 0.0F);
      glintcolorizer4_22.method5(this);

      for (IComponentParticleInitialize glintcolorizerextension54 : this.field3.field8) {
         glintcolorizerextension54.method2(this, glintcolorizer4_22);
      }

      if (glintcolorizer4_22.field7 && !glintcolorizer4_22.field8) {
         Vector3f vector3f5 = new Vector3f(glintcolorizer4_22.field25);
         glintcolorizer4_22.field28.transform(vector3f5);
         glintcolorizer4_22.field25.x = vector3f5.x;
         glintcolorizer4_22.field25.y = vector3f5.y;
         glintcolorizer4_22.field25.z = vector3f5.z;
      }

      if (!glintcolorizer4_22.field7 || !glintcolorizer4_22.field8) {
         glintcolorizer4_22.field25.add(this.field8);
         glintcolorizer4_22.field26.add(this.field8);
      }

      glintcolorizer4_22.field27.set(glintcolorizer4_22.field25);
      glintcolorizer4_22.rotation = glintcolorizer4_22.field20;
      glintcolorizer4_22.field21 = glintcolorizer4_22.rotation;
      return glintcolorizer4_22;
   }

   public abstract double method13(CameraBridge bridge2_191);

   public abstract void method7(AbstractRenderContext bridgeextension_91, CameraBridge bridge2_192);

   public abstract int method17(float value1, double value2, double value4, double value6);

   @Generated
   public void setScale(float value1) {
      this.scale = value1;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }
}
