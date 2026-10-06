package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.potion.PotionRegistryBridge;
import com.moonsworth.lunar.bridge.minecraft.ImageConverterBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatComponentFactoryBridge;
import com.moonsworth.lunar.bridge.minecraft.ClipboardBridge;
import com.moonsworth.lunar.bridge.minecraft.GameProfilePropertyMerger;
import com.moonsworth.lunar.bridge.horsestats.CryptManagerBridge;
import com.moonsworth.lunar.bridge.minecraft.FaceBakeryBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;

public class Bridge {
   private static BridgeImplementation field1;
   private static MinecraftBridge field2;
   private static CryptManagerBridge field3;
   private static TextureUtilBridge field4;
   private static RenderHelperBridge field5;
   private static ImageConverterBridge field6;
   private static KeyboardBridge field7;
   private static MouseHelperBridge field8;
   private static OpenGlHelperBridge field9;
   private static PlayerModelPartsBridge field10;
   private static FaceBakeryBridge field11;
   private static ItemsBridge field12;
   private static TiersBridge field13;
   private static ProtectionEnchantmentsBridge field14;
   private static BlocksBridge field15;
   private static PotionRegistryBridge field16;
   private static OptifineBridge field17;
   private static EntityRegistryBridge field18;
   private static ViewFrustumBridge field19;
   private static RenderSystemBridge field20;
   private static RendererLivingEntityBridge field21;
   private static String field22 = "https://s-optifine.net";
   private static ClipboardBridge field23;
   private static JsonToNBTBridge field24;
   private static NbtIoBridge field25;
   private static ItemEntityRendererBridge field26;
   private static List<ResourcePackBridge> field27 = new ArrayList<>();
   private static Bridge7_3 field28;
   private static ChatComponentFactoryBridge field29;
   private static PacketFactory field30;
   private static EntityListBridge field31;
   private static ParticleSpawner field32;
   private static AlcBridge field33;
   private static GlFenceSyncBridge field34;
   private static GameProfilePropertyMerger field35;

   public Bridge() {
   }

   public static void method1(BridgeImplementation bridge20) {
      if (field1 != null) {
         throw new IllegalStateException("Can't reset Bridge Implementation!");
      }

      field1 = bridge20;
      System.out.println("[Bridge] Setting Bridge Implementation to " + bridge20.getClass().getName());
      field1.enable();
   }

   public static void method2(MinecraftBridge bridge5_120) {
      if (field2 != null) {
         throw new IllegalStateException("Can't reset Minecraft Client instance!");
      }

      field2 = bridge5_120;
      System.out.println("[Bridge] Setting Minecraft Client instance to " + bridge5_120.getClass().getName());
   }

   public static void method3(ResourcePackBridge bridge140) {
      field27.add(bridge140);
   }

   public static List<ResourcePackBridge> method4() {
      return field27;
   }

   public static Config getMinecraftVersion() {
      return field1.getMinecraftVersion();
   }

   public static Optional<OptifineBridge> method5() {
      return Optional.ofNullable(field17);
   }

   public static Optional<EntityRegistryBridge> method6() {
      return Optional.ofNullable(field18);
   }

   public static Optional<ViewFrustumBridge> method7() {
      return Optional.ofNullable(field19);
   }

   @Generated
   public static BridgeImplementation method8() {
      return field1;
   }

   @Generated
   public static MinecraftBridge method9() {
      return field2;
   }

   @Generated
   public static CryptManagerBridge method10() {
      return field3;
   }

   @Generated
   public static void method11(CryptManagerBridge horsestats30) {
      field3 = horsestats30;
   }

   @Generated
   public static TextureUtilBridge method12() {
      return field4;
   }

   @Generated
   public static void method13(TextureUtilBridge bridge5_70) {
      field4 = bridge5_70;
   }

   @Generated
   public static RenderHelperBridge method14() {
      return field5;
   }

   @Generated
   public static void method15(RenderHelperBridge bridge3_350) {
      field5 = bridge3_350;
   }

   @Generated
   public static ImageConverterBridge method16() {
      return field6;
   }

   @Generated
   public static void method17(ImageConverterBridge horsestats110) {
      field6 = horsestats110;
   }

   @Generated
   public static KeyboardBridge method18() {
      return field7;
   }

   @Generated
   public static void method19(KeyboardBridge bridge2_390) {
      field7 = bridge2_390;
   }

   @Generated
   public static MouseHelperBridge method20() {
      return field8;
   }

   @Generated
   public static void method21(MouseHelperBridge bridge3_270) {
      field8 = bridge3_270;
   }

   @Generated
   public static OpenGlHelperBridge method22() {
      return field9;
   }

