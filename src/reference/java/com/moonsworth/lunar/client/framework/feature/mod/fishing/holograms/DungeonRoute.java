package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.PickaxeTier;
import com.moonsworth.lunar.files.ValuePair;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class DungeonRoute {
   private List<RouteSection> sections = new ArrayList<>();
   private RouteConditions field1 = RouteConditions.NONE;
   private RouteCompletion field2 = new RouteCompletion();
   private List<RouteSegment> field3 = new ArrayList<>();
   private String name = "An unnamed route";
   private String field4 = "An Unknown Player";
   private String field5 = "An unnamed route";

   public DungeonRoute(DungeonRoute holograms21) {
      this.sections = holograms21.sections.stream().map(arg1x -> new RouteSection(arg1x, this)).collect(Collectors.toList());
      this.field1 = holograms21.field1;
      this.field2 = new RouteCompletion(holograms21.field2);
      this.field3 = holograms21.field3.stream().map(RouteSegment::new).collect(Collectors.toList());
      this.name = holograms21.name;
      this.field4 = holograms21.field4;
      this.field5 = holograms21.field5;
   }

   public boolean method1(RouteConditions holograms6_21) {
      return this.field1.isSubsetOf(holograms6_21);
   }

   public RouteSection method2() {
      if (this.sections.isEmpty()) {
         this.method4();
      }

      return this.sections.get(this.sections.size() - 1);
   }

   public RouteSection method3() {
      if (this.sections.isEmpty()) {
         this.method4();
      }

      return this.sections.get(0);
   }

   public void method4() {
      this.sections.add(new RouteSection(this));
   }

   public void method5(PickaxeTier nameplatetype21) {
      this.field1 = this.field1.setPickaxeTier(nameplatetype21);
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

   public void setName(String text1) {
      this.field5 = text1;
      String[] items2 = text1.split("-");
      this.field4 = items2[0];
      if (items2.length > 1) {
         this.name = items2[1];
      }
   }

   public void method9(DataOutputStream output1) {
      output1.writeInt(this.sections.size());

      for (RouteSection holograms73 : this.sections) {
         holograms73.method3(output1);
      }
   }

   public static DungeonRoute method10(DataInputStream input0) {
      DungeonRoute holograms21 = new DungeonRoute();
      int number2 = input0.readInt();

      for (int index3 = 0; index3 < number2; index3++) {
         RouteSection holograms74 = RouteSection.method4(input0, holograms21);
         if (!method12(holograms74)) {
            holograms21.sections.add(holograms74);
         } else {
            List list5 = method11(holograms74, holograms21);
            holograms21.sections.addAll(list5);
         }
      }

      return holograms21;
   }

   private static List<RouteSection> method11(RouteSection holograms70, DungeonRoute holograms21) {
      ArrayList list2 = new ArrayList();
      RouteSection holograms73 = new RouteSection(holograms21);
      int number4 = 0;

      for (ValuePair files6_26 : holograms70.method9()) {
         holograms73.method1((NameplateType)files6_26.field1, (int[])files6_26.field2);
         if (files6_26.field1 == NameplateType.ITEM_DROP && !files6_26.equals(holograms70.method9().get(holograms70.method9().size() - 1))) {
            double value7 = 10000.0;
            int number9 = 0;

            for (int index10 = number4; index10 < holograms70.method8().size(); index10++) {
               int[] items11 = holograms70.method8().get(index10);
               double value12 = Math.pow(items11[0] - ((int[])files6_26.field2)[0], 2.0)
                  + Math.pow(items11[1] - ((int[])files6_26.field2)[1], 2.0)
                  + Math.pow(items11[2] - ((int[])files6_26.field2)[2], 2.0);
               if (value12 < value7) {
                  number9 = index10;
                  value7 = value12;
               }
            }

            if (!(value7 > 100.0)) {
               for (int index15 = number4; index15 < number9; index15++) {
                  holograms73.method2(holograms70.method8().get(index15));
               }

               number4 = number9;
               list2.add(holograms73);
               holograms73 = new RouteSection(holograms21);
            }
         }
      }

      for (int index14 = number4; index14 < holograms70.method8().size(); index14++) {
         holograms73.method2(holograms70.method8().get(index14));
      }

      list2.add(holograms73);
      return list2;
   }

   private static boolean method12(RouteSection holograms70) {
      for (ValuePair files6_22 : holograms70.method9()) {
         if (files6_22.equals(holograms70.method9().get(holograms70.method9().size() - 1))) {
            return false;
         }

         if (files6_22.field1 == NameplateType.ITEM_DROP) {
            return true;
         }
      }

      return false;
   }

   public void method13(RouteCompletion holograms11_21) {
      if (holograms11_21 == null) {
         holograms11_21 = new RouteCompletion();
      }

      this.field2 = holograms11_21;
   }

   public void method14(List<RouteSegment> list1) {
      if (list1 == null) {
         list1 = new ArrayList();
      }

      this.field3 = list1;
   }

   public RouteSegment method15(RouteSection holograms71) {
      int index2 = this.sections.indexOf(holograms71);
      if (index2 == -1) {
         return RouteSegment.EMPTY;
      }

      while (index2 >= this.field3.size()) {
         this.field3.add(new RouteSegment());
      }

      return this.field3.get(index2);
   }

   @Generated
   public DungeonRoute() {
   }

   @Generated
   public List<RouteSection> getSections() {
      return this.sections;
   }

   @Generated
   public RouteConditions method17() {
      return this.field1;
   }

   @Generated
   public void method18(RouteConditions holograms6_21) {
      this.field1 = holograms6_21;
   }

   @Generated
   public RouteCompletion method19() {
      return this.field2;
   }

   @Generated
   public List<RouteSegment> method20() {
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
