package gt.edu.umg.demodb.utils;

public enum RegisterStatus {

	disabled((byte) 0), active((byte) 1), unauthorized((byte) -99), 
	finalize((byte) 2), validated((byte) 3), acepted((byte) 4),
	amendment((byte) 99);

	private final Byte value;

	private RegisterStatus(Byte value) {
		this.value = value;
	}

	public Byte getValue() {
		return this.value;
	}

}