   @Generated
   public static void method23(OpenGlHelperBridge bridge180) {
      field9 = bridge180;
   }

   @Generated
   public static PlayerModelPartsBridge method24() {
      return field10;
   }

   @Generated
   public static void method25(PlayerModelPartsBridge bridge4_240) {
      field10 = bridge4_240;
   }

   @Generated
   public static FaceBakeryBridge method26() {
      return field11;
   }

   @Generated
   public static void method27(FaceBakeryBridge horsestats70) {
      field11 = horsestats70;
   }

   @Generated
   public static ItemsBridge method28() {
      return field12;
   }

   @Generated
   public static void method29(ItemsBridge bridge2_210) {
      field12 = bridge2_210;
   }

   @Generated
   public static TiersBridge method30() {
      return field13;
   }

   @Generated
   public static void method31(TiersBridge bridge_500) {
      field13 = bridge_500;
   }

   @Generated
   public static ProtectionEnchantmentsBridge method32() {
      return field14;
   }

   @Generated
   public static void method33(ProtectionEnchantmentsBridge bridge_40) {
      field14 = bridge_40;
   }

   @Generated
   public static BlocksBridge method34() {
      return field15;
   }

   @Generated
   public static void method35(BlocksBridge bridge_560) {
      field15 = bridge_560;
   }

   @Generated
   public static PotionRegistryBridge method36() {
      return field16;
   }

   @Generated
   public static void method37(PotionRegistryBridge fog30) {
      field16 = fog30;
   }

   @Generated
   public static void method38(OptifineBridge slayer20) {
      field17 = slayer20;
   }

   @Generated
   public static void method39(EntityRegistryBridge bridge_220) {
      field18 = bridge_220;
   }

   @Generated
   public static void method40(ViewFrustumBridge heightlimit0) {
      field19 = heightlimit0;
   }

   @Generated
   public static void method41(RenderSystemBridge bridge120) {
      field20 = bridge120;
   }

   @Generated
   public static RenderSystemBridge method42() {
      return field20;
   }

   @Generated
   public static RendererLivingEntityBridge method43() {
      return field21;
   }

   @Generated
   public static void method44(RendererLivingEntityBridge bridge110) {
      field21 = bridge110;
   }

   @Generated
   public static String method45() {
      return field22;
   }

   @Generated
   public static void method46(String text) {
      field22 = text;
   }

   @Generated
   public static ClipboardBridge method47() {
      return field23;
   }

   @Generated
   public static void method48(ClipboardBridge horsestats230) {
      field23 = horsestats230;
   }

   @Generated
   public static JsonToNBTBridge method49() {
      return field24;
   }

   @Generated
   public static void method50(JsonToNBTBridge bridge2_340) {
      field24 = bridge2_340;
   }

   @Generated
   public static NbtIoBridge method51() {
      return field25;
   }

   @Generated
   public static void method52(NbtIoBridge bridge5_20) {
      field25 = bridge5_20;
   }

   @Generated
   public static ItemEntityRendererBridge method53() {
      return field26;
   }

   @Generated
   public static void method54(ItemEntityRendererBridge bridge_370) {
      field26 = bridge_370;
   }

   @Generated
   public static Bridge7_3 method55() {
      return field28;
   }

   @Generated
   public static void method56(Bridge7_3 bridge7_30) {
      field28 = bridge7_30;
   }

   @Generated
   public static ChatComponentFactoryBridge method57() {
      return field29;
   }

   @Generated
   public static void method58(ChatComponentFactoryBridge horsestats130) {
      field29 = horsestats130;
   }

   @Generated
   public static PacketFactory method59() {
      return field30;
   }

   @Generated
   public static void method60(PacketFactory bridge_160) {
      field30 = bridge_160;
   }

   @Generated
   public static EntityListBridge method61() {
      return field31;
   }

   @Generated
   public static void method62(EntityListBridge bridge_690) {
      field31 = bridge_690;
   }

   @Generated
   public static ParticleSpawner method63() {
      return field32;
   }

   @Generated
   public static void method64(ParticleSpawner mixinhelper_30) {
      field32 = mixinhelper_30;
   }

   @Generated
   public static AlcBridge method65() {
      return field33;
   }

   @Generated
   public static void method66(AlcBridge bridge5_30) {
      field33 = bridge5_30;
   }

   @Generated
   public static GlFenceSyncBridge method67() {
      return field34;
   }

   @Generated
   public static void method68(GlFenceSyncBridge bridge_110) {
      field34 = bridge_110;
   }

   @Generated
   public static GameProfilePropertyMerger method69() {
      return field35;
   }

   @Generated
   public static void method70(GameProfilePropertyMerger horsestats260) {
      field35 = horsestats260;
   }
}
