require 'prawn'

Prawn::Document.generate 'test.pdf' do
  text 'Hello, PDF!'
end
