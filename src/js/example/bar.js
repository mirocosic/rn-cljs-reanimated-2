export default () => {
  console.log("Hey from another JS module!")
}

export const miro = () => {
  console.log("Hey from miro fn!")
}

export const worklet = function someWorklet(greeting) {
  'worklet';
  console.log("Hey I'm running on the UI thread");
}