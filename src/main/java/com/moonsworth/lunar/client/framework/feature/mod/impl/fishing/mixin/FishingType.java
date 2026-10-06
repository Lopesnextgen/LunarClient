package com.moonsworth.lunar.client.framework.feature.mod.impl.fishing.mixin;

import java.util.HashSet;
import lombok.Generated;

public enum FishingType {
   WATER_HYDRA("Water Hydra"),
   ALLIGATOR("Alligator"),
   SEA_EMPEROR("The Loch Emperor"),
   PHANTOM_FISHER("Phantom Fisher"),
   ABYSSAL_MINER("Abyssal Miner"),
   GREAT_WHITE_SHARK("Great White Shark"),
   YETI("Yeti"),
   GRIM_REAPER("Grim Reaper"),
   TITANOBOA("Titanoboa"),
   BLUE_RINGED_OCTOPUS("Blue Ringed Octopus"),
   REINDRAKE("Reindrake"),
   PLHLEGBLAST("Plhlegblast"),
   THUNDER("Thunder"),
   FIERY_SCUTTLER("Fiery Scuttler"),
   WIKI_TIKI("Wiki Tiki"),
   LORD_JAWBUS("Lord Jawbus"),
   RAGNAROK("Ragnarok"),
   NESSIE("Nessie"),
   PUDDLE_JUMPER("Puddle Jumper"),
   FROG_PRINCE("Frog Prince"),
   SILKBREEZE("Silkbreeze"),
   GIANT_ISOPOD("Giant Isopod");

   private final String name;

   public static HashSet<String> names() {
      HashSet set0 = new HashSet();

      for (FishingType fishingtype4 : values()) {
         set0.add(fishingtype4.getName());
      }

      return set0;
   }

   @Generated
   FishingType(String text) {
      this.name = text;
   }

   @Generated
   public String getName() {
      return this.name;
   }
}
