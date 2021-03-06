package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.shapes.interactions;

import com.google.common.collect.Iterators;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.shapes.interactions.IShapeDescriptorProvider;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.utilities.EnumUISide;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.references.OptionalWeakReference;
import sun.misc.Cleaner;

import java.util.Optional;

public final class ImmutableShapeAnchor
		extends AbstractShapeAnchor {
	private final OptionalWeakReference<IShapeDescriptorProvider> target;
	private final EnumUISide originSide, targetSide;
	private final double borderThickness;

	public ImmutableShapeAnchor(IShapeDescriptorProvider target, EnumUISide originSide, EnumUISide targetSide, double borderThickness) {
		this.target = OptionalWeakReference.of(target);
		this.originSide = originSide;
		this.targetSide = targetSide;
		this.borderThickness = borderThickness;
		Cleaner.create(target, () ->
				getContainer().ifPresent(c ->
						c.removeSides(Iterators.singletonIterator(getOriginSide()))));
	}

	@Override
	public Optional<? extends IShapeDescriptorProvider> getTarget() { return target.getOptional(); }

	@Override
	public EnumUISide getOriginSide() { return originSide; }

	@Override
	public EnumUISide getTargetSide() { return targetSide; }

	@Override
	public double getBorderThickness() { return borderThickness; }
}
