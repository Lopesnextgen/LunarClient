package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Killsounds3 {
   @RegExp
   private static final String field1 = "(?<target>\\b[a-zA-Z0-9_-]{3,16}\\b)";
   private static final LoadingCache<String, Pattern> field2 = CacheBuilder.newBuilder()
      .expireAfterWrite(5L, TimeUnit.MINUTES)
      .build(new CacheLoader<String, Pattern>() {
         public Pattern method1(@NotNull String var1) {
            return Pattern.compile(var1);
         }
      });
   @SerializedName("name")
   private final String field3;
   @SerializedName("patterns")
   private final List<String> field4;
   @SerializedName("type")
   private final Killsounds3.Type field5;
   @SerializedName("gameTypes")
   private final Set<String> field6;

   public boolean method1(boolean var1, @Nullable String var2) {
      boolean var3 = this.field5 == Killsounds3.Type.ALL || this.field5 == Killsounds3.Type.HYPIXEL == var1;
      boolean var4 = var2 == null || this.field6.isEmpty() || this.field6.contains(var2);
      return var3 && var4;
   }

   public Optional<String> method2(String var1, String var2) {
      return this.field4.stream().map(var3 -> this.method3(var3, var1, var2)).flatMap(Optional::stream).findFirst();
   }

   private Optional<String> method3(String var1, String var2, String var3) {
      String var4 = var1.replace("<username>", Pattern.quote(var2)).replace("<target>", "(?<target>\\b[a-zA-Z0-9_-]{3,16}\\b)");
      Matcher var5 = ((Pattern)field2.getUnchecked(var4)).matcher(var3);
      if (var5.find()) {
         String var6 = var5.group("target");
         return Optional.ofNullable(var6);
      } else {
         return Optional.empty();
      }
   }

   public String getName() {
      return this.field3 != null ? this.field3 : "UNKNOWN_FILTER";
   }

   @Generated
   public Killsounds3(String var1, List<String> var2, Killsounds3.Type var3, Set<String> var4) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field6 = var4;
   }

   private enum Type {
      HYPIXEL,
      OTHER,
      ALL;
   }
}
