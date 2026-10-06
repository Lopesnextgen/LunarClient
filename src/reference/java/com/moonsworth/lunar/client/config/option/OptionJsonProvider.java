package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionFlag;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.jspecify.annotations.Nullable;

public class OptionJsonProvider implements OptionDataProvider, Cloneable {
   protected ClientOption<?> option;
   private @Nullable Set<OptionJsonProvider.Data> field1;

   public OptionJsonProvider(ClientOption<?> lightingextension1) {
      this.option = lightingextension1;
   }

   @Override
   public OptionDataProvider method7(ClientOption<?> lightingextension1) {
      try {
         OptionJsonProvider guiextension22 = (OptionJsonProvider)super.clone();
         guiextension22.option = lightingextension1;
         guiextension22.field1 = null;
         return guiextension22;
      } catch (CloneNotSupportedException clonenotsupportedexception3) {
         throw new AssertionError();
      }
   }

   @Override
   public void method4(OptionCategory lightingtype21, @Nullable String text2) {
      if (this.field1 == null) {
         this.field1 = new LinkedHashSet<>(2);
      }

      this.field1.add(new OptionJsonProvider.Data(lightingtype21, text2));
   }

   @Override
   public void method5() {
      if (this.field1 != null) {
         for (OptionJsonProvider.Data data2 : this.field1) {
            this.HORHROIOIOICIRHIOCOICHHHIHCIIO(data2.method1(), data2.id());
         }
      }
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.option.getId());
      json1.add("value", this.method4(this.option.getValue()));
      json1.add("default", this.method4(this.option.getDefaultValue()));
      json1.addProperty("isDefault", this.option.isDefault());
      String text2 = this.option.method3() + "Description";
      String text3 = this.option.OHROCHICOIOICHOCRROORRCIIICIHO(text2, new Object[0]);
      if (!text3.equals(text2)) {
         json1.addProperty("description", text3);
      }

      List list4 = this.option.getChildren();
      JsonArray array5 = new JsonArray(list4.size());

      for (ClientOption lightingextension7 : list4) {
         OptionDataProvider guiextension8 = (OptionDataProvider)lightingextension7.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (guiextension8 != null) {
            array5.add(guiextension8.provide());
         }
      }

      json1.add("children", array5);
      OptionDisplay nameplate411 = (OptionDisplay)this.option.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field2);
      if (nameplate411 != null) {
         nameplate411.method6(this.option, json1);
      }

      Set set12 = (Set)this.option.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field3);
      if (set12 != null && !set12.isEmpty()) {
         JsonArray array13 = new JsonArray(set12.size());

         for (OptionFlag lightingtype10 : set12) {
            array13.add(lightingtype10.getId());
         }

         json1.add("badges", array13);
      }

      if (!json1.has("name")) {
         json1.addProperty("name", this.option.getName());
      }

      com.moonsworth.lunar.client.config.option.NumberRule nameplate14 = (com.moonsworth.lunar.client.config.option.NumberRule)this.option
         .HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field7);
      if (nameplate14 != null) {
         nameplate14.method8(json1);
      }

      return json1;
   }

   private JsonPrimitive method4(Object obj1) {
      if (obj1 == null) {
         return new JsonPrimitive("null");
      } else {
         Class clazz2 = obj1.getClass();
         if (clazz2 == String.class) {
            return new JsonPrimitive((String)obj1);
         } else if (clazz2 == Boolean.class) {
            return new JsonPrimitive((Boolean)obj1);
         } else if (clazz2 == Integer.class) {
            return new JsonPrimitive((Integer)obj1);
         } else if (clazz2 == Float.class) {
            return new JsonPrimitive((Float)obj1);
         } else if (clazz2 == Double.class) {
            return new JsonPrimitive((Double)obj1);
         } else {
            return obj1 instanceof OptionEnumValue gui2extension3 ? new JsonPrimitive(gui2extension3.id()) : new JsonPrimitive(obj1.toString());
         }
      }
   }

   private class Data {
      private final OptionCategory field1;
      private final @Nullable String field2;

      private Data(OptionCategory lightingtype21, @Nullable String text2) {
         this.field1 = lightingtype21;
         this.field2 = text2;
      }

      public OptionCategory method1() {
         return this.field1;
      }

      public @Nullable String id() {
         return this.field2;
      }
   }
}
