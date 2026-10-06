package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.driver.bridge.DriverDataProvider;
import com.moonsworth.lunar.client.util.text.DateUtils;
import com.moonsworth.lunar.client.framework.Ref;
import javax.annotation.Nullable;
import lombok.Generated;
import lombok.NonNull;
import org.apache.commons.lang3.text.WordUtils;
import org.intellij.lang.annotations.Subst;

public class Waypoint implements DriverDataProvider {
   private transient double distance;
   @NonNull
   private String name;
   @NonNull
   private Vec3Bridge field1;
   @NonNull
   private String world;
   private int dimension;
   private SkyblockIsland field2;
   private boolean field3;
   private boolean field4;
   @Nullable
   private String field5;
   private boolean visible;
   private boolean field6;
   @NonNull
   private String server;
   private boolean field7;
   private long field8;
   private int field9;
   @Nullable
   private GuiHandler field10;
   private int field11;
   private GuiLoader field12;

   public boolean shouldRender() {
      return !Client.method109().method40().method64().method13()
         && Ref.method8() != null
         && Client.method109().method16(this.world)
         && (this.dimension == -999 || Ref.method8().bridge$getDimensionId() == this.dimension)
         && (this.field3 || this.server.equals(WaypointStore.method19()))
         && (!this.field3 || Ref.method4().method40().method82().isEnabled())
         && (!this.field3 || IslandUtils.getIsland() == this.field2)
         && (!this.field4 || (Boolean)Ref.method4().method40().method20().method23().get());
   }

   public boolean method1(String text1) {
      return text1.isEmpty() || this.world.isEmpty() || Client.method109().method16(text1);
   }

   public String getLabel() {
      return this.name.isEmpty() ? "W" : this.name.substring(0, 1).toUpperCase();
   }

   public void method2(Waypoint waypoint) {
      this.name = waypoint.name;
      this.field1 = waypoint.field1;
      this.dimension = waypoint.dimension;
      this.field5 = waypoint.field5;
      this.field2 = waypoint.field2;
      this.field3 = waypoint.field3;
      this.field10 = waypoint.field10;
      this.field12.method1(waypoint.field12);
   }

   public JsonElement method128() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("name", this.name);
      json1.addProperty("icon", this.field12.getIcon().name());
      json1.addProperty("server", this.server);
      json1.addProperty("isHandledByServer", this.method41());
      json1.addProperty("isDeathWaypoint", this.field4);
      json1.addProperty("skyBlockLocation", this.field2.name());
      json1.addProperty("skyBlockLocationName", this.field2.getMapValue());
      JsonObject json2 = new JsonObject();
      json2.addProperty("x", this.field1.bridge$xCoord());
      json2.addProperty("y", this.field1.bridge$yCoord());
      json2.addProperty("z", this.field1.bridge$zCoord());
      JsonObject json3 = new JsonObject();
      json3.addProperty("name", this.world);
      json2.add("world", json3);
      json1.add("location", json2);
      json1.addProperty("dimension", this.dimension);
      if (this.method39() != null && !this.method39().isEmpty() && (this.dimension < -1 || this.dimension > 1)) {
         json1.addProperty("dimensionKey", method4(this.method39()));
      }

      json1.addProperty("visible", this.visible);
      json1.add("renderConfig", this.field12.method128());
      json1.addProperty("sortIndex", this.field9);
      if (this.field10 != null) {
         json1.addProperty("groupId", this.field10.getId().toString());
      }

