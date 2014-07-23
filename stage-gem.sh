#!/bin/sh

rvm jruby@jruby-embed-demo 'do' gem install -i local-gems "$@" --no-rdoc --no-ri
