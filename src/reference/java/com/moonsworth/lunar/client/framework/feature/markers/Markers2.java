package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.common.v1.Vector3f;
import com.lunarclient.websocket.marker.v1.Marker;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.render.markers.ApolloMarkerData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.jspecify.annotations.NonNull;

public class Markers2 {
   public static final Gson field1 = new GsonBuilder()
      .registerTypeAdapter(Markers3_2.class, Markers3_2.field3)
      .registerTypeAdapter(Markers3_2.class, Markers3_2.field4)
      .create();
   private final UUID field2;
   private final String field3;
   private final int field4;
   private final long field5 = ThreadModuleDump63.method3().bridge$getSystemTime();
   private final Vector3d pos;
   private Markers2.@NonNull Data field6;
   private long field7 = 0L;
   @Nullable
   protected Markers3 field8 = null;
   @Nullable
   protected ApolloMarkerData field9 = null;
   private boolean field10 = false;
   private long field11 = this.field5;
   @Nullable
   private Markers2.Data2 field12 = null;
   @Nullable
   private ItemStackBridge field13 = null;
   @Nullable
   private SIterator2.Data field14 = null;
   private Component field15 = null;

   @Nullable
   public Markers2.Data2 method1() {
      if (this.field12 == null && this.field6.field2 != null && this.field6.field1 == Markers2.Type2.PLAYER) {
         this.field12 = Markers2.Data2.method2(this.field6.field2);
      }

      return this.field12;
   }

   @Nullable
   public ItemStackBridge method2() {
      if (this.field13 == null && this.field6.field2 != null && (this.field6.field1 == Markers2.Type2.ITEM || this.field6.field1 == Markers2.Type2.BLOCK)) {
         this.field13 = (ItemStackBridge)SIterator.method6().method3(this.field6.field2, this.field4).orElse(null);
      }

      return this.field13;
   }

   @Nullable
   public SIterator2.Data method3() {
      if (this.field14 == null && this.field6.field1 == Markers2.Type2.ENTITY) {
         SIterator2.Data var1 = new SIterator2.Data(
            "unknown", Component.text(ThreadModuleDump63.method4().method40().method87().method2("unknownEntity", new Object[0]))
         );
         if (this.field6.field2 == null) {
            this.field14 = var1;
         } else {
            this.field14 = SIterator.method5().method3(this.field6.field2, this.field4).orElse(var1);
         }
      }

      return this.field14;
   }

   @NotNull
   public Component method4() {
      if (this.field15 == null) {
         Markers2.Type2 var1 = this.field6.method3();
         if (var1 == Markers2.Type2.ITEM || var1 == Markers2.Type2.BLOCK) {
            ItemStackBridge var6 = this.method2();
            if (var6 == null) {
               String var8 = var1 == Markers2.Type2.ITEM ? "unknownItem" : "unknownBlock";
               this.field15 = Component.text(ThreadModuleDump63.method4().method40().method87().method2(var8, new Object[0]));
            } else {
               this.field15 = Component.text(var6.bridge$getDisplayName());
            }
         } else if (var1 == Markers2.Type2.ENTITY) {
            SIterator2.Data var2 = this.method3();
            TextComponent var3 = Component.text(ThreadModuleDump63.method4().method40().method87().method2("unknownEntity", new Object[0]));
            if ("unknown".equals(var2.method2())) {
               this.field15 = var3;
            } else {
               this.field15 = Bridge.method61().method4(var2.method2());
               if (this.field15 == null || this.field15 == Component.empty()) {
                  this.field15 = var3;
               }

               if (var2.method3() != null) {
                  Component var4 = ((TextComponent)((TextComponent)Component.empty().append(Component.text(" (").color(NamedTextColor.GRAY)))
                        .append(var2.method3().colorIfAbsent(NamedTextColor.WHITE)))
                     .append(((TextComponent)Component.empty().append(Component.text(")"))).color(NamedTextColor.GRAY));
                  this.field15 = this.field15.append(var4);
               }
            }
         } else if (var1 == Markers2.Type2.PLAYER) {
            Markers2.Data2 var5 = this.method1();
            TextComponent var7 = Component.text(ThreadModuleDump63.method4().method40().method87().method2("unknownPlayer", new Object[0]));
            if (var5 != null) {
               this.field15 = this.method5(var5.field1, var5.field2).orElse(var7);
            } else {
               this.field15 = var7;
            }
         }
      }

      if (this.field15 == null) {
         this.field15 = Component.text("Unknown");
      }

      return this.field15;
   }

