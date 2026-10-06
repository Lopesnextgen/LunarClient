package com.moonsworth.lunar.client.mod.render.nickhider;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.nickhider.NicknameListener;
import com.moonsworth.lunar.client.framework.feature.nickhider.Nickhider;
import com.moonsworth.lunar.client.framework.feature.nickhider.NickHiderBridge;
import com.moonsworth.lunar.client.framework.feature.nickhider.NicknameTransformerCache;
import com.moonsworth.lunar.client.framework.feature.nickhider.NickhiderImpl;
import com.moonsworth.lunar.client.event.player.EventPlayerJoinWorld;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerPing;
import com.moonsworth.lunar.client.event.mixin.gui.EventPlayerListEntry.EventPlayerListAdd;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class NickHider extends AbstractFeature {
   private static final ResourceLocationBridge STEVE_SKIN = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");
   private static final ResourceLocationBridge ALEX_SKIN = ResourceLocationBridge.create("minecraft", "textures/skins/slim/alex.png");
   private static final Pattern LOBBY_MESSAGE_PATTERN = Pattern.compile("Sending to server (?<lobbyName>([a-zA-Z0-9])+)(...|!)");
   private static final Pattern HUB_JOIN_PATTERN = Pattern.compile("Request join for Hub (\\#[0-9]+ \\()?(mini|mega)[0-9]+[A-Z]\\)?(...|!)");
   private static final Pattern SENDING_PATTERN = Pattern.compile("Sending you to (mini|mega)[0-9]+[A-Z](...|!)");
   private final ToggleOption hideName = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideName")
         .method4(true))
      .method31();
   private final ToggleOption hideRealName = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideRealName")
         .method4(true))
      .method31();
   private final ToggleOption hideOthersNames = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideOthersNames")
      .method31();
   private final ToggleOption hideOwnSkin = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideOwnSkin")
         .method4(true))
      .method31();
   private final ToggleOption useRealSkin = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("useRealSkin")
         .method4(true))
      .method31();
   private final ToggleOption hideOthersSkin = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideOthersSkin")
      .method31();
   private final ToggleOption hideLobbyID = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideLobbyID")
      .method31();
   private String ownNameInput;
   private String prefixInput;
   private String suffixInput;
   private final TextOption ownName = (TextOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12("ownName")
         .method2("You"))
      .method4(this::setOwnNameInput)
      .method5(arg1 -> {
         if (this.method1(arg1, "Nickname")) {
            this.refreshSelfNickname();
         } else {
            this.ownName.method10(this.ownNameInput);
         }
      })
      .method7(arg1 -> {
         if (((String)this.ownName.get()).length() >= 16 && !TextOption.method1(arg1)) {
            return null;
         } else if (arg1 == ' ') {
            return '_';
         } else {
            return !"abcdefghijklmnopqrstuvwxyz0123456789_\b".contains(String.valueOf(arg1).toLowerCase(Locale.ROOT)) ? null : arg1;
         }
      })
      .method31();
   private final TextOption hiddenPrefix = (TextOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12("hiddenPrefix")
         .method2("Player"))
      .method4(this::setPrefixInput)
      .method5(arg1 -> {
         if (this.method1(arg1, "Prefix")) {
            this.method15();
         } else {
            this.hiddenPrefix.method10(this.prefixInput);
         }
      })
      .method7(arg1 -> {
         if (((String)this.hiddenPrefix.get()).length() >= 16 && arg1 != '\b') {
            return null;
         } else if (arg1 == ' ') {
            return '_';
         } else {
            return !"abcdefghijklmnopqrstuvwxyz0123456789_\b".contains(String.valueOf(arg1).toLowerCase(Locale.ROOT)) ? null : arg1;
         }
      })
      .method31();
   private final ToggleOption customSuffix = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customSuffix")
      .method31();
   private final TextOption hiddenSuffix = (TextOption)com.moonsworth.lunar.client.config.option.OptionFactory.method12("hiddenSuffix")
      .method4(this::setSuffixInput)
      .method5(arg1 -> {
         if (this.method1(arg1, "Suffix")) {
            this.method15();
         } else {
            this.hiddenSuffix.method10(this.suffixInput);
         }
      })
      .method7(arg1 -> {
         if (((String)this.hiddenSuffix.get()).length() >= 16 && arg1 != '\b') {
            return null;
         } else if (arg1 == ' ') {
            return '_';
         } else {
            return !"abcdefghijklmnopqrstuvwxyz0123456789_\b".contains(String.valueOf(arg1).toLowerCase(Locale.ROOT)) ? null : arg1;
         }
      })
      .method31();
   private final Pattern NAME_PATTERN = Pattern.compile("([a-zA-Z0-9_]{3,16})");
   private ImmutableList<Nickhider> nicknames = ImmutableList.of();
   private final AtomicInteger nicknameCounter = new AtomicInteger();
   private final NicknameTransformerCache<String, String> nicknameCache = new NicknameTransformerCache<String, String>() {
      protected NickhiderImpl method1(String text1) {
         return NickhiderImpl.method1(text1);
      }

      protected String method3(NickhiderImpl nickhiderimpl1) {
         return nickhiderimpl1.method7();
      }
   };
   private final LoadingCache<Component, Component> componentCache = CacheBuilder.newBuilder().maximumSize(5000L).build(new CacheLoader<Component, Component>() {
      public Component method1(Component component1) {
         return NickHider.this.transformComponent(component1);
      }
   });
   public static String realSkinLocation;
   private final NicknameListener nicknameListener;
   private String currentNickname = "";

   public NickHider() {
      super(false);
      this.nicknameListener = new NicknameListener(this);
      this.handle(EventPlayerListAdd.class, this::onPlayerListAdd);
      this.handle(EventPlayerJoinWorld.class, this::onPlayerJoinWorld);
      this.handle(EventServerPing.class, this::onServerPing);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onTypedChatMessage);
   }

   public String getId() {
      return "NICK_HIDER";
   }

   private boolean method1(String text1, String text2) {
      if (text1.isBlank() && !"Suffix".equals(text2)) {
         Ref.method4().method69().method3(text2 + " cannot be empty!");
         return false;
      } else {
         return !this.method2(text1);
      }
   }

   private boolean method2(String text1) {
      ArrayList list2 = new ArrayList(Ref.method4().method71().method2());
      list2.add(Ref.method4().method71().method4());

      for (Pattern pattern4 : list2) {
         if (pattern4.matcher(text1).find()) {
            return true;
         }
      }

      return false;
   }

   public void method3(boolean flag1) {
      if (this.nicknames != null) {
         this.clean();
         if (Ref.method7() != null && Ref.method7().bridge$getSendQueue() != null && flag1) {
            this.refreshSelfNickname();
            this.hideAllPlayerNames();
         }

         if (!this.currentNickname.isEmpty()) {
            this.method14();
         }
      }
   }

   private void hideProfileName(GameProfile gameprofile1) {
      if (!Ref.method4().method40().method64().method13()) {
         if (gameprofile1 != null && this.field4.method31() != null) {
            String text2 = ChatFormatting.getTextWithoutFormattingCodes(gameprofile1.getName());
            boolean flag3 = text2.equals(this.mc.bridge$getSession().bridge$getUsername());
            boolean flag4 = text2.equals(this.nicknameListener.method6()) || !this.currentNickname.isEmpty() && text2.equals(this.currentNickname);
            boolean flag5 = !flag3 && !flag4 && (Boolean)this.hideOthersNames.get();
            boolean flag6 = !this.currentNickname.isEmpty();
            if (flag3 && ((Boolean)this.hideRealName.get() || flag6) || flag4 && ((Boolean)this.hideName.get() || flag6) || flag5) {
               this.addNickname(text2, flag3 || flag4);
            }
         }
      }
   }

   public void setNickname(String text1) {
      if (text1 != null && !text1.isEmpty()) {
         this.currentNickname = text1;
         this.method14();
      } else {
         this.method13();
      }
   }

   public void method13() {
      if (!this.currentNickname.isEmpty()) {
         this.currentNickname = "";
         this.nicknames = this.nicknames.stream().filter(arg0 -> !arg0.method3()).collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
         this.method34();
         if (this.isEnabled()) {
            this.refreshSelfNickname();
         }
      }
   }

   private void method14() {
      this.nicknames = this.nicknames.stream().filter(arg0 -> !arg0.method3()).collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
      Horsestats horsestats1 = this.mc.bridge$getSession();
      if (horsestats1 != null) {
         this.addNickname(horsestats1.bridge$getUsername(), true);
      }

      if (!this.currentNickname.isEmpty()) {
         this.addNickname(this.currentNickname, true);
      }

      if (!this.nicknameListener.method6().isEmpty()) {
         this.addNickname(this.nicknameListener.method6(), true);
      }

      this.method34();
   }

   public void removeNickname(String text1) {
      this.method15(text1)
         .ifPresent(
            arg1x -> {
               this.nicknames = this.nicknames
                  .stream()
                  .filter(arg1xx -> !arg1xx.equals(arg1x))
                  .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
               this.method34();
            }
         );
   }

   public String transformTextCached(String text1) {
      if (text1 == null) {
         return null;
      } else {
         return !this.isEnabled() && this.currentNickname.isEmpty() ? text1 : (String)this.nicknameCache.method4(text1);
      }
   }

   public Component transformComponentCached(Component component1) {
      if (component1 == null) {
         return null;
      }

      if (!this.isEnabled() && this.currentNickname.isEmpty()) {
         return component1;
      }

      try {
         return (Component)this.componentCache.get(component1);
      } catch (ExecutionException | UncheckedExecutionException executionexception3) {
         return component1;
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1("nameOptions", arg1x -> {
         arg1x.method7(this.hideName, arg1xx -> arg1xx.method9(new ClientOption[]{this.ownName}));
         arg1x.method9(new ClientOption[]{this.hideRealName});
         arg1x.method7(this.hideOthersNames, arg1xx -> {
            arg1xx.method9(new ClientOption[]{this.hiddenPrefix});
            arg1xx.method7(this.customSuffix, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.hiddenSuffix}));
         });
         arg1x.method9(new ClientOption[]{this.hideLobbyID});
      });
      lightingextension231.method1(
         "skinOptions", arg1x -> arg1x.method9(new ClientOption[]{this.hideOwnSkin, this.useRealSkin, this.hideOthersSkin})
      );
      this.hideName.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (arg1x) {
            this.refreshSelfNickname();
         } else {
            this.nicknameListener.method4(false);
         }
      });
      this.hideRealName.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (arg1x) {
            this.nicknameListener.method4(false);
            this.refreshSelfNickname();
         } else {
            this.removeSelfNickname();
            if ((Boolean)this.hideName.get()) {
               this.nicknameListener.method3();
            }
         }
      });
      this.hideOthersNames
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg1x -> {
               if (arg1x) {
                  this.hideAllPlayerNames();
               } else {
                  this.nicknames = this.nicknames
                     .stream()
                     .filter(arg0 -> !arg0.method3())
                     .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
                  this.method34();
               }
            }
         );
      this.customSuffix.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method15());
   }

   private void method15() {
      this.clean();
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 != null) {
         itemcounter6extension1.bridge$getPlayerEntities().forEach(arg1x -> this.hideProfileName(arg1x.bridge$getGameProfile()));
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method3(new String[]{"Sk1er"}).method2(new String[]{"nick hider", "nickhider"}).method11(this);
   }

   public boolean method14(String text1) {
      return this.NAME_PATTERN.matcher(text1).matches() && text1.length() >= 3 && text1.length() <= 16;
   }

   private Optional<Nickhider> method15(String text1) {
      return this.nicknames.stream().filter(arg1x -> arg1x.method1().equalsIgnoreCase(text1)).findFirst();
   }

   public ResourceLocationBridge getPlayerSkin(UUID uuid1, String text2, ResourceLocationBridge horsestats143) {
      if (this.isEnabled() && Ref.method7() != null) {
         boolean flag4 = Ref.method7().bridge$getUniqueID().equals(uuid1);
         if (flag4) {
            if ((Boolean)this.useRealSkin.get() && HologramsIterator2.method15() != null) {
               return HologramsIterator2.method15();
            }

            if ((Boolean)this.hideOwnSkin.get()) {
               return this.getDefaultSkin(text2);
            }
         } else if ((Boolean)this.hideOthersSkin.get()) {
            return this.getDefaultSkin(text2);
         }

         return horsestats143;
      } else {
         return horsestats143;
      }
   }

   @Nullable
   public String getPlayerName(UUID uuid1, String text2) {
      if (this.isEnabled() && Ref.method7() != null) {
         boolean flag3 = Ref.method7().bridge$getUniqueID().equals(uuid1);
         return flag3 && this.useRealSkin.get() ? field32 : text2;
      } else {
         return text2;
      }
   }

   public static boolean isDefaultSkin(ResourceLocationBridge horsestats140) {
      return field8.equals(horsestats140) || field9.equals(horsestats140);
   }

   private ResourceLocationBridge getDefaultSkin(String text1) {
      return text1 != null && !text1.equals("default") ? field9 : field8;
   }

   private String method20(boolean flag1) {
      if (flag1) {
         if (!this.currentNickname.isEmpty()) {
            return this.currentNickname;
         } else {
            return this.hideRealName.get() ? (String)this.ownName.get() : this.mc.bridge$getSession().bridge$getUsername();
         }
      } else {
         return this.customSuffix.get()
            ? (String)this.hiddenPrefix.get() + (String)this.hiddenSuffix.get()
            : (String)this.hiddenPrefix.get() + "-" + this.nicknameCounter.incrementAndGet();
      }
   }

   public void addNickname(String text1, boolean flag2) {
      boolean flag3 = this.isEnabled() || flag2 && !this.currentNickname.isEmpty();
      if (flag3 && text1 != null && !this.method15(text1).isPresent() && this.method14(text1)) {
         String text4 = this.method20(flag2);
         Nickhider nickhider5 = new Nickhider(Pattern.compile(Pattern.quote(text1), 2), text1, text4, flag2);
         ArrayList list6 = new ArrayList(this.nicknames);
         list6.add(nickhider5);
         Comparator comparator7 = Comparator.comparingInt(arg0 -> arg0.method1().length());
         list6.sort(comparator7.reversed());
         this.nicknames = ImmutableList.copyOf(list6);
         this.method34();
      }
   }

   private void onTypedChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.CCCCRIIIHIIRHOCIIOICICHHROHHRC());
      if ((Boolean)this.hideLobbyID.get() && (field10.matcher(text2).matches() || field12.matcher(text2).matches() || field11.matcher(text2).matches())) {
         data1.setCancelled(true);
      }
   }

   private void onServerPing(EventServerPing highlightimpl51) {
      this.currentNickname = "";
      this.clean();
      this.refreshSelfNickname();
   }

   private void onPlayerJoinWorld(EventPlayerJoinWorld highlightimpl4_21) {
      this.hideProfileName(highlightimpl4_21.method1().bridge$getGameProfile());
   }

   private void onPlayerListAdd(EventPlayerListAdd data141) {
      this.hideProfileName(data141.getGameProfile());
   }

   private void refreshSelfNickname() {
      Horsestats horsestats1 = this.mc.bridge$getSession();
      if (horsestats1 != null) {
         String text2 = horsestats1.bridge$getUsername();
         if ((Boolean)this.hideRealName.get()) {
            this.removeNickname(text2);
            this.addNickname(text2, true);
         }

         if ((Boolean)this.hideName.get()) {
            this.nicknameListener.method3();
         }
      }
   }

   private void removeSelfNickname() {
      this.removeNickname(this.mc.bridge$getSession().bridge$getUsername());
      this.nicknameListener.method4(false);
   }

   private void hideAllPlayerNames() {
      if ((Boolean)this.hideOthersNames.get() && Ref.method7() != null) {
         Ref.method7()
            .bridge$getSendQueue()
            .bridge$getPlayerInfoMap()
            .stream()
            .<GameProfile>map(PlayerInfoBridge::bridge$getGameProfile)
            .filter(
               arg1 -> arg1 != null
                  && !arg1.getName().equals(this.mc.bridge$getSession().bridge$getUsername())
                  && !arg1.getName().equals(this.nicknameListener.method6())
            )
            .forEach(this::hideProfileName);
      }
   }

   private void clean() {
      this.nicknames = ImmutableList.of();
      this.nicknameCounter.set(0);
      this.method34();
   }

   public void method29(BiConsumer<Pattern, String> biconsumer1) {
      boolean flag2 = !this.currentNickname.isEmpty();
      if ((Boolean)this.hideName.get() || (Boolean)this.hideOthersNames.get() || (Boolean)this.hideRealName.get() || flag2) {
         UnmodifiableIterator unmodifiableiterator3 = this.nicknames.iterator();

         while (unmodifiableiterator3.hasNext()) {
            Nickhider nickhider4 = (Nickhider)unmodifiableiterator3.next();
            boolean flag5 = nickhider4.method3() && ((Boolean)this.hideName.get() || (Boolean)this.hideRealName.get() || flag2)
               || !nickhider4.method3() && (Boolean)this.hideOthersNames.get();
            if (flag5) {
               biconsumer1.accept(nickhider4.getPattern(), nickhider4.method2());
            }
         }
      }
   }

   public String transformName(String text1) {
      String text2 = text1;
      boolean flag3 = !this.currentNickname.isEmpty();
      if ((Boolean)this.hideName.get() || (Boolean)this.hideOthersNames.get() || (Boolean)this.hideRealName.get() || flag3) {
         UnmodifiableIterator unmodifiableiterator4 = this.nicknames.iterator();

         while (unmodifiableiterator4.hasNext()) {
            Nickhider nickhider5 = (Nickhider)unmodifiableiterator4.next();
            boolean flag6 = nickhider5.method3() && ((Boolean)this.hideName.get() || (Boolean)this.hideRealName.get() || flag3)
               || !nickhider5.method3() && (Boolean)this.hideOthersNames.get();
            if (flag6) {
               text2 = nickhider5.getPattern().matcher(text2).replaceAll(nickhider5.method2());
            }
         }
      }

      return text2;
   }

   public Component transformComponent(Component component1) {
      Component component2 = component1;
      boolean flag3 = !this.currentNickname.isEmpty();
      if ((Boolean)this.hideName.get() || (Boolean)this.hideOthersNames.get() || (Boolean)this.hideRealName.get() || flag3) {
         boolean flag4 = (Boolean)this.hideName.get() || (Boolean)this.hideRealName.get() || flag3;
         UnmodifiableIterator unmodifiableiterator5 = this.nicknames.iterator();

         while (unmodifiableiterator5.hasNext()) {
            Nickhider nickhider6 = (Nickhider)unmodifiableiterator5.next();
            if (nickhider6.method3() ? flag4 : (Boolean)this.hideOthersNames.get()) {
               component2 = nickhider6.method4().transform(component2);
            }
         }
      }

      return component2;
   }

   public void method34() {
      this.nicknameCache.invalidateAll();
      this.componentCache.invalidateAll();
      this.field4.method106().ifPresent(NickHiderBridge::method1);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.method34();
      if (Ref.method7() != null && Ref.method7().bridge$getSendQueue() != null && this.isEnabled()) {
         this.refreshSelfNickname();
         this.hideAllPlayerNames();
      }

      if (json1.has("lastKnownHypixelNick")) {
         this.nicknameListener.method7(json1.get("lastKnownHypixelNick").getAsString());
      }
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      if (!this.nicknameListener.method6().isEmpty()) {
         json1.addProperty("lastKnownHypixelNick", this.nicknameListener.method6());
      }
   }

   public boolean method34(PlayerInfoBridge bridge2_331) {
      return IslandUtils.isOnIsland() && TextBridge.getTextContent(bridge2_331.bridge$formatName()).contains("Server: ");
   }

   @Generated
   public ToggleOption getHideLobbyID() {
      return this.hideLobbyID;
   }

   @Generated
   public void setOwnNameInput(String text1) {
      this.ownNameInput = text1;
   }

   @Generated
   public void setPrefixInput(String text1) {
      this.prefixInput = text1;
   }

   @Generated
   public void setSuffixInput(String text1) {
      this.suffixInput = text1;
   }

   @Generated
   public NicknameListener method39() {
      return this.nicknameListener;
   }

   @Generated
   public String getCurrentNickname() {
      return this.currentNickname;
   }
}
