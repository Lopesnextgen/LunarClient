package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@KeepName
public class ClientGameBootstrap$Data {
   private final String[] args;
   private final Path classpathDir;
   private final Path mxCacheDir;
   private final Path overridesDirectory;
   private final String mcVersionName;
   private final String[] ichorClassPath;
   private final String[] ichorExternalFiles;
   private final boolean runIntegrationTests;
   private final Path workingDirectory;
   private final Path extraLibsDir;
   @Nullable
   private final Path partialJarPath;
   private final List<String> classesToDump;

   public ClientGameBootstrap$Data(
      String[] items1,
      Path path2,
      Path path3,
      Path path4,
      String text5,
      String[] items6,
      String[] items7,
      boolean flag8,
      Path path9,
      Path path10,
      @Nullable Path path11,
      List<String> list12
   ) {
      this.args = items1;
      this.classpathDir = path2;
      this.mxCacheDir = path3;
      this.overridesDirectory = path4;
      this.mcVersionName = text5;
      this.ichorClassPath = items6;
      this.ichorExternalFiles = items7;
      this.runIntegrationTests = flag8;
      this.workingDirectory = path9;
      this.extraLibsDir = path10;
      this.partialJarPath = path11;
      this.classesToDump = list12;
   }
}
