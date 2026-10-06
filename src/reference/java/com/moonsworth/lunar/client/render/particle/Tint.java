package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;

public abstract class Tint {
   public Tint() {
   }

   public static TintSolid method1(JsonElement element0, MolangParser glintcolorizer3iterator1) {
      MolangExpression glintcolorizer_52 = MolangParser.field4;
      MolangExpression glintcolorizer_53 = MolangParser.field4;
      MolangExpression glintcolorizer_54 = MolangParser.field4;
      MolangExpression glintcolorizer_55 = MolangParser.field4;
      if (element0.isJsonPrimitive()) {
         String text6 = element0.getAsString();
         if (text6.startsWith("#") && (text6.length() == 7 || text6.length() == 9)) {
            try {
               int number7 = Integer.parseInt(text6.substring(1), 16);
               float value8 = (number7 >> 16 & 0xFF) / 255.0F;
               float value9 = (number7 >> 8 & 0xFF) / 255.0F;
               float value10 = (number7 & 0xFF) / 255.0F;
               float value11 = text6.length() == 9 ? number7 >> 24 & 0xFF : 1.0F;
               glintcolorizer_52 = new MolangValue(glintcolorizer3iterator1, new Constant(value8));
               glintcolorizer_53 = new MolangValue(glintcolorizer3iterator1, new Constant(value9));
               glintcolorizer_54 = new MolangValue(glintcolorizer3iterator1, new Constant(value10));
               glintcolorizer_55 = new MolangValue(glintcolorizer3iterator1, new Constant(value11));
            } catch (Exception exception12) {
            }
         }
      } else if (element0.isJsonArray()) {
         JsonArray array13 = element0.getAsJsonArray();
         if (array13.size() == 3 || array13.size() == 4) {
            glintcolorizer_52 = glintcolorizer3iterator1.method5(array13.get(0));
            glintcolorizer_53 = glintcolorizer3iterator1.method5(array13.get(1));
            glintcolorizer_54 = glintcolorizer3iterator1.method5(array13.get(2));
            if (array13.size() == 4) {
               glintcolorizer_55 = glintcolorizer3iterator1.method5(array13.get(3));
            }
         }
      }

      return new TintSolid(glintcolorizer_52, glintcolorizer_53, glintcolorizer_54, glintcolorizer_55);
   }

   public static Tint method2(JsonObject json0, MolangParser glintcolorizer3iterator1) {
      JsonElement element2 = json0.get("gradient");
      MolangExpression glintcolorizer_53 = MolangParser.field3;
      ArrayList list4 = new ArrayList();
      boolean flag5 = true;
      if (element2.isJsonObject()) {
         for (Entry entry7 : element2.getAsJsonObject().entrySet()) {
            list4.add(new TintGradient.ColorStop(Float.parseFloat((String)entry7.getKey()), method1((JsonElement)entry7.getValue(), glintcolorizer3iterator1)));
         }

         Collections.sort(list4, (arg0x, arg1x) -> arg0x.field1 > arg1x.field1 ? 1 : -1);
         flag5 = false;
      } else if (element2.isJsonArray()) {
         JsonArray array10 = element2.getAsJsonArray();
         int index11 = 0;

         for (JsonElement element9 : array10) {
            list4.add(new TintGradient.ColorStop((float)index11 / (array10.size() - 1), method1(element9, glintcolorizer3iterator1)));
            index11++;
         }
      }

      if (json0.has("interpolant")) {
         glintcolorizer_53 = glintcolorizer3iterator1.method5(json0.get("interpolant"));
      }

      return new TintGradient(list4, glintcolorizer_53, flag5);
   }

   public abstract void method3(BedrockParticle glintcolorizer4_21);

   public abstract JsonElement method4();
}
