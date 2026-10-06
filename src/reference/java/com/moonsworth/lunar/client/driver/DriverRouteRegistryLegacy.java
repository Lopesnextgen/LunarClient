package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension63;
import com.moonsworth.lunar.bridge.Bridge5Extension65;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms.Markers3Iterator;
import com.moonsworth.lunar.client.driver.core.rewindhandlers.MinimapViewContextLegacy;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Generated;

public class DriverRouteRegistryLegacy implements DriverElementLegacy {
   private static final Set<DriverRouteRegistryLegacy> field1 = new HashSet<>();
   private static final MinimapViewContextLegacy field2 = new MinimapViewContextLegacy();
   public static final DriverRouteRegistryLegacy field3 = method4().method1("/").method2(false).method14();
   public static final DriverRouteRegistryLegacy field4 = method4().method1("/home").method9(true).method12(Bridge5Extension610.class).method14();
   public static final DriverRouteRegistryLegacy field5 = method4().method1("/home_wrapped").method9(true).method14();
   public static final DriverRouteRegistryLegacy field6 = method4().method1("/server_discovery").method9(true).method14();
   public static final DriverRouteRegistryLegacy field7 = method4().method1("/multiplayer_wrapped").method13(Bridge5Extension65.class).method14();
   public static final DriverRouteRegistryLegacy field8 = method4().method1("/hud").method2(false).method14();
   public static final DriverRouteRegistryLegacy field9 = method4()
      .method1("/language")
      .method4(() -> Client.method109().method67().method18())
      .method9(true)
      .method12(Bridge5Extension63.class)
      .method14();
   public static final DriverRouteRegistryLegacy field10 = method4()
      .method1("/rewind")
      .method8(true)
      .method7(false)
      .method6(true)
      .method10(new Markers3Iterator())
      .method14();
   public static final DriverRouteRegistryLegacy field11 = method4().method1("/rewind_effects").method8(true).method7(false).method14();
   public static final DriverRouteRegistryLegacy field12 = method4()
      .method1("/rewinds_list")
      .method8(true)
      .method7(false)
      .method9(true)
      .method14();
   public static final DriverRouteRegistryLegacy field13 = method4().method1("/locker").method7(false).method9(true).method14();
   public static final DriverRouteRegistryLegacy field14 = method4().method1("/emote_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistryLegacy field15 = method4().method1("/spray_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistryLegacy field16 = method4()
      .method1("/spirit_leap")
      .method10(new com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.Markers3Handler())
      .method14();
   public static final DriverRouteRegistryLegacy field17 = method4()
      .method1("/navigator")
      .method7(false)
      .method9(true)
      .method10(field2)
      .method5(() -> Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC())
      .method11(com.moonsworth.lunar.client.driver.rewindhandlers.WaypointContextLegacy::new)
      .method14();
   public static final DriverRouteRegistryLegacy field18 = method4()
      .method1("/configure_waypoint")
      .method5(() -> Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC())
      .method14();
   public static final DriverRouteRegistryLegacy field19 = method4().method1("/screenshot_upload").method14();
   public static final DriverRouteRegistryLegacy field20 = method4().method1("/profile_viewer").method14();
   public static final DriverRouteRegistryLegacy field21 = method4()
      .method1("/profile_import")
      .method9(true)
      .method11(MigrationContextLegacy::new)
      .method14();
   public static final DriverRouteRegistryLegacy field22 = method4().method1("/marker_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistryLegacy field23 = method4().method1("/store_preview_escape_menu").method7(true).method14();
   public static final DriverRouteRegistryLegacy field24 = method4()
      .method1("/shader_cloak_editor")
      .method10(new com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Markers3Handler())
      .method14();
   private final String field25;
   private final boolean field26;
   private final boolean field27;
   private final Runnable field28;
   private final Runnable field29;
   private final boolean field30;
   private final boolean field31;
   private final boolean field32;
   private final boolean field33;
   private final DriverViewContextLegacy field34;
   private final Supplier<DriverContextLegacy> field35;
   private final Class<? extends Bridge5Extension6> field36;
   private final Class<? extends Bridge5Extension6> field37;

   @Override
   public boolean method2() {
      return this.field27;
   }

   public static Set<DriverRouteRegistryLegacy> method3() {
      return field1;
   }

   public static DriverRouteRegistryLegacy.Data2 method4() {
      return new DriverRouteRegistryLegacy.Data2();
   }

   @Generated
   private static boolean method5() {
      return true;
   }

   @Generated
   private static boolean method6() {
      return false;
   }

   @Generated
   private static Runnable method7() {
      return null;
   }

   @Generated
   private static Runnable method8() {
      return null;
   }

   @Generated
   private static boolean method9() {
      return false;
   }

   @Generated
   private static boolean method10() {
      return true;
   }

   @Generated
   private static boolean method11() {
      return false;
   }

   @Generated
   private static boolean method12() {
      return false;
   }

   @Generated
   private static DriverViewContextLegacy method13() {
      return null;
   }

   @Generated
   private static Supplier<DriverContextLegacy> method14() {
      return null;
   }

   @Generated
   DriverRouteRegistryLegacy(
      String var1,
      boolean var2,
      boolean var3,
      Runnable var4,
      Runnable var5,
      boolean var6,
      boolean var7,
      boolean var8,
      boolean var9,
      DriverViewContextLegacy var10,
      Supplier<DriverContextLegacy> var11,
      Class<? extends Bridge5Extension6> var12,
      Class<? extends Bridge5Extension6> var13
   ) {
      this.field25 = var1;
      this.field26 = var2;
      this.field27 = var3;
      this.field28 = var4;
      this.field29 = var5;
      this.field30 = var6;
      this.field31 = var7;
      this.field32 = var8;
      this.field33 = var9;
      this.field34 = var10;
      this.field35 = var11;
      this.field36 = var12;
      this.field37 = var13;
   }

   @Generated
   public String getPath() {
      return this.field25;
   }

   @Generated
   @Override
   public boolean method1() {
      return this.field26;
   }

   @Generated
   public boolean method15() {
      return this.field27;
   }

   @Generated
   public Runnable method16() {
      return this.field28;
   }

   @Generated
   public Runnable method17() {
      return this.field29;
   }

   @Generated
   public boolean method18() {
      return this.field30;
   }

   @Generated
   public boolean method19() {
      return this.field31;
   }

   @Generated
   public boolean method20() {
      return this.field32;
   }

   @Generated
   public boolean method21() {
      return this.field33;
   }

   @Generated
   public DriverViewContextLegacy method22() {
      return this.field34;
   }

   @Generated
   public Supplier<DriverContextLegacy> method23() {
      return this.field35;
   }

   @Generated
   public Class<? extends Bridge5Extension6> method24() {
      return this.field36;
   }

   @Generated
   public Class<? extends Bridge5Extension6> method25() {
      return this.field37;
   }

   @Generated
   public static class Data {
      @Generated
      private String path;
      @Generated
      private boolean field1;
      @Generated
      private boolean field2;
      @Generated
      private boolean field3;
      @Generated
      private boolean field4;
      @Generated
      private boolean field5;
      @Generated
      private Runnable field6;
      @Generated
      private boolean field7;
      @Generated
      private Runnable field8;
      @Generated
      private boolean field9;
      @Generated
      private boolean field10;
      @Generated
      private boolean field11;
      @Generated
      private boolean field12;
      @Generated
      private boolean field13;
      @Generated
      private boolean field14;
      @Generated
      private boolean field15;
      @Generated
      private boolean field16;
      @Generated
      private boolean field17;
      @Generated
      private DriverViewContextLegacy field18;
      @Generated
      private boolean field19;
      @Generated
      private Supplier<DriverContextLegacy> field20;
      @Generated
      private Class<? extends Bridge5Extension6> field21;
      @Generated
      private Class<? extends Bridge5Extension6> field22;

      @Generated
      Data() {
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method1(String var1) {
         this.path = var1;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method2(boolean var1) {
         this.field2 = var1;
         this.field1 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method3(boolean var1) {
         this.field4 = var1;
         this.field3 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method4(Runnable var1) {
         this.field6 = var1;
         this.field5 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method5(Runnable var1) {
         this.field8 = var1;
         this.field7 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method6(boolean var1) {
         this.field10 = var1;
         this.field9 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method7(boolean var1) {
         this.field12 = var1;
         this.field11 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method8(boolean var1) {
         this.field14 = var1;
         this.field13 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method9(boolean var1) {
         this.field16 = var1;
         this.field15 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method10(DriverViewContextLegacy var1) {
         this.field18 = var1;
         this.field17 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method11(Supplier<DriverContextLegacy> var1) {
         this.field20 = var1;
         this.field19 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method12(Class<? extends Bridge5Extension6> var1) {
         this.field21 = var1;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy.Data method13(Class<? extends Bridge5Extension6> var1) {
         this.field22 = var1;
         return this;
      }

      @Generated
      public DriverRouteRegistryLegacy method14() {
         boolean var1 = this.field2;
         if (!this.field1) {
            var1 = DriverRouteRegistryLegacy.method5();
         }

         boolean var2 = this.field4;
         if (!this.field3) {
            var2 = DriverRouteRegistryLegacy.method6();
         }

         Runnable var3 = this.field6;
         if (!this.field5) {
            var3 = DriverRouteRegistryLegacy.method7();
         }

         Runnable var4 = this.field8;
         if (!this.field7) {
            var4 = DriverRouteRegistryLegacy.method8();
         }

         boolean var5 = this.field10;
         if (!this.field9) {
            var5 = DriverRouteRegistryLegacy.method9();
         }

         boolean var6 = this.field12;
         if (!this.field11) {
            var6 = DriverRouteRegistryLegacy.method10();
         }

         boolean var7 = this.field14;
         if (!this.field13) {
            var7 = DriverRouteRegistryLegacy.method11();
         }

         boolean var8 = this.field16;
         if (!this.field15) {
            var8 = DriverRouteRegistryLegacy.method12();
         }

         DriverViewContextLegacy var9 = this.field18;
         if (!this.field17) {
            var9 = DriverRouteRegistryLegacy.method13();
         }

         Supplier var10 = this.field20;
         if (!this.field19) {
            var10 = DriverRouteRegistryLegacy.method14();
         }

         return new DriverRouteRegistryLegacy(this.path, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, this.field21, this.field22);
      }

      @Generated
      @Override
      public String toString() {
         return "DriverRoute.DriverRouteBuilder0(path="
            + this.path
            + ", inputFocus$value="
            + this.field2
            + ", restoreOldUi$value="
            + this.field4
            + ", onDisplay$value="
            + this.field6
            + ", onClose$value="
            + this.field8
            + ", allowMovementKeys$value="
            + this.field10
            + ", pauseGame$value="
            + this.field12
            + ", isRewind$value="
            + this.field14
            + ", mainMenu$value="
            + this.field16
            + ", viewContext$value="
            + this.field18
            + ", defaultProps$value="
            + this.field20
            + ", replaces="
            + this.field21
            + ", attaches="
            + this.field22
            + ")";
      }
   }

   public static class Data2 extends DriverRouteRegistryLegacy.Data {
      @Override
      public DriverRouteRegistryLegacy method14() {
         DriverRouteRegistryLegacy var1 = super.method14();
         DriverRouteRegistryLegacy.field1.add(var1);
         return var1;
      }
   }
}
