package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType2;
import com.moonsworth.lunar.files.Files6_2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class Holograms2 {
   private List<Holograms7> sections = new ArrayList<>();
   private Holograms6_2 field1 = Holograms6_2.NONE;
   private Holograms11_2 field2 = new Holograms11_2();
   private List<Holograms10_2> field3 = new ArrayList<>();
   private String name = "An unnamed route";
   private String field4 = "An Unknown Player";
   private String field5 = "An unnamed route";

   public Holograms2(Holograms2 var1) {
      this.sections = var1.sections.stream().map(var1x -> new Holograms7(var1x, this)).collect(Collectors.toList());
      this.field1 = var1.field1;
      this.field2 = new Holograms11_2(var1.field2);
      this.field3 = var1.field3.stream().map(Holograms10_2::new).collect(Collectors.toList());
      this.name = var1.name;
      this.field4 = var1.field4;
      this.field5 = var1.field5;
   }

   public boolean method1(Holograms6_2 var1) {
      return this.field1.isSubsetOf(var1);
   }

   public Holograms7 method2() {
      if (this.sections.isEmpty()) {
         this.method4();
      }

      return this.sections.get(this.sections.size() - 1);
   }

   public Holograms7 method3() {
      if (this.sections.isEmpty()) {
         this.method4();
      }

      return this.sections.get(0);
   }

   public void method4() {
      this.sections.add(new Holograms7(this));
   }

   public void method5(NameplateType2 var1) {
      this.field1 = this.field1.setPickaxeTier(var1);
   }

   public void method6() {
      this.field1 = this.field1.setEtherwarpNeeded(true);
   }

   public void method7() {
      this.field1 = this.field1.setAoteNeeded(true);
   }

   public void method8() {
      this.field1 = this.field1.setPearls(true);
   }

   public void setName(String var1) {
      this.field5 = var1;
      String[] var2 = var1.split("-");
      this.field4 = var2[0];
      if (var2.length > 1) {
         this.name = var2[1];
      }
   }

   public void method9(DataOutputStream var1) {
      var1.writeInt(this.sections.size());

      for (Holograms7 var3 : this.sections) {
         var3.method3(var1);
      }
   }

   public static Holograms2 method10(DataInputStream var0) {
      Holograms2 var1 = new Holograms2();
      int var2 = var0.readInt();

      for (int var3 = 0; var3 < var2; var3++) {
         Holograms7 var4 = Holograms7.method4(var0, var1);
         if (!method12(var4)) {
            var1.sections.add(var4);
         } else {
            List var5 = method11(var4, var1);
            var1.sections.addAll(var5);
         }
      }

      return var1;
   }

   private static List<Holograms7> method11(Holograms7 var0, Holograms2 var1) {
      ArrayList var2 = new ArrayList();
      Holograms7 var3 = new Holograms7(var1);
      int var4 = 0;

      for (Files6_2 var6 : var0.method9()) {
         var3.method1((NameplateType)var6.field1, (int[])var6.field2);
         if (var6.field1 == NameplateType.ITEM_DROP && !var6.equals(var0.method9().get(var0.method9().size() - 1))) {
            double var7 = 10000.0;
            int var9 = 0;

            for (int var10 = var4; var10 < var0.method8().size(); var10++) {
               int[] var11 = var0.method8().get(var10);
               double var12 = Math.pow(var11[0] - ((int[])var6.field2)[0], 2.0)
                  + Math.pow(var11[1] - ((int[])var6.field2)[1], 2.0)
                  + Math.pow(var11[2] - ((int[])var6.field2)[2], 2.0);
               if (var12 < var7) {
                  var9 = var10;
                  var7 = var12;
               }
            }

            if (!(var7 > 100.0)) {
               for (int var15 = var4; var15 < var9; var15++) {
                  var3.method2(var0.method8().get(var15));
               }

               var4 = var9;
               var2.add(var3);
               var3 = new Holograms7(var1);
            }
         }
      }

      for (int var14 = var4; var14 < var0.method8().size(); var14++) {
         var3.method2(var0.method8().get(var14));
      }

      var2.add(var3);
      return var2;
   }

   private static boolean method12(Holograms7 var0) {
      for (Files6_2 var2 : var0.method9()) {
         if (var2.equals(var0.method9().get(var0.method9().size() - 1))) {
            return false;
         }

         if (var2.field1 == NameplateType.ITEM_DROP) {
            return true;
         }
      }

      return false;
   }

   public void method13(Holograms11_2 var1) {
      if (var1 == null) {
         var1 = new Holograms11_2();
      }

      this.field2 = var1;
   }

   public void method14(List<Holograms10_2> var1) {
      if (var1 == null) {
         var1 = new ArrayList();
      }

      this.field3 = var1;
   }

   public Holograms10_2 method15(Holograms7 var1) {
      int var2 = this.sections.indexOf(var1);
      if (var2 == -1) {
         return Holograms10_2.EMPTY;
      }

      while (var2 >= this.field3.size()) {
         this.field3.add(new Holograms10_2());
      }

      return this.field3.get(var2);
   }

   @Generated
   public Holograms2() {
   }

   @Generated
   public List<Holograms7> getSections() {
      return this.sections;
   }

   @Generated
   public Holograms6_2 method17() {
      return this.field1;
   }

   @Generated
   public void method18(Holograms6_2 var1) {
      this.field1 = var1;
   }

   @Generated
   public Holograms11_2 method19() {
      return this.field2;
   }

   @Generated
   public List<Holograms10_2> method20() {
      return this.field3;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String method21() {
      return this.field4;
   }

   @Generated
   public String method22() {
      return this.field5;
   }
}
