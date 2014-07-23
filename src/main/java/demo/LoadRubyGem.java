package demo;

import org.jruby.embed.ScriptingContainer;

import java.util.HashMap;
import java.util.Map;

public class LoadRubyGem {
  public static void main (String[] args) {
    String gem = args[0];
    ScriptingContainer container = new ScriptingContainer();
    @SuppressWarnings("unchecked")
	Map<String, String> env = container.getEnvironment();
    if (env.containsKey("GEM_HOME")) {
      System.out.println("Clearing reference to external GEM_HOME");
      Map<String, String> tweaked_env = new HashMap<String, String>(env);
      tweaked_env.remove("GEM_HOME");
      tweaked_env.remove("GEM_PATH");
      container.setEnvironment(tweaked_env);
    }
    Object result = container.runScriptlet(
        "puts %(GEM_HOME=#{ENV['GEM_HOME'] || '<not set>'})\n" +
        "puts %(GEM_PATH=#{ENV['GEM_PATH'] || '<not set>'})\n" + 
        "require '" + gem + "'");
    System.out.println(result);
  }
}
