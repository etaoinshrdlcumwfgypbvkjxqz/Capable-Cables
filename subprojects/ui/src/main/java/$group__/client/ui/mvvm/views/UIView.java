package $group__.client.ui.mvvm.views;

import $group__.client.ui.mvvm.core.binding.IBinderAction;
import $group__.client.ui.mvvm.core.extensions.IUIExtension;
import $group__.client.ui.mvvm.core.structures.IShapeDescriptor;
import $group__.client.ui.mvvm.core.views.IUIView;
import $group__.utilities.extensions.IExtensionContainer;
import $group__.utilities.specific.MapUtilities;
import com.google.common.collect.ImmutableMap;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;
import net.minecraft.util.ResourceLocation;

import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static $group__.utilities.CapacityUtilities.INITIAL_CAPACITY_SMALL;

public abstract class UIView<SD extends IShapeDescriptor<?, ?>>
		implements IUIView<SD> {
	protected final ConcurrentMap<ResourceLocation, IUIExtension<? extends IUIView<?>>> extensions = MapUtilities.getMapMakerSingleThreaded().initialCapacity(INITIAL_CAPACITY_SMALL).makeMap();
	protected final Subject<IBinderAction> binderNotifierSubject = UnicastSubject.create();

	@Override
	public ObservableSource<IBinderAction> getBinderNotifier() { return getBinderNotifierSubject(); }

	protected Subject<IBinderAction> getBinderNotifierSubject() { return binderNotifierSubject; }

	@Override
	public Optional<IUIExtension<? extends IUIView<?>>> getExtension(ResourceLocation key) { return Optional.ofNullable(getExtensions().get(key)); }

	@Override
	public Optional<IUIExtension<? extends IUIView<?>>> addExtension(IUIExtension<? extends IUIView<?>> extension) {
		IUIExtension.RegUIExtension.checkExtensionRegistered(extension);
		return IExtensionContainer.addExtension(this, getExtensions(), extension.getType().getKey(), extension);
	}

	@Override
	public Optional<IUIExtension<? extends IUIView<?>>> removeExtension(ResourceLocation key) { return IExtensionContainer.removeExtension(getExtensions(), key); }

	@Override
	public Map<ResourceLocation, IUIExtension<? extends IUIView<?>>> getExtensionsView() { return ImmutableMap.copyOf(getExtensions()); }

	@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
	protected ConcurrentMap<ResourceLocation, IUIExtension<? extends IUIView<?>>> getExtensions() { return extensions; }

	@Override
	public boolean reshape(Function<? super SD, Boolean> action) throws ConcurrentModificationException { return getShapeDescriptor().modify(getShapeDescriptor(), action); }
}
