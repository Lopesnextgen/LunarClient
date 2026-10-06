package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiMainMenuBridge;
import com.moonsworth.lunar.bridge.GuiLanguageBridge;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.render.ReplayViewContext;
import com.moonsworth.lunar.client.driver.waypoint.MinimapViewContext;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Generated;

public class DriverRouteRegistry implements DriverElement {
   private static final Set<DriverRouteRegistry> field1 = new HashSet<>();
   private static final MinimapViewContext field2 = new MinimapViewContext();
   public static final DriverRouteRegistry field3 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/").method2(false).method14();
   public static final DriverRouteRegistry field4 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/home").method9(true).method12(GuiMainMenuBridge.class).method14();
   public static final DriverRouteRegistry field5 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/home_wrapped").method9(true).method14();
   public static final DriverRouteRegistry field6 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/server_discovery").method9(true).method14();
   public static final DriverRouteRegistry field7 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/multiplayer_wrapped").method13(GuiMultiplayerBridge.class).method14();
   public static final DriverRouteRegistry field8 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/hud").method2(false).method14();
   public static final DriverRouteRegistry field9 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/language")
      .method4(() -> Client.method109().method67().method18())
      .method9(true)
      .method12(GuiLanguageBridge.class)
      .method14();
   public static final DriverRouteRegistry field10 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/rewind")
      .method8(true)
      .method7(false)
      .method6(true)
      .method10(new ReplayViewContext())
      .method14();
   public static final DriverRouteRegistry field11 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/rewind_effects").method8(true).method7(false).method14();
   public static final DriverRouteRegistry field12 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/rewinds_list")
      .method8(true)
      .method7(false)
      .method9(true)
      .method14();
   public static final DriverRouteRegistry field13 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/locker").method7(false).method9(true).method14();
   public static final DriverRouteRegistry field14 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/emote_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistry field15 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/spray_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistry field16 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/spirit_leap")
      .method10(new com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.SpiritLeapMarkerDispatcher())
      .method14();
   public static final DriverRouteRegistry field17 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/navigator")
      .method7(false)
      .method9(true)
      .method10(field2)
      .method5(() -> Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC())
      .method11(com.moonsworth.lunar.client.driver.waypoint.WaypointContext::new)
      .method14();
   public static final DriverRouteRegistry field18 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/configure_waypoint")
      .method5(() -> Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC())
      .method14();
   public static final DriverRouteRegistry field19 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/screenshot_upload").method14();
   public static final DriverRouteRegistry field20 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/profile_viewer").method14();
   public static final DriverRouteRegistry field21 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/profile_import")
      .method9(true)
      .method11(MigrationContext::new)
      .method14();
   public static final DriverRouteRegistry field22 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/marker_wheel").method6(true).method7(false).method14();
   public static final DriverRouteRegistry field23 = method4().IHHOCIRRHHHCHOORHRIOCOICICOOCI("/store_preview_escape_menu").method7(true).method14();
   public static final DriverRouteRegistry field24 = method4()
      .IHHOCIRRHHHCHOORHRIOCOICICOOCI("/shader_cloak_editor")
      .method10(new com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.ShaderCloakEditorContext())
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
   private final DriverViewContext field34;
   private final Supplier<DriverContext> field35;
   private final Class<? extends GuiScreenBridge> field36;
   private final Class<? extends GuiScreenBridge> field37;

   @Override
   public boolean method2() {
      return this.field27;
   }

   public static Set<DriverRouteRegistry> method3() {
      return field1;
   }

