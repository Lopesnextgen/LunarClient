package com.moonsworth.lunar.client.framework.feature.killsounds;

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

public class KillSoundFilter {
   @RegExp
   private static final String field1 = "(?<target>\\b[a-zA-Z0-9_-]{3,16}\\b)";
   private static final LoadingCache<String, Pattern> field2 = CacheBuilder.newBuilder()
      .expireAfterWrite(5L, TimeUnit.MINUTES)
      .build(new CacheLoader<String, Pattern>() {
         public Pattern method1(@NotNull String text1) {
            return Pattern.compile(text1);
         }
      });
   @SerializedName("name")
   private final String field3;
   @SerializedName("patterns")
   private final List<String> field4;
   @SerializedName("type")
   private final KillSoundFilter.Type field5;
   @SerializedName("gameTypes")
   private final Set<String> field6;

   public boolean method1(boolean flag1, @Nullable String text2) {
      boolean flag3 = this.field5 == KillSoundFilter.Type.ALL || this.field5 == KillSoundFilter.Type.HYPIXEL == flag1;
      boolean flag4 = text2 == null || this.field6.isEmpty() || this.field6.contains(text2);
      return flag3 && flag4;
   }

   public Optional<String> method2(String text1, String text2) {
      return this.field4.stream().map(arg3 -> this.method3(arg3, text1, text2)).flatMap(Optional::stream).findFirst();
   }

   private Optional<String> method3(String text1, String text2, String text3) {
      String text4 = text1.replace("<username>", Pattern.quote(text2)).replace("<target>", "(?<target>\\b[a-zA-Z0-9_-]{3,16}\\b)");
      Matcher matcher5 = ((Pattern)field2.getUnchecked(text4)).matcher(text3);
      if (matcher5.find()) {
         String text6 = matcher5.group("target");
         return Optional.ofNullable(text6);
      } else {
         return Optional.empty();
      }
   }

   public String getName() {
      return this.field3 != null ? this.field3 : "UNKNOWN_FILTER";
   }

   @Generated
   public KillSoundFilter(String text1, List<String> list2, KillSoundFilter.Type type3, Set<String> set4) {
      this.field3 = text1;
      this.field4 = list2;
      this.field5 = type3;
      this.field6 = set4;
   }

   private enum Type {
      HYPIXEL,
      OTHER,
      ALL;

      Type() {
      }
   }
}
