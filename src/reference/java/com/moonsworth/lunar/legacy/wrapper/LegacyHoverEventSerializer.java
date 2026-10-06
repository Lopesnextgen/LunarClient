package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.io.IOException;
import java.util.UUID;
import lombok.NonNull;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent.ShowEntity;
import net.kyori.adventure.text.event.HoverEvent.ShowItem;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.util.Codec;
import net.kyori.adventure.util.Codec.Decoder;
import net.kyori.adventure.util.Codec.Encoder;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public final class LegacyHoverEventSerializer implements net.kyori.adventure.text.serializer.gson.LegacyHoverEventSerializer {
   public static final LegacyHoverEventSerializer field1 = new LegacyHoverEventSerializer();
   private static final Codec<NBTTagCompound, String, NBTException, RuntimeException> field2 = Ref.MC_VERSION >= 1
      ? Codec.codec(JsonToNBT::getTagFromJson, NBTBase::toString)
      : Codec.codec(LegacyHoverEventSerializer::method1, NBTBase::toString);
   static final String field3 = "id";
   static final String field4 = "Count";
   static final String field5 = "tag";
   static final String field6 = "name";
   static final String field7 = "type";
   static final String field8 = "id";

   private LegacyHoverEventSerializer() {
   }

   @VersionGate(max = 0)
   private static NBTTagCompound method1(String text0) {
      return (NBTTagCompound)JsonToNBT.func_150315_a$v1_7(text0);
   }

   public ShowItem deserializeShowItem(Component component1) {
      String text2 = LegacyComponentSerializer.legacySection().serialize(component1);

      try {
         NBTTagCompound compound3 = (NBTTagCompound)field2.decode(text2);
         NBTTagCompound compound4 = compound3.getCompoundTag("tag");
         boolean flag5 = Ref.MC_VERSION >= 5 ? compound4.isEmpty() : (Ref.MC_VERSION <= 0 ? compound4.hasNoTags() : compound4.hasNoTags());
         return ShowItem.showItem(
            Key.key(compound3.getString("id")), compound3.hasKey("Count") ? compound3.getByte("Count") : 1, flag5 ? null : BinaryTagHolder.encode(compound4, field2)
         );
      } catch (NBTException nbtexception6) {
         throw new IOException(nbtexception6);
      }
   }

   public @NonNull ShowEntity deserializeShowEntity(Component component1, Decoder<Component, String, ? extends RuntimeException> decoder2) {
      String text3 = PlainTextComponentSerializer.plainText().serialize(component1);

      try {
         NBTTagCompound compound4 = (NBTTagCompound)field2.decode(text3);
         return ShowEntity.showEntity(Key.key(compound4.getString("type")), UUID.fromString(compound4.getString("id")), Component.text(compound4.getString("name")));
      } catch (NBTException nbtexception5) {
         throw new IOException(nbtexception5);
      }
   }

   @NonNull
   public Component serializeShowItem(@NonNull ShowItem showitem1) {
      if (showitem1 == null) {
         throw new NullPointerException("input is marked non-null but is null");
      }

      NBTTagCompound compound2 = new NBTTagCompound();
      if (Ref.MC_VERSION >= 1) {
         compound2.setString("id", showitem1.item().asString());
      } else {
         Item item3 = (Item)Item.itemRegistry$v1_7.getObject(showitem1.item().value());
         if (item3 != null) {
            compound2.setShort("id", (short)Item.getIdFromItem(item3));
         }
      }

      compound2.setByte("Count", (byte)showitem1.count());
      if (showitem1.nbt() != null) {
         try {
            compound2.setTag("tag", (NBTBase)showitem1.nbt().get(field2));
         } catch (NBTException nbtexception4) {
            throw new IOException(nbtexception4);
         }
      }

      return Component.text((String)field2.encode(compound2));
   }

   @NonNull
   public Component serializeShowEntity(ShowEntity showentity1, Encoder<Component, String, ? extends RuntimeException> encoder2) {
      NBTTagCompound compound3 = new NBTTagCompound();
      compound3.setString("id", showentity1.id().toString());
      compound3.setString("type", showentity1.type().asString());
      if (showentity1.name() != null) {
         compound3.setString("name", PlainTextComponentSerializer.plainText().serialize(showentity1.name()));
      }

      return Component.text((String)field2.encode(compound3));
   }
}
