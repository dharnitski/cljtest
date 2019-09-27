(ns cljtest.main-test
  (:require [clojure.test :refer :all]
            [cljtest.main :refer :all]
            [clojure.string :as str]
            [clj-log.core :refer :all]))

(testing "loging"
  (testing "mocked proxy"
    (with-redefs [cljtest.main/sayHello
                  (fn [hello]
                    (do
                      (is (= "Hello Proxied World!" hello))
                      nil))]
      (is (= nil (greeting))))))


(testing "mocked proxy"

; commented to avoid output polution 
;   (testing "call without nock with side effects"
;     (is (= nil (greetingNoProxy))))

  (testing "mock external function no assertion"
    (with-redefs [clojure.tools.logging/log* (fn [logger level throwable message] nil)]
      (is (= nil (greetingNoProxy)))))



  (testing "mock external function with assertion"
    (with-redefs [clojure.tools.logging/log*
                  (fn [logger level throwable message]
                    (do
                      (is (= :info level))
                      ; message is whole message as it is logged including timestamp and formatting
                      (is (str/includes? message "Hello Direct World!"))
                      nil))]
      (is (= nil (greetingNoProxy))))))