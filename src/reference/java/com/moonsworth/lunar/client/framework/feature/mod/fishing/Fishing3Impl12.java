package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fishing3Impl12 extends Fishing3_3 {
   private static final List<String> field1 = List.of(
      "It is certain",
      "It is decidedly so",
      "Without a doubt",
      "Yes definitely",
      "You may rely on it",
      "As I see it, yes",
      "Most likely",
      "Outlook good",
      "Yes",
      "Signs point to yes",
      "Reply hazy, try again",
      "Ask again later",
      "Better not tell you now",
      "Cannot predict now",
      "Concentrate and ask again",
      "Don't count on it",
      "My reply is no",
      "My sources say no",
      "Outlook not so good",
      "Very doubtful"
   );

   @Override
   public String getCommand() {
      return "eightball";
   }

   @Override
   public List<String> getAliases() {
      return List.of("8ball");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      String var5 = field1.get(ThreadLocalRandom.current().nextInt(field1.size()));
      this.method6(var4, var5);
   }
}