      json1.addProperty("addedAt", DateUtils.method4(this.field8));
      return json1;
   }

   @Subst("dimensionName")
   public static String method4(String text) {
      text = Bridge.method8().method71(text);
      return text.contains(":") ? WordUtils.capitalizeFully(text.substring(text.indexOf(58) + 1).replace('_', ' ')) : text;
   }

   @Generated
   private static double method5() {
      return -1.0;
   }

   @Generated
   private static String method6() {
      return "";
   }

   @Generated
   private static int method7() {
      return -999;
   }

   @Generated
   private static SkyblockIsland method8() {
      return SkyblockIsland.NONE;
   }

   @Generated
   private static boolean method9() {
      return false;
   }

   @Generated
   private static boolean method10() {
      return false;
   }

   @Generated
   private static boolean method11() {
      return true;
   }

   @Generated
   private static boolean method12() {
      return false;
   }

   @Generated
   private static boolean method13() {
      return true;
   }

   @Generated
   private static long method14() {
      return System.currentTimeMillis();
   }

   @Generated
   private static int method15() {
      return -1;
   }

   @Generated
   private static int method16() {
      return -1;
   }

   @Generated
   private static GuiLoader method17() {
      return GuiLoader.field1;
   }

   @Generated
   public static Waypoint.Data method18() {
      return new Waypoint.Data();
   }

   @Generated
   public void setDistance(double value1) {
      this.distance = value1;
   }

   @Generated
   public void setName(@NonNull String text1) {
      if (text1 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      this.name = text1;
   }

   @Generated
   public void method20(@NonNull Vec3Bridge horsestats151) {
      if (horsestats151 == null) {
         throw new NullPointerException("location is marked non-null but is null");
      }

      this.field1 = horsestats151;
   }

   @Generated
   public void method21(@NonNull String text1) {
      if (text1 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      this.world = text1;
   }

   @Generated
   public void setDimension(int number1) {
      this.dimension = number1;
   }

   @Generated
   public void method23(SkyblockIsland gui2extension31) {
      this.field2 = gui2extension31;
   }

   @Generated
   public void method24(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public void method25(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   public void method26(@Nullable String text1) {
      this.field5 = text1;
   }

   @Generated
   public void setVisible(boolean flag1) {
      this.visible = flag1;
   }

   @Generated
   public void method27(boolean flag1) {
      this.field6 = flag1;
   }

   @Generated
   public void method28(@NonNull String text1) {
      if (text1 == null) {
         throw new NullPointerException("server is marked non-null but is null");
      }

      this.server = text1;
   }

   @Generated
   public void method29(boolean flag1) {
      this.field7 = flag1;
   }

   @Generated
   public void method30(long number1) {
      this.field8 = number1;
   }

   @Generated
   public void method31(int number1) {
      this.field9 = number1;
   }

   @Generated
   public void method32(@Nullable GuiHandler guihandler1) {
      this.field10 = guihandler1;
   }

   @Generated
   public void method33(int number1) {
      this.field11 = number1;
   }

   @Generated
   public void method34(GuiLoader guiloader1) {
      this.field12 = guiloader1;
   }

   @Generated
   public double getDistance() {
      return this.distance;
   }

   @NonNull
   @Generated
   public String getName() {
      return this.name;
   }

   @NonNull
   @Generated
   public Vec3Bridge method35() {
      return this.field1;
   }

   @NonNull
   @Generated
   public String getWorld() {
      return this.world;
   }

   @Generated
   public int getDimension() {
      return this.dimension;
   }

   @Generated
   public SkyblockIsland method36() {
      return this.field2;
   }

   @Generated
   public boolean method37() {
      return this.field3;
   }

   @Generated
   public boolean method38() {
      return this.field4;
   }

   @Nullable
   @Generated
   public String method39() {
      return this.field5;
   }

   @Generated
   public boolean isVisible() {
      return this.visible;
   }

   @Generated
   public boolean method40() {
      return this.field6;
   }

   @NonNull
   @Generated
   public String getServer() {
      return this.server;
   }

   @Generated
   public boolean method41() {
      return this.field7;
   }

   @Generated
   public long method42() {
      return this.field8;
   }

   @Generated
   public int method43() {
      return this.field9;
   }

   @Nullable
   @Generated
   public GuiHandler method44() {
      return this.field10;
   }

   @Generated
   public int method45() {
      return this.field11;
   }

   @Generated
   public GuiLoader method46() {
      return this.field12;
   }

   @Generated
   @Override
   public String toString() {
      return "Waypoint(distance="
         + this.getDistance()
         + ", name="
         + this.getName()
         + ", location="
         + this.method35()
         + ", world="
         + this.getWorld()
         + ", dimension="
         + this.getDimension()
         + ", skyBlockLocation="
         + this.method36()
         + ", isSkyBlockWaypoint="
         + this.method37()
         + ", isDeathWaypoint="
         + this.method38()
         + ", customDimensionKey="
         + this.method39()
         + ", visible="
         + this.isVisible()
         + ", forced="
         + this.method40()
         + ", server="
         + this.getServer()
         + ", handledByServer="
         + this.method41()
         + ", addedAtMs="
         + this.method42()
         + ", sortIndex="
         + this.method43()
         + ", group="
         + this.method44()
         + ", groupIndex="
         + this.method45()
         + ", renderConfig="
         + this.method46()
         + ")";
   }

   @Generated
   public Waypoint(
      double value1,
      @NonNull String text3,
      @NonNull Vec3Bridge horsestats154,
      @NonNull String text5,
      int value,
      SkyblockIsland skyblockIsland,
      boolean flag8,
      boolean flag9,
      @Nullable String text10,
      boolean flag,
      boolean flag2,
      @NonNull String text13,
      boolean flag3,
      long value2,
      int value3,
      @Nullable GuiHandler guihandler18,
      int value4,
      GuiLoader guiLoader
   ) {
      if (text3 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      if (horsestats154 == null) {
         throw new NullPointerException("location is marked non-null but is null");
      }

      if (text5 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      if (text13 == null) {
         throw new NullPointerException("server is marked non-null but is null");
      }

      this.distance = value1;
      this.name = text3;
      this.field1 = horsestats154;
      this.world = text5;
      this.dimension = value;
      this.field2 = skyblockIsland;
      this.field3 = flag8;
      this.field4 = flag9;
      this.field5 = text10;
      this.visible = flag;
      this.field6 = flag2;
      this.server = text13;
      this.field7 = flag3;
      this.field8 = value2;
      this.field9 = value3;
      this.field10 = guihandler18;
      this.field11 = value4;
      this.field12 = guiLoader;
   }

   @Generated
   public Waypoint() {
      this.distance = method5();
      this.name = method6();
      this.dimension = method7();
      this.field2 = method8();
      this.field3 = method9();
      this.field4 = method10();
      this.visible = method11();
      this.field6 = method12();
      this.field7 = method13();
      this.field8 = method14();
      this.field9 = method15();
      this.field11 = method16();
      this.field12 = method17();
   }

   @Generated
   public static class Data {
      @Generated
      private boolean field1;
      @Generated
      private double field2;
      @Generated
      private boolean field3;
      @Generated
      private String field4;
      @Generated
      private Vec3Bridge field5;
      @Generated
      private String world;
      @Generated
      private boolean field6;
      @Generated
      private int field7;
      @Generated
      private boolean field8;
      @Generated
      private SkyblockIsland field9;
      @Generated
      private boolean field10;
      @Generated
      private boolean field11;
      @Generated
      private boolean field12;
      @Generated
      private boolean field13;
      @Generated
      private String field14;
      @Generated
      private boolean field15;
      @Generated
      private boolean field16;
      @Generated
      private boolean field17;
      @Generated
      private boolean field18;
      @Generated
      private String server;
      @Generated
      private boolean field19;
      @Generated
      private boolean field20;
      @Generated
      private boolean field21;
      @Generated
      private long field22;
      @Generated
      private boolean field23;
      @Generated
      private int field24;
      @Generated
      private GuiHandler field25;
      @Generated
      private boolean field26;
      @Generated
      private int field27;
      @Generated
      private boolean field28;
      @Generated
      private GuiLoader field29;

      @Generated
      Data() {
      }

      @Generated
      public Waypoint.Data method1(double value1) {
         this.field2 = value1;
         this.field1 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method2(@NonNull String text1) {
         if (text1 == null) {
            throw new NullPointerException("name is marked non-null but is null");
         }

         this.field4 = text1;
         this.field3 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method3(@NonNull Vec3Bridge horsestats151) {
         if (horsestats151 == null) {
            throw new NullPointerException("location is marked non-null but is null");
         }

         this.field5 = horsestats151;
         return this;
      }

      @Generated
      public Waypoint.Data method4(@NonNull String text1) {
         if (text1 == null) {
            throw new NullPointerException("world is marked non-null but is null");
         }

         this.world = text1;
         return this;
      }

      @Generated
      public Waypoint.Data method5(int number1) {
         this.field7 = number1;
         this.field6 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method6(SkyblockIsland gui2extension31) {
         this.field9 = gui2extension31;
         this.field8 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method7(boolean flag1) {
         this.field11 = flag1;
         this.field10 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method8(boolean flag1) {
         this.field13 = flag1;
         this.field12 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method9(@Nullable String text1) {
         this.field14 = text1;
         return this;
      }

      @Generated
      public Waypoint.Data method10(boolean flag1) {
         this.field16 = flag1;
         this.field15 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method11(boolean flag1) {
         this.field18 = flag1;
         this.field17 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method12(@NonNull String text1) {
         if (text1 == null) {
            throw new NullPointerException("server is marked non-null but is null");
         }

         this.server = text1;
         return this;
      }

      @Generated
      public Waypoint.Data method13(boolean flag1) {
         this.field20 = flag1;
         this.field19 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method14(long number1) {
         this.field22 = number1;
         this.field21 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method15(int number1) {
         this.field24 = number1;
         this.field23 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method16(@Nullable GuiHandler guihandler1) {
         this.field25 = guihandler1;
         return this;
      }

      @Generated
      public Waypoint.Data method17(int number1) {
         this.field27 = number1;
         this.field26 = true;
         return this;
      }

      @Generated
      public Waypoint.Data method18(GuiLoader guiloader1) {
         this.field29 = guiloader1;
         this.field28 = true;
         return this;
      }

      @Generated
      public Waypoint method19() {
         double value1 = this.field2;
         if (!this.field1) {
            value1 = Waypoint.method5();
         }

         String text3 = this.field4;
         if (!this.field3) {
            text3 = Waypoint.method6();
         }

         int number4 = this.field7;
         if (!this.field6) {
            number4 = Waypoint.method7();
         }

         SkyblockIsland gui2extension35 = this.field9;
         if (!this.field8) {
            gui2extension35 = Waypoint.method8();
         }

         boolean flag6 = this.field11;
         if (!this.field10) {
            flag6 = Waypoint.method9();
         }

         boolean flag7 = this.field13;
         if (!this.field12) {
            flag7 = Waypoint.method10();
         }

         boolean flag8 = this.field16;
         if (!this.field15) {
            flag8 = Waypoint.method11();
         }

         boolean flag9 = this.field18;
         if (!this.field17) {
            flag9 = Waypoint.method12();
         }

         boolean flag10 = this.field20;
         if (!this.field19) {
            flag10 = Waypoint.method13();
         }

         long number11 = this.field22;
         if (!this.field21) {
            number11 = Waypoint.method14();
         }

         int number13 = this.field24;
         if (!this.field23) {
            number13 = Waypoint.method15();
         }

         int number14 = this.field27;
         if (!this.field26) {
            number14 = Waypoint.method16();
         }

         GuiLoader guiloader15 = this.field29;
         if (!this.field28) {
            guiloader15 = Waypoint.method17();
         }

         return new Waypoint(
            value1, text3, this.field5, this.world, number4, gui2extension35, flag6, flag7, this.field14, flag8, flag9, this.server, flag10, number11, number13, this.field25, number14, guiloader15
         );
      }

      @Generated
      @Override
      public String toString() {
         return "Waypoint.WaypointBuilder(distance$value="
            + this.field2
            + ", name$value="
            + this.field4
            + ", location="
            + this.field5
            + ", world="
            + this.world
            + ", dimension$value="
            + this.field7
            + ", skyBlockLocation$value="
            + this.field9
            + ", isSkyBlockWaypoint$value="
            + this.field11
            + ", isDeathWaypoint$value="
            + this.field13
            + ", customDimensionKey="
            + this.field14
            + ", visible$value="
            + this.field16
            + ", forced$value="
            + this.field18
            + ", server="
            + this.server
            + ", handledByServer$value="
            + this.field20
            + ", addedAtMs$value="
            + this.field22
            + ", sortIndex$value="
            + this.field24
            + ", group="
            + this.field25
            + ", groupIndex$value="
            + this.field27
            + ", renderConfig$value="
            + this.field29
            + ")";
      }
   }
}
