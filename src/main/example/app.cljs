(ns example.app
  (:require [example.events]
            [example.subs]
            [example.widgets :refer [button]]
            [expo.root :as expo-root]
            ["expo-status-bar" :refer [StatusBar]]
            [re-frame.core :as rf]
            ["react-native" :as rn]
            [reagent.core :as r]
            ["react-native-reanimated" :refer [default withSpring] :rename {default reanimated}]))

(def worklets (js/require "../src/js/example/bar.js"))

(defn root* []
  (let [counter @(rf/subscribe [:get-counter])
        tap-enabled? @(rf/subscribe [:counter-tappable?])
        opacity-style (.opacityWorklet worklets)
        width-worklet (.widthWorklet worklets)
        width-style (.-style ^js width-worklet)
        shared-val (.-shared ^js width-worklet)]

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
       "Tap me, I'll count"]

      [button {:on-press #(set! (.-value shared-val) (withSpring (+ 50 (rand-int 200)))) ;; se if this can be moved to worklets too.
               :disabled? (not tap-enabled?)
               :style {:background-color :blue}}
       "Tap me, I'll animate"]]

     [:>
      (.-View reanimated)
      {:style [{:backgroundColor "red" :width 200 :height 100}
               opacity-style
               width-style]}]
     [:> StatusBar {:style "auto"}]]))

(defn root []
  [:f> root*])

(defn start
  {:dev/after-load true}
  []
  (expo-root/render-root (r/as-element [root])))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (start))