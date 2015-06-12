;;Test examples built to start a discuss about testing style
;;Run in repl top to bottom

(use 'midje.repl)

(defn remove-at [m k]
  (dissoc m k))

(defn do-something [m]
  (-> m (remove-at :b)))


(fact "do-something returns the expected map"
  (let [m {:a 1
           :b "This"
           :c "Hello"
           :d {:e {:f "Test"}}
           :g 3}]
           (do-something m) => {:a 1
                                :c "Hello"
                                :d {:e {:f "Test"}}
                                :g 3}))


(fact "remove unused keys from map"
  (let [m {:a 1
           :b "This"
           :c "Hello"
           :d {:e {:f "Test"}}
           :g 3}]
           (do-something m) => {:a 1
                                :c "Hello"
                                :d {:e {:f "Test"}}
                                :g 3}))



(fact "if a is passed in as 1 then we expect it to be in the map as 1 so that we can render it"
  (let [m {:a 1
           :b "This"
           :c "Hello"
           :d {:e {:f "Test"}}
           :g 3}]
           (:a (do-something m)) => 1))


(fact "if b is passed in we expect it to be filtered out"
  (let [m {:a 1
           :b "This"
           :c "Hello"
           :d {:e {:f "Test"}}
           :g 3}]
           (:b (do-something m)) => nil))


(facts "prepare data to be rendered, filter out unused keys"
  (let [m {:a 1
           :b "This"
           :c "Hello"
           :d {:e {:f "Test"}}
           :g 3}
        result (do-something m)]
          (fact "if b is passed in we expect it to be filtered out"
            (:b result) => nil)
          (fact "if a is passed in as 1 then we expect it to be in the map as 1 so that we can render it"
            (:a result) => 1)))
