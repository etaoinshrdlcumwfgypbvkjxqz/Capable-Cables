package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.mvvm.viewmodels;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.models.IUIModel;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.core.mvvm.IUIMinecraftViewModel;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.viewmodels.UIDefaultViewModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UIDefaultMinecraftViewModel<M extends IUIModel>
		extends UIDefaultViewModel<M>
		implements IUIMinecraftViewModel<M> {
	public UIDefaultMinecraftViewModel(M model) { super(model); }

	@Override
	public void tick() {}
}