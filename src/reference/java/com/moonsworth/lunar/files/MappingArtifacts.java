package com.moonsworth.lunar.files;

public final class MappingArtifacts {
   public static final Files3 MANIFEST_ARTIFACT = new Files3(
      "mojang", "version_manifest.json", "https://launchermeta.mojang.com/mc/game/version_manifest.json", true, false
   );
   public static final MappingBundle MANIFEST_BUNDLE = MappingBundle.of("mx", new Files3[]{field1});
   public static final String field3 = "5";
   public static final Files3 LUNAR_NAMED_ARTIFACT = new Files3("lunar", "lunar_named_b5_${mcVer}.kin", null, false, false);
   public static final MappingBundle LUNAR_NAMED_BUNDLE = MappingBundle.of("mx", new Files3[]{field4});
   public static final Files3 OPTIFINE_ARTIFACT = new Files3("lunar", "optifine_b3_${mcVer}.csrg", null, false, true);
   public static final MappingBundle OPTIFINE_BUNDLE = MappingBundle.of("mx", new Files3[]{field6});
   public static final Files3 FORGE_ARTIFACT = new Files3("forge", "mcp_searge_${mcVer}.kin", null, false, true);
   public static final MappingBundle FORGE_BUNDLE = MappingBundle.of("mx", new Files3[]{field8});
   public static final Files3 INTERMEDIARY_ARTIFACT = new Files3("fabric", "intermediary_${mcVer}.kin", null, false, true);
   public static final MappingBundle INTERMEDIARY_BUNDLE = MappingBundle.of("mx", new Files3[]{field10});
   public static final Files3 INTERMEDIARY_TO_LUNAR_ARTIFACT = new Files3("lunar", "intermediary2lunar_${mcVer}.kin", null, false, true);
   public static final MappingBundle INTERMEDIARY_TO_LUNAR_BUNDLE = MappingBundle.of("mx", new Files3[]{field12});
   public static final Files3 LUNAR_TO_INTERMEDIARY_ARTIFACT = new Files3("lunar", "lunar2intermediary_${mcVer}.kin", null, false, true);
   public static final MappingBundle LUNAR_TO_INTERMEDIARY_BUNDLE = MappingBundle.of("mx", new Files3[]{field14});
   public static final Files3 SEARGE_TO_LUNAR_ARTIFACT = new Files3("lunar", "searge2lunar_${mcVer}.kin", null, false, true);
   public static final MappingBundle SEARGE_TO_LUNAR_BUNDLE = MappingBundle.of("mx", new Files3[]{field16});
   public static final Files3 PARCHMENT_ARTIFACT = new Files3("parchment", "parchment_${mcVer}.json", null, false, true);
   public static final MappingBundle PARCHMENT_BUNDLE = MappingBundle.of("mx", new Files3[]{field18});

   public MappingArtifacts() {
   }
}
