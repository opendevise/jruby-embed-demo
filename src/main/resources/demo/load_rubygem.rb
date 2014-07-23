puts ENV.select {|(key, val)| key.start_with? 'GEM_'}
    .map {|(key, val)| %(#{key}: #{val}) }
require 'coderay'
puts CodeRay
