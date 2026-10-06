package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@Annotation2(min = 1)
public class MixinHelper2_2 extends MixinHelper2_3 {
   private final Map<String, List<ThreadModuleDump70>> field2 = new HashMap<>();
   private List<ThreadModuleDump70> field3 = new ArrayList<>();
   private List<ThreadModuleDump70> field4 = new ArrayList<>();
   private boolean field5 = false;
   private boolean field6 = false;
   private Map<String, List<MixinHelper_2>> field7 = new HashMap<>();
   private Map<String, List<MixinHelper_2>> field8 = new HashMap<>();
   private boolean field9 = false;
   private int field10 = 0;
   private int field11 = 0;

   public MixinHelper2_2(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void start() {
      this.field7 = this.field8;
      this.field8 = new HashMap<>();
      this.field5 = this.field6;
      this.field6 = false;
      if (this.field5) {
         this.method7().method17();
      }

      this.field3 = this.field2.get(this.method15());
      this.field4 = new ArrayList<>();
      this.field2.put(this.method15(), this.field4);
   }

   public void method1(int var1, int var2) {
      this.field10 = var1;
      this.field11 = var2;
   }

   public boolean method2(String var1, int var2, int var3, int var4) {
      if (var4 > 1) {
         return false;
      }

      if (!this.field1.method73() && !this.field3.stream().anyMatch(var2x -> var2x.method9(var2, var3))) {
         return false;
      }

      this.field9 = true;
      return true;
   }

   public boolean method3(String var1, int var2, int var3, int var4) {
      if (var4 > 1) {
         return false;
      }

      this.field6 = true;
      if (!this.field9) {
         return false;
      }

      this.field9 = false;
      if (!this.field8.containsKey(var1)) {
         this.field8.put(var1, new ArrayList<>());
      }

      this.field8.get(var1).add(new MixinHelper_2(var2, var3, var4));
      this.method9().method10(true);
      return true;
   }

   public MixinHelper_2 method4(int var1, int var2, int var3, int var4) {
      if (!this.method1().field2) {
         return null;
      }

      if (!this.field1.method73()) {
         ThreadModuleDump70 var5 = ThreadModuleDump70.of(var1, var2, var3, var4);
         ThreadModuleDump70 var6 = this.method3().method6(var5);
         if (var6 != null) {
            this.field4.add(var6);
         }
      }

      if (!this.field7.containsKey(this.method15())) {
         return null;
      }

      Iterator var7 = this.field7.get(this.method15()).iterator();

      while (var7.hasNext()) {
         MixinHelper_2 var8 = (MixinHelper_2)var7.next();
         if (var8.method1(var1, var2, var3, var4) && this.method3().method4(var8.x(), var8.y())) {
            var7.remove();
            return var8;
         }
      }

      return null;
   }

   public boolean method5(int var1, int var2, int var3, int var4) {
      return this.field10 >= var1
         && this.field10 <= var1 + var3
         && this.field11 >= var2
         && this.field11 <= var2 + var4
         && this.method3().method4(this.field10, this.field11);
   }

   public boolean method17() {
      return ThreadModuleDump63.method3().bridge$isMouseButtonDown(0) || ThreadModuleDump63.method3().bridge$isMouseButtonDown(1);
   }

   @Generated
   public int method18() {
      return this.field10;
   }

   @Generated
   public int method19() {
      return this.field11;
   }
}
