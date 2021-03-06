package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.controls;

import com.google.common.collect.ImmutableList;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.animations.IUIAnimationControl;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.animations.IUIAnimationTarget;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.UIAbstractAnimationPlayable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CapacityUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.def.IFunction3;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.def.IThrowingConsumer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.time.def.ITicker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressBoxing;

public abstract class UIAbstractAnimationControl
		extends UIAbstractAnimationPlayable
		implements IUIAnimationControl {
	private final List<IUIAnimationTarget> targets;
	private final List<Consumer<@Nonnull ? super IUIAnimationControl>> endActions = new ArrayList<>(CapacityUtilities.getInitialCapacitySmall());
	private boolean reversed = false;

	public UIAbstractAnimationControl(Iterable<? extends IUIAnimationTarget> targets, ITicker ticker) {
		super(ticker);
		this.targets = ImmutableList.copyOf(targets);
	}

	@Immutable
	protected static <R, TH extends Throwable> List<R> generateListFromFunction(Iterator<? extends IUIAnimationTarget> targets,
	                                                                            IFunction3<? super IUIAnimationTarget, ? super Integer, ? super Integer, ? extends R, ? extends TH> function)
			throws TH {
		@Immutable List<IUIAnimationTarget> targetsList = ImmutableList.copyOf(targets);
		final int[] index = {0};
		@SuppressWarnings("AutoBoxing") Integer size = targetsList.size();
		ImmutableList.Builder<R> ret = ImmutableList.builder();
		targetsList.forEach(IThrowingConsumer.executeNow(target -> {
			ret.add(AssertionUtilities.assertNonnull(function.apply(target, suppressBoxing(index[0]), size)));
			++index[0];
		}));
		return ret.build();
	}

	@Override
	public void render() {
		int[] index = {0};
		int size = getTargets().size();
		getTargets().forEach(target -> {
			target.accept(getProgressForTarget(target, index[0], size));
			++index[0];
		});
	}

	protected List<? extends IUIAnimationTarget> getTargets() { return targets; }

	protected abstract double getProgressForTarget(IUIAnimationTarget target, int index, int size);

	@Override
	protected long updateElapsed(long previousElapsed, long difference) {
		return getElapsed() + (isReversed() ? -1 : 1) * difference;
	}

	@Override
	public EnumUpdateResult update0() {
		EnumUpdateResult result = getResult();
		if (result == EnumUpdateResult.END)
			getEndActions().forEach(action -> action.accept(this));
		return result;
	}

	protected abstract EnumUpdateResult getResult();

	@Override
	public void onEnd(Consumer<@Nonnull ? super IUIAnimationControl> action) {
		getEndActions().add(action);
	}

	protected boolean isReversed() { return reversed; }

	protected void setReversed(boolean reversed) { this.reversed = reversed; }

	@Override
	public void pause() {
		update();
		setPlaying(false);
	}

	@Override
	public void reverse() {
		update();
		setReversed(!isReversed());
	}

	@Override
	public void reset() {
		update();
		setPlaying(false);
		setReversed(false);
		setElapsed(0);
	}

	@Override
	public void seek(long progress) {
		setElapsed(progress);
	}

	@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
	protected List<Consumer<@Nonnull ? super IUIAnimationControl>> getEndActions() {
		return endActions;
	}
}
