package demo;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

public class RunRubyScript {
  public static void main (String[] args) {
    String filename = args[0];
    ScriptingContainer container = new ScriptingContainer();
    Object result = container.runScriptlet(PathType.CLASSPATH, filename);
    if (result != null) {
    	System.out.println(result);
    }
  }
}
