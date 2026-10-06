package com.moonsworth.lunar.client.driver.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.driver.BrowserHandler;
import com.moonsworth.lunar.client.driver.FunctionBusImplLegacy;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.LoggerHandler;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.VanillaHomeContextLegacy;
import com.moonsworth.lunar.client.driver.WebOsrNativeLegacy;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.core.gui.mixin.SettingsBridgeLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.lunar.ichor.MixinSupport;
import com.moonsworth.webosr.config.ThreadConfig;
import com.moonsworth.webosr.config.UltralightConfig;
import com.moonsworth.webosr.input.Mouse.Cursor;
import com.moonsworth.webosr.javascript.FunctionBus;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.WebEngine;
import com.moonsworth.webosr.wrappers.WebOSR;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class DriverViewportLegacy extends com.moonsworth.lunar.client.driver.DriverViewportLegacy {
   private static final Map<Browser, Boolean> field3 = new HashMap<>();
   private static final boolean field4 = Boolean.getBoolean("lunar.webosr.debug");
   private static final boolean field5 = Boolean.getBoolean("lunar.webosr.noFilter");
   private static final String field6 = System.getProperty("lunar.webosr.bundlePath", ".");
   private static final String field7 = method44(System.getProperty("lunar.webosr.url", "file:///index.html"));
   private static DriverViewportLegacy field8;
   public static boolean shutdown;
   private static WebEngine field9;
   private static boolean field10;
   private static DriverViewContextLegacy<?> field11;
   private final DriverViewLegacy field12;
   protected MigrationContextLegacy field13;
   private final com.moonsworth.lunar.client.driver.DriverBridgeLegacy field14 = new com.moonsworth.lunar.client.driver.DriverBridgeLegacy(this);
   protected final AtomicBoolean field15 = new AtomicBoolean(false);
   protected final AtomicBoolean field16 = new AtomicBoolean(false);
   private static final Map<String, DriverGuiExtensionLegacy> field17 = new HashMap<>();
   private static final Map<String, JsonObject> field18 = new ConcurrentHashMap<>();
   private final Map<String, JsonElement> field19 = new HashMap<>();
   @NotNull
   private DriverRouteRegistryLegacy field20 = DriverRouteRegistryLegacy.field3;
   private DriverContextLegacy field21 = null;
   @NotNull
   private DriverRouteRegistryLegacy field22 = DriverRouteRegistryLegacy.field3;
   private DriverOverlayRegistryLegacy field23 = null;
   private volatile boolean field24;
   private DriverOverlayRegistryLegacy field25 = null;
   private JsonObject field26 = null;
   private boolean field27 = false;
   private boolean field28 = true;
   private boolean field29 = false;
   private boolean field30 = false;
   private long field31 = 0L;
   private boolean field32 = false;
   private double field33;
   private final Queue<Runnable> field34 = new LinkedList<>();
   private long field35;
   private long field36;

   public DriverViewportLegacy(DriverViewLegacy var1) {
      this(var1, new BrowserHandler());
   }

   public DriverViewportLegacy(DriverViewLegacy var1, BrowserHandler var2) {
      if (var1 != null) {
         field8 = this;
      }

      this.field12 = var1;
      ClientEventBus.method29().method2(EventClientTick.class, var1x -> this.method16());
      this.width = ThreadModuleDump63.method3().bridge$displayWidth();
      this.height = ThreadModuleDump63.method3().bridge$displayHeight();
      this.field33 = Math.max(1.0F, LcuiScreen.method17() / LcuiScreen.field6);
      this.field13 = (MigrationContextLegacy)((MigrationContextLegacy.Data2)((MigrationContextLegacy.Data2)MigrationContextLegacy.method9(this)
               .method1(field11.method3(this.width, this.height, this.field33, var2))
               .IHCORIOHOHHOIORHCCOOIIIHOCROOI(this.width, this.height))
            .HRHCHRICROCCHOHOROROIRIICHCRHH(0, 0))
         .method8()
         .CCRHHHIHHRIOCRIOHHHRRRRCIHRICI();
      if (var1 != null) {
         field11.method5().subscribe(this.field14);
         field11.method5().subscribe(var1.method14().method14());

         for (Entry var4 : this.field14.method19().entrySet()) {
            String var5 = (String)var4.getKey();
            Class var6 = (Class)((Files6_2)var4.getValue()).field1;
            FunctionBusImplLegacy var7 = new FunctionBusImplLegacy();
            var7.subscribe(var6);
            field11.method5().registerChild(var5, var7);
         }
      }

      this.field13.method13().loadURL(MixinSupport.isEnabled() ? "file:///none" : field7);
      ThreadModuleDump63.method4().method41().method7().method29().CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         if (ThreadModuleDump63.method4().method40().method85().method19()) {
            var0 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getFrameRateLimit();
         }

         short var1x = (short)Math.max(67, Math.min(var0.shortValue(), 240));
         WebEngine var2x = method52();
         if (var2x != null) {
            var2x.setFPS(var1x);
         }
      });
      if (var1 != null) {
         if (field4) {
            Slayer.method4(
               "WebOSR",
               "Browser Create [w:%d, h:%d, lh:%d, sd:%f, sf:%f]",
               this.width,
               this.height,
               ThreadModuleDump63.method3().bridge$logicalHeight(),
               LcuiScreen.method19(),
               this.field33
            );
            if (!field7.startsWith("file:")) {
               new DriverElementLegacy(field11.method2().fileSystem).start();
            }
         }

         ClientEventBus.method29().method2(ScreenChangeEvent.class, var1x -> {
            Bridge5Extension6 var2x = var1x.method1();
            if (var2x != null) {
               if (!var1x.isCancelled()) {
                  if (var1x.method1() instanceof Bridge5Extension610) {
                     if (ThreadModuleDump63.method8() != null) {
                        this.method18(DriverRouteRegistryLegacy.field3, true);
                        this.method22(false);
                        var1x.setCancelled(true);
                        return;
                     }

                     this.field27 = true;
                     if (this.method8()) {
                        this.method10(true, var2x);
                        return;
                     }

                     if (!this.field15.get()) {
                        Client.method109().method23();
                        return;
                     }
                  } else if (this.field27 && !(var1x.method1() instanceof Bridge5Extension62)) {
                     this.field27 = false;
                  }

                  for (DriverRouteRegistryLegacy var4x : DriverRouteRegistryLegacy.method3()) {
                     if (var4x.method24() != null && var4x.method24().isAssignableFrom(var2x.getClass())) {
                        this.method16(var4x);
                        var1x.setCancelled(true);
                        return;
                     }
                  }

                  for (DriverOverlayRegistryLegacy var8x : DriverOverlayRegistryLegacy.method3()) {
                     if (var8x.method15() != null && var8x.method15().isAssignableFrom(var2x.getClass())) {
                        this.method19(var8x);
                        var1x.setCancelled(true);
                        return;
                     }
                  }

                  for (DriverRouteRegistryLegacy var9 : DriverRouteRegistryLegacy.method3()) {
                     if (var9.method25() != null && var9.method25().isAssignableFrom(var2x.getClass())) {
                        this.method16(var9);
                        return;
                     }
                  }

                  Bridge7_8 var7x = null;
                  if (var1x.method1() instanceof Bridge5Extension62) {
                     var7x = ((Bridge5Extension62)var1x.method1()).method2();
                  }

                  if (!(var7x instanceof DualMarkerScreenLegacy) && this.field20 != DriverRouteRegistryLegacy.field10) {
                     this.method18(DriverRouteRegistryLegacy.field3, true);
                     this.method22(false);
                  }
               }
            }
         });
         Bridge5Extension6 var8 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (var8 instanceof Bridge5Extension610) {
            this.field27 = true;
         }
      }
   }

   @Override
   public void tick() {
      Rewind var1 = ThreadModuleDump63.method4().method40().method85();
      if (!var1.method17(RewindHandlers::method64)) {
         if (ThreadModuleDump63.method4().method37() && this.field15.get()) {
            if (!this.field16.get() && this.field13.method13() != null) {
               this.field13.method13().evalNoResult("window.lunarReady = true;");
               this.field16.set(true);
            }

            this.method12();
            this.method1();
            this.method2();

            for (Entry var3 : field17.entrySet()) {
               this.method24(this.field13.method13(), (String)var3.getKey(), (DriverGuiExtensionLegacy)var3.getValue());
            }

            if (shutdown) {
               ThreadModuleDump63.method3().bridge$shutdownMinecraftApplet();
            }
         }
      }
   }

   private void method1() {
      if (this.field24 && this.field25 != null) {
         DriverOverlayRegistryLegacy var1 = this.field25;
         JsonObject var2 = this.field26;
         this.field25 = null;
         this.field26 = null;
         this.method20(var1, var2);
      }
   }

   private void method2() {
      if (this.field13 != null && this.field13.method13() != null) {
         if (!field18.isEmpty()) {
            ArrayList var1 = new ArrayList(field18.size());

            for (Entry var3 : field18.entrySet()) {
               if (field18.remove(var3.getKey(), var3.getValue())) {
                  var1.add((JsonObject)var3.getValue());
               }
            }

            if (!var1.isEmpty()) {
               JsonArray var4 = new JsonArray(var1.size());
               var1.forEach(var4::add);
               this.method23(this.field13.method13(), "option:update", var4);
            }
         }
      }
   }

   public static void method3(OptionCategory var0, String var1, JsonElement var2) {
      JsonObject var3 = new JsonObject();
      var3.addProperty("scope", var0.name());
      var3.add("option", var2);
      if (var1 != null) {
         var3.addProperty("id", var1);
      }

      String var4 = var2.getAsJsonObject().get("id").getAsString();
      String var5 = var0.name() + ":" + var4 + ":" + (var1 == null ? "" : var1);
      field18.put(var5, var3);
   }

   public void method4(String var1) {
      if (field17.containsKey(var1)) {
         if (this.field15.get()) {
            this.method24(this.field13.method13(), var1, field17.get(var1));
         }
      }
   }

   @Override
   public void method15(MixinHelper_4 var1, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var2) {
      this.method6();
   }

   protected void method6() {
      double var1 = Math.max(1.0F, LcuiScreen.method17() / LcuiScreen.field6);
      if (this.field33 != var1) {
         if (field4) {
            Slayer.method4(
               "WebOSR",
               "Browser scale [w:%d, h:%d, lh:%d, sd:%f, sf:%f]",
               this.width,
               this.height,
               ThreadModuleDump63.method3().bridge$logicalHeight(),
               LcuiScreen.method19(),
               var1
            );
         }

         this.field13.method13().setDeviceScaleFactor(var1);
         this.field33 = var1;
      }
   }

   public void method7(@NotNull DriverRouteRegistryLegacy var1, DriverContextLegacy var2, boolean var3) {
      if (method52() != null) {
         if (!ThreadModuleDump63.method4().method40().method85().method19() || var1.method20()) {
            if (var1 == DriverRouteRegistryLegacy.field4 && this.method8()) {
               this.method9(var3);
            } else if (var3 || var1 != this.field20 || var1 == DriverRouteRegistryLegacy.field5) {
               if ((!this.field16.get() || !this.field15.get()) && var1 == DriverRouteRegistryLegacy.field4) {
                  this.field27 = true;
               }

               if (field4) {
                  Slayer.method4("WebOSR", "Routing to %s", var1.getPath());
               }

               if (!var1.method1()) {
                  setCursor(Cursor.PASS_THROUGH);
               }

               this.field13.method6();
               this.field20 = var1;
               this.field21 = var2;
               if (var1.method16() != null) {
                  var1.method16().run();
               }

               if (var1.method22() != null) {
                  var1.method22().method7();
               }

               try {
                  JsonObject var4 = new JsonObject();
                  var4.addProperty("route", var1.getPath());
                  var4.add("state", var2.method1());
                  this.method23(this.field13.method13(), "route", var4);
               } catch (Exception var6) {
                  throw new RuntimeException(var6);
               }

               if (this.field12 != null
                  && (ThreadModuleDump63.method8() == null || var1.method1())
                  && !(
                     ThreadModuleDump63.method31(ThreadModuleDump63.method3().bridge$getCurrentScreen()) instanceof DualMarkerScreenLegacy var5
                        && var5.method19() == var1
                  )
                  && var1 != DriverRouteRegistryLegacy.field5
                  && var1.method25() == null) {
                  if (!var3 && this.field32 && ThreadModuleDump63.method3().bridge$getCurrentScreen() != null) {
                     method50().method75().add(() -> {
                        if (this.field20.method1()) {
                           this.field12.method1(var1, var2);
                        }
                     });
                  } else {
                     this.field12.method1(var1, var2);
                  }
               }

               this.field32 = true;
            }
         }
      }
   }

   private boolean method8() {
      return ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method41() != null
         && ThreadModuleDump63.method4().method41().method6().method65().get() == GeneralSettings.Type3.VANILLA;
   }

   private void method9(boolean var1) {
      this.method10(var1, null);
   }

   private void method10(boolean var1, Bridge5Extension6 var2) {
      this.field27 = true;
      if (this.field16.get() && this.field15.get()) {
         Bridge5Extension6 var3 = var2 != null ? var2 : ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (var3 instanceof Bridge5Extension610) {
            this.method7(DriverRouteRegistryLegacy.field5, new VanillaHomeContextLegacy(true), var1);
            this.field27 = false;
         } else if (var2 == null && this.method11(var3)) {
            ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method19());
         }
      }
   }

   private boolean method11(Bridge5Extension6 var1) {
      return var1 != null && ThreadModuleDump63.method8() == null && this.method8()
         ? var1 instanceof Bridge5Extension62 var2 && var2.method2() instanceof DualMarkerScreenLegacy
         : false;
   }

   public void method12() {
      if ((this.field27 || this.method13()) && this.field15.get()) {
         if (this.method8()) {
            this.method9(true);
         } else if (this.field22 == DriverRouteRegistryLegacy.field4) {
            this.field27 = false;
         } else {
            Client.method109().method23();
         }
      }
   }

   private boolean method13() {
      return this.method8() && this.field20 != DriverRouteRegistryLegacy.field5 && this.field22 != DriverRouteRegistryLegacy.field5
         ? ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension610
         : false;
   }

   public void method14(String var1) {
      this.method15(var1, "");
   }

   public void method15(String var1, Object var2) {
      try {
         this.method23(this.field13.method13(), var1, var2);
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public void method16(@NotNull DriverRouteRegistryLegacy var1) {
      this.method18(var1, false);
   }

   public void method17(@NotNull DriverRouteRegistryLegacy var1, DriverContextLegacy var2) {
      this.method7(var1, var2, false);
   }

   public void method18(@NotNull DriverRouteRegistryLegacy var1, boolean var2) {
      DriverContextLegacy var3 = var1.method23() != null ? var1.method23().get() : new DriverContextLegacy();
      this.method7(var1, var3, var2);
   }

   public void method19(@NotNull DriverOverlayRegistryLegacy var1) {
      this.method20(var1, new JsonObject());
   }

   public void method20(@NotNull DriverOverlayRegistryLegacy var1, JsonObject var2) {
      if (this.field23 != var1) {
         if (!this.field24) {
            if (field4) {
               Slayer.method4("WebOSR", "Queueing overlay until the driver attaches: %s", var1.getId());
            }

            this.field25 = var1;
            this.field26 = var2;
         } else {
            if (field4) {
               Slayer.method4("WebOSR", "Show overlay: %s", var1.getId());
            }

            if (this.field23 != null) {
               this.field23.setActive(false);
               if (this.field23.method17() != null) {
                  this.field23.method17().run();
               }
            }

            if (!this.field22.method1()) {
               this.field13.method6();
            }

            ThreadModuleDump63.method3().bridge$getGameSettings().bridge$unpressAllKeys();
            if (this.field12 != null) {
               this.field12.method14().method1(null);
            }

            this.field23 = var1;
            this.field23.setActive(true);
            if (this.field23.method16() != null) {
               this.field23.method16().run();
            }

            JsonObject var3 = new JsonObject();
            var3.add("params", var2);
            var3.addProperty("state", true);
            var3.addProperty("overlay", this.field23.getId());
            this.method23(this.field13.method13(), "overlay", var3);
            if (ThreadModuleDump63.method11() == null && this.field12 != null) {
               this.field12.method1(this.field20, this.field21);
            }
         }
      }
   }

   public void method21() {
      this.method22(true);
   }

   public void method22(boolean var1) {
      this.field25 = null;
      this.field26 = null;
      if (this.field23 != null) {
         if (this.field23.method1() && (!this.field23.method2() || !this.field22.method1())) {
            this.field13.method6();
            if (var1 && DualMarkerScreenLegacy.method14() && this.field22 != DriverRouteRegistryLegacy.field5) {
               ThreadModuleDump63.method3().bridge$displayScreen(null);
            }
         }

         if (!this.field22.method1() && this.field23.method1()) {
            setCursor(Cursor.PASS_THROUGH);
         }

         if (field4) {
            Slayer.method4("WebOSR", "Hiding active overlay (" + this.field23.getId() + ")");
         }

         this.field23.setActive(false);
         if (this.field23.method17() != null) {
            this.field23.method17().run();
         }

         JsonObject var2 = new JsonObject();
         var2.addProperty("state", false);
         var2.addProperty("overlay", this.field23.getId());
         this.method23(this.field13.method13(), "overlay", var2);
         this.field23 = null;
         if (this.field12 != null) {
            this.field12.method14().method1(null);
         }
      }
   }

   public void method23(Browser var1, String var2, Object var3) {
      if (var1 != null) {
         try {
            var1.evalNoResult("window.lunarInternalAccept('" + var2 + "', " + var3.toString() + ");");
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }
      }
   }

   private void method24(Browser var1, String var2, DriverGuiExtensionLegacy var3) {
      JsonElement var4 = var3.method128();
      if (var4 != null && (var3.method6() || !Objects.equals(var4, this.field19.get(var2)))) {
         String var5 = ThreadModuleDump48.field22.toJson(var4);
         this.method23(var1, "data:" + var2, var5);
         this.field19.put(var2, var4);
         if ("notification".equals(var2)) {
            this.method25(var4);
         }
      }
   }

   private void method25(JsonElement var1) {
      long var2 = var1.isJsonObject() && var1.getAsJsonObject().has("durationMs") ? var1.getAsJsonObject().get("durationMs").getAsLong() : 0L;
      this.field31 = Math.max(this.field31, System.currentTimeMillis() + var2 + 2000L);
   }

   public boolean method26() {
      return this.field22 == DriverRouteRegistryLegacy.field3
         && this.field20 == DriverRouteRegistryLegacy.field3
         && this.field23 == null
         && !this.field30
         && System.currentTimeMillis() >= this.field31;
   }

   public void method27(DriverRouteRegistryLegacy var1) {
      this.field24 = true;
      if (var1 != DriverRouteRegistryLegacy.field3 || !ThreadModuleDump63.method4().method40().method85().method19()) {
         if (var1 != DriverRouteRegistryLegacy.field11) {
            if (this.field22.method1() && !var1.method1()) {
               MenuBlur var2 = Client.method109().method40().method43();
               if (var2.isEnabled() && var2.getLunarScreenData().method3().get()) {
                  var2.stopBlurAnimation();
               }
            }

            if (var1 != this.field22) {
               if (this.field22.method17() != null) {
                  this.field22.method17().run();
               }

               if (this.field22.method22() != null) {
                  this.field22.method22().onClose();
               }
            }

            this.field22 = var1;
            this.field20 = var1;
            if (this.field22.method22() != null) {
               this.field22.method22().method2(this.framebufferWidth, this.framebufferHeight);
            }
         }
      }
   }

   @Override
   public void method4(int var1, int var2, int var3, int var4) {
      LcuiScreen.method150(new ThreadModuleDump71(ThreadModuleDump63.method3()));
      if (this.framebufferWidth != var3 || this.framebufferHeight != var4) {
         this.method7(var3, var4);
         this.method6(var3, var4);
      }
   }

   public boolean hasFocus() {
      return true;
   }

   public void method29() {
      if (field4) {
         Slayer.method4("WebOSR", "Resized Browser [w:%s, h:%s, sf:%f]", this.width, this.height, this.field33);
      }

      this.field13.method14(this.width, this.height);
      this.field13.method2(0, 0);
      this.field13.method13().triggerResize(this.framebufferWidth, this.framebufferHeight);
   }

   @Override
   public void method7(int var1, int var2) {
      super.method7(var1, var2);
   }

   @Override
   public void method6(int var1, int var2) {
      super.method6(var1, var2);
      this.method29();
   }

   @Override
   public void method12(int var1, int var2, int var3, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var4) {
      super.method12(var1, var2, var3, var4);
      if (this.method40() && this.field28) {
         for (DriverGuiExtensionLegacy var6 : field17.values()) {
            var6.method3(KeyCode.fromMouseButton(var1), var1, var2, var3, var4);
         }
      }
   }

   @Override
   public void method14(List<Path> var1) {
      super.method14(var1);
      if (this.method40() && this.field28) {
         for (DriverGuiExtensionLegacy var3 : field17.values()) {
            var3.method5(var1);
         }
      }
   }

   @Override
   public void method13(double var1, double var3) {
      super.method13(var1, var3);
      if (this.method40() && this.field28) {
         for (DriverGuiExtensionLegacy var6 : field17.values()) {
            var6.method4(var1, var3);
         }
      }
   }

   @Override
   public void method9(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (var4 == 0) {
         if (isDebug()) {
            switch (var1) {
               case KEY_F5:
                  this.field13.method13().reload();
                  break;
               case KEY_F1:
                  this.field13.method13().setFocus(!this.field13.method13().hasFocus());
            }
         }

         if (var1 == KeyCode.KEY_ESCAPE && SettingsBridgeLegacy.field1 == null && this.field20 != DriverRouteRegistryLegacy.field23) {
            if (this.field23 != null) {
               this.method21();
               return;
            }

            if (ThreadModuleDump63.method11() != null && this.method40()) {
               Client.method109().method43().method28(false);
               Client.method109().method43().method27(false);
               if (ThreadModuleDump63.method8() == null) {
                  Client.method109().method23();
                  return;
               }

               if (ThreadModuleDump63.method7() != null) {
                  ThreadModuleDump63.method7().bridge$closeScreen();
               } else {
                  ThreadModuleDump63.method3().bridge$displayScreen(null);
               }

               return;
            }
         }
      } else if (var4 == 1) {
         boolean var6 = (var5 & 1) != 0;
         boolean var7 = (var5 & 4) != 0;
         boolean var8 = (var5 & 2) != 0;
         ModifierKeybindOption var9 = ThreadModuleDump63.method4().method41().method8().method22();
         if (this.method36()
            && !GuiRewindhandlers3.method4()
            && ThreadModuleDump63.method4().method41().method6().method30().get()
            && var9.isKeyDown()
            && !var9.get().method7()
            && !var9.get().method5()
            && !var9.get().method6()) {
            this.method37();
            return;
         }

         if ((!var9.get().method6() || var1 != KeyCode.KEY_LSHIFT)
            && (!var9.get().method5() || var1 != KeyCode.KEY_LMENU)
            && (!var9.get().method7() || var1 != KeyCode.KEY_LCONTROL)) {
            if (ThreadModuleDump63.method4().method41().method6().method30().get()
               && var9.method5(var1, var5)
               && this.field35 != 0L
               && (var6 || var7 || var8)
               && System.nanoTime() - this.field35 <= TimeUnit.MILLISECONDS.toNanos(500L)) {
               this.method37();
               return;
            }
         } else {
            this.field35 = System.nanoTime();
         }
      }

      if (this.method40()) {
         super.method9(var1, var2, var3, var4, var5);

         for (DriverGuiExtensionLegacy var11 : field17.values()) {
            var11.method2(var1, var2, var3, var4, var5);
         }
      }
   }

   private boolean method36() {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var1 == null) {
         return true;
      }

      if (var1 instanceof Bridge5Extension612 || var1 instanceof Bridge5Extension4) {
         return false;
      }

      if (!(var1 instanceof Bridge5Extension62)) {
         return true;
      }

      Bridge7_8 var2 = ((Bridge5Extension62)var1).method2();
      return !(var2 instanceof LcuiScreen) || !this.method38((LcuiScreen)var2);
   }

   private void method37() {
      if (this.field23 == DriverOverlayRegistryLegacy.field2 && System.nanoTime() - this.field36 > TimeUnit.MILLISECONDS.toNanos(250L)) {
         this.method21();
         this.field36 = 0L;
      } else {
         this.method19(DriverOverlayRegistryLegacy.field2);
         this.field36 = System.nanoTime();
      }
   }

   private boolean method38(@NotNull LcuiScreen var1) {
      return var1.method148().stream().filter(var0 -> var0 instanceof EditState).anyMatch(var0 -> ((EditState)var0).isEditing());
   }

   public FunctionBus method39() {
      return field11.method5();
   }

   public boolean method40() {
      return this.field20.method1() && this.field20 != DriverRouteRegistryLegacy.field5 && this.field20.method25() == null
         || this.field23 != null && this.field23.method1();
   }

   public static void method41() {
      if (field4) {
         Slayer.method4("WebOSR", "========= WebOSR Configuration =========");
         Slayer.method4("WebOSR", "Filter: %s", !field5);
         Slayer.method4("WebOSR", "Bundle: %s", field6);
         Slayer.method4("WebOSR", "URL: %s", field7);
         Slayer.method4("WebOSR", "UI dir: %s", Client.method26().toString());
         Slayer.method4("WebOSR", "WebOSR dir: %s", Client.method29().toString());
         Slayer.method4("WebOSR", "========================================");
      }

      WebOsrNativeLegacy.loadWebOSR();
      field11 = new UltralightConfigFactoryLegacy(false, 0, method45());
      ThreadConfig var0 = new ThreadConfig();
      var0.idleTimer = 0.001;
      field9 = WebOSR.createEngine(new LoggerHandler(), (UltralightConfig)((UltralightConfigFactoryLegacy)field11).method1(), var0);
   }

   public static void method42() {
      field10 = true;

      for (Fishing var1 : new HashSet<>(Fishing.field1)) {
         var1.delete();
      }

      method50().method55().method13().close();
      method50().method55().method12(null);
      field9.close();
      field9 = null;
   }

   public static boolean method43() {
      return true;
   }

   public static void setCursor(Cursor var0) {
      WebEngine var1 = method52();
      if (var1 != null) {
         var1.getPlatformInterface().setCursor(var0);
      }
   }

   private static String method44(String var0) {
      if (var0 != null && var0.startsWith("file:") && !var0.startsWith("file:///")) {
         String var1 = var0.substring(5);

         while (var1.startsWith("/")) {
            var1 = var1.substring(1);
         }

         return "file:///" + var1;
      } else {
         return var0;
      }
   }

   private static String method45() {
      if (new File(Client.method29().get(), "resources/icudt67l.dat").exists()) {
         return "resources/";
      } else if (new File(Client.method26().get(), "ul-resources/resources/icudt67l.dat").exists()) {
         return "ul-resources/resources/";
      } else {
         throw new RuntimeException("Did not find Ultralight Resource Directory");
      }
   }

   public void method46(String var1) {
      if (!LunarBuildData.field4 && field4) {
         if (!ThreadModuleDump48.field17.toFile().exists()) {
            ThreadModuleDump48.field17.toFile().mkdirs();
         }

         String var2 = var1;
         if (var2.startsWith("file:///")) {
            var2 = var2.substring("file:///".length());
         }

         if (var2.endsWith(".imgsrc")) {
            var2 = var2.substring(0, var2.length() - ".imgsrc".length());
         }

         String var3 = var2.replaceAll("[^A-Za-z0-9_]", "");
         Path var4 = ThreadModuleDump48.field17.resolve(var3 + ".imgsrc");
         if (!var4.toFile().exists()) {
            String var5 = "IMGSRC-V1\n" + var3;
            byte[] var6 = var5.getBytes(StandardCharsets.UTF_8);
            ByteBuffer var7 = ByteBuffer.allocateDirect(var6.length).order(ByteOrder.nativeOrder());
            var7.put(var6);
            Slayer.method3("Created ImageSource for " + var3 + "\n" + var5);

            try {
               Files.writeString(var4, var5);
            } catch (IOException var9) {
               throw new RuntimeException(var9);
            }
         }
      }
   }

   @Generated
   public static Map<Browser, Boolean> method47() {
      return field3;
   }

   @Generated
   public static boolean isDebug() {
      return field4;
   }

   @Generated
   public static boolean method48() {
      return field5;
   }

   @Generated
   public static String method49() {
      return field6;
   }

   @Generated
   public static String getUrl() {
      return field7;
   }

   @Generated
   public static DriverViewportLegacy method50() {
      return field8;
   }

   @Generated
   public static void setShutdown(boolean var0) {
      shutdown = var0;
   }

   @Generated
   public static WebEngine method52() {
      return field9;
   }

   @Generated
   public static boolean method53() {
      return field10;
   }

   @Generated
   public static DriverViewContextLegacy<?> method54() {
      return field11;
   }

   @Generated
   public MigrationContextLegacy method55() {
      return this.field13;
   }

   @Generated
   public com.moonsworth.lunar.client.driver.DriverBridgeLegacy method56() {
      return this.field14;
   }

   @Generated
   public AtomicBoolean method57() {
      return this.field15;
   }

   @Generated
   public AtomicBoolean method58() {
      return this.field16;
   }

   @Generated
   public static Map<String, DriverGuiExtensionLegacy> method59() {
      return field17;
   }

   @Generated
   public Map<String, JsonElement> method60() {
      return this.field19;
   }

   @NotNull
   @Generated
   public DriverRouteRegistryLegacy method61() {
      return this.field20;
   }

   @Generated
   public DriverContextLegacy method62() {
      return this.field21;
   }

   @NotNull
   @Generated
   public DriverRouteRegistryLegacy method63() {
      return this.field22;
   }

   @Generated
   public DriverOverlayRegistryLegacy method64() {
      return this.field23;
   }

   @Generated
   public void method65(boolean var1) {
      this.field24 = var1;
   }

   @Generated
   public boolean method66() {
      return this.field27;
   }

   @Generated
   public void method67(boolean var1) {
      this.field27 = var1;
   }

   @Generated
   public void method68(boolean var1) {
      this.field28 = var1;
   }

   @Generated
   public boolean method69() {
      return this.field28;
   }

   @Generated
   public void method70(boolean var1) {
      this.field29 = var1;
   }

   @Generated
   public boolean method71() {
      return this.field29;
   }

   @Generated
   public void method72(boolean var1) {
      this.field30 = var1;
   }

   @Generated
   public boolean method73() {
      return this.field30;
   }

   @Generated
   public double method74() {
      return this.field33;
   }

   @Generated
   public Queue<Runnable> method75() {
      return this.field34;
   }
}
