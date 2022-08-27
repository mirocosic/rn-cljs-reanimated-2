(ns example.app
  (:require [example.events]
            [example.subs]
            [example.widgets :refer [button]]
            [expo.root :as expo-root]
            ["expo-status-bar" :refer [StatusBar]]
            [re-frame.core :as rf]
            ["react-native" :as rn]
            [reagent.core :as r]
            ["./bar" :as js-module :refer [miro worklet]]
            ["react-native-reanimated" :refer [default] :rename {default reanimated}]))


(def shadow-splash (js/require "../assets/shadow-cljs.png"))
(def cljs-splash (js/require "../assets/cljs.png"))


(defn root []
  (let [counter @(rf/subscribe [:get-counter])
        tap-enabled? @(rf/subscribe [:counter-tappable?])]

    (println "HEY!")
    (println js-module)
    (miro)
    (println worklet)
    (worklet)

    ;(cljs.pprint/pprint reanimated)
    (println (.-View reanimated))

    [:> rn/View {:style {:flex 1
                         :padding-vertical 50
                         :justify-content :space-between
                         :align-items :center
                         :background-color :white}}
     [:> rn/View {:style {:align-items :center}}
      [:> rn/Text {:style {:font-weight   :bold
                           :font-size     72
                           :color         :blue
                           :margin-bottom 20}} counter]
      [button {:on-press #(rf/dispatch [:inc-counter])
               :disabled? (not tap-enabled?)
               :style {:background-color :blue}}
       "Tap me, I'll count"]]

     [:> (.-View reanimated) {:style {:background-color "red" :width 200 :height 100}}]
     [:> StatusBar {:style "auto"}]]))

(defn start
  {:dev/after-load true}
  []
  (expo-root/render-root (r/as-element [root])))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (start))