package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.net.InternetDomainName;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent.Data4;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.ThreadModuleDump85;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class Chat5 {
   private static final ExecutorService field1 = Executors.newFixedThreadPool(
      1, new ThreadFactoryBuilder().setNameFormat("Hover Image Preview Downloader #%d").setDaemon(true).build()
   );
   private final LoadingCache<String, Future<BufferedImage>> field2 = CacheBuilder.newBuilder()
      .maximumSize(10L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .softValues()
      .build(new CacheLoader<String, Future<BufferedImage>>() {
         public Future<BufferedImage> method1(String var1) {
            return Chat5.this.method4(var1);
         }
      });
   private static final ResourceLocationBridge field3 = ResourceLocationBridge.create("lunar", "hover_image");
   private static final List<String> field4 = ImmutableList.of(
      "lunr.pics",
      "imgur.com",
      "i.imgur.com",
      "discordapp.com",
      "discordapp.net",
      "media.discordapp.net",
      "hypixel.net",
      "prnt.sc",
      "ibb.co",
      "wixmp.com",
      "fbcdn.net",
      "cdninstagram.com",
      new String[]{"redd.it", "gyazo.com", "twimg.com", "mcstats.com", "lunarclientcdn.com", "moonsworth.store"}
   );
   private static final Pattern field5 = Pattern.compile("https://[^\\s\"']+");
   private Bridge8Extension33 field6 = null;
   private boolean field7 = false;
   private String field8;
   private Future<BufferedImage> field9;
   private final HoverAnimation field10;
   private final List<String> field11 = new ArrayList<>();
   private List<String> urls = new ArrayList<>(2);
   private int field12;

   protected Chat5() {
      this.field10 = new HoverAnimation(250L);
   }

   protected void method1(String[] var1) {
      this.field11.clear();
      Arrays.stream(var1).map(var0 -> var0.toLowerCase(Locale.ROOT)).forEach(this.field11::add);
   }

   protected void method2(Data4 var1) {
      if (ThreadModuleDump63.MC_VERSION < 24) {
         this.method3(var1.method2(), var1.method3());
      }
   }

   public void method3(MixinHelper_4 var1, MarkerModel<?> var2) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat var3 = ThreadModuleDump63.method4().method40().method47();
      if ((Boolean)var3.method66().get()) {
         if (this.field7 && this.field9 != null && this.field9.isDone()) {
            Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreenOrRewind();
            if (!(var4 instanceof Bridge5Extension612) && !(var4 instanceof Bridge5Extension4)) {
               this.method9();
            } else {
               try {
                  BufferedImage var5 = this.field9.get();
                  if (var5 == null) {
                     return;
                  }

                  com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 var6 = ThreadModuleDump85.get().method7();
                  RewindHandlers var7 = ThreadModuleDump63.method4().method40().method85().method35();
                  if (var7 != null) {
                     Nameplate2 var8 = ((Nameplate4)var7.method42().get()).method8();
                     ThreadModuleDump71 var9 = LcuiScreen.method151();
                     var6 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(
                        var8.method3(ThreadModuleDump63.method3().bridge$getTimer().method1(), var9.getScaledWidth()),
                        var8.method4(ThreadModuleDump63.method3().bridge$getTimer().method1(), var9.getScaledHeight())
                     );
                  }

                  double var27 = var5.getHeight();
                  double var10 = var5.getWidth();
                  double var12 = (Double)var3.method69().get();
                  double var14 = (Double)var3.method70().get();
                  if ((Boolean)var3.method67().get() && var14 < 100.0) {
                     if (Bridge.method18().method1(KeyCode.KEY_LSHIFT) && !this.field10.method7()) {
                        if (!this.field10.method5()) {
                           this.field10.method20(false);
                           this.field10.start();
                        } else if (this.field10.method6()) {
                           this.field10.stop();
                           this.field10.method20(!this.field10.method21());
                           this.field10.start();
                        }
                     }

                     var14 += (100.0 - var14) * this.field10.method1();
                  }

                  double var16 = var10 / var27;
                  double var18 = var2.method10();
                  double var20 = var2.method11();
                  if (var27 / var20 > var10 / var18) {
                     var27 = ClampUtils.clamp(var27, var12 / 100.0 * var20, var14 / 100.0 * var20);
                     var10 = var27 * var16;
                  } else {
                     var10 = ClampUtils.clamp(var10, var12 / 100.0 * var18, var14 / 100.0 * var18);
                     var27 = var10 / var16;
                  }

                  double var22 = var6.HHHCHORHIHRCOHIOICICICHCRRICCI();
                  double var24 = var6.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - var27;
                  if (var24 < 0.0 || var24 + var27 > var20) {
                     var24 = 0.0;
                  }

                  if (var22 + var10 > var18) {
                     var22 = var18 - var10;
                  }

                  if (this.field6 == null) {
                     this.field6 = Bridge.method8().method22(var5);
                     ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(field3);
                     ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(field3, this.field6);
                  }

                  var1.method38(0.0F, 0.0F, 200.0F);
                  LcuiScreen.method31(var1, field3, (float)var22, (float)var24, (float)var10, (float)var27, -1);
               } catch (Exception var26) {
                  var26.printStackTrace();
               }
            }
         }
      }
   }

   private Future<BufferedImage> method4(String var1) {
      URL var2;
      String var3;
      try {
         var2 = new URL(var1);
         var3 = InternetDomainName.from(var2.getHost()).topPrivateDomain().toString();
      } catch (Exception var5) {
         return Futures.immediateFuture(null);
      }

      if (!field4.contains(var3) && !this.field11.contains(var3)) {
         return Futures.immediateFuture(null);
      }

      Callable var4 = () -> {
         HttpURLConnection var1x = null;

         try {
            var1x = (HttpURLConnection)var2.openConnection();
            var1x.setRequestMethod("GET");
            var1x.addRequestProperty("Accept", "image/*");
            var1x.addRequestProperty("User-Agent", "LunarClient-Java");

            try (InputStream var2x = var1x.getInputStream()) {
               BufferedImage var3x = ImageIO.read(var2x);
               if (var3x == null) {
                  return null;
               }

               if (ThreadModuleDump63.MC_VERSION >= 6) {
                  int[] var4x = var3x.getRGB(0, 0, var3x.getWidth(), var3x.getHeight(), null, 0, var3x.getWidth());

                  for (int var5x = 0; var5x < var4x.length; var5x++) {
                     var4x[var5x] = ThreadModuleDump23.method17(var4x[var5x]);
                  }

                  var3x.setRGB(0, 0, var3x.getWidth(), var3x.getHeight(), var4x, 0, var3x.getWidth());
               }

               return var3x;
            }
         } catch (Throwable var13) {
            if (!(var13 instanceof IOException)) {
               var13.printStackTrace();
            }

            return null;
         } finally {
            if (var1x != null) {
               var1x.disconnect();
            }
         }
      };
      return field1.submit(var4);
   }

   @NotNull
   private String method5(@NotNull String var1) {
      if (var1.startsWith("https://lunr.pics/")) {
         return var1.replace("https://lunr.pics/", "https://lunr.pics/i/") + ".png";
      }

      if (var1.startsWith("https://imgur.com/")) {
         var1 = var1.replace("https://imgur.com/", "https://i.imgur.com/") + ".png";
      }

      return var1;
   }

   public boolean method6(@Nullable String var1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat var2 = ThreadModuleDump63.method4().method40().method47();
      if (!(Boolean)var2.method66().get()) {
         return false;
      }

      this.urls.clear();
      if (var1 != null) {
         this.urls.add(var1);
      } else {
         Component var3 = ThreadModuleDump63.method3()
            .bridge$getGuiIngame()
            .bridge$getChatGUI()
            .bridge$getMessageContentByLunarId(com.moonsworth.lunar.client.mod.misc.chat.Chat.method88());
         if (var3 == null) {
            return false;
         }

         String var4 = AdventureChatFormatting.getTextWithoutFormattingCodes(AdventureTextBridge.getTextContent(var3));
         Matcher var5 = field5.matcher(var4);

         while (var5.find()) {
            this.urls.add(this.method5(var5.group()));
         }
      }

      if (this.urls.isEmpty()) {
         return false;
      }

      int var7 = this.field12 % this.urls.size();
      String var8 = this.urls.get(var7);
      if (this.field7 && var8.equals(this.field8)) {
         return true;
      }

      try {
         Future var9 = (Future)this.field2.get(var8);
         if (this.urls.size() > 1 && this.field7 && this.field9 != null && this.field9.isDone() && !var9.isDone()) {
            return true;
         }

         this.field9 = var9;
         this.field7 = true;
         this.field8 = var8;
         this.field6 = null;
         return true;
      } catch (ExecutionException var6) {
         var6.printStackTrace();
         return false;
      }
   }

   public void method7(KeybindEvent var1) {
      if (!var1.method12() && var1.method11() == InputActionLegacy.DOWN && var1.method10() == KeyCode.KEY_LCONTROL) {
         this.field12++;
      }
   }

   public boolean method8() {
      return this.field7 && this.field9 != null && this.field9.isDone();
   }

   public void method9() {
      this.field7 = false;
      this.field9 = null;
      this.field6 = null;
      this.field10.stop();
      this.field10.method20(false);
   }

   protected void clearCache() {
      this.field2.invalidateAll();
   }
}
