package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.util.KeepName;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@KeepName
public enum LoaderDependency implements IchorStage, IchorLoader {
   FABRIC_LOADER("FABRIC_LOADER", 0, "0.1.5", "0.19.5", new String[]{"net.fabricmc:fabric-loader"}),
   ADVENTURE(
      "ADVENTURE",
      1,
      "0.0",
      "4.13.1",
      new String[]{
         "net.kyori:adventure-api",
         "net.kyori:adventure-text-serializer-gson",
         "net.kyori:adventure-text-serializer-legacy",
         "net.kyori:adventure-text-serializer-plain"
      }
   );

   private final String transformerVersion;
   private final String recommendedVersion;
   private final String[] mavenIds;

   public String[] mavenIds(@Nullable String text1) {
      if (text1 == null) {
         text1 = this.recommendedVersion;
      }

      String[] items2 = new String[this.mavenIds.length];

      for (int index3 = 0; index3 < this.mavenIds.length; index3++) {
         items2[index3] = this.mavenIds[index3] + ":" + text1;
      }

      return items2;
   }

   public String[] rawMavenPrefixes() {
      return this.mavenIds;
   }

   public boolean hasMixinRuntime() {
      return false;
   }

   public boolean shouldUseParentAsMixinRuntime() {
      return false;
   }

   public boolean shouldUseClassBytes() {
      return false;
   }

   @Generated
   LoaderDependency(String text3, String text4, String[] items5) {
      this.transformerVersion = text3;
      this.recommendedVersion = text4;
      this.mavenIds = items5;
   }

   @Generated
   public String getTransformerVersion() {
      return this.transformerVersion;
   }

   @Generated
   public String getRecommendedVersion() {
      return this.recommendedVersion;
   }
}