   private Optional<Component> method5(UUID var1, Component var2) {
      Optional var3 = ThreadModuleDump63.method8().bridge$getPlayerByUniqueId(var1);
      if (var3.isPresent()) {
         return !ThreadModuleDump63.method7().bridge$canSeeName((Bridge6_10)var3.get())
            ? Optional.empty()
            : Optional.of(((Bridge6_10)var3.get()).bridge$getDisplayNameComponent());
      }

      ClientPacketListenerBridge var4 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var4 != null) {
         Bridge2_33 var5 = var4.bridge$getPlayerInfo(var1);
         if (var5 != null) {
            return Optional.of(var5.bridge$getDisplayName());
         }
      }

      return Optional.of(var2);
   }

   public Markers3 method6() {
      if (this.field8 == null) {
         this.field8 = new Markers3(this);
      }

      return this.field8;
   }

   public void method7(Markers2 var1, ApolloMarkerData var2) {
      this.pos.set(var1.pos);
      this.field6 = var1.field6;
      this.field12 = var1.field12;
      this.field13 = var1.field13;
      this.field14 = var1.field14;
      this.field15 = null;
      this.field9 = var2;
      this.field11 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   public boolean method8() {
      return this.field9 != null;
   }

   public float scale() {
      return this.field9 != null && this.field9.method4() != null ? this.field9.method4() : ThreadModuleDump63.method4().method40().method87().method34().get();
   }

   public boolean method9() {
      return this.field9 != null && this.field9.method5() != null ? this.field9.method5() : ThreadModuleDump63.method4().method40().method87().method35().get();
   }

   public boolean method10() {
      return this.field9 != null && this.field9.method6() != null ? this.field9.method6() : ThreadModuleDump63.method4().method40().method87().method43().get();
   }

   public boolean method11() {
      return this.field9 != null && this.field9.method7() != null ? this.field9.method7() : ThreadModuleDump63.method4().method40().method87().method45().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type method12() {
      return this.field9 != null && this.field9.method9() != null ? this.field9.method9() : ThreadModuleDump63.method4().method40().method87().method37().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type method13() {
      return this.field9 != null && this.field9.method12() != null
         ? this.field9.method12()
         : ThreadModuleDump63.method4().method40().method87().method38().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type method14() {
      return this.field9 != null && this.field9.method10() != null
         ? this.field9.method10()
         : ThreadModuleDump63.method4().method40().method87().method41().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type method15() {
      return this.field9 != null && this.field9.method11() != null
         ? this.field9.method11()
         : ThreadModuleDump63.method4().method40().method87().method42().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type3 method16() {
      return this.field9 != null && this.field9.method8() != null ? this.field9.method8() : ThreadModuleDump63.method4().method40().method87().method39().get();
   }

   public com.moonsworth.lunar.client.mod.render.markers.Markers.Type2 method17() {
      return this.field9 != null && this.field9.method13() != null
         ? this.field9.method13()
         : ThreadModuleDump63.method4().method40().method87().method40().get();
   }

   public String method18() {
      return this.field9 != null && this.field9.getOwnerSuffix() != null
         ? this.field9.getOwnerSuffix()
         : ThreadModuleDump63.method4().method40().method87().method44().get();
   }

   public int method19() {
      if (this.field9 != null && this.field9.method3() != null) {
         return this.field9.method3();
      }

      com.moonsworth.lunar.client.mod.render.markers.Markers var1 = ThreadModuleDump63.method4().method40().method87();

      return switch (this.field6.method5()) {
         case DANGER -> var1.getDangerMarkerColor().method13();
         case INFO -> var1.getInfoMarkerColor().method13();
         case INTEREST -> var1.getInterestMarkerColor().method13();
         default -> var1.getMarkerColor().method13();
      };
   }

   public Marker method20(com.moonsworth.lunar.client.framework.feature.markers.mixin.Markers var1) {
      return Marker.newBuilder()
         .setSource(var1.method1())
         .addDescription(this.field6.method1())
         .setPosition(Vector3f.newBuilder().setX((float)this.pos.x).setY((float)this.pos.y).setZ((float)this.pos.z).build())
         .build();
   }

   public static Markers2 method21(UUID var0, String var1, int var2, Marker var3) {
      Vector3f var4 = var3.getPosition();
      return new Markers2(var0, var1, var2, new Vector3d(var4.getX(), var4.getY(), var4.getZ()), Markers2.Data.method2(var3.getDescription(0)));
   }

   @Generated
   public Markers2(UUID var1, String var2, int var3, Vector3d var4, Markers2.@NonNull Data var5) {
      if (var5 == null) {
         throw new NullPointerException("description is marked non-null but is null");
      }

      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.pos = var4;
      this.field6 = var5;
   }

   @Generated
   public UUID getOwnerId() {
      return this.field2;
   }

   @Generated
   public String getOwnerName() {
      return this.field3;
   }

   @Generated
   public int method22() {
      return this.field4;
   }

   @Generated
   public long method23() {
      return this.field5;
   }

   @Generated
   public Vector3d getPos() {
      return this.pos;
   }

   @Generated
   public Markers2.@NonNull Data method25() {
      return this.field6;
   }

   @Generated
   public long method26() {
      return this.field7;
   }

   @Nullable
   @Generated
   public ApolloMarkerData method27() {
      return this.field9;
   }

   @Generated
   public boolean method28() {
      return this.field10;
   }

   @Generated
   public long method29() {
      return this.field11;
   }

   @Generated
   public void method30(long var1) {
      this.field7 = var1;
   }

   @Generated
   public void method31(@Nullable ApolloMarkerData var1) {
      this.field9 = var1;
   }

   @Generated
   public void method32(boolean var1) {
      this.field10 = var1;
   }

   @Generated
   public void method33(@Nullable ItemStackBridge var1) {
      this.field13 = var1;
   }

   public class Data {
      @SerializedName("type")
      @NotNull
      private final Markers2.Type2 field1;
      @SerializedName("type_key")
      @Nullable
      private final Markers3_2 field2;
      @SerializedName("flag")
      @NotNull
      private final Markers2.Type field3;

      public Data(@NotNull Markers2.Type2 var1, @Nullable Markers3_2 var2, @NotNull Markers2.Type var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public String method1() {
         return Markers2.field1.toJson(this);
      }

      public static Markers2.Data method2(String var0) {
         return (Markers2.Data)Markers2.field1.fromJson(var0, Markers2.Data.class);
      }

      @SerializedName("type")
      @NotNull
      public Markers2.Type2 method3() {
         return this.field1;
      }

      @SerializedName("type_key")
      @Nullable
      public Markers3_2 method4() {
         return this.field2;
      }

      @SerializedName("flag")
      @NotNull
      public Markers2.Type method5() {
         return this.field3;
      }
   }

   public class Data2 {
      @NotNull
      private final UUID field1;
      @Nullable
      private final Component field2;
      private final boolean field3;

      public Data2(@NotNull UUID var1, @Nullable Component var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static Markers3_2 method1(UUID var0, @Nullable Component var1, boolean var2) {
         Markers2_3 var3 = Markers2_3.method2();
         if (var1 != null) {
            var3 = Markers2_3.method3(LegacyComponentSerializer.legacySection().serialize(var1) + ":" + var2);
         }

         return new Markers3_2(var0.toString(), var3);
      }

      public static Markers2.Data2 method2(Markers3_2 var0) {
         TextComponent var1 = null;
         boolean var2 = false;
         if (!var0.method1().isEmpty()) {
            String[] var3 = var0.method1().value().split(":");
            var1 = LegacyComponentSerializer.legacySection().deserialize(var3[0]);
            var2 = Boolean.parseBoolean(var3[1]);
         }

         return new Markers2.Data2(UUID.fromString(var0.value()), var1, var2);
      }

      @NotNull
      public UUID id() {
         return this.field1;
      }

      @Nullable
      public Component name() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }
   }

   public enum Type {
      NORMAL("Normal", "normal.png"),
      DANGER("Danger", "danger.png"),
      INFO("Info", "info.png"),
      INTEREST("Interest", "interest.png");

      final String name;
      final ResourceLocationBridge resource;

      Type(String var3, String var4) {
         this.name = var3;
         this.resource = ResourceLocationBridge.create("lunar", "markers/" + var4);
      }

      @Generated
      public String getName() {
         return this.name;
      }

      @Generated
      public ResourceLocationBridge getResource() {
         return this.resource;
      }
   }

   public enum Type2 {
      ITEM,
      ENTITY,
      PLAYER,
      BLOCK;
   }
}
