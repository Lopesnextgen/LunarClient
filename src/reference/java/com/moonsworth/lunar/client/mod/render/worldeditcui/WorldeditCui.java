package com.moonsworth.lunar.client.mod.render.worldeditcui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.worldeditcui.Worldeditcui;
import com.moonsworth.lunar.client.framework.feature.worldeditcui.WorldEditSelectionBase;
import com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin.WorldeditcuiIterator;
import com.moonsworth.lunar.client.network.server.PluginMessageHandler;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.network.EventPluginChannelRegister;
import com.moonsworth.lunar.client.event.mixin.gui.EventPluginMessage;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.buffer.Unpooled;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import lombok.Generated;

public final class WorldeditCui extends AbstractFeature {
   public static final String field8 = "WECUI";
   public static final String field9 = "worldedit:cui";
   private final Worldeditcui field10 = new Worldeditcui();
   private final WorldeditcuiIterator field11 = new WorldeditcuiIterator();
   private final ColorOption field12 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("positionOneColor")
      .method9(Color.BLUE)
      .method31();
   private final ColorOption field13 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("positionTwoColor")
      .method9(Color.RED)
      .method31();
   private final ColorOption field14 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("outlineColor")
      .method9(Color.GREEN)
      .method31();

   public WorldeditCui() {
      super(false);
      this.handle(HudRenderLegacyEvent.class, arg1 -> {
         AbstractRenderContext bridgeextension_92 = arg1.method3();

         try {
            if (this.field10.method2() != null) {
               if (!bridgeextension_92.method38()) {
                  bridgeextension_92.method13();
                  bridgeextension_92.method19();
                  bridgeextension_92.method6(false);
               } else {
                  bridgeextension_92.method19();
               }

               Bridge.method5().ifPresent(arg0 -> {
                  if (arg0.getConfig().hasShaders()) {
                     ShadersBridge slayer31x = arg0.getShaders();
                     slayer31x.pushUseProgram(slayer31x.getProgramBasic());
                  }
               });
               if (((WorldEditSelectionBase)this.field10.method2()).getPoints().size() >= 2) {
                  com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin.Worldeditcui worldeditcui3 = this.field10.method1().getRenderer();
                  if (worldeditcui3 != null) {
                     worldeditcui3.method1(bridgeextension_92, this);
                  }
               }

               this.field11.method1(bridgeextension_92, this);
               Bridge.method5().ifPresent(arg0 -> {
                  if (arg0.getConfig().hasShaders()) {
                     ShadersBridge slayer31x = arg0.getShaders();
                     slayer31x.popProgram();
                  }
               });
               if (!bridgeextension_92.method38()) {
                  bridgeextension_92.method12();
                  bridgeextension_92.method18();
                  bridgeextension_92.method6(true);
               }
            }
         } catch (Throwable exception4) {
            exception4.printStackTrace();
         }
      });
      this.handle(EventPluginMessage.class, arg1 -> {
         if (arg1.getChannel().equals("WECUI") || arg1.getChannel().equals("worldedit:cui")) {
            this.field10.method3(new String(arg1.getData(), StandardCharsets.UTF_8));
         }
      });
      LunarEventBus.method29()
         .method2(
            EventPluginChannelRegister.class,
            arg0 -> {
               if (PluginMessageHandler.field2.contains("WECUI") || PluginMessageHandler.field2.contains("worldedit:cui")) {
                  NetHandlerPlayClientBridge bridgeextension_71 = Ref.method9();
                  if (bridgeextension_71 != null) {
                     if (Bridge.getMinecraftVersion().method21()) {
                        bridgeextension_71.bridge$addToSendQueue(
                           Bridge.method8().method24("WECUI", Bridge.method8().method23(Unpooled.wrappedBuffer("v|3".getBytes(StandardCharsets.UTF_8))))
                        );
                     }

                     bridgeextension_71.bridge$addToSendQueue(
                        Bridge.method8().method24("worldedit:cui", Bridge.method8().method23(Unpooled.wrappedBuffer("v|3".getBytes(StandardCharsets.UTF_8))))
                     );
                  }
               }
            }
         );
      PluginMessageHandler.method6("WECUI");
      PluginMessageHandler.method6("worldedit:cui");
   }

   public String getId() {
      return "WORLDEDIT_CUI";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12, this.field13, this.field14});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   @Generated
   public Worldeditcui method13() {
      return this.field10;
   }

   @Generated
   public WorldeditcuiIterator method14() {
      return this.field11;
   }

   @Generated
   public ColorOption method15() {
      return this.field12;
   }

   @Generated
   public ColorOption method16() {
      return this.field13;
   }

   @Generated
   public ColorOption method17() {
      return this.field14;
   }
}
