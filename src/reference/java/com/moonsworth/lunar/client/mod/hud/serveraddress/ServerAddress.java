package com.moonsworth.lunar.client.mod.hud.serveraddress;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.DownloadedImageCache;
import java.awt.image.BufferedImage;
import org.jetbrains.annotations.Nullable;

public class ServerAddress extends AbstractFeature {
   private final ResourceLocationBridge field8 = ResourceLocationBridge.create("minecraft", "textures/misc/unknown_server.png");
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("serverIcon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private ResourceLocationBridge field10 = null;
   private BufferedImage field11 = null;

   public ServerAddress() {
      super(false);
      this.method9(ModTraits.field1, new ServerAddress.Data());
      this.handle(EventServerJoin.class, this::method3);
      this.handle(EventTick.class, this::method2);
      this.handle(EventDisconnect.class, this::method4);
   }

   public String getId() {
      return "SERVER_ADDRESS";
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method13();
      }
   }

   private void method2(EventTick highlightimpl21) {
      if (this.field11 != null) {
         if (this.field10 != null) {
            this.mc.bridge$getTextureManager().bridge$deleteTexture(this.field10);
         }

         Bridge8Extension33 bridge8extension332 = Bridge.method8().method22(this.field11);
         this.field10 = this.mc.bridge$getTextureManager().bridge$getDynamicTextureLocation("server-icon-thumbnail", bridge8extension332);
         this.field11 = null;
      }
   }

   private void method3(EventServerJoin highlightimpl161) {
      this.method13();
   }

   private void method4(EventDisconnect highlightimpl111) {
      if (this.field10 != null) {
         this.mc.bridge$getTextureManager().bridge$deleteTexture(this.field10);
         this.field10 = null;
      }
   }

   private void method13() {
      ServerDataBridge bridge3_191 = this.mc.bridge$getCurrentServerData();
      if (bridge3_191 != null && bridge3_191.bridge$getBase64Icon() != null && !bridge3_191.bridge$getBase64Icon().isEmpty()) {
         DownloadedImageCache.method3(bridge3_191.bridge$getBase64Icon()).ifPresent(arg1x -> this.field11 = arg1x);
      } else {
         this.field10 = null;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.SETTINGS, arg1x -> arg1x.method9(new ClientOption[]{this.field9}));
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (this.OIIHCHHRRIHCHOIHCCCIHCRICOHICH != null && !((String)this.OIIHCHHRRIHCHOIHCCCIHCRICOHICH).isEmpty()) {
            String text5 = (String)this.OIIHCHHRRIHCHOIHCCCIHCRICOHICH;
            boolean flag6 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
            boolean flag7 = !flag6 && this.IRRCCROCIRRIIOORIIRHOICCHCICIC.method3().isConditionalOrTrue(this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII::get);
            if (flag7) {
               text5 = "[" + text5 + "]";
            }

            boolean flag8 = (Boolean)ServerAddress.this.field9.get();
            float value9 = Ref.method10().bridge$getStringWidth(text5);
            this.method7(
               value9 + 8.0F + (flag8 ? (Integer)this.OCRCICOROIRHIOHCCIHHICORCHCOOO.get() : 0), ((Integer)this.OCRCICOROIRHIOHCCIHHICORCHCOOO.get()).intValue()
            );
            float value10 = this.getWidth();
            float value11 = this.getHeight();
            if (flag8) {
               value2 += value11;
               value10 -= value11;
            }

            MixinHelper_4 mixinhelper_412 = highlightimpl1.method2();
            if (flag6) {
               float value13 = flag8 ? value11 : 0.0F;
               this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_412, value2 - value13, value3, value10 + value13, value11);
               if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
                  this.CRROIIOHCOROIIOROHHCHIRRCORCRH
                     .method11(mixinhelper_412, this, value2 - value13, value3, value10 + value13, value11, (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
               }
            }

            if (flag8) {
               ResourceLocationBridge horsestats1415 = ServerAddress.this.field10 == null ? ServerAddress.this.field8 : ServerAddress.this.field10;
               mixinhelper_412.method24(horsestats1415, (int)(value2 - value11), (int)value3, (int)value11, (int)value11, -1);
            }

            float value16 = value3 + (value11 / 1.88F - Ref.method10().method19() / 2.0F + 0.5F);
            float value14 = this.method3(value2, value10, value9, flag6);
            this.method3(mixinhelper_412, this, text5, value14, value16, flag7, (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get(), flag6);
         }
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method1(false).method8();
      }

      public HudSize method15() {
         return HudSize.method1(10, 18, 22, 40, 56, 62);
      }

      @Nullable
      public String method5(boolean flag1) {
         try {
            ServerDataBridge bridge3_192 = ServerAddress.this.mc.bridge$getCurrentServerData();
            String text3;
            if (bridge3_192 == null) {
               text3 = flag1 ? "na.lunar.gg" : "";
            } else {
               text3 = bridge3_192.bridge$serverIP();
            }

            com.moonsworth.lunar.client.network.hostedworld.HostedWorldHost coordinates4 = Ref.method4().method81().method37();
            return coordinates4 != null
               ? (String)ServerAddress.this.method12("ip", coordinates4.username() + ServerAddress.this.method11("playersWorld", new Object[0]))
               : (String)ServerAddress.this.method12("ip", text3);
         } catch (NullPointerException nullpointerexception5) {
            return (String)ServerAddress.this.method12("ip", flag1 ? "na.lunar.gg" : "");
         }
      }
   }
}
