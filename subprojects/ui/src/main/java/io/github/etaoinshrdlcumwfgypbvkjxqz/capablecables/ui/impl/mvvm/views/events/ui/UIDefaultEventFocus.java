package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.events.ui;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.IUIViewContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.events.IUIEventFocus;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.events.IUIEventTarget;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.events.ui.UIDefaultEvent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IIdentifier;

import java.util.Optional;

public class UIDefaultEventFocus extends UIDefaultEvent implements IUIEventFocus {
	@Nullable
	private final IUIEventTarget relatedTarget;

	public UIDefaultEventFocus(IIdentifier type, boolean canBubble, boolean cancelable, IUIViewContext viewContext, IUIEventTarget target, @Nullable IUIEventTarget relatedTarget) {
		super(type, canBubble, cancelable, viewContext, target);
		this.relatedTarget = relatedTarget;
	}

	@Override
	public Optional<? extends IUIEventTarget> getRelatedTarget() { return Optional.ofNullable(relatedTarget); }
}
