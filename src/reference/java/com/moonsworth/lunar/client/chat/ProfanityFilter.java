package com.moonsworth.lunar.client.chat;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.itemphysics.mixin.Itemphysics2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public class ProfanityFilter implements LoadableHandler {
   private List<Pattern> field1 = new ArrayList<>();
   private List<Pattern> field2 = new ArrayList<>();
   @Nullable
   private Pattern field3;
   @Nullable
   private Pattern field4;
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("lunar:profanity/profanity_filter.json");

   @Override
   public void close() {
   }

   @Override
   public void init() {
      try {
         Gson var1 = new Gson();
         IResourceBridge var2 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(field5);
         if (var2 == null) {
            Slayer.method5("Could not find profanity filter json file: " + field5, new Object[0]);
         } else {
            InputStream var3 = var2.bridge$getInputStream();
            JsonReader var4 = new JsonReader(new InputStreamReader(var3));
            Itemphysics2 var5 = (Itemphysics2)var1.fromJson(var4, Itemphysics2.class);
            var5.method1().method1().stream().map(var0 -> Pattern.compile(var0, 2)).forEach(this.field1::add);
            var5.method1().method3().stream().map(var0 -> Pattern.compile(var0, 2)).forEach(this.field2::add);
            String var6 = "\\b(";
            String var7 = ")\\b";
            this.field3 = Pattern.compile(var6 + StringUtils.join(var5.method3().method1(), "|") + var7, 2);
            this.field4 = Pattern.compile(var6 + StringUtils.join(var5.method3().method3(), "|") + var7, 2);
         }
      } catch (Throwable var8) {
         throw var8;
      }
   }

   @Generated
   public List<Pattern> method1() {
      return this.field1;
   }

   @Generated
   public List<Pattern> method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Pattern method3() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Pattern method4() {
      return this.field4;
   }
}
