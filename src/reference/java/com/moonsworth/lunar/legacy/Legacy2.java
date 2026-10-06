package com.moonsworth.lunar.legacy;

import com.google.common.collect.ImmutableList;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.AutoCloseableExtension;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.Bridge2_11;
import com.moonsworth.lunar.bridge.Bridge2_23;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge3Extension_2;
import com.moonsworth.lunar.bridge.Bridge3Extension_6;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge3_30;
import com.moonsworth.lunar.bridge.Bridge3_4;
import com.moonsworth.lunar.bridge.Bridge4_11;
import com.moonsworth.lunar.bridge.Bridge4_21;
import com.moonsworth.lunar.bridge.Bridge4_4;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension63;
import com.moonsworth.lunar.bridge.Bridge5Extension64;
import com.moonsworth.lunar.bridge.Bridge5Extension65;
import com.moonsworth.lunar.bridge.Bridge5Extension66;
import com.moonsworth.lunar.bridge.Bridge5Extension662;
import com.moonsworth.lunar.bridge.Bridge5Extension67;
import com.moonsworth.lunar.bridge.Bridge5Extension68;
import com.moonsworth.lunar.bridge.Bridge5Extension_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_2;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.Bridge6_8;
import com.moonsworth.lunar.bridge.Bridge7_10;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.Bridge8_7;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BridgeType2_6;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.NativeImageBridge;
import com.moonsworth.lunar.bridge.NetworkConnectionBridge;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.bridge.Bridge_27;
import com.moonsworth.lunar.bridge.Bridge_46;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.Bridge_63;
import com.moonsworth.lunar.bridge.Itemcounter4Extension;
import com.moonsworth.lunar.bridge.MixinHelper$Extension;
import com.moonsworth.lunar.bridge.MixinHelper$Extension2;
import com.moonsworth.lunar.bridge.MixinHelper$Extension3;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.OutputStateShardBridge;
import com.moonsworth.lunar.bridge.RenderPipelineBuilder;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeBuilder;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.GameProfilePropertyCopier;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.lighting.Lighting;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.render.shader.ShaderPackHelper;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.ui.Click2Base;
import com.moonsworth.lunar.client.render.Click7;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.legacy.wrapper.AbstractTextureImpl;
import com.moonsworth.lunar.legacy.wrapper.Bridge18Handler;
import com.moonsworth.lunar.legacy.wrapper.Bridge19Task2;
import com.moonsworth.lunar.legacy.wrapper.Bridge2Handler_2;
import com.moonsworth.lunar.legacy.wrapper.Bridge2Iterator;
import com.moonsworth.lunar.legacy.wrapper.Bridge3Iterator;
import com.moonsworth.lunar.legacy.wrapper.Bridge4Handler_2;
import com.moonsworth.lunar.legacy.wrapper.Bridge5Handler_2;
import com.moonsworth.lunar.legacy.wrapper.Bridge6Handler;
import com.moonsworth.lunar.legacy.wrapper.Bridge7Handler;
import com.moonsworth.lunar.legacy.wrapper.BridgeHandler;
import com.moonsworth.lunar.legacy.wrapper.ItemEntityRendererBridgeImpl;
import com.moonsworth.lunar.legacy.wrapper.BlocksBridgeAdapter;
import com.moonsworth.lunar.legacy.wrapper.TiersBridgeAdapter;
import com.moonsworth.lunar.legacy.wrapper.ProtectionEnchantmentsBridgeImpl;
import com.moonsworth.lunar.legacy.wrapper.BridgeIterator;
import com.moonsworth.lunar.legacy.wrapper.NameTagRendererBridgeImpl;
import com.moonsworth.lunar.legacy.wrapper.EntityListBridgeAdapter;
import com.moonsworth.lunar.legacy.wrapper.EntityLivingBase;
import com.moonsworth.lunar.legacy.wrapper.EntityPlayerSPImpl;
import com.moonsworth.lunar.legacy.wrapper.Fog3Handler;
import com.moonsworth.lunar.legacy.wrapper.FramebufferImpl;
import com.moonsworth.lunar.legacy.wrapper.ExternalTextureFramebufferAdapter;
import com.moonsworth.lunar.legacy.wrapper.GuiScreenImpl;
import com.moonsworth.lunar.legacy.wrapper.ServerLinkScreen;
import com.moonsworth.lunar.legacy.wrapper.Horsestats23Handler;
import com.moonsworth.lunar.legacy.wrapper.Horsestats7Handler;
import com.moonsworth.lunar.legacy.wrapper.LegacyRenderTypeFactory;
import com.moonsworth.lunar.legacy.wrapper.ModelBase;
import com.moonsworth.lunar.legacy.wrapper.NetHandlerPlayClientImpl;
import com.moonsworth.lunar.legacy.wrapper.RendererLivingEntityImpl;
import com.moonsworth.lunar.legacy.wrapper.RendererLivingEntityBrightnessAdapter;
import com.moonsworth.lunar.legacy.wrapper.TessellatorImpl;
import com.moonsworth.lunar.legacy.wrapper.WorldBorderImpl;
import com.moonsworth.lunar.legacy.wrapper.WorldClientImpl;
import com.moonsworth.lunar.legacy.wrapper.LegacyServerBorderState;
import com.moonsworth.lunar.legacy.wrapper.LegacyGlStateManagerBridge;
import com.moonsworth.lunar.legacy.wrapper.AncientDummyPlayerFactory;
import com.moonsworth.lunar.legacy.wrapper.ItemDataComponentAccessors;
import com.moonsworth.lunar.legacy.wrapper.mixin.Bridge5Handler;
import com.moonsworth.lunar.legacy.wrapper.util.Horsestats13Handler;
import com.moonsworth.lunar.legacy.wrapper.util.Horsestats16Task;
import com.moonsworth.lunar.legacy.wrapper.util.OpenALNative2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.settings.KeyBinding_v1_7;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkManager_v1_12;
import net.minecraft.network.NetworkManager_v1_7;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;
import org.lwjgl.opengl.GL11;

