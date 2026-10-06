package com.moonsworth.lunar.legacy.wrapper;

import com.lunarclient.apollo.module.serverlink.ServerLinkModule;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_2;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class ServerLinkScreen extends GuiScreen implements Bridge5Extension_2 {
   private static final int field1 = 40;
   private static final int field2 = 30;
   private static final int field3 = 64;
   private static final int field4 = 10;
   private static final int field5 = 8;
   public Bridge5Extension6 field6;
   private ServerLinkScreen.ServerLinkList field7;
   @Nullable
   private final ResourceLocationBridge field8;
   private final Collection<com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler.Data> field9;

   public ServerLinkScreen(Bridge5Extension6 var1) {
      this.field6 = var1;
      Optional var2 = Client.method109().method84().method3(ServerLinkModule.class).map(var0 -> (ServerLinkApolloHandler)var0);
      if (var2.isPresent()) {
         ServerLinkApolloHandler var3 = (ServerLinkApolloHandler)var2.get();
         this.field8 = var3.getResource();
         this.field9 = var3.method8();
      } else {
         this.field8 = null;
         this.field9 = Collections.emptyList();
      }
   }

   public void initGui() {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         this.buttonList$v1_7.clear();
      } else {
         this.buttonList.clear();
      }

      this.field7 = new ServerLinkScreen.ServerLinkList(this.mc, this.width, this.height, this.method2(), this.height - 30, 25);
      ((RenderBackgroundToggle)this.field7).ext$setRenderBackgrounds(false);
      GuiButton var1 = new GuiButton(0, this.width / 2 - 100, this.height - 30 + 5, 200, 20, I18n.format("gui.back", new Object[0]));
      if (ThreadModuleDump63.MC_VERSION == 0) {
         this.buttonList$v1_7.add(var1);
      } else {
         this.buttonList.add(var1);
      }
   }

   public void actionPerformed(GuiButton var1) {
      if (var1.id == 0) {
         this.onClose();
      }
   }

   public void onClose() {
      ThreadModuleDump63.method3().bridge$displayScreen(this.field6);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      super.drawScreen(var1, var2, var3);
      int var4 = LcuiScreen.method151().getScaledHeight();
      float var5 = (float)ThreadModuleDump63.method3().bridge$displayHeight() / var4;
      BridgeExtension3_5 var6 = AbstractRenderContext.method32();
      LcuiScreen.method113(var6, 0, this.method2(), this.width, this.height - 30, var5, var4);
      this.field7.drawScreen(var1, var2, var3);
      LcuiScreen.method114(var6);
      if (this.field8 == null) {
         String var7 = ThreadModuleDump63.method4().method67().method2("gui.apollo.serverLinks", "title");
         int var8 = (int)ThreadModuleDump63.method10().bridge$getStringWidth(var7);
         int var9 = (this.width - var8) / 2;
         ThreadModuleDump63.method10().bridge$drawString(AbstractRenderContext.method32(), var7, var9, 20.0F, 16777215, false);
      } else {
         this.method1(this.field8);
      }
   }

   private void method1(ResourceLocationBridge var1) {
      int var2 = this.width / 2 - 32;
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(var1);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      }

      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      drawModalRectWithCustomSizedTexture(var2, 10, 0.0F, 0.0F, 64, 64, 64.0F, 64.0F);
   }

   private int method2() {
      return this.field8 == null ? 40 : Math.max(40, 82);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.field7.handleMouseInput();
      }
   }

   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.field7.mouseClicked(var1, var2, var3);
      } else {
         this.field7.func_148179_a$v1_7(var1, var2, var3);
      }
   }

   public void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.field7.mouseReleased(var1, var2, var3);
      } else {
         this.field7.func_148181_b$v1_7(var1, var2, var3);
      }
   }

   private final class Data implements IGuiListEntry {
      private final GuiButton field1;
      private final com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler.Data field2;

      public Data(GuiButton var2, com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler.Data var3) {
         this.field1 = var2;
         this.field2 = var3;
      }

      @Annotation2(0)
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
         this.field1.xPosition = var2;
         this.field1.yPosition = var3;
         this.field1.drawButton(Minecraft.getMinecraft(), var7, var8);
      }

      @Annotation2(1)
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
         this.field1.xPosition = var2;
         this.field1.yPosition = var3;
         this.field1.drawButton(Minecraft.getMinecraft(), var6, var7);
      }

      @Annotation2(5)
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9) {
         this.field1.xPosition = var2;
         this.field1.yPosition = var3;
         this.field1.drawButton(Minecraft.getMinecraft(), var6, var7, var9);
      }

      public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
         if (this.field1.mousePressed(Minecraft.getMinecraft(), var2, var3)) {
            this.field1.playPressSound(Minecraft.getMinecraft().getSoundHandler());
            Bridge5_12 var7 = ThreadModuleDump63.method3();
            URI var8 = this.field2.uri();
            String var9 = var8.toString();
            GameOptionsBridge var10 = var7.bridge$getGameSettings();
            if (!var10.bridge$isChatLinks()) {
               return false;
            }

            if (var10.bridge$isChatPromptLinks()) {
               var7.bridge$displayScreen(Bridge.method8().method33((Bridge5Extension6)ServerLinkScreen.this, var9, var8, false));
            } else {
               ThreadModuleDump61.method7(var9, Initiator.INITIATOR_UNSPECIFIED);
            }

            return true;
         } else {
            return false;
         }
      }

      public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
         this.field1.mouseReleased(var2, var3);
      }

      @Annotation2(1)
      public void setSelected(int var1, int var2, int var3) {
      }

      @Annotation2(5)
      public void updatePosition$v1_12(int var1, int var2, int var3, float var4) {
         this.field1.xPosition = var2;
         this.field1.yPosition = var3;
      }
   }

   private final class ServerLinkList extends GuiListExtended {
      private final List<ServerLinkScreen.Data> field1 = new ArrayList<>();

      public ServerLinkList(Minecraft var2, int var3, int var4, int var5, int var6, int var7) {
         super(var2, var3, var4, var5, var6, var7);
         int var8 = this.getListWidth();
         int var9 = 1;

         for (com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler.Data var11 : ServerLinkScreen.this.field9) {
            String var12 = AdventureTextBridge.asLegacyString(var11.method1());
            GuiButton var13 = new GuiButton(var9++, 0, 0, var8, 20, var12);
            this.field1.add(ServerLinkScreen.this.new Data(var13, var11));
         }
      }

      public IGuiListEntry getListEntry(int var1) {
         return this.field1.get(var1);
      }

      public int getSize() {
         return this.field1.size();
      }

      public int getListWidth() {
         return Math.min(310, this.width - 20);
      }

      public int getScrollBarX() {
         return this.width / 2 + this.getListWidth() / 2 + 6;
      }
   }
}
