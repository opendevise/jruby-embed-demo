package demo;

import java.util.HashMap;
import java.util.Map;

import org.jruby.Ruby;
import org.jruby.RubySymbol;
import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

public class AsciidoctorJLite {
	
	public static void main(String[] args) {
		ScriptingContainer container = new ScriptingContainer();
		Ruby runtime = container.getProvider().getRuntime();
		
		Asciidoctor asciidoctor = AsciidoctorJ.create(container);
		String html = asciidoctor.convert("*strong*");
		System.out.println(html);
		
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(
			RubySymbol.newSymbol(runtime, "doctype"),
			RubySymbol.newSymbol(runtime, "inline"));
		html = asciidoctor.convert("*strong*", options);
		System.out.println(html);
	}
	
	public interface Asciidoctor {
		public String convert(String source);
		public String convert(String source, Map<Object, Object> options);
		public void convert_file(String filename);
		public void convert_file(String filename, Map<Object, Object> options);
	}

	public static class AsciidoctorJ {
		public static Asciidoctor create() {
			return create(new ScriptingContainer());
		}
		
		public static Asciidoctor create(ScriptingContainer container) {
			Object asciidoctor = container.runScriptlet("require 'asciidoctor'\nAsciidoctor");
			return toJava(container, asciidoctor, Asciidoctor.class);
		}
		
		public static <T> T toJava(ScriptingContainer container, Object rubyObject,
				Class<T> toType) {
			return toJava(container.getProvider().getRuntime(), rubyObject, toType);
		}
		
		@SuppressWarnings("unchecked")
		public static <T> T toJava(Ruby rubyRuntime, Object rubyObject,
				Class<T> toType) {
			return (T) JavaEmbedUtils.rubyToJava(rubyRuntime,
					(IRubyObject) rubyObject, toType);
		}
	}
}
