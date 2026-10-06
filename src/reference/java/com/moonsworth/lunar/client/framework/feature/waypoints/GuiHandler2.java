package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import javax.annotation.Nullable;
import lombok.Generated;
import lombok.NonNull;
import org.apache.commons.lang3.text.WordUtils;
import org.intellij.lang.annotations.Subst;

public class GuiHandler2 implements Gui {
   private transient double distance;
   @NonNull
   private String name;
   @NonNull
   private Vec3Bridge field1;
   @NonNull
   private String world;
   private int dimension;
   private Gui2Extension3 field2;
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
         && ThreadModuleDump63.method8() != null
         && Client.method109().method16(this.world)
         && (this.dimension == -999 || ThreadModuleDump63.method8().bridge$getDimensionId() == this.dimension)
         && (this.field3 || this.server.equals(WaypointStore.method19()))
         && (!this.field3 || ThreadModuleDump63.method4().method40().method82().isEnabled())
         && (!this.field3 || Click3.getIsland() == this.field2)
         && (!this.field4 || (Boolean)ThreadModuleDump63.method4().method40().method20().method23().get());
   }

   public boolean method1(String var1) {
      return var1.isEmpty() || this.world.isEmpty() || Client.method109().method16(var1);
   }

   public String getLabel() {
      return this.name.isEmpty() ? "W" : this.name.substring(0, 1).toUpperCase();
   }

   public void method2(GuiHandler2 var1) {
      this.name = var1.name;
      this.field1 = var1.field1;
      this.dimension = var1.dimension;
      this.field5 = var1.field5;
      this.field2 = var1.field2;
      this.field3 = var1.field3;
      this.field10 = var1.field10;
      this.field12.method1(var1.field12);
   }

   public JsonElement method128() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("name", this.name);
      var1.addProperty("icon", this.field12.getIcon().name());
      var1.addProperty("server", this.server);
      var1.addProperty("isHandledByServer", this.method41());
      var1.addProperty("isDeathWaypoint", this.field4);
      var1.addProperty("skyBlockLocation", this.field2.name());
      var1.addProperty("skyBlockLocationName", this.field2.getMapValue());
      JsonObject var2 = new JsonObject();
      var2.addProperty("x", this.field1.bridge$xCoord());
      var2.addProperty("y", this.field1.bridge$yCoord());
      var2.addProperty("z", this.field1.bridge$zCoord());
      JsonObject var3 = new JsonObject();
      var3.addProperty("name", this.world);
      var2.add("world", var3);
      var1.add("location", var2);
      var1.addProperty("dimension", this.dimension);
      if (this.method39() != null && !this.method39().isEmpty() && (this.dimension < -1 || this.dimension > 1)) {
         var1.addProperty("dimensionKey", method4(this.method39()));
      }

      var1.addProperty("visible", this.visible);
      var1.add("renderConfig", this.field12.method128());
      var1.addProperty("sortIndex", this.field9);
      if (this.field10 != null) {
         var1.addProperty("groupId", this.field10.getId().toString());
      }

      var1.addProperty("addedAt", ThreadModuleDump34.method4(this.field8));
      return var1;
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
   private static Gui2Extension3 method8() {
      return Gui2Extension3.NONE;
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
   public static GuiHandler2.Data method18() {
      return new GuiHandler2.Data();
   }

   @Generated
   public void setDistance(double var1) {
      this.distance = var1;
   }

   @Generated
   public void setName(@NonNull String var1) {
      if (var1 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      this.name = var1;
   }

   @Generated
   public void method20(@NonNull Vec3Bridge var1) {
      if (var1 == null) {
         throw new NullPointerException("location is marked non-null but is null");
      }

      this.field1 = var1;
   }

   @Generated
   public void method21(@NonNull String var1) {
      if (var1 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      this.world = var1;
   }

   @Generated
   public void setDimension(int var1) {
      this.dimension = var1;
   }

   @Generated
   public void method23(Gui2Extension3 var1) {
      this.field2 = var1;
   }

   @Generated
   public void method24(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public void method25(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public void method26(@Nullable String var1) {
      this.field5 = var1;
   }

   @Generated
   public void setVisible(boolean var1) {
      this.visible = var1;
   }

   @Generated
   public void method27(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public void method28(@NonNull String var1) {
      if (var1 == null) {
         throw new NullPointerException("server is marked non-null but is null");
      }

      this.server = var1;
   }

   @Generated
   public void method29(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public void method30(long var1) {
      this.field8 = var1;
   }

   @Generated
   public void method31(int var1) {
      this.field9 = var1;
   }

   @Generated
   public void method32(@Nullable GuiHandler var1) {
      this.field10 = var1;
   }

   @Generated
   public void method33(int var1) {
      this.field11 = var1;
   }

   @Generated
   public void method34(GuiLoader var1) {
      this.field12 = var1;
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
   public Gui2Extension3 method36() {
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
   public GuiHandler2(
      double var1,
      @NonNull String var3,
      @NonNull Vec3Bridge var4,
      @NonNull String var5,
      int var6,
      Gui2Extension3 var7,
      boolean var8,
      boolean var9,
      @Nullable String var10,
      boolean var11,
      boolean flag,
      @NonNull String var13,
      boolean var14,
      long var15,
      int value,
      @Nullable GuiHandler var18,
      int value2,
      GuiLoader guiLoader
   ) {
      if (var3 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      if (var4 == null) {
         throw new NullPointerException("location is marked non-null but is null");
      }

      if (var5 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      if (var13 == null) {
         throw new NullPointerException("server is marked non-null but is null");
      }

      this.distance = var1;
      this.name = var3;
      this.field1 = var4;
      this.world = var5;
      this.dimension = var6;
      this.field2 = var7;
      this.field3 = var8;
      this.field4 = var9;
      this.field5 = var10;
      this.visible = var11;
      this.field6 = flag;
      this.server = var13;
      this.field7 = var14;
      this.field8 = var15;
      this.field9 = value;
      this.field10 = var18;
      this.field11 = value2;
      this.field12 = guiLoader;
   }

   @Generated
   public GuiHandler2() {
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
      private Gui2Extension3 field9;
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
      public GuiHandler2.Data method1(double var1) {
         this.field2 = var1;
         this.field1 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method2(@NonNull String var1) {
         if (var1 == null) {
            throw new NullPointerException("name is marked non-null but is null");
         }

         this.field4 = var1;
         this.field3 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method3(@NonNull Vec3Bridge var1) {
         if (var1 == null) {
            throw new NullPointerException("location is marked non-null but is null");
         }

         this.field5 = var1;
         return this;
      }

      @Generated
      public GuiHandler2.Data method4(@NonNull String var1) {
         if (var1 == null) {
            throw new NullPointerException("world is marked non-null but is null");
         }

         this.world = var1;
         return this;
      }

      @Generated
      public GuiHandler2.Data method5(int var1) {
         this.field7 = var1;
         this.field6 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method6(Gui2Extension3 var1) {
         this.field9 = var1;
         this.field8 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method7(boolean var1) {
         this.field11 = var1;
         this.field10 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method8(boolean var1) {
         this.field13 = var1;
         this.field12 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method9(@Nullable String var1) {
         this.field14 = var1;
         return this;
      }

      @Generated
      public GuiHandler2.Data method10(boolean var1) {
         this.field16 = var1;
         this.field15 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method11(boolean var1) {
         this.field18 = var1;
         this.field17 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method12(@NonNull String var1) {
         if (var1 == null) {
            throw new NullPointerException("server is marked non-null but is null");
         }

         this.server = var1;
         return this;
      }

      @Generated
      public GuiHandler2.Data method13(boolean var1) {
         this.field20 = var1;
         this.field19 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method14(long var1) {
         this.field22 = var1;
         this.field21 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method15(int var1) {
         this.field24 = var1;
         this.field23 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method16(@Nullable GuiHandler var1) {
         this.field25 = var1;
         return this;
      }

      @Generated
      public GuiHandler2.Data method17(int var1) {
         this.field27 = var1;
         this.field26 = true;
         return this;
      }

      @Generated
      public GuiHandler2.Data method18(GuiLoader var1) {
         this.field29 = var1;
         this.field28 = true;
         return this;
      }

      @Generated
      public GuiHandler2 method19() {
         double var1 = this.field2;
         if (!this.field1) {
            var1 = GuiHandler2.method5();
         }

         String var3 = this.field4;
         if (!this.field3) {
            var3 = GuiHandler2.method6();
         }

         int var4 = this.field7;
         if (!this.field6) {
            var4 = GuiHandler2.method7();
         }

         Gui2Extension3 var5 = this.field9;
         if (!this.field8) {
            var5 = GuiHandler2.method8();
         }

         boolean var6 = this.field11;
         if (!this.field10) {
            var6 = GuiHandler2.method9();
         }

         boolean var7 = this.field13;
         if (!this.field12) {
            var7 = GuiHandler2.method10();
         }

         boolean var8 = this.field16;
         if (!this.field15) {
            var8 = GuiHandler2.method11();
         }

         boolean var9 = this.field18;
         if (!this.field17) {
            var9 = GuiHandler2.method12();
         }

         boolean var10 = this.field20;
         if (!this.field19) {
            var10 = GuiHandler2.method13();
         }

         long var11 = this.field22;
         if (!this.field21) {
            var11 = GuiHandler2.method14();
         }

         int var13 = this.field24;
         if (!this.field23) {
            var13 = GuiHandler2.method15();
         }

         int var14 = this.field27;
         if (!this.field26) {
            var14 = GuiHandler2.method16();
         }

         GuiLoader var15 = this.field29;
         if (!this.field28) {
            var15 = GuiHandler2.method17();
         }

         return new GuiHandler2(
            var1, var3, this.field5, this.world, var4, var5, var6, var7, this.field14, var8, var9, this.server, var10, var11, var13, this.field25, var14, var15
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
