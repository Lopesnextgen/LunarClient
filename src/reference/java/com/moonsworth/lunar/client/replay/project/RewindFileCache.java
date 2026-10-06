package com.moonsworth.lunar.client.replay.project;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.timeline.ThumbnailManager;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;

@SerializedNameOnly
public class RewindFileCache {
   private final Map<UUID, RewindFileReader> field1 = new HashMap<>();
   @SerializedName("files")
   private final Set<UUID> field2 = new HashSet<>();
   private final List<File> field3 = new ArrayList<>();
   private final Map<UUID, File> field4 = new HashMap<>();
   private MediaPool field5;
   private ThumbnailManager field6;

   public RewindFileCache() {
   }

   public void method1(MediaPool highlight_41, ThumbnailManager highlight32) {
      try {
         FileUtils.deleteDirectory(RewindPaths.field6);
      } catch (IOException exception7) {
         exception7.printStackTrace();
      }

      this.field5 = highlight_41;
      this.field6 = highlight32;
      RewindPaths.field8.mkdirs();
      this.field3.addAll(FileUtils.listFiles(RewindPaths.field8, new String[]{"rewind"}, true));

      for (Entry entry4 : new HashSet<>(highlight_41.entrySet())) {
         File file5 = (File)entry4.getValue();
         if (file5.getName().toLowerCase().endsWith(".rewind")) {
            try {
               if (!file5.isFile()) {
                  file5 = this.method3((UUID)entry4.getKey());
                  if (file5 == null || !file5.isFile()) {
                     continue;
                  }
               }

               this.method6(file5);
            } catch (IOException exception8) {
               exception8.printStackTrace();
            }
         }
      }
   }

   private UUID method2(File file1) {
      if (!file1.isFile()) {
         return null;
      }

      try (ZipFile zipfile2 = new ZipFile(file1, StandardCharsets.UTF_8)) {
         Rewind2 rewind23 = (Rewind2)LunarConstants.field22
            .fromJson(new InputStreamReader(zipfile2.getInputStream(zipfile2.getEntry("metadata.json")), StandardCharsets.UTF_8), Rewind2.class);
         this.field4.put(rewind23.getId(), file1);
         return rewind23.getId();
      } catch (Exception exception7) {
         exception7.printStackTrace();
         return null;
      }
   }

   private File method3(UUID uuid1) {
      File file2 = this.field4.get(uuid1);
      if (file2 != null) {
         return file2;
      }

      file2 = this.field5.method6(uuid1);
      if (file2 != null && file2.isFile()) {
         return file2;
      }

      Iterator iterator3 = this.field3.iterator();

      while (iterator3.hasNext()) {
         File file4 = (File)iterator3.next();
         iterator3.remove();
         UUID uuid5 = this.method2(file4);
         if (uuid5 != null && uuid5.equals(uuid1)) {
            return file4;
         }
      }

      return null;
   }

   public RewindFileReader method4(UUID uuid1) {
      if (this.field1.containsKey(uuid1)) {
         return this.field1.get(uuid1);
      }

      File file2 = this.method3(uuid1);
      if (file2 == null) {
         return null;
      }

      try {
         return this.method6(file2);
      } catch (IOException exception4) {
         exception4.printStackTrace();
         this.field1.put(uuid1, null);
         return null;
      }
   }

   public boolean method5(UUID uuid1) {
      return this.field1.containsKey(uuid1) && this.field1.get(uuid1) == null ? false : this.field1.containsKey(uuid1) || this.method3(uuid1) != null;
   }

   public RewindFileReader method6(File file1) {
      RewindFileReader rewind32 = new RewindFileReader(file1);
      UUID uuid3 = rewind32.method13().getId();
      RewindFileReader rewind34 = this.field1.get(uuid3);
      if (rewind34 != null) {
         rewind32.close();
         return rewind34;
      } else {
         this.field6.method1(uuid3);
         this.field1.put(uuid3, rewind32);
         this.field2.add(uuid3);
         this.field5.method1(uuid3, file1);
         return rewind32;
      }
   }

   public void close() {
      for (RewindFileReader rewind32 : this.field1.values()) {
         if (rewind32 != null) {
            try {
               rewind32.close();
            } catch (IOException exception4) {
               exception4.printStackTrace();
            }
         }
      }
   }
}
