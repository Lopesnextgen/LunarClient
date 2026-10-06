package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class SkyBlockCommandMigrations {
   private static final List<Supplier<SkyBlockCommandMigrationStep>> field1 = new ArrayList<Supplier<SkyBlockCommandMigrationStep>>() {
      {
         this.add(CommandPathMigrationStep::new);
      }
   };
   public static final int field2 = field1.size();

   public SkyBlockCommandMigrations() {
   }

   public static void method1(SkyBlockCommandKeybinds fishing40, SkyBlockCommandConfig jsondeserializeriterator$data1, int value) {
      SkyBlockCommandMigration fishing53 = new SkyBlockCommandMigration(fishing40, jsondeserializeriterator$data1);

      for (int index4 = value; index4 < field2; index4++) {
         field1.get(index4).get().method1(fishing53);
      }
   }
}
