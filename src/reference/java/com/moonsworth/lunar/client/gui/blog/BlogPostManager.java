package com.moonsworth.lunar.client.gui.blog;

import com.google.gson.JsonArray;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.gui.blog.BlogPost;
import com.moonsworth.lunar.client.gui.blog.BlogPostDownloadTask;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class BlogPostManager extends ItemSetHandler<BlogPost> implements Extension {
   private final GuiIterator field2 = new GuiIterator();

   public void method2() {
      File var1 = ThreadModuleDump48.field14.toFile();
      if (var1.exists()) {
         File[] var2 = var1.listFiles();
         if (var2 == null) {
            return;
         }

         Set var3 = this.method3().stream().map(BlogPost::getHash).collect(Collectors.toSet());

         for (File var7 : var2) {
            String var8 = FilenameUtils.removeExtension(var7.getName());
            if (!var3.contains(var8) && var7.delete()) {
               Slayer.method4("Blog Posts", "Removed cached blog post image: %s", new Object[]{var8});
            }
         }
      }
   }

   public void method5() {
      Path var1 = ThreadModuleDump48.field14;

      try {
         Files.createDirectories(var1);
      } catch (IOException var8) {
         Inventorymod2.method5(var8, "Loading BlogImages");
         return;
      }

      File[] var2 = var1.toFile().listFiles();
      if (var2 != null) {
         ArrayList var3 = new ArrayList();

         for (BlogPost var5 : this.method3()) {
            String var6 = var5.getHash();
            String var7 = var6 + "." + BlogPost.field1.subtype();
            if (Arrays.stream(var2).noneMatch(var1x -> var1x.getName().equals(var7))) {
               var3.add(var5);
            } else {
               var5.method3(var7);
            }
         }

         this.method6();
         if (!var3.isEmpty()) {
            new Thread(new BlogPostDownloadTask(var3)).start();
         }
      }
   }

   @Override
   protected Set<BlogPost> method3() {
      return new LinkedHashSet<>();
   }

   public void method4(BlogPost var1) {
      this.method3().add(var1);
      this.method6();
   }

   public void method6() {
      JsonArray var1 = new JsonArray();

      for (BlogPost var3 : this.method3()) {
         var1.add(var3.provide());
      }

      this.field2.method3("blogPosts", var1);
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field2;
   }
}
