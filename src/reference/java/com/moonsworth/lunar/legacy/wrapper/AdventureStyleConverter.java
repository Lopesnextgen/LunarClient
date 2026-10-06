package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_25;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import lombok.NonNull;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.ClickEvent.Action;
import net.kyori.adventure.text.event.HoverEvent.ShowEntity;
import net.kyori.adventure.text.event.HoverEvent.ShowItem;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.util.Codec;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class AdventureStyleConverter {
   public static final GsonComponentSerializer field1 = GsonComponentSerializer.builder().legacyHoverEventSerializer(LegacyHoverEventSerializer.field1).build();
   public static final Codec<NBTTagCompound, String, IOException, IOException> field2 = new Codec<NBTTagCompound, String, IOException, IOException>() {
      @NonNull
      public NBTTagCompound method1(@NonNull String var1) {
         if (var1 == null) {
            throw new NullPointerException("encoded is marked non-null but is null");
         }

         try {
            return ThreadModuleDump63.MC_VERSION >= 1 ? JsonToNBT.getTagFromJson(var1) : (NBTTagCompound)JsonToNBT.func_150315_a$v1_7(var1);
         } catch (NBTException | ClassCastException var3) {
            throw new IOException(var3);
         }
      }

      @NonNull
      public String method2(@NonNull NBTTagCompound var1) {
         if (var1 == null) {
            throw new NullPointerException("decoded is marked non-null but is null");
         } else {
            return var1.toString();
         }
      }
   };

   public static EnumChatFormatting method1(TextColor var0) {
      return var0 == null ? null : method2(NamedTextColor.nearestTo(var0));
   }

   public static EnumChatFormatting method2(NamedTextColor var0) {
      if (var0 == NamedTextColor.BLACK) {
         return EnumChatFormatting.blackColor;
      } else if (var0 == NamedTextColor.DARK_BLUE) {
         return EnumChatFormatting.DARK_BLUE;
      } else if (var0 == NamedTextColor.DARK_GREEN) {
         return EnumChatFormatting.DARK_GREEN;
      } else if (var0 == NamedTextColor.DARK_AQUA) {
         return EnumChatFormatting.DARK_AQUA;
      } else if (var0 == NamedTextColor.DARK_RED) {
         return EnumChatFormatting.DARK_RED;
      } else if (var0 == NamedTextColor.DARK_PURPLE) {
         return EnumChatFormatting.DARK_PURPLE;
      } else if (var0 == NamedTextColor.GOLD) {
         return EnumChatFormatting.goldColor;
      } else if (var0 == NamedTextColor.GRAY) {
         return EnumChatFormatting.grayColor;
      } else if (var0 == NamedTextColor.DARK_GRAY) {
         return EnumChatFormatting.DARK_GRAY;
      } else if (var0 == NamedTextColor.BLUE) {
         return EnumChatFormatting.blueColor;
      } else if (var0 == NamedTextColor.GREEN) {
         return EnumChatFormatting.greenColor;
      } else if (var0 == NamedTextColor.AQUA) {
         return EnumChatFormatting.AQUA;
      } else if (var0 == NamedTextColor.RED) {
         return EnumChatFormatting.redColor;
      } else if (var0 == NamedTextColor.LIGHT_PURPLE) {
         return EnumChatFormatting.LIGHT_PURPLE;
      } else if (var0 == NamedTextColor.YELLOW) {
         return EnumChatFormatting.yellowColor;
      } else if (var0 == NamedTextColor.WHITE) {
         return EnumChatFormatting.WHITE;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Nullable
   public static NamedTextColor method3(@Nullable EnumChatFormatting var0) {
      if (var0 == null) {
         return null;
      } else if (var0 == EnumChatFormatting.blackColor) {
         return NamedTextColor.BLACK;
      } else if (var0 == EnumChatFormatting.DARK_BLUE) {
         return NamedTextColor.DARK_BLUE;
      } else if (var0 == EnumChatFormatting.DARK_GREEN) {
         return NamedTextColor.DARK_GREEN;
      } else if (var0 == EnumChatFormatting.DARK_AQUA) {
         return NamedTextColor.DARK_AQUA;
      } else if (var0 == EnumChatFormatting.DARK_RED) {
         return NamedTextColor.DARK_RED;
      } else if (var0 == EnumChatFormatting.DARK_PURPLE) {
         return NamedTextColor.DARK_PURPLE;
      } else if (var0 == EnumChatFormatting.goldColor) {
         return NamedTextColor.GOLD;
      } else if (var0 == EnumChatFormatting.grayColor) {
         return NamedTextColor.GRAY;
      } else if (var0 == EnumChatFormatting.DARK_GRAY) {
         return NamedTextColor.DARK_GRAY;
      } else if (var0 == EnumChatFormatting.blueColor) {
         return NamedTextColor.BLUE;
      } else if (var0 == EnumChatFormatting.greenColor) {
         return NamedTextColor.GREEN;
      } else if (var0 == EnumChatFormatting.AQUA) {
         return NamedTextColor.AQUA;
      } else if (var0 == EnumChatFormatting.redColor) {
         return NamedTextColor.RED;
      } else if (var0 == EnumChatFormatting.LIGHT_PURPLE) {
         return NamedTextColor.LIGHT_PURPLE;
      } else {
         return var0 == EnumChatFormatting.yellowColor ? NamedTextColor.YELLOW : NamedTextColor.WHITE;
      }
   }

   public static ClickEvent method4(net.kyori.adventure.text.event.ClickEvent var0) {
      if (var0 == null) {
         return null;
      }

      if (var0.action() == Action.OPEN_URL && ThreadModuleDump61.method3(var0.value())) {
         return null;
      }

      net.minecraft.util.text.event.ClickEvent.Action var1 = method5(var0.action());
      return var1 != null ? new ClickEvent(var1, var0.value()) : null;
   }

   public static net.minecraft.util.text.event.ClickEvent.Action method5(Action var0) {
      if (Objects.requireNonNull(var0) == Action.OPEN_URL) {
         return net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
      } else if (var0 == Action.OPEN_FILE) {
         return net.minecraft.util.text.event.ClickEvent.Action.OPEN_FILE;
      } else if (var0 == Action.RUN_COMMAND) {
         return net.minecraft.util.text.event.ClickEvent.Action.RUN_COMMAND;
      } else if (var0 == Action.SUGGEST_COMMAND) {
         return net.minecraft.util.text.event.ClickEvent.Action.SUGGEST_COMMAND;
      } else if (var0 == Action.CHANGE_PAGE && ThreadModuleDump63.MC_VERSION >= 1) {
         return net.minecraft.util.text.event.ClickEvent.Action.CHANGE_PAGE;
      } else if (var0 == Action.COPY_TO_CLIPBOARD) {
         return null;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static Action method6(net.minecraft.util.text.event.ClickEvent.Action var0) {
      if (Objects.requireNonNull(var0) == net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL) {
         return Action.OPEN_URL;
      } else if (var0 == net.minecraft.util.text.event.ClickEvent.Action.OPEN_FILE) {
         return Action.OPEN_FILE;
      } else if (var0 == net.minecraft.util.text.event.ClickEvent.Action.RUN_COMMAND) {
         return Action.RUN_COMMAND;
      } else if (var0 == net.minecraft.util.text.event.ClickEvent.Action.SUGGEST_COMMAND) {
         return Action.SUGGEST_COMMAND;
      } else if (ThreadModuleDump63.MC_VERSION <= 1 && var0 == net.minecraft.util.text.event.ClickEvent.Action.TWITCH_USER_INFO) {
         return null;
      } else if (ThreadModuleDump63.MC_VERSION >= 1 && var0 == net.minecraft.util.text.event.ClickEvent.Action.CHANGE_PAGE) {
         return Action.CHANGE_PAGE;
      } else {
         throw new IllegalArgumentException(var0.name());
      }
   }

   public static HoverEvent<?> method7(net.minecraft.event.HoverEvent var0) {
      net.minecraft.util.text.event.HoverEvent.Action var1 = var0.action;

      try {
         if (Objects.requireNonNull(var1) == net.minecraft.util.text.event.HoverEvent.Action.SHOW_ITEM) {
            TextComponent var4;
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               var4 = Component.text(var0.value.getUnformattedText());
            } else {
               var4 = Component.text(var0.value$v1_7.getUnformattedText());
            }

            return HoverEvent.showItem(LegacyHoverEventSerializer.field1.deserializeShowItem(var4));
         } else {
            Component var2;
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               var2 = method14(var0.value);
            } else {
               var2 = method14(var0.value$v1_7);
            }

            if (var1 == net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT) {
               return HoverEvent.showText(var2);
            } else if (ThreadModuleDump63.MC_VERSION < 5 && var1 == net.minecraft.util.text.event.HoverEvent.Action.SHOW_ACHIEVEMENT) {
               return HoverEvent.showText(var2);
            } else if (ThreadModuleDump63.MC_VERSION >= 1 && var1 == net.minecraft.util.text.event.HoverEvent.Action.SHOW_ENTITY) {
               return HoverEvent.showEntity(LegacyHoverEventSerializer.field1.deserializeShowEntity(var2, field1::deserialize));
            } else {
               throw new IllegalArgumentException();
            }
         }
      } catch (IOException var3) {
         throw new IllegalArgumentException("Failed to convert", var3);
      }
   }

   public static net.minecraft.util.text.event.HoverEvent.Action method8(net.kyori.adventure.text.event.HoverEvent.Action<?> var0) {
      if (var0 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_TEXT) {
         return net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;
      } else if (var0 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_ITEM) {
         return net.minecraft.util.text.event.HoverEvent.Action.SHOW_ITEM;
      } else if (ThreadModuleDump63.MC_VERSION >= 1 && var0 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_ENTITY) {
         return net.minecraft.util.text.event.HoverEvent.Action.SHOW_ENTITY;
      } else {
         throw new IllegalArgumentException(var0.toString());
      }
   }

   public static net.minecraft.event.HoverEvent method9(@Nullable HoverEvent<?> var0) {
      if (var0 == null) {
         return null;
      }

      net.kyori.adventure.text.event.HoverEvent.Action var1 = var0.action();

      try {
         if (var1 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_TEXT) {
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, method10((Component)var0.value()));
            }

            if (ThreadModuleDump63.MC_VERSION >= 1) {
               return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, method10((Component)var0.value()));
            }

            return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT, method11((Component)var0.value()));
         }

         if (var1 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_ITEM) {
            ShowItem var5 = (ShowItem)var0.value();
            Component var6 = LegacyHoverEventSerializer.field1.serializeShowItem(var5);
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_ITEM, method10(var6));
            }

            if (ThreadModuleDump63.MC_VERSION >= 1) {
               return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_ITEM, method10(var6));
            }

            return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_ITEM, method11(var6));
         }

         if (ThreadModuleDump63.MC_VERSION >= 1 && var1 == net.kyori.adventure.text.event.HoverEvent.Action.SHOW_ENTITY) {
            ShowEntity var2 = (ShowEntity)var0.value();
            Component var3 = LegacyHoverEventSerializer.field1.serializeShowEntity(var2, field1::serialize);
            if (ThreadModuleDump63.MC_VERSION >= 5) {
               return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_ENTITY, method10(var3));
            }

            return new net.minecraft.event.HoverEvent(net.minecraft.util.text.event.HoverEvent.Action.SHOW_ENTITY, method10(var3));
         }
      } catch (IOException var4) {
         throw new IllegalArgumentException("Failed to convert", var4);
      }

      throw new IllegalArgumentException(var0.toString());
   }

   @Annotation2(min = 1)
   public static IChatComponent method10(Component var0) {
      return var0 == null ? (IChatComponent)(new ChatComponentText("")) : (IChatComponent)AdventureTextBridge.asBridge(var0);
   }

   @Annotation2(max = 0)
   public static IChatComponent method11(Component var0) {
      return var0 == null ? (IChatComponent)(new ChatComponentText("")) : (IChatComponent)AdventureTextBridge.asBridge(var0);
   }

   @Annotation2(min = 1)
   public static Component method14(IChatComponent var0) {
      return (Component)(var0 == null ? Component.empty() : ((Bridge3_25)var0).moonBridge$asAdventureComponent());
   }

   @Annotation2(max = 0)
   public static Component method14(IChatComponent var0) {
      return (Component)(var0 == null ? Component.empty() : ((Bridge3_25)var0).moonBridge$asAdventureComponent());
   }

   @Nullable
   public static NBTTagCompound method14(@Nullable BinaryTagHolder var0) {
      if (var0 == null) {
         return null;
      }

      try {
         return (NBTTagCompound)var0.get(field2);
      } catch (IOException var2) {
         return null;
      }
   }

   @Nullable
   public static BinaryTagHolder method15(@Nullable NBTTagCompound var0) {
      if (var0 == null) {
         return null;
      }

      try {
         return BinaryTagHolder.encode(var0, field2);
      } catch (IOException var2) {
         return null;
      }
   }
}
