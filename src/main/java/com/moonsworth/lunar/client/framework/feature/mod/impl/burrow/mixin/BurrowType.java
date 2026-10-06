package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;

public enum BurrowType {
   MINOS_HUNTER("Minos Hunter", false),
   SIAMESE_LYNXES("Siamese Lynxes", false),
   GAIA_CONSTRUCT("Gaia Construct", false),
   MINOTAUR("Minotaur", false),
   NYMPH("Stranded Nymph", false),
   HARPY("Harpy", false),
   CRETAN_BULL("Cretan Bull", false),
   MINOS_CHAMPION("Minos Champion", false),
   SPHINX("Sphinx", false),
   MINOS_INQUISITOR("Minos Inquisitor", true),
   MANTICORE("Manticore", true),
   KING_MINOS("King Minos", true);

   private final String name;
   private final boolean enabledByDefault;

   public static Set<String> names() {
      return Arrays.stream(values()).map(BurrowType::getName).collect(Collectors.toSet());
   }

   public static Set<String> noLynxes() {
      return Arrays.stream(values()).filter(arg0 -> arg0 != SIAMESE_LYNXES).map(BurrowType::getName).collect(Collectors.toSet());
   }

   public static Set<String> getEnabledByDefault() {
      return Arrays.stream(values()).filter(arg0 -> arg0.enabledByDefault).map(BurrowType::getName).collect(Collectors.toSet());
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   BurrowType(String text, boolean flag) {
      this.name = text;
      this.enabledByDefault = flag;
   }
}
