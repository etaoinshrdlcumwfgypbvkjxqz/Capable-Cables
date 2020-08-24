package $group__.client.ui.mvvm.views.events.ui;

import $group__.client.ui.core.mvvm.views.events.IUIEventMouse;
import $group__.client.ui.core.mvvm.views.events.IUIEventTarget;
import $group__.client.ui.core.structures.IUIDataMouseButtonClick;
import $group__.client.ui.events.ui.UIEvent;
import $group__.utilities.interfaces.INamespacePrefixedString;

import javax.annotation.Nullable;
import java.util.Optional;

public class UIEventMouse extends UIEvent implements IUIEventMouse {
	protected final IUIDataMouseButtonClick data;
	@Nullable
	protected final IUIEventTarget relatedTarget;

	public UIEventMouse(INamespacePrefixedString type, boolean canBubble, boolean cancelable, IUIEventTarget target, IUIDataMouseButtonClick data, @Nullable IUIEventTarget relatedTarget) {
		super(type, canBubble, cancelable, target);
		this.data = data;
		this.relatedTarget = relatedTarget;
	}

	@Override
	public IUIDataMouseButtonClick getData() { return data; }

	@Override
	public Optional<IUIEventTarget> getRelatedTarget() { return Optional.ofNullable(relatedTarget); }

}
