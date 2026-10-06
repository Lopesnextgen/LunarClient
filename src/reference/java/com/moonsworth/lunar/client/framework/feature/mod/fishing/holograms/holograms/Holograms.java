package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_7;
import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

@Annotation2
public class Holograms {
   private final String[] hashes;
   private final String[] ids;
   private final String communityName;
   @Nullable
   private final String blcName;
   private final int blcRotOffset;
   private final int roofHeight;
   @Nullable
   private final Integer secretCount;
   private final Set<Holograms_7> secrets;
   @SerializedName("type")
   private final HologramsType5 roomType;
   private final HologramsType3 size;

   public Holograms(
      String[] var1,
      String[] var2,
      String text,
      @Nullable String var4,
      int value,
      int value2,
      @Nullable Integer var7,
      Set<Holograms_7> set,
      HologramsType5 hologramsType5,
      HologramsType3 hologramsType3
   ) {
      this.hashes = var1;
      this.ids = var2;
      this.communityName = text;
      this.blcName = var4;
      this.blcRotOffset = value;
      this.roofHeight = value2;
      this.secretCount = var7;
      this.secrets = set;
      this.roomType = hologramsType5;
      this.size = hologramsType3;
   }

   public Set<Holograms_7> secrets() {
      return this.secrets.stream().map(Holograms_7::method1).collect(Collectors.toSet());
   }

   @Deprecated
   public String getBlcID() {
      return this.blcName != null ? this.blcName : this.communityName;
   }

   @Deprecated
   public HologramsType_3 getBlcRot(HologramsType_3 var1) {
      if (this.size != HologramsType3.ONE_BY_ONE) {
         return var1;
      }

      int var2 = this.blcRotOffset;
      return var2 != -1 && var2 != 0 ? HologramsType_3.VALUES[(var2 + var1.ordinal()) % 4] : var1;
   }

   public int getSecretCount() {
      return this.secretCount != null ? this.secretCount : (int)this.secrets().stream().filter(var0 -> var0.getType() != HologramsType6.FAIRY_SOUL).count();
   }
}