public class Legacy2 implements BridgeImplementation {
   private Config field1;

   @Override
   public void enable() {
      Config.method1(null);
      Bridge.method13(new Bridge5Handler_2());
      Bridge.method19(new Bridge2Handler());
      Bridge.method21(new Bridge3Handler());
      Bridge.method41(new LegacyGlStateManagerBridge());
      Bridge.method15(new Bridge3Iterator());
      Bridge.method23(new Bridge18Handler());
      Bridge.method25(new Bridge4Handler_2());
      Bridge.method29(new Bridge2Iterator());
      Bridge.method31(new TiersBridgeAdapter());
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Bridge.method27(new Horsestats7Handler());
         Bridge.method54(new ItemEntityRendererBridgeImpl());
      }

      Bridge.method33(new ProtectionEnchantmentsBridgeImpl());
      Bridge.method35(new BlocksBridgeAdapter());
      Bridge.method37(new Fog3Handler());
      Bridge.method48(new Horsestats23Handler());
      Bridge.method56(new Bridge7Handler());
      Bridge.method50(new com.moonsworth.lunar.legacy.wrapper.mixin.Bridge2Handler());
      Bridge.method52(new Bridge5Handler());
      Bridge.method58(new Horsestats13Handler());
      Bridge.method60(new BridgeIterator());
      Bridge.method62(new EntityListBridgeAdapter());
      Bridge.method64(new com.moonsworth.lunar.legacy.wrapper.MixinHelper());
      Bridge.method66(new com.moonsworth.lunar.legacy.wrapper.Bridge5Handler());
      Bridge.method68(new BridgeHandler());
      Bridge.method70(GameProfilePropertyCopier.method2());
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         Bridge_27.field1 = new BridgeType2_6(false, false);
         Bridge_27.field2 = new BridgeType2_6(true, false);
         Bridge_27.field3 = new BridgeType2_6(false, true);
         Bridge_27.field8 = new BridgeType2_6(true, true);
         Bridge_27.field9 = new BridgeType2_6(true, true);
         Bridge_27.field10 = new BridgeType2_6(true, true);
         Bridge_27.field5 = new BridgeType2_6(true, false);
         Bridge_27.field7 = new BridgeType2_6(true, true);
         Bridge_27.field6 = new BridgeType2_6(true, false);
         Bridge_27.field11 = new BridgeType2_6(true, true);
         Bridge_27.field12 = new BridgeType2_6(true, true);
         Bridge_27.field4 = new BridgeType2_6(false, true);
      } else {
         Bridge_27.field1 = (Bridge_63)DefaultVertexFormats.POSITION;
         Bridge_27.field2 = (Bridge_63)DefaultVertexFormats.POSITION_TEX;
         Bridge_27.field3 = (Bridge_63)DefaultVertexFormats.POSITION_COLOR;
         Bridge_27.field5 = (Bridge_63)DefaultVertexFormats.POSITION_TEX_NORMAL;
         Bridge_27.field7 = (Bridge_63)DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL;
         Bridge_27.field8 = (Bridge_63)DefaultVertexFormats.POSITION_TEX_COLOR;
         Bridge_27.field9 = (Bridge_63)new VertexFormat()
            .addElement(DefaultVertexFormats.POSITION_3F)
            .addElement(DefaultVertexFormats.COLOR_4UB)
            .addElement(DefaultVertexFormats.TEX_2F)
            .addElement(DefaultVertexFormats.TEX_2S);
         Bridge_27.field10 = (Bridge_63)DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP;
         Bridge_27.field6 = (Bridge_63)new VertexFormat()
            .addElement(DefaultVertexFormats.POSITION_3F)
            .addElement(DefaultVertexFormats.TEX_2F)
            .addElement(DefaultVertexFormats.NORMAL_3B)
            .addElement(DefaultVertexFormats.TEX_2S);
         Bridge_27.field11 = (Bridge_63)DefaultVertexFormats.BLOCK;
         Bridge_27.field12 = Bridge_27.field7;
         Bridge_27.field4 = (Bridge_63)new VertexFormat()
            .addElement(DefaultVertexFormats.POSITION_3F)
            .addElement(DefaultVertexFormats.COLOR_4UB)
            .addElement(DefaultVertexFormats.NORMAL_3B);
      }

      this.method1(var0 -> {});
   }

   @Override
   public void method1(BridgeImplementation.Extension var1) {
      com.moonsworth.lunar.bridge.MixinHelper_2.method1(ThreadModuleDump63.MC_VERSION <= 28, var1);
      LunarRenderTypes.method1();
   }

   @Override
   public void method2(BridgeImplementation.Extension var1) {
      com.moonsworth.lunar.bridge.MixinHelper_2.method2(true, var1);
      LunarRenderTypes.method2(true);
   }

   @Override
   public void method3() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Bridge.method44(new RendererLivingEntityBrightnessAdapter(Minecraft.getMinecraft().getRenderManager(), null, 0.0F));
      } else {
         Bridge.method44(new RendererLivingEntityImpl(null, 0.0F));
      }

      ItemDataComponentAccessors.method1();
      this.method2(var0 -> {});
   }

   @Override
   public Config getMinecraftVersion() {
      return this.field1 != null ? this.field1 : (this.field1 = Config.method36(IchorAPI.getPipeline(this).orElseThrow().method34().method6()));
   }

   @Override
   public MixinHelper_15 method12(String var1, int var2, String var3, boolean var4) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         KeyBinding var6 = new KeyBinding(var1, var2, var3);
         if (var4) {
            Minecraft.getMinecraft().gameSettings.keyBindings = (KeyBinding[])ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, var6);
         }

         if (ThreadModuleDump63.MC_VERSION >= 5 && !KeyBinding.CATEGORY_ORDER$v1_12.containsKey(var3)) {
            KeyBinding.CATEGORY_ORDER$v1_12.put(var3, KeyBinding.CATEGORY_ORDER$v1_12.values().size());
         }

         return (MixinHelper_15)var6;
      } else {
         KeyBinding_v1_7 var5 = new KeyBinding_v1_7(var1, var2, var3);
         if (var4) {
            Minecraft.getMinecraft().gameSettings.keyBindings$v1_7 = (KeyBinding_v1_7[])ArrayUtils.add(
               Minecraft.getMinecraft().gameSettings.keyBindings$v1_7, var5
            );
         }

         return (MixinHelper_15)var5;
      }
   }

   @Override
   public Bridge2_42 method13(String var1) {
      return (Bridge2_42)(new ChatComponentText(var1));
   }

   @Override
   public Bridge20Extension method15(File var1, String var2, ResourceLocationBridge var3, ResourceLocationBridge var4, boolean var5) {
      return (Bridge20Extension)(new ThreadDownloadImageData(var1, var2, (ResourceLocation)var4, var5 ? new ImageBufferDownload() : null));
   }

   @Override
   public AutoCloseableExtension method16(NativeImageBridge var1) {
      throw new AbstractMethodErrorImpl("initWrappedDownloadedTexture only available in modern!");
   }

   @Override
   public Horsestats method17(String var1, String var2, String var3, String var4) {
      return (Horsestats)(new Session(var1, var2, var3, var4));
   }

   @Override
   public Bridge5Extension62 method18(Bridge7_8 var1) {
      return new GuiScreenImpl(var1);
   }

   public Bridge5Extension610 method10() {
      return (Bridge5Extension610)(new GuiMainMenu());
   }

   @Override
   public Bridge4_11 method20() {
      return (Bridge4_11)Tessellator.theMinecraft;
   }

   @Override
   public Bridge8Extension33 method21(int var1, int var2) {
      return (Bridge8Extension33)(new DynamicTexture(var1, var2));
   }

   @Override
   public Bridge8Extension33 method22(BufferedImage var1) {
      return (Bridge8Extension33)(new DynamicTexture(var1));
   }

   @Override
   public Bridge5Extension67 method28(@Nullable Bridge5Extension6 var1) {
      return (Bridge5Extension67)(new GuiSelectWorld((GuiScreen)var1));
   }

   @Override
   public Bridge5Extension65 method29(@Nullable Bridge5Extension6 var1) {
      return (Bridge5Extension65)(new GuiMultiplayer((GuiScreen)var1));
   }

   @Override
   public Bridge5Extension64 method30(@Nullable Bridge5Extension6 var1) {
      return (Bridge5Extension64)(new GuiOptions((GuiScreen)var1, Minecraft.getMinecraft().gameSettings));
   }

   @Override
   public Bridge5Extension63 method31(@Nullable Bridge5Extension6 var1) {
      return (Bridge5Extension63)(new GuiLanguage((GuiScreen)var1, Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().getLanguageManager()));
   }

   @Override
   public Bridge5Extension6 method32(@Nullable Bridge5Extension6 var1) {
      throw new UnsupportedOperationException("Realms is only available on the latest version!");
   }

   @Override
   public Bridge5Extension662 method33(@Nullable Bridge5Extension6 var1, String var2, URI var3, boolean var4) {
      return (Bridge5Extension662)(new GuiConfirmOpenLink((var2x, var3x) -> {
         if (var2x) {
            ThreadModuleDump61.method5(var3, Initiator.INITIATOR_LINK_CONFIRM);
         }

         ThreadModuleDump63.method3().bridge$displayScreen(var1);
      }, var2, 0, var4));
   }

   @Override
   public Bridge5Extension66 method34(String var1, String var2, String var3, String var4, Runnable var5, Runnable var6) {
      return (Bridge5Extension66)(new GuiYesNo((var2x, var3x) -> {
         if (var2x) {
            var5.run();
         } else {
            var6.run();
         }
      }, var1, var2, var3, var4, 0));
   }

   @Override
   public Bridge5Extension68 method35(final Runnable var1, String var2, String var3) {
      GuiErrorScreen var4 = new GuiErrorScreen(var2, var3) {
         public void actionPerformed(GuiButton var1x) {
            var1.run();
         }
      };
      return (Bridge5Extension68)var4;
   }

   @Override
   public Bridge5Extension_2 method36(@Nullable Bridge5Extension6 var1) {
      return new ServerLinkScreen(var1);
   }

   @Override
   public void method37() {
      throw new AbstractMethodErrorImpl("openFriendsOverlayScreen only available on 26.2+");
   }

   @Override
   public ItemStackBridge method38(Bridge6_4 var1) {
      return (ItemStackBridge)(new ItemStack((Item)var1));
   }

   @Override
   public ItemStackBridge method39(Bridge3_23 var1) {
      return (ItemStackBridge)(new ItemStack((Block)var1));
   }

   @Override
   public ItemStackBridge method40(Bridge_57 var1) {
      return ThreadModuleDump63.MC_VERSION >= 5
         ? (ItemStackBridge)(new ItemStack((NBTTagCompound)var1))
         : (ItemStackBridge)ItemStack.loadItemStackFromNBT((NBTTagCompound)var1);
   }

   @Override
   public ItemStackBridge method41() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (ItemStackBridge)ItemStack.EMPTY$v1_12 : (ItemStackBridge)(new ItemStack(Blocks.air));
   }

   @Override
   public List<Bridge2_11> method42() {
      return (List<Bridge2_11>)(ThreadModuleDump63.MC_VERSION >= 5
         ? Minecraft.getMinecraft().ingameGUI.overlayBoss$v1_12.mapBossInfos.values().stream().map(Bridge2Handler_2::new).collect(Collectors.toList())
         : ImmutableList.of(new Bridge2Handler_2()));
   }

   @Override
   public Bridge7_9 method23(ByteBuf var1) {
      return (Bridge7_9)(new PacketBuffer(var1));
   }

   @Override
   public Bridge3Extension_2 method24(String var1, Bridge7_9 var2) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge3Extension_2)(new C17PacketCustomPayload(var1, (PacketBuffer)var2))
         : (Bridge3Extension_2)(new C17PacketCustomPayload(var1, var2.bridge$readBytes(new byte[var2.bridge$readableBytes()])));
   }

   @Override
   public Bridge3Extension_6 method25(String var1, Bridge7_9 var2) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Bridge3Extension_6)(new S3FPacketCustomPayload(var1, (PacketBuffer)var2))
         : (Bridge3Extension_6)(new S3FPacketCustomPayload(var1, var2.bridge$readBytes(new byte[var2.bridge$readableBytes()])));
   }

   @Override
   public Bridge3_23 method26(Bridge6_4 var1) {
      return (Bridge3_23)Block.getBlockFromItem((Item)var1);
   }

   @Override
   public Lighting4 method43() {
      return (Lighting4)(new Scoreboard());
   }

   @Override
   public Lighting method44(Lighting4 var1, String var2) {
      return (Lighting)(new ScoreObjective((Scoreboard)var1, var2, IScoreObjectiveCriteria.DUMMY));
   }

   @Override
   public AxisAlignedBBBridge method45(double var1, double var3, double var5, double var7, double var9, double var11) {
      return (AxisAlignedBBBridge)(new AxisAlignedBB(var1, var3, var5, var7, var9, var11));
   }

   @Override
   public Itemcounter4Extension method46(String var1, int var2) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? new WorldBorderImpl(var1, var2) : new LegacyServerBorderState(var1, var2);
   }

   @Override
   public Bridge8Extension34 method47(Bridge3_4 var1) {
      return new AbstractTextureImpl<>(var1);
   }

   @Override
   public Bridge5Extension_5 method48() {
      NetHandlerPlayClientImpl var1 = NetHandlerPlayClientImpl.method1(Minecraft.getMinecraft());
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return (Bridge5Extension_5)EntityPlayerSPImpl.method1(Minecraft.getMinecraft(), new WorldClientImpl(var1), var1, new StatFileWriter());
      }

      ThreadModuleDump54 var2 = AncientDummyPlayerFactory.getInstance().createDummyPlayer(Minecraft.getMinecraft(), new WorldClientImpl(var1));
      return (Bridge5Extension_5)var2;
   }

   @Override
   public MovementStateBridge method49(GameOptionsBridge var1) {
      return (MovementStateBridge)(new MovementInputFromOptions((GameSettings)var1));
   }

   @Override
   public Bridge3_19 method50(String var1, String var2, boolean var3) {
      return (Bridge3_19)(new ServerData(var1, var2, var3));
   }

   @Override
   public Bridge4_4 method51() {
      return (Bridge4_4)(new ServerList(Minecraft.getMinecraft()));
   }

   @Override
   public Bridge2_23 method52() {
      return TessellatorImpl.method2();
   }

   @NotNull
   @Override
   public TexturedBoxRenderer method53(int var1, int var2) {
      return Horsestats16Task.method2(var1, var2);
   }

   @Override
   public Bridge4_21 method54() {
      return new ModelBase();
   }

   @Override
   public Bridge3_24 method56(int var1, int var2, boolean var3) {
      OpenALNative2.field1 = true;
      Bridge3_24 var4 = (Bridge3_24)(new Framebuffer(var1, var2, var3));
      OpenALNative2.field1 = false;
      return var4;
   }

   @Override
   public Bridge3_24 method57(Bridge_52 var1) {
      return (Bridge3_24)(new ExternalTextureFramebufferAdapter(var1));
   }

   @Override
   public Bridge3_24 method58(int var1, int var2) {
      return (Bridge3_24)(new FramebufferImpl(var1, var2, GL11.glGenTextures()));
   }

   @Override
   public Bridge7_10 method59(Bridge3_24 var1, ResourceLocationBridge var2) {
      if (ShaderLinkHelper.staticShaderLinkHelper == null) {
         ShaderLinkHelper.setNewStaticShaderLinkHelper();
      }

      TextureManager var3 = Minecraft.getMinecraft().getTextureManager();
      IResourceManager var4 = Minecraft.getMinecraft().getResourceManager();
      ShaderGroup var5 = new ShaderGroup(var3, var4, (Framebuffer)var1, (ResourceLocation)var2);
      var5.createBindFramebuffers(var1.bridge$framebufferWidth(), var1.bridge$framebufferHeight());
      return (Bridge7_10)var5;
   }

   @Override
   public Bridge3_30 method27() {
      return (Bridge3_30)(new OldServerPinger());
   }

   @Override
   public Horsestats20Extension2 method4(int var1, int var2, int var3) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Horsestats20Extension2)(new BlockPos(var1, var2, var3))
         : (Horsestats20Extension2)(new Vector3i(var1, var2, var3));
   }

   @Override
   public Vector3iBridge.Extension method9(int var1, int var2, int var3) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Vector3iBridge.Extension)(new MutableBlockPos(var1, var2, var3))
         : (Vector3iBridge.Extension)(new Vector3i(var1, var2, var3));
   }

   @Override
   public Horsestats20Extension method11(int var1, int var2, int var3) {
      return (Horsestats20Extension)(new Vector3i(var1, var2, var3));
   }

   @Override
   public BufferedImage method55(InputStream var1) {
      return ThreadModuleDump59.method1(var1, null);
   }

   @Override
   public Bridge6_2 method63() {
      return new Bridge6Handler();
   }

   @Override
   public boolean method64(URI var1) {
      MixinMisc.method1().openUri(var1);
      return false;
   }

   @Override
   public boolean method65(String var1) {
      MixinMisc.method1().openUri(var1);
      return false;
   }

   @Override
   public boolean method66(File var1) {
      MixinMisc.method1().openFile(var1);
      return true;
   }

   @Override
   public void method67() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         KeyBinding.resetKeyBindingArrayAndHash();
      } else {
         KeyBinding_v1_7.resetKeyBindingArrayAndHash();
      }
   }

   @Override
   public Bridge_57 method68() {
      return (Bridge_57)(new NBTTagCompound());
   }

   @Annotation2(min = 1)
   @Override
   public Optional<Bridge_57> method69(String var1) {
      try {
         return Optional.of((Bridge_57)JsonToNBT.getTagFromJson(var1));
      } catch (NBTException var3) {
         return Optional.empty();
      }
   }

   @Override
   public NetworkConnectionBridge method77(UUID var1, String var2, String var3) {
      EmbeddedChannel var4;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var4 = new EmbeddedChannel();
      } else {
         var4 = new EmbeddedChannel(new ChannelHandler[]{new ChannelInitializer<Channel>() {
            protected void initChannel(Channel var1) {
            }
         }});
      }

      InetSocketAddress var5;
      try {
         String[] var6 = var3.split(":");
         String var7 = var6[0];
         String var8 = var6.length > 1 ? var6[1] : "25565";
         InetAddress var9 = InetAddress.getByName(var7);
         var5 = new InetSocketAddress(var9, Integer.parseInt(var8));
      } catch (UnknownHostException var10) {
         var5 = InetSocketAddress.createUnresolved(var3, 25565);
      }

      NetworkConnectionBridge var11;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         NetworkManager_v1_7 var12 = new NetworkManager_v1_7(true);
         var11 = (NetworkConnectionBridge)var12;
         var12.channel = var4;
         var12.setConnectionState(EnumConnectionState.PLAY);
         var12.setNetHandler(new NetHandlerPlayClient(Minecraft.getMinecraft(), null, var12));
         var12.socketAddress = var5;
      } else if (ThreadModuleDump63.MC_VERSION <= 1) {
         NetworkManager var13 = new NetworkManager(EnumPacketDirection.CLIENTBOUND);
         var11 = (NetworkConnectionBridge)var13;
         var13.channel = var4;
         var13.setConnectionState(EnumConnectionState.PLAY);
         var13.setNetHandler(new NetHandlerPlayClient(Minecraft.getMinecraft(), null, var13, new GameProfile(var1, var2)));
         var13.socketAddress = var5;
      } else {
         NetworkManager_v1_12 var14 = new NetworkManager_v1_12(EnumPacketDirection.CLIENTBOUND);
         var11 = (NetworkConnectionBridge)var14;
         var14.channel = var4;
         var14.setConnectionState(EnumConnectionState.PLAY);
         var14.setNetHandler(new NetHandlerPlayClient(Minecraft.getMinecraft(), null, var14, new GameProfile(var1, var2)));
         var14.socketAddress = var5;
      }

      return var11;
   }

   @Override
   public BridgeExtension2_5 method78(Itemcounter6 var1) {
      return (BridgeExtension2_5)(new EntityLivingBase((World)var1));
   }

   @Override
   public IResourcePackBridge method79(File var1, boolean var2) {
      return (IResourcePackBridge)(var1.isFile() ? new FileResourcePack(var1) : new FolderResourcePack(var1));
   }

   @Override
   public Bridge6_8 method87(String var1, Bridge_63 var2) {
      return (Bridge6_8)(new ShaderManager(Minecraft.getMinecraft().getResourceManager(), var1));
   }

   @Override
   public String method71(String var1) {
      return Bridge.method57().method1(Bridge.method57().method2(var1));
   }

   @Override
   public RenderPipelineBuilder method80() {
      return new com.moonsworth.lunar.legacy.wrapper.LegacyRenderPipelineBuilder();
   }

   @Override
   public RenderTypeBuilder method81() {
      return new LegacyRenderTypeFactory();
   }

   @Override
   public Itemcounter4 method82(String var1) {
      WorldInfo var2 = new WorldInfo();
      var2.randomSeed = new Random().nextLong();
      if (ThreadModuleDump63.MC_VERSION == 5) {
         var2.gameType$v1_12 = GameType.CREATIVE;
      } else {
         var2.theGameType = net.minecraft.world.WorldSettings.GameType.CREATIVE;
      }

      var2.field_177475_o = WorldType.DEFAULT;
      return (Itemcounter4)WorldSettings.newInstance(var2);
   }

   @Override
   public Bridge5Extension612 method88(String var1) {
      return (Bridge5Extension612)GuiChat.newInstance(var1);
   }

   @Override
   public MixinHelper$Extension2 method90(MixinHelper$Extension var1, int var2, int var3) {
      return new Click2Base.Data2(var1, var2, var3);
   }

   @Override
   public MixinHelper$Extension3 method89(Component var1) {
      return new Click2Base.Data(var1);
   }

   @Override
   public Bridge_46 method85() {
      return Bridge.getMinecraftVersion().equals(Config.field2) ? new NameTagRendererBridgeImpl() : null;
   }

   @Override
   public OutputStateShardBridge method93(String var1, Supplier<Bridge3_24> var2) {
      return new Bridge19Task2(() -> ThreadModuleDump63.method3().bridge$overrideMainRenderTarget((Bridge3_24)var2.get(), true, true), ShaderPackHelper::method1);
   }

   @Override
   public void method91(Bridge8_7 var1, int var2, int var3, int var4, int var5, TexturePixelFormat var6, Consumer<ByteBuffer> var7) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         GlStateManager.glPixelStorei$v1_12(3331, 0);
         GlStateManager.glPixelStorei$v1_12(3332, 0);
         GlStateManager.glPixelStorei$v1_12(3330, 0);
         GlStateManager.glPixelStorei$v1_12(3333, 4);
      } else {
         GL11.glPixelStorei(3331, 0);
         GL11.glPixelStorei(3332, 0);
         GL11.glPixelStorei(3330, 0);
         GL11.glPixelStorei(3333, 4);
      }

      Click7.method1(var1, var2, var3, var4, var5, var6, var7);
   }

   @Override
   public IResourceBridge method94(ResourceLocationBridge var1, Supplier<InputStream> var2) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (IResourceBridge)SimpleResource.newInstance("lunar-jit", (ResourceLocation)var1, (InputStream)var2.get(), null, new IMetadataSerializer())
         : (IResourceBridge)SimpleResource.newInstance((ResourceLocation)var1, (InputStream)var2.get(), null, new IMetadataSerializer());
   }
}
