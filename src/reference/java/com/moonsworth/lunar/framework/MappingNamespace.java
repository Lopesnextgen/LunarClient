package com.moonsworth.lunar.framework;

import com.moonsworth.lunar.config.Config;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;

public enum MappingNamespace {
   NONE("", Optional::of),
   NOTCH("notch", arg0 -> {
      if (arg0.startsWith("notch/") || arg0.startsWith("patch/notch/")) {
         arg0 = arg0.replace("notch/", "");
         return Optional.of(arg0);
      } else {
         return !arg0.startsWith("srg/") && !arg0.startsWith("patch/srg/") ? Optional.of(arg0) : Optional.empty();
      }
   }),
   SRG("srg", arg0 -> {
      if (arg0.startsWith("srg/") || arg0.startsWith("patch/srg/")) {
         arg0 = arg0.replace("srg/", "");
         return Optional.of(arg0);
      } else {
         return !arg0.startsWith("net/optifine/") && !arg0.startsWith("patch/") ? Optional.of(arg0) : Optional.empty();
      }
   }),
   PATCH("patch", arg0 -> {
      if (arg0.startsWith("srg/") || arg0.startsWith("patch/srg/")) {
         return Optional.empty();
      } else {
         return !arg0.startsWith("net/optifine/") && !arg0.startsWith("patch/") ? Optional.of(arg0) : Optional.of(arg0);
      }
   });

   private final String name;
   private final Function<String, Optional<String>> fileFilter;

   public static MappingNamespace from(Config config0, boolean flag) {
      if (config0.method21()) {
         return NONE;
      } else {
         return config0.method40() ? NOTCH : PATCH;
      }
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public Function<String, Optional<String>> getFileFilter() {
      return this.fileFilter;
   }

   @Generated
   MappingNamespace(String text, Function<String, Optional<String>> function4) {
      this.name = text;
      this.fileFilter = function4;
   }
}
