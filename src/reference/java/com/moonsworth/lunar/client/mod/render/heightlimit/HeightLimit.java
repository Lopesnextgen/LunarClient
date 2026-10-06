package com.moonsworth.lunar.client.mod.render.heightlimit;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.heightlimit.Heightlimit;
import com.moonsworth.lunar.client.framework.feature.heightlimit.Heightlimit2;
import com.moonsworth.lunar.client.framework.feature.heightlimit.HeightlimitType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.event.mixin.gui.LocationChangeEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.ChunkReloadEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.ui.hud.row.Gui2Extension;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox;
import com.moonsworth.lunar.client.ui.hud.row.Hitbox2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;

public class HeightLimit extends AbstractFeature {
   private static final String[] field8 = new String[]{"planks", "log", "end_stone", "glass", "obsidian", "wool", "ladder"};
   private static final int field9 = 100;
   private final IntegerOption field10 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "renderRange"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .method7(1, 8))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "heightLimitOverlay"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("border")
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "autoAlign"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field16 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final EnumOption<Gui2Extension> field17 = (EnumOption<Gui2Extension>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "alignment", Gui2Extension.LEFT
      )
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "autoColorDistance"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field19 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "titleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field20 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field21 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "numberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field22 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption field23 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final ColorOption field24 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "red"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption field25 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "gold"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field26 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "yellow"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private boolean field27;
   private JsonObject field28;
   private JsonObject field29;
   private int field30 = -1;
   @Nullable
   private HeightLimitProfile field31;
   private String mapName;
   @Nullable
   private Heightlimit field32;
   private final Heightlimit2 field33 = new Heightlimit2();
   public final HeightLimitProfile field34 = HeightLimitProfile.method1(this, true, HeightlimitType.BEDWARS);
   public final HeightLimitProfile field35 = HeightLimitProfile.method1(this, true, HeightlimitType.BRIDGE);
   public final HeightLimitProfile field36 = HeightLimitProfile.method1(this, true, HeightlimitType.VANILLA);
   public final HeightLimitProfile field37 = HeightLimitProfile.method1(this, true, HeightlimitType.SERVER);

   public HeightLimit() {
      super(false);
      this.method59(Framework.field1, this.method17());
      this.handle(LocationChangeEvent.class, this::method9);
      this.handle(HudRenderLegacyEvent.class, this::method6);
      this.handle(EventBlockModified.class, this::method7);
      this.handle(EventChunkLifecycle.EventChunkLoaded.class, this::method8);
      this.handle(ChunkReloadEvent.class, var1 -> this.field33.markDirty());
   }

   @Override
   public String getId() {
      return "HEIGHT_LIMIT";
   }

   @Override
   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field34, this.field35, this.field36, this.field37);
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(Calculator2Handler.field5, Calculator2Handler.field3)
         .method2("bedwars", "hypixel bedwars", "height overlay", "build limit", "barrier")
         .method3("Pinkulu")
         .method11(this);
   }

   @Override
   public void method3(boolean var1) {
      if (var1 && !this.field27) {
         this.method13();
         this.field27 = true;
      }

      if (var1) {
         this.method14();
      } else {
         this.field33.clear();
      }
   }

   private void method13() {
      ThreadModuleDump37.method4(() -> {
         JsonObject var1 = this.method5(ThreadModuleDump48.field12.resolve("hypixel/bedwars.json"));
         JsonObject var2 = this.method5(ThreadModuleDump48.field12.resolve("hypixel/duels.json"));
         ThreadModuleDump37.method11(() -> {
            this.field28 = var1;
            this.field29 = var2;
            this.method14();
            this.field33.markDirty();
         });
      });
   }

   @Nullable
   private JsonObject method5(Path var1) {
      if (!var1.toFile().exists()) {
         return null;
      }

      try {
         String var2 = Files.readString(var1);
         JsonObject var3 = (JsonObject)ThreadModuleDump48.field22.fromJson(var2, JsonObject.class);
         return var3.getAsJsonObject("build_heights");
      } catch (Exception var4) {
         Inventorymod2.method5(var4, "Load Height Limit Data");
         return null;
      }
   }

   private void method6(HudRenderLegacyEvent var1) {
      this.field33.method2(var1.method3(), this);
   }

   private void method7(EventBlockModified var1) {
      HeightLimit.Data var2 = this.method15();
      if (var2 != null) {
         int var3 = var1.method1().bridge$getY();
         int var4 = var2.limit() - 1;
         if (var3 >= var4 - var2.method1().method14() - 1 && var3 <= var4 + 1) {
            this.field33.markDirty();
         }
      }
   }

   private void method8(EventChunkLifecycle.EventChunkLoaded var1) {
      this.field33.method1(var1.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getX(), var1.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getZ(), this.field10.get() * 8);
   }

   private void method9(LocationChangeEvent var1) {
      this.method11(var1.method2());
      this.field33.markDirty();
   }

   public void method14() {
      this.method11(GuiRewindhandlersHandler23.field7.method7());
   }

   private void method11(@Nullable Rewindhandlers2 var1) {
      this.field30 = this.method12(var1 != null && !var1.method2() ? var1 : null);
   }

   private int method12(@Nullable Rewindhandlers2 var1) {
      this.mapName = null;
      this.field31 = null;
      if (var1 != null && Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
         String var2 = var1.field4 == null ? null : var1.field4.toLowerCase(Locale.ROOT).replace(" ", "_");
         if (this.field34.method13() && "BEDWARS".equalsIgnoreCase(var1.field2) && var2 != null && this.field28 != null && this.field28.has(var2)) {
            this.mapName = this.method13(var2);
            this.field31 = this.field34;
            return this.field28.get(var2).getAsInt();
         }

         if (this.field35.method13() && "DUELS".equalsIgnoreCase(var1.field2) && var1.field3 != null && var1.field3.toUpperCase(Locale.ROOT).contains("BRIDGE")
            )
          {
            if (var2 != null) {
               this.mapName = this.method13(var2);
            }

            this.field31 = this.field35;
            return var2 != null && this.field29 != null && this.field29.has(var2) ? this.field29.get(var2).getAsInt() : 100;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   private String method13(String var1) {
      return WordUtils.capitalizeFully(var1.replace("_", " "));
   }

   public void method14(@Nullable Heightlimit var1) {
      if (!Objects.equals(this.field32, var1)) {
         this.field32 = var1;
         this.field33.markDirty();
      }
   }

   @Nullable
   public HeightLimit.Data method15() {
      if (this.field37.method13() && this.field32 != null) {
         return new HeightLimit.Data(this.field37, this.field32.limit());
      }

      if (this.field30 > 0 && this.field31 != null) {
         return new HeightLimit.Data(this.field31, this.field30);
      }

      if (this.field36.method13()) {
         Itemcounter6Extension var1 = ThreadModuleDump63.method3().bridge$getWorld();
         if (var1 != null) {
            int var2 = var1.bridge$getMaxBuildHeight();
            if (ThreadModuleDump63.MC_VERSION >= 26) {
               var2++;
            }

            if (ThreadModuleDump63.MC_VERSION <= 0 && ThreadModuleDump63.method3().bridge$getIntegratedServer() != null) {
               var2--;
            }

            if (var2 > 0) {
               return new HeightLimit.Data(this.field36, var2);
            }
         }
      }

      return null;
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10});
      var1.OHOOORICRHIIIIRHCICICOCHROICRC(
         "hudDisplayOptions",
         var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            this.field11,
            var1xx -> {
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12});
               var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field13,
                  var1xxx -> var1xxx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.field14, var1xxxx -> var1xxxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16})
                  )
               );
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15});
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17}).method3(this.field15::get);
            }
         )
      );
      ((SettingsSectionImpl)var1.OHOOORICRHIIIIRHCICICOCHROICRC(
            "extraRenderOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field18})
         ))
         .method2(() -> !this.field11.get());
      ((SettingsSectionImpl)var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.COLOR, var1x -> {
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field19, this.field20, this.field21});
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field22}).method3(() -> !this.field13.get());
         var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field23}).method3(() -> !this.field14.get());
      })).method2(() -> !this.field11.get());
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.field33.markDirty());
   }

   private MixinCore9Extension method17() {
      return new HudRowElement(0.0F, 0.0F, HudAnchor.TOP_RIGHT) {
         @Override
         public void method2(MixinHelper_4 var1, float var2, float var3, boolean var4, List<Hitbox2> var5) {
            this.method2(
               var1,
               var2,
               var3,
               HeightLimit.this.field15.get(),
               HeightLimit.this.field17.get(),
               HeightLimit.this.field13.get(),
               HeightLimit.this.field22,
               HeightLimit.this.field14.get(),
               HeightLimit.this.field16.get(),
               HeightLimit.this.field23
            );
         }

         @Nullable
         @Override
         protected List<Hitbox2> method5(boolean var1) {
            Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
            if (var2 == null) {
               return null;
            }

            HeightLimit.Data var3 = HeightLimit.this.method15();
            HeightLimitProfile var4 = var3 != null ? var3.method1() : HeightLimit.this.field34;
            String var5 = var3 != null && var3.method1() == HeightLimit.this.field37 && HeightLimit.this.field32 != null
               ? HeightLimit.this.field32.displayName()
               : HeightLimit.this.mapName;
            int var6 = var3 == null ? -1 : var3.limit();
            int var7 = (int)var2.bridge$getBoundingBox().bridge$getMinY();
            int var8 = var6 - var7;
            if (var8 < 0) {
               var8 = 0;
            }

            if (var1 && var5 == null) {
               var5 = "Lighthouse";
            }

            if (var1 && var6 <= 0) {
               var6 = 110;
               var8 = 27;
               var7 = var6 - var8;
            }

            boolean var9 = var4.method30() != HeightlimitType.VANILLA && var4.method26().get() && var5 != null;
            boolean var10 = var4.method27().get() && var6 > 0;
            boolean var11 = var4.method29().get() && var6 > 0;
            if (!var9 && !var10 && !var11) {
               return null;
            }

            ColorOption var12 = HeightLimit.this.field21;
            if (HeightLimit.this.field18.get()) {
               if (var8 <= 5) {
                  var12 = HeightLimit.this.field24;
               } else if (var8 <= 10) {
                  var12 = HeightLimit.this.field25;
               } else if (var8 <= 15) {
                  var12 = HeightLimit.this.field26;
               }
            }

            byte var13 = 0;
            byte var14 = 0;
            switch (Hitbox.method3(HeightLimit.this.field15.get(), this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(), HeightLimit.this.field17.get())) {
               case LEFT:
                  var14 = 4;
                  break;
               case RIGHT:
                  var13 = 4;
            }

            ArrayList var15 = new ArrayList();
            if (var4.method24().get() == HeightLimit.Type3.CLASSIC) {
               if (var4.method25().get()) {
                  var15.add(Hitbox.method4(var13, Hitbox.method5("§lHeight Limit", HeightLimit.this.field19, HeightLimit.this.field12.get())));
               }

               if (var9) {
                  var15.add(
                     Hitbox.method4(
                        var14,
                        Hitbox.method5("Map: ", HeightLimit.this.field20, HeightLimit.this.field12.get()),
                        Hitbox.method5(var5, HeightLimit.this.field21, HeightLimit.this.field12.get())
                     )
                  );
               }

               if (var10) {
                  ArrayList var16 = new ArrayList();
                  var16.add(Hitbox.method5("Height Limit: ", HeightLimit.this.field20, HeightLimit.this.field12.get()));
                  var16.addAll(HeightLimit.this.method18(var4, var7, var6));
                  var15.add(Hitbox.method4(var14, var16.toArray(new Hitbox2[0])));
               }

               if (var11) {
                  var15.add(
                     Hitbox.method4(
                        var14,
                        Hitbox.method5("Distance: ", HeightLimit.this.field20, HeightLimit.this.field12.get()),
                        Hitbox.method5(var8 + "", var12, HeightLimit.this.field12.get())
                     )
                  );
               }
            } else {
               if (var9) {
                  var15.add(Hitbox.method4(var14, Hitbox.method5("§l" + var5, HeightLimit.this.field19, HeightLimit.this.field12.get())));
               }

               if (var10 && var11) {
                  ArrayList var18 = new ArrayList();
                  var18.add(Hitbox.method5("Y: ", HeightLimit.this.field20, HeightLimit.this.field12.get()));
                  var18.addAll(HeightLimit.this.method18(var4, var7, var6));
                  var18.add(Hitbox.method5(" §7(§r" + var8 + "§7)", var12, HeightLimit.this.field12.get()));
                  var15.add(Hitbox.method4(var13, var18.toArray(new Hitbox2[0])));
               } else if (var10) {
                  ArrayList var17 = new ArrayList();
                  var17.add(Hitbox.method5("Y: ", HeightLimit.this.field20, HeightLimit.this.field12.get()));
                  var17.addAll(HeightLimit.this.method18(var4, var7, var6));
                  var15.add(Hitbox.method4(var13, var17.toArray(new Hitbox2[0])));
               } else {
                  var15.add(
                     Hitbox.method4(
                        var13,
                        Hitbox.method5("Dist: ", HeightLimit.this.field20, HeightLimit.this.field12.get()),
                        Hitbox.method5(var8 + "", var12, HeightLimit.this.field12.get())
                     )
                  );
               }
            }

            return var15;
         }

         @Override
         public boolean method4(boolean var1) {
            if (!HeightLimit.this.field11.get()) {
               this.method58(0.0F, 0.0F);
               return false;
            }

            if (!var1) {
               HeightLimit.Data var2 = HeightLimit.this.method15();
               if (var2 == null || !var2.method1().method23().get()) {
                  this.method58(0.0F, 0.0F);
                  return false;
               }
            }

            return super.method4(var1);
         }
      };
   }

   private List<Hitbox2> method18(HeightLimitProfile var1, int var2, int var3) {
      boolean var4 = this.field12.get();
      ArrayList var5 = new ArrayList();
      if (var1.method28().get()) {
         var5.add(Hitbox.method5(var2 + "", this.field21, var4));
         var5.add(Hitbox.method5(" / ", this.field20, var4));
      }

      var5.add(Hitbox.method5(var3 + "", this.field21, var4));
      return var5;
   }

   @Generated
   public IntegerOption method19() {
      return this.field10;
   }

   @Generated
   public ToggleOption method21() {
      return this.field11;
   }

   @Generated
   public ToggleOption method22() {
      return this.field12;
   }

   @Generated
   public ToggleOption method23() {
      return this.field13;
   }

   @Generated
   public ToggleOption method24() {
      return this.field14;
   }

   @Generated
   public ToggleOption method25() {
      return this.field15;
   }

   @Generated
   public FloatOption method26() {
      return this.field16;
   }

   @Generated
   public EnumOption<Gui2Extension> method27() {
      return this.field17;
   }

   @Generated
   public ToggleOption method28() {
      return this.field18;
   }

   @Generated
   public ColorOption method29() {
      return this.field19;
   }

   @Generated
   public ColorOption method30() {
      return this.field20;
   }

   @Generated
   public ColorOption method34() {
      return this.field21;
   }

   @Generated
   public ColorOption method35() {
      return this.field22;
   }

   @Generated
   public ColorOption method36() {
      return this.field23;
   }

   @Generated
   public ColorOption method37() {
      return this.field24;
   }

   @Generated
   public ColorOption method38() {
      return this.field25;
   }

   @Generated
   public ColorOption method39() {
      return this.field26;
   }

   @Generated
   public boolean method40() {
      return this.field27;
   }

   @Generated
   public JsonObject method41() {
      return this.field28;
   }

   @Generated
   public JsonObject method42() {
      return this.field29;
   }

   @Generated
   public int method43() {
      return this.field30;
   }

   @Nullable
   @Generated
   public HeightLimitProfile method44() {
      return this.field31;
   }

   @Generated
   public String getMapName() {
      return this.mapName;
   }

   @Nullable
   @Generated
   public Heightlimit method45() {
      return this.field32;
   }

   @Generated
   public Heightlimit2 method46() {
      return this.field33;
   }

   @Generated
   public HeightLimitProfile method47() {
      return this.field34;
   }

   @Generated
   public HeightLimitProfile method48() {
      return this.field35;
   }

   @Generated
   public HeightLimitProfile method49() {
      return this.field36;
   }

   @Generated
   public HeightLimitProfile method50() {
      return this.field37;
   }

   public class Data {
      private final HeightLimitProfile field1;
      private final int field2;

      public Data(HeightLimitProfile var1, int var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public HeightLimitProfile method1() {
         return this.field1;
      }

      public int limit() {
         return this.field2;
      }
   }

   public enum Type {
      ALL,
      BRIDGE,
      BEDWARS;

      public boolean matches(Bridge3_23 var1) {
         if (this == ALL) {
            return true;
         }

         String var2 = var1.bridge$getRegistryName();
         if (var2 == null) {
            return true;
         }

         if (this != BRIDGE) {
            for (String var6 : HeightLimit.field8) {
               if (var2.contains(var6)) {
                  return true;
               }
            }

            return false;
         } else {
            return var2.contains("clay") || var2.contains("terracotta");
         }
      }
   }

   public enum Type2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      DARKEN("darken"),
      BARRIER("barrier"),
      BOTH("both");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      @Generated
      Type2(String var3) {
         this.id = var3;
      }
   }

   public enum Type3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      CLASSIC,
      COMPACT;

      @Override
      public String id() {
         return this.name();
      }
   }
}
