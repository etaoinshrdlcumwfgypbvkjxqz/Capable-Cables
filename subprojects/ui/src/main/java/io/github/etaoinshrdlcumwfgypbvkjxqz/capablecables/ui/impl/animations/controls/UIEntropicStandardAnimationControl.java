package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.controls;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.animations.IUIAnimationTarget;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.time.def.ITicker;

@Deprecated
public class UIEntropicStandardAnimationControl
		extends UIMonoDirectionalSimpleStandardAnimationControl {
	protected UIEntropicStandardAnimationControl(IUIAnimationTarget target, ITicker ticker, boolean autoPlay, long duration, long startDelay, long endDelay, long loop) {
		super(target, ticker, autoPlay, duration, startDelay, endDelay, loop);
	}
}
