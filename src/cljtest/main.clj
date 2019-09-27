
(ns cljtest.main
  (:require [clj-log.core :refer :all]))

(defn sayHello
  "print Hello world"
  [hello]
  (log :info hello))

; log is macro and cannot be mocked. Use mockable proxy function sayHello 
(defn greeting
  "print Hello world"
  []
  (sayHello "Hello Proxied World!"))

; use log function directly to see if we can somehow mock it
(defn greetingNoProxy
  "print Hello world"
  []
  (log :info "Hello Direct World!"))

(defn -main
  "I don't do a whole lot."
  [& args]
  (greeting))