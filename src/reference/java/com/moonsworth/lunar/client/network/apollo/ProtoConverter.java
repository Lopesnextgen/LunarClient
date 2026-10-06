package com.moonsworth.lunar.client.network.apollo;

import com.google.gson.JsonObject;
import com.google.protobuf.Duration;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.Struct.Builder;
import com.google.protobuf.util.JsonFormat;
import com.lunarclient.common.v1.Direction;
import com.lunarclient.common.v1.Uuid;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.common.v1.Vector2i;
import com.lunarclient.common.v1.Vector3f;
import com.lunarclient.common.v1.Vector3i;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LunarConstants;

public final class ProtoConverter {
   public ProtoConverter() {
   }

   public static UUID method1(Uuid uuid0) {
      return new UUID(uuid0.getHigh64(), uuid0.getLow64());
   }

   public static List<UUID> method2(Collection<Uuid> list0) {
      return list0.stream().map(ProtoConverter::method1).collect(Collectors.toList());
   }

   public static Uuid method3(UUID uuid0) {
      return Uuid.newBuilder().setHigh64(uuid0.getMostSignificantBits()).setLow64(uuid0.getLeastSignificantBits()).build();
   }

   public static List<Uuid> method4(Collection<UUID> list0) {
      return list0.stream().map(ProtoConverter::method3).collect(Collectors.toList());
   }

   public static Instant method5(Timestamp timestamp0) {
      return Instant.ofEpochSecond(timestamp0.getSeconds(), timestamp0.getNanos());
   }

   public static Timestamp method6(Instant instant0) {
      return Timestamp.newBuilder().setSeconds(instant0.getEpochSecond()).setNanos(instant0.getNano()).build();
   }

   public static UuidAndUsername method7(UUID uuid0, String text1) {
      return UuidAndUsername.newBuilder().setUuid(method3(uuid0)).setUsername(text1).build();
   }

   public static UuidAndUsername method8(Bridge6_10 bridge6_100) {
      return UuidAndUsername.newBuilder().setUuid(method3(bridge6_100.bridge$getUniqueID())).setUsername(bridge6_100.bridge$getName()).build();
   }

   public static UuidAndUsername method9() {
      return method8(Ref.method7());
   }

   public static Direction method10(HorsestatsType_2 horsestatstype_20) {
      return Direction.forNumber(horsestatstype_20.getId() + 1);
   }

   public static HorsestatsType_2 method11(Direction direction0) {
      return HorsestatsType_2.byId(direction0.getNumber() - 1);
   }

   public static Vector3f method12(org.joml.Vector3f vector3f0) {
      return Vector3f.newBuilder().setX(vector3f0.x()).setY(vector3f0.y()).setZ(vector3f0.z()).build();
   }

   public static org.joml.Vector3f method13(Vector3f vector3f0) {
      return new org.joml.Vector3f(vector3f0.getX(), vector3f0.getY(), vector3f0.getZ());
   }

   public static Timestamp method14(long number0) {
      return Timestamp.newBuilder().setSeconds(number0 / 1000L).setNanos((int)(number0 % 1000L * 1000000L)).build();
   }

   public static long method15(Timestamp timestamp0) {
      return timestamp0.getSeconds() * 1000L + timestamp0.getNanos() / 1000000;
   }

   public static Duration method16(java.time.Duration duration0) {
      return Duration.newBuilder().setSeconds(duration0.toSeconds()).setNanos(duration0.toNanosPart()).build();
   }

   public static Vector2i method17(org.joml.Vector2i vector2i0) {
      return Vector2i.newBuilder().setX(vector2i0.x).setZ(vector2i0.y).build();
   }

   public static org.joml.Vector2i method18(Vector2i vector2i0) {
      return new org.joml.Vector2i(vector2i0.getX(), vector2i0.getZ());
   }

   public static Vector3i method19(org.joml.Vector3i vector3i0) {
      return Vector3i.newBuilder().setX(vector3i0.x()).setY(vector3i0.y()).setZ(vector3i0.z()).build();
   }

   public static org.joml.Vector3i method20(Vector3i vector3i0) {
      return new org.joml.Vector3i(vector3i0.getX(), vector3i0.getY(), vector3i0.getZ());
   }

   public static Struct method21(JsonObject json0) {
      Builder builder1 = Struct.newBuilder();

      try {
         JsonFormat.parser().merge(json0.toString(), builder1);
      } catch (InvalidProtocolBufferException invalidprotocolbufferexception3) {
         throw new RuntimeException(invalidprotocolbufferexception3);
      }

      return builder1.build();
   }

   public static JsonObject method22(Struct struct0) {
      try {
         String text1 = JsonFormat.printer().print(struct0);
         return (JsonObject)LunarConstants.field22.fromJson(text1, JsonObject.class);
      } catch (InvalidProtocolBufferException invalidprotocolbufferexception2) {
         throw new RuntimeException(invalidprotocolbufferexception2);
      }
   }
}
