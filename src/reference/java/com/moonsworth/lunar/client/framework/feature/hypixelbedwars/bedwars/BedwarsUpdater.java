package com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars;

import com.moonsworth.lunar.client.framework.Client;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BedwarsUpdater extends Bedwars {
   private final LinkedHashMap<Pattern, Bedwars.Type> field17 = new LinkedHashMap<>();

   public BedwarsUpdater() {
   }

   @Override
   public void update() {
      String text1 = this.playerName;
      String text2 = Client.method109().method40().method41().method39().method6();
      if (text2.length() > 1) {
         text1 = "(?:" + this.playerName + "|" + text2 + ")";
      }

      String text3 = "(?:Red|Blue|Green|Yellow|Aqua|White|Pink|Gray)";
      String text4 = text1 + "[^A-Za-z0-9_]";
      StringBuilder builder5 = new StringBuilder("(?:");

      for (int index6 = 0; index6 < this.field13.size(); index6++) {
         if (index6 > 0) {
            builder5.append("|");
         }

         builder5.append((String)this.field13.get(index6));
      }

      String text16 = builder5.append(")").toString();
      String text7 = "FINAL KILL!";
      Pattern pattern8 = Pattern.compile("^ {2}.*" + text3 + " - .*[^A-Za-z0-9_]." + text1 + "[^A-Za-z0-9_].*");
      Pattern pattern9 = Pattern.compile("^ {2}.*" + text3 + " - .*");
      Pattern pattern10 = Pattern.compile("^" + text16 + "[^A-Za-z0-9_].*" + text4 + ".*" + text7);
      Pattern pattern11 = Pattern.compile("^" + text1 + "[^A-Za-z0-9_].*" + text7);
      Pattern pattern12 = Pattern.compile("^" + text16 + "[^A-Za-z0-9_].*" + text4 + ".*");
      Pattern pattern13 = Pattern.compile("^" + text1 + "[^A-Za-z0-9_].*");
      Pattern pattern14 = Pattern.compile("^BED DESTRUCTION > Your Bed .*");
      Pattern pattern15 = Pattern.compile("^BED DESTRUCTION > .*" + text4 + ".*");
      this.field17.clear();
      this.field17.put(pattern8, Bedwars.Type.WIN);
      this.field17.put(pattern9, Bedwars.Type.LOSS);
      this.field17.put(pattern10, Bedwars.Type.FINAL_KILL);
      this.field17.put(pattern11, Bedwars.Type.FINAL_DEATH);
      this.field17.put(pattern12, Bedwars.Type.KILL);
      this.field17.put(pattern13, Bedwars.Type.DEATH);
      this.field17.put(pattern14, Bedwars.Type.BED_LOSS);
      this.field17.put(pattern15, Bedwars.Type.BED_BREAK);
   }

   @Override
   public Bedwars.Type method13(String text1) {
      if (text1.contains(":")) {
         return Bedwars.Type.NONE;
      }

      String text2 = text1.replace("\n", "");

      for (Entry entry4 : this.field17.entrySet()) {
         Matcher matcher5 = ((Pattern)entry4.getKey()).matcher(text2);
         if (matcher5.matches()) {
            if (entry4.getValue() == Bedwars.Type.BED_LOSS) {
               this.field16 = true;
            }

            return (Bedwars.Type)entry4.getValue();
         }
      }

      return Bedwars.Type.NONE;
   }
}
