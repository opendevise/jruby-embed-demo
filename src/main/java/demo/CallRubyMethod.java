package demo;

import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.ScriptingContainer;

public class CallRubyMethod {
  public static void main (String[] args) {
    ScriptingContainer container = new ScriptingContainer(LocalVariableBehavior.PERSISTENT);
    
    container.runScriptlet("x = 144");
    container.runScriptlet("puts x");
  }
}
