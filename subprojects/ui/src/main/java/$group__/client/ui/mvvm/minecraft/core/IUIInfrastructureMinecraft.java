package $group__.client.ui.mvvm.minecraft.core;

import $group__.client.ui.mvvm.core.IUIInfrastructure;
import $group__.client.ui.mvvm.core.binding.IBinder;
import $group__.client.ui.mvvm.minecraft.core.views.IUIViewMinecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface IUIInfrastructureMinecraft<V extends IUIViewMinecraft<?>, VM extends IUIViewModelMinecraft<?>, B extends IBinder>
		extends IUIInfrastructure<V, VM, B> {
	default void initialize() {
		getViewModel().initialize();
		getView().initialize();
	}

	default void tick() {
		getViewModel().tick();
		getView().tick();
	}

	default void removed() {
		getViewModel().removed();
		getView().removed();
	}
}
