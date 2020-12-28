package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.inputs.impl;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AbstractDelegatingObject;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.inputs.core.IKeyboardDevice;

public final class ImmutableKeyboardDevice
		extends AbstractDelegatingObject<IKeyboardDevice>
		implements IKeyboardDevice {
	private ImmutableKeyboardDevice(IKeyboardDevice delegate) {
		super(delegate);
	}

	public static ImmutableKeyboardDevice of(IKeyboardDevice delegate) {
		return new ImmutableKeyboardDevice(delegate);
	}

	@Override
	public boolean isKeyDown(int key) {
		return getDelegate().isKeyDown(key);
	}
}
