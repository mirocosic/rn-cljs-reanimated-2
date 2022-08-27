# React Native + ClojureScript + Shadow CLJS + Reanimated 2 using worklets

This example projects is using reanimated v2 with worklets and clojurescript. 

The problem this solves is that clojure compiler doesn't do babel transformations needed for reanimated 2 to work. 
So we bypass this via shadow cljs and separate JS files where worklets will live. These files are then processed by metro and babel and required in our CLJS codebase. 


## Running the app locally

```sh
$ npm i
$ npx shadow-cljs watch app
# wait for first compile to finish or expo gets confused 
# on another terminal tab/window:
$ npm start
```
This will run Expo DevTools at http://localhost:19002/

## Published app in Expo 
https://expo.dev/@miromiro/cljs-reanimated-2
