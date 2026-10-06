package com.moonsworth.lunar.client.replay.timeline;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.ArrayUtils;

@SerializedNameOnly
public class MediaPool {
   public static final String[] field1 = new String[]{
      "mp3", "wav", "aiff", "aif", "aifc", "au", "snd", "mid", "midi", "rmi", "ogg", "opus", "oga", "m4a", "caf"
   };
   public static final String[] field2 = new String[]{"rewind"};
   public static final String[] field3 = new String[]{"png", "jpg", "jpeg", "gif", "bmp", "tiff", "webp", "svg"};
   public static final String[] field4 = (String[])ArrayUtils.addAll(field1, (String[])ArrayUtils.addAll(field2, field3));
   private final BiMap<UUID, File> field5 = HashBiMap.create();
   private final Map<UUID, String> field6 = new LinkedHashMap<>();
   private final Map<UUID, UUID> field7 = new HashMap<>();
   private final Map<UUID, String> field8 = new HashMap<>();

   public MediaPool() {
   }

   public Set<Entry<UUID, File>> entrySet() {
      return this.field5.entrySet();
   }

   public void method1(UUID uuid1, File file2) {
      if (!this.field5.containsValue(file2)) {
         this.field5.put(uuid1, file2);
      }
   }

   public UUID method2(File file1) {
      UUID uuid2 = (UUID)this.field5.inverse().get(file1);
      if (uuid2 != null) {
         return uuid2;
      }

      uuid2 = UUID.randomUUID();
      this.method1(uuid2, file1);
      return uuid2;
   }

   public File method3(UUID uuid1) {
      if (this.field6.containsKey(uuid1)) {
         this.method4(uuid1);
         return null;
      } else {
         this.field7.remove(uuid1);
         this.field8.remove(uuid1);
         return (File)this.field5.remove(uuid1);
      }
   }

   private void method4(UUID uuid1) {
      for (UUID uuid3 : this.method5(uuid1)) {
         this.method3(uuid3);
      }

      this.field6.remove(uuid1);
      this.field7.remove(uuid1);
      this.field8.remove(uuid1);
   }

   private List<UUID> method5(UUID uuid1) {
      ArrayList list2 = new ArrayList();

      for (Entry entry4 : this.field7.entrySet()) {
         if (((UUID)entry4.getValue()).equals(uuid1)) {
            list2.add((UUID)entry4.getKey());
         }
      }

      return list2;
   }

   public File method6(UUID uuid1) {
      return (File)this.field5.get(uuid1);
   }

   public Set<Entry<UUID, String>> method7() {
      return this.field6.entrySet();
   }

   public boolean method8(UUID uuid1) {
      return this.field6.containsKey(uuid1);
   }

   public UUID method9(UUID uuid1) {
      return this.field7.get(uuid1);
   }

   public UUID method10(String text1, UUID uuid2) {
      UUID uuid3 = UUID.randomUUID();
      this.field6.put(uuid3, text1);
      if (uuid2 != null && this.field6.containsKey(uuid2)) {
         this.field7.put(uuid3, uuid2);
      }

      return uuid3;
   }

   public String method11(UUID uuid1, File file2) {
      if (this.field6.containsKey(uuid1)) {
         return this.field6.get(uuid1);
      } else {
         String text3 = this.field8.get(uuid1);
         if (text3 != null) {
            return text3;
         } else {
            return file2 == null ? "" : file2.getName();
         }
      }
   }

   public void method12(UUID uuid1, String text) {
      if (text != null && !text.isEmpty()) {
         if (this.field6.containsKey(uuid1)) {
            this.field6.put(uuid1, text);
         } else if (this.field5.containsKey(uuid1)) {
            this.field8.put(uuid1, text);
         }
      }
   }

   public void method13(UUID uuid1, UUID uuid2) {
      if (!uuid1.equals(uuid2)) {
         if (!this.field6.containsKey(uuid1) || !this.method14(uuid2, uuid1)) {
            if (uuid2 == null) {
               this.field7.remove(uuid1);
            } else if (this.field6.containsKey(uuid2)) {
               this.field7.put(uuid1, uuid2);
            }
         }
      }
   }

   private boolean method14(UUID uuid1, UUID uuid2) {
      UUID uuid3 = uuid1;
      int index4 = 0;

      while (uuid3 != null && index4++ < 1000) {
         if (uuid3.equals(uuid2)) {
            return true;
         }

         uuid3 = this.field7.get(uuid3);
      }

      return false;
   }

   public static String method15(File file0) {
      String text1 = file0.getAbsolutePath();
      return "file:///" + text1 + "?v=" + file0.lastModified();
   }

   public void method16(ReplayProjectManager rewind2_31, UUID uuid2, Runnable runnable3, Runnable runnable4, Runnable runnable5) {
      File file6 = this.method6(uuid2);
      com.moonsworth.lunar.client.replay.audio.MusicTrackManager highlight7 = rewind2_31.method44();
      if (file6 != null && highlight7.method5().contains(file6.getName())) {
         file6 = new File(highlight7.method3(), file6.getName());
      }

      this.method17(file6, runnable3, runnable4, runnable5);
   }

   public void method17(File file1, Runnable runnable2, Runnable runnable3, Runnable runnable4) {
      if (file1 != null && file1.isFile()) {
         if (this.method18(file1, field2)) {
            runnable2.run();
         } else if (this.method18(file1, field1)) {
            runnable3.run();
         } else if (this.method18(file1, field3)) {
            runnable4.run();
         }
      }
   }

   private boolean method18(File file1, String[] items2) {
      for (String text6 : items2) {
         if (file1.getName().endsWith(text6)) {
            return true;
         }
      }

      return false;
   }
}
