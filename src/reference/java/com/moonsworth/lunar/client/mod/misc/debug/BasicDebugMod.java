package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.command.MixinCoreImpl;
import com.moonsworth.lunar.client.command.GreedyStringArgumentParser;
import com.moonsworth.lunar.client.command.IntegerArgumentParser;
import com.moonsworth.lunar.client.command.StringArgumentParser;
import com.moonsworth.lunar.client.command.PlayerArgumentParser;
import com.moonsworth.lunar.client.command.DurationArgumentParser;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.LinkedList;
import org.java_websocket.client.WebSocketClient;

public class BasicDebugMod extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("showDebugOutput").method31();
   private final EnumOption<DebugType> field9 = (EnumOption<DebugType>)OptionFactory.method10("debugType", DebugType.ALL)
      .method31();

   public BasicDebugMod() {
      super(false);
      this.method10(
         new ClientCommand(
            LiteralCommandNode.method1("testcmd")
               .method2(
                  LiteralCommandNode.method1("word")
                     .method2(
                        ArgumentCommandNode.method1("v", StringArgumentParser.field1).method8(arg0 -> Ref.method17("word: " + arg0.getString("v")))
                     )
               )
               .method2(
                  LiteralCommandNode.method1("greedy")
                     .method2(
                        ArgumentCommandNode.method1("v", GreedyStringArgumentParser.field1)
                           .method8(arg0 -> Ref.method17("greedy: " + arg0.getString("v")))
                     )
               )
               .method2(
                  LiteralCommandNode.method1("username")
                     .method2(
                        ArgumentCommandNode.method1("v", PlayerArgumentParser.field2)
                           .method8(arg0 -> Ref.method17("username: " + arg0.getString("v")))
                     )
               )
               .method2(
                  LiteralCommandNode.method1("int")
                     .method2(
                        ArgumentCommandNode.method1("v", IntegerArgumentParser.field1).method8(arg0 -> Ref.method17("int: " + arg0.getInteger("v")))
                     )
               )
               .method2(
                  LiteralCommandNode.method1("double")
                     .method2(
                        ArgumentCommandNode.method1("v", MixinCoreImpl.field1)
                           .method8(arg0 -> Ref.method17("double: " + arg0.getDouble("v")))
                     )
               )
               .method2(
                  LiteralCommandNode.method1("time")
                     .method2(
                        ArgumentCommandNode.method1("v", DurationArgumentParser.field1)
                           .method8(arg0 -> Ref.method17("time: " + arg0.getDuration("v").toMillis() + "ms"))
                     )
               )
         )
      );
   }

   public String getId() {
      return "BASIC_DEBUG_MOD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(this.field8, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9}));
      lightingextension231.method1("buttons", arg0 -> arg0.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("crashOOM").method4(() -> {
         LinkedList list0x = new LinkedList();

         while (true) {
            byte[] items1x = new byte[10485760];
            list0x.add(items1x);
         }
      }), OptionFactory.method14("disconnectAssetServer").method5(140.0F).method4(() -> Ref.method5().ifPresent(WebSocketClient::close))}));
      this.field8
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg1x -> LunarLogger.method3(
               "Debugging has been %s for %s type(s).", new Object[]{arg1x ? "enabled" : "disabled", ((DebugType)this.field9.get()).name()}
            )
         );
   }

   public boolean method2(DebugType gui2extension1) {
      if (!(Boolean)this.field8.get()) {
         return false;
      }

      DebugType gui2extension2 = (DebugType)this.field9.get();
      return gui2extension2 == DebugType.ALL || gui2extension2 == gui2extension1;
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method8().method11(this);
   }
}
