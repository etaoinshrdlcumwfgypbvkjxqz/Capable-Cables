package $group__.utilities.extensions;

import $group__.utilities.interfaces.IHasGenericClass;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Optional;

public abstract class ExtensionContainerAware<C extends IExtensionContainer<?, ?>>
		extends IHasGenericClass.Impl<C>
		implements IExtension<C> {
	protected WeakReference<C> container = new WeakReference<>(null);

	public ExtensionContainerAware(Class<C> genericClass) { super(genericClass); }

	@Override
	public void onExtensionAdded(C container) { setContainer(container); }

	@Override
	public void onExtensionRemoved() { setContainer(null); }

	protected Optional<C> getContainer() { return Optional.ofNullable(container.get()); }

	protected void setContainer(@Nullable C container) { this.container = new WeakReference<>(container); }
}
