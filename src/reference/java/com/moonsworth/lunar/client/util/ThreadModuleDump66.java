package com.moonsworth.lunar.client.util;

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

public final class ThreadModuleDump66 {
   public static UUID method1(Uuid var0) {
      return new UUID(var0.getHigh64(), var0.getLow64());
   }

   public static List<UUID> method2(Collection<Uuid> var0) {
      return var0.stream().map(ThreadModuleDump66::method1).collect(Collectors.toList());
   }

   public static Uuid method3(UUID var0) {
      return Uuid.newBuilder().setHigh64(var0.getMostSignificantBits()).setLow64(var0.getLeastSignificantBits()).build();
   }

   public static List<Uuid> method4(Collection<UUID> var0) {
      return var0.stream().map(ThreadModuleDump66::method3).collect(Collectors.toList());
   }

   public static Instant method5(Timestamp var0) {
      return Instant.ofEpochSecond(var0.getSeconds(), var0.getNanos());
   }

   public static Timestamp method6(Instant var0) {
      return Timestamp.newBuilder().setSeconds(var0.getEpochSecond()).setNanos(var0.getNano()).build();
   }

   public static UuidAndUsername method7(UUID var0, String var1) {
      return UuidAndUsername.newBuilder().setUuid(method3(var0)).setUsername(var1).build();
   }

   public static UuidAndUsername method8(Bridge6_10 var0) {
      return UuidAndUsername.newBuilder().setUuid(method3(var0.bridge$getUniqueID())).setUsername(var0.bridge$getName()).build();
   }

   public static UuidAndUsername method9() {
      return method8(ThreadModuleDump63.method7());
   }

   public static Direction method10(HorsestatsType_2 var0) {
      return Direction.forNumber(var0.getId() + 1);
   }

   public static HorsestatsType_2 method11(Direction var0) {
      return HorsestatsType_2.byId(var0.getNumber() - 1);
   }

   public static Vector3f method12(org.joml.Vector3f var0) {
      return Vector3f.newBuilder().setX(var0.x()).setY(var0.y()).setZ(var0.z()).build();
   }

   public static org.joml.Vector3f method13(Vector3f var0) {
      return new org.joml.Vector3f(var0.getX(), var0.getY(), var0.getZ());
   }

   public static Timestamp method14(long var0) {
      return Timestamp.newBuilder().setSeconds(var0 / 1000L).setNanos((int)(var0 % 1000L * 1000000L)).build();
   }

   public static long method15(Timestamp var0) {
      return var0.getSeconds() * 1000L + var0.getNanos() / 1000000;
   }

   public static Duration method16(java.time.Duration var0) {
      return Duration.newBuilder().setSeconds(var0.toSeconds()).setNanos(var0.toNanosPart()).build();
   }

   public static Vector2i method17(org.joml.Vector2i var0) {
      return Vector2i.newBuilder().setX(var0.x).setZ(var0.y).build();
   }

   public static org.joml.Vector2i method18(Vector2i var0) {
      return new org.joml.Vector2i(var0.getX(), var0.getZ());
   }

   public static Vector3i method19(org.joml.Vector3i var0) {
      return Vector3i.newBuilder().setX(var0.x()).setY(var0.y()).setZ(var0.z()).build();
   }

   public static org.joml.Vector3i method20(Vector3i var0) {
      return new org.joml.Vector3i(var0.getX(), var0.getY(), var0.getZ());
   }

   public static Struct method21(JsonObject var0) {
      Builder var1 = Struct.newBuilder();

      try {
         JsonFormat.parser().merge(var0.toString(), var1);
      } catch (InvalidProtocolBufferException var3) {
         throw new RuntimeException(var3);
      }

      return var1.build();
   }

   public static JsonObject method22(Struct var0) {
      try {
         String var1 = JsonFormat.printer().print(var0);
         return (JsonObject)ThreadModuleDump48.field22.fromJson(var1, JsonObject.class);
      } catch (InvalidProtocolBufferException var2) {
         throw new RuntimeException(var2);
      }
   }
}
