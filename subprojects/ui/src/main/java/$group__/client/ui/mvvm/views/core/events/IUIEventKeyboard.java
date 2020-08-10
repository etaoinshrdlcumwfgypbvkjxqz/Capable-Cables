package $group__.client.ui.mvvm.views.core.events;

import $group__.client.ui.mvvm.core.structures.IUIDataKeyboardKeyPress;
import $group__.utilities.NamespaceUtilities;
import net.minecraft.util.ResourceLocation;

public interface IUIEventKeyboard extends IUIEvent {
	@SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
	String
			TYPE_KEY_DOWN_STRING = NamespaceUtilities.NAMESPACE_DEFAULT + NamespaceUtilities.NAMESPACE_SEPARATOR_DEFAULT + "keydown",
			TYPE_KEY_UP_STRING = NamespaceUtilities.NAMESPACE_DEFAULT + NamespaceUtilities.NAMESPACE_SEPARATOR_DEFAULT + "keyup";
	ResourceLocation
			TYPE_KEY_DOWN = new ResourceLocation(TYPE_KEY_DOWN_STRING),
			TYPE_KEY_UP = new ResourceLocation(TYPE_KEY_UP_STRING);

	IUIDataKeyboardKeyPress getData();
}