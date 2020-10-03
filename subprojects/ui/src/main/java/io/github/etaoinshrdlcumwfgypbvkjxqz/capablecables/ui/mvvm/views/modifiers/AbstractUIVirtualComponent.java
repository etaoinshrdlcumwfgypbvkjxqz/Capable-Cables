package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.mvvm.views.modifiers;

import com.google.common.collect.ImmutableMap;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.modifiers.EnumModifyStage;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.modifiers.IUIComponentModifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.modifiers.IUIVirtualComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.structures.shapes.descriptors.IShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.mvvm.views.components.UIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.ConstantSupplier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.references.OptionalWeakReference;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class AbstractUIVirtualComponent
		extends UIComponent
		implements IUIComponentModifier, IUIVirtualComponent {
	private final ThreadLocal<EnumModifyStage> threadLocalModifyStage = ThreadLocal.withInitial(ConstantSupplier.of(EnumModifyStage.NONE));
	private OptionalWeakReference<IUIComponent> targetComponent = new OptionalWeakReference<>(null);

	public AbstractUIVirtualComponent(IShapeDescriptor<?> shapeDescriptor) {
		super(ImmutableMap.of(), null, shapeDescriptor);
	}

	@Override
	public void setTargetComponent(@Nullable IUIComponent targetComponent) { this.targetComponent = new OptionalWeakReference<>(targetComponent); }

	@Override
	public Optional<? extends IUIComponent> getTargetComponent() { return targetComponent.getOptional(); }

	@Override
	public void advanceModifyStage()
			throws IllegalStateException {
		setModifyStage(getModifyStage().next());
	}

	@Override
	public void resetModifyStage() { setModifyStage(EnumModifyStage.NONE); }

	@Override
	public EnumModifyStage getModifyStage() { return AssertionUtilities.assertNonnull(getThreadLocalModifyStage().get()); }

	protected void setModifyStage(EnumModifyStage stage) {
		getThreadLocalModifyStage().set(stage);
	}

	protected ThreadLocal<EnumModifyStage> getThreadLocalModifyStage() { return threadLocalModifyStage; }
}
