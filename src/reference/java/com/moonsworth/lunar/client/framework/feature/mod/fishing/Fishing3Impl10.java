package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class Fishing3Impl10 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "queueinstance";
   }

   @Override
   public List<String> getAliases() {
      return List.of("f1", "f2", "f3", "f4", "f5", "f6", "f7", "m1", "m2", "m3", "m4", "m5", "m6", "m7", "t1", "t2", "t3", "t4", "t5");
   }

   @Override
   public List<String> method1() {
      return List.of(this.getCommand(), "f1-f7", "m1-m7", "t1-t5");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      String var5 = text.equals(this.getCommand()) && items.length >= 1 ? items[0] : text;

      String var6 = switch (var5) {
         case "f1" -> "CATACOMBS_FLOOR_ONE";
         case "f2" -> "CATACOMBS_FLOOR_TWO";
         case "f3" -> "CATACOMBS_FLOOR_THREE";
         case "f4" -> "CATACOMBS_FLOOR_FOUR";
         case "f5" -> "CATACOMBS_FLOOR_FIVE";
         case "f6" -> "CATACOMBS_FLOOR_SIX";
         case "f7" -> "CATACOMBS_FLOOR_SEVEN";
         case "m1" -> "MASTER_CATACOMBS_FLOOR_ONE";
         case "m2" -> "MASTER_CATACOMBS_FLOOR_TWO";
         case "m3" -> "MASTER_CATACOMBS_FLOOR_THREE";
         case "m4" -> "MASTER_CATACOMBS_FLOOR_FOUR";
         case "m5" -> "MASTER_CATACOMBS_FLOOR_FIVE";
         case "m6" -> "MASTER_CATACOMBS_FLOOR_SIX";
         case "m7" -> "MASTER_CATACOMBS_FLOOR_SEVEN";
         case "t1" -> "KUUDRA_NORMAL";
         case "t2" -> "KUUDRA_HOT";
         case "t3" -> "KUUDRA_BURNING";
         case "t4" -> "KUUDRA_FIERY";
         case "t5" -> "KUUDRA_INFERNAL";
         default -> null;
      };
      if (var6 != null) {
         this.method7("/joininstance " + var6);
      }
   }
}
