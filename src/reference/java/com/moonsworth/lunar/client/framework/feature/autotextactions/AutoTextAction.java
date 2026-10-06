package com.moonsworth.lunar.client.framework.feature.autotextactions;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoTextAction implements JsonConfigurable {
   protected final ToggleOption field1 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("active").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final TextOption field2 = (TextOption)OptionFactory.method12("triggerKey").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field3 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("regex").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field4 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("contains").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("caseSensitive").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field6 = (ToggleOption)OptionFactory.method7("hideMessage").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field7 = (ToggleOption)OptionFactory.method7("showTitleAction").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final TextOption field8 = (TextOption)OptionFactory.method12("titleText").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field9 = (ToggleOption)OptionFactory.method7("desktopNotification").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("ingameNotification").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("playSound").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private Pattern pattern = null;
   private String field12 = null;

   public AutoTextAction() {
   }

   public AutoTextAction method1() {
      JsonObject json1 = new JsonObject();
      this.method1(json1);
      AutoTextAction lighting3loader2 = new AutoTextAction();
      lighting3loader2.load(json1);
      return lighting3loader2;
   }

   public void load(JsonObject json1) {
      this.field1.load(json1);
      this.field2.load(json1);
      this.field3.load(json1);
      this.field4.load(json1);
      this.field5.load(json1);
      this.field6.load(json1);
      this.field7.load(json1);
      this.field8.load(json1);
      this.field9.load(json1);
      this.field10.load(json1);
      this.field11.load(json1);
   }

   public void method1(JsonObject json1) {
      this.field1.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field2.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field3.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field4.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field5.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field6.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field7.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field8.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field9.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field10.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      this.field11.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
   }

   public String getDisplayName() {
      TranslationManager foghandler281 = Ref.method4().method67();
      String text2 = (String)this.field2.get();
      if (text2 != null && !text2.trim().isEmpty()) {
         if (text2.length() > 8) {
            text2 = text2.substring(0, 8) + "...";
         }

         StringBuilder builder3 = new StringBuilder();
         builder3.append(text2);
         builder3.append(" (");
         if ((Boolean)this.field3.get()) {
            this.validate();
            if (this.pattern == null) {
               builder3.append(foghandler281.method2("gui.autoTextTriggers", "regexInvalid", new Object[0]));
            } else {
               builder3.append(foghandler281.method2("gui.autoTextTriggers", "regexValid", new Object[0]));
            }
         } else {
            if ((Boolean)this.field4.get()) {
               builder3.append(foghandler281.method2("gui.autoTextTriggers", "contains", new Object[0]));
            } else {
               builder3.append(foghandler281.method2("gui.autoTextTriggers", "literal", new Object[0]));
            }

            if ((Boolean)this.field5.get()) {
               builder3.append(", ");
               builder3.append(foghandler281.method2("gui.autoTextTriggers", "caseSensitive", new Object[0]));
            }
         }

         builder3.append(")");
         return builder3.toString();
      } else {
         return foghandler281.method2("gui.autoTextTriggers", "newTrigger", new Object[0]);
      }
   }

   public boolean matches(String text1) {
      if (!(Boolean)this.field1.get()) {
         return false;
      }

      String text2 = (String)this.field2.get();
      if (text2 == null || text2.trim().isEmpty()) {
         return false;
      }

      if ((Boolean)this.field3.get()) {
         this.validate();
         if (this.pattern == null) {
            return false;
         }

         Matcher matcher4 = this.pattern.matcher(text1);
         return matcher4.find();
      } else {
         if (!(Boolean)this.field5.get()) {
            text1 = text1.toLowerCase(Locale.ROOT);
            text2 = text2.toLowerCase(Locale.ROOT);
         }

         boolean flag3 = !(Boolean)this.field4.get();
         return flag3 ? text1.equals(text2) : text1.contains(text2);
      }
   }

   protected void validate() {
      String text1 = (String)this.field2.get();
      if (!(Boolean)this.field3.get()) {
         this.field12 = null;
      } else {
         if (!Objects.equals(this.field12, text1)) {
            this.field12 = text1;

            try {
               this.pattern = Pattern.compile(text1);
            } catch (Exception exception3) {
               this.pattern = null;
            }
         }
      }
   }
}
