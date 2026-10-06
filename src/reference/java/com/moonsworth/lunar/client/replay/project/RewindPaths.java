package com.moonsworth.lunar.client.replay.project;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.nio.file.Path;

public class RewindPaths {
   public static final int field1 = 3;
   public static final int field2 = 0;
   public static final int field3 = 0;
   private static final File field4 = new File(Ref.method3().bridge$getMcDataDir(), "rewind");
   public static final File field5 = new File(field4, ".record");
   public static final File field6 = new File(field4, ".packs");
   public static final File field7 = new File(field4, "rewinds");
   public static final File field8 = new File(field7, Bridge.getMinecraftVersion().method45());
   public static final File field9 = new File(field8, ".thumbnails");
   public static final File field10 = new File(field4, "projects");
   public static final File field11 = new File(field4, "videos");
   public static final File field12 = new File(field4, "screenshots");
   public static final Path field13 = LunarConstants.field6.resolve("ffmpeg");
   public static final int field14 = (int)Math.pow(2.0, 16.0);

   public RewindPaths() {
   }
}
