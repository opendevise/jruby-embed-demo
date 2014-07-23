package demo;

import org.jruby.Ruby;
import org.jruby.RubyHash;
import org.jruby.RubySymbol;
import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

public class ExchangeDataWithRuby {
  public static void main (String[] args) {
    ScriptingContainer container = new ScriptingContainer();
    Ruby rubyRuntime = container.getProvider().getRuntime();
    
    Object coderay = container.runScriptlet("require 'coderay'\nCodeRay");
    Object[] params = new Object[2];
    params[0] = "sum = 2 * 2";
    params[1] = "ruby";
    Object tokens = container.callMethod(coderay, "scan", params, Object.class);
    String html = container.callMethod(tokens, "html", String.class);
    System.out.println(html);
    
    Object asciidoctor = container.runScriptlet("require 'asciidoctor'\nrequire 'asciidoctor-diagram'\nAsciidoctor");
    params = new Object[2];
    params[0] = "[source,ruby]\n----\nsum = 2 * 2\n----";
    params[1] = container.runScriptlet("{ attributes: 'source-highlighter=coderay' }");
    String output = container.callMethod(asciidoctor, "convert", params, String.class);
    System.out.println(output);
    
    Asciidoctor a = toJava(rubyRuntime, asciidoctor, Asciidoctor.class);
    RubyHash options = new RubyHash(rubyRuntime);
    //options.put(RubySymbol.newSymbol(rubyRuntime, "doctype"), RubySymbol.newSymbol(rubyRuntime, "inline"));
    options.put(RubySymbol.newSymbol(rubyRuntime, "safe"), RubySymbol.newSymbol(rubyRuntime, "safe"));
    //System.out.println(a.convert("*strong*", options));
    //System.out.println(toJava(container, tokens, Tokens.class).html());
    a.convert_file("sample-diagram.adoc", options);
  }
  
  public static <T> T toJava(ScriptingContainer container, Object rubyObject, Class<T> toType) {
	  return toJava(container.getProvider().getRuntime(), rubyObject, toType);
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T toJava(Ruby rubyRuntime, Object rubyObject, Class<T> toType) {
	  return (T) JavaEmbedUtils.rubyToJava(
			  rubyRuntime,
			  (IRubyObject) rubyObject,
			  toType);
  }
  
  public interface Tokens {
	  public String html();
  }
  
  public interface Asciidoctor {
	  public String convert(String source);
	  public String convert(String source, RubyHash options);
	  public void convert_file(String filename);
	  public void convert_file(String filename, RubyHash options);
  }
}
