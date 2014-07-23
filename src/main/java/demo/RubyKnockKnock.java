package demo;

import org.jruby.embed.ScriptingContainer;

public class RubyKnockKnock {
  public static void main (String[] args) {
    ScriptingContainer container = new ScriptingContainer();
    container.runScriptlet("puts 'Hello from Ruby!'");
  }
}
