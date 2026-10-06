package com.moonsworth.lunar.client.framework.feature.rewind.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
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

@Annotation7
public class Rewind {
   private final Map<UUID, Rewind3> field1 = new HashMap<>();
   @SerializedName("files")
   private final Set<UUID> field2 = new HashSet<>();
   private final List<File> field3 = new ArrayList<>();
   private final Map<UUID, File> field4 = new HashMap<>();
   private Highlight_4 field5;
   private Highlight3 field6;

   public void method1(Highlight_4 var1, Highlight3 var2) {
      try {
         FileUtils.deleteDirectory(Gui.field6);
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      this.field5 = var1;
      this.field6 = var2;
      Gui.field8.mkdirs();
      this.field3.addAll(FileUtils.listFiles(Gui.field8, new String[]{"rewind"}, true));

      for (Entry var4 : new HashSet<>(var1.entrySet())) {
         File var5 = (File)var4.getValue();
         if (var5.getName().toLowerCase().endsWith(".rewind")) {
            try {
               if (!var5.isFile()) {
                  var5 = this.method3((UUID)var4.getKey());
                  if (var5 == null || !var5.isFile()) {
                     continue;
                  }
               }

               this.method6(var5);
            } catch (IOException var8) {
               var8.printStackTrace();
            }
         }
      }
   }

   private UUID method2(File var1) {
      if (!var1.isFile()) {
         return null;
      }

      try (ZipFile var2 = new ZipFile(var1, StandardCharsets.UTF_8)) {
         Rewind2 var3 = (Rewind2)ThreadModuleDump48.field22
            .fromJson(new InputStreamReader(var2.getInputStream(var2.getEntry("metadata.json")), StandardCharsets.UTF_8), Rewind2.class);
         this.field4.put(var3.getId(), var1);
         return var3.getId();
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   private File method3(UUID var1) {
      File var2 = this.field4.get(var1);
      if (var2 != null) {
         return var2;
      }

      var2 = this.field5.method6(var1);
      if (var2 != null && var2.isFile()) {
         return var2;
      }

      Iterator var3 = this.field3.iterator();

      while (var3.hasNext()) {
         File var4 = (File)var3.next();
         var3.remove();
         UUID var5 = this.method2(var4);
         if (var5 != null && var5.equals(var1)) {
            return var4;
         }
      }

      return null;
   }

   public Rewind3 method4(UUID var1) {
      if (this.field1.containsKey(var1)) {
         return this.field1.get(var1);
      }

      File var2 = this.method3(var1);
      if (var2 == null) {
         return null;
      }

      try {
         return this.method6(var2);
      } catch (IOException var4) {
         var4.printStackTrace();
         this.field1.put(var1, null);
         return null;
      }
   }

   public boolean method5(UUID var1) {
      return this.field1.containsKey(var1) && this.field1.get(var1) == null ? false : this.field1.containsKey(var1) || this.method3(var1) != null;
   }

   public Rewind3 method6(File var1) {
      Rewind3 var2 = new Rewind3(var1);
      UUID var3 = var2.method13().getId();
      Rewind3 var4 = this.field1.get(var3);
      if (var4 != null) {
         var2.close();
         return var4;
      } else {
         this.field6.method1(var3);
         this.field1.put(var3, var2);
         this.field2.add(var3);
         this.field5.method1(var3, var1);
         return var2;
      }
   }

   public void close() {
      for (Rewind3 var2 : this.field1.values()) {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var4) {
               var4.printStackTrace();
            }
         }
      }
   }
}
