package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomShape;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SecretType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomRotation;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomSecret;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

@KeepName
public class DungeonRoom {
   private final String[] hashes;
   private final String[] ids;
   private final String communityName;
   @Nullable
   private final String blcName;
   private final int blcRotOffset;
   private final int roofHeight;
   @Nullable
   private final Integer secretCount;
   private final Set<RoomSecret> secrets;
   @SerializedName("type")
   private final MapRoomType roomType;
   private final RoomShape size;

   public DungeonRoom(
      String[] items1,
      String[] items2,
      String text,
      @Nullable String text4,
      int value,
      int value2,
      @Nullable Integer number7,
      Set<RoomSecret> set,
      MapRoomType map,
      RoomShape roomShape
   ) {
      this.hashes = items1;
      this.ids = items2;
      this.communityName = text;
      this.blcName = text4;
      this.blcRotOffset = value;
      this.roofHeight = value2;
      this.secretCount = number7;
      this.secrets = set;
      this.roomType = map;
      this.size = roomShape;
   }

   public Set<RoomSecret> secrets() {
      return this.secrets.stream().map(RoomSecret::method1).collect(Collectors.toSet());
   }

   @Deprecated
   public String getBlcID() {
      return this.blcName != null ? this.blcName : this.communityName;
   }

   @Deprecated
   public RoomRotation getBlcRot(RoomRotation hologramstype_31) {
      if (this.size != RoomShape.ONE_BY_ONE) {
         return hologramstype_31;
      }

      int number2 = this.blcRotOffset;
      return number2 != -1 && number2 != 0 ? RoomRotation.VALUES[(number2 + hologramstype_31.ordinal()) % 4] : hologramstype_31;
   }

   public int getSecretCount() {
      return this.secretCount != null ? this.secretCount : (int)this.secrets().stream().filter(arg0 -> arg0.getType() != SecretType.FAIRY_SOUL).count();
   }
}