   public static DriverRouteRegistry.DriverRouteRegistrar method4() {
      return new DriverRouteRegistry.DriverRouteRegistrar();
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
   private static DriverViewContext method13() {
      return null;
   }

   @Generated
   private static Supplier<DriverContext> method14() {
      return null;
   }

   @Generated
   DriverRouteRegistry(
      String text1,
      boolean flag2,
      boolean flag3,
      Runnable runnable4,
      Runnable runnable5,
      boolean flag6,
      boolean flag7,
      boolean flag8,
      boolean flag9,
      DriverViewContext markers310,
      Supplier<DriverContext> supplier11,
      Class<? extends GuiScreenBridge> clazz12,
      Class<? extends GuiScreenBridge> clazz13
   ) {
      this.field25 = text1;
      this.field26 = flag2;
      this.field27 = flag3;
      this.field28 = runnable4;
      this.field29 = runnable5;
      this.field30 = flag6;
      this.field31 = flag7;
      this.field32 = flag8;
      this.field33 = flag9;
      this.field34 = markers310;
      this.field35 = supplier11;
      this.field36 = clazz12;
      this.field37 = clazz13;
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
   public DriverViewContext method22() {
      return this.field34;
   }

   @Generated
   public Supplier<DriverContext> method23() {
      return this.field35;
   }

   @Generated
   public Class<? extends GuiScreenBridge> method24() {
      return this.field36;
   }

   @Generated
   public Class<? extends GuiScreenBridge> method25() {
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
      private DriverViewContext field18;
      @Generated
      private boolean field19;
      @Generated
      private Supplier<DriverContext> field20;
      @Generated
      private Class<? extends GuiScreenBridge> field21;
      @Generated
      private Class<? extends GuiScreenBridge> field22;

      @Generated
      Data() {
      }

      @Generated
      public DriverRouteRegistry.Data method1(String text1) {
         this.path = text1;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method2(boolean flag1) {
         this.field2 = flag1;
         this.field1 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method3(boolean flag1) {
         this.field4 = flag1;
         this.field3 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method4(Runnable runnable1) {
         this.field6 = runnable1;
         this.field5 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method5(Runnable runnable1) {
         this.field8 = runnable1;
         this.field7 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method6(boolean flag1) {
         this.field10 = flag1;
         this.field9 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method7(boolean flag1) {
         this.field12 = flag1;
         this.field11 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method8(boolean flag1) {
         this.field14 = flag1;
         this.field13 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method9(boolean flag1) {
         this.field16 = flag1;
         this.field15 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method10(DriverViewContext markers31) {
         this.field18 = markers31;
         this.field17 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method11(Supplier<DriverContext> supplier1) {
         this.field20 = supplier1;
         this.field19 = true;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method12(Class<? extends GuiScreenBridge> clazz1) {
         this.field21 = clazz1;
         return this;
      }

      @Generated
      public DriverRouteRegistry.Data method13(Class<? extends GuiScreenBridge> clazz1) {
         this.field22 = clazz1;
         return this;
      }

      @Generated
      public DriverRouteRegistry method14() {
         boolean flag1 = this.field2;
         if (!this.field1) {
            flag1 = DriverRouteRegistry.method5();
         }

         boolean flag2 = this.field4;
         if (!this.field3) {
            flag2 = DriverRouteRegistry.method6();
         }

         Runnable runnable3 = this.field6;
         if (!this.field5) {
            runnable3 = DriverRouteRegistry.method7();
         }

         Runnable runnable4 = this.field8;
         if (!this.field7) {
            runnable4 = DriverRouteRegistry.method8();
         }

         boolean flag5 = this.field10;
         if (!this.field9) {
            flag5 = DriverRouteRegistry.method9();
         }

         boolean flag6 = this.field12;
         if (!this.field11) {
            flag6 = DriverRouteRegistry.method10();
         }

         boolean flag7 = this.field14;
         if (!this.field13) {
            flag7 = DriverRouteRegistry.method11();
         }

         boolean flag8 = this.field16;
         if (!this.field15) {
            flag8 = DriverRouteRegistry.method12();
         }

         DriverViewContext markers39 = this.field18;
         if (!this.field17) {
            markers39 = DriverRouteRegistry.method13();
         }

         Supplier supplier10 = this.field20;
         if (!this.field19) {
            supplier10 = DriverRouteRegistry.method14();
         }

         return new DriverRouteRegistry(this.path, flag1, flag2, runnable3, runnable4, flag5, flag6, flag7, flag8, markers39, supplier10, this.field21, this.field22);
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

   public static class DriverRouteRegistrar extends DriverRouteRegistry.Data {
      public DriverRouteRegistrar() {
      }

      @Override
      public DriverRouteRegistry method14() {
         DriverRouteRegistry markers2handler21 = super.method14();
         DriverRouteRegistry.field1.add(markers2handler21);
         return markers2handler21;
      }
   }
}
