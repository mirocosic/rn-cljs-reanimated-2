import Animated, {
  useSharedValue,
  withTiming,
  withSpring,
  useAnimatedStyle,
  Easing,
  withDelay,
} from 'react-native-reanimated';

export const opacityWorklet = function () {
  'worklet';

  const opacity = useSharedValue(0)

  opacity.value = withDelay(500, withTiming(1,{duration: 2000}))

  const style = useAnimatedStyle(() => {
    return {
      opacity: opacity.value
    };
  });

  return style
}

export const widthWorklet = function () {
  'worklet';

  const shared = useSharedValue(0)

  shared.value = withDelay(500, withTiming(200,{duration: 2000}))

  const style = useAnimatedStyle(() => {
    return {
      width: shared.value,
      height: shared.value,
    };
  });

  return style
}