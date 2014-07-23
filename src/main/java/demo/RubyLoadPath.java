package demo;

import org.jruby.embed.ScriptingContainer;

public class RubyLoadPath {
  public static void main (String[] args) {
    ScriptingContainer container = new ScriptingContainer();
    container.runScriptlet("puts $LOAD_PATH * \"\\n\"");
  }
}
