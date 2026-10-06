package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.net.InternetDomainName;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiNewChatBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.replay.gui.GuiScreenContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase.EventRenderHudFocused;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.client.ui.MousePosition;
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

public class ChatImagePreview {
   private static final ExecutorService field1 = Executors.newFixedThreadPool(
      1, new ThreadFactoryBuilder().setNameFormat("Hover Image Preview Downloader #%d").setDaemon(true).build()
   );
   private final LoadingCache<String, Future<BufferedImage>> field2 = CacheBuilder.newBuilder()
      .maximumSize(10L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .softValues()
      .build(new CacheLoader<String, Future<BufferedImage>>() {
         public Future<BufferedImage> method1(String text1) {
            return ChatImagePreview.this.method4(text1);
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

   protected ChatImagePreview() {
      this.field10 = new HoverAnimation(250L);
   }

   protected void method1(String[] items1) {
      this.field11.clear();
      Arrays.stream(items1).map(arg0 -> arg0.toLowerCase(Locale.ROOT)).forEach(this.field11::add);
   }

   protected void method2(EventRenderHudFocused data41) {
      if (Ref.MC_VERSION < 24) {
         this.method3(data41.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(), data41.RHIORIRICRIHIRIOOICCICRHCIOCCI());
      }
   }

   public void method3(MixinHelper_4 mixinhelper_41, MarkerModel<?> markers2) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat chat3 = Ref.method4().method40().method47();
      if ((Boolean)chat3.method66().get()) {
         if (this.field7 && this.field9 != null && this.field9.isDone()) {
            GuiScreenBridge bridge5extension64 = Ref.method3().bridge$getCurrentScreenOrRewind();
            if (!(bridge5extension64 instanceof Bridge5Extension612) && !(bridge5extension64 instanceof GuiNewChatBridge)) {
               this.method9();
            } else {
               try {
                  BufferedImage bufferedimage5 = this.field9.get();
                  if (bufferedimage5 == null) {
                     return;
                  }

                  com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4 data46 = MousePosition.method1().method7();
                  RewindHandlers rewindhandlers7 = Ref.method4().method40().method85().method35();
                  if (rewindhandlers7 != null) {
                     GuiScreenContext nameplate28 = ((ReplayContext)rewindhandlers7.method42().get()).method8();
                     GuiResolution threadmoduledump719 = LcuiScreen.method151();
                     data46 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4(
                        nameplate28.method3(Ref.method3().bridge$getTimer().method1(), threadmoduledump719.getScaledWidth()),
                        nameplate28.method4(Ref.method3().bridge$getTimer().method1(), threadmoduledump719.getScaledHeight())
                     );
                  }

                  double value27 = bufferedimage5.getHeight();
                  double value10 = bufferedimage5.getWidth();
                  double value12 = (Double)chat3.method69().get();
                  double value14 = (Double)chat3.method70().get();
                  if ((Boolean)chat3.method67().get() && value14 < 100.0) {
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

                     value14 += (100.0 - value14) * this.field10.method9();
                  }

                  double value16 = value10 / value27;
                  double value18 = markers2.method10();
                  double value20 = markers2.method11();
                  if (value27 / value20 > value10 / value18) {
                     value27 = ClampUtils.clamp(value27, value12 / 100.0 * value20, value14 / 100.0 * value20);
                     value10 = value27 * value16;
                  } else {
                     value10 = ClampUtils.clamp(value10, value12 / 100.0 * value18, value14 / 100.0 * value18);
                     value27 = value10 / value16;
                  }

                  double value22 = data46.HHHCHORHIHRCOHIOICICICHCRRICCI();
                  double value24 = data46.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - value27;
                  if (value24 < 0.0 || value24 + value27 > value20) {
                     value24 = 0.0;
                  }

                  if (value22 + value10 > value18) {
                     value22 = value18 - value10;
                  }

                  if (this.field6 == null) {
                     this.field6 = Bridge.method8().method22(bufferedimage5);
                     Ref.method3().bridge$getTextureManager().bridge$deleteTexture(field3);
                     Ref.method3().bridge$getTextureManager().bridge$loadTexture(field3, this.field6);
                  }

                  mixinhelper_41.method38(0.0F, 0.0F, 200.0F);
                  LcuiScreen.method31(mixinhelper_41, field3, (float)value22, (float)value24, (float)value10, (float)value27, -1);
               } catch (Exception exception26) {
                  exception26.printStackTrace();
               }
            }
         }
      }
   }

   private Future<BufferedImage> method4(String text1) {
      URL url2;
      String text3;
      try {
         url2 = new URL(text1);
         text3 = InternetDomainName.from(url2.getHost()).topPrivateDomain().toString();
      } catch (Exception exception5) {
         return Futures.immediateFuture(null);
      }

      if (!field4.contains(text3) && !this.field11.contains(text3)) {
         return Futures.immediateFuture(null);
      }

      Callable callable4 = () -> {
         HttpURLConnection httpurlconnection1x = null;

         try {
            httpurlconnection1x = (HttpURLConnection)url2.openConnection();
            httpurlconnection1x.setRequestMethod("GET");
            httpurlconnection1x.addRequestProperty("Accept", "image/*");
            httpurlconnection1x.addRequestProperty("User-Agent", "LunarClient-Java");

            try (InputStream input2x = httpurlconnection1x.getInputStream()) {
               BufferedImage bufferedimage3x = ImageIO.read(input2x);
               if (bufferedimage3x == null) {
                  return null;
               }

               if (Ref.MC_VERSION >= 6) {
                  int[] items4x = bufferedimage3x.getRGB(0, 0, bufferedimage3x.getWidth(), bufferedimage3x.getHeight(), null, 0, bufferedimage3x.getWidth());

                  for (int index5x = 0; index5x < items4x.length; index5x++) {
                     items4x[index5x] = ColorUtils.method17(items4x[index5x]);
                  }

                  bufferedimage3x.setRGB(0, 0, bufferedimage3x.getWidth(), bufferedimage3x.getHeight(), items4x, 0, bufferedimage3x.getWidth());
               }

               return bufferedimage3x;
            }
         } catch (Throwable exception13) {
            if (!(exception13 instanceof IOException)) {
               exception13.printStackTrace();
            }

            return null;
         } finally {
            if (httpurlconnection1x != null) {
               httpurlconnection1x.disconnect();
            }
         }
      };
      return field1.submit(callable4);
   }

   @NotNull
   private String method5(@NotNull String text1) {
      if (text1.startsWith("https://lunr.pics/")) {
         return text1.replace("https://lunr.pics/", "https://lunr.pics/i/") + ".png";
      }

      if (text1.startsWith("https://imgur.com/")) {
         text1 = text1.replace("https://imgur.com/", "https://i.imgur.com/") + ".png";
      }

      return text1;
   }

   public boolean method6(@Nullable String text1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat chat2 = Ref.method4().method40().method47();
      if (!(Boolean)chat2.method66().get()) {
         return false;
      }

      this.urls.clear();
      if (text1 != null) {
         this.urls.add(text1);
      } else {
         Component component3 = Ref.method3()
            .bridge$getGuiIngame()
            .bridge$getChatGUI()
            .bridge$getMessageContentByLunarId(com.moonsworth.lunar.client.mod.misc.chat.Chat.method88());
         if (component3 == null) {
            return false;
         }

         String text4 = ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent(component3));
         Matcher matcher5 = field5.matcher(text4);

         while (matcher5.find()) {
            this.urls.add(this.method5(matcher5.group()));
         }
      }

      if (this.urls.isEmpty()) {
         return false;
      }

      int index7 = this.field12 % this.urls.size();
      String text8 = this.urls.get(index7);
      if (this.field7 && text8.equals(this.field8)) {
         return true;
      }

      try {
         Future future9 = (Future)this.field2.get(text8);
         if (this.urls.size() > 1 && this.field7 && this.field9 != null && this.field9.isDone() && !future9.isDone()) {
            return true;
         }

         this.field9 = future9;
         this.field7 = true;
         this.field8 = text8;
         this.field6 = null;
         return true;
      } catch (ExecutionException executionexception6) {
         executionexception6.printStackTrace();
         return false;
      }
   }

   public void method7(EventKeybind highlightimpl1) {
      if (!highlightimpl1.method12() && highlightimpl1.method11() == InputAction.DOWN && highlightimpl1.method10() == KeyCode.KEY_LCONTROL) {
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
