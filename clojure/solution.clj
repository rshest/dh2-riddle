(ns solution
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]
        [clojure.tools.macro :only [symbol-macrolet]]))

(defn lefto [x y p]
  (all
    (membero x p)
    (membero y p)
    (matche [x y]
      ([['fleft  . _] ['left   . _]])
      ([['left   . _] ['center . _]])
      ([['center . _] ['right  . _]])
      ([['right  . _] ['fright . _]]))))

(defn nexto [x y p]
  (conde
    ((lefto x y p))
    ((lefto y x p))))

; "g" is a list of attribute lists for each guest, each in form:
; [Location, Name, Color, Drink, citY, Trinket]
(defn riddleo [g]
  (symbol-macrolet
    [_ (lvar)]
    (all
      ; 0. Structural constraint:
      ; The number of guests should be 5
      (== [_ _ _ _ _] g)

      ; 1. Constraints, as corresponding to the text
      ;  (following verbatim in comments):

      ; Madam Natsiou wore a jaunty blue hat
      (membero [_ 'natsiou 'blue _ _ _] g)
      ; Lady Winslow was at the far left
      (membero ['fleft 'winslow _ _ _ _] g)
      ; next to the guest wearing a red jacket.
      (membero ['left _ 'red _ _ _] g)
      ; The lady in white sat left of someone in green.
      (lefto [_ _ 'white _ _ _] [_ _ 'green _ _ _] g)
      ; I remember that white outfit because the woman spilled her beer all over it.
      (membero [_ _ 'white 'beer _ _] g)
      ; The traveler from Fraeport was dressed entirely in purple
      (membero [_ _ 'purple _ 'fraeport _] g)
      ; When one of the dinner guests bragged about her Bird Pendant
      ; the woman next to her said they were finer in Fraeport, where she lived.
      (nexto [_ _ _ _ _ 'pendant] [_ _ _ _ 'fraeport _] g)

      ; So Doctor Marcolla showed off a prized Snuff Tin,
      (membero [_ 'marcolla _ _ _ 'tin] g)
      ; at which the lady from Dunwall scoffed, saying it was no match for her Ring.
      (membero [_ _ _ _ 'dunwall 'ring] g)
      ; Someone else carried a valuable War Medal and when she saw it, the visitor from Baleton next to her
      (nexto [_ _ _ _ _ 'medal] [_ _ _ _ 'baleton _] g)
      ; almost spilled her neighor's absinthe.
      (nexto [_ _ _ 'absinthe _ _] [_ _ _ _ 'baleton _] g)
      ; Countess Contee raised her rum in toast.
      (membero [_ 'contee _ 'rum _ _] g)
      ; The lady from Karnaca, full of wine, jumped onto the table,
      (membero [ _ _ _ 'wine 'karnaca _] g)
      ; falling onto the guest in the center seat, spilling the poor woman's whiskey.
      (membero [ 'center _ _ 'whiskey _ _] g)
      ; Then Baroness Finch captivated them all with a story about her wild youth in Dabokva.
      (membero [_ 'finch _ _ 'dabokva _] g)

      ; 3. Implicit constraints:
      ; Pad with information about unmentioned atoms
      (membero ['left _ _ _ _ _] g)
      (membero ['right _ _ _ _ _] g)
      (membero ['fright _ _ _ _ _] g)
      (membero [_ _ _ _ _ 'diamond] g))))


(defn -main [& args]
  (let [sol (first (run* [g] (riddleo g)))]
    (println "Solution:" sol)))
