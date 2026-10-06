package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.util.Annotation7;
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

@Annotation7
public class Highlight_4 {
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

   public Set<Entry<UUID, File>> entrySet() {
      return this.field5.entrySet();
   }

   public void method1(UUID var1, File var2) {
      if (!this.field5.containsValue(var2)) {
         this.field5.put(var1, var2);
      }
   }

   public UUID method2(File var1) {
      UUID var2 = (UUID)this.field5.inverse().get(var1);
      if (var2 != null) {
         return var2;
      }

      var2 = UUID.randomUUID();
      this.method1(var2, var1);
      return var2;
   }

   public File method3(UUID var1) {
      if (this.field6.containsKey(var1)) {
         this.method4(var1);
         return null;
      } else {
         this.field7.remove(var1);
         this.field8.remove(var1);
         return (File)this.field5.remove(var1);
      }
   }

   private void method4(UUID var1) {
      for (UUID var3 : this.method5(var1)) {
         this.method3(var3);
      }

      this.field6.remove(var1);
      this.field7.remove(var1);
      this.field8.remove(var1);
   }

   private List<UUID> method5(UUID var1) {
      ArrayList var2 = new ArrayList();

      for (Entry var4 : this.field7.entrySet()) {
         if (((UUID)var4.getValue()).equals(var1)) {
            var2.add((UUID)var4.getKey());
         }
      }

      return var2;
   }

   public File method6(UUID var1) {
      return (File)this.field5.get(var1);
   }

   public Set<Entry<UUID, String>> method7() {
      return this.field6.entrySet();
   }

   public boolean method8(UUID var1) {
      return this.field6.containsKey(var1);
   }

   public UUID method9(UUID var1) {
      return this.field7.get(var1);
   }

   public UUID method10(String var1, UUID var2) {
      UUID var3 = UUID.randomUUID();
      this.field6.put(var3, var1);
      if (var2 != null && this.field6.containsKey(var2)) {
         this.field7.put(var3, var2);
      }

      return var3;
   }

   public String method11(UUID var1, File var2) {
      if (this.field6.containsKey(var1)) {
         return this.field6.get(var1);
      } else {
         String var3 = this.field8.get(var1);
         if (var3 != null) {
            return var3;
         } else {
            return var2 == null ? "" : var2.getName();
         }
      }
   }

   public void method12(UUID var1, String var2) {
      if (var2 != null && !var2.isEmpty()) {
         if (this.field6.containsKey(var1)) {
            this.field6.put(var1, var2);
         } else if (this.field5.containsKey(var1)) {
            this.field8.put(var1, var2);
         }
      }
   }

   public void method13(UUID var1, UUID var2) {
      if (!var1.equals(var2)) {
         if (!this.field6.containsKey(var1) || !this.method14(var2, var1)) {
            if (var2 == null) {
               this.field7.remove(var1);
            } else if (this.field6.containsKey(var2)) {
               this.field7.put(var1, var2);
            }
         }
      }
   }

   private boolean method14(UUID var1, UUID var2) {
      UUID var3 = var1;
      int var4 = 0;

      while (var3 != null && var4++ < 1000) {
         if (var3.equals(var2)) {
            return true;
         }

         var3 = this.field7.get(var3);
      }

      return false;
   }

   public static String method15(File file) {
      String var1 = file.getAbsolutePath();
      return "file:///" + var1 + "?v=" + file.lastModified();
   }

   public void method16(Rewind2_3 var1, UUID var2, Runnable var3, Runnable var4, Runnable runnable) {
      File var6 = this.method6(var2);
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight var7 = var1.method44();
      if (var6 != null && var7.method5().contains(var6.getName())) {
         var6 = new File(var7.method3(), var6.getName());
      }

      this.method17(var6, var3, var4, runnable);
   }

   public void method17(File var1, Runnable var2, Runnable var3, Runnable var4) {
      if (var1 != null && var1.isFile()) {
         if (this.method18(var1, field2)) {
            var2.run();
         } else if (this.method18(var1, field1)) {
            var3.run();
         } else if (this.method18(var1, field3)) {
            var4.run();
         }
      }
   }

   private boolean method18(File var1, String[] var2) {
      for (String var6 : var2) {
         if (var1.getName().endsWith(var6)) {
            return true;
         }
      }

      return false;
   }
}
