package $group__.client.ui.coredeprecated.structures;

public enum EnumUIMouseClickResult {
	CLICK(true),
	DRAG(true),
	PASS(false);

	public final boolean result;

	EnumUIMouseClickResult(boolean result) {this.result = result;}
}