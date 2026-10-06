package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.nio.file.Path;

@KeepName
public class PreLaunchLibraryBootstrap$Data {
   private final String mcVersionName;
   private final Path mxCacheDir;
   private final Path classpathDir;
   private final Path overridesDir;
   private final Path extraLibsDir;

   public PreLaunchLibraryBootstrap$Data(String text1, Path path2, Path path3, Path path4, Path path5) {
      this.mcVersionName = text1;
      this.mxCacheDir = path2;
      this.classpathDir = path3;
      this.overridesDir = path4;
      this.extraLibsDir = path5;
   }
}
